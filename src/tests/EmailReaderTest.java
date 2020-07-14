package tests;

import client.Count;
import readers.EmailReader;
import util.Email;

public class EmailReaderTest {
	public static void main(String args[]){
		Email email = new Email(new Count("matheus@gmail.com", "Matheus", "123456"), new Count("francisco@gmail.com", "Francisco", "jhgklo"), "NE", "Novo Email");
		EmailReader.read(email.toString());
	}
}
