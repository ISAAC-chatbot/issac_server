package issac.issac_server.reaction.domain;

import issac.issac_server.common.domain.BaseCreateTimeEntity;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reaction")
public class Reaction extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Column(nullable = false)
    private String targetId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @Column(nullable = false)
    private LocalDate targetCreatedAt;

    @Builder
    public Reaction(Long userId, TargetType targetType, String targetId, ReactionType type, LocalDate targetCreatedAt) {
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.type = type;
        this.targetCreatedAt = targetCreatedAt;
    }

    public static Reaction from(Long userId, ReactionCreateRequest request, LocalDate targetCreatedAt) {
        return Reaction.builder()
                .userId(userId)
                .targetType(request.getTargetType())
                .targetId(request.getTargetId())
                .type(request.getType())
                .targetCreatedAt(targetCreatedAt)
                .build();
    }

}
