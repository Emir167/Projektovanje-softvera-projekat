/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import controller.KlijentController;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author korisnk
 */
public class TableModelStavkeRacuna extends AbstractTableModel {
    
    private final String[] kolone = {"Redni broj", "Kolicina", "Usluga", "Cena", "Iznos"};
    private ArrayList<StavkaRacuna> stavke;
    private int rb;
    
    
     public TableModelStavkeRacuna() {
         stavke = new ArrayList<>();
         rb = 0;
    }

    public TableModelStavkeRacuna(Racun racun) {
        try {
            stavke = KlijentController.getInstance().getAllStavkaRacuna(racun);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkeRacuna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna sr = stavke.get(rowIndex);
        

        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getKolicina();
            case 2:
                return sr.getVrstaUsluge().toString();
            case 3:
                return sr.getCenaUsluge();
            case 4:
                return sr.getIznos();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
    private void reindex() {
        for (int i = 0; i < stavke.size(); i++) {
            stavke.get(i).setRb(i + 1); 
        }
    }

    public void dodajStavku(StavkaRacuna nova) {
        if (nova == null || nova.getVrstaUsluge() == null) return;

        int idUslugeNove = nova.getVrstaUsluge().getIdUsluge();

        // 1) Proveri da li već postoji ta usluga -> spoji (uvećaj količinu i iznos)
        for (int i = 0; i < stavke.size(); i++) {
            StavkaRacuna postojeca = stavke.get(i);
            if (postojeca.getVrstaUsluge() != null &&
                postojeca.getVrstaUsluge().getIdUsluge() == idUslugeNove) {

                int novaKolicina = postojeca.getKolicina() + nova.getKolicina();
                postojeca.setKolicina(novaKolicina);

                double cena = postojeca.getCenaUsluge();   // zadrži postojeću jediničnu cenu
                postojeca.setIznos(novaKolicina * cena);

                // RB ostaje isti jer je to isti red/usluga
                reindex();
                fireTableRowsUpdated(i, i);
                return;
            }
        }

        // 2) Nije nađena – dodaj kao novu stavku i dodeli RB po poziciji
        stavke.add(nova);
        reindex();
        fireTableRowsInserted(stavke.size() - 1, stavke.size() - 1);
    }
    public void obrisiStavku(int row) {
        if (row >= 0 && row < stavke.size()) {
            stavke.remove(row);
            // Reindeksiranje rb
            rb = 0;
            for (StavkaRacuna s : stavke) {
                s.setRb(++rb);
            }
            fireTableDataChanged();
        }
    }

    public double vratiUkupanIznos() {
        double ukupno = 0;
        for (StavkaRacuna s : stavke) {
            ukupno += s.getIznos();
        }
        return ukupno;
    }

    public ArrayList<StavkaRacuna> getStavke() {
        return stavke;
    }

    
    
    public StavkaRacuna getStavka(int row) {
        if (row >= 0 && row < stavke.size()) {
            return stavke.get(row);
        }
        return null;
    }
    
    
}
