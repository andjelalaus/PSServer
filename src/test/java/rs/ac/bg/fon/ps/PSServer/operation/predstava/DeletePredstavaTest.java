/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class DeletePredstavaTest {
    
    DeletePredstava ak;
    
    @BeforeEach
    public void setUp() {
        ak=new DeletePredstava();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

    @Test
    public void testPreconditions() {
        Predstava p=new Predstava();
        p.setNaziv("Faust");
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        assertDoesNotThrow(()->ak.preconditions(p));
    }
    @Test
     public void testPreconditionNazivNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(Exception.class,()->ak.preconditions(p),"Naziv predstave ne sme biti prazan");
    }
     @Test
     public void testPreconditionMestoNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setNaziv("Ubistvo u Orjent ekspresu");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(Exception.class,()->ak.preconditions(p),"Mesto predstave ne sme biti prazno");
    }
       @Test
     public void testPreconditionVremeNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setNaziv("Ubistvo na Nilu");
        p.setMesto("Novi Sad");
        p.setKapacitet(100);
        
        assertThrows(Exception.class,()->ak.preconditions(p),"Vreme predstave ne sme biti prazno");
    }
      @Test
    public void testExecuteOperation() throws Exception{
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        Predstava p=new Predstava();
        p.setNaziv("Faust");
        p.setMesto("Belgrade");
        p.setVreme(ld);
        p.setKapacitet(100);
        
        Repository repository = mock(Repository.class);
        DeletePredstava dk = new DeletePredstava(repository);
        
        given(repository.delete(p)).willReturn(Boolean.TRUE);
        
        dk.executeOperation(p);
        
        verify(repository,times(1)).delete(p);
    }
    
}
