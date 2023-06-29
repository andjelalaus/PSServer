/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije;

import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.IValidator;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;
import rs.ac.bg.fon.ps.PSServer.validator.components.NumberValidator;
import rs.ac.bg.fon.ps.PSServer.validator.components.PopustValidator;

/**
 * Konkretna klasa za auzuriranje stavki rezervacija.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 * @author andjelalaus
 */
public class UpdateStavka extends AbstractGenericOperation {
     /**
     * Flag koji označava da li je dodavanje stavke uspešno.Po defaultu je false.
     */
    private boolean flag = false;
     /**
     * Validator koji se koristi za validaciju stavki rezervacije.
     */
    private IValidator validator;
    /**
     * Poruka o izuzetku vezanom za broj sedista.
     */
    private String exceptionBrojSedista;
     /**
     * Poruka o izuzetku vezanom za nepostojanje rezervacije.
     */
    private String exceptionRezervacija;
     /**
     * Poruka o izuzetku vezanom za nepostojanje predstave.
     */
    private String exceptionPredstava;
     /**
     * Poruka o izuzetku vezanom za popust.
     */
    private String exceptionPopust;
     /**
     * Poruka o svim izuzecima.
     */
    private String exception="";

    public UpdateStavka() {
    }

    
    public UpdateStavka(Repository repo) {
        repository=repo;
    }
    
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
        flag = repository.edit((StavkaRezervacije)param);
    }
    /**
    * Proverava da li je auzuriranje stavke uspešno.
    *
    * @return true ako je dodavanje stavke uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    }
}
