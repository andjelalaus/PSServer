/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije;

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
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 *
 * @author andelalausevic
 */
public class GetAllStavkeTest {
    
   
    GetAllStavke ga;
    @BeforeEach
    public void setUp() {
        ga=new GetAllStavke();
    }
    
    @AfterEach
    public void tearDown() {
        ga=null;
    }

    @Test
    public void testExecuteOperation() throws Exception{
        
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        Klijent klij=new Klijent();
        klij.setKlijentId(1);
        klij.setIme("Andjela");
        klij.setEmail("aa@gg.com");
        klij.setPrezime("Lausevic");
        klij.setStatus("redovan");
        
        Rezervacija r=new Rezervacija();
        r.setRezervacijaId(1);
        r.setKlijentId(klij);
        r.setBrojPredstave(1);
        
        Predstava p=new Predstava();
        p.setPredstavaId(1);
        p.setMesto("Velika scena");
        p.setNaziv("Uspavana lepotica");
        p.setKapacitet(250);
        p.setVreme(ld);
        
        StavkaRezervacije st=new StavkaRezervacije();
        st.setBrojSedista(10);
        st.setRezervacijaId(r);
        st.setPredstavaId(p);
        st.setGledato(true);
        st.setPopust(15);
        st.setStavkaId(1);
        
        StavkaRezervacije st2=new StavkaRezervacije();
        st2.setBrojSedista(2);
        st2.setRezervacijaId(r);
        st2.setPredstavaId(p);
        st2.setGledato(false);
        st2.setPopust(25);
        st2.setStavkaId(2);
        
        Repository repository = mock(Repository.class);
        GetAllStavke gak = new GetAllStavke(repository);
        
        given(repository.getAll(st)).willReturn(List.of(st,st2));
       
        gak.executeOperation(st);
       
        verify(repository,times(1)).getAll(st);
      
    }
    
}
