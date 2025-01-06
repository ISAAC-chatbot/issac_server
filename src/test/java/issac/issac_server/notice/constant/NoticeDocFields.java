package issac.issac_server.notice.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.NOTICE_SOURCE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.UNIVERSITY;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class NoticeDocFields {

    public static final ParameterDescriptor[] NOTICE_SEARCH_CONDITION = new ParameterDescriptor[]{
            parameterWithName("university").description(generateLinkCode(UNIVERSITY)).optional(),
            parameterWithName("source").description(generateLinkCode(NOTICE_SOURCE)).optional(),
            parameterWithName("title").description("공지사항 제목").optional()
    };

    public static final FieldDescriptor[] NOTICE_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("university").type(JsonFieldType.STRING).description(generateLinkCode(UNIVERSITY)),
            fieldWithPath("source").type(JsonFieldType.STRING).description(generateLinkCode(NOTICE_SOURCE)),
            fieldWithPath("subCategory").type(JsonFieldType.STRING).description("서브 카테고리").optional(),
            fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용(HTML)"),
            fieldWithPath("author").type(JsonFieldType.STRING).description("작성자").optional(),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("작성 일자(2024.1.1)")
    };
}
