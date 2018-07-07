package com.bank.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bank.bo.UserAuthenticationBO;
import com.bank.form.LoginForm;

public class SendMail {
	
	private String host;
	private String from;
	private String pass;
	private Properties props;
	
	public SendMail(){
		host = "mail.jewelbankers.com";
	    from = BankConstant.SUPPORT_MAIL_ID;
	    pass = "Bhawarlalkag@82";
	    props = System.getProperties();
	    //props.put("mail.smtp.starttls.enable", "true"); // added this line
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "26");
	    props.put("mail.smtp.auth", "true");
	    
	/* This seettings is for gmail id   
	 * host = "smtp.gmail.com";
	    from = BankConstant.SUPPORT_MAIL_ID;
	    pass = "Bhawarlalkag@82";
	    props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", "true"); // added this line
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	*/
	}
	
	public void sendGmail(String[] to, String subject, String body){
		try{
			
		
	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(from));
	 
	    InternetAddress[] toAddress = new InternetAddress[to.length];
	 
	    // To get the array of addresses
	    for( int i=0; i < to.length; i++ ) { // changed from a while loop
	        toAddress[i] = new InternetAddress(to[i]);
	    }
	 
	    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
	        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    }
	    message.setSubject(subject);
	    message.setText(body);
	    Transport transport = session.getTransport("smtp");
	    transport.connect(host, from, pass);
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
		}catch(AddressException ae){
			ae.printStackTrace();
		}catch(MessagingException me){
			me.printStackTrace();
		}finally{
			
		}
	}
	
	public static void main(String[] args) throws Exception {
	// Send a test message
	SendMail sender = new SendMail();
	 String[] toAddress = {"jewelbankers@gmail.com","support@jewelbankers.com"}; // added this line
	 //String[] toAddress = {"support@jewelbankers.com"}; // added this line
	 String subject = "Test mail from support@jewelbankers.com";
	 String body = "Test mail from mahesh4kc@gmail.com.com - java code";
	sender.sendGmail(toAddress,subject,body);
	//sender.send("smtp.gmail.com", 465, "mahesh4kc@gmail.com", "mahesh4kc@gmail.com", "Test Mail from java", "Test Mail from java");
	}
	
	public String getSubject(){
		String subject = "Jewel Bankers Account Details";
		return subject;
	}
	
	public String getBody(LoginForm loginForm){
		StringBuffer body = new StringBuffer("Welcome to Jewelbankers\n");
		body.append("User Id:"+loginForm.getLoginID()+"\n");
		body.append("For any queries reach us at: "+BankConstant.SUPPORT_MAIL_ID+"\n");
		return body.toString();
	}
	
	public String getBodyForResetPassword(UserAuthenticationBO userAuthenticationBO){
		StringBuffer body = new StringBuffer("You have opted to reset the password\n");
		CryptoLibrary cryptoLibrary = new CryptoLibrary();
		String decryptLoginId = cryptoLibrary.decrypt(userAuthenticationBO.getLoginID());
		String decryptPassword = cryptoLibrary.decrypt(userAuthenticationBO.getPassword());
		body.append("User Id:"+decryptLoginId+"\n");
		body.append("Password:"+decryptPassword+"\n");
		body.append("For any queries reach us at: "+BankConstant.SUPPORT_MAIL_ID+"\n");
		return body.toString();
	}
	
	public String getSubjectForAccountPasswordReset(){
		String subject = "Password for Jewel Bankers is reset";
		return subject;
	}
	
	
	
}