/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.plan;

import rs.ac.bg.fon.ps.domain.PlanGledanja;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class DeletePlan extends AbstractGenericOperation{
     private boolean flag = false;
    private String exceptionOcena;
   
    private String exception;
    @Override
    protected void preconditions(Object param) throws Exception {
         PlanGledanja t = (PlanGledanja)param;
      
      
        if(t.getOcena()<0 || t.getOcena()>10){
            exceptionOcena = "Ocena ne sme biti manje od 0 ni veca od 10";
            exception = exceptionOcena + "," + exception;
        }
     
          
         if(exceptionOcena!=null){
            throw new Exception(exception);
        }
         
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.delete((PlanGledanja)param);
    }
    
    public boolean confirm(){
        return flag;
    }
}
