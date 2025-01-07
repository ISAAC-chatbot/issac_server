package issac.issac_server.file.constant;

import issac.issac_server.file.application.dto.PreSignedUrlResponse;

public class FileFactory {

    public static PreSignedUrlResponse createMockPreSignedUrlResponse() {
        return PreSignedUrlResponse.builder()
                .uploadUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf439?x-amz-acl=public-read&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20250107T175632Z&X-Amz-SignedHeaders=host&X-Amz-Expires=119&X-Amz-Credential=AKIA2CUNLL43LPPVYGPK%2F20250107%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=eb86ddcfd8e5c5c11569e04eb4377c0446717029cc12609ed857c85e85ed102a")
                .downloadUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/post/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438")
                .build();
    }
}
