package issac.issac_server.notice.domain;

import issac.issac_server.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookmark")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticeSource source;


    public static List<Bookmark> of(Long userId, List<NoticeSource> sources) {
        return sources.stream()
                .map(source -> Bookmark.builder()
                        .userId(userId)
                        .source(source)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<NoticeSource> extractSources(List<Bookmark> bookmarks) {
        return bookmarks.stream()
                .map(Bookmark::getSource) // 각 Bookmark 객체의 source 추출
                .collect(Collectors.toList()); // 리스트로 변환
    }
}
