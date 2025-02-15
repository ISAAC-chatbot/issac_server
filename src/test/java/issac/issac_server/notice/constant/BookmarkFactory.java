package issac.issac_server.notice.constant;

import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.request.BookmarkUpdateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponse;
import issac.issac_server.notice.application.dto.response.BookmarkResponseV2;
import issac.issac_server.notice.domain.NoticeSource;

import java.util.Arrays;
import java.util.List;

import static issac.issac_server.notice.domain.NoticeSource.ACADEMIC_NOTICE;

public class BookmarkFactory {

    public static BookmarkUpdateRequest createMockBookmarkUpdateRequest() {
        return new BookmarkUpdateRequest(Arrays.asList(ACADEMIC_NOTICE, NoticeSource.COMPUTER_SCIENCE));
    }

    public static BookmarkCreateRequest createMockBookmarkCreateRequest() {
        return new BookmarkCreateRequest(ACADEMIC_NOTICE);
    }

    public static BookmarkResponse createMockBookmarkResponse() {
        return new BookmarkResponse(Arrays.asList(ACADEMIC_NOTICE, NoticeSource.COMPUTER_SCIENCE));
    }

    public static BookmarkResponseV2 createMockBookmarkResponseV2() {
        return new BookmarkResponseV2(ACADEMIC_NOTICE, true);
    }

    public static List<BookmarkResponseV2> createMockBookmarkResponsesV2() {
        return Arrays.asList(createMockBookmarkResponseV2(), createMockBookmarkResponseV2());
    }
}
