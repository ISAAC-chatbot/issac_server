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
public class NoticePreviewResponse {

    @Setter
    private String id;
    private NoticeSource source;
    private String subCategory;
    private String title;
//    private String content;
    private String author;
    private String createdDate;

    public static List<String> getFieldNames() {
        return Arrays.stream(NoticePreviewResponse.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

//    public void extractFirstLineContent() {
//        if (this.content == null || this.content.isEmpty() || this.content.startsWith("첨부파일")) {
//            this.content = "";
//            return;
//        }
//
//        // 100자 초과 시 자르기
//        if (this.content.length() > 100) {
//            this.content = this.content.substring(0, 100);
//        }
//    }

}
