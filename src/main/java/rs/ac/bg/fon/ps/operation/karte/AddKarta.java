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
public class AddKarta extends AbstractGenericOperation {
    private boolean flag = false;
    private IValidator validator;
    private String exceptionCena;
    private String exceptionStavka;
    private String exceptionRezerv;
    private String exception="";
    
    @Override
    protected void preconditions(Object param) throws ValidatorException{
        Karta karta = (Karta)param;
        validator = new NumberValidator();
        try {
            validator.validate(Integer.toString(karta.getCena()));
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionCena = ex.getMessage();
            exception = exceptionCena + "\r" + exception;
        }
        
        if(karta.getRezervacijaId()==null){
            exceptionRezerv="Morate izabrati rezervaciju!";
            exception = exceptionRezerv + "\r" + exception;
        }
        else{
            exception = " " + "," + exception;
        }
         if(karta.getRezervacijaId()==null){
            exceptionStavka="Morate izabrati predstavu i klijenta!";
            exception = exceptionStavka + "\r" + exception;
        }
        else{
            exception = " " + "\r" + exception;
        }
        
        if(exceptionCena!=null || exceptionStavka!=null || exceptionRezerv!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.add((Karta)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
