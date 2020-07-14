/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deyvison
 */
public class ConnectionTCP extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
	private ServerTCP serverTcp;
	private String returnData;

	public ConnectionTCP(Socket clientSocket, ServerTCP serverTcp) {
		this.serverTcp = serverTcp;
		try {
			this.setClientSocket(clientSocket);
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException ex) {
			Logger.getLogger(ConnectionTCP.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		try {						
			serverTcp.receiveDataFromClient(in.readUTF(), this);			
			if (returnData != null){		
				out.writeUTF(returnData);
				returnData = null;
			}			
		} catch (IOException ex) {
			Logger.getLogger(ConnectionTCP.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public String getReturnData() {
		return returnData;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}
	
}
