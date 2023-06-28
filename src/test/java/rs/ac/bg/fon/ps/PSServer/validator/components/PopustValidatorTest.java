/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.validator.components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSServer.validator.IValidatorTest;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class PopustValidatorTest extends IValidatorTest {
    
    
    
    @BeforeEach
    public void setUp() {
        ak=new PopustValidator();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     @Test
         public void testPreconditionsBrojNull() throws ValidatorException {
         String br=null;
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Polje je obavezno!");
    }
         @Test
       public void testPreconditionsBrojPrazno() throws ValidatorException {
       String br="";
        assertThrows(ValidatorException.class,()->ak.validate(br),"Polje je obavezno!");
    }
        @Test
    public void testPreconditionsBrojNijeBroj() throws ValidatorException {
         String br="aaa";
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Polje mora da sadrzi samo brojeve");
    }
    @Test
   public void testPreconditionsOK() throws ValidatorException {
         String br="12";
        
        assertDoesNotThrow(()->ak.validate(br));
    }
       @Test
    public void testPreconditionsManjiOd0() throws ValidatorException {
         String br="-1";
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Popust ne moze da bude manji od 0 i veci od 100");
    }
     @Test
    public void testPreconditionsVeciOd100() throws ValidatorException {
         String br="103";
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Popust ne moze da bude manji od 0 i veci od 100");
    }
    
}
