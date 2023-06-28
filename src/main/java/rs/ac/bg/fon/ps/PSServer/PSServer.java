/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer;

import rs.ac.bg.fon.ps.PSServer.form.FrmMain;

/**
 * Klasa koja sadrzi main metodu za pokretanje glavne severske forme i cele serverske aplikacije
 * @author andjelalaus
 */
public class PSServer {
    /**
    * Main metoda: Pokreće glavnu formu aplikacije.
    *
    * @param args argument komandne linije (nije korišćeno)
    */
    public static void main(String[] args) {
        new FrmMain().setVisible(true);
    }
}
