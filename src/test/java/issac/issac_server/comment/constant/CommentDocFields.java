package issac.issac_server.comment.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static issac.issac_server.post.constant.PostDocFields.USER_INFO_RESPONSE;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class CommentDocFields {

    public static final FieldDescriptor[] COMMENT_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
            fieldWithPath("parentCommentId").type(JsonFieldType.NUMBER).description("부모 ID").optional()
    };
    public static final FieldDescriptor[] COMMENT_UPDATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
    };


    public static final FieldDescriptor[] REPLY_RESPONSE = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
                    fieldWithPath("author").type(JsonFieldType.OBJECT).description("작성자 정보"),
            },
            generateFields("author.", USER_INFO_RESPONSE),
            new FieldDescriptor[]{
                    fieldWithPath("createdAt").type(JsonFieldType.STRING).description("댓글 생성 시간"),
                    fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 시간"),
                    fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 수").optional(),
                    fieldWithPath("dislikeCount").type(JsonFieldType.NUMBER).description("싫어요 수").optional(),
            });

    public static final FieldDescriptor[] COMMENT_RESPONSE = mergeFields(new FieldDescriptor[]{
                    fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
                    fieldWithPath("author").type(JsonFieldType.OBJECT).description("작성자 정보"),
            },
            generateFields("author.", USER_INFO_RESPONSE),
            new FieldDescriptor[]{
                    fieldWithPath("createdAt").type(JsonFieldType.STRING).description("댓글 생성 시간"),
                    fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 시간"),
                    fieldWithPath("deleted").type(JsonFieldType.BOOLEAN).description("삭제 여부"),
                    fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 수").optional(),
                    fieldWithPath("dislikeCount").type(JsonFieldType.NUMBER).description("싫어요 수").optional(),
                    fieldWithPath("replies").type(JsonFieldType.ARRAY).description("대댓글 목록").optional(),
            },
            generateFields("replies[].", REPLY_RESPONSE)
    );


}
