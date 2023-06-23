/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.rezervacija;

import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class DeleteRezervacija extends AbstractGenericOperation{
    private boolean flag = false;
    private String exceptionBrojSedista;
    private String exception;
    @Override
    protected void preconditions(Object param) throws Exception {
         Rezervacija t = (Rezervacija)param;
      
        
        if(t.getBrojPredstave()==null || t.getBrojPredstave()<0){
            exceptionBrojSedista = "Broj predstavi ne sme biti prazan ili biti manje od 0";
            exception = exceptionBrojSedista + "," + exception;
        }
         if(exceptionBrojSedista!=null){
            throw new Exception(exception);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.delete((Rezervacija)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
