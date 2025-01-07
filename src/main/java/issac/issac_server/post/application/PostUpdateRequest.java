package issac.issac_server.post.application;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String thumbnailPhotoUrl;

    private List<String> photoUrls;
}
