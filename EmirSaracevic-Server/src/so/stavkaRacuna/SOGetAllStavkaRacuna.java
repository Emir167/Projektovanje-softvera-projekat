/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaRacuna;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.StavkaRacuna;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOGetAllStavkaRacuna extends AbstractSO {
     private ArrayList<StavkaRacuna> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StavkaRacuna)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaRacuna!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> stavke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaRacuna>) (ArrayList<?>) stavke;
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }
}
