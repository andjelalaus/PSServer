/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.rezervacija;


import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 *
 * @author andelalausevic
 */
public class GetAllRezervacijaTest {
    
    
    GetAllRezervacija ga;
    @BeforeEach
    public void setUp() {
        ga=new GetAllRezervacija();
    }
    
    @AfterEach
    public void tearDown() {
        ga=null;
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
       
        Rezervacija r2=new Rezervacija();
        r2.setRezervacijaId(2);
        r2.setKlijentId(klij);
        r2.setBrojPredstave(3);
        
        Repository repository = mock(Repository.class);
        GetAllRezervacija gak = new GetAllRezervacija(repository);
        
        given(repository.getAll(r)).willReturn(List.of(r,r2));
       
        gak.executeOperation(r);
       
        verify(repository,times(1)).getAll(r);
      
    }
    
}
