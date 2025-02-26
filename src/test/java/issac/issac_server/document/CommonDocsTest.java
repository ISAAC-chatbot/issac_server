package issac.issac_server.document;

import com.fasterxml.jackson.core.type.TypeReference;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.document.controller.Docs;
import issac.issac_server.document.controller.EnumViewController;
import issac.issac_server.document.utils.CustomResponseFieldsSnippet;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommonDocsTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new EnumViewController();
    }

    @Test
    void commons() throws Exception {

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/docs")
                        .accept(MediaType.APPLICATION_JSON)
        );

        MvcResult mvcResult = result.andReturn();
        Docs docs = getData(mvcResult);

        result.andExpect(status().isOk())
                .andDo(document("common",
                        customResponseFields("custom-response", beneathPath("role").withSubsectionId("role"),
                                attributes(key("title").value("사용자 역할 종류")),
                                enumConvertFieldDescriptor(docs.getRole())
                        ),
                        customResponseFields("custom-response", beneathPath("oauthProvider").withSubsectionId("oauthProvider"),
                                attributes(key("title").value("OAuth 인증 제공자")),
                                enumConvertFieldDescriptor(docs.getOauthProvider())
                        ),
                        customResponseFields("custom-response", beneathPath("university").withSubsectionId("university"),
                                attributes(key("title").value("대학교")),
                                enumConvertFieldDescriptor(docs.getUniversity())
                        ),
                        customResponseFields("custom-response", beneathPath("noticeSource").withSubsectionId("noticeSource"),
                                attributes(key("title").value("공지사항 출처 사이트")),
                                enumConvertFieldDescriptor(docs.getNoticeSource())
                        ),
                        customResponseFields("custom-response", beneathPath("reactionType").withSubsectionId("reactionType"),
                                attributes(key("title").value("리액션 타입")),
                                enumConvertFieldDescriptor(docs.getReactionType())
                        ),
                        customResponseFields("custom-response", beneathPath("targetType").withSubsectionId("targetType"),
                                attributes(key("title").value("대상 타입")),
                                enumConvertFieldDescriptor(docs.getTargetType())
                        ),
                        customResponseFields("custom-response", beneathPath("fileTargetType").withSubsectionId("fileTargetType"),
                                attributes(key("title").value("파일의 대상 타입")),
                                enumConvertFieldDescriptor(docs.getFileTargetType())
                        ),
                        customResponseFields("custom-response", beneathPath("notificationType").withSubsectionId("notificationType"),
                                attributes(key("title").value("알림 종류")),
                                enumConvertFieldDescriptor(docs.getNotificationType())
                        ),
                        customResponseFields("custom-response", beneathPath("reportType").withSubsectionId("reportType"),
                                attributes(key("title").value("신고 종류")),
                                enumConvertFieldDescriptor(docs.getReportType())
                        ),
                        customResponseFields("custom-response", beneathPath("settingType").withSubsectionId("settingType"),
                                attributes(key("title").value("사용자 설정 목록")),
                                enumConvertFieldDescriptor(docs.getSettingType())
                        ),
                        customResponseFields("custom-response", beneathPath("revokeReasonType").withSubsectionId("revokeReasonType"),
                                attributes(key("title").value("탈퇴 사유 유형")),
                                enumConvertFieldDescriptor(docs.getRevokeReasonType())
                        ),
                        customResponseFields("custom-response", beneathPath("educationLevel").withSubsectionId("educationLevel"),
                                attributes(key("title").value("교육 단계")),
                                enumConvertFieldDescriptor(docs.getEducationLevel())
                        )
                ));
    }

    private static FieldDescriptor[] enumConvertFieldDescriptor(Map<String, String> enumValues) {

        return enumValues.entrySet().stream()
                .map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
                .toArray(FieldDescriptor[]::new);
    }

    Docs getData(MvcResult result) throws IOException {
        return objectMapper.readValue(result.getResponse().getContentAsByteArray(), new TypeReference<Docs>() {
        });
    }

    public static CustomResponseFieldsSnippet customResponseFields(String type,
                                                                   PayloadSubsectionExtractor<?> subsectionExtractor,
                                                                   Map<String, Object> attributes,
                                                                   FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes,
                true);
    }

}
