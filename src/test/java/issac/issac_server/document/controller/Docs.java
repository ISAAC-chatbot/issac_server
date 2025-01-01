package issac.issac_server.document.controller;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Docs {

    Map<String, String> role;
    Map<String, String> oauthProvider;

    @Builder(builderClassName = "TestBuilder", builderMethodName = "testBuilder")
    public Docs(Map<String, String> role, Map<String, String> oauthProvider) {
        this.role = role;
        this.oauthProvider = oauthProvider;
    }
}
