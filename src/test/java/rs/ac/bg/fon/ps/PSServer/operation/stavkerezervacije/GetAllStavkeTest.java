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
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        StavkaRezervacije k=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here",ld,20));
         StavkaRezervacije k2=new StavkaRezervacije(2, 5, 2, false, r, new Predstava(1, "Here", "Here",ld,20));
        
        Repository repository = mock(Repository.class);
        GetAllStavke gak = new GetAllStavke(repository);
        
        given(repository.getAll(k)).willReturn(List.of(k,k2));
       
        gak.executeOperation(k);
       
        verify(repository,times(1)).getAll(k);
      
    }
    
}
