package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.NoticeSearchCondition;
import issac.issac_server.notice.application.dto.response.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.response.NoticeResponse;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.notice.exception.NoticeErrorCode;
import issac.issac_server.notice.exception.NoticeException;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.user.domain.University;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.SortOrder;
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery;
import org.opensearch.client.opensearch._types.query_dsl.Operator;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.opensearch.client.opensearch._types.query_dsl.BoolQuery.Builder;

@Component
@RequiredArgsConstructor
public class NoticeFinder {

    private final OpenSearchClient openSearchClient;

    public Page<NoticePreviewResponse> search(NoticeSearchCondition condition, Pageable pageable) {
        try {
            SearchRequest request = buildSearchRequest(condition, pageable);

            SearchResponse<NoticePreviewResponse> response = openSearchClient.search(request, NoticePreviewResponse.class);

            List<NoticePreviewResponse> notices = extractNoticePreviewResponses(response);

            return new PageImpl<>(notices, pageable, response.hits().total().value());

        } catch (IllegalArgumentException e) {
            throw new NoticeException(NoticeErrorCode.INVALID_SEARCH_CONDITION);
        } catch (Exception e) {
            throw new NoticeException(NoticeErrorCode.SEARCH_FAILED);
        }
    }

    public NoticeResponse find(String noticeId) {
        try {
            SearchRequest request = buildFindRequest(noticeId);

            SearchResponse<NoticeResponse> response = openSearchClient.search(request, NoticeResponse.class);

            return response.hits().hits().stream()
                    .findFirst()
                    .map(hit -> {
                        assert hit.source() != null;
                        hit.source().setId(hit.id());
                        return hit.source();
                    })
                    .orElseThrow(() -> new NoticeException(NoticeErrorCode.NOT_FOUND));
        } catch (NoticeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new NoticeException(NoticeErrorCode.SEARCH_FAILED);
        }
    }

    public Page<NoticePreviewResponse> findByReactions(Page<Reaction> reactions) {

        List<String> noticeIds = reactions.getContent().stream()
                .map(Reaction::getTargetId)
                .collect(Collectors.toList());

        if (noticeIds.isEmpty()) {
            return Page.empty(); // ID가 없으면 빈 페이지 반환
        }

        SearchRequest request = buildFindByIdsRequest(noticeIds);

        // Elasticsearch 호출
        try {
            SearchResponse<NoticePreviewResponse> response = openSearchClient.search(request, NoticePreviewResponse.class);

            List<NoticePreviewResponse> notices = extractNoticePreviewResponses(response);

            return new PageImpl<>(notices, reactions.getPageable(), reactions.getTotalElements());
        } catch (Exception e) {
            throw new NoticeException(NoticeErrorCode.SEARCH_FAILED);
        }
    }

    private SearchRequest buildSearchRequest(NoticeSearchCondition condition, Pageable pageable) {
        return SearchRequest.of(searchRequest -> searchRequest
                .index("notice")
                .source(source -> source
                        .filter(filter -> filter
                                .includes(NoticePreviewResponse.getFieldNames())
                        )
                )
                .query(query -> query.bool(bool -> {
                    universityEq(bool, condition.getUniversity());
                    sourceEq(bool, condition.getSource());
                    keywordContain(bool, condition.getKeyword());
                    return bool;
                }))
                .from((int) pageable.getOffset())
                .size(pageable.getPageSize())
                .sort(sort -> sort.field(f -> f
                        .field("createdDate") // 날짜 필드
                        .order(SortOrder.Desc) // 최신순 정렬
                ))
                .trackTotalHits(v->v.enabled(true))
        );
    }

    private SearchRequest buildFindRequest(String noticeId) {
        return SearchRequest.of(searchRequest -> searchRequest
                .index("notice")
                .source(source -> source
                        .filter(filter -> filter
                                .includes(NoticeResponse.getFieldNames())
                        )
                )
                .query(query -> query.ids(ids -> ids.values(noticeId)))
        );
    }


    private SearchRequest buildFindByIdsRequest(List<String> noticeIds) {
        return SearchRequest.of(searchRequest -> searchRequest
                .index("notice") // Elasticsearch 인덱스 설정
                .source(source -> source
                        .filter(filter -> filter
                                .includes(NoticePreviewResponse.getFieldNames()) // 필요한 필드만 포함
                        )
                )
                .query(query -> query.ids(ids -> ids.values(noticeIds)))
                .sort(sort -> sort.field(f -> f
                        .field("createdDate") // 날짜 필드
                        .order(SortOrder.Desc) // 최신순 정렬
                ))
        );
    }

    private List<NoticePreviewResponse> extractNoticePreviewResponses(SearchResponse<NoticePreviewResponse> response) {
        return response.hits().hits().stream()
                .peek(hit -> {
                    assert hit.source() != null;
                    hit.source().setId(hit.id());
                })
                .map(Hit::source)
                .collect(Collectors.toList());
    }


    private void universityEq(Builder bool, University university) {
        if (university != null) {
            bool.filter(filter -> filter
                    .term(term -> term
                            .field("university")
                            .value(FieldValue.of(university.toString()))
                    )
            );
        }
    }

    private void sourceEq(BoolQuery.Builder bool, List<NoticeSource> sources) {
        if (sources != null && !sources.isEmpty()) {
            bool.filter(filter -> filter
                    .terms(terms -> terms
                            .field("source")
                            .terms(termsQuery -> termsQuery
                                    .value(sources.stream()
                                            .map(source -> FieldValue.of(source.toString()))
                                            .collect(Collectors.toList()))
                            )
                    )
            );
        }
    }

    private void keywordContain(Builder bool, String keyword) {
        if (StringUtils.hasText(keyword)) {
            // ✅ `should` 내부에서 title과 content 중 하나라도 검색어 포함하면 검색됨
            bool.must(must -> must
                    .bool(subBool -> subBool
                            .should(should -> should
                                    .match(match -> match
                                            .field("title")
                                            .query(FieldValue.of(keyword))
                                            .operator(Operator.And)
                                    ))
                            .should(should -> should
                                    .match(match -> match
                                            .field("content")
                                            .query(FieldValue.of(keyword))
                                            .operator(Operator.And)
                                    ))
                            .minimumShouldMatch("1") // ✅ 하나라도 만족하면 검색됨
                    ));
        }
    }

}
