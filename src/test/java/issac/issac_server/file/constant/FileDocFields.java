package issac.issac_server.file.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class FileDocFields {

    public static final FieldDescriptor[] PRESIGNED_URL_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("uploadUrl").type(JsonFieldType.STRING).description("업로드 URL"),
            fieldWithPath("downloadUrl").type(JsonFieldType.STRING).description("다운로드 URL"),
    };
}
