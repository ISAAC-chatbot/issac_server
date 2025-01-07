package issac.issac_server.file.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileTargetType implements DescriptiveEnum {

    POST("게시글"),
    PROFILE("프로필"),
    ;

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}
