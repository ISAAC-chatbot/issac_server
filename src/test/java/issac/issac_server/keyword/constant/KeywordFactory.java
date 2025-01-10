package issac.issac_server.keyword.constant;

import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;

import java.util.Arrays;
import java.util.List;

public class KeywordFactory {

    public static KeywordRequest createMockKeywordRequest() {
        return new KeywordRequest("복학");
    }

    public static KeywordResponse createMockKeywordResponse() {
        return new KeywordResponse(1L, "복학");
    }

    public static List<KeywordResponse> createMockKeywordResponses() {
        return Arrays.asList(createMockKeywordResponse(), createMockKeywordResponse());
    }
}
