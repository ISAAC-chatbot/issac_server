package issac.issac_server.device.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class DeviceDocFields {

    public static final FieldDescriptor[] DEVICE_TOKEN_REQUEST = new FieldDescriptor[]{
            fieldWithPath("token").type(JsonFieldType.STRING).description("디바이스 토큰"),
    };
}
