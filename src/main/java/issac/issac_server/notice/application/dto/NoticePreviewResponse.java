package issac.issac_server.notice.application.dto;

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
public class NoticePreviewResponse {

    @Setter
    private String id;
    private NoticeSource source;
    private String subCategory;
    private String title;
    private String author;
    private String createdDate;

    public static List<String> getFieldNames() {
        return Arrays.stream(NoticePreviewResponse.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
