/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.repository.db.impl.RepositoryDBGeneric;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
@ExtendWith(MockitoExtension.class)
public class AddKartaTest {
    
    @Mock
    RepositoryDBGeneric repo;        
    @InjectMocks
   AddKarta ak;
    @BeforeEach
    public void setUp() {
        ak=new AddKarta();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

    /**
     * Test of preconditions method, of class AddKarta.
     */
  
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
    public void testPreconditionsNemaRez() throws ValidatorException {
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));

        StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here", LocalDateTime.MIN,20));
        Karta k=new Karta();
        k.setId(1);
        k.setStavkaId(st);
        
        k.setCena(1000);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
        @Test
    public void testPreconditionsNemaStavke() throws ValidatorException {
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        Karta k=new Karta();
        k.setId(1);
        k.setRezervacijaId(r);
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
        // TODO review the generated test code and remove the default call to fail.
    }
    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    public void testExecuteOperation() throws Exception{
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here",ld,20));
        Karta k=new Karta(1, 2, r, st);
       given(repo.add(k)).willReturn(true);
        assertDoesNotThrow(()->ak.executeOperation(k));
        
      
    }
       

  
    
}
