package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import issac.issac_server.reaction.application.ReactionReader;
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
    private final ReactionReader reactionReader;

    public Page<NoticePreviewResponse> search(NoticeSearchCondition condition, Pageable pageable) {
        return noticeFinder.search(condition, pageable);
    }

    public NoticeResponse find(Long userId, String noticeId) {
        NoticeResponse noticeResponse = noticeFinder.find(noticeId);
        boolean isScrap = reactionReader.exists(userId, TargetType.NOTICE, noticeResponse.getId(), ReactionType.SCRAP);
        return isScrap ? noticeResponse.markAsScrap() : noticeResponse.unmarkAsScrap();
    }

    public Page<NoticePreviewResponse> findNoticesByReaction(Long userId, ReactionType reactionType, Pageable pageable) {
        Page<Reaction> reactions = reactionReader.find(userId, TargetType.NOTICE, reactionType, pageable);
        return noticeFinder.findByReactions(reactions);
    }
}
