/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.PSServer.controller;


import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.PlanGledanja;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSCommon.domain.User;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.operation.karte.AddKarta;
import rs.ac.bg.fon.ps.PSServer.operation.karte.DeleteKarta;
import rs.ac.bg.fon.ps.PSServer.operation.karte.GetAllKarte;
import rs.ac.bg.fon.ps.PSServer.operation.klijent.AddKlijent;
import rs.ac.bg.fon.ps.PSServer.operation.klijent.GetAllClients;
import rs.ac.bg.fon.ps.PSServer.operation.plan.AddPlan;
import rs.ac.bg.fon.ps.PSServer.operation.plan.DeletePlan;
import rs.ac.bg.fon.ps.PSServer.operation.plan.GetAllPlan;
import rs.ac.bg.fon.ps.PSServer.operation.predstava.AddPredstava;
import rs.ac.bg.fon.ps.PSServer.operation.predstava.DeletePredstava;
import rs.ac.bg.fon.ps.PSServer.operation.predstava.GetAllPredstava;
import rs.ac.bg.fon.ps.PSServer.operation.rezervacija.AddRezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.rezervacija.DeleteRezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.rezervacija.GetAllRezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.rezervacija.UpdateRezervacija;
import rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije.AddStavka;
import rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije.DeleteStavka;
import rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije.GetAllStavke;
import rs.ac.bg.fon.ps.PSServer.operation.stavkerezervacije.UpdateStavka;
import rs.ac.bg.fon.ps.PSServer.operation.user.Login;
import rs.ac.bg.fon.ps.PSServer.repository.db.impl.RepositoryDBGeneric;


/**
  * Ova klasa predstavlja klasu kontrolera serverske aplikacije
  * Kontroler se koristi za upravljanje različitim operacijama nad objektima u sistemu.
  * Klasa sadrži metode za dodavanje, ažuriranje, brisanje i vracanje objekata iz sistema.
  * Svaka metoda je specifična za određeni tip objekta i koristi odgovarajuću operaciju.
  * 
  * @author andjelalaus
  * @since 1.0.0.
  */
public class Controller {

 
    /**
    * parametar klase AbstractGenericOperation koja predstavlja apstraktnu klasu sistemskih operacija
    * u ovaj parametar se setuju klase koje implementiraju datu apstraktnu klasu
    */
    private AbstractGenericOperation operation;
    
    /**
    * Jedinstvena instanca kontrolera.
    */
    private static Controller controller;
    
    /**
    * Privatni konstruktor klase Controller.
    * Koristi se za sprečavanje direktnog instanciranja kontrolera.
    */
    private Controller() {
      
    }

    /**
     * Metoda koja vraća instancu kontrolera.
     * ako je null vrednost objekta kontrolera stvara novu instancu
     * ako nije samo vraca vec postojecu instancu
     * @return instanca kontrolera
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

 
      /**
     * Metoda koja vrši selekciju za ažuriranje objekta.
     * Proverava instancu da li je od klase rezervacija ili stavka rezervacije
     * ukoliko je instanca klase rezervacija poziva metodu updateRezervacija
     * ukoliko je instanca StavkaRezervacije poziva metodu updateStavka
     * @param object objekat koji se ažurira
     * @return true ako je ažuriranje uspešno, false ako se operacija nije dobro izvrsila ili ukoliko objekat nije ni instanca klase rezervacija ni stavka rezervacije
     * @throws Exception u slučaju greške prilikom ažuriranja
     * 
     */
    public boolean update(Object object) throws Exception{
        if(object instanceof Rezervacija) return updateRezervacija(object);
        if(object instanceof StavkaRezervacije) return updateStavka(object);
        
        return false;
    }
    /**
     * Metoda koja vrši selekciju za dodavanje objekta.
     * Proverava instancu da li je od klase predstava,klijent,karta,plangledanja ili stavka rezervacije
     * ukoliko je instanca klase predstava poziva metodu addPredstava
     * ukoliko je instanca klase klijent poziva metodu addKlijent
     * ukoliko je instanca Karte poziva metodu addKarta
     * ukoliko je instanca PlanGledanja poziva metodu addPlanGledanja
     * ukoliko je instanca StavkaRezervacije poziva metodu addStavka
     * @param object objekat koji se dodaje
     * @return true ako je dodavanje uspešno, false ako se operacija nije dobro izvrsila ili ukoliko objekat nije instanca navedenih klasa koje smeju
     * @throws Exception u slučaju greške prilikom dodavanja
     * 
     */
    public boolean add(Object object) throws Exception {
        if(object instanceof Predstava) return addPredstava(object);
        //if(object instanceof Rezervacija) return addRezervacija(object);
        if(object instanceof Klijent) return addKlijent(object);
        if(object instanceof Karta) return addKarta(object);
        if(object instanceof PlanGledanja) return addPlanGledanja(object);
        if(object instanceof StavkaRezervacije) return addStavka(object);
        return false;
    }
    /**
     * Metoda koja vrši selekciju za dodavanje rezervacije i vraća generisani identifikator rezervacije.
     * Vrsi proveru da li je prosledjen objekat instanca klase rezervacija
     * @param object rezervacija koja se dodaje
     * @return generisani identifikator rezervacije ukoliko je prosledjen dobar objekat u suprotnom null vraca
     * @throws Exception u slučaju greške prilikom dodavanja rezervacije
     */
    public Integer addR(Object object) throws Exception{
        if(object instanceof Rezervacija) return addRezervacija(object);
        else return null;
    }

     /**
     * Metoda koja vrši selekciju za brisanje objekta.
     * Proverava instancu da li je od klase predstava,karta,plangledanja,rezervacija ili stavka rezervacije
     * ukoliko je instanca klase predstava poziva metodu deletePredstava
     * ukoliko je instanca klase rezervacija poziva metodu deleteRezervaciju
     * ukoliko je instanca Karte poziva metodu deleteKarta
     * ukoliko je instanca PlanGledanja poziva metodu deletePlan
     * ukoliko je instanca StavkaRezervacije poziva metodu deleteStavka
     * @param object objekat koji se briše
     * @return true ako je brisanje uspešno, false ako se operacija nije dobro izvrsila ili ukoliko objekat nije ni instanca klasa navedenih da smeju
     * @throws Exception u slučaju greške prilikom brisanja objekta
     */
    public boolean delete(Object object) throws Exception {
        if(object instanceof Predstava) return deletePredstava(object);
        if(object instanceof Karta) return deleteKarta(object);
        if(object instanceof Rezervacija) return deleteRezervaciju(object);
        if(object instanceof StavkaRezervacije) return deleteStavka(object);
        if(object instanceof PlanGledanja) return deletePlan(object);
        
        return false;
    }

     /**
     * Metoda koja vrsi selekciju za pozivanje drugih metoda koje rade : Vraca sve objekte određenog tipa iz sistema.
     * Proverava instancu da li je od klase predstava,karta,plangledanja,klijent,rezervacija ili stavka rezervacije
     * ukoliko je instanca klase predstava poziva metodu getPredstava
     * ukoliko je instanca klase rezervacija poziva metodu getRezervacija
     * ukoliko je instanca Karte poziva metodu getKarta
     * ukoliko je instanca PlanGledanja poziva metodu getPlan
     * ukoliko je instanca StavkaRezervacije poziva metodu getStavkaRezervacije
     * ukoliko je instanca Klijenta poziva metodu getKlijent
     * @param object objekt koji predstavlja instancu klase cije objekte zelimo da vratimo iz baze
     * @return lista objekata ili null ako se dogodilo da objekat nije instanca zadatih klasa
     * @throws Exception u slučaju greške prilikom vracanja liste objekata
     */
    public Object getAll(Object object) throws Exception {
        if(object instanceof Rezervacija) return getRezervacija(object);
        if(object instanceof StavkaRezervacije) return getStavkaRezervacije(object);
        if(object instanceof Predstava) return getPredstava(object);
        if(object instanceof Karta) return getKarta(object);
        if(object instanceof Klijent) return getKlijent(object);
        if(object instanceof PlanGledanja) return getPlan(object);
        
        return null;
    }

      /**
     * Vrši pozivanje metoda koja vrsi prijavu korisnika u sistem.
     * setuje parametar operation da je instanca klase Login
     * poziva execute operaciju objekta klase Login
     * @param user korisnik koji se prijavljuje
     * @return rezultat prijave korisnika ili null ako prijava nije uspešna
     * @throws Exception u slučaju greške prilikom prijave korisnika
     */
    public Object login(User user) throws Exception {
        operation = new Login();
        operation.execute(user);
        return ((Login)operation).getLogin();
    }

     /**
     * Vrši pozivanje metoda koja vrsi: Ažurira rezervaciju u sistemu.
     * setuje parametar operation na instancu klase UpdateRezervacija
     * poziva execute operaciju objekta klase UpdateRezervacija
     * @param argument objekat rezervacije koju želimo ažurirati
     * @return true ako je ažuriranje uspešno, false ako nije
     * @throws Exception u slučaju greške prilikom ažuriranja
     */
    private boolean updateRezervacija(Object argument) throws Exception {
        operation = new UpdateRezervacija();
        operation.execute(argument);
        return ((UpdateRezervacija)operation).confirm();
    }

     /**
     * Vrši pozivanje metoda koja vrsi: Dodaje rezervaciju u sistem.
     * setuje parametar operation na instancu klase AddRezervacija
     * poziva execute operaciju objekta klase AddRezervacija
     * @param argument objekat rezervacije koji se dodaje
     * @return identifikator dodane rezervacije
     * @throws Exception u slučaju greške prilikom dodavanja
     */
    private Integer addRezervacija(Object argument) throws Exception {
        operation = new AddRezervacija();
        operation.execute(argument);
        return ((AddRezervacija)operation).confirm();
    }
     /**
     * Vrši pozivanje metoda koja vrsi: Dodaje kartu u sistem.
     * setuje parametar operation na instancu klase AddKarta
     * poziva execute operaciju objekta klase AddKarta
     * @param argument objekat karte koji se dodaje
     * @return true ako je dodavanje uspešno, false ako nije
     * @throws Exception u slučaju greške prilikom dodavanja
     */
      private boolean addKarta(Object argument) throws Exception {
        
        operation = new AddKarta(new RepositoryDBGeneric());
        operation.execute(argument);
        return ((AddKarta)operation).confirm();
    }
     /**
     * Vrši pozivanje metoda koja vrsi: Dodaje klijenta u sistem.
     * setuje parametar operation na instancu klase AddKlijent
     * poziva execute operaciju objekta klase AddKlijent
     * @param argument objekat klijenta koji se dodaje
     * @return true ako je dodavanje uspešno, false ako nije
     * @throws Exception u slučaju greške prilikom dodavanja
     */
    private boolean addKlijent(Object argument) throws Exception {
        operation = new AddKlijent();
        operation.execute(argument);
        return ((AddKlijent)operation).confirm();
    }
     /**
     * Vrši pozivanje metoda koja vrsi: Dodaje plan gledanja u sistem.
     * setuje parametar operation na instancu klase AddPlan
     * poziva execute operaciju objekta klase AddPlan
     * @param argument objekat plana gledanja koji se dodaje
     * @return true ako je dodavanje uspešno, false ako nije
     * @throws Exception u slučaju greške prilikom dodavanja
     */
     private boolean addPlanGledanja(Object argument) throws Exception {
        operation = new AddPlan();
        operation.execute(argument);
        return ((AddPlan)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja briše predstavu iz sistema.
    * Postavlja parametar operacije na instancu klase DeletePredstava.
    * Poziva execute operaciju objekta klase DeletePredstava.
    *
    * @param argument objekat predstave koja se briše
    * @return true ako je brisanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom brisanja
    */ 
    private boolean deletePredstava(Object argument) throws Exception {
        operation = new DeletePredstava();
        operation.execute(argument);
        return ((DeletePredstava)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja briše kartu iz sistema.
    * Postavlja parametar operacije na instancu klase DeleteKarta.
    * Poziva execute operaciju objekta klase DeleteKarta.
    *
    * @param argument objekat karte koja se briše
    * @return true ako je brisanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom brisanja
    */
    private boolean deleteKarta(Object argument) throws Exception {
        operation = new DeleteKarta(new RepositoryDBGeneric());
        operation.execute(argument);
        return ((DeleteKarta)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja briše rezervaciju iz sistema.
    * Postavlja parametar operacije na instancu klase DeleteRezervacija.
    * Poziva execute operaciju objekta klase DeleteRezervacija.
    *
    * @param argument objekat rezervacije koja se briše
    * @return true ako je brisanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom brisanja
    */
    private boolean deleteRezervaciju(Object argument) throws Exception {
        operation = new DeleteRezervacija();
        operation.execute(argument);
        return ((DeleteRezervacija)operation).confirm();
    }

    
    /**
     * Vrši pozivanje metoda koja vrsi: Metoda koja vraca sve rezervacije iz sistema.
     * Postavlja parametar operacije na instancu klase GetAllRezervacija.
     * Poziva execute operaciju objekta klase GetAllRezervacija.
     *
     * @param argument objekat koji je instanca rezervacije
     * @return lista rezervacija iz sistema
     * @throws Exception u slučaju greške prilikom vracanja rezervacija
     */
    private Object getRezervacija(Object argument) throws Exception {
        operation = new GetAllRezervacija();
        operation.execute(argument);
        return ((GetAllRezervacija)operation).getRezervacije();
    }
    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja vraca sve stavke rezervacije iz sistema.
    * Postavlja parametar operacije na instancu klase GetAllStavke.
    * Poziva execute operaciju objekta klase GetAllStavke.
    *
    * @param argument objekat koji instanca stavke rezervacije
    * @return lista stavki rezervacije iz sistema
    * @throws Exception u slučaju greške prilikom vracanja stavki rezervacije
    */
    private Object getStavkaRezervacije(Object argument) throws Exception {
        operation = new GetAllStavke();
        operation.execute(argument);
        return ((GetAllStavke)operation).getStavke();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja vraca sve predstave iz sistema.
    * Postavlja parametar operacije na instancu klase GetAllPredstava.
    * Poziva execute operaciju objekta klase GetAllPredstava.
    *
    * @param argument objekat koji je instanca klase predstava
    * @return lista predstava iz sistema
    * @throws Exception u slučaju greške prilikom vracanja predstava
    */
    private Object getPredstava(Object argument) throws Exception {
        operation = new GetAllPredstava();
        operation.execute(argument);
        return ((GetAllPredstava)operation).getPredstave();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja vraca sve karte iz sistema.
    * Postavlja parametar operacije na instancu klase GetAllKarte.
    * Poziva execute operaciju objekta klase GetAllKarte.
    *
    * @param argument objekat koji je instanca karte
    * @return lista karata iz sistema
    * @throws Exception u slučaju greške prilikom vracanja karata
    */
    private Object getKarta(Object argument) throws Exception {
        operation = new GetAllKarte(new RepositoryDBGeneric());
        operation.execute(argument);
        return ((GetAllKarte)operation).getKarte();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja vraca sve klijente iz sistema.
    * Postavlja parametar operacije na instancu klase GetAllClients.
    * Poziva execute operaciju objekta klase GetAllClients.
    *
    * @param argument objekat koji je instanca klijenta
    * @return lista klijenata iz sistema
    * @throws Exception u slučaju greške prilikom vracanja klijenata
    */

    private Object getKlijent(Object argument) throws Exception {
        operation = new GetAllClients();
        operation.execute(argument);
        return ((GetAllClients)operation).getClients();
    }

   /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja dodaje predstavu u sistem.
    * Postavlja parametar operacije na instancu klase AddPredstava.
    * Poziva execute operaciju objekta klase AddPredstava.
    *
    * @param argument objekat predstave koja se dodaje
    * @return true ako je dodavanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom dodavanja predstave
    */
    private boolean addPredstava(Object argument) throws Exception {
        operation = new AddPredstava();
        operation.execute(argument);
        return ((AddPredstava)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja dodaje stavku u sistem.
    * Postavlja parametar operacije na instancu klase AddStavka.
    * Poziva execute operaciju objekta klase AddStavka.
    *
    * @param object objekat stavke koja se dodaje
    * @return true ako je dodavanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom dodavanja stavke
    */
    private boolean addStavka(Object object) throws Exception {
        operation = new AddStavka();
        operation.execute(object);
        return ((AddStavka)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja ažurira stavku u sistemu.
    * Postavlja parametar operacije na instancu klase UpdateStavka.
    * Poziva execute operaciju objekta klase UpdateStavka.
    *
    * @param object objekat stavke koja se ažurira
    * @return true ako je ažuriranje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom ažuriranja stavke
    */
    private boolean updateStavka(Object object) throws Exception {
        operation = new UpdateStavka();
        operation.execute(object);
        return ((UpdateStavka)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja briše stavku iz sistema.
    * Postavlja parametar operacije na instancu klase DeleteStavka.
    * Poziva execute operaciju objekta klase DeleteStavka.
    *
    * @param object objekat stavke koja se briše
    * @return true ako je brisanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom brisanja stavke
    */
    private boolean deleteStavka(Object object) throws Exception {
        operation = new DeleteStavka();
        operation.execute(object);
        return ((DeleteStavka)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja briše plan gledanja iz sistema.
    * Postavlja parametar operacije na instancu klase DeletePlan.
    * Poziva execute operaciju objekta klase DeletePlan.
    *
    * @param object objekat plana gledanja koji se briše
    * @return true ako je brisanje uspešno, false ako nije
    * @throws Exception u slučaju greške prilikom brisanja plana gledanja
    */
    private boolean deletePlan(Object object) throws Exception{
        operation = new DeletePlan();
        operation.execute(object);
        return ((DeletePlan)operation).confirm();
    }

    /**
    * Vrši pozivanje metoda koja vrsi: Metoda koja vraca sve planove gledanja iz sistema.
    * Postavlja parametar operacije na instancu klase GetAllPlan.
    * Poziva execute operaciju objekta klase GetAllPlan.
    *
    * @param object objekat koji je instanca plana gledanja
    * @return lista planova gledanja iz sistema
    * @throws Exception u slučaju greške prilikom vracanja planova gledanja
    */
    private Object getPlan(Object object) throws Exception{
        operation = new GetAllPlan();
        operation.execute(object);
        return ((GetAllPlan)operation).getPlan();
    }
}
