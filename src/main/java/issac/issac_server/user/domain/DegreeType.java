package issac.issac_server.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DegreeType {

    BACHELOR("학사"),
    MASTER("석사"),
    DOCTORATE("박사");

    private final String description;
}
