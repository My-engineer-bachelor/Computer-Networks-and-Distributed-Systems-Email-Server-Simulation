package tests;

import client.Count;
import util.Email;

public class EmailClientTest {
	public static void main(String[] args) throws InterruptedException {
		Count matheusCount = new Count("matheus@gmail.com", "matheus", "asdasd"),
				franciscoCount = new Count("francisco@gmail.com", "francisco", "ghjkl");		
		matheusCount.registerInEmailServer();		
		Thread.sleep(1000);
		matheusCount.sendEmail(new Email(franciscoCount, matheusCount, "Email de teste", "Este é um email de teste"));
		Thread.sleep(1000);
		matheusCount.receiveEmails();
		Thread.sleep(1000);		
	}
}
