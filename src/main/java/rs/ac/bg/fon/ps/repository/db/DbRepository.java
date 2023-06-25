/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import rs.ac.bg.fon.ps.repository.Repository;

/**
 * Interfejs koji nasledjuje interfejs Repository
 * definise metode za konekciju,raskidanje veze sa bazom podataka, potvrdjivanje i povlacenje transakcije nad bazom podataka.
 * @author Andjy
 * @param <T> predstavlja genericki objekat nad kojim se vrse crud operacije
 */
public interface DbRepository<T>  extends Repository<T>{
    /**
     * metoda koja preko DBConnectionFactory uspostavlja konekciju sa bazom podatka
     * @throws Exception ukoliko konekcija nije uspostavljena baca gresku
     */
    default public void connect() throws Exception{
        DbConnectionFactory.getInstance().getConnection();
    }
     /**
     * metoda koja preko DBConnectionFactory zatvara uspostavljenu konekciju sa bazom podatka
     * @throws Exception ukoliko konekcija nije zatvorena baca gresku
     */
    default public void disconnect() throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
     /**
     * metoda koja preko DBConnectionFactory uspostavlja konekciju sa bazom podatka i potvrdjuje transakciju
     * @throws Exception ukoliko konekcija nije uspostavljena baca gresku ili ukoliko nije moguce potvrditi transakciju
     */
    default public void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
     /**
     * metoda koja preko DBConnectionFactory uspostavlja konekciju sa bazom podatka i odbacuje transakciju
     * @throws Exception ukoliko konekcija nije uspostavljena baca gresku ili ukoliko nije odbacena transakcija
     */
    default public void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
