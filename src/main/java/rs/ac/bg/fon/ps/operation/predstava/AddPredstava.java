/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.predstava;

import rs.ac.bg.fon.ps.domain.Predstava;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
import rs.ac.bg.fon.ps.validator.components.NumberValidator;
import rs.ac.bg.fon.ps.validator.components.TextValidator;

/**
 *
 * @author andelalausevic
 */
public class AddPredstava extends AbstractGenericOperation{
    private boolean flag = false;
    private IValidator validator;
    private String exceptionNaziv;
    private String exceptionMesto;
    private String exceptionKapacitet;
    private String exception="";
    
    @Override
    protected void preconditions(Object param) throws ValidatorException{
        Predstava predstava = (Predstava)param;
        validator = new TextValidator();
        try {
            validator.validate(predstava.getNaziv());
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionNaziv = ex.getMessage();
            exception = exceptionNaziv + "," + exception;
        }
        try {
            validator.validate(predstava.getMesto());
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionMesto=ex.getMessage();
            exception = exceptionMesto+","+exception;
        }
        validator = new NumberValidator();
try {
            validator.validate(predstava.getKapacitet().toString());
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionKapacitet=ex.getMessage();
            exception = exceptionKapacitet+","+exception;
        }
        
        if(exceptionNaziv!=null || exceptionKapacitet!=null || exceptionMesto!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.add((Predstava)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
