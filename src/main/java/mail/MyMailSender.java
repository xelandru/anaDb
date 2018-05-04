package mail;


import configuration.CandidateProperties;
import domain.Candidate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MyMailSender {


    private static final CandidateProperties properties = new CandidateProperties();
    private static void sendMails(List<Candidate> candidates) {


        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getMailUserName(),
                                properties.getMailPassword());
                    }
                });
        try {

            InternetAddress sender = new InternetAddress(properties.getMailSenderAddress());
            Message message = new MimeMessage(session);
            message.setFrom(sender);

            for (Candidate candidate : candidates) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(candidate.getEmail()));
                message.setSubject("Mail test");
                message.setText("HI");
                Transport.send(message);
            }
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
