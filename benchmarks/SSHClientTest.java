package edu.rice.pliny.apitrans.examples;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


/**
 * Connect to a FTP Server and then list the files in the default directory
 */
public class SSHClientTest {

    @Test
    public void send() throws IOException, EmailException {
        String username = "username";
        String password = "password";
        String smtp = "smtp";
        String from = "from";
        String subject = "subject";
        String msg = "msg";
        String to = "to";

        Email email = new SimpleEmail();
        email.setSmtpPort(587);
        DefaultAuthenticator auth = new DefaultAuthenticator(username, password);
        email.setHostName(smtp);
        email.setFrom(from);
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }

    @Test
    public void send2() throws IOException, EmailException, MessagingException {
        String username = "username";
        String password = "password";
        String smtp = "smtp";
        String from = "from";
        String subject = "subject";
        String msg = "msg";
        String to = "to";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtp);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        DefaultAuthenticator auth = new DefaultAuthenticator(username, password);
        Session session = Session.getDefaultInstance(prop, auth);
        Message message = new MimeMessage(session);
        InternetAddress from_addr = new InternetAddress(from);
        message.setFrom(from_addr);
        Message.RecipientType to_addr_type = Message.RecipientType.TO;
        InternetAddress[] to_addr = InternetAddress.parse(to);
        message.setRecipients(to_addr_type, to_addr);
        message.setSubject(subject);
        message.setText(msg);
        Transport.send(message);
    }
}
