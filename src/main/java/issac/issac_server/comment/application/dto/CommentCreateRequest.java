package issac.issac_server.comment.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

    @NotBlank
    @Length(max = 100)
    private String content;

    private Long parentCommentId;

}
