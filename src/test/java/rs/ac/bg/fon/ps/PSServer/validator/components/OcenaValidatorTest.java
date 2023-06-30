/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.validator.components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSServer.validator.IValidatorTest;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class OcenaValidatorTest extends IValidatorTest{
    
    
    
    @BeforeEach
    public void setUp() {
        ak=new OcenaValidator();
                
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }
    
    
    @Test
   public void testPreconditionsOK() throws ValidatorException {
         String br="10";
        
        assertDoesNotThrow(()->ak.validate(br));
    }
       @Test
    public void testPreconditionsManjiOd0() throws ValidatorException {
         String br="-1";
        
        assertThrows(ValidatorException.class,()->ak.validate(br));
    }
     @Test
    public void testPreconditionsVeciOd10() throws ValidatorException {
         String br="11";
        
        assertThrows(ValidatorException.class,()->ak.validate(br));
    }

   
    
}
