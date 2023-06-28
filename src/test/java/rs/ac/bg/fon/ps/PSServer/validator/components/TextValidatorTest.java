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
public class TextValidatorTest extends IValidatorTest{
    
    
    
    @BeforeEach
    public void setUp() {
        ak=new TextValidator();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

    public void testPreconditionsTextNull() throws ValidatorException {
         String br=null;
        
        assertThrows(ValidatorException.class,()->ak.validate(br));
    }
         @Test
       public void testPreconditionsTextPrazno() throws ValidatorException {
       String br="";
        assertThrows(ValidatorException.class,()->ak.validate(br));
    }
       @Test
   public void testPreconditionsTextBroj() throws ValidatorException {
         String br="123";
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Polje mora da sadrzi samo slova");
    }
    @Test
   public void testPreconditionsTextZnak() throws ValidatorException {
         String br="#$&";
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Polje mora da sadrzi samo slova");
    }
    @Test
   public void testPreconditionsPreko100() throws ValidatorException {
         String br="Brzo smeškaći mali pas skače preko velikih žbunova i veselo mlatara repom dok trči livadom punom šarenih cvetova.";
        
        assertThrows(ValidatorException.class,()->ak.validate(br),"Polje moze da sadrzi maksimalno 100 znakova");
    }
   @Test
   public void testPreconditionsOk() throws ValidatorException {
         String br="Brzosmeka";
        
        assertDoesNotThrow(()->ak.validate(br));
    }
    
}
