/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        User u=new User();
        u.setUsername("marko");
        u.setPassword("marko123");
        assertDoesNotThrow(()->ak.preconditions(u));
    }
    @Test
     public void testPreconditionUsernameNull() throws ValidatorException {
       User u=new User();
       u.setPassword("pass12345");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
     @Test
      public void testPreconditionsUsernamePrazno() throws ValidatorException {
          //preko konstruktora setovane vrednosti jer bi setter uhvatio gresku
          //ovde hocemo da vidimo da li ce precondition da uhvati izuzetak
         User u=new User("", "1234567");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
         @Test
     public void testPreconditionPassNull() throws ValidatorException {
       User u=new User();
       u.setUsername("admin");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
     @Test
      public void testPreconditionsPassPrazno() throws ValidatorException {
          //preko konstruktora setovane vrednosti jer bi setter uhvatio gresku
          //ovde hocemo da vidimo da li ce precondition da uhvati izuzetak
         User u=new User("admin", "");
        
        assertThrows(ValidatorException.class,()->ak.preconditions(u));
    }
      @Test
      public void testexcecute() throws Exception{
           User u=new User();
           u.setUsername("admin");
           u.setPassword("admin");
           ak.executeOperation(u);
           assertEquals(u,ak.getLogin());
      }
       @Test
      public void testexcecuteError() throws Exception{
           User u=new User();
           u.setUsername("admin1");
           u.setPassword("admin1");
           ak.executeOperation(u);
           assertNotEquals(u,ak.getLogin());
      }
    
}
