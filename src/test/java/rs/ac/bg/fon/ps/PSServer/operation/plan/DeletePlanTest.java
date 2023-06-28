/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.plan;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.PlanGledanja;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class DeletePlanTest {
    
    DeletePlan ak;
    
   
    @BeforeEach
    public void setUp() {
        ak=new DeletePlan();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     @Test
    public void testPreconditionsOcenaOk() {
           
        Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
       Predstava p=new Predstava(1, "Here", "Here", LocalDateTime.MIN,20);
        PlanGledanja pl=new PlanGledanja(p, k, Boolean.TRUE,10);
        
        assertDoesNotThrow(()->ak.preconditions(pl));
    }
       @Test
    public void testPreconditionsOcenaManjaOd0() {
           
        Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
       Predstava p=new Predstava(1, "Here", "Here", LocalDateTime.MIN,20);
        PlanGledanja pl=new PlanGledanja(p, k, Boolean.TRUE,-1);
        
           assertThrows(Exception.class, ()->ak.preconditions(pl));
    }
      @Test
    public void testPreconditionsOcenaVecaOd0() {
           
        Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
       Predstava p=new Predstava(1, "Here", "Here", LocalDateTime.MIN,20);
        PlanGledanja pl=new PlanGledanja(p, k, Boolean.TRUE,11);
        
           assertThrows(Exception.class, ()->ak.preconditions(pl));
    }
    
}