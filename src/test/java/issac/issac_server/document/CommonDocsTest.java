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
                        customResponseFields("custom-response", beneathPath("degreeType").withSubsectionId("degreeType"),
                                attributes(key("title").value("학위 종류")),
                                enumConvertFieldDescriptor(docs.getDegreeType())
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
