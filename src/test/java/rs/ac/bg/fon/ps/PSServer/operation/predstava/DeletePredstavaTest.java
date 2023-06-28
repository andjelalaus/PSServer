/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
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
        Predstava p=new Predstava(1, "Lala", "Belgrade", LocalDateTime.MIN,100);
        assertDoesNotThrow(()->ak.preconditions(p));
    }
    @Test
     public void testPreconditionNazivNull() throws ValidatorException {
        Predstava p=new Predstava();
        //p.setNaziv("Napredno programiranje");
        p.setMesto("Belgrade");
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(Exception.class,()->ak.preconditions(p),"Naziv predstave ne sme biti prazan");
    }
     @Test
     public void testPreconditionMestoNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setNaziv("Napredno programiranje");
        //p.setMesto();
        p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(Exception.class,()->ak.preconditions(p),"Mesto predstave ne sme biti prazno");
    }
       @Test
     public void testPreconditionVremeNull() throws ValidatorException {
        Predstava p=new Predstava();
        p.setNaziv("Napredno programiranje");
        p.setMesto("Na lepom plavom dunavu");
        //p.setVreme(LocalDateTime.MIN);
        p.setKapacitet(100);
        
        assertThrows(Exception.class,()->ak.preconditions(p),"Vreme predstave ne sme biti prazno");
    }
    
}
