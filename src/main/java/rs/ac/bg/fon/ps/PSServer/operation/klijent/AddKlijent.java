/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.klijent;

import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;
import rs.ac.bg.fon.ps.PSServer.validator.components.EmailValidator;
import rs.ac.bg.fon.ps.PSServer.validator.components.TextValidator;

/**
 * Konkretna klasa za dodavanje klijenta.
 * Nasleđuje apstraktnu generičku klasu AbstractGenericOperation i implementira njene metode
 * @author andjelalaus
 */
public class AddKlijent extends AbstractGenericOperation {
     /**
     * Flag koji označava da li je dodavanje klijenta uspešno.
     */
    private boolean flag = false;
     /**
     * Validator koji se koristi za validaciju klijenta.
     */
    private IValidator validator;
     /**
     * Poruka o izuzetku vezanom za status klijenta.
     */
    private String exceptionStatus;
     /**
     * Poruka o izuzetku vezanom za ime klijenta.
     */
    private String exceptionIme;
      /**
     * Poruka o izuzetku vezanom za prezime klijenta.
     */
    private String exceptionPrezime;
      /**
     * Poruka o izuzetku vezanom za mejl klijenta.
     */
    private String exceptionMejl;
    /**
     * Opšta poruka o izuzetku.
     */
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
    
    /**
    * Proverava da li je dodavanje klijenta uspešno.
    *
    * @return true ako je dodavanje klijenta uspešno, false ako nije
    */
    public boolean confirm(){
        System.out.println("Dodat klijent");
        return flag;
        
    }
}
