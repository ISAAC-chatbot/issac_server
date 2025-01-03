package issac.issac_server.auth.application;

import issac.issac_server.auth.application.dto.EmailRequest;
import issac.issac_server.auth.application.dto.EmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    public static String SUBJECT = "ISSAC : 회원가입 인증번호 안내";
    public static String TEXT = "안녕하세요? ISSAC입니다. 인증번호를 확인하시고 회원가입을 진행해주세요.\n\n인증번호 : %s";

    public EmailResponse send(EmailRequest request) throws MessagingException {

        String code = generateAuthCode();
        String formattedText = String.format(TEXT, code);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(request.getEmail());
        helper.setSubject(SUBJECT);
        helper.setText(formattedText);

        try {
            mailSender.send(mimeMessage);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to send email in sendEmail", e);
        }

        return new EmailResponse(code);
    }

    private String generateAuthCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // 100000 ~ 999999
    }
}
