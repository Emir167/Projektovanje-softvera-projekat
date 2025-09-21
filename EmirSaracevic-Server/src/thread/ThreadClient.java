/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import operacije.Status;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author korisnk
 */
public class ThreadClient extends Thread{
    
    
    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev request = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor handleRequest(KlijentskiZahtev request) {
        ServerskiOdgovor response = new ServerskiOdgovor(null, null, Status.Success);
        try {
            switch (request.getOperacija()) {

                
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(Status.Error);
            response.setExc(ex);
        }
        return response;
    }
}
