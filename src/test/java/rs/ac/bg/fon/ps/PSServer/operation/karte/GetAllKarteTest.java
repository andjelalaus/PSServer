/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;


/**
 *
 * @author andelalausevic
 */
public class GetAllKarteTest {

    GetAllKarte ak;
    
   
    @BeforeEach
    public void setUp() {
        ak=new GetAllKarte();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
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
        
        Karta k2=new Karta();
        k2.setCena(1500);
        k2.setKartaId(2);
        k2.setRezervacijaId(r);
        k2.setStavkaId(st);
        
        Repository repository = mock(Repository.class);
        GetAllKarte gak = new GetAllKarte(repository);
        
        given(repository.getAll(k)).willReturn(List.of(k,k2));
       
        gak.executeOperation(k);
       
        verify(repository,times(1)).getAll(k);
      
    }
   
    
}
