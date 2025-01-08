package issac.issac_server.comment.constant;

import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.comment.application.dto.CommentUpdateRequest;
import issac.issac_server.comment.application.dto.ReplyResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static issac.issac_server.post.constant.PostFactory.createMockUserInfoResponse;

public class CommentFactory {

    public static CommentCreateRequest createMockCommentCreateRequest() {
        return CommentCreateRequest.builder()
                .content("계절학기 중간고사 일주일도 안남았네;")
                .parentCommentId(1L)
                .build();
    }

    public static CommentUpdateRequest createMockCommentUpdateRequest() {
        return CommentUpdateRequest.builder()
                .commentId(1L)
                .content("계절학기 중간고사 일주일도 안남았네;")
                .build();
    }

    public static List<CommentResponse> createMockCommentResponses() {
        return Arrays.asList(createMockCommentResponse(), createMockCommentResponse());
    }

    public static CommentResponse createMockCommentResponse() {
        return CommentResponse.builder()
                .commentId(1L)
                .content("계절학기 중간고사 일주일도 안남았네;")
                .author(createMockUserInfoResponse())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .deleted(false)
                .likeCount(0L)
                .dislikeCount(0L)
                .replies(Arrays.asList(createMockReplyResponse(), createMockReplyResponse()))
                .build();
    }

    public static ReplyResponse createMockReplyResponse() {
        return ReplyResponse.builder()
                .commentId(1L)
                .content("계절학기 중간고사 일주일도 안남았네;")
                .author(createMockUserInfoResponse())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .likeCount(0L)
                .dislikeCount(0L)
                .build();
    }
}
