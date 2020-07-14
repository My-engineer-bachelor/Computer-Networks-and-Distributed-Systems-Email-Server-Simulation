package readers;

import client.Count;
import util.Email;

public class EmailReader {	
	public static Email read(String email){
		return new Email(countTo(email), countFrom(email), getTitle(email), getContent(email));
	}
		
	private static String getTitle(String email){
		String[] parts1 = email.split(", content=");		
		if (parts1.length == 2){
			String[] parts2 = parts1[1].split(", title=");
			if (parts2.length == 2){
				String[] parts3 = parts2[1].split("]");				
				return parts3[0];
			}
		}
		throw new RuntimeException("Erro: E-mail invalid.");
	}
	
	private static String getContent(String email){
		String[] parts1 = email.split(", title=");		
		if (parts1.length == 2){
			String[] parts2 = parts1[0].split(", content=");
			if (parts2.length == 2)
				return parts2[1];
		}
		throw new RuntimeException("Erro: E-mail invalid.");
	}
	
	private static Count countFrom(String email){
		String[] parts1 = email.split(", from=");		
		if (parts1.length == 2){
			String[] parts2 = parts1[1].split(", content=");
			if (parts2.length == 2)
				return CountReader.read(parts2[0]);
		}
		throw new RuntimeException("Erro: E-mail invalid.");		
	}	
	
	private static Count countTo(String email){		
		String[] parts1 = email.split("to=");		
		if (parts1.length == 2){			
			String[] parts2 = parts1[1].split(", from=");			
			if (parts2.length == 2){								
				return CountReader.read(parts2[0]);
			}
				
		}				
		throw new RuntimeException("Erro: E-mail invalid.");		
	}	
}
