/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.thread.ProcessClientsRequests;

/**
 *
 * @author Cartman
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    List<ProcessClientsRequests> activeClients;

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

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        activeClients.add(processClientsRequests);
        processClientsRequests.start();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public List<User> getActiveUsers() {
        List<User> users = new ArrayList<>();
        for (ProcessClientsRequests c : activeClients) {
            users.add(c.getUser());
        }

        return users;
    }

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
