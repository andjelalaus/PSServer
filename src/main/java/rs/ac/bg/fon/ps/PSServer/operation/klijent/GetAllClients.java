/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.klijent;

import java.util.List;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 * Konkretna klasa za vracanje svih klijenata.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation i implementia njene metode.
 * 
 * @author andjelalaus
 */
public class GetAllClients extends AbstractGenericOperation {
    /**
     * Lista klijenata koja nije inicijalizovana
     */
    private List<Klijent> klijenti;

    public GetAllClients() {
    }

    
    public GetAllClients(Repository repo) {
        repository=repo;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        klijenti = repository.getAll((Klijent) param);
    }
    /**
     * metoda koja vraca listu klijenta iz baze
     * i ispisuje Poslata lista klijenta
     * @return lista klijenti
     */
     public List<Klijent> getClients() {
         System.out.println("Poslata lista klijenta");
        return klijenti;
         
    }
    
}
