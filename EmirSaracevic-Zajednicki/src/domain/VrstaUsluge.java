package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VrstaUsluge extends ApstraktniDomenskiObjekat {

    private int idUsluge;
    private String nazivUsluge;
    private double cena;

    public VrstaUsluge() {}
    public VrstaUsluge(int idUsluge, String nazivUsluge, double cena) {
        this.idUsluge = idUsluge;
        this.nazivUsluge = nazivUsluge;
        this.cena = cena;
    }

    public int getIdUsluge() { return idUsluge; }
    public void setIdUsluge(int idUsluge) { this.idUsluge = idUsluge; }

    public String getNazivUsluge() { return nazivUsluge; }
    public void setNazivUsluge(String nazivUsluge) { this.nazivUsluge = nazivUsluge; }

    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }

    @Override public String toString() { return nazivUsluge; }

    // tabele snake_case, kolone camelCase
    @Override public String tableName() { return "vrsta_usluge"; }
    @Override public String alijas() { return "vu"; }
    @Override public String join() { return ""; }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idUsluge");
            String naziv = rs.getString("nazivUsluge");
            double cena = rs.getDouble("cena");
            lista.add(new VrstaUsluge(id, naziv, cena));
        }
        rs.close();
        return lista;
    }

    @Override public String columnsForInsert() { return "(nazivUsluge,cena)"; }
    @Override public String requirement() { return "idUsluge=" + idUsluge; }
    @Override public String valuesForInsert() { return "'" + nazivUsluge + "'," + cena; }
    @Override public String valuesForUpdate() { return "nazivUsluge='" + nazivUsluge + "', cena=" + cena; }

    @Override @SuppressWarnings("unchecked")
    public String requirementForSelect(Object o) {
        if (o == null) return "";
        HashMap<Integer, String> q = (HashMap<Integer, String>) o;
        int key = q.keySet().iterator().next();
        switch (key) {
            case 1: return "ORDER BY nazivUsluge ASC";
            case 2: return "ORDER BY nazivUsluge DESC";
            case 3: return "WHERE nazivUsluge LIKE '" + q.get(key) + "%'";
            default: return "";
        }
    }
}
