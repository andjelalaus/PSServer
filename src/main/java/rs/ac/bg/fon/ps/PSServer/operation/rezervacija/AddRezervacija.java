/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;

import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

import rs.ac.bg.fon.ps.PSServer.validator.components.NumberValidator;

/**
 * Konkretna klasa za dodavanje rezervacija.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 * @author andjelalaus
 */
public class AddRezervacija extends AbstractGenericOperation{
    /**
     * id rezervacije
     */
    private Integer id;
     /**
     * Validator koji se koristi za validaciju rezervacije.
     */
    private IValidator validator;
    /**
     * Poruka o izuzetku vezanom za broj predstavi.
     */
    private String exceptionBrojPredstava;
    /**
     * Poruka o izuzetku vezanom za klijenta.
     */
    private String exceptionKlijent;
    /**
     * Poruka o svim izuzecima.
     */
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
    /**
    * Vraca id rezervacije ako je dodavanje rezervacije uspešno.
    *
    * @return integer ako je dodavanje rezervacije uspesno
    */
    public Integer confirm(){
        return id;
    }
}
