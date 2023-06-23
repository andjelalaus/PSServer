/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.karte;

import rs.ac.bg.fon.ps.domain.Karta;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
import rs.ac.bg.fon.ps.validator.components.NumberValidator;

/**
 *
 * @author andelalausevic
 */
public class DeleteKarta extends AbstractGenericOperation {
     private boolean flag = false;
    private String exceptionCena;
    private String exception="";
    private IValidator validator;
    
    @Override
    protected void preconditions(Object param) throws ValidatorException {
        Karta k = (Karta)param;
        
        validator = new NumberValidator();
        try {
            validator.validate(Integer.toString(k.getCena()));
        } catch (ValidatorException ex) {
            exceptionCena = ex.getMessage();
            exception = exceptionCena + "," + exception;
        }
        
        
        
        if(exceptionCena!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.delete((Karta)param);
    }
    
    public boolean confirm(){
        return flag;
    }
    
}
