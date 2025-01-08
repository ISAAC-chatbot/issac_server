package issac.issac_server.post.constant;

import issac.issac_server.post.application.PostUpdateRequest;
import issac.issac_server.post.application.dto.PostCreateRequest;
import issac.issac_server.post.application.dto.PostResponse;
import issac.issac_server.post.application.dto.ReactionStatusResponse;
import issac.issac_server.post.application.dto.UserInfoResponse;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.user.domain.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostFactory {

    public static PostCreateRequest createMockPostCreateRequest() {
        return PostCreateRequest.builder()
                .title("계절학기 중간고사 일주일도 안남았네;")
                .content("생각보다 빡세네 다음부턴 계절학기 안해야겠다")
                .thumbnailPhotoUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438")
                .photoUrls(Arrays.asList("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438","https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438"))
                .build();
    }

    public static PostUpdateRequest createMockPostUpdateRequest() {
        return PostUpdateRequest.builder()
                .title("계절학기 중간고사 일주일도 안남았네;")
                .content("생각보다 빡세네 다음부턴 계절학기 안해야겠다")
                .thumbnailPhotoUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438")
                .photoUrls(Arrays.asList("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438","https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438"))
                .build();
    }
    public static PostResponse createMockPostResponse() {
        return PostResponse.builder()
                .postId(1L)
                .title("계절학기 중간고사 일주일도 안남았네;")
                .content("생각보다 빡세네 다음부턴 계절학기 안해야겠다")
                .reactions(new ArrayList<>())
                .author(createMockUserInfoResponse())
                .photoUrls(Arrays.asList("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438","https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438"))
                .commentCount(0L)
                .createdAt(LocalDateTime.now()) // 현재 시간을 설정
                .modifiedAt(LocalDateTime.now()) // 현재 시간을 설정
                .build();
    }

    public static PostResponse createMockPostResponseWithReaction() {
        List<ReactionStatusResponse> reactions = Arrays.asList(
                ReactionStatusResponse.of(ReactionType.LIKE, 10, true),
                ReactionStatusResponse.of(ReactionType.UNLIKE, 10, false),
                ReactionStatusResponse.of(ReactionType.SCRAP, 10, true)
        );
        return PostResponse.builder()
                .postId(1L)
                .title("계절학기 중간고사 일주일도 안남았네;")
                .content("생각보다 빡세네 다음부턴 계절학기 안해야겠다")
                .reactions(reactions)
                .author(createMockUserInfoResponse())
                .photoUrls(Arrays.asList("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438","https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438"))
                .commentCount(4L)
                .createdAt(LocalDateTime.now()) // 현재 시간을 설정
                .modifiedAt(LocalDateTime.now()) // 현재 시간을 설정
                .build();
    }

    public static UserInfoResponse createMockUserInfoResponse() {
        return UserInfoResponse.builder()
                .userId(1L)
                .nickname("익명")
                .department("소프트웨어학과")
                .profilePhotoUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/profile/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438")
                .role(Role.STUDENT)
                .build();
    }

}
