package issac.issac_server.keyword.domain;

import issac.issac_server.common.domain.BaseCreateTimeEntity;
import issac.issac_server.user.domain.University;
import issac.issac_server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "keyword")
public class Keyword extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private University university;

    @Column(nullable = false)
    private String text;

    public static Keyword of(User user, String text) {
        return Keyword.builder()
                .userId(user.getId())
                .university(user.getProfile().getUniversity())
                .text(text)
                .build();
    }
}
