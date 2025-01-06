package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.NoticeResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import issac.issac_server.notice.exception.NoticeErrorCode;
import issac.issac_server.notice.exception.NoticeException;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoticeFinder {

    private final OpenSearchClient openSearchClient;

    public Page<NoticeResponse> search(NoticeSearchCondition condition, Pageable pageable) {
        try {
            SearchRequest request = buildSearchRequest(condition, pageable);

            SearchResponse<NoticeResponse> response = openSearchClient.search(request, NoticeResponse.class);

            List<NoticeResponse> notices = response.hits().hits().stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());

            return new PageImpl<>(notices, pageable, response.hits().total().value());
        } catch (IllegalArgumentException e) {
            throw new NoticeException(NoticeErrorCode.INVALID_SEARCH_CONDITION);
        } catch (Exception e) {
            throw new NoticeException(NoticeErrorCode.SEARCH_FAILED);
        }
    }

    private SearchRequest buildSearchRequest(NoticeSearchCondition condition, Pageable pageable) {
        return SearchRequest.of(searchRequest -> searchRequest
                .index("notice")
                .source(source -> source
                        .filter(filter -> filter
                                .includes(NoticeResponse.getFieldNames())
                        )
                )
                .query(query -> query.bool(bool -> {
                    universityEq(bool, condition);
                    sourceEq(bool, condition);
                    titleContain(bool, condition);
                    return bool;
                }))
                .from((int) pageable.getOffset())
                .size(pageable.getPageSize())
        );
    }

    private void universityEq(org.opensearch.client.opensearch._types.query_dsl.BoolQuery.Builder bool, NoticeSearchCondition condition) {
        if (condition.getUniversity() != null) {
            bool.filter(filter -> filter
                    .term(term -> term
                            .field("university.keyword")
                            .value(FieldValue.of(condition.getUniversity().toString()))
                    )
            );
        }
    }

    private void sourceEq(org.opensearch.client.opensearch._types.query_dsl.BoolQuery.Builder bool, NoticeSearchCondition condition) {
        if (condition.getSource() != null) {
            bool.filter(filter -> filter
                    .term(term -> term
                            .field("source.keyword")
                            .value(FieldValue.of(condition.getSource().toString()))
                    )
            );
        }
    }

    private void titleContain(org.opensearch.client.opensearch._types.query_dsl.BoolQuery.Builder bool, NoticeSearchCondition condition) {
        if (condition.getTitle() != null && !condition.getTitle().isEmpty()) {
            bool.should(should -> should
                    .wildcard(wildcard -> wildcard
                            .field("title")
                            .value("*" + condition.getTitle() + "*")
                    )
            );
        }
    }
}
