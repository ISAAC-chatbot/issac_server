package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import issac.issac_server.reaction.application.ReactionFinder;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeFinder noticeFinder;
    private final ReactionFinder reactionFinder;

    public Page<NoticePreviewResponse> search(NoticeSearchCondition condition, Pageable pageable) {
        return noticeFinder.search(condition, pageable);
    }

    public NoticeResponse find(String noticeId) {
        return noticeFinder.find(noticeId);
    }

    public Page<NoticePreviewResponse> findNoticesByReaction(Long userId, ReactionType reactionType, Pageable pageable) {
        Page<Reaction> reactions = reactionFinder.find(userId, TargetType.NOTICE, reactionType, pageable);
        return noticeFinder.findByReactions(reactions);
    }
}
