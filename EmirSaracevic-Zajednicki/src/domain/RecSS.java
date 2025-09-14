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

    
    
    @Override
    public String tableName() {
        return "recSS";
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

    @Override
    public String requirement() {
        return "id = " + id;
    }

    @Override
    public String valuesForInsert() {
        Calendar c = Calendar.getInstance();
        c.setTime(datumSticanja);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int m = c.get(Calendar.MONTH) + 1;
        int y = c.get(Calendar.YEAR);
        String ds = y + "-" + m + "-" + d;

        return idRecepcioner + "," + idStrucnaSprema + ",'" + ds + "'";    }

    @Override
    public String valuesForUpdate() {
        Calendar c = Calendar.getInstance();
        c.setTime(datumSticanja);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int m = c.get(Calendar.MONTH) + 1;
        int y = c.get(Calendar.YEAR);
        String ds = y + "-" + m + "-" + d;

        return "datumSticanja='" + ds + "'";    }

    @Override
    public String requirementForSelect(Object o) {
        int idRec = (int) o;
        return "WHERE idRecepcioner= " + idRec;
    }
    
    
    
    
}
