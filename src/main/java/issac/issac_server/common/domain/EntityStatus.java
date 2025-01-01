package issac.issac_server.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityStatus {

    ACTIVE("활성"),
    DELETED("삭제");

    private final String text;
}
