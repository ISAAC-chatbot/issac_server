package issac.issac_server.keyword.application;

import issac.issac_server.keyword.domain.Keyword;
import issac.issac_server.keyword.domain.KeywordRepository;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordRemover {

    private final KeywordRepository keywordRepository;
    private final KeywordFinder keywordFinder;

    public void remove(User user, Long keywordId) {

        Keyword keyword = keywordFinder.find(keywordId);
        user.getKeywords().remove(keyword);
        keyword.getUsers().remove(user);

        if (keyword.getUsers().isEmpty()) {
            keywordRepository.delete(keyword);
        }
    }
}
