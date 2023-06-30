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
        Predstava k=new Predstava(1, "Here", "Here",ld,20);
        Predstava k2=new Predstava(2, "There", "There",ld,200);
        
        
        Repository repository = mock(Repository.class);
        GetAllPredstava gak = new GetAllPredstava(repository);
        
        given(repository.getAll(k)).willReturn(List.of(k,k2));
       
        gak.executeOperation(k);
       
        verify(repository,times(1)).getAll(k);
      
    }
    
}
