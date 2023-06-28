/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.PSServer.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.User;
import rs.ac.bg.fon.ps.PSServer.thread.ProcessClientsRequests;

/**
 * Klasa koja predstavlja server nasledjuje klasu nit Thread
 * Implementira njenu run metodu u kojoj uspostavlja konekciju sve dok se server ne zatvori i u kojoj poziva handleClient metodu
 * kada se server zatvori poziva se metoda cancelClients
 * @author andjelalaus
 */
public class Server extends Thread {

    /**
     * parametar serverSocket koji predstavlja objekat klase ServerSocket za uspostavljanja porta na kom ce se podici server
     */
    private ServerSocket serverSocket;
    /**
     * lista klijenata koji su se povezali na server preko klijentske aplikacije
     */
    List<ProcessClientsRequests> activeClients;

    /**
     * Konstruktor servera.
     * inicijalizuje serverski soket na portu 9000
     * inicijalizuje listu aktivnih korisnika
     * @throws IOException ukoliko dodje do greske prilikom pokretanja servera
     */
    public Server() throws IOException {
        serverSocket = new ServerSocket(9000);
        activeClients = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cancelClients();
    }

    /**
     * Metoda handleClient koja pravi novi objekat klase ProcessClientsRequests i prosledjuje mu socket
     * dodaje u listu aktivnih klijenata novonapravljeni objekat
     * i pokrece nit processClientsRequests
     * @param socket koji predstavlja socket
     * @throws Exception  baca ukoliko dodje do greske prilikom startovanja niti
     */
    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        activeClients.add(processClientsRequests);
        processClientsRequests.start();
    }
/**
 * metoda za vracanje instance serversocket-a
 * @return serverSocket koji predstavlja vec inicijalizovan parametar
 */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Metoda koja vraca listu aktivnih korisnika koji su uspostavliki konekciju sa serverom
     * Prolazi se kroz listu zahteva korisnika i ubacuje u listu korisnika
     * @return users koja predstavlja listu aktivnih korisnika
     */
    public List<User> getActiveUsers() {
        List<User> users = new ArrayList<>();
        for (ProcessClientsRequests c : activeClients) {
            users.add(c.getUser());
        }

        return users;
    }
/**
 * Metoda koja zatvara sokete svih aktivnih klijenata
 * Prolazi se kroz sve zahteve korisnika i zatvara im se soket i izbacuju su iz list aktivnih korisnika
 * 
 */
    private void cancelClients() {
        //TODO.. ekstra
        for (ProcessClientsRequests k : activeClients) {
            try {
                k.getSocket().close();
                activeClients.remove(k);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
