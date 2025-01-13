package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.NoticeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeAppender {

    private final OpenSearchClient openSearchClient;

    public String append(NoticeCreateRequest request) {

        try {
            IndexRequest<Object> indexRequest = new IndexRequest.Builder<>()
                    .index("notice")
                    .document(request)
                    .build();

            IndexResponse response = openSearchClient.index(indexRequest);

            return response.id();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
