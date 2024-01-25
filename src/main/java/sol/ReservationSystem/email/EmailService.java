package sol.ReservationSystem.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;
    public void sendMail(EmailDto emailDto) {
        LocalDateTime time = LocalDateTime.now();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(emailDto.getEmail());
            helper.setSubject("[예약구매] 인증번호");
            helper.setText(makeInt() + " 를 입력해주세요.\n" + "인증번호 만료 시각 : " + time.plusMinutes(5).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")));
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makeInt() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
    }
}
