/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Recepcioner;
import java.util.ArrayList;

/**
 *
 * @author korisnk
 */
public class ServerController {
    
    private static ServerController instance;
    private ArrayList<Recepcioner> ulogovaniRecepcioneri = new ArrayList<>();

    
    private ServerController() {
         
    }
     
    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    } 

    public ArrayList<Recepcioner> getUlogovaniRecepcioneri() {
        return ulogovaniRecepcioneri;
    }

    public void setUlogovaniRecepcioneri(ArrayList<Recepcioner> ulogovaniRecepcioneri) {
        this.ulogovaniRecepcioneri = ulogovaniRecepcioneri;
    }
    
    
     
}
