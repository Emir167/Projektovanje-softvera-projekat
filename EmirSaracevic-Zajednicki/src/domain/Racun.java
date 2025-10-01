/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author korisnk
 */
public class Racun extends ApstraktniDomenskiObjekat{
    
    private int idRacuna;
    private Date datumIzdavanja;
    private double ukupniIznos;
    private Recepcioner recepcioner;
    private Gost gost;
    private ArrayList<StavkaRacuna> stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(int idRacuna, Date datumIzdavanja, double ukupniIznos, Recepcioner recepcioner, Gost gost, ArrayList<StavkaRacuna> stavke) {
        this.idRacuna = idRacuna;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupniIznos = ukupniIznos;
        this.recepcioner = recepcioner;
        this.gost = gost;
        this.stavke = stavke;
    }
    
    

    public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public double getUkupniIznos() {
        return ukupniIznos;
    }

    public void setUkupniIznos(double ukupniIznos) {
        this.ukupniIznos = ukupniIznos;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    public Recepcioner getRecepcioner() {
        return recepcioner;
    }

    public void setRecepcioner(Recepcioner recepcioner) {
        this.recepcioner = recepcioner;
    }

    public ArrayList<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }
    
    

    @Override
    public String tableName() {
        return "racun";
    }

    @Override
    public String alijas() {
        return "rac";
    }

    @Override
    public String join() {
         return " JOIN recepcioner r ON rac.recepcioner = r.idRecepcioner " +
           " JOIN gost g ON rac.gost = g.idGost " +
           " JOIN drzavljanstvo d ON g.drzavljanstvo = d.idDrzavljanstvo ";

    }

   @Override
public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
    ArrayList<ApstraktniDomenskiObjekat> list = new ArrayList<>();
    while (rs.next()) {
        Recepcioner r = new Recepcioner(
            rs.getInt("r.idRecepcioner"),
            rs.getString("r.ime"),
            rs.getString("r.prezime"),
            rs.getString("r.korisnickoIme"),
            rs.getString("r.email"),
            rs.getString("r.sifra")
        );

        Drzavljanstvo d = new Drzavljanstvo(
        rs.getInt("d.idDrzavljanstvo"),
        rs.getString("d.drzava")
        );

        Gost g = new Gost(
            rs.getInt("g.idGost"),
            rs.getString("g.ime"),
            rs.getString("g.prezime"),
            rs.getString("g.email"),
            d
        );

        Racun rac = new Racun();
        rac.setIdRacuna(rs.getInt("rac.idRacuna"));
        rac.setDatumIzdavanja(rs.getDate("rac.datumIzdavanja"));
        rac.setUkupniIznos(rs.getDouble("rac.ukupniIznos"));
        rac.setRecepcioner(r);
        rac.setGost(g);
        list.add(rac);
    }
    rs.close();
    return list;
}

    @Override
    public String valuesForInsert() {
    return "'" + new java.sql.Date(datumIzdavanja.getTime()) + "', "
         + ukupniIznos + ", "
         + recepcioner.getIdRecepcioner() + ", "
         + gost.getIdGost();
    }



    @Override
    public String valuesForUpdate() {
    return " datumIzdavanja = '" + new java.sql.Date(datumIzdavanja.getTime()) + "', "
         + "ukupniIznos = " + ukupniIznos + ", "
         + "recepcioner = " + recepcioner.getIdRecepcioner() + ", "
         + "gost = " + gost.getIdGost();
    }

     @Override
    public String columnsForInsert() {
            return "(datumIzdavanja, ukupniIznos, recepcioner, gost)";    
    }

    
    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Racun)) return "";
        Racun r = (Racun) o;

        if (r.getIdRacuna() > 0) {
            return " WHERE rac.idRacuna = " + r.getIdRacuna();
        }
        if (r.getGost() != null && r.getGost().getIdGost() > 0) {
            return " WHERE rac.gost = " + r.getGost().getIdGost();
        }
        return "";
    }

   
    @Override
    public String requirement() {
        return "idRacuna=" + idRacuna;
    }
    
}