/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;

import java.util.List;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;

/**
 * Konkretna klasa za varacnje svih rezervacija.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 * @author andjelalaus
 */
public class GetAllRezervacija extends AbstractGenericOperation {

    /**
     * Lista svih rezervacija imena rezervacije, nije inicijalizovana.
     */
    private List<Rezervacija> rezervacije;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rezervacije = repository.getAll((Rezervacija) param);
    }
/**
 * Metoda koja vraca listu rezervacija.
 * @return rezervacije koja predstavlja listu rezervacija
 */
    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
