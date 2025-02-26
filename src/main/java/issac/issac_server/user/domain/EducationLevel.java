package issac.issac_server.user.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationLevel implements DescriptiveEnum {

    UNDERGRADUATE("학부생"),
    GRADUATE("대학원생"),
    ;

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}
