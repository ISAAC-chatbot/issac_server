package issac.issac_server.notice.application.dto.request;

import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.user.domain.University;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class NoticeCreateRequest {

    @NotNull
    private University university;

    @NotNull
    private NoticeSource source;

    private String subCategory;

    @NotBlank
    private String title;

    @NotBlank
    private String rawContent;

    private String content;

    @NotBlank
    private String author;

    @NotBlank
    private String createdDate;

    private List<NoticeFileRequest> files;
}
