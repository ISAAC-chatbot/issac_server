package issac.issac_server.device;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.device.application.DeviceTokenService;
import issac.issac_server.device.application.dto.DeviceTokenRequest;
import issac.issac_server.device.presentation.DeviceController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.device.constant.DeviceDocFields.DEVICE_TOKEN_REQUEST;
import static issac.issac_server.device.constant.DeviceFactory.createMockDeviceTokenRequest;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeviceControllerDocsTest extends RestDocsSupport {

    private final DeviceTokenService deviceTokenService = mock(DeviceTokenService.class);

    @Override
    protected Object initController() {
        return new DeviceController(deviceTokenService);
    }

    @DisplayName("업데이트 : DeviceToken")
    @Test
    void updateToken() throws Exception {
        // given
        DeviceTokenRequest request = createMockDeviceTokenRequest();

        // when & then
        mockMvc.perform(
                        put("/api/v1/devices", 1L)
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("put-v1-device-updateToken",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Device API")
                                .summary("DeviceToken 업데이트")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(DEVICE_TOKEN_REQUEST)
                                .requestSchema(Schema.schema("DeviceTokenRequest"))
                                .build())));
    }
}