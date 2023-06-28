/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class AddPredstavaTest {
    
    AddPredstava ak;
   
    
    @BeforeEach
    public void setUp() {
        ak=new AddPredstava();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

      @Test
    public void testPreconditions() {
        Predstava p=new Predstava(1, "Lala", "Belgrade", LocalDateTime.MIN,100);
        assertDoesNotThrow(()->ak.preconditions(p));
    }
    @Test
     public void testPreconditionNazivNull() throws ValidatorException {
        Predstava p=new Predstava();
        //p.setNaziv("Napredno programiranje");
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
     @Test
      public void testPreconditionsnazivPrazno() throws ValidatorException {
          Predstava p=new Predstava(1, "", "Belgrade", LocalDateTime.MIN,100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsNazivBroj() throws ValidatorException {
         
        Predstava p=new Predstava(1, "12a", "Belgrade", LocalDateTime.MIN,100);
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsNazivVeciOD100() throws ValidatorException {
        Predstava p=new Predstava();
        //p.setNaziv("Napredno programiranje");
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        p.setNaziv("Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p),"Polje moze da sadrzi maksimalno 100 znakova");
    }
          @Test
     public void testPreconditionMestoNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setNaziv("Napredno programiranje");
        //p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
     @Test
      public void testPreconditionsMestoPrazno() throws ValidatorException {
          Predstava p=new Predstava(1, "Lalal", "", LocalDateTime.MIN,100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsMestoBroj() throws ValidatorException {
         
        Predstava p=new Predstava(1, "Bela", "12a", LocalDateTime.MIN,100);
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsMestoVeciOD100() throws ValidatorException {
        Predstava p=new Predstava();
        //p.setNaziv("Napredno programiranje");
        p.setNaziv("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        p.setMesto("Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p),"Polje moze da sadrzi maksimalno 100 znakova");
    }
        @Test
    public void testPreconditionsKpacitetManjaOd0() {
           
        
       Predstava p=new Predstava(1, "Here", "Here", LocalDateTime.MIN,-1);

        
      assertThrows(ValidatorException.class, ()->ak.preconditions(p));
    }
    
}
