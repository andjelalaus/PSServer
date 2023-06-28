/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.User;

    /**
     *Model tabele koji se koristi za prikazivanje podataka o korisnicima.
     * sadrzi listu korisnika i naziv kolona
     * moguce je dodati usera, staviti da celiju nije moguce mnejati i postavljati vrednosti u polja
     * @author andjelalaus
     */
public class UserTableModel extends AbstractTableModel{

    /**
     * niz stringova koji predstavlja imena kolona i inicijalizovan da ima za sada ime kolone username
     */
    String[] columnNames = new String[]{"username"};
    /**
     * lista korisnika imena users
     */
    List<User> users;

    /**
     * Konstruktor klase UserTableModel.
     * Inicijalizuje model tabele sa datom listom korisnika.
     *
     * @param users lista korisnika
     */
    public UserTableModel(List<User> users) {
        this.users = users;
    }

    
    @Override
    public String getColumnName(int column) {
    if(column>columnNames.length) return "n/a";
    return columnNames[column];
    }

    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
   
    @Override
    public int getRowCount() {
        return users.size();
    }

    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
   
        User u = users.get(rowIndex);
        switch (columnIndex) {
            case 0: return u.getUsername();
         
            default:return "n/a";
        }
    }

     /**
     * Dodaje korisnika u model tabele.
     * Ako korisnik već postoji u listi, neće se dodati.
     * Nakon dodavanja, ažurira se prikaz tabele.
     *
     * @param u korisnik koji se dodaje
     */
    public void addUser(User u){
        if(users.contains(u)) return;
        users.add(u);
        fireTableDataChanged();
    }
    
}
