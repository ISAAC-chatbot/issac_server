package issac.issac_server.auth.presentation;

import issac.issac_server.common.exception.ExceptionResponse;
import issac.issac_server.common.exception.IssacException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
@RequiredArgsConstructor
@Log4j2
public class ErrorController {

    @RequestMapping("/unauthorized")
    public ResponseEntity<ExceptionResponse> handleUnauthorized(HttpServletRequest request) {

        IssacException e = (IssacException) request.getAttribute("exception");

//        // 기존 요청 정보 가져오기
//        String originalMethod = (String) request.getAttribute("originalMethod");
//        String originalURI = (String) request.getAttribute("originalURI");
//        String originalQueryString = (String) request.getAttribute("originalQueryString");
//
//        // 요청 정보와 예외 로그 남기기
//        log.error("[IssacException] Method: {}, RequestURI: {}{}, Exception: {}, Message: {}",
//                originalMethod,
//                originalURI,
//                (originalQueryString != null ? "?" + originalQueryString : ""), // 쿼리 파라미터 있으면 추가
//                e.getClass().getSimpleName(),
//                e.getMessage());


        ExceptionResponse response = ExceptionResponse.from(e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
