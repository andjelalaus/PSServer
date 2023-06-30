/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.validator.components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rs.ac.bg.fon.ps.PSServer.validator.IValidatorTest;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class EmailValidatorTest extends IValidatorTest{
    

    @BeforeEach
    public void setUp() {
        ak=new EmailValidator();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     @Test
         public void testPreconditionsMejlPrazno() throws ValidatorException {
         String mejl=null;
        
        assertThrows(ValidatorException.class,()->ak.validate(mejl));
    }
         @Test
       public void testPreconditionsMejlNull() throws ValidatorException {
       String mejl="";
        assertThrows(ValidatorException.class,()->ak.validate(mejl));
    }
        @ParameterizedTest
    @CsvSource ({
		"aaaaaa",
		"aa@",
                "@aaa",
                "123",
                "@aa.c"
	})
    public void testPreconditionsMejlNijeMejl(String em) throws ValidatorException {
         String email=em;
        
        assertThrows(ValidatorException.class,()->ak.validate(email));
    }
   public void testPreconditionsOK() throws ValidatorException {
         String email="an@an.c";
        
        assertDoesNotThrow(()->ak.validate(email));
    }
   
    
}
