/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import Class.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */
public class BancoCategoria {
    
    
    public BancoCategoria(){
        
    }
    public void inserirCategoria(Categoria categoria){
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "Insert into Categoria(NOME) VAlUES (?)";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql);
           // cb.preparedStatement.setInt(1, categoria.getCodigo());
            cb.preparedStatement.setString(1, categoria.getNome());
            cb.preparedStatement.executeUpdate();
            cb.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try { 
                cb.preparedStatement.close();
                
            }catch(SQLException e) {
                e.printStackTrace();
                
            }
        }
    }
    
    public List<Categoria> buscarTodasCategorias() {  
        List<Categoria> resultados = new ArrayList<Categoria>(); 
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT IDENT, NOME FROM Categoria ORDER BY NOME"; 
        try {  
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();  
            while (cb.resultSet.next()) { 
                Categoria temp = new Categoria();  
                temp.setCodigo(cb.resultSet.getInt("IDENT"));  
                temp.setNome(cb.resultSet.getString("NOME"));  
                resultados.add(temp);
            }  
            cb.desconectar();
            return resultados;  
        } catch (SQLException e) {  
            e.printStackTrace();  
            return null;
        }  finally { 
            try { cb.preparedStatement.close();
            } catch (SQLException e) { 
                e.printStackTrace();
            }
        }
    }
    
    public Categoria ProcurarCat(int ident){
        Categoria Resultado = new Categoria();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT IDENT, NOME FROM Categoria WHERE IDENT = " + ident + "";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
            Resultado.setCodigo(cb.resultSet.getInt("IDENT"));
            Resultado.setNome(cb.resultSet.getString("NOME"));
            }
            cb.desconectar();
            return Resultado;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        } finally{
            try { 
                cb.preparedStatement.close();
                
            }catch(SQLException e) {
                e.printStackTrace();
                
            }
        }
    }
    public Categoria ProcurarCat(String ident){
        Categoria Resultado = new Categoria();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT IDENT, NOME FROM Categoria WHERE NOME = '" + ident + "'";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            
            while(cb.resultSet.next()){
            Resultado.setCodigo(cb.resultSet.getInt("IDENT"));
            Resultado.setNome(cb.resultSet.getString("NOME"));
            }
            cb.desconectar();
            return Resultado;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            try { 
                cb.preparedStatement.close();
                
            }catch(SQLException e) {
                e.printStackTrace();
                
            }
        } 
    }
}
