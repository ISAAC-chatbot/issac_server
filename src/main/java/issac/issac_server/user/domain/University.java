package issac.issac_server.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum University {

    YONSEI("연세대학교", "yonsei.ac.kr"),
    AJOU("아주대학교", "ajou.ac.kr"),
    ;
    private final String name;
    private final String domain;
}
