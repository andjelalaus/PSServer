/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.rezervacija;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Cartman
 */
public class GetAllRezervacija extends AbstractGenericOperation {

    private List<Rezervacija> rezervacije;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rezervacije = repository.getAll((Rezervacija) param);
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
