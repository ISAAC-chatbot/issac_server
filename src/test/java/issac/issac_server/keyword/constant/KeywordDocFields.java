package issac.issac_server.keyword.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class KeywordDocFields {

    public static final FieldDescriptor[] KEYWORD_REQUEST = new FieldDescriptor[]{
            fieldWithPath("text").type(JsonFieldType.STRING).description("키워드")
    };

    public static final FieldDescriptor[] KEYWORD_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("keywordId").type(JsonFieldType.NUMBER).description("키워드 ID"),
            fieldWithPath("text").type(JsonFieldType.STRING).description("키워드")
    };
}
