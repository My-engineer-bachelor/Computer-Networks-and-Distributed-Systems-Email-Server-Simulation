package readers;

import client.Count;

public class CountReader {
	public static Count read(String data){
		System.out.println(data);
		String[] parts1 = data.split("emailAddress=");		
		if (parts1.length == 2){			
			String[] parts2 = parts1[1].split(", userName=");			
			if (parts2.length == 2){
				String[] parts3 = parts2[1].split(", password=");			
				if (parts2.length == 2){				
					String[] parts4 = parts3[1].split("]");								
                                        if (parts4.length == 0)
                                            return new Count(parts2[0], parts3[0], "");
                                        else
                                            return new Count(parts2[0], parts3[0], parts4[0]);
				}				
			}
		}
		throw new RuntimeException("Erro: Count invalid.");
	}
}
