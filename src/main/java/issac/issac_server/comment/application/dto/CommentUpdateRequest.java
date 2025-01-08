package issac.issac_server.comment.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CommentUpdateRequest {

    @NotNull
    private Long commentId;

    @NotBlank
    @Length(max = 100)
    private String content;

}
