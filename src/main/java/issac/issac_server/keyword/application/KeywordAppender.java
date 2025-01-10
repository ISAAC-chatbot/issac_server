package issac.issac_server.keyword.application;

import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;
import issac.issac_server.keyword.domain.Keyword;
import issac.issac_server.keyword.domain.KeywordRepository;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordAppender {

    private final KeywordRepository keywordRepository;
    private final KeywordFinder keywordFinder;

    public KeywordResponse append(User user, KeywordRequest request) {

        Keyword keyword = keywordFinder.findByText(request.getText())
                .orElseGet(() -> keywordRepository.save(Keyword.from(request.getText())));

        user.getKeywords().add(keyword);
        keyword.getUsers().add(user);

        return KeywordResponse.from(keyword);
    }
}
