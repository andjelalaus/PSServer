/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.ps.PSCommon.domain.User;
import rs.ac.bg.fon.ps.PSServer.validator.ValidatorException;

/**
 *
 * @author andelalausevic
 */
public class LoginTest {
    
    Login ak;
    
    @BeforeEach
    public void setUp() {
        ak=new Login();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }

     @Test
    public void testPreconditions() {
        User u=new User("aaaa", "lalalala");
        assertDoesNotThrow(()->ak.preconditions(u));
    }
    @Test
     public void testPreconditionUsernameNull() throws ValidatorException {
       User u=new User();
       u.setPassword("lalala");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
     @Test
      public void testPreconditionsUsernamePrazno() throws ValidatorException {
         User u=new User("", "lalalala");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
         @Test
     public void testPreconditionPassNull() throws ValidatorException {
       User u=new User();
       u.setUsername("lalala");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
     @Test
      public void testPreconditionsPassPrazno() throws ValidatorException {
         User u=new User("allalal", "");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
    
}
