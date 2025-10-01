package so.racun;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import so.AbstractSO;

public class SOUpdateRacun extends AbstractSO {

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;
        if (r.getIdRacuna() <= 0) {
            throw new Exception("Racun mora imati vazeci ID za izmenu.");
        }
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku.");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        Racun r = (Racun) ado;

       
        DBBroker.getInstance().update(r);

        Connection conn = DBBroker.getInstance().getConnection();

        try (PreparedStatement del = conn.prepareStatement(
                "DELETE FROM stavka_racuna WHERE idRacuna = ?")) {
            del.setInt(1, r.getIdRacuna());
            del.executeUpdate();
        }

        int rb = 1;
        for (StavkaRacuna s : r.getStavke()) {
            s.setIdRacuna(r.getIdRacuna());
            s.setRb(rb++);
            DBBroker.getInstance().insert(s);
        }
    }
}
