package issac.issac_server.post.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String thumbnailPhotoUrl;

    private List<String> photoUrls;
}
