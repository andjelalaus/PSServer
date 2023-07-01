/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.plan;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.PlanGledanja;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class AddPlanTest {
    
   AddPlan ak;
    
    @BeforeEach
    public void setUp() {
        ak=new AddPlan();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

    /**
     * Test of preconditions method, of class AddPlan.
     */
       @Test
    public void testPreconditionsOcenaOk() {
           
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(LocalDateTime.MIN);
        
        PlanGledanja pl=new PlanGledanja();
        pl.setGledao(Boolean.TRUE);
        pl.setKlijentId(k);
        pl.setPredstavaId(p);
        pl.setOcena(10);
        
        assertDoesNotThrow(()->ak.preconditions(pl));
    }
       @Test
    public void testPreconditionsOcenaManjaOd0() {
           
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(LocalDateTime.MIN);
        
        //preko konstruktora postavljene vrednosti
        //da bi proverili precondition jer bi inace setter uhvatio gresku
        PlanGledanja pl=new PlanGledanja(p, k, Boolean.TRUE,-1);
        
           assertThrows(ValidatorException.class, ()->ak.preconditions(pl));
    }
      @Test
    public void testPreconditionsOcenaVecaOd10() {
           
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(LocalDateTime.MIN);
        
         //preko konstruktora postavljene vrednosti
        //da bi proverili precondition jer bi inace setter uhvatio gresku
        PlanGledanja pl=new PlanGledanja(p, k, Boolean.TRUE,11);
        
           assertThrows(ValidatorException.class, ()->ak.preconditions(pl));
    }
    @Test
    public void testExecuteOperation() throws Exception{
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);

        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(ld);
        
        PlanGledanja pl=new PlanGledanja();
        pl.setGledao(Boolean.TRUE);
        pl.setKlijentId(k);
        pl.setPredstavaId(p);
        pl.setOcena(10);
       
        
        Repository repository = mock(Repository.class);
        AddPlan addPlan = new AddPlan(repository);
        
        when(repository.add(pl)).thenReturn(Boolean.TRUE);
        
        addPlan.executeOperation(pl);
        
        verify(repository,times(1)).add(pl);
        
    }
}
