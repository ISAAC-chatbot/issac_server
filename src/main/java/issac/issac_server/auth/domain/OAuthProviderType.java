package issac.issac_server.auth.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OAuthProviderType {

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
}

