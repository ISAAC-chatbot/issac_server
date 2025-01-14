package issac.issac_server.device.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.device.application.DeviceTokenService;
import issac.issac_server.device.application.dto.DeviceTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceTokenService deviceTokenService;

    @PutMapping
    public ResponseEntity<Void> updateToken(@Auth Long userId, @RequestBody @Valid DeviceTokenRequest request) {
        deviceTokenService.updateToken(userId, request);
        return ResponseEntity.ok().build();
    }
}
