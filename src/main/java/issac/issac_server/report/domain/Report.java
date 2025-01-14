package issac.issac_server.report.domain;

import issac.issac_server.common.domain.BaseEntity;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.application.dto.ReportCreateRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Report extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Column(nullable = false)
    private String targetId;

    @Column(nullable = false, length = 500)
    private String content;

    public static Report of(Long userId, ReportCreateRequest request) {
        return Report.builder()
                .userId(userId)
                .type(request.getType())
                .targetType(request.getTargetType())
                .targetId(request.getTargetId())
                .content(request.getContent())
                .build();
    }
}
