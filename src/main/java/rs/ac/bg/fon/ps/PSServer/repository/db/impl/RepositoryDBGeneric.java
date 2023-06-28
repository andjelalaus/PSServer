/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.PSServer.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.PSCommon.domain.GenericEntity;
import rs.ac.bg.fon.ps.PSServer.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.PSServer.repository.db.DbRepository;

/**
 * Klasa RepositoryDBGeneric koja implementira interfejs DbRepository 
 * i sve metode koje poseduje interfejs Repository koji je impementirao DbRepository
 * sve crud operacije se nalaze u ovoj klasi implementirane
 * @author Andjy
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

     @Override
    public boolean edit(GenericEntity entity) {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ").append(entity.getUpdateSetValues(entity))
                    .append(" WHERE ").append(entity.getDeleteAndUpdateCondition(entity));
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean add(GenericEntity entity) {
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Integer id = rsKey.getInt(1);
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
            return true;
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             return false;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public Integer addReturnKey(GenericEntity entity) {
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Integer id = rsKey.getInt(1);
                entity.setId(id);
                return id;
            }
            statement.close();
            rsKey.close();
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             return null;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
         return null;
    }
    

    @Override
    public boolean delete(GenericEntity entity) {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(entity.getTableName())
                    .append(" WHERE ").append(entity.getDeleteAndUpdateCondition(entity));
            Statement statement = connection.createStatement();
            String query = sb.toString();
            System.out.println(query);
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity entity) {
        List<GenericEntity> entities = new ArrayList<>();
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append(entity.getSelectValues());
            String query = sb.toString();
            System.out.println(query);
            
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next()){
                entities.add(entity.getNewObject(rs));
            }
            
            return entities;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ispisi mi gresku kod getAll");
        return null;
    }
    

}
