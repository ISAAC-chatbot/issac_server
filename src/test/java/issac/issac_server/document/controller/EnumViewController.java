package issac.issac_server.document.controller;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.common.domain.DescriptiveEnum;
import issac.issac_server.file.domain.FileTargetType;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.domain.ReportType;
import issac.issac_server.user.domain.*;
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
        Map<String, String> noticeSource = getDocs(NoticeSource.values());
        Map<String, String> reactionType = getDocs(ReactionType.values());
        Map<String, String> targetType = getDocs(TargetType.values());
        Map<String, String> fileTargetType = getDocs(FileTargetType.values());
        Map<String, String> notificationType = getDocs(NotificationType.values());
        Map<String, String> reportType = getDocs(ReportType.values());
        Map<String, String> settingType = getDocs(SettingType.values());
        Map<String, String> revokeReasonType = getDocs(RevokeReasonType.values());
        Map<String, String> educationLevel = getDocs(EducationLevel.values());

        return Docs.testBuilder()
                .role(role)
                .oauthProvider(oauthProvider)
                .university(university)
                .noticeSource(noticeSource)
                .reactionType(reactionType)
                .targetType(targetType)
                .fileTargetType(fileTargetType)
                .notificationType(notificationType)
                .reportType(reportType)
                .settingType(settingType)
                .revokeReasonType(revokeReasonType)
                .educationLevel(educationLevel)
                .build();
    }

    private Map<String, String> getDocs(DescriptiveEnum[] descriptiveEnums) {
        return Arrays.stream(descriptiveEnums)
                .collect(Collectors.toMap(DescriptiveEnum::getCode, DescriptiveEnum::getDescription));
    }

}
