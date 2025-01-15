package issac.issac_server.device.constant;

import issac.issac_server.device.application.dto.DeviceTokenRequest;

public class DeviceFactory {

    public static DeviceTokenRequest createMockDeviceTokenRequest() {
        return new DeviceTokenRequest("mock-token-1234");
    }

}
