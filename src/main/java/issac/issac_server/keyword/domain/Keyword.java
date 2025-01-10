package issac.issac_server.keyword.domain;

import issac.issac_server.common.domain.BaseCreateTimeEntity;
import issac.issac_server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    private String text;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_keyword",
            joinColumns = @JoinColumn(name = "keyword_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    public static Keyword from(String text) {
        return Keyword.builder()
                .users(new HashSet<>())
                .text(text)
                .build();
    }
}
