package issac.issac_server.reaction.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TargetType implements DescriptiveEnum {

    NOTICE("공지사항"),
    POST("게시글"),
    COMMENT("댓글"),
    ;

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}

