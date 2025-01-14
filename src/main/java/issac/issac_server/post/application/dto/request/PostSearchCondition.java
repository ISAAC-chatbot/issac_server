package issac.issac_server.post.application.dto.request;

import issac.issac_server.user.domain.University;
import lombok.Data;

@Data
public class PostSearchCondition {
    private University university;
    private String keyword;
}
