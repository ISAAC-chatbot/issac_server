package issac.issac_server.document.controller;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.common.domain.DescriptiveEnum;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.user.domain.DegreeType;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.University;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EnumViewController {

    @GetMapping("/docs")
    public Docs findAll() {

        Map<String, String> role = getDocs(Role.values());
        Map<String, String> oauthProvider = getDocs(OAuthProviderType.values());
        Map<String, String> university = getDocs(University.values());
        Map<String, String> degreeType = getDocs(DegreeType.values());
        Map<String, String> noticeSource = getDocs(NoticeSource.values());
        Map<String, String> reactionType = getDocs(ReactionType.values());
        Map<String, String> targetType = getDocs(TargetType.values());

        return Docs.testBuilder()
                .role(role)
                .oauthProvider(oauthProvider)
                .university(university)
                .degreeType(degreeType)
                .noticeSource(noticeSource)
                .reactionType(reactionType)
                .targetType(targetType)
                .build();
    }

    private Map<String, String> getDocs(DescriptiveEnum[] descriptiveEnums) {
        return Arrays.stream(descriptiveEnums)
                .collect(Collectors.toMap(DescriptiveEnum::getCode, DescriptiveEnum::getDescription));
    }

}
