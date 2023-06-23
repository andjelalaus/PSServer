/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.plan;

import java.util.List;
import rs.ac.bg.fon.ps.domain.PlanGledanja;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class GetAllPlan extends AbstractGenericOperation {
    private List<PlanGledanja> stavke;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        stavke = repository.getAll((PlanGledanja) param);
    }

    public List<PlanGledanja> getPlan() {
        return stavke;
    }
}
