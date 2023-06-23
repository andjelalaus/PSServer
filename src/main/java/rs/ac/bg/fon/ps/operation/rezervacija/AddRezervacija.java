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
public class AddRezervacija extends AbstractGenericOperation{
    private Integer id;
    private IValidator validator;
    private String exceptionBrojPredstava;
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
            exceptionBrojPredstava = ex.getMessage();
            exception = exceptionBrojPredstava + "," + exception;
        }
        
        
        if(rz.getKlijentId()==null){
            exceptionKlijent="Morate izabrati klijenta!";
            exception = exceptionKlijent + "," + exception;
        }
        else{
            exception = " " + "," + exception;
        }
        
        if(exceptionBrojPredstava!=null || exceptionKlijent!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
     id = repository.addReturnKey((Rezervacija)param);
    }
    
    public Integer confirm(){
        return id;
    }
}
