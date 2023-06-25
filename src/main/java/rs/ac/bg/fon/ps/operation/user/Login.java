/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.user;

import java.util.List;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
import rs.ac.bg.fon.ps.validator.components.OpstiValidator;

/**
 * Konkretna klasa za prijavu korisnika.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 * @author andjelalaus
 */
public class Login extends AbstractGenericOperation{
     /**
     * Validator koji se koristi za validaciju login-a.
     */
     private IValidator validator;
       /**
     * Poruka o svim izuzecima.
     */
    private String exception = "";
     /**
     * Poruka o izuzetku vezanom za username.
     */
    private String exceptionUsername;
     /**
     * Poruka o izuzetku vezanom za password.
     */
    private String exceptionPassword;
     /**
     * Objekat login kojem se dodeljuje user
     */
    private Object login;

    @Override
    protected void preconditions(Object param) throws ValidatorException{
        User user = (User)param;
        validator = new OpstiValidator();
        try {
            validator.validate(user.getUsername());
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionUsername = ex.getMessage();
            exception = exceptionUsername + "," + exception;
        }
        try {
            validator.validate(user.getPassword());
            exception = " " + "," + exception;
        } catch (ValidatorException ex) {
            exceptionPassword = ex.getMessage();
            exception = exceptionPassword + "," + exception;
        }
        
        if(exceptionUsername!=null || exceptionPassword!=null){
            throw new ValidatorException(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        System.out.println("LOGIN");
        User user = (User)param;
        if(user.getUsername().equals("admin") && user.getPassword().equals("admin")){
            login = user;
            return;
        }

    }
    /**
     * Metoda koja vraca korisnika koji je ulogovan.
     * @return login koji predstavlja korisnika koji je ulogovan.
     */
    public Object getLogin(){
        return login;
    }
}
