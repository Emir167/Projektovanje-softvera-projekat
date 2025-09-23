package thread;

import controller.ServerController;
import domain.Gost;
import domain.Racun;
import domain.Recepcioner;
import domain.StrucnaSprema;
import domain.VrstaUsluge;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import operacije.Operacije;
import operacije.Status;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class ThreadClient extends Thread {

    private final Socket socket;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(socket.getInputStream())) {

            while (!socket.isClosed()) {
                Object obj = in.readObject();
                if (!(obj instanceof KlijentskiZahtev)) break;

                KlijentskiZahtev request = (KlijentskiZahtev) obj;
                ServerskiOdgovor response = handleRequest(request);

                out.writeObject(response);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor handleRequest(KlijentskiZahtev request) {
        ServerskiOdgovor response = new ServerskiOdgovor(null, null, Status.Success);
        try {
            Operacije op = request.getOperacija();
            Object p = request.getParam();

            switch (op) {
                
                case LOGIN: {
                    Recepcioner creds = (Recepcioner) p;
                    Recepcioner ulogovani = ServerController.getInstance().login(creds);
                    response.setOdgovor(ulogovani);
                    break;
                }

                
                case RECEPCIONER_GET_ALL:
                    response.setOdgovor(ServerController.getInstance().getAllRecepcioner());
                    break;

                
                case GOST_KREIRAJ:
                    ServerController.getInstance().kreirajGosta((Gost) p);
                    break;
                case GOST_PRETRAZI:
                    response.setOdgovor(ServerController.getInstance().pretraziGoste(p));
                    break;
                case GOST_IZMENI:
                    ServerController.getInstance().izmeniGosta((Gost) p);
                    break;
                case GOST_OBRISI:
                    ServerController.getInstance().obrisiGosta((Gost) p);
                    break;

                
                case RACUN_KREIRAJ:
                    ServerController.getInstance().kreirajRacun((Racun) p);
                    break;
                case RACUN_PRETRAZI:
                    response.setOdgovor(ServerController.getInstance().pretraziRacune(p));
                    break;
                case RACUN_IZMENI:
                    ServerController.getInstance().izmeniRacun((Racun) p);
                    break;

                
                case STRUCNA_SPREMA_UNESI:
                    ServerController.getInstance().unesiStrucnuSpremu((StrucnaSprema) p);
                    break;
                    
                case USLUGA_UNESI:
                    ServerController.getInstance().unesiUslugu((VrstaUsluge) p);
                    break;
                case USLUGA_PRETRAZI:
                    response.setOdgovor(ServerController.getInstance().getAllUsluga());
                    break;
                case USLUGA_IZMENI:
                    ServerController.getInstance().izmeniUslugu((VrstaUsluge) p);
                    break;
                case USLUGA_OBRISI:
                    ServerController.getInstance().obrisiUslugu((VrstaUsluge) p);
                    break;
                default:
                    throw new UnsupportedOperationException("Nepodržana operacija: " + op);
            }
        } catch (Exception ex) {
            response.setResponseStatus(Status.Error);
            response.setExc(ex);
        }
        return response;
    }
}
