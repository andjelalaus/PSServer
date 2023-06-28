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
public class OpstiValidatorTest extends IValidatorTest{
    
   
    @BeforeEach
    public void setUp() {
        ak=new OpstiValidator();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     public void testPreconditionsBrojNull() throws ValidatorException {
         String br=null;
        
        assertThrows(ValidatorException.class,()->ak.validate(br));
    }
         @Test
       public void testPreconditionsBrojPrazno() throws ValidatorException {
       String br="";
        assertThrows(ValidatorException.class,()->ak.validate(br));
    }
       @Test
   public void testPreconditionsOK() throws ValidatorException {
         String br="123";
        
        assertDoesNotThrow(()->ak.validate(br));
    }
    
}
