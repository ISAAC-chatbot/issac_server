package issac.issac_server.keyword.application;

import issac.issac_server.keyword.application.dto.KeywordResponse;
import issac.issac_server.keyword.domain.Keyword;
import issac.issac_server.keyword.domain.KeywordRepository;
import issac.issac_server.keyword.exception.KeywordErrorCode;
import issac.issac_server.keyword.exception.KeywordException;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KeywordFinder {

    private final KeywordRepository keywordRepository;

    public Optional<Keyword> findByText(String text) {
        return keywordRepository.findByText(text);
    }

    public List<KeywordResponse> findKeywords(User user) {
        return user.getKeywords().stream().map(KeywordResponse::from).collect(Collectors.toList());
    }

    public Keyword find(Long keywordId) {
        return keywordRepository.findById(keywordId).orElseThrow(() -> new KeywordException(KeywordErrorCode.NOT_EXIST));
    }
}
