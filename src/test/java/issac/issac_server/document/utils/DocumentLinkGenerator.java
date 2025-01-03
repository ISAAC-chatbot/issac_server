package issac.issac_server.document.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface DocumentLinkGenerator {

    static String generateLinkCode(DocUrl docUrl) {
        return String.format("[%s](common/%s.html)", docUrl.text, docUrl.pageId);
    }

    static String generateText(DocUrl docUrl) {
        return String.format("%s %s", docUrl.text, "코드명");
    }

    @RequiredArgsConstructor
    enum DocUrl {
        ROLE("role", "사용자 역할"),
        OAUTH_PROVIDER_TYPE("oAuthProviderType", "OAuth 인증 제공자"),
        UNIVERSITY("university", "대학교"),
        DEGREE_TYPE("degreeType", "학위 종류"),

        ;

        private final String pageId;

        @Getter
        private final String text;
    }
}
