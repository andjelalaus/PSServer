/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.plan;

import java.util.List;
import rs.ac.bg.fon.ps.domain.PlanGledanja;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 * Klasa koja vraca sve planove gledanja koji se nalaze u bazi
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i ima svoju metodu vracanja liste planova.
 * @author andelalausevic
 */
public class GetAllPlan extends AbstractGenericOperation {
    /**
     * Lista planova gledanja koja nije inicijalizovana.
     */
    private List<PlanGledanja> stavke;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        stavke = repository.getAll((PlanGledanja) param);
    }

    /**
     * Metoda koja vraca listu plana gledanja
     * @return  stavke koje predstavljaju listu plana gledanja
     */
    public List<PlanGledanja> getPlan() {
        return stavke;
    }
}
