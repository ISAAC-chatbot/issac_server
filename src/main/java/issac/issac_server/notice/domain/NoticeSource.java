package issac.issac_server.notice.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeSource implements DescriptiveEnum {

    ACADEMIC_NOTICE("학사공지"),
    SONGDO_DORM("송도학사"),
    INTERNATIONAL_OFFICE("국제처"),
    RC_EDUCATION("RC교육원"),
    MAIN_DORM("기숙사"),

    // 문과대학
    LITERATURE_COLLEGE("문과대학"),
    KOREAN_LANGUAGE_LITERATURE("국어국문학"),
    CHINESE_LANGUAGE_LITERATURE("중어중문학"),
    ENGLISH_LANGUAGE_LITERATURE("영어영문학"),
    GERMAN_LANGUAGE_LITERATURE("독어독문학"),
    FRENCH_LANGUAGE_LITERATURE("불어불문학"),
    RUSSIAN_LANGUAGE_LITERATURE("노어노문학"),
    HISTORY("사학"),
    PHILOSOPHY("철학"),
    LIBRARY_INFORMATION_SCIENCE("문헌정보학"),
    PSYCHOLOGY("심리학"),

    // 상경대학
    ECONOMICS_COLLEGE("상경대학"),
    ECONOMICS("경제학부"),
    APPLIED_STATISTICS("응용통계학"),

    // 경영대학
    BUSINESS_COLLEGE("경영대학"),

    // 이과대학
    NATURAL_SCIENCES_COLLEGE("이과대학"),
    MATHEMATICS("수학"),
    PHYSICS("물리학"),
    CHEMISTRY("화학"),
    EARTH_SYSTEM_SCIENCE("지구시스템과학"),
    ASTRONOMY("천문우주학"),
    ATMOSPHERIC_SCIENCE("대기과학"),

    // 공과대학
    ENGINEERING_COLLEGE("공과대학"),
    CHEMICAL_ENGINEERING("화공생명공학"),
    ELECTRICAL_ENGINEERING("전기전자공학"),
    ARCHITECTURE_ENGINEERING("건축공학"),
    URBAN_ENGINEERING("도시공학"),
    CIVIL_ENGINEERING("건설환경공학"),
    MECHANICAL_ENGINEERING("기계공학"),
    MATERIALS_SCIENCE_ENGINEERING("신소재공학"),
    INDUSTRY_ENGINEERING("산업공학"),
    SYSTEM_SEMICONDUCTOR_ENGINEERING("시스템반도체공학"),
    DISPLAY_CONVERGENCE_ENGINEERING("디스플레이융합공학"),

    // 생명시스템대학
    LIFE_SYSTEMS_COLLEGE("생명시스템대학"),
    SYSTEMS_BIOLOGY("시스템생물학"),
    BIOCHEMISTRY("생화학"),
    BIOTECHNOLOGY("생명공학"),


    // 인공지능융합대학
    AI_CONVERGENCE_COLLEGE("인공지능융합대학"),
    COMPUTER_SCIENCE("컴퓨터과학"),
    ADVANCED_COMPUTING("첨단컴퓨팅학부"),
    ARTIFICIAL_INTELLIGENCE("인공지능학과"),
    ADVANCED_CONVERGENCE_ENGINEERING("첨단융합공학부"),

    // 신과대학
    THEOLOGY_COLLEGE("신과대학"),
    THEOLOGY("신학"),

    // 사회과학대학
    SOCIAL_SCIENCES_COLLEGE("사회과학대학"),
    POLITICAL_SCIENCE("정치외교학"),
    PUBLIC_ADMINISTRATION("행정학"),
    SOCIAL_WELFARE("사회복지학"),
    SOCIOLOGY("사회학"),
    CULTURAL_ANTHROPOLOGY("문화인류학"),
    MASS_COMMUNICATION("언론홍보영상학부"),

    // 음악대학
    MUSIC_COLLEGE("음악대학"),

    // 생활과학대학
    HUMAN_ECOLOGY("생활과학대"),
    CLOTHING_ENVIRONMENT("의류환경"),
    FOOD_NUTRITION("식품영양"),
    INTERIOR_ARCHITECTURE("실내건축"),
    CHILD_FAMILY("아동·가족"),
    INTEGRATED_DESIGN("통합 디자인"),

    // 교육과학대학
    EDUCATION_SCIENCES_COLLEGE("교육과학대학"),
    EDUCATION("교육학"),
    PHYSICAL_EDUCATION("체육교육학"),
    SPORTS_APPLIED_INDUSTRY("스포츠응용산업학"),

    UNDERGRADUATE_COLLEGE("학부대학"),
    GLOBAL_TALENT_COLLEGE("글로벌인재대학"),

    NURSING("간호학"),
    MEDICAL("의과대학"),
    DENTAL("치과대학"),
    PHARMACY("약학"),
    INTERDISCIPLINARY_MAJOR("연계전공"),
    INTERNATIONAL_COLLEGE_ACADEMIC_AFFAIRS("국제학부 Academic Affairs"),
    INTERNATIONAL_COLLEGE_STUDENT_SERVICES("국제학부 Student Services"),
    LAW("법학전문대학원"),
    ;

    private final String name;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return getName();
    }
}
