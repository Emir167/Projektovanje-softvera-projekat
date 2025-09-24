package controller;

import domain.Drzavljanstvo;
import domain.Gost;
import domain.Racun;
import domain.Recepcioner;
import domain.StavkaRacuna;
import domain.StrucnaSprema;
import domain.VrstaUsluge;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import operacije.Operacije;
import operacije.Status;
import session.Session;               
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class KlijentController {

    private static KlijentController instance;

    private KlijentController() {}

    public static KlijentController getInstance() {
        if (instance == null) instance = new KlijentController();
        return instance;
    }

    private Object sendRequest(Operacije op, Object data) throws Exception {
        KlijentskiZahtev req = new KlijentskiZahtev(op, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        ServerskiOdgovor resp = (ServerskiOdgovor) in.readObject();

        if (resp.getResponseStatus() == Status.Error) {
            throw resp.getExc();
        }
        return resp.getOdgovor();
    }


    public Recepcioner login(Recepcioner creds) throws Exception {
        return (Recepcioner) sendRequest(Operacije.LOGIN, creds);
    }

    public ArrayList<Recepcioner> getAllRecepcioner() throws Exception {
        return (ArrayList<Recepcioner>) sendRequest(Operacije.RECEPCIONER_GET_ALL, null);
    }

    

    public void addGost(Gost g) throws Exception {
        sendRequest(Operacije.GOST_KREIRAJ, g);
    }


    public ArrayList<Gost> getAllGost() throws Exception {
        return (ArrayList<Gost>) sendRequest(Operacije.GOST_PRETRAZI, null);
    }

   
    public ArrayList<Gost> searchGost(Gost filter) throws Exception {
        return (ArrayList<Gost>) sendRequest(Operacije.GOST_PRETRAZI, filter);
    }

    public void updateGost(Gost g) throws Exception {
        sendRequest(Operacije.GOST_IZMENI, g);
    }

    public void deleteGost(Gost g) throws Exception {
        sendRequest(Operacije.GOST_OBRISI, g);
    }


    public void addRacun(Racun r) throws Exception {
        sendRequest(Operacije.RACUN_KREIRAJ, r);
    }

    

    public ArrayList<Racun> getAllRacun(Racun filter) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operacije.RACUN_PRETRAZI, filter);
    }

    public void updateRacun(Racun r) throws Exception {
        sendRequest(Operacije.RACUN_IZMENI, r);
    }

   

    public void addStrucnaSprema(StrucnaSprema ss) throws Exception {
        sendRequest(Operacije.STRUCNA_SPREMA_UNESI, ss);
    }

   

    public void addVrstaUsluge(VrstaUsluge v) throws Exception {
        sendRequest(Operacije.USLUGA_UNESI, v);
    }


    public ArrayList<VrstaUsluge> getAllVrstaUsluge() throws Exception {
        return (ArrayList<VrstaUsluge>) sendRequest(Operacije.USLUGA_PRETRAZI, null);
    }

    public ArrayList<VrstaUsluge> searchVrstaUsluge(VrstaUsluge filter) throws Exception {
        return (ArrayList<VrstaUsluge>) sendRequest(Operacije.USLUGA_PRETRAZI, filter);
    }

    public void updateVrstaUsluge(VrstaUsluge v) throws Exception {
        sendRequest(Operacije.USLUGA_IZMENI, v);
    }

    public void deleteVrstaUsluge(VrstaUsluge v) throws Exception {
        sendRequest(Operacije.USLUGA_OBRISI, v);
    }
    public ArrayList<StavkaRacuna> getAllStavkaRacuna(Racun idRacuna) throws Exception {
            return (ArrayList<StavkaRacuna>) sendRequest(Operacije.STAVKA_RACUNA_GET_ALL, idRacuna);
    }

    public ArrayList<Drzavljanstvo> getAllDrzavljanstvo() throws Exception {
            return (ArrayList<Drzavljanstvo>) sendRequest(Operacije.DRZAVLJANSTVO_GET_ALL, null);
    }

    public void unesiUslugu(VrstaUsluge vu) throws Exception {
    sendRequest(Operacije.USLUGA_UNESI, vu);
    }

}
