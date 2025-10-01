package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StavkaRacuna extends ApstraktniDomenskiObjekat {

    private int idRacuna;
    private int rb;
    private VrstaUsluge vrstaUsluge; 
    private double cenaUsluge;
    private int kolicina;
    private double iznos;

    public StavkaRacuna() {}

    public StavkaRacuna(int idRacuna, int rb, VrstaUsluge vrstaUsluge,
                        double cenaUsluge, int kolicina, double iznos) {
        this.idRacuna = idRacuna;
        this.rb = rb;
        this.vrstaUsluge = vrstaUsluge;
        this.cenaUsluge = cenaUsluge;
        this.kolicina = kolicina;
        this.iznos = iznos;
    }

    public int getIdRacuna() { return idRacuna; }
    public void setIdRacuna(int idRacuna) { this.idRacuna = idRacuna; }

    public int getRb() { return rb; }
    public void setRb(int rb) { this.rb = rb; }

    public VrstaUsluge getVrstaUsluge() { return vrstaUsluge; }
    public void setVrstaUsluge(VrstaUsluge vrstaUsluge) { this.vrstaUsluge = vrstaUsluge; }

    public double getCenaUsluge() { return cenaUsluge; }
    public void setCenaUsluge(double cenaUsluge) { this.cenaUsluge = cenaUsluge; }

    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    public double getIznos() { return iznos; }
    public void setIznos(double iznos) { this.iznos = iznos; }

    @Override
    public String toString() {
        return "StavkaRacuna{idRacuna=" + idRacuna + ", rb=" + rb +
               ", vrsta=" + (vrstaUsluge != null ? vrstaUsluge.getNazivUsluge() : "") +
               ", cena=" + cenaUsluge + ", kolicina=" + kolicina +
               ", iznos=" + iznos + "}";
    }

    @Override
    public String tableName() { return "stavka_racuna"; }

    @Override
    public String alijas() { return "sr"; }

    @Override
    public String join() {
        return "JOIN racun r ON r.idRacuna = sr.idRacuna "
             + "JOIN vrsta_usluge vu ON vu.idUsluge = sr.idUsluge ";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int vuId       = rs.getInt("vu.idUsluge");
            String vuNaziv = rs.getString("vu.nazivUsluge");
            double vuCena  = rs.getDouble("vu.cena");
            VrstaUsluge vu = new VrstaUsluge(vuId, vuNaziv, vuCena);

            int idR       = rs.getInt("sr.idRacuna");
            int rbVal     = rs.getInt("sr.rb");
            double cenaU  = rs.getDouble("sr.cenaUsluge");
            int kol       = rs.getInt("sr.kolicina");
            double izn    = rs.getDouble("sr.iznos");

            lista.add(new StavkaRacuna(idR, rbVal, vu, cenaU, kol, izn));
        }
        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(idRacuna, rb, idUsluge, cenaUsluge, kolicina, iznos)";
    }

    @Override
    public String requirement() {
        return "idRacuna=" + idRacuna + " AND rb=" + rb;
    }

    @Override
    public String valuesForInsert() {
        return idRacuna + "," + rb + "," + vrstaUsluge.getIdUsluge() + ","
             + cenaUsluge + "," + kolicina + "," + iznos;
    }

    @Override
    public String valuesForUpdate() {
        return "idUsluge=" + vrstaUsluge.getIdUsluge()
             + ", cenaUsluge=" + cenaUsluge
             + ", kolicina=" + kolicina
             + ", iznos=" + iznos;
    }

    @Override
    public String requirementForSelect(Object o) {
    
        Integer racunId = null;

         if (o instanceof Integer) {
             racunId = (Integer) o;
         } else if (o instanceof Racun) {
             racunId = ((Racun) o).getIdRacuna();
         } else if (o instanceof StavkaRacuna) {
             racunId = ((StavkaRacuna) o).getIdRacuna();
         }

         if (racunId != null && racunId > 0) {
             return " WHERE sr.idRacuna = " + racunId;
         }
         return "";
         }
}
