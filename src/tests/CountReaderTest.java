package tests;

import client.Count;
import readers.CountReader;

public class CountReaderTest {
	public static void main(String[] args){
		Count oldCount = new Count("matheus", "matheus", "nhhhdh"),
				newCount = CountReader.read(oldCount.toString());
		System.out.println(newCount.toString());
	}
}
