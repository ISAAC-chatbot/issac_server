package issac.issac_server.notice.constant;

import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponse;
import issac.issac_server.notice.domain.NoticeSource;

import java.util.Arrays;

public class BookmarkFactory {

    public static BookmarkCreateRequest createMockBookmarkCreateRequest() {
        return new BookmarkCreateRequest(Arrays.asList(NoticeSource.ACADEMIC_NOTICE, NoticeSource.COMPUTER_SCIENCE));
    }

    public static BookmarkResponse createMockBookmarkResponse() {
        return new BookmarkResponse(Arrays.asList(NoticeSource.ACADEMIC_NOTICE, NoticeSource.COMPUTER_SCIENCE));
    }
}
