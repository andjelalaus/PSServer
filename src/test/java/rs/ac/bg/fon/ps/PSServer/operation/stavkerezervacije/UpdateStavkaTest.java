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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
public class UpdateStavkaTest {
    UpdateStavka ak;
    
    @BeforeEach
    public void setUp() {
        ak=new UpdateStavka();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

        @Test
    public void testPreconditions() {
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Milos");
        klij.setEmail("miki@jopa.com");
        klij.setPrezime("Josipovic");
        klij.setStatus("nezaposlen");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Krcko Orascic");
        p.setKapacitet(250);
        p.setVreme(LocalDateTime.MIN);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(15);
        st.setStavkaId(1);
        assertDoesNotThrow(()->ak.preconditions(st));
    }
    
    @Test
     public void testPreconditionsPredstavaNull() throws ValidatorException {
        
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Milos");
        klij.setEmail("miki@jopa.com");
        klij.setPrezime("Josipovic");
        klij.setStatus("nezaposlen");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setStavkaId(1);
        st.setBrojSedista(2);
        st.setPopust(10);
        st.setGledato(true);
        st.setRezervacijaId(r);
        
        
        assertThrows(ValidatorException.class,()->ak.preconditions(st));
    }
       @Test
    public void testPreconditionsBrSedistaManjaOd0() {
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Milos");
        klij.setEmail("miki@jopa.com");
        klij.setPrezime("Josipovic");
        klij.setStatus("nezaposlen");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Karmen");
        p.setKapacitet(1050);
        p.setVreme(LocalDateTime.MIN);
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter  gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
        StavkaRezervacije st=new StavkaRezervacije(1, 10, -1, true, r, p);

        
      assertThrows(ValidatorException.class, ()->ak.preconditions(st));
    }
       @Test
    public void testPreconditionsPopustManjiOd0() {
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Milos");
        klij.setEmail("miki@jopa.com");
        klij.setPrezime("Josipovic");
        klij.setStatus("nezaposlen");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Karmen");
        p.setKapacitet(1050);
        p.setVreme(LocalDateTime.MIN);
        
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
        StavkaRezervacije st=new StavkaRezervacije(1, -1, 2, true, r, p);
         
        
      assertThrows(ValidatorException.class, ()->ak.preconditions(st));
    }
        @Test
    public void testPreconditionsPopustVeciOd100() {
           
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Milos");
        klij.setEmail("miki@jopa.com");
        klij.setPrezime("Josipovic");
        klij.setStatus("nezaposlen");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Magbet");
        p.setKapacitet(1050);
        p.setVreme(LocalDateTime.MIN);
        
        //stavljene vrednosti preko konstruktora jer da su preko settera 
        //uhvatio bi setter  gresku a ovde hocemo da proverimo da li ce je uhvatiti precondition
        StavkaRezervacije st=new StavkaRezervacije(1, 101, 2, true, r, p);

        
      assertThrows(ValidatorException.class, ()->ak.preconditions(st));
    }
            @Test
    public void testPreconditionsRezervacijaNull() {
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Magbet");
        p.setKapacitet(1050);
        p.setVreme(LocalDateTime.MIN);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setStavkaId(1);
        st.setBrojSedista(2);
        st.setPopust(10);
        st.setGledato(true);
        st.setPredstavaId(p);
     
        
      assertThrows(ValidatorException.class, ()->ak.preconditions(st));
    }
       @Test
    public void testExecuteOperation() throws Exception{
        
        LocalDateTime ld=LocalDateTime.of(2023, Month.NOVEMBER, 19, 20, 0);
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Milos");
        klij.setEmail("miki@jopa.com");
        klij.setPrezime("Josipovic");
        klij.setStatus("nezaposlen");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Kopelija");
        p.setKapacitet(2500);
        p.setVreme(ld);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(15);
        st.setStavkaId(1);
        
       
        Repository repository = mock(Repository.class);
        UpdateStavka upSt = new UpdateStavka(repository);
        
        when(repository.edit(st)).thenReturn(Boolean.TRUE);
        
        upSt.executeOperation(st);
        
        verify(repository,times(1)).edit(st);
        
    }
}
