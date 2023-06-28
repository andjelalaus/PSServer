/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.operation.karte.AddKarta;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class AddRezervacijaTest {
    
    AddRezervacija ak;
    
    @BeforeEach
    public void setUp() {
        ak=new AddRezervacija();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

   @Test
    public void testPreconditions() {
         Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
         Rezervacija r=new Rezervacija(1, 22, k);
        assertDoesNotThrow(()->ak.preconditions(r));
    }
    
    @Test
     public void testPreconditionsKlijentNull() throws ValidatorException {
        Rezervacija r=new Rezervacija();
        r.setId(1);
        r.setBrojPredstave(22);
        
        
        assertThrows(ValidatorException.class,()->ak.preconditions(r));
    }
       @Test
    public void testPreconditionsBrPredstavaManjaOd0() {
           
       Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
       Rezervacija r=new Rezervacija(1, -1, k);

        
      assertThrows(ValidatorException.class, ()->ak.preconditions(r));
    }
    @Test
    public void testExecuteOperation() throws Exception{
        Rezervacija k=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        
       
        Repository repository = mock(Repository.class);
        AddRezervacija addRez = new AddRezervacija(repository);
        
        when(repository.addReturnKey(k)).thenReturn(1);
        
        addRez.executeOperation(k);
        
        verify(repository,times(1)).addReturnKey(k);
        
    }
    
}
