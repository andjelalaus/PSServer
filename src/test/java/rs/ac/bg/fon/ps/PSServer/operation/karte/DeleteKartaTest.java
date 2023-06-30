/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class DeleteKartaTest {
    
    DeleteKarta ak;
    @BeforeEach
    public void setUp() {
        ak=new DeleteKarta();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

    @Test
    public void testPreconditions() {
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here", LocalDateTime.MIN,20));
        Karta k=new Karta(1, 1000, r, st);
        assertDoesNotThrow(()->ak.preconditions(k));
    }
        @Test
    public void testPreconditionsCenaManjaODNule() throws ValidatorException {
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here", LocalDateTime.MIN,20));
        Karta k=new Karta(1, -1, r, st);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
    @Test
    public void testExecuteOperation() throws Exception{
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here",ld,20));
        Karta k=new Karta(1, 2, r, st);
        
        Repository repository = mock(Repository.class);
        DeleteKarta dk = new DeleteKarta(repository);
        
        given(repository.delete(k)).willReturn(Boolean.TRUE);
        
        dk.executeOperation(k);
        
        verify(repository,times(1)).delete(k);
    }
  
    
}
