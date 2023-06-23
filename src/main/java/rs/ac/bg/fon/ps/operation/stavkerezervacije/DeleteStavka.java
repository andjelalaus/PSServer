/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.stavkerezervacije;

import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class DeleteStavka extends AbstractGenericOperation {
    private boolean flag = false;
    private String exceptionBrojSedista;
    private String exceptionPopust;
     private String exceptionNULL;
    private String exception;
    @Override
    protected void preconditions(Object param) throws Exception {
         StavkaRezervacije t = (StavkaRezervacije)param;
      
      
        if(t.getBrojSedista()<0){
            exceptionBrojSedista = "Broj sedista ne sme biti manje od 0";
            exception = exceptionBrojSedista + "," + exception;
        }
          if(t.getBrojSedista()<0 || t.getBrojSedista()>101){
            exceptionPopust = "Popust ne sme biti manje od 0 ili veci od 100";
            exception = exceptionPopust + "," + exception;
        }
          
         if(exceptionBrojSedista!=null || exceptionPopust!=null){
            throw new Exception(exception);
        }
         
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.delete((StavkaRezervacije)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
