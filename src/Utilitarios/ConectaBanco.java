/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */
public class ConectaBanco {
    public Statement statement;
    public ResultSet resultSet;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String caminho = "jdbc:mysql://localhost:3306/SI";
    private final String usuario = "root";
    private final String senha = "123456";
    public Connection connection;
    public PreparedStatement preparedStatement;
            
    public void conectar(){
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nao Conectado!");
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void desconectar(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
