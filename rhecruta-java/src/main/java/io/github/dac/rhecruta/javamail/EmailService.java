package io.github.dac.rhecruta.javamail;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailService {

    public void sendEmail(String emailDestinatario, String titulo, String corpo) {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("projetorhecruta@gmail.com",
                                "!@rhecruta12");
                    }
                });
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projetorhecruta@gmail.com"));

            Address[] revicerUser = InternetAddress.parse(emailDestinatario);

            message.setRecipients(Message.RecipientType.TO, revicerUser);
            message.setSubject(titulo);
            message.setText(corpo);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}