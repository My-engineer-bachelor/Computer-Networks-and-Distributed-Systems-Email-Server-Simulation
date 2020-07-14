package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
	private Socket socket;
	private int serverPort;
	private String serverIpAddress,
					receivedData;
	private DataInputStream in;
	private DataOutputStream out;
	
	public ClientTCP(int serverPort, String serverIpAddress) {
		super();
		this.setServerPort(serverPort);
		this.setServerIpAddress(serverIpAddress);
		try {
			socket = new Socket(serverIpAddress, serverPort);			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void send (String data){
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(data);						
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public String read(){
		try {
			return receivedData = in.readUTF();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getReceivedData() {
		return receivedData;
	}

	public void setReceivedData(String receivedData) {
		this.receivedData = receivedData;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}
	
}
