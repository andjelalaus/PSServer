/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.plan;

import rs.ac.bg.fon.ps.PSCommon.domain.PlanGledanja;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;
import rs.ac.bg.fon.ps.PSServer.validator.components.NumberValidator;
import rs.ac.bg.fon.ps.PSServer.validator.components.OcenaValidator;

/**
 * Konkretna klasa za dodavanje plana gledanja.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i ima svoju metodu potvrdu izvrsenja transakcije.
 *
 * @author andelalausevic
 */
public class AddPlan extends AbstractGenericOperation{
     /**
     * Flag koji označava da li je dodavanje plana uspešno.Po defaultu je false.
     */
   private boolean flag = false;
    /**
     * Poruka o izuzetku vezanom za ocenu.
     */
    private String exceptionOcena;
     /**
     * Validator koji se koristi za validaciju plana.
     */
    private IValidator validator;
/**
 * Bezparametarski konstruktor date klase
 */
    public AddPlan() {
    }
    /**
     * Parametarski konstruktor sa parametrom repository
     * @param repo predstavlja objekat klase Repository
     */
     public AddPlan(Repository repo) {
         repository=repo;
    }
    @Override
    protected void preconditions(Object param) throws ValidatorException {
        PlanGledanja pt = (PlanGledanja)param;
        validator = new OcenaValidator();
        try {
            validator.validate(Integer.toString(pt.getOcena()));
        } catch (ValidatorException ex) {
            exceptionOcena = ex.getMessage();
        }
        
        if(exceptionOcena!=null){
            throw new ValidatorException(exceptionOcena);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.add((PlanGledanja)param);
    }
     /**
    * Proverava da li je dodavanje plana uspešno.
    *
    * @return true ako je dodavanje plana uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    }
}
