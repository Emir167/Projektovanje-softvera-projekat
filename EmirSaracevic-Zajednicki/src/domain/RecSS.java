/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author korisnk
 */
public class RecSS extends ApstraktniDomenskiObjekat{
    
    private int idRecepcioner;
    private int idStrucnaSprema;
    private Date datumSticanja;
    private int id;
    
    public RecSS() {
    }

    public RecSS(int idRecepcioner, int idStrucnaSprema, Date datumSticanja, int id) {
        this.idRecepcioner = idRecepcioner;
        this.idStrucnaSprema = idStrucnaSprema;
        this.datumSticanja = datumSticanja;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public int getIdRecepcioner() {
        return idRecepcioner;
    }

    public void setIdRecepcioner(int idRecepcioner) {
        this.idRecepcioner = idRecepcioner;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    @Override
    public String toString() {
        return "RecSS{" + "idRecepcioner=" + idRecepcioner + ", idStrucnaSprema=" + idStrucnaSprema + ", datumSticanja=" + datumSticanja + '}';
    }

    
    
    public String tableName() {
        return "rec_ss"; 
    }

    @Override
    public String alijas() {
        return "";
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
            int idStrucnaSprema = rs.getInt("idStrucnaSprema");
            Date datumSticanja = rs.getDate("datumSticanja");
            int id = rs.getInt("id");
            RecSS recSS = new RecSS(idRecepcioner, idStrucnaSprema, datumSticanja, id);
            lista.add(recSS);
        }

        rs.close();
        return lista;
        
    }

    @Override
    public String columnsForInsert() {
        return "(idRecepcioner,idStrucnaSprema,datumSticanja)";
    }

    public String requirement() {
        return "idRecepcioner=" + idRecepcioner + " AND idStrucnaSprema=" + idStrucnaSprema;
    }

    @Override
    public String valuesForInsert() {
        return idRecepcioner + "," + idStrucnaSprema + ",'" +
           new java.sql.Date(datumSticanja.getTime()) + "'";
    }

    @Override
    public String valuesForUpdate() {
        return "datumSticanja='" + new java.sql.Date(datumSticanja.getTime()) + "'";
    }

     @Override
    public String requirementForSelect(Object o) {
        if (o == null) return "";
        if (o instanceof Integer) {
            int idRec = (Integer) o;
            return " WHERE idRecepcioner=" + idRec;
        }
        if (o instanceof RecSS) {
            RecSS key = (RecSS) o;
            return " WHERE idRecepcioner=" + key.idRecepcioner +
                   " AND idStrucnaSprema=" + key.idStrucnaSprema;
        }
        return "";
    }
    
    
    
    
}
