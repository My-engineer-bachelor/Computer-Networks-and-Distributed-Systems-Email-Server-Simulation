package server;

import readers.PasswordEmailReader;
import java.util.ArrayList;
import java.util.List;

import tcp.ConnectionTCP;
import tcp.ServerTCP;
import util.Constants;
import util.Email;
import util.EnumEmail;
import client.Count;
import java.util.regex.Pattern;
import readers.CountReader;
import readers.EmailReader;
import readers.OperationReader;

public class EmailServer {	
	private String name;	
	private List<Count> counts;
	private List<Email> emails;	
	private final String serverIp;
	private final int serverPort;
	public Count emailServerCount;	
	
	public EmailServer(String name) {
		super();
		this.name = name;		
		counts = new ArrayList<Count> ();
		emails = new ArrayList<Email> ();
		serverIp = Constants.serverIp;
		serverPort = Constants.serverPort;
		emailServerCount = new Count(name+"@"+name+".com", name, null);		
		(new ServerTCP(serverPort, this)).listen();								
	}
        
	public void autenticate(String emailAddress, String password, ConnectionTCP connection){		
            for (Count count:counts){            	
                if (count.getEmailAddress().equals(emailAddress) && count.getPassword().equals(password)){
                    connection.setReturnData(count.toString());
                    return;
                }
            }
            connection.setReturnData("");
        }
        
	public void sendEmail(Email email, ConnectionTCP connection){		
		Count to = email.getTo(),
			from = email.getFrom();
		boolean containsTo = counts.contains(to),
				containsFrom = counts.contains(from);		
		if (containsTo && containsFrom){			
			emails.add(email);
		}else if (!containsTo && containsFrom){								
			Email emailToSend = new Email(from, emailServerCount, "Error: E-mail to aren't registered.", "There's a error, the "+to.getEmailAddress()+" arent' registered..");
			emails.add(emailToSend);					
		}
	}
		
	public void receiveDataFromTCP(String data, ConnectionTCP connection){		
		EnumEmail enumEmail = OperationReader.read(data);
		if (enumEmail.getOperation() == EnumEmail.GET_RECEIVED_EMAILS.getOperation()){
			List<Email> emails = getReceivedEmails(CountReader.read(data));
			String receivedEmails = "";
			for(Email email:emails)
				receivedEmails += email.toString()+"|";
			connection.setReturnData(receivedEmails);
		}
		else if (enumEmail.getOperation() == EnumEmail.REGISTER_COUNT.getOperation())			
			registerCount(CountReader.read(data));			
		else if (enumEmail.getOperation() == EnumEmail.SEND_EMAIL.getOperation())	
			sendEmail(EmailReader.read(data), connection);					
        else if (enumEmail.getOperation() == EnumEmail.AUTENTICATE.getOperation()){
            String[] parts = data.split(Pattern.quote("|"));
            autenticate(parts[0], PasswordEmailReader.read(parts[1]), connection);
        }
	} 		
	
	public List<Email> getReceivedEmails(Count count){		
		List<Email> emails = new ArrayList<Email> ();
		for (int i = this.emails.size() - 1; i >= 0; i--){
			Email currentEmail = this.emails.get(i);
			//if (currentEmail.getTo().equals(count) && !currentEmail.isRead()){				
			if (currentEmail.getTo().equals(count)){
				currentEmail.read();
				emails.add(currentEmail);				
			}
		}				
		return emails;
	}
	
	public List<Email> getSentEmails(Count count){
		List<Email> emails = new ArrayList<Email> (); 
		for (Email email : this.emails) 
			if (email.getFrom().equals(count))
				emails.add(email);
		return emails;
	}	
	
	public void registerCount(Count count){
		if (count != null)			
			counts.add(count);
		else
			throw new RuntimeException("Error! empty count, the count is null!");
	}		
	
	public String getName() {
		return name;
	}

	public Count getEmailServerCount() {
		return emailServerCount;
	}

	public void setEmailServerCount(Count emailServerCount) {
		this.emailServerCount = emailServerCount;
	}

	public String getServerIp() {
		return serverIp;
	}

	public int getServerPort() {
		return serverPort;
	}			
	
}
