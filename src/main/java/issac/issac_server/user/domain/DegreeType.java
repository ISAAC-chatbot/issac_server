package issac.issac_server.user.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DegreeType implements DescriptiveEnum {

    BACHELOR("학사"),
    MASTER("석사"),
    DOCTORATE("박사");

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}
