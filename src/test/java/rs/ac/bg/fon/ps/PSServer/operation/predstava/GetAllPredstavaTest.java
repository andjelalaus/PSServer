/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

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
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 *
 * @author andelalausevic
 */
public class GetAllPredstavaTest {
    
   GetAllPredstava ga;
    @BeforeEach
    public void setUp() {
        ga=new GetAllPredstava();
    }
    
    @AfterEach
    public void tearDown() {
        ga=null;
    }

      @Test
    public void testExecuteOperation() throws Exception{
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        Predstava p=new Predstava();
        p.setNaziv("Faust");
        p.setMesto("Belgrade");
        p.setVreme(ld);
        p.setKapacitet(100);
        
        Predstava p2=new Predstava();
        p2.setNaziv("Ko to tamo peva");
        p2.setMesto("Zrenjanin");
        p2.setVreme(ld);
        p2.setKapacitet(100);
        
        Repository repository = mock(Repository.class);
        GetAllPredstava gak = new GetAllPredstava(repository);
        
        given(repository.getAll(p)).willReturn(List.of(p,p2));
       
        gak.executeOperation(p);
       
        verify(repository,times(1)).getAll(p);
      
    }
    
}
