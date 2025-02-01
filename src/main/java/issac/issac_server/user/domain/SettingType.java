package issac.issac_server.user.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SettingType implements DescriptiveEnum {

    MARKETING("마케팅"),
    NOTIFICATION("알림");

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}
