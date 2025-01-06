package issac.issac_server.reaction.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReactionType implements DescriptiveEnum {

    LIKE("좋아요"),
    UNLIKE("싫어요/그냥 그래요"),
    SCRAP("스크랩");

    private final String description;

    @Override
    public String getCode() {
        return name();
    }

    public ReactionType getOppositeType() {
        switch (this) {
            case LIKE:
                return UNLIKE;
            case UNLIKE:
                return LIKE;
            default:
                return null; // SCRAP이나 기타 타입은 반대값 없음
        }
    }
}

