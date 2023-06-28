/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;
import rs.ac.bg.fon.ps.PSServer.validator.components.NumberValidator;

/**
 *
 * Konkretna klasa za brisanje karte.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation i implementira sve njene metode.
 * 
 *
 * @author andelalausevic
 */
public class DeleteKarta extends AbstractGenericOperation {
     /**
     * Flag koji označava da li je brisanje karte uspešno.
     */
    private boolean flag = false;
     /**
     * Poruka o izuzetku vezanom za cenu.
     */
    private String exceptionCena;
     /**
     * Poruka o izuzetku koji ce sadrzati sve izuzetke vezane za kartu.
     */
    private String exception="";
    /**
     * Validator koji se koristi za validaciju karte.
     */
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
    
    /**
    * Proverava da li je brisanje karte uspešno.
    *
    * @return true ako je brisanje karte uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    }
    
}
