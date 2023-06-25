package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 * Interfejs koji sadrzi CRUD operacije koje treba da se izvrse sa bazom podataka.
 * Operacije koje su napisane su getAll,add,addReturnKey,edit i delte.
 * @author Andjy
 * @param <T> koji predstavlja genericki objekat na kojem se vrse crud operacije
 */
public interface Repository<T> {
      /**
    * Vrši vracanje podataka u bazi podataka koristeći pruženi GenericEntity objekat.
    * Pravi se nova lista generickog objekta koja se inicijalizuje al je prazna
    * Uspostavlja se konekcija preko DBConnectionFactori-ja
    * Preko string buildera se pise sql upit za select gde se od prosledjenog entiteta uzimaju ime tabele,vrednosti koje se ubacuju
    * Pravi se statement koji se izvrsava i ubacuje se u listu dokle god ima objekata i zatim se lista vraca
    * @param param GenericEntity objekat koji treba pronaci i vratiti sve takve objekte iz baze podataka.
    * @return listu generickih objekata, ukoliko nije uspesna operacija vratice praznu listu 
    * @throws Exception ako dođe do greške tokom operacije vracanja objekata.
    */
    List<T> getAll(T param) throws Exception;
     /**
    * Vrši dodavanja objekta u bazu podataka koristeći pruženi GenericEntity objekat koji se treba ubaciti.
    * Uspostavlja se konekcija preko DBConnectionFactori-ja
    * Preko string buildera se pise sql upit za insert into gde se od prosledjenog entiteta uzimaju ime tabele,imena kolona za insert, vrednosti koje se ubacuju
    * Pravi se statement koji se izvrsava, setuju se id za svaki dodari objekat i zatim zatvara statement
    * @param param GenericEntity objekat koji treba ubaciti  u bazu podataka.
    * @return True ukoliko je operacija dodabvanja uspešna, false u suprotnom.
    * @throws Exception ako dođe do greške tokom operacije dodavanja.
    */
    boolean add(T param) throws Exception;
     /**
    * Vrši dodavanja objekta u bazu podataka koristeći pruženi GenericEntity objekat koji se treba ubaciti.
    * Uspostavlja se konekcija preko DBConnectionFactori-ja
    * Preko string buildera se pise sql upit za insert into gde se od prosledjenog entiteta uzimaju ime tabele,imena kolona za insert, vrednosti koje se ubacuju
    * Pravi se statement koji se izvrsava, setuju se id za svaki dodari objekat i zatim zatvara statement
    * @param param GenericEntity objekat koji treba ubaciti  u bazu podataka.
    * @return id od dodatog objekta
    * @throws Exception ako dođe do greške tokom operacije dodavanja.
    */
    Integer addReturnKey(T param) throws Exception;
    /**
    * Vrši izmenu zapisa u bazi podataka koristeći pruženi GenericEntity objekat.
    * Uspostavlja se konekcija preko DBConnectionFactori-ja
    * Preko string buildera se pise sql upit za update gde se od prosledjenog entiteta uzimaju ime tabele,vrednosti koje se menjaju i uslovi
    * Pravi se statement koji se izvrsava i zatim zatvara
    * @param param GenericEntity objekat koji treba izmeniti i ažurirati u bazi podataka.
    * @return True ukoliko je operacija izmene uspešna, false u suprotnom.
    * @throws Exception ako dođe do greške tokom operacije izmene.
    */
    boolean edit(T param) throws Exception;
     /**
    * Vrši brisanje zapisa u bazi podataka koristeći pruženi GenericEntity objekat.
    * Uspostavlja se konekcija preko DBConnectionFactori-ja
    * Preko string buildera se pise sql upit za delete gde se od prosledjenog entiteta uzimaju ime tabele i uslovi
    * Pravi se statement koji se izvrsava i zatim zatvara
    * @param param GenericEntity objekat koji treba izbrisati iz baze podataka.
    * @return True ukoliko je operacija brisanja uspešna, false u suprotnom.
    * @throws Exception ako dođe do greške tokom operacije brisanja.
    */
    boolean delete(T param)throws Exception;
    //List<T> getAll();
}
