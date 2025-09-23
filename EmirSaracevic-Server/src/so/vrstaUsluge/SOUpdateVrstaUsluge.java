package so.vrstaUsluge;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.VrstaUsluge;
import so.AbstractSO;

public class SOUpdateVrstaUsluge extends AbstractSO {

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof VrstaUsluge)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaUsluge!");
        }
        VrstaUsluge vu = (VrstaUsluge) ado;
        if (vu.getIdUsluge() <= 0) {
            throw new Exception("ID usluge mora biti zadat za izmenu.");
        }
        if (vu.getNazivUsluge() == null || vu.getNazivUsluge().trim().isEmpty()) {
            throw new Exception("Naziv usluge je obavezan.");
        }
        if (vu.getCena() < 0) {
            throw new Exception("Cena usluge ne moze biti negativna.");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
