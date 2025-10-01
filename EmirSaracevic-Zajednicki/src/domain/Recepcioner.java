/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author korisnk
 */
public class Recepcioner extends ApstraktniDomenskiObjekat implements Serializable{
    
    
    private int idRecepcioner;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email;
    private String sifra;

    public Recepcioner() {
    }

    public Recepcioner(int idRecepcioner, String ime, String prezime, String korisnickoIme, String email, String sifra) {
        this.idRecepcioner = idRecepcioner;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.sifra = sifra;
    }

    public int getIdRecepcioner() {
        return idRecepcioner;
    }

    public void setIdRecepcioner(int idRecepcioner) {
        this.idRecepcioner = idRecepcioner;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
    }
    
    @Override
    public String tableName() {
        return "recepcioner";
    }

    @Override
    public String alijas() {
        return "r";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
             ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
                int idRecepcioner = rs.getInt("idRecepcioner");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String email = rs.getString("email");
                String sifra = rs.getString("sifra");
                Recepcioner r = new Recepcioner(idRecepcioner, ime, prezime, korisnickoIme, email, sifra);
                lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(ime,prezime,korisnickoIme,email,sifra)";
    }

    @Override
    public String valuesForInsert() {
    return "'" + ime + "','" + prezime + "','" + korisnickoIme + "','" + email + "','" + sifra + "'";
    }

@Override
    public String requirement() {
    return "idRecepcioner=" + idRecepcioner; 
    }      

    @Override
    public String valuesForUpdate() {
        return "ime='" + ime +"',prezime ='" + prezime + "',korisnickoIme='" + korisnickoIme + "',email='" + email + "',sifra='" + sifra + "'";
    }

    @Override
    public String requirementForSelect(Object o) {
        return "";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recepcioner)) return false;
        Recepcioner that = (Recepcioner) o;
        return this.getIdRecepcioner() == that.getIdRecepcioner();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getIdRecepcioner());
    }
}
