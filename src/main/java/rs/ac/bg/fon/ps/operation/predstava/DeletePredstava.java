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
public class DeletePredstava extends AbstractGenericOperation{
    private boolean flag = false;

    private String exceptionNaziv;
    private String exceptionMesto;
    private String exceptionKapacitet;
    private String exceptionVreme;
    private String exception="";
    
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
    
    public boolean confirm(){
        return flag;
    }
}
