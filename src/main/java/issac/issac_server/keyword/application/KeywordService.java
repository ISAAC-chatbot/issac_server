package issac.issac_server.keyword.application;

import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final UserFinder userFinder;
    private final KeywordAppender keywordAppender;
    private final KeywordRemover keywordRemover;
    private final KeywordFinder keywordFinder;

    @Transactional
    public KeywordResponse save(Long userId, KeywordRequest request) {
        User user = userFinder.find(userId);
        return keywordAppender.append(user, request);
    }

    public List<KeywordResponse> findKeywords(Long userId) {
        User user = userFinder.find(userId);
        return keywordFinder.findKeywords(user);
    }

    @Transactional
    public void remove(Long userId, Long keywordId) {
        keywordRemover.remove(userId, keywordId);
    }
}
