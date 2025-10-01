/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Gost;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author korisnk
 */
public class TableModelGost extends AbstractTableModel {
    
    String[] kolone = {"Ime","Prezime","Email","Drzavljanstvo"};
    List<Gost> gosti = new ArrayList();

    @Override
    public int getRowCount() {
        return gosti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Gost g = gosti.get(rowIndex);
        switch (columnIndex) {
            case 0: return g.getIme();
            case 1: return g.getPrezime();
            case 2: return g.getEmail();
            case 3: return g.getDrzavljanstvo().getDrzava();
            default: return "";
        }
    }
    
    
      public void setData(List<Gost> lista) {
        gosti.clear();
        if (lista != null) gosti.addAll(lista);
        fireTableDataChanged();
    }

    public Gost getGostAt(int row) {
        if (row < 0 || row >= gosti.size()) return null;
        return gosti.get(row);
    }

    public void removeRow(int row) {
        if (row < 0 || row >= gosti.size()) return;
        gosti.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, Gost izmenjen) {
        if (row < 0 || row >= gosti.size()) return;
        gosti.set(row, izmenjen);
        fireTableRowsUpdated(row, row);
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
}
