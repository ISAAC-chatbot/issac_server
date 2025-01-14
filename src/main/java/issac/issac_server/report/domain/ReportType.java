package issac.issac_server.report.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReportType implements DescriptiveEnum {

    SPAM("스팸 홍보/도배글"),
    INAPPROPRIATE_PHOTO("부적절한 사진"),
    HARMFUL_TO_YOUTH("청소년에게 유해한 내용"),
    ABUSIVE_LANGUAGE("욕설/혐오/차별적 표현"),
    MISINFORMATION("거짓 정보"),
    ETC("기타")
    ;

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}
