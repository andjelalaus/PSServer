/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

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
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
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
        Predstava p=new Predstava();
        p.setNaziv("Napredno programiranje");
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        assertDoesNotThrow(()->ak.preconditions(p));
    }
    @Test
     public void testPreconditionNazivNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
     @Test
      public void testPreconditionsnazivPrazno() throws ValidatorException {
          //preko konstruktora zadate vrednosti da ne bi setter uhvatio gresku
          Predstava p=new Predstava(1, "", "Belgrade", LocalDateTime.MIN,100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsNazivBroj() throws ValidatorException {
         
        Predstava p=new Predstava();
        p.setNaziv("12a");
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsNazivVeciOD100() throws ValidatorException {
        Predstava p=new Predstava();
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
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
     @Test
      public void testPreconditionsMestoPrazno() throws ValidatorException {
           //preko konstruktora zadate vrednosti da ne bi setter uhvatio gresku
          Predstava p=new Predstava(1, "Under the sea", "", LocalDateTime.MIN,100);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsMestoBroj() throws ValidatorException {
         
        Predstava p=new Predstava();
        p.setNaziv("Napredno programiranje");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        p.setMesto("22a");
        assertThrows(ValidatorException.class,()->ak.preconditions(p));
    }
       @Test
      public void testPreconditionsMestoVeciOD100() throws ValidatorException {
        Predstava p=new Predstava();
        p.setNaziv("Hamlet");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        p.setMesto("Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(p),"Polje moze da sadrzi maksimalno 100 znakova");
    }
        @Test
    public void testPreconditionsKapacitetManjaOd0() {
           
       //preko konstruktora zadate vrednosti da ne bi setter uhvatio gresku
       Predstava p=new Predstava(1, "Here", "Here", LocalDateTime.MIN,-1);

        
      assertThrows(ValidatorException.class, ()->ak.preconditions(p));
    }
    @Test
    public void testExecuteOperation() throws Exception{
        
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        Predstava p=new Predstava();
        p.setNaziv("Napredno programiranje");
        p.setMesto("Belgrade");
        p.setVreme(ld);
        p.setKapacitet(100);
        
       
        Repository repository = mock(Repository.class);
        AddPredstava addPredstava = new AddPredstava(repository);
        
        when(repository.add(p)).thenReturn(Boolean.TRUE);
        
        addPredstava.executeOperation(p);
        
        verify(repository,times(1)).add(p);
        
    }
    
}
