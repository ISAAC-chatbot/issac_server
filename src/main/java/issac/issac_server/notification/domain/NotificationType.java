package issac.issac_server.notification.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType implements DescriptiveEnum {

    KEYWORD("키워드"),
    BOOKMARK("즐겨찾기"),
    COMMENT("댓글"),
    REPORT("신고");

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}
