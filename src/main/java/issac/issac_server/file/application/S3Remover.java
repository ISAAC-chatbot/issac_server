package issac.issac_server.file.application;

import com.amazonaws.services.s3.AmazonS3;
import issac.issac_server.file.exception.FileErrorCode;
import issac.issac_server.file.exception.FileException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class S3Remover {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3Client;

    public void deleteObjects(List<String> urls) {
        urls.forEach(this::deleteObjectByUrl);
    }

    public void deleteObjectByUrl(String url) {
        String key = extractKeyFromUrl(url);
        amazonS3Client.deleteObject(bucket, key);
    }

    private String extractKeyFromUrl(String url) {
        int index = url.indexOf(".com/");
        if (index == -1) {
            throw new FileException(FileErrorCode.INVALID_S3_URL);
        }
        return url.substring(index + 5);
    }
}

