package issac.issac_server.keyword.domain;

import issac.issac_server.common.domain.BaseCreateTimeEntity;
import issac.issac_server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<User> users = new HashSet<>();

    public static List<String> extractKeywordTexts(Set<Keyword> keywords) {
        return keywords != null
                ? keywords.stream().map(Keyword::getText).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public static Keyword from(String text) {
        return Keyword.builder()
                .text(text)
                .build();
    }
}
