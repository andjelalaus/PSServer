/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.stavkerezervacije;

import rs.ac.bg.fon.ps.operation.rezervacija.*;
import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;

import rs.ac.bg.fon.ps.validator.components.NumberValidator;
import rs.ac.bg.fon.ps.validator.components.PopustValidator;

/**
 *
 * @author andelalausevic
 */
public class AddStavka extends AbstractGenericOperation{
    private boolean flag = false;
    private IValidator validator;
    private String exceptionBrojSedista;
    private String exceptionRezervacija;
    private String exceptionPredstava;
    private String exceptionPopust;
    private String exception="";
    
    @Override
    protected void preconditions(Object param) throws ValidatorException{
        StavkaRezervacije rz = (StavkaRezervacije)param;
        validator = new NumberValidator();
        try {
            validator.validate(Integer.toString(rz.getBrojSedista()));
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionBrojSedista = ex.getMessage();
            exception = exceptionBrojSedista + "," + exception;
        }
        validator = new PopustValidator();
        try {
            validator.validate(Integer.toString(rz.getPopust()));
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionPopust = ex.getMessage();
            exception = exceptionPopust + "," + exception;
        }
        
        
        if(rz.getRezervacijaId()==null){
            exceptionRezervacija="Morate izabrati rezervaciju!";
            exception = exceptionRezervacija + "," + exception;
        }
        else{
            exception = " " + "," + exception;
        }
        if(rz.getPredstavaId()==null){
            exceptionPredstava="Morate izabrati predstavu!";
            exception = exceptionPredstava + "," + exception;
        }
        else{
            exception = " " + "," + exception;
        }
        
        if(exceptionBrojSedista!=null || exceptionRezervacija!=null || exceptionPredstava!=null || exceptionPopust!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.add((StavkaRezervacije)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
