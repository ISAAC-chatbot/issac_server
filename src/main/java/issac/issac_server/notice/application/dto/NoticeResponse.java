package issac.issac_server.notice.application.dto;

import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.user.domain.University;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {

    private University university;
    private NoticeSource source;
    private String subCategory;
    private String title;
    private String content;
    private String author;
    private String createdDate;

    public static List<String> getFieldNames() {
        return Arrays.stream(NoticeResponse.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
