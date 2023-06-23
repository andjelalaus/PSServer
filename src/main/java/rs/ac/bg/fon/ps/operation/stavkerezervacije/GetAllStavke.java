/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.stavkerezervacije;

import java.util.List;
import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class GetAllStavke extends AbstractGenericOperation {
    private List<StavkaRezervacije> stavke;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        stavke = repository.getAll((StavkaRezervacije) param);
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }
}
