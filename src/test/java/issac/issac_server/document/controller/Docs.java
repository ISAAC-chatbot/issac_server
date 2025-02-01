package issac.issac_server.document.controller;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Docs {

    Map<String, String> role;
    Map<String, String> oauthProvider;
    Map<String, String> university;
    Map<String, String> degreeType;
    Map<String, String> noticeSource;
    Map<String, String> reactionType;
    Map<String, String> targetType;
    Map<String, String> fileTargetType;
    Map<String, String> notificationType;
    Map<String, String> reportType;
    Map<String, String> settingType;


    @Builder(builderClassName = "TestBuilder", builderMethodName = "testBuilder")
    public Docs(Map<String, String> role, Map<String, String> oauthProvider, Map<String, String> university,
                Map<String, String> degreeType, Map<String, String> noticeSource, Map<String, String> reactionType,
                Map<String, String> targetType, Map<String, String> fileTargetType, Map<String, String> notificationType,
                Map<String, String> reportType, Map<String, String> settingType) {
        this.role = role;
        this.oauthProvider = oauthProvider;
        this.university = university;
        this.degreeType = degreeType;
        this.noticeSource = noticeSource;
        this.reactionType = reactionType;
        this.targetType = targetType;
        this.fileTargetType = fileTargetType;
        this.notificationType = notificationType;
        this.reportType = reportType;
        this.settingType = settingType;
    }
}
