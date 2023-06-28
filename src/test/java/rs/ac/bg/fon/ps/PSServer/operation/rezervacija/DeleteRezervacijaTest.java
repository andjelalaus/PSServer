/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class DeleteRezervacijaTest {
    
    DeleteRezervacija ak;
    
    @BeforeEach
    public void setUp() {
        ak=new DeleteRezervacija();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     @Test
    public void testPreconditionsBrPredstavaManjaOd0() {
           
       Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
       Rezervacija r=new Rezervacija(1, -1, k);

        
      assertThrows(Exception.class, ()->ak.preconditions(r),"Broj predstavi ne sme biti prazan ili biti manje od 0");
    }
     @Test
    public void testPreconditions() {
         Klijent k=new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan");
         Rezervacija r=new Rezervacija(1, 22, k);
        assertDoesNotThrow(()->ak.preconditions(r));
    }
    
}
