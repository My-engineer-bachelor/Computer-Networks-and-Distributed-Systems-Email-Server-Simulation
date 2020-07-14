package readers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import util.Email;

public class EmailsReader {
	public static List<Email> read(String data){
		if (!data.isEmpty()){
                    List<Email> emails = new ArrayList<Email> ();
                    String[] parts = data.split(Pattern.quote("|"));
                    for (int i = parts.length - 1; i >= 0; i--){			
                            emails.add(EmailReader.read(parts[i]));
                    }
                    return emails;
                }
                return null;
	}
}
