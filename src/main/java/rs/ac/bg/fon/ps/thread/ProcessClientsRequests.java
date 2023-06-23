/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Predstava;
import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Cartman
 */
public class ProcessClientsRequests extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    User user2;

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

    
    public Socket getSocket() {
        return socket;
    }
    public User getUser() {
        return user2;
    }

}
