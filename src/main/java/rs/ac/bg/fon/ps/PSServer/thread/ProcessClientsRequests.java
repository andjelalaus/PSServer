/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.PSServer.thread;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.PSCommon.communication.Receiver;
import rs.ac.bg.fon.ps.PSCommon.communication.Request;
import rs.ac.bg.fon.ps.PSCommon.communication.Response;
import rs.ac.bg.fon.ps.PSCommon.communication.Sender;
import rs.ac.bg.fon.ps.PSServer.controller.Controller;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.User;

/**
 * Klasa za procesiranje zahteva klijenata.
 * Nasledjuje klasu nit Thread
 * i implementira njenu metodu run
 * u kojoj sve dok je tacno prima zahtev preko receiver-a i generise response
 * proverava da li zahtev odgovar loginu,getall(reservations/shows/items_reservation/all_predstave/all_tickets/all_plan
 * ili add(predstava/rezervacija/klijent/karta/plan/stavka) ili delete(predstava/karta/rezervacija/stavka/plan)
 * ili update(rezervacija/stavka) ukoliko odgovara setuje rezultat odgovora response i salje controleru sta da radi
 * ukoliko ne izvrsava se exception
 * na kraju se setuje odgovor preko sendera
 * @author andjelalaus
 */
public class ProcessClientsRequests extends Thread {

    /**
     * socket koji predstavlja objekat Socket klase
     * za uspostavljanje veze sa serverskim soketom
     */
    Socket socket;
    /**
     * sender koji predstavlja objekat klase Sender
     * za odgovaranje klijentu
     */
    Sender sender;
    /**
     * receiver koji predstavlja objekat klase Receiver
     * za dobijanje zahteva od klijenta
     */
    Receiver receiver;
    /**
     * user2 objekat klase User predstavlja povezanog korisnika
     */
    User user2;

    /**
     * Konstruktor ProcessClientsRequests gde se inizijalizuje soket dobijen kao parametar od servera
     * inicijalizuje se novi sender i receiver kojima se prosledjuje socket
     * @param socket koji se dobija od Servera-a za uspostavljanje veze
     */
    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {

        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            User user = (User) request.getArgument();
                            response.setResult(Controller.getInstance().login(user));
                            user2=user;
                            break;
                        case GET_ALL_RESERVATIONS:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case GET_ALL_SHOWS:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case GET_ALL_ITEMS_RESERVATIONS:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case GET_ALL_PREDSTAVE:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case GET_ALL_CLIENTS:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case GET_ALL_TICKETS:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case GET_ALL_PLAN:
                            response.setResult(Controller.getInstance().getAll(request.getArgument()));
                            break;
                        case ADD_PREDSTAVA:
                            response.setResult(Controller.getInstance().add(request.getArgument()));
                            break;
                        case ADD_REZERVACIJA:
                            response.setResult(Controller.getInstance().addR(request.getArgument()));
                            break;
                        case ADD_KLIJENT:
                            response.setResult(Controller.getInstance().add(request.getArgument()));
                            break;
                        case ADD_KARTA:
                            response.setResult(Controller.getInstance().add(request.getArgument()));
                            break;
                        case ADD_PLAN:
                            response.setResult(Controller.getInstance().add(request.getArgument()));
                            break;
                        case ADD_STAVKA:
                            response.setResult(Controller.getInstance().add(request.getArgument()));
                            break;
                        
                        
                            
                        case UPDATE_REZERVACIJA:
                            response.setResult(Controller.getInstance().update(request.getArgument()));
                            break;
                        case UPDATE_STAVKA:
                            response.setResult(Controller.getInstance().update(request.getArgument()));
                            break;
                        case DELETE_PREDSTAVA:
                           response.setResult(Controller.getInstance().delete(request.getArgument()));
                           break;
                        case DELETE_KARTA:
                           response.setResult(Controller.getInstance().delete(request.getArgument()));
                           break;
                        case DELETE_REZERVACIJA:
                           response.setResult(Controller.getInstance().delete(request.getArgument()));
                           break;
                        case DELETE_STAVKA:
                           response.setResult(Controller.getInstance().delete(request.getArgument()));
                           break;
                        case DELETE_PLAN:
                           response.setResult(Controller.getInstance().delete(request.getArgument()));
                           break;
                          
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metoda za vracanja instance klase Socket
     * @return socket instanca klase Socket
     */
    public Socket getSocket() {
        return socket;
    }
    /**
     * Metoda za vracanje korisnika
     * 
     * @return user2 koji predstavlja korisnika
     */
    public User getUser() {
        return user2;
    }

}
