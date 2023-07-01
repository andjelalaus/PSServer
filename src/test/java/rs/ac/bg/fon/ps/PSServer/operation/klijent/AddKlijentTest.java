/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.klijent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class AddKlijentTest {
   
    AddKlijent ak;
    
    @BeforeEach
    public void setUp() {
        ak=new AddKlijent();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     @Test
    public void testPreconditions() {
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        assertDoesNotThrow(()->ak.preconditions(k));
    }
        @Test
    public void testPreconditionsImePrazno() throws ValidatorException {
        //preko konstruktora postavljene vrednosti jer bi uhvatio setter gresku
        //a ovde hocemo da proverimo da li bi uhvatio precondition
        Klijent k=new Klijent(1, "", "Laus", "aa@gg.com", "redovan");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
       @Test
    public void testPreconditionsImeBroj() throws ValidatorException {
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("a123");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
    @Test
     public void testPreconditionsImeNull() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setEmail("aa@gg.com");
        k.setStatus("redovan");
        k.setPrezime("Laus");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
     @Test
      public void testPreconditionsPrezimePrazno() throws ValidatorException {
         //preko konstruktora postavljene vrednosti jer bi uhvatio setter gresku
        //a ovde hocemo da proverimo da li bi uhvatio precondition
          Klijent k=new Klijent(1, "And", "", "aa@gg.com", "redovan");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
       @Test
      public void testPreconditionsPrezimeBroj() throws ValidatorException {
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andjela");
        k.setEmail("aa@gg.com");
        k.setPrezime("12dad");
        k.setStatus("redovan");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
      @Test
       public void testPreconditionsPrezimeNull() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setEmail("aa@gg.com");
        k.setStatus("redovan");
        k.setIme("Laus");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
       @Test
         public void testPreconditionsMejlPrazno() throws ValidatorException {
           //preko konstruktora postavljene vrednosti jer bi uhvatio setter gresku
        //a ovde hocemo da proverimo da li bi uhvatio precondition
          Klijent k=new Klijent(1, "And", "Laal", "", "redovan");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
         @Test
       public void testPreconditionsMejlNull() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setPrezime("Nana");
        k.setStatus("redovan");
        k.setIme("Laus");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
    @ParameterizedTest
    @CsvSource ({
		"aaaaa",
                "aaaa@",
                "@gggg",
                "@gg.c",
                "aa.aa",
                "1234"
                
	})
    public void testPreconditionsMejlNijeMejl(String email) throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setPrezime("Nana");
        k.setStatus("redovan");
        k.setIme("Laus");
        k.setEmail(email);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
    
            @Test
      public void testPreconditionsStatusPrazno() throws ValidatorException {
         //preko konstruktora postavljene vrednosti jer bi uhvatio setter gresku
        //a ovde hocemo da proverimo da li bi uhvatio precondition
          Klijent k=new Klijent(1, "And", "Laal", "@gg.c", "");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
        @Test
      public void testPreconditionsStatusNull() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setPrezime("Nana");
        k.setEmail("aa@gg.com");
        k.setIme("Laus");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
    @Test
      public void testPreconditionsStatusVeciOD100() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setPrezime("Nana");
        k.setEmail("aa@gg.com");
        k.setIme("Laus");
        k.setStatus("Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k),"Polje moze da sadrzi maksimalno 100 znakova");
    }
       @Test
      public void testPreconditionsImeVeciOD100() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setPrezime("Nana");
        k.setEmail("aa@gg.com");
        k.setStatus("Laus");
        k.setIme("Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k),"Polje moze da sadrzi maksimalno 100 znakova");
    }
       @Test
      public void testPreconditionsPrezimeVeciOD100() throws ValidatorException {
        Klijent k=new Klijent();
        k.setId(1);
        k.setIme("Nana");
        k.setEmail("aa@gg.com");
        k.setStatus("Laus");
        k.setPrezime("Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k),"Polje moze da sadrzi maksimalno 100 znakova");
    }
       @Test
    public void testExecuteOperation() throws Exception{
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        Repository repository = mock(Repository.class);
        AddKlijent addKlijent = new AddKlijent(repository);
        
        when(repository.add(k)).thenReturn(Boolean.TRUE);
        
        addKlijent.executeOperation(k);
        
        verify(repository,times(1)).add(k);
        
    }
}
