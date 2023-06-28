/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;
import rs.ac.bg.fon.ps.PSServer.validator.components.NumberValidator;

/**
 * 
 * Konkretna klasa za dodavanje karte.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 *
 * @author andjelalaus
 */
public class AddKarta extends AbstractGenericOperation {
     /**
     * Flag koji označava da li je dodavanje karte uspešno.Po defaultu je false.
     */
    private boolean flag = false;
     /**
     * Validator koji se koristi za validaciju karte.
     */
    private IValidator validator;
     /**
     * Poruka o izuzetku vezanom za cenu.
     */
    private String exceptionCena;
     /**
     * Poruka o izuzetku vezanom za stavku rezervacije.
     */
    private String exceptionStavka;
    /**
     * Poruka o izuzetku vezanom za rezervaciju.
     */
    private String exceptionRezerv;
    /**
     * Poruka o izuzetku koja ce sadrzati sve nastale izuzetke.
     */
    private String exception="";

    public AddKarta(){
        
    }
    public AddKarta(Repository repo) {
        this.repository=repo;
    }

   
    
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
         if(karta.getStavkaId()==null){
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
    /**
    * Proverava da li je dodavanje karte uspešno.
    *
    * @return true ako je dodavanje karte uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    } 
}
