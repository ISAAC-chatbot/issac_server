package issac.issac_server.post.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String thumbnailPhotoUrl;

    private List<String> photoUrls;
}
