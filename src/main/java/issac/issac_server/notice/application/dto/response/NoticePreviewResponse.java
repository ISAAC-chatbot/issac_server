package issac.issac_server.notice.application.dto.response;

import issac.issac_server.notice.domain.NoticeSource;
import lombok.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private String content;
    private String author;
    private String createdDate;

    public static List<String> getFieldNames() {
        return Arrays.stream(NoticePreviewResponse.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public void extractFirstLineContent() {

        if (this.content == null || this.content.isEmpty() || this.content.startsWith("첨부파일")) {
            this.content = "";
            return;
        }

        // 정규식을 사용하여 첫 번째 문장 찾기 (마침표로 끝나는 부분 찾기)
        Pattern pattern = Pattern.compile(".*?\\."); // 가장 처음 나오는 "."까지 매칭
        Matcher matcher = pattern.matcher(this.content);

        if (matcher.find()) {
            this.content = matcher.group(); // 첫 번째 문장만 남기기
        }
    }

}
