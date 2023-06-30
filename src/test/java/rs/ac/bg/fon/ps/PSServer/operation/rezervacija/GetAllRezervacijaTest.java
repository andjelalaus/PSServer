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
        Rezervacija k=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        Rezervacija k2=new Rezervacija(2,2, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
       
        
        Repository repository = mock(Repository.class);
        GetAllRezervacija gak = new GetAllRezervacija(repository);
        
        given(repository.getAll(k)).willReturn(List.of(k,k2));
       
        gak.executeOperation(k);
       
        verify(repository,times(1)).getAll(k);
      
    }
    
}
