/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escola.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vini
 */
public class ConnectionFactory {
    
        public static Connection getConexao() throws Exception {
        
    
        
        
        
        
         Connection con = null;
        try {
            String serverName = "localhost:3306";
            String mydatabase = "escola";
            String username = "root";
            String password = "admin";
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?allowPublicKeyRetrieval=true&useSSL=false";
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado " + e.toString());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar o Banco de Dados " + e.toString());
        }
        return con;
        
    
    }
    
    
}
