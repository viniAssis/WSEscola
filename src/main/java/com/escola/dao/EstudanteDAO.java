/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escola.dao;

import com.escola.model.Estudante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author vini
 */
public class EstudanteDAO {
    
    public Estudante getEstudante(String matricula){
        Estudante estudante =  new Estudante();
        try{
            Connection con = ConnectionFactory.getConexao();
            Statement stmt = con.createStatement();
            String sql = "select * from escola.estudante where matricula='" + matricula+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                estudante.setMatricula(rs.getString("matricula"));
                estudante.setNome(rs.getString("nome"));
                estudante.setSobrenome(rs.getString("sobrenome"));

            }
            else{
                estudante = null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return estudante;
    }
        
        
    public String inserirEstudante(Estudante estudante){
        String resp="";
        
        try{
            Connection con = ConnectionFactory.getConexao();
            String sql = "INSERT INTO escola.estudante(matricula, nome, sobrenome) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estudante.getMatricula());
            ps.setString(2, estudante.getNome());
            ps.setString(3, estudante.getSobrenome());
            
            ps.execute();
            ps.close();
            con.close();
            
            resp = "Inserida matricula: "+ estudante.getMatricula();
        }
        catch(Exception e){
          resp = "ERRO: " + e.toString();
        }
        return resp;
    }
    
    public String alterarEstudante(Estudante estudante, String matriculaNova) {

     String resp = "";

     try {
         Connection con = ConnectionFactory.getConexao();
         String sql = "UPDATE escola.estudante SET matricula=?, nome=?, sobrenome=? WHERE (matricula = ?)";
         PreparedStatement ps = con.prepareStatement(sql);
         
         if(!matriculaNova.equals(""))
         ps.setString(1, matriculaNova);
         else
         ps.setString(1, estudante.getMatricula());
         
         ps.setString(2, estudante.getNome());
         ps.setString(3, estudante.getSobrenome());
         ps.setString(4, estudante.getMatricula());
            


         ps.execute();

         ps.close();
         con.close();
         
         
         if(!matriculaNova.equals(""))
          resp = "Alterada matricula: " +  matriculaNova;
         else
          resp = "Alterada matricula: " +  estudante.getMatricula();



        } catch (Exception e) {
            resp = e.toString();
        }

        return resp;
    } 

    public String excluirEstudante(String matricula) {
        
        String resp = "";
        
        try {
         Connection con = ConnectionFactory.getConexao();
         String sql =  "DELETE FROM escola.estudante WHERE matricula = ?";
         PreparedStatement ps = con.prepareStatement(sql);
         
         ps.setString(1, matricula);
                  
         ps.execute();

         ps.close();
         con.close();

         resp = "Excluido";

        } catch (Exception e) {
            resp = e.toString();
        }

        return resp;
            
        
    }
    
}
