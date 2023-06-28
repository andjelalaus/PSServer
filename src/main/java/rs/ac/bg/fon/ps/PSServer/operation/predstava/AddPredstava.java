/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;
import rs.ac.bg.fon.ps.PSServer.validator.components.NumberValidator;
import rs.ac.bg.fon.ps.PSServer.validator.components.TextValidator;

/**
 * Konkretna klasa za dodavanje predstave.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 *
 * @author andjelalaus
 */
public class AddPredstava extends AbstractGenericOperation{
     /**
     * Flag koji označava da li je dodavanje predstave uspešno.Po defaultu je false.
     */
    private boolean flag = false;
    /**
     * Validator koji se koristi za validaciju predstave.
     */
    private IValidator validator;
      /**
     * Poruka o izuzetku vezanom za naziv predstave.
     */
    private String exceptionNaziv;
      /**
     * Poruka o izuzetku vezanom za naziv mesta gde se odrzava predstava.
     */
    private String exceptionMesto;
      /**
     * Poruka o izuzetku vezanom za smestajni kapacitet predstave.
     */
    private String exceptionKapacitet;
     /**
     * Poruka o svim izuzecima.
     */
    private String exception="";

    public AddPredstava() {
    }

    public AddPredstava(Repository repo) {
        repository=repo;
    }
    
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
        /**
    * Proverava da li je dodavanje predstave uspešno.
    *
    * @return true ako je dodavanje predstave uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    }
}
