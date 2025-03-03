package issac.issac_server.notice.constant;

import issac.issac_server.notice.application.dto.request.NoticeCreateRequest;
import issac.issac_server.notice.application.dto.request.NoticeFileRequest;
import issac.issac_server.notice.application.dto.response.NoticeFileResponse;
import issac.issac_server.notice.application.dto.response.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.response.NoticeResponse;
import issac.issac_server.user.domain.University;

import java.util.Arrays;
import java.util.List;

import static issac.issac_server.notice.domain.NoticeSource.ACADEMIC_NOTICE;

public class NoticeFactory {


    public static NoticeCreateRequest createMockNoticeCreateRequest() {
        return new NoticeCreateRequest(
                University.YONSEI,
                ACADEMIC_NOTICE,
                "공통",
                "인문예술진흥사업단, 2025-1 후배 사랑 한끼 나눔 사업 신청 안내",
                "<div class='cont_area'><p></p><div class='fr-view'><p><img src='https://www.yonsei.ac.kr/_attach/editor_image/2024-12/llsuwckpzjua.jpg' class='fr-fic fr-dib' data-height='5846' data-alt='' data-width='4134' data-path='/_attach/editor_image/2024-12/llsuwckpzjua.jpg' data-file_name='2025-1 후배 사랑 한끼 나눔 사업 포스터.jpg' data-success='true' data-size='2459893'><br></p><p class='0' style='text-align:center;background:#ffffff;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;'>인문예술진흥사업단, 2025-1 후배 사랑 한끼 나눔 사업 신청 안내(~25.1.5.)</p><p class='0' style='text-align:center;background:#ffffff;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;'><br></p><p class='0' style='text-align:center;background:#ffffff;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;font-weight:bold;font-size:14.0pt;background:#ffffff;'><!--[if !supportEmptyParas]--> <!--[endif]--></p><p>□ 목적</p><p>▷ 본교 문과대학 졸업생 기부자의 기부 취지에 따라 본교 문과대학생 중 소득분위 0-1분위에 해당하는 학생들에게 식비를 지원함</p><p>▷ 식비를 경감하고 생활비 부담을 완화하여 학생들이 학업에 전념할 수 있도록 함&nbsp;</p><p><br></p><p>□ 대상</p><p>▷ 문과대학 소속 학부생(휴학생 포함)</p><p><br></p><p>□ 선발 인원</p><p>▷ 학기당 4~5명</p><p><br></p><p>□ 지원 자격</p><p>▷ 소득분위 0~1분위(한국장학재단 기준)</p><p><br></p><p>□ 지원 금액</p><p>▷ 1인당 60만원(식대 6,000원, 100회 기준)</p><p><br></p><p>□ 지원 방법</p><p>▷ 생협 모바일 상품권 40만원 + 장학금 20만원</p><p><br></p><p>□ 제출 서류</p><p>▷ 인문예술진흥사업단 홈페이지에 사업 공고(<span style='color: rgb(0, 0, 255);'><a href='http://humanart.yonsei.ac.kr'><u style='text-underline:#0000ff single;'>humanart.yonsei.ac.kr</u></a></span>)</p><p>→ 신청서를 사업단 이메일로 제출(신청서 별첨): 메일 제목은 ‘후배 사랑 한끼 나눔 지원사업 신청_000(이름)’ 형식으로 작성</p><p><br></p><p>□ 일정</p><p>▷ 신청 및 접수: 2024년 12월 18일 ~ 2025년 1월 5일</p><p>▷ 발표: 2025년 1월 말(개별 연락)</p><p>▷ 사업기간: 2025년 3월 ~ 2025년 8월</p><p><br></p><p>□ 문의</p><p>연세대학교 인문예술진흥사업단</p><p>02-2123-7920</p><p>humanart@yonsei.ac.kr</p></div><p></p></div>",
                "인문예술진흥사업단, 2025-1 후배 사랑 한끼 나눔 사업 신청 안내(~25.1.5.)",
                "인문예술진흥사업단",
                "2024-12-17",
                createMockNoticeFileRequests(),
                "https://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=241809&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&search:search_key:search_or=article_text&search:search_key2:search_or=article_title&search:search_val:search_or=%25C0%25CE%25B9%25AE%25BF%25B9%25BC%25FA%25C1%25F8%25C8%25EF%25BB%25E7%25BE%25F7%25B4%25DC&board_no=15"
        );
    }

    public static List<NoticeFileRequest> createMockNoticeFileRequests() {
        return Arrays.asList(createMockNoticeFileRequest(), createMockNoticeFileRequest());
    }

    public static NoticeFileRequest createMockNoticeFileRequest() {
        return new NoticeFileRequest("2025학년도_1학기_복학_안내문_241227.pdf", "https://www.yonsei.ac.kr/_custom/yonsei/_common/board/download.jsp?attach_no=114577");
    }

    public static NoticeResponse createMockNoticeResponse() {
        return NoticeResponse.builder()
                .id("fGhOO5QBORDZUx8puAN_")
                .source(ACADEMIC_NOTICE)
                .subCategory("공통")
                .title("인문예술진흥사업단, 2025-1 후배 사랑 한끼 나눔 사업 신청 안내")
                .rawContent("<div class='cont_area'><p></p><div class='fr-view'><p><img src='https://www.yonsei.ac.kr/_attach/editor_image/2024-12/llsuwckpzjua.jpg' class='fr-fic fr-dib' data-height='5846' data-alt='' data-width='4134' data-path='/_attach/editor_image/2024-12/llsuwckpzjua.jpg' data-file_name='2025-1 후배 사랑 한끼 나눔 사업 포스터.jpg' data-success='true' data-size='2459893'><br></p><p class='0' style='text-align:center;background:#ffffff;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;'>인문예술진흥사업단, 2025-1 후배 사랑 한끼 나눔 사업 신청 안내(~25.1.5.)</p><p class='0' style='text-align:center;background:#ffffff;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;'><br></p><p class='0' style='text-align:center;background:#ffffff;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;font-weight:bold;font-size:14.0pt;background:#ffffff;'><!--[if !supportEmptyParas]--> <!--[endif]--></p><p>□ 목적</p><p>▷ 본교 문과대학 졸업생 기부자의 기부 취지에 따라 본교 문과대학생 중 소득분위 0-1분위에 해당하는 학생들에게 식비를 지원함</p><p>▷ 식비를 경감하고 생활비 부담을 완화하여 학생들이 학업에 전념할 수 있도록 함&nbsp;</p><p><br></p><p>□ 대상</p><p>▷ 문과대학 소속 학부생(휴학생 포함)</p><p><br></p><p>□ 선발 인원</p><p>▷ 학기당 4~5명</p><p><br></p><p>□ 지원 자격</p><p>▷ 소득분위 0~1분위(한국장학재단 기준)</p><p><br></p><p>□ 지원 금액</p><p>▷ 1인당 60만원(식대 6,000원, 100회 기준)</p><p><br></p><p>□ 지원 방법</p><p>▷ 생협 모바일 상품권 40만원 + 장학금 20만원</p><p><br></p><p>□ 제출 서류</p><p>▷ 인문예술진흥사업단 홈페이지에 사업 공고(<span style='color: rgb(0, 0, 255);'><a href='http://humanart.yonsei.ac.kr'><u style='text-underline:#0000ff single;'>humanart.yonsei.ac.kr</u></a></span>)</p><p>→ 신청서를 사업단 이메일로 제출(신청서 별첨): 메일 제목은 ‘후배 사랑 한끼 나눔 지원사업 신청_000(이름)’ 형식으로 작성</p><p><br></p><p>□ 일정</p><p>▷ 신청 및 접수: 2024년 12월 18일 ~ 2025년 1월 5일</p><p>▷ 발표: 2025년 1월 말(개별 연락)</p><p>▷ 사업기간: 2025년 3월 ~ 2025년 8월</p><p><br></p><p>□ 문의</p><p>연세대학교 인문예술진흥사업단</p><p>02-2123-7920</p><p>humanart@yonsei.ac.kr</p></div><p></p></div>")
                .author("인문예술진흥사업단")
                .createdDate("2024-12-17")
                .scrap(false)
                .files(createMockNoticeFileResponses())
                .url("https://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=241809&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&search:search_key:search_or=article_text&search:search_key2:search_or=article_title&search:search_val:search_or=%25C0%25CE%25B9%25AE%25BF%25B9%25BC%25FA%25C1%25F8%25C8%25EF%25BB%25E7%25BE%25F7%25B4%25DC&board_no=15")
                .build();
    }

    public static List<NoticeFileResponse> createMockNoticeFileResponses() {
        return Arrays.asList(createMockNoticeFileResponse(), createMockNoticeFileResponse());
    }

    public static NoticeFileResponse createMockNoticeFileResponse() {
        return new NoticeFileResponse("2025학년도_1학기_복학_안내문_241227.pdf", "https://www.yonsei.ac.kr/_custom/yonsei/_common/board/download.jsp?attach_no=114577");
    }

    public static NoticePreviewResponse createMockNoticePreviewResponse() {
        return NoticePreviewResponse.builder()
                .id("fGhOO5QBORDZUx8puAN_")
                .source(ACADEMIC_NOTICE)
                .subCategory("공통")
                .title("인문예술진흥사업단, 2025-1 후배 사랑 한끼 나눔 사업 신청 안내")
                .author("인문예술진흥사업단")
                .createdDate("2024-12-17")
                .content("후배 사랑 한끼 나눔 사업 신청 안내입니다.")
                .build();
    }

    public static List<NoticePreviewResponse> createMockNoticePreviewResponses() {
        return Arrays.asList(createMockNoticePreviewResponse(), createMockNoticePreviewResponse());
    }

}
