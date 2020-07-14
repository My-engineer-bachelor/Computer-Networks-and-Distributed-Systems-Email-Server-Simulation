package tests;

import client.Count;
import readers.OperationReader;
import util.Email;
import util.EnumEmail;

public class OperationReaderTest {
	public static void main(String[] args){
		Email email = new Email(new Count("Matheus", "Matheus", "Matheus"), new Count("Matheus", "Matheus", "Matheus"), "ghghghg", "jhgjgjhgjhgj");
		System.out.println(OperationReader.read(email.toString()+EnumEmail.GET_RECEIVED_EMAILS.toString()));
	}
}
