/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.plan;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.PlanGledanja;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 *
 * @author andelalausevic
 */
public class GetAllPlanTest {
    
    GetAllPlan gat;
    
  
    
    @BeforeEach
    public void setUp() {
        gat=new GetAllPlan();
    }
    
    @AfterEach
    public void tearDown() {
        gat=null;
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
        
        PlanGledanja pl2=new PlanGledanja();
        pl2.setGledao(Boolean.FALSE);
        pl2.setKlijentId(k);
        pl2.setPredstavaId(p);
        pl2.setOcena(7);
        
       
        
        Repository repository = mock(Repository.class);
        GetAllPlan gak = new GetAllPlan(repository);
        
        given(repository.getAll(pl)).willReturn(List.of(pl,pl2));
       
        gak.executeOperation(pl);
       
        verify(repository,times(1)).getAll(pl);
      
    }
    
}
