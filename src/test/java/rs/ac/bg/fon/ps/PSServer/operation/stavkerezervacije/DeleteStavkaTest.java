/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class DeleteStavkaTest {
    
    DeleteStavka ak;
    
    @BeforeEach
    public void setUp() {
        ak=new DeleteStavka();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }
       @Test
    public void testPreconditions() {
         Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
         Rezervacija r=new Rezervacija(1, 22, k);
         StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Tataratira", "Bosko buha", LocalDateTime.MIN,2));
        assertDoesNotThrow(()->ak.preconditions(st));
    }
    

      @Test
    public void testPreconditionsBrSedistaManjaOd0() {
           
      Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
         Rezervacija r=new Rezervacija(1, 22, k);
         StavkaRezervacije st=new StavkaRezervacije(1, 10, -1, true, r, new Predstava(1, "Tataratira", "Bosko buha", LocalDateTime.MIN,2));

        
      assertThrows(Exception.class, ()->ak.preconditions(st),"Broj sedista ne sme biti manje od 0");
    }
       @Test
    public void testPreconditionsPopustManjiOd0() {
           
      Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
         Rezervacija r=new Rezervacija(1, 22, k);
         StavkaRezervacije st=new StavkaRezervacije(1, -1, 2, true, r, new Predstava(1, "Tataratira", "Bosko buha", LocalDateTime.MIN,2));
         
        
      assertThrows(Exception.class, ()->ak.preconditions(st),"Popust ne sme biti manje od 0 ili veci od 100");
    }
        @Test
    public void testPreconditionsPopustVeciOd100() {
           
      Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
         Rezervacija r=new Rezervacija(1, 22, k);
         StavkaRezervacije st=new StavkaRezervacije(1, 101, 2, true, r, new Predstava(1, "Tataratira", "Bosko buha", LocalDateTime.MIN,2));

        
      assertThrows(Exception.class, ()->ak.preconditions(st),"Popust ne sme biti manje od 0 ili veci od 100");
    }
    
}
