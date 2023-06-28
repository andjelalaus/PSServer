/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSServer.repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author andelalausevic
 */
public class GetAllKarteTest {
     @Mock
    RepositoryDBGeneric repo;   
    GetAllKarte ak;
    
   
    @BeforeEach
    public void setUp() {
        ak=new GetAllKarte();
    }
    
    @AfterEach
    public void tearDown() {
        ak=null;
    }
    /*
    @Test
    public void testExecuteOperation() throws Exception{
        Rezervacija r=new Rezervacija(1,1, new Klijent(1, "Andja", "Laus", "aa@gg.com", "redovan"));
        LocalDateTime ld=LocalDateTime.of(2023, Month.MARCH, 10, 20, 0);
        StavkaRezervacije st=new StavkaRezervacije(1, 10, 1, true, r, new Predstava(1, "Here", "Here",ld,20));
        Karta k=new Karta(1, 2, r, st);
        Karta k2=new Karta(2,3,r,st);
        given(ak.getKarte()).willReturn(List.of(k,k2));
       given(repo.getAll(k)).willReturn(List.of(k,k2));
       
        ak.executeOperation(k);
        List<Karta> karte=ak.getKarte();
        
        
           assertThat(karte).isNotNull();
           assertThat(karte.size()).isEqualTo(2);
        verify(repo,times(1)).getAll(k);
        verify(ak,times(1)).getKarte();
      
    }
*/
   
    
}
