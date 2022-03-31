/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Conta;
import view.telaInicial;

/**
 *
 * @author anton
 */
public class ContaDAO {
     public void create(Conta c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO conta (nomeUsuario,loginUsuario,senhaUsuario)VALUES(?,?,?)");
            stmt.setString(1, c.getNomeUsuario());
            stmt.setString(2, c.getLoginUsuario());
            stmt.setString(3, c.getSenhaUsuario());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Conta criada com sucesso");
            telaInicial telaInicial = new telaInicial();
            telaInicial.setVisible(true);
            
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "erro ao salvar : "+ex);
        }finally{
            ConnectionFactory.closeConection(con, stmt);
        }
        
    }
     public List<Conta> read(){
        
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Conta> Contas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Conta");
           rs = stmt.executeQuery();
           
            while(rs.next()){
                
                Conta Conta = new Conta();
                
                Conta.setIdContas(rs.getInt("idConta"));
                Conta.setNomeUsuario(rs.getString("nomeUsuario"));
                Conta.depositar(rs.getDouble("saldoUsuario"));
                
                Contas.add(Conta);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConection(con, stmt, rs);
        }
        
        return Contas;
        
    }
     
     
     public boolean checkLogin(String login, String senha){
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         boolean check = false;
         
         try {
            stmt = con.prepareStatement("SELECT * FROM Conta WHERE loginUsuario = ? and senhaUsuario = ?"); 
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            
             if (rs.next()) {
                 
                 check = true;
                 System.setProperty("loginUsuarioDoBD", login);
                 
             }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "erro ao achar conta : "+ ex);
            
        }finally{
             ConnectionFactory.closeConection(con, stmt, rs);
         }
         return check;
                 
    }
     
          public void update(Conta c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Conta SET nomeUsuario = ?, saldoUsuario = ? WHERE idConta = ?");
            stmt.setString(1, c.getNomeUsuario());
            stmt.setDouble(2, c.getSaldoUsuario());
            stmt.setInt(3, c.getIdContas());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "erro ao Atualizar Usuario: "+ex);
        }finally{
            ConnectionFactory.closeConection(con, stmt);
        }
        
    }
     
     public List<Conta> readForLogin(String login){
        
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Conta> mc = new ArrayList<>();
        Conta usuario1 = new Conta();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Conta WHERE loginUsuario = ?");
            
            stmt.setString(1, login);
            
           rs = stmt.executeQuery();
           
            while(rs.next()){
                
                Conta conta = new Conta();
                conta.setIdContas(rs.getInt("idConta"));
                conta.setNomeUsuario(rs.getString("nomeUsuario"));
                conta.depositar(rs.getDouble("saldoUsuario"));
               
                
                mc.add(conta);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConection(con, stmt, rs);
        }
        
        return mc;
        
    }
     public List<Conta> readForID(String id, String nome){
        
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Conta> mc = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Conta WHERE idConta = ? and nomeUsuario = ?");
            
            stmt.setString(1, id);
            stmt.setString(2, nome);
            
           rs = stmt.executeQuery();
           
            while(rs.next()){
                
                Conta conta = new Conta();
                
                conta.setIdContas(rs.getInt("idConta"));
                conta.setNomeUsuario(rs.getString("nomeUsuario"));
                conta.depositar(rs.getDouble("saldoUsuario"));
               
                
                mc.add(conta);
//                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!!");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            ConnectionFactory.closeConection(con, stmt, rs);
        }
        
        return mc;
        
    }
}
