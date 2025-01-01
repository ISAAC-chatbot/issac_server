package issac.issac_server.user.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role implements DescriptiveEnum {

    UNREGISTERED_PROFILE("ROLE_UNREGISTERED_PROFILE", "프로필 미 등록자"),
    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_USER", "일반 사용자"),
    STUDENT("ROLE_STUDENT", "학생"),
    TEACHING_ASSISTANT("ROLE_TEACHING_ASSISTANT", "조교"),
    PROFESSOR("ROLE_PROFESSOR", "교수"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String description;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getText() {
        return getDescription();
    }
}
