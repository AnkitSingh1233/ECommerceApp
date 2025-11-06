package in.nareshit.ankit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {


    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${master.user.email}")
    private String fromEmail;


    public Boolean send(String[] to, String[] cc, String[] bcc, String subject, String text, Resource[] files) {
        boolean sent = false;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, files != null && files.length > 0);

            helper.setTo(to);
            if (cc != null) helper.setCc(cc);
            if (bcc != null) helper.setBcc(bcc);

            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom(fromEmail);

            if (files != null) {
                for (Resource file : files) {
                    helper.addAttachment(file.getFilename(), file);
                }
            }

            mailSender.send(message);
            sent = true;

        } catch (Exception e) {
            e.printStackTrace();
            sent = false;
        }

        return sent;
    }

    public Boolean send(String to, String subject, String text, Resource file) {
        return send(new String[]{to}, null, null, subject, text, file != null ? new Resource[]{file} : null);
    }

    public Boolean send(String to, String subject, String text) {
        return send(to, subject, text, null);
    }
}
