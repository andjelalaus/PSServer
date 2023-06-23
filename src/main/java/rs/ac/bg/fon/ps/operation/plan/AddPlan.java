/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.plan;

import rs.ac.bg.fon.ps.domain.PlanGledanja;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
import rs.ac.bg.fon.ps.validator.components.NumberValidator;
import rs.ac.bg.fon.ps.validator.components.OcenaValidator;

/**
 *
 * @author andelalausevic
 */
public class AddPlan extends AbstractGenericOperation{
   private boolean flag = false;
    private String exceptionOcena;
    private IValidator validator;
    
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
    
    public boolean confirm(){
        return flag;
    }
}
