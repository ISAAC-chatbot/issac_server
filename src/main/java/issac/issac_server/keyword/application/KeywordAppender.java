package issac.issac_server.keyword.application;

import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;
import issac.issac_server.keyword.domain.Keyword;
import issac.issac_server.keyword.domain.KeywordRepository;
import issac.issac_server.keyword.exception.KeywordErrorCode;
import issac.issac_server.keyword.exception.KeywordException;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordAppender {

    private final KeywordRepository keywordRepository;
    private final KeywordFinder keywordFinder;

    public KeywordResponse append(User user, KeywordRequest request) {

        if (keywordFinder.exist(user.getId(), request.getText())) {
            throw new KeywordException(KeywordErrorCode.ALREADY_EXIST);
        }

        Keyword keyword = keywordRepository.save(Keyword.of(user, request.getText()));

        return KeywordResponse.from(keyword);
    }
}
