package issac.issac_server.reaction.application.dto;

import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReactionCreateRequest {

    @NotNull
    private TargetType targetType;

    @NotNull
    private String targetId;

    @NotNull
    private ReactionType type;

    @Builder
    public ReactionCreateRequest(TargetType targetType, String targetId, ReactionType type) {
        this.targetType = targetType;
        this.targetId = targetId;
        this.type = type;
    }
}
