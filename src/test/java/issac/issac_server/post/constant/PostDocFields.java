package issac.issac_server.post.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.REACTION_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.ROLE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class PostDocFields {

    public static final FieldDescriptor[] POST_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
            fieldWithPath("thumbnailPhotoUrl").type(JsonFieldType.STRING).description("썸네일 사진 URL"),
            fieldWithPath("photoUrls").type(JsonFieldType.ARRAY).description("게시물 사진 URL 목록")
    };

    public static final FieldDescriptor[] POST_UPDATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
            fieldWithPath("thumbnailPhotoUrl").type(JsonFieldType.STRING).description("썸네일 사진 URL"),
            fieldWithPath("photoUrls").type(JsonFieldType.ARRAY).description("게시물 사진 URL 목록")
    };
    public static final FieldDescriptor[] USER_INFO_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("작성자 ID"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("department").type(JsonFieldType.STRING).description("학과"),
            fieldWithPath("profilePhotoUrl").type(JsonFieldType.STRING).description("프로필 사진 URL"),
            fieldWithPath("role").type(JsonFieldType.STRING).description(generateLinkCode(ROLE))
    };

    public static final FieldDescriptor[] REACTION_STATUS_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("reactionType").type(JsonFieldType.STRING).description(generateLinkCode(REACTION_TYPE)),
            fieldWithPath("count").type(JsonFieldType.NUMBER).description("리액션 수"),
            fieldWithPath("selected").type(JsonFieldType.BOOLEAN).description("사용자 선택 여부")
    };

    public static final FieldDescriptor[] POST_RESPONSE = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("postId").type(JsonFieldType.NUMBER).description("게시물 ID"),
                    fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
                    fieldWithPath("reactions").type(JsonFieldType.ARRAY).description("반응 목록"),
                    fieldWithPath("author").type(JsonFieldType.OBJECT).description("작성자 정보"),
            },
            generateFields("author.", USER_INFO_RESPONSE),
            new FieldDescriptor[]{
                    fieldWithPath("photoUrls").type(JsonFieldType.ARRAY).description("게시물 사진 URL 목록"),
                    fieldWithPath("commentCount").type(JsonFieldType.NUMBER).description("게시물 댓글 수"),
                    fieldWithPath("createdAt").type(JsonFieldType.STRING).description("게시물 생성 시간"),
                    fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("게시물 수정 시간")
            });

    public static final FieldDescriptor[] POST_RESPONSE_WITH_REACTION = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("postId").type(JsonFieldType.NUMBER).description("게시물 ID"),
                    fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
                    fieldWithPath("reactions").type(JsonFieldType.ARRAY).description("반응 목록"),
            },
            generateFields("reactions[].", REACTION_STATUS_RESPONSE),
            new FieldDescriptor[]{
                    fieldWithPath("author").type(JsonFieldType.OBJECT).description("작성자 정보"),
            },
            generateFields("author.", USER_INFO_RESPONSE),
            new FieldDescriptor[]{
                    fieldWithPath("photoUrls").type(JsonFieldType.ARRAY).description("게시물 사진 URL 목록"),
                    fieldWithPath("commentCount").type(JsonFieldType.NUMBER).description("게시물 댓글 수"),
                    fieldWithPath("createdAt").type(JsonFieldType.STRING).description("게시물 생성 시간"),
                    fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("게시물 수정 시간")
            });



}
