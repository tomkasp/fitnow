package com.tomkasp.service;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by tomkasp on 17/09/16.
 */
public class MailServiceTest {

    @Test
    public void sendEmail() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "tomkasp.nazwa.pl");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("tomkasp@tomkasp.nazwa.pl","Builder123");
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@fitnowcloud.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("tomkasp@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
