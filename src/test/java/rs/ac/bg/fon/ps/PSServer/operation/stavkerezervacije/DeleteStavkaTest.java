/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije;

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
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

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
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Nadja");
        klij.setEmail("nadja@gg.com");
        klij.setPrezime("Mirkovic");
        klij.setStatus("student");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Mala scena");
        p.setNaziv("Pinokio");
        p.setKapacitet(250);
        p.setVreme(LocalDateTime.MIN);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(25);
        st.setStavkaId(1);
        assertDoesNotThrow(()->ak.preconditions(st));
    }
    

      @Test
    public void testPreconditionsBrSedistaManjaOd0() {
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Nadja");
        klij.setEmail("nadja@gg.com");
        klij.setPrezime("Mirkovic");
        klij.setStatus("student");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Mala scena");
        p.setNaziv("Ruzno pace");
        p.setKapacitet(250);
        p.setVreme(LocalDateTime.MIN);
        
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter  gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
        StavkaRezervacije st=new StavkaRezervacije(1, 10, -1, true, r, p);

        
      assertThrows(Exception.class, ()->ak.preconditions(st),"Broj sedista ne sme biti manje od 0");
    }
       @Test
    public void testPreconditionsPopustManjiOd0() {
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Nadja");
        klij.setEmail("nadja@gg.com");
        klij.setPrezime("Mirkovic");
        klij.setStatus("student");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Mala scena");
        p.setNaziv("Crvenkapa");
        p.setKapacitet(250);
        p.setVreme(LocalDateTime.MIN);
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
         StavkaRezervacije st=new StavkaRezervacije(1, -1, 2, true, r, p);
         
        
      assertThrows(Exception.class, ()->ak.preconditions(st),"Popust ne sme biti manje od 0 ili veci od 100");
    }
        @Test
    public void testPreconditionsPopustVeciOd100() {
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Nadja");
        klij.setEmail("nadja@gg.com");
        klij.setPrezime("Mirkovic");
        klij.setStatus("student");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Mala scena");
        p.setNaziv("Snezana i sedam patuljaka");
        p.setKapacitet(250);
        p.setVreme(LocalDateTime.MIN);
        
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
        StavkaRezervacije st=new StavkaRezervacije(1, 101, 2, true, r, p);

        
      assertThrows(Exception.class, ()->ak.preconditions(st),"Popust ne sme biti manje od 0 ili veci od 100");
    }
    @Test
    public void testExecuteOperation() throws Exception{
        
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Nadja");
        klij.setEmail("nadja@gg.com");
        klij.setPrezime("Mirkovic");
        klij.setStatus("student");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Mala scena");
        p.setNaziv("Pepeljuga");
        p.setKapacitet(250);
        p.setVreme(ld);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(25);
        st.setStavkaId(1);
        
        
        Repository repository = mock(Repository.class);
        DeleteStavka dk = new DeleteStavka(repository);
        
        given(repository.delete(st)).willReturn(Boolean.TRUE);
        
        dk.executeOperation(st);
        
        verify(repository,times(1)).delete(st);
    }
    
}
