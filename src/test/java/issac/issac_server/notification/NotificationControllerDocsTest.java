package issac.issac_server.notification;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.notification.application.NotificationService;
import issac.issac_server.notification.application.dto.NotificationResponse;
import issac.issac_server.notification.application.dto.NotificationSearchCondition;
import issac.issac_server.notification.application.dto.NotificationUpdateRequest;
import issac.issac_server.notification.presentation.NotificationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.document.utils.ApiDocumentUtils.*;
import static issac.issac_server.document.utils.DocumentFormatGenerator.*;
import static issac.issac_server.notification.constant.NotificationDocFields.*;
import static issac.issac_server.notification.constant.NotificationFactory.createMockNotificationResponses;
import static issac.issac_server.notification.constant.NotificationFactory.createMockNotificationUpdateRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NotificationControllerDocsTest extends RestDocsSupport {

    private final NotificationService notificationService = mock(NotificationService.class);

    @Override
    protected Object initController() {
        return new NotificationController(notificationService);
    }

    @DisplayName("검색 : 알림")
    @Test
    void findNotifications() throws Exception {

        // given
        List<NotificationResponse> responses = createMockNotificationResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<NotificationResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(notificationService.findNotifications(any(), any(NotificationSearchCondition.class), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/notifications")
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-notification-findNotifications",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Notification API")
                                .summary("알림 목록 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(mergeParameters(
                                        new ParameterDescriptor[]{
                                                pageParam(),
                                                sizeParam(),
                                        },
                                        NOTIFICATION_SEARCH_CONDITION
                                ))
                                .responseFields(mergeFields(
                                        PAGE_RESPONSE,
                                        generateFields("content[].", NOTIFICATION_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("NotificationResponse"))
                                .build())));
    }

    @DisplayName("읽음 상태 변경(알림 목록) : 알림")
    @Test
    void markAsReadById() throws Exception {

        // when & then
        mockMvc.perform(
                        patch("/api/v1/notifications/{notificationId}/read", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v1-notification-markAsReadById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Notification API")
                                .summary("알림 읽음 상태 변경 in 알림 목록")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .pathParameters(
                                        parameterWithName("notificationId").description("알림 ID")
                                )
                                .build())));
    }

    @DisplayName("읽음 상태 변경(FCM) : 알림")
    @Test
    void markAsRead() throws Exception {

        NotificationUpdateRequest request = createMockNotificationUpdateRequest();

        // when & then
        mockMvc.perform(
                        patch("/api/v1/notifications/read", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v1-notification-markAsRead",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Notification API")
                                .summary("알림 읽음 상태 변경 in FCM")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(NOTIFICATION_UPDATE_REQUEST)
                                .requestSchema(Schema.schema("NotificationUpdateRequest"))
                                .build())));
    }
}