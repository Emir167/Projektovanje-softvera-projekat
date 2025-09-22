/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gost;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Gost;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOAddGost extends AbstractSO {

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Gost)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Gost!");
        }

        Gost g = (Gost) ado;

        if (!g.getEmail().contains("@")) {
            throw new Exception("Neispravan format email-a!");
        }
        ArrayList<Gost> gosti = (ArrayList<Gost>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
