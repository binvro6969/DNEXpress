/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package google_context;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author haian
 */
public class SendEmail {
    
    public String getRandom(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    
    public boolean sendEmail (UserEmail userEmail){
        boolean test = false;
        final String from ="dnexpressd04@gmail.com";
        final String pass ="uuocxjlixiflrsfb";       
        
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        //create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,pass);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }          
        };
        
        Session session = Session.getInstance(props, auth);
        
        //gui mail
        final String to = userEmail.getAccount().getEmail(); 
        MimeMessage msg = new MimeMessage(session);
        try {     
            //người gửi       
            msg.setFrom(new InternetAddress(from, "DN EXPRESS"));
            //người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            //tiêu đề Email
            msg.setSubject("Your OTP");
            
            //Nội dung
            msg.setText("Enter your OTP:"+userEmail.getCode());
            Transport.send(msg);
            test = true;
        } catch (Exception e) {
            
        }
        return test;
    }
}
