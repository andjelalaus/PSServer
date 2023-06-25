/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.stavkerezervacije;

import java.util.List;
import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 * Konkretna klasa za vracanje svih stavki rezervacija.
 * Nasleđuje apstraktnu klasu AbstractGenericOperation.
 * Implementira metode za proveru preduslova, izvrsenje i potvrdu izvrsenja transakcije.
 * @author andjelalaus
 */
public class GetAllStavke extends AbstractGenericOperation {
    /**
     * Lista stavki rezervacije koja nije inicijalizovana
     */
    private List<StavkaRezervacije> stavke;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        stavke = repository.getAll((StavkaRezervacije) param);
    }
/**
 * Metoda koja vraca listu stavki rezervacija
 * @return stavke koja predstavlja listu stavki
 */
    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }
}
