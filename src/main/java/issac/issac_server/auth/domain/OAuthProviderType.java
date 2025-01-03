package issac.issac_server.auth.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OAuthProviderType implements DescriptiveEnum {

    KAKAO("카카오"),

    GOOGLE("구글"),

    APPLE("애플");

    private final String providerName;

    @JsonCreator
    public static OAuthProviderType parsing(String inputValue) {
        return Stream.of(OAuthProviderType.values())
                .filter(providerType -> providerType.toString().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return getProviderName();
    }
}

