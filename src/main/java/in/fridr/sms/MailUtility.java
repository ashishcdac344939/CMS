package in.fridr.sms;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtility {

	public MailUtility() {
		super();
		// TODO Auto-generated constructor stub
	}
/**
 * 
 * @param emailId
 * @param otp
 * @return return true if otp sent successfully otherwise false
 */
	public boolean sendMail(String emailId,String htmlContent) {
		//Email id and password of source gmail account and set the all required properties
		final String username = "cmsaiims007@gmail.com";
        final String password = "yrardkkeyfiwjvnv";
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cmsaiims007@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailId)
            );
            message.setSubject("CMS One Time Password");
            
            // Set the HTML content as the message text
           // message.setContent(htmlContent, "text/html");
            // Set the HTML content as the message text
            message.setContent(htmlContent, "text/html");
            
           // message.setText("OTP: "+otp);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
	}

}

