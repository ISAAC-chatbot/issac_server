package issac.issac_server.notice.domain;

import issac.issac_server.user.domain.University;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static issac.issac_server.user.domain.University.YONSEI;

@Getter
@RequiredArgsConstructor
public enum NoticeSource {

    ACADEMIC_NOTICE("학사공지", YONSEI, "https://www.yonsei.ac.kr/sc/"),
    SONGDO_DORM("송도학사", YONSEI, "https://yicdorm.yonsei.ac.kr/"),
    INTERNATIONAL_OFFICE("국제처", YONSEI, "https://oia.yonsei.ac.kr/"),
    RC_EDUCATION("RC교육원", YONSEI, "https://yicrc.yonsei.ac.kr/"),
    AI_CONVERGENCE_COLLEGE("인공지능융합대학", YONSEI, "https://computing.yonsei.ac.kr/"),
    MAIN_DORM("기숙사", YONSEI, "https://dorm.yonsei.ac.kr/"),
    COMPUTER_SCIENCE("컴퓨터 과학과", YONSEI, "https://cs.yonsei.ac.kr/"),
    BUSINESS_SCHOOL("경영대학", YONSEI, "https://ysb.yonsei.ac.kr/");

    private final String name;
    private final University parent;
    private final String url;
}
