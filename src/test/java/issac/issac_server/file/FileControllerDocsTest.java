package issac.issac_server.file;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.file.application.FileService;
import issac.issac_server.file.application.dto.PreSignedUrlResponse;
import issac.issac_server.file.domain.FileTargetType;
import issac.issac_server.file.presentation.FileController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.FILE_TARGET_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static issac.issac_server.file.constant.FileDocFields.PRESIGNED_URL_RESPONSE;
import static issac.issac_server.file.constant.FileFactory.createMockPreSignedUrlResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FileControllerDocsTest extends RestDocsSupport {

    private final FileService fileService = mock(FileService.class);

    @Override
    protected Object initController() {
        return new FileController(fileService);
    }

    @DisplayName("업로드 링크 조회 : 파일")
    @Test
    void getPresignedUrl() throws Exception {
        // given
        PreSignedUrlResponse response = createMockPreSignedUrlResponse();

        given(fileService.getPresignedUrl(any())).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/file/presigned-url")
                                .param("targetType", FileTargetType.POST.toString())
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("get-v1-file-getPresignedUrl",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("File API")
                                .summary("파일 업로드 링크 조회")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(
                                        parameterWithName("targetType").description(generateLinkCode(FILE_TARGET_TYPE))
                                )
                                .responseFields(PRESIGNED_URL_RESPONSE)
                                .responseSchema(Schema.schema("PreSignedUrlResponse"))
                                .build())));
    }
}