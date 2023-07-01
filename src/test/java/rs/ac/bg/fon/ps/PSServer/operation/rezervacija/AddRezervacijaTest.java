/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
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
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Andja");
        klij.setEmail("aa@gg.com");
        klij.setPrezime("Laus");
        klij.setStatus("redovan");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
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
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Andja");
        klij.setEmail("aa@gg.com");
        klij.setPrezime("Laus");
        klij.setStatus("redovan");
        
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter  gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
       Rezervacija r=new Rezervacija(1, -1, klij);

        
      assertThrows(ValidatorException.class, ()->ak.preconditions(r));
    }
    @Test
    public void testExecuteOperation() throws Exception{
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Andja");
        klij.setEmail("aa@gg.com");
        klij.setPrezime("Laus");
        klij.setStatus("redovan");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
       
        Repository repository = mock(Repository.class);
        AddRezervacija addRez = new AddRezervacija(repository);
        
        when(repository.addReturnKey(r)).thenReturn(1);
        
        addRez.executeOperation(r);
        
        verify(repository,times(1)).addReturnKey(r);
        
    }
    
}
