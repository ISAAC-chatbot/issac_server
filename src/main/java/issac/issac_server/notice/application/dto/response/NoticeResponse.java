package issac.issac_server.notice.application.dto.response;

import issac.issac_server.notice.domain.NoticeSource;
import lombok.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResponse {

    @Setter
    private String id;
    private NoticeSource source;
    private String subCategory;
    private String title;
    private String rawContent;
    private String author;
    private String createdDate;
    private List<NoticeFileResponse> files;
    private Boolean scrap;
    private String url;

    public NoticeResponse markAsScrap() {
        this.scrap = true;
        return this;
    }

    public NoticeResponse unmarkAsScrap() {
        this.scrap = false;
        return this;
    }

    public static List<String> getFieldNames() {
        return Arrays.stream(NoticeResponse.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
