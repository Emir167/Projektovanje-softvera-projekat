package controller;

import domain.Gost;
import domain.Racun;
import domain.Recepcioner;
import domain.StrucnaSprema;
import domain.VrstaUsluge;

import java.util.ArrayList;

// SO paketi po tvojoj strukturi
import so.login.SOLogin;

import so.gost.SOAddGost;
import so.gost.SODeleteGost;
import so.gost.SOGetAllGost;
import so.gost.SOUpdateGost;

import so.racun.SOAddRacun;
import so.racun.SOGetRacun;
import so.racun.SOUpdateRacun;

import so.recepcioner.SOGetAllRecepcioner;

import so.strucna_sprema.SOAddStrucnaSprema;
import so.strucna_sprema.SOGetAllStrucnaSprema;

import so.vrstaUsluge.SOAddVrstaUsluge;
import so.vrstaUsluge.SODeleteVrstaUsluge;
import so.vrstaUsluge.SOGetAllVrstaUsluge;
import so.vrstaUsluge.SOUpdateVrstaUsluge;

public class ServerController {

    private static ServerController instance;
    private final ArrayList<Recepcioner> ulogovaniRecepcioneri = new ArrayList<>();

    private ServerController() { }

    public static synchronized ServerController getInstance() {
        if (instance == null) instance = new ServerController();
        return instance;
    }

    public ArrayList<Recepcioner> getUlogovaniRecepcioneri() {
        return ulogovaniRecepcioneri;
    }

    
    public Recepcioner login(Recepcioner kredencijali) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(kredencijali);
        Recepcioner ulogovani = so.getUlogovaniRecepcioner();
        if (ulogovani != null && !ulogovaniRecepcioneri.contains(ulogovani)) {
            ulogovaniRecepcioneri.add(ulogovani);
        }
        return ulogovani;
    }

    public void logout(Recepcioner ulogovani) {
        ulogovaniRecepcioneri.remove(ulogovani);
    }

    
    public Object getAllRecepcioner() throws Exception {
        SOGetAllRecepcioner so = new SOGetAllRecepcioner();
        so.templateExecute(new Recepcioner());
        return so.getLista();
    }

    
    public void kreirajGosta(Gost gost) throws Exception {
        new SOAddGost().templateExecute(gost);
    }

    public Object pretraziGoste(Object filter) throws Exception {
        
        SOGetAllGost so = new SOGetAllGost();
        if (filter instanceof Gost) {
            so.templateExecute((Gost) filter);
        } else {
            so.templateExecute(new Gost());
        }
        return so.getLista();
    }

    public void izmeniGosta(Gost gost) throws Exception {
        new SOUpdateGost().templateExecute(gost);
    }

    public void obrisiGosta(Gost gost) throws Exception {
        new SODeleteGost().templateExecute(gost);
    }

    
    public void kreirajRacun(Racun racun) throws Exception {
        new SOAddRacun().templateExecute(racun);
    }

    public Object pretraziRacune(Object filter) throws Exception {
        SOGetRacun so = new SOGetRacun();
        if (filter instanceof Racun) {
            so.templateExecute((Racun) filter);
        } else {
            so.templateExecute(new Racun());
        }
        return so.getLista();
    }

    public void izmeniRacun(Racun racun) throws Exception {
        new SOUpdateRacun().templateExecute(racun);
    }

    
    public void unesiStrucnuSpremu(StrucnaSprema ss) throws Exception {
        new SOAddStrucnaSprema().templateExecute(ss);
    }

    public Object getAllStrucnaSprema() throws Exception {
        SOGetAllStrucnaSprema so = new SOGetAllStrucnaSprema();
        so.templateExecute(new StrucnaSprema());
        return so.getLista();
    }

    
    public void unesiUslugu(VrstaUsluge vu) throws Exception {
        new SOAddVrstaUsluge().templateExecute(vu);
    }

    public void izmeniUslugu(VrstaUsluge vu) throws Exception {
        new SOUpdateVrstaUsluge().templateExecute(vu);
    }

    public void obrisiUslugu(VrstaUsluge vu) throws Exception {
        new SODeleteVrstaUsluge().templateExecute(vu);
    }

    public Object getAllUsluga() throws Exception {
        SOGetAllVrstaUsluge so = new SOGetAllVrstaUsluge();
        so.templateExecute(new VrstaUsluge());
        return so.getLista();
    }
   
}
   
