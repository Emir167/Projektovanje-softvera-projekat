/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.drzavljanstvo;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Drzavljanstvo;
import domain.Recepcioner;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOGetAllDrzavljanstvo extends AbstractSO {

    private ArrayList<Recepcioner> lista;
    
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
             if (!(ado instanceof Drzavljanstvo)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Recepcioner!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> drzavljanstva = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Recepcioner>) (ArrayList<?>) drzavljanstva;
    }

    public ArrayList<Recepcioner> getLista() {
        return lista;
    }
    
    
    
}
