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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

public class AddKartaTest {
    
 
    
   
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
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(LocalDateTime.MIN);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(10);
        st.setStavkaId(1);
        
        Karta k=new Karta();
        k.setCena(1000);
        k.setKartaId(1);
        k.setRezervacijaId(r);
        k.setStavkaId(st);
        assertDoesNotThrow(()->ak.preconditions(k));
    }
        @Test
    public void testPreconditionsCenaManjaODNule() throws ValidatorException {
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
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(LocalDateTime.MIN);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(10);
        st.setStavkaId(1);
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter za cenu gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
        Karta k=new Karta(1, -1, r, st);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
   
      @Test
    public void testPreconditionsNemaRez() throws ValidatorException {
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
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(LocalDateTime.MIN);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(10);
        st.setStavkaId(1);
        
        Karta k=new Karta();
        k.setId(1);
        k.setStavkaId(st);
        
        k.setCena(1000);
        
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
    }
        @Test
    public void testPreconditionsNemaStavke() throws ValidatorException {
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
        
        Karta k=new Karta();
        k.setId(1);
        k.setRezervacijaId(r);
        assertThrows(ValidatorException.class,()->ak.preconditions(k));
        // TODO review the generated test code and remove the default call to fail.
    }
  @Test
    public void testExecuteOperation() throws Exception{
       
        LocalDateTime ld=LocalDateTime.of(2023, Month.DECEMBER, 10, 20, 0);
        
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
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Don Kihot");
        p.setKapacitet(200);
        p.setVreme(ld);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(10);
        st.setStavkaId(1);
        
        Karta k=new Karta();
        k.setCena(1000);
        k.setKartaId(1);
        k.setRezervacijaId(r);
        k.setStavkaId(st);
       
        Repository repository = mock(Repository.class);
        AddKarta addKarta = new AddKarta(repository);
        
        when(repository.add(k)).thenReturn(Boolean.TRUE);
        
        addKarta.executeOperation(k);
        
        verify(repository,times(1)).add(k);
        
    }
       

  
    
}
