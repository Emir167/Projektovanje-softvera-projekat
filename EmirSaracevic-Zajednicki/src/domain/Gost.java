/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author korisnk
 */
public class Gost extends ApstraktniDomenskiObjekat{
    
    private int idGost;
    private String ime;
    private String prezime;
    private String email;
    private Drzavljanstvo drzavljanstvo;

    public Gost() {
    }

    public Gost(int idGost, String ime, String prezime, String email, Drzavljanstvo drzavljanstvo) {
        this.idGost = idGost;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.drzavljanstvo = drzavljanstvo;
    }

    public int getIdGost() {
        return idGost;
    }

    public void setIdGost(int idGost) {
        this.idGost = idGost;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Drzavljanstvo getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(Drzavljanstvo drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }
    
   

    @Override
    public String tableName() {
        return "gost";
    }

    @Override
    public String alijas() {
        return "g";
    }

    @Override
    public String join() {
        return "JOIN drzavljanstvo d ON g.drzavljanstvo = d.idDrzavljanstvo";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
            ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

            while (rs.next()) {
                int idGost = rs.getInt("g.idGost");
                String ime = rs.getString("g.ime");
                String prezime = rs.getString("g.prezime");
                String email = rs.getString("g.email");

                int idDrzavljanstvo = rs.getInt("d.idDrzavljanstvo");
                String drzava = rs.getString("d.drzava");
                Drzavljanstvo d = new Drzavljanstvo(idDrzavljanstvo, drzava);
                
                Gost g = new Gost(idGost, ime, prezime, email, drzavljanstvo);
                lista.add(g);

            }

        rs.close();
        return lista;    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String columnsForInsert() {
        return "(ime,prezime,email, drzavljanstvo)";
    }

    @Override
    public String requirement() {
        return "idGost=" + idGost;
    }

    @Override
    public String valuesForInsert() {
        return "'"+ ime + "','" + prezime + "','" +  email + "'," + drzavljanstvo.getIdDrzavljanstvo();
    }

    @Override
    public String valuesForUpdate() {
        return "ime='" + ime + "',prezime='" + prezime + "',email='" + email + "',idDrzavljanstvo=" + drzavljanstvo.getIdDrzavljanstvo() + "'";
    }

    @Override
    public String requirementForSelect(Object o) {
         if (o == null) return "";

    if (o instanceof Gost) {
        Gost g = (Gost) o;

        if (g.getIdGost() > 0) {
            return " WHERE g.idGost = " + g.getIdGost();
        }

        // prioritet: ime -> prezime -> email (uzmi prvo što nije prazno)
        if (g.getIme() != null && !g.getIme().isBlank()) {
            String ime = g.getIme().replace("'", "''");
            return " WHERE g.ime LIKE '" + ime + "%'";
        }
        if (g.getPrezime() != null && !g.getPrezime().isBlank()) {
            String prezime = g.getPrezime().replace("'", "''");
            return " WHERE g.prezime LIKE '" + prezime + "%'";
        }
        if (g.getEmail() != null && !g.getEmail().isBlank()) {
            String email = g.getEmail().replace("'", "''");
            return " WHERE g.email LIKE '" + email + "%'";
        }
    }
        return "";
    }    



}
    
    
    
    

