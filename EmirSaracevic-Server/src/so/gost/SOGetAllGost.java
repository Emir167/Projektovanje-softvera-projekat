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
public class SOGetAllGost extends AbstractSO{

    private ArrayList<Gost> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Gost)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Gost!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> gosti = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Gost>) (ArrayList<?>) gosti;
    }

    public ArrayList<Gost> getLista() {
        return lista;
    }
    
}
