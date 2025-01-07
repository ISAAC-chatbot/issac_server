package issac.issac_server.file.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.net.URL;

@Getter
@Builder
public class PreSignedUrlResponse {

    private String uploadUrl;
    private String downloadUrl;

    public static PreSignedUrlResponse from(URL url) {
        String urlString = url.toString();
        String downloadUrl = urlString.split("\\?")[0];
        return PreSignedUrlResponse.builder()
                .uploadUrl(urlString)
                .downloadUrl(downloadUrl)
                .build();
    }
}