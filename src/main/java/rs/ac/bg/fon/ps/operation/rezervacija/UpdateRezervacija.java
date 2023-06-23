/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.rezervacija;

import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
import rs.ac.bg.fon.ps.validator.components.NumberValidator;

/**
 *
 * @author andelalausevic
 */
public class UpdateRezervacija extends AbstractGenericOperation{
     private boolean flag = false;
    private IValidator validator;
    private String exceptionBrojSedista;
    private String exceptionKlijent;
    private String exception="";
    
    @Override
    protected void preconditions(Object param) throws ValidatorException{
  Rezervacija rz = (Rezervacija)param;
        validator = new NumberValidator();
        try {
            validator.validate(rz.getBrojPredstave().toString());
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionBrojSedista = ex.getMessage();
            exception = exceptionBrojSedista + "," + exception;
        }
        
        
        if(rz.getKlijentId()==null){
            exceptionKlijent="Morate izabrati klijenta!";
            exception = exceptionKlijent + "," + exception;
        }
        else{
            exception = " " + "," + exception;
        }
        
        if(exceptionBrojSedista!=null || exceptionKlijent!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.edit((Rezervacija)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
