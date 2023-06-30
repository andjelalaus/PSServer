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
        Klijent klij=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        Predstava p= new Predstava(1, "Here", "Here",ld,20);
        
        PlanGledanja k1=new PlanGledanja(p, klij, Boolean.TRUE,9);
        PlanGledanja k2=new PlanGledanja(p, klij, Boolean.FALSE,10);
        
        Repository repository = mock(Repository.class);
        GetAllPlan gak = new GetAllPlan(repository);
        
        given(repository.getAll(k1)).willReturn(List.of(k1,k2));
       
        gak.executeOperation(k1);
       
        verify(repository,times(1)).getAll(k1);
      
    }
    
}
