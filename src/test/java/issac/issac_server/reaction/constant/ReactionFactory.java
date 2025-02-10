package issac.issac_server.reaction.constant;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.application.dto.ReactionResponse;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;

public class ReactionFactory {

    public static ReactionCreateRequest createMockReactionCreateRequest() {
        return ReactionCreateRequest.builder()
                .targetType(TargetType.NOTICE)
                .targetId("fGhOO5QBORDZUx8puAN_")
                .type(ReactionType.SCRAP)
                .build();
    }

    public static ReactionResponse createMockReactionResponse() {
        return new ReactionResponse(ReactionType.SCRAP, true);
    }
}
