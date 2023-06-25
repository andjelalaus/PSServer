/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Klasa za uspostavljanje konekcije sa bazom podataka
 * @author Andjy
 */
public class DbConnectionFactory {

    /**
     *  conncection je objekat klase Connection koji se setuje preko DriverManagera-a
     */
    private Connection connection;
    /**
     * instanca date klase
     */
    private static DbConnectionFactory instance;

    /**
     * prazan konstruktor date klase
     */
    private DbConnectionFactory() {
    }
/**
 * Metoda koja pravi instancu ukoliko je data null a ako nije vraca vec postojecu instancu
 * @return instance koja predstvalja instacnu date klase
 */
    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }
/**
 * Metoda koja vraca konekciju sa bazom podataka.
 * Ukoliko konekcija nije setovana ili je zatvorena
 * preko klase Properties ucitace se fajl koji se nalazi u config/dbconfig.properties
 * uzece se url username i password i preko klase DriverManager ce se uspostaviti konekcija
 * auto commit se postavlja na false
 * @return konekciju
 * @throws Exception ukoliko dodje do greske prilikom uspostavljanja konekcije
 */
    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
