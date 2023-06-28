/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;

import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 * 
 * Konkretna klasa za brisanje rezervacije.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 *
 * @author andjelalaus
 */
public class DeleteRezervacija extends AbstractGenericOperation{
    /**
     * Flag koji označava da li je brisanje rezervacije uspešno.Po defaultu je false.
     */
    private boolean flag = false;
     /**
     * Poruka o izuzetku vezanom za broj sedista.
     */
    private String exceptionBrojSedista;
    /**
     * Poruka o svim izuzecima
     */
    private String exception;
/**
 * Bezparametarski konstruktor
 */
    public DeleteRezervacija() {
    }

   /**
     * Parametarski konstruktor sa parametrom repository
     * @param repo predstavlja objekat klase Repository
     */
    public DeleteRezervacija(Repository repo) {
        repository=repo;
    }
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
    /**
    * Proverava da li je brisanje rezervacije uspešno.
    *
    * @return true ako je brisanje rezervacije uspešno, false ako nije
    */
    public boolean confirm(){
        return flag;
    }
}
