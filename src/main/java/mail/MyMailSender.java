package mail;


import domain.Candidate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MyMailSender {

    private static final String PROPERTIES_FILE = "configuration.properties";
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static Properties loadProperties() {

        Properties properties = new Properties();
        InputStream is = MyMailSender.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    private static void sendMails(List<Candidate> candidates) {


        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("username"),
                                properties.getProperty("password"));
                    }
                });
        try {

            InternetAddress sender = new InternetAddress(properties.getProperty("sender.address"));
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
