/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.karte;

import java.util.List;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;

/**
 * Klasa za vracanje svih karata koje postoje u bazi podataka.
 * Nasledjuje apstraktnu klasu AbstractGenericOperation i implementira njene metode.
 * @author andjelalaus
 */
public class GetAllKarte extends AbstractGenericOperation {
     /**
     * Lista karata koja ce se napuniti operacijom pretrage svih karata.
     */
        private List<Karta> karte;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        karte = repository.getAll((Karta) param);
    }

    /**
    * Vraća listu karata dobijenih operacijom pretrage svih karata.
    *
    * @return lista karata
    */
    public List<Karta> getKarte() {
        return karte;
    }
}
