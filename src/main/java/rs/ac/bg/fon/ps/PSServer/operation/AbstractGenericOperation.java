/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.PSServer.operation;

import rs.ac.bg.fon.ps.PSServer.repository.Repository;
import rs.ac.bg.fon.ps.PSServer.repository.db.DbRepository;
import rs.ac.bg.fon.ps.PSServer.repository.db.impl.RepositoryDBGeneric;

/**
 *
 * Klasa koja se zove Apstraktna generička operacija jer
 * Definiše šablon za izvršavanje operacije u sistemskom okviru.
 * @author andjelalaus
 */
public abstract class AbstractGenericOperation {

    /**
     * objekat klase Repository imena repository
     */
    protected final Repository repository;

    /**
     * Konstruktor klase AbstractGenericOperation.
     * Inicijalizuje repository objekat sa instancom klase RepositoryDBGeneric.
     */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    /**
     * Izvršava generičku operaciju.
     * Sadrži logiku izvršavanja operacije u okviru transakcije.
     * Poziva preconditions, startTransaction, executeOperation, commitTransaction metode.
     * U slučaju greške, vrši rollback transakcije i baca Exception.
     * Nakon izvršavanja operacije, prekida konekciju sa bazom podataka.
     *
     * @param param parametar operacije
     * @throws Exception u slučaju greške prilikom izvršavanja operacije
     */
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    /**
     * Metoda preconditions.
     * Definiše preduslove operacije koje treba proveriti pre njenog izvršavanja.
     * cena karte mora biti broj, klijent mora imati mejl u formi mejla i ne sme biti prazan ili null, ime, prezime, status klijenta mora biti tekst i ne vece od 100 karaktera i ne sme biti null
     * cena planagledanja mora biti izmeju 1 i 10 zakljucno sa tim brojevima ne sme biti null, 
     * karta mora imati rezervaciju i stavku rezervacije
     * predstava mora imati naziv i ime mesta koji moraju biti tekst i ne smeju biti null, ne smeju biti preko 100 karaktera
     * predstava mora imati kapacitet u vidu broja ne sme biti null, vreme predstave ne sme biti null
     * rezervacija mora imati broj predstavi koja mora biti u formi broja ne sme biti null sto oznacava isto sto i broj sedista
     * rezervacija mora imati parametar klijenta ne sme biti null
     * stavka rezervacije mora imati id predstave i rezervacije, popust koji mora biti broj od 0 do 100 uzimajuci i te brojeve
     * stavka rezervacije mora imati broj sedista koji mora biti broj
     * nijedan broj ne sme biti negativan
     * username i password ne smeju biti null i moraju biti u formi teksta kod login-a
     * @param param parametar operacije
     * @throws Exception u slučaju da preduslovi nisu ispunjeni sa objasnjenjem zasto nije ispunjen
     */
    protected abstract void preconditions(Object param) throws Exception;

    /**
    * Pokreće novu transakciju povezivanjem sa bazom podataka.
    * poziva nad objektom repository connect
    * @throws Exception u slučaju greške prilikom povezivanja sa bazom podataka
    */
    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

     /**
     * Metoda executeOperation.
     * Definiše konkretne korake izvršavanja operacije.
     * dodaje, brise kartu,vraca sve karte
     * dodaje, vraca sve klijente
     * dodaje,brise i vraca sve planove gledanja
     * dodaje,brise,vraca,azurira sve rezervacije
     * dodaje,brise,vraca i auzurira sve stavke rezervacije
     * vrsi login, koji ce ukoliko su parametri jednaki admin admin ulogovati korisnika
     * @param param parametar operacije
     * @throws Exception u slučaju greške prilikom izvršavanja operacije
     */
    protected abstract void executeOperation(Object param) throws Exception;

    
    /**
    * Commituje trenutnu transakciju.
    * poziva nad objektom repository commit
    * @throws Exception u slučaju greške prilikom commitovanja transakcije
    */
    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    /**
    * Vrši rollback trenutne transakcije.
    * poziva nad objektom repository rollback
    * @throws Exception u slučaju greške prilikom rollbacka transakcije
    */
    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    /**
    * Prekida konekciju sa bazom podataka.
     poziva nad objektom repository disconnect
    * @throws Exception u slučaju greške prilikom prekida konekcije
    */
    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
