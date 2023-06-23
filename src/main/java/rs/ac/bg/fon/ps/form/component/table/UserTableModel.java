/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author LENOVO
 */
public class UserTableModel extends AbstractTableModel{

    String[] columnNames = new String[]{"username"};
    List<User> users;

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

    public void addUser(User u){
        if(users.contains(u)) return;
        users.add(u);
        fireTableDataChanged();
    }
    
}
