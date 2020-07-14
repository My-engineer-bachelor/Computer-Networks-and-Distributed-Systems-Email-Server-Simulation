package readers;

import util.EnumEmail;

public class OperationReader {
	public static EnumEmail read(String data){		
		String[] parts1 = data.split("type=");
		int parts1size = parts1.length;		
		if (parts1size == 2){
			String[] parts2 = parts1[1].split("]");
			int convertedOperation = Integer.valueOf(parts2[0]);
			if (convertedOperation == EnumEmail.GET_RECEIVED_EMAILS.getOperation())
				return EnumEmail.GET_RECEIVED_EMAILS;
			else if (convertedOperation == EnumEmail.REGISTER_COUNT.getOperation())
				return EnumEmail.REGISTER_COUNT;
			else if (convertedOperation == EnumEmail.SEND_EMAIL.getOperation())
				return EnumEmail.SEND_EMAIL;
			else if (convertedOperation == EnumEmail.AUTENTICATE.getOperation())
				return EnumEmail.AUTENTICATE;
		}
		throw new RuntimeException("Erro: Operation invalid.");
	}		
}
