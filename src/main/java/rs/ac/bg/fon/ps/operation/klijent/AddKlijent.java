/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.klijent;

import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
import rs.ac.bg.fon.ps.validator.components.EmailValidator;
import rs.ac.bg.fon.ps.validator.components.TextValidator;

/**
 *
 * @author andelalausevic
 */
public class AddKlijent extends AbstractGenericOperation {
    private boolean flag = false;
    private IValidator validator;
    private String exceptionStatus;
    private String exceptionIme;
    private String exceptionPrezime;
    private String exceptionMejl;
    private String exception="";
    
    protected void preconditions(Object param) throws ValidatorException{
        Klijent klijent = (Klijent)param;
        validator = new EmailValidator();
     try {
            validator.validate(klijent.getEmail());
            exception = " " + " " + exception;
        } catch (ValidatorException ex) {
            exceptionMejl = ex.getMessage();
            exception = exceptionMejl+ "\n " + exception;
        }
        validator = new TextValidator();
           try {
            validator.validate(klijent.getIme());
            exception = " " + " " + exception;
        } catch (ValidatorException ex) {
            exceptionIme = ex.getMessage();
            exception = exceptionIme+ "\n " + exception;
        }
        try {
            validator.validate(klijent.getPrezime());
            exception = " " + " " + exception;
        } catch (ValidatorException ex) {
            exceptionPrezime = ex.getMessage();
            exception = exceptionPrezime + "\n" + exception;
        }
        try {
            validator.validate(klijent.getStatus());
            exception = " " + " " + exception;
        } catch (ValidatorException ex) {
            exceptionStatus = ex.getMessage();
            exception = exceptionStatus + "\n " + exception;
        }
        
        if(exceptionMejl!=null || exceptionIme!=null || exceptionPrezime!=null || exceptionStatus!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.add((Klijent)param);
    }
    
    
    public boolean confirm(){
        System.out.println("Dodat klijent");
        return flag;
        
    }
}
