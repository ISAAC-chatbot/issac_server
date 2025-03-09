package issac.issac_server.notice.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.NOTICE_SOURCE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.UNIVERSITY;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class NoticeDocFields {

    public static final ParameterDescriptor[] NOTICE_SEARCH_CONDITION = new ParameterDescriptor[]{
            parameterWithName("university").description(generateLinkCode(UNIVERSITY)).optional(),
            parameterWithName("source").description(generateLinkCode(NOTICE_SOURCE)).optional(),
            parameterWithName("keyword").description("검색어").optional()
    };

    public static final FieldDescriptor[] NOTICE_PREVIEW_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.STRING).description("공지사항 ID"),
            fieldWithPath("source").type(JsonFieldType.STRING).description(generateLinkCode(NOTICE_SOURCE)),
            fieldWithPath("subCategory").type(JsonFieldType.STRING).description("서브 카테고리").optional(),
            fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
//            fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 첫번째 문장"),
            fieldWithPath("author").type(JsonFieldType.STRING).description("작성자").optional(),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("작성 일자(2024.1.1)")
    };

    public static final FieldDescriptor[] NOTICE_FILE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("name").type(JsonFieldType.STRING).description("파일 이름"),
            fieldWithPath("url").type(JsonFieldType.STRING).description("파일 url"),
    };

    public static final FieldDescriptor[] NOTICE_FILE_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("name").type(JsonFieldType.STRING).description("파일 이름"),
            fieldWithPath("url").type(JsonFieldType.STRING).description("파일 url"),
    };


    public static final FieldDescriptor[] NOTICE_RESPONSE = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("id").type(JsonFieldType.STRING).description("공지사항 ID"),
                    fieldWithPath("source").type(JsonFieldType.STRING).description(generateLinkCode(NOTICE_SOURCE)),
                    fieldWithPath("subCategory").type(JsonFieldType.STRING).description("서브 카테고리").optional(),
                    fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                    fieldWithPath("rawContent").type(JsonFieldType.STRING).description("공지사항 내용(HTML)"),
                    fieldWithPath("author").type(JsonFieldType.STRING).description("작성자").optional(),
                    fieldWithPath("createdDate").type(JsonFieldType.STRING).description("작성 일자(2024.1.1)"),
                    fieldWithPath("scrap").type(JsonFieldType.BOOLEAN).description("스크랩 여부"),
                    fieldWithPath("files[]").type(JsonFieldType.ARRAY).description("첨부파일"),
                    fieldWithPath("url").type(JsonFieldType.STRING).description("원본 사이트 URL")
            },
            generateFields("files[].", NOTICE_FILE_RESPONSE)
    );

    public static final FieldDescriptor[] NOTICE_CREATE_REQUEST = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("university").type(JsonFieldType.STRING).description(generateLinkCode(UNIVERSITY)),
                    fieldWithPath("source").type(JsonFieldType.STRING).description(generateLinkCode(NOTICE_SOURCE)),
                    fieldWithPath("subCategory").type(JsonFieldType.STRING).description("서브 카테고리").optional(),
                    fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                    fieldWithPath("rawContent").type(JsonFieldType.STRING).description("공지사항 내용(HTML)"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용"),
                    fieldWithPath("author").type(JsonFieldType.STRING).description("작성자").optional(),
                    fieldWithPath("createdDate").type(JsonFieldType.STRING).description("작성 일자(2024.1.1)"),
                    fieldWithPath("files[]").type(JsonFieldType.ARRAY).description("첨부파일"),
                    fieldWithPath("url").type(JsonFieldType.STRING).description("원본 사이트 URL")
            },
            generateFields("files[].", NOTICE_FILE_REQUEST)
    );

}
