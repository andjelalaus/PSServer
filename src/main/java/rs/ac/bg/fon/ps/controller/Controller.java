/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Karta;
import rs.ac.bg.fon.ps.domain.Predstava;
import rs.ac.bg.fon.ps.domain.PlanGledanja;
import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.operation.karte.AddKarta;
import rs.ac.bg.fon.ps.operation.karte.DeleteKarta;
import rs.ac.bg.fon.ps.operation.karte.GetAllKarte;
import rs.ac.bg.fon.ps.operation.klijent.AddKlijent;
import rs.ac.bg.fon.ps.operation.klijent.GetAllClients;
import rs.ac.bg.fon.ps.operation.plan.AddPlan;
import rs.ac.bg.fon.ps.operation.plan.DeletePlan;
import rs.ac.bg.fon.ps.operation.plan.GetAllPlan;
import rs.ac.bg.fon.ps.operation.predstava.AddPredstava;
import rs.ac.bg.fon.ps.operation.predstava.DeletePredstava;
import rs.ac.bg.fon.ps.operation.predstava.GetAllPredstava;
import rs.ac.bg.fon.ps.operation.rezervacija.AddRezervacija;
import rs.ac.bg.fon.ps.operation.rezervacija.DeleteRezervacija;
import rs.ac.bg.fon.ps.operation.rezervacija.GetAllRezervacija;

import rs.ac.bg.fon.ps.operation.rezervacija.UpdateRezervacija;
import rs.ac.bg.fon.ps.operation.stavkerezervacije.AddStavka;
import rs.ac.bg.fon.ps.operation.stavkerezervacije.DeleteStavka;
import rs.ac.bg.fon.ps.operation.stavkerezervacije.GetAllStavke;
import rs.ac.bg.fon.ps.operation.stavkerezervacije.UpdateStavka;
import rs.ac.bg.fon.ps.operation.user.Login;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DbRepository;


/**
 *
 * @author Cartman
 */
public class Controller {

 
    private AbstractGenericOperation operation;
    private static Controller controller;

    private Controller() {
      
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

 
    
    public boolean update(Object object) throws Exception{
        if(object instanceof Rezervacija) return updateRezervacija(object);
        if(object instanceof StavkaRezervacije) return updateStavka(object);
        
        return false;
    }
    
    public boolean add(Object object) throws Exception {
        if(object instanceof Predstava) return addPredstava(object);
        //if(object instanceof Rezervacija) return addRezervacija(object);
        if(object instanceof Klijent) return addKlijent(object);
        if(object instanceof Karta) return addKarta(object);
        if(object instanceof PlanGledanja) return addPlanGledanja(object);
        if(object instanceof StavkaRezervacije) return addStavka(object);
        return false;
    }
    public Integer addR(Object object) throws Exception{
        if(object instanceof Rezervacija) return addRezervacija(object);
        else return null;
    }

    public boolean delete(Object object) throws Exception {
        if(object instanceof Predstava) return deletePredstava(object);
        if(object instanceof Karta) return deleteKarta(object);
        if(object instanceof Rezervacija) return deleteRezervaciju(object);
        if(object instanceof StavkaRezervacije) return deleteStavka(object);
        if(object instanceof PlanGledanja) return deletePlan(object);
        
        return false;
    }

    public Object getAll(Object object) throws Exception {
        if(object instanceof Rezervacija) return getRezervacija(object);
        if(object instanceof StavkaRezervacije) return getStavkaRezervacije(object);
        if(object instanceof Predstava) return getPredstava(object);
        if(object instanceof Karta) return getKarta(object);
        if(object instanceof Klijent) return getKlijent(object);
        if(object instanceof PlanGledanja) return getPlan(object);
        
        return null;
    }

    
    public Object login(User user) throws Exception {
        operation = new Login();
        operation.execute(user);
        return ((Login)operation).getLogin();
    }

    
    private boolean updateRezervacija(Object argument) throws Exception {
        operation = new UpdateRezervacija();
        operation.execute(argument);
        return ((UpdateRezervacija)operation).confirm();
    }


    private Integer addRezervacija(Object argument) throws Exception {
        operation = new AddRezervacija();
        operation.execute(argument);
        return ((AddRezervacija)operation).confirm();
    }
      private boolean addKarta(Object argument) throws Exception {
        operation = new AddKarta();
        operation.execute(argument);
        return ((AddKarta)operation).confirm();
    }

    private boolean addKlijent(Object argument) throws Exception {
        operation = new AddKlijent();
        operation.execute(argument);
        return ((AddKlijent)operation).confirm();
    }
     private boolean addPlanGledanja(Object argument) throws Exception {
        operation = new AddPlan();
        operation.execute(argument);
        return ((AddPlan)operation).confirm();
    }

    private boolean deletePredstava(Object argument) throws Exception {
        operation = new DeletePredstava();
        operation.execute(argument);
        return ((DeletePredstava)operation).confirm();
    }

    private boolean deleteKarta(Object argument) throws Exception {
        operation = new DeleteKarta();
        operation.execute(argument);
        return ((DeleteKarta)operation).confirm();
    }

    private boolean deleteRezervaciju(Object argument) throws Exception {
        operation = new DeleteRezervacija();
        operation.execute(argument);
        return ((DeleteRezervacija)operation).confirm();
    }


    private Object getRezervacija(Object argument) throws Exception {
        operation = new GetAllRezervacija();
        operation.execute(argument);
        return ((GetAllRezervacija)operation).getRezervacije();
    }

    private Object getStavkaRezervacije(Object argument) throws Exception {
        operation = new GetAllStavke();
        operation.execute(argument);
        return ((GetAllStavke)operation).getStavke();
    }

    private Object getPredstava(Object argument) throws Exception {
        operation = new GetAllPredstava();
        operation.execute(argument);
        return ((GetAllPredstava)operation).getPredstave();
    }

    private Object getKarta(Object argument) throws Exception {
        operation = new GetAllKarte();
        operation.execute(argument);
        return ((GetAllKarte)operation).getKarte();
    }

    private Object getKlijent(Object argument) throws Exception {
        operation = new GetAllClients();
        operation.execute(argument);
        return ((GetAllClients)operation).getClients();
    }

   
    private boolean addPredstava(Object argument) throws Exception {
        operation = new AddPredstava();
        operation.execute(argument);
        return ((AddPredstava)operation).confirm();
    }

    private boolean addStavka(Object object) throws Exception {
        operation = new AddStavka();
        operation.execute(object);
        return ((AddStavka)operation).confirm();
    }

    private boolean updateStavka(Object object) throws Exception {
        operation = new UpdateStavka();
        operation.execute(object);
        return ((UpdateStavka)operation).confirm();
    }

    private boolean deleteStavka(Object object) throws Exception {
        operation = new DeleteStavka();
        operation.execute(object);
        return ((DeleteStavka)operation).confirm();
    }

    private boolean deletePlan(Object object) throws Exception{
        operation = new DeletePlan();
        operation.execute(object);
        return ((DeletePlan)operation).confirm();
    }

    private Object getPlan(Object object) throws Exception{
        operation = new GetAllPlan();
        operation.execute(object);
        return ((GetAllPlan)operation).getPlan();
    }
}
