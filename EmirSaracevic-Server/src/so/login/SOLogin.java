/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Recepcioner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOLogin extends AbstractSO{
    
    Recepcioner ulogovaniRecepcioner;
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Recepcioner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Recepcioner!");
        }
        Recepcioner r = (Recepcioner) ado;
        for (Recepcioner recepcioner : ServerController.getInstance().getUlogovaniRecepcioneri()) {
            if (recepcioner.getKorisnickoIme().equals(r.getKorisnickoIme())) {
                throw new Exception("Recepcioner je vec ulogovan!");
            }
        }

    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        Recepcioner r = (Recepcioner) ado;

        ArrayList<Recepcioner> recepcioneri
                = (ArrayList<Recepcioner>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Recepcioner recepcioner : recepcioneri) {
            if (recepcioner.getKorisnickoIme().equals(r.getKorisnickoIme())
                    && recepcioner.getSifra().equals(r.getSifra())) {
                ulogovaniRecepcioner = recepcioner;
                ServerController.getInstance().getUlogovaniRecepcioneri().add(recepcioner);
                return;
            }
        }
        throw new Exception("Recepcioner sa tim kredencijalima ne postoji!");
    }

    public Recepcioner getUlogovaniRecepcioner() {
        return ulogovaniRecepcioner;
    }
    
    
}
