/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author korisnk
 */
public class KlijentController {
    
    private static KlijentController instance;

    private KlijentController() {
    }

    public static KlijentController getInstance() {
        if (instance == null) {
            instance = new KlijentController();
        }
        return instance;
    }
    
    
    
    
    
}
