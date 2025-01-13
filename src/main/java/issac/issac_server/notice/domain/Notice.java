package issac.issac_server.notice.domain;

import issac.issac_server.notice.application.dto.response.NoticeFileResponse;
import issac.issac_server.user.domain.University;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Notice {

    @Setter
    private String id;

    private University university;

    private NoticeSource source;

    private String subCategory;

    private String title;

    private String content;

    private String rawContent;

    private String author;

    private String createdDate;

    private List<NoticeFileResponse> files;


    public static List<String> getFieldNames() {
        return Arrays.stream(Notice.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
