package issac.issac_server.reaction.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.REACTION_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.TARGET_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ReactionDocFields {
    public static final FieldDescriptor[] REACTION_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("targetType").type(JsonFieldType.STRING).description(generateLinkCode(TARGET_TYPE)),
            fieldWithPath("targetId").type(JsonFieldType.STRING).description("대상 ID"),
            fieldWithPath("type").type(JsonFieldType.STRING).description(generateLinkCode(REACTION_TYPE)),
    };
}
