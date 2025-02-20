package issac.issac_server.keyword.application;

import issac.issac_server.keyword.domain.Keyword;
import issac.issac_server.keyword.domain.KeywordRepository;
import issac.issac_server.keyword.exception.KeywordErrorCode;
import issac.issac_server.keyword.exception.KeywordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordRemover {

    private final KeywordRepository keywordRepository;
    private final KeywordFinder keywordFinder;

    public void remove(Long userId, Long keywordId) {
        Keyword keyword = keywordFinder.find(keywordId);

        if (!keyword.getUserId().equals(userId)) {
            throw new KeywordException(KeywordErrorCode.USER_IS_NOT_AUTHOR);
        }

        keywordRepository.deleteById(keywordId);
    }

    public void removeAll(Long userId) {
        keywordRepository.deleteAllByUserId(userId);
    }
}
