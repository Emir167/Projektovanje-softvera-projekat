package so.vrstaUsluge;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.VrstaUsluge;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

public class SOGetAllVrstaUsluge extends AbstractSO {

    private ArrayList<VrstaUsluge> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof VrstaUsluge)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaUsluge!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> usluge = DBBroker.getInstance().select(ado);
        lista = (ArrayList<VrstaUsluge>) (ArrayList<?>) usluge;
    }

    public List<VrstaUsluge> getLista() {
        return lista;
    }
}
