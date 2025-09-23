package so.recepcioner;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Recepcioner;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllRecepcioner extends AbstractSO {

    private ArrayList<Recepcioner> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Recepcioner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Recepcioner!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> recs = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Recepcioner>) (ArrayList<?>) recs;
    }

    public ArrayList<Recepcioner> getLista() {
        return lista;
    }
}
