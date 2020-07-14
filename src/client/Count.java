package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import readers.EmailsReader;
import tcp.ClientTCP;
import util.Constants;
import util.Email;
import util.EnumEmail;

public class Count {
	private String emailAddress,
					userName,
					password;	
	private List<Email> receivedEmails,
						sentEmails;	
	private ClientTCP clientTcp;		 	

	public Count(String emailAddress, String userName, String password) {
		super();
		this.emailAddress = emailAddress;
		this.userName = userName;
		this.password = password;
		receivedEmails = new ArrayList<Email> ();
		sentEmails = new ArrayList<Email> ();			
	}	

	public void registerInEmailServer(){		
		sendDataToEmailServer(this.toString(), EnumEmail.REGISTER_COUNT);		
	}
	
	@Override
	public String toString() {
		return "Count [emailAddress=" + emailAddress + ", userName=" + userName + ", password=" + password + "]";
	}

	@Override
	public boolean equals(Object obj) {		
		if (obj == null || getClass() != obj.getClass())
			return false;							
		Count otherCount = (Count) obj;
		return emailAddress.equals(otherCount.emailAddress);		
	}

	public void receiveEmails(){				
		String emails = sendDataToAndReadFromEmailServer(this.toString(), EnumEmail.GET_RECEIVED_EMAILS).trim();		
		if (!emails.isEmpty()){			
			List<Email> receivedEmailFromCount = this.getReceivedEmails(),
						receivedEmails = EmailsReader.read(emails);
			System.out.println(receivedEmailFromCount.size());
			for(Email email: receivedEmails){
				addReceivedEmail(email);
			}
		}		
	}
	
	public void sendEmail(Email email){		
		sentEmails.add(email);
		sendDataToEmailServer(email.toString(), EnumEmail.SEND_EMAIL);				
	}
	
	public void sendEmail(String content, String title, Count to){		
		Email email = new Email(to, this, title, content);
		sentEmails.add(email);		
		sendDataToEmailServer(email.toString(), EnumEmail.SEND_EMAIL);				
	}		
	
	private void sendDataToEmailServer(String data, EnumEmail enumEmail){		
		open();
		clientTcp.send(data+enumEmail.toString());		
		close();
	}
	
	private String sendDataToAndReadFromEmailServer(String data, EnumEmail enumEmail){		
		open();
		clientTcp.send(data+enumEmail.toString());
		String receivedData = clientTcp.read();				
		close();
		return receivedData;
	}	
	
	private void open(){
		clientTcp = new ClientTCP(Constants.serverPort, Constants.serverIp);
	}
	
	private void close(){
		try {
			clientTcp.getSocket().close();
			clientTcp = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
    public void addReceivedEmail(Email email){
    	List<Email> receivedEmailFromCount = getReceivedEmails();
    	boolean received = false;
		for (Email emailFromCount:receivedEmailFromCount)
			if (email.equals(emailFromCount))
				received = true;
		if (!received)
			receivedEmailFromCount.add(email);        
    }               
        
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Email> getReceivedEmails() {
		return receivedEmails;
	}

	public List<Email> getSentEmails() {
		return sentEmails;
	}		
	
}