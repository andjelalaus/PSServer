/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.klijent;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 *
 * @author andelalausevic
 */
public class GetAllClientsTest {
    
   GetAllClients ga;
    
    @BeforeEach
    public void setUp() {
        ga=new GetAllClients();
    }
    
    @AfterEach
    public void tearDown() {
        ga=null;
    }

    @Test
    public void testExecuteOperation() throws Exception{
        Klijent k=new Klijent();
        k.setKlijentId(1);
        k.setIme("Andja");
        k.setEmail("aa@gg.com");
        k.setPrezime("Laus");
        k.setStatus("redovan");
        
        Klijent k2=new Klijent();
        k2.setKlijentId(2);
        k2.setIme("Stefan");
        k2.setEmail("dd@ss.com");
        k2.setPrezime("Dobras");
        k2.setStatus("student");
        
        Repository repository = mock(Repository.class);
        GetAllClients gak = new GetAllClients(repository);
        
        given(repository.getAll(k)).willReturn(List.of(k,k2));
       
        gak.executeOperation(k);
       
        verify(repository,times(1)).getAll(k);
      
    }
    
}
