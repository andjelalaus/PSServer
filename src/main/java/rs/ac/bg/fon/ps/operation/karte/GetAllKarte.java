/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.karte;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Karta;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class GetAllKarte extends AbstractGenericOperation {
        private List<Karta> karte;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        karte = repository.getAll((Karta) param);
    }

    public List<Karta> getKarte() {
        return karte;
    }
}
