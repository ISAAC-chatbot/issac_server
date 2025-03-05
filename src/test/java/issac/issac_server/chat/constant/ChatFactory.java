package issac.issac_server.chat.constant;

import issac.issac_server.chat.application.dto.ChatMessageCreateRequest;
import issac.issac_server.chat.application.dto.ChatMessageResponse;
import issac.issac_server.chat.application.dto.ChatRoomInfoResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ChatFactory {

    public static ChatMessageCreateRequest createMockChatMessageCreateRequest() {
        return ChatMessageCreateRequest.builder()
                .chatRoomId(1L)
                .question("2025-1 학부 등록금 납부 일정")
                .answer("2025-1 학부 등록금 납부 일정은 다음과 같습니다:\n" +
                        "\n" +
                        "등록금 납부 기간: 2025년 2월 21일(금) ~ 2월 27일(목)입니다.\n" +
                        "자세한 사항은 연세대학교 공식 웹사이트를 참고하시기 바랍니다.")
                .sourceURL("https://www.yonsei.ac.kr/sc/support/calendar.jsp?cYear=2025&hakGi=1")
                .elapsedTime(2.345)
                .build();
    }

    public static ChatMessageResponse createMockChatMessageResponse() {
        return ChatMessageResponse.builder()
                .question("2025-1 학부 등록금 납부 일정")
                .answer("2025-1 학부 등록금 납부 일정은 다음과 같습니다:\n" +
                        "\n" +
                        "등록금 납부 기간: 2025년 2월 21일(금) ~ 2월 27일(목)입니다.\n" +
                        "자세한 사항은 연세대학교 공식 웹사이트를 참고하시기 바랍니다.")
                .sourceURL("https://www.yonsei.ac.kr/sc/support/calendar.jsp?cYear=2025&hakGi=1")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<ChatMessageResponse> createMockChatMessageResponses() {
        return Arrays.asList(createMockChatMessageResponse(), createMockChatMessageResponse());
    }

    public static ChatRoomInfoResponse createMockChatRoomInfoResponse() {
        return ChatRoomInfoResponse.builder()
                .id(1L)
                .title("2025-1 학부 등록금 납부 일정")
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static List<ChatRoomInfoResponse> createMockChatRoomInfoResponses() {
        return Arrays.asList(createMockChatRoomInfoResponse(), createMockChatRoomInfoResponse());
    }


}
