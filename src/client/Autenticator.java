/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import readers.CountReader;
import tcp.ClientTCP;
import util.Constants;

/**
 *
 * @author cvtlab6
 */
public class Autenticator {
    private ClientTCP clientTcp;
      
    public Count autenticate(String emailAddress, String password){
        clientTcp = new ClientTCP(Constants.serverPort, Constants.serverIp);
        clientTcp.send(emailAddress+"|"+password);
        String dataReturned = clientTcp.read();
        if (dataReturned != "")
            return CountReader.read(dataReturned);
        try {
            clientTcp.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(Autenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        clientTcp = null;
        throw new RuntimeException("Count wasn't found.");
    }
}
