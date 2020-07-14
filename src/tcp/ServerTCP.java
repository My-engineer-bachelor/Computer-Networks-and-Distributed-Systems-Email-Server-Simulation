package tcp;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.EmailServer;

/**
 *
 * @author deyvison
 */
public class ServerTCP {
	private int serverPort;
	private ServerSocket listenSocket;		
	private EmailServer emailServer;
	
	public ServerTCP(int serverPort, EmailServer emailServer) {
		this.emailServer = emailServer;
		this.setServerPort(serverPort);
		try {
			listenSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void receiveDataFromClient(String data, ConnectionTCP connection){		
		emailServer.receiveDataFromTCP(data, connection);
	}
	
	public String sendDataToClient(String data){
		return data;
	}
	
	public void listen(){		
        try {                                    
            while(true){
                Socket client = listenSocket.accept();
                new ConnectionTCP(client, this);                
            }                        
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
}
