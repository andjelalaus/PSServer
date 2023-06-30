/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;


/**
 * Konkretna klasa za brisanje predstave.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 * @author andjelalaus
 */
public class DeletePredstava extends AbstractGenericOperation{
    /**
     * Flag koji označava da li je brisanje predstave uspešno.Po defaultu je false.
     */
    private boolean flag = false;

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
     * Poruka o izuzetku vezanom za vreme odrzavanja predstave.
     */
    private String exceptionVreme;
    /**
     * Poruka o svim izuzecima.
     */
    private String exception="";
/**
 * Bezparametarski konstruktor
 */
    public DeletePredstava() {
    }
/**
     * Parametarski konstruktor sa parametrom repository
     * @param repo predstavlja objekat klase Repository
     */
    public DeletePredstava(Repository repo) {
        repository=repo;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception{
        Predstava predstava = (Predstava)param;
    
         if(predstava.getNaziv()==null){
            exceptionNaziv="Naziv predstave ne sme biti prazan";
            exception = exceptionNaziv + "," + exception;
            }
         if(predstava.getMesto()==null){
            exceptionMesto="Mesto predstave ne sme biti prazno";
            exception = exceptionMesto + "," + exception;
         }
         if(predstava.getVreme()==null){
            exceptionVreme="Vreme predstave ne sme biti prazno";
            exception = exceptionVreme + "," + exception;
         }
          if(predstava.getKapacitet()==null){
            exceptionKapacitet="Kapacitet predstave ne sme biti prazno";
            exception = exceptionKapacitet + "," + exception;
         }
        
        if(exceptionNaziv!=null || exceptionKapacitet!=null || exceptionMesto!=null || exceptionVreme!=null){
            throw new Exception(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.delete((Predstava)param);
    }
    /**
    * Proverava da li je brisanje predstave uspešno.
    *
    * @return true ako je brisanje predstave uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    }
}
