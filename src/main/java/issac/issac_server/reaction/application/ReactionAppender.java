package issac.issac_server.reaction.application;

import issac.issac_server.comment.application.CommentFinder;
import issac.issac_server.notice.application.NoticeFinder;
import issac.issac_server.post.application.PostFinder;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.reaction.domain.ReactionRepository;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionAppender {

    private final ReactionReader reactionReader;
    private final ReactionRemover reactionRemover;
    private final ReactionRepository reactionRepository;

    private final NoticeFinder noticeFinder;
    private final PostFinder postFinder;
    private final CommentFinder commentFinder;

    public Optional<Reaction> append(Long userId, ReactionCreateRequest request) {

        Optional<Reaction> existingReaction = reactionReader.find(userId, request);

        if (existingReaction.isPresent()) {
            reactionRemover.remove(existingReaction.get().getId());
            return Optional.empty();
        }

        LocalDate targetCreatedDate = getTargetCreatedDate(request.getTargetType(), request.getTargetId());

        return Optional.of(reactionRepository.save(Reaction.from(userId, request, targetCreatedDate)));
    }

    /**
     * TargetType에 따라 생성 날짜를 가져와 LocalDate로 변환하는 메서드
     */
    private LocalDate getTargetCreatedDate(TargetType targetType, String targetId) {
        return switch (targetType) {
            case NOTICE -> {
                String createdDate = noticeFinder.find(targetId).getCreatedDate();
                yield LocalDate.parse(createdDate, DateTimeFormatter.ISO_DATE);
            }
            case POST -> {
                LocalDateTime createdDateTime = postFinder.find(Long.valueOf(targetId)).getCreatedDateTime();
                yield createdDateTime.toLocalDate();
            }
            case COMMENT -> {
                LocalDateTime createdDateTime1 = commentFinder.find(Long.valueOf(targetId)).getCreatedDateTime();
                yield createdDateTime1.toLocalDate();
            }
        };
    }


}
