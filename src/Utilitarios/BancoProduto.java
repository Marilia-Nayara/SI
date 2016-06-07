/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import Class.Categoria;
import Class.Produto;
import Negocios.NegociosCategoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BancoProduto {
    public void inserirProduto(Produto produto){
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "Insert into Produto(CODIGO, NOME, P_CUSTO, P_VENDA, QUANTIDADE, IDENT) VALUES (CODIGO.NEXTVAL,?,?,?,?,?)";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql);
            cb.preparedStatement.setString(1, produto.getNome());
            cb.preparedStatement.setFloat(2, produto.getPcusto());
            cb.preparedStatement.setFloat(3, produto.getPvenda());
            cb.preparedStatement.setInt(4, produto.getQuantidade());
            cb.preparedStatement.setInt(5, produto.getCategoria().getCodigo());
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
    
    public List<Produto> buscarTodosProdutos() {  
        List<Produto> resultados = new ArrayList<Produto>(); 
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT CODIGO, NOME, P_CUSTO, P_VENDA, QUANTIDADE, IDENT FROM Produto ORDER BY NOME"; 
        try {  
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();  
            while (cb.resultSet.next()) { 
                Produto temp = new Produto();  
                temp.setCodigo(cb.resultSet.getInt("CODIGO"));  
                temp.setNome(cb.resultSet.getString("NOME"));  
                temp.setPcusto(cb.resultSet.getFloat("P_CUSTO"));
                temp.setPvenda(cb.resultSet.getFloat("P_VENDA"));
                temp.setQuantidade(cb.resultSet.getInt("QUANTIDADE"));
                Categoria c = new Categoria();
                c.setCodigo(cb.resultSet.getInt("IDENT"));
                NegociosCategoria nc = new NegociosCategoria();
                c = nc.ProcurarCategoria(c.getCodigo());
                temp.setCategoria(c);
                resultados.add(temp);
            }
            cb.desconectar();
            return resultados;  
        } catch (SQLException e) {  
            e.printStackTrace();
            cb.desconectar();
            return null;
        }  finally { 
            try { cb.preparedStatement.close();
            } catch (SQLException e) { 
                e.printStackTrace();
            }
        }
    }
    public List<Produto> buscarProdutos(int categoria){
        List<Produto> resultados = new ArrayList<Produto>(); 
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Produto WHERE IDENT = "+categoria+" ORDER BY NOME"; 
        try {  
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();  
            while (cb.resultSet.next()) { 
                Produto temp = new Produto();  
                temp.setCodigo(cb.resultSet.getInt("CODIGO"));  
                temp.setNome(cb.resultSet.getString("NOME"));  
                temp.setPcusto(cb.resultSet.getFloat("P_CUSTO"));
                temp.setPvenda(cb.resultSet.getFloat("P_VENDA"));
                temp.setQuantidade(cb.resultSet.getInt("QUANTIDADE"));
                Categoria c = new Categoria();
                c.setCodigo(cb.resultSet.getInt("IDENT"));
                NegociosCategoria nc = new NegociosCategoria();
                c = nc.ProcurarCategoria(c.getCodigo());
                temp.setCategoria(c);
                resultados.add(temp);
            }  
            cb.desconectar();
            return resultados;  
        } catch (SQLException e) {  
            e.printStackTrace();
            cb.desconectar();
            return null;
        }  finally { 
            try { cb.preparedStatement.close();
            } catch (SQLException e) { 
                e.printStackTrace();
            }
        }
    }
     public Produto ProcurarProd(int ident){
        Produto Resultado = new Produto();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Produto WHERE CODIGO = " + ident + "";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
            Resultado.setCodigo(cb.resultSet.getInt("CODIGO"));
            Resultado.setNome(cb.resultSet.getString("NOME"));
            Resultado.setPcusto(cb.resultSet.getFloat("P_CUSTO"));
            Resultado.setPvenda(cb.resultSet.getFloat("P_VENDA"));
            Resultado.setQuantidade(cb.resultSet.getInt("QUANTIDADE"));
            Categoria c = new Categoria();
                c.setCodigo(cb.resultSet.getInt("IDENT"));
                NegociosCategoria nc = new NegociosCategoria();
                c = nc.ProcurarCategoria(c.getCodigo());
                Resultado.setCategoria(c);
            }
            cb.desconectar();
            return Resultado;
        }catch(SQLException e){
            e.printStackTrace();
            cb.desconectar();
            return null;
        }finally{
            try { 
                cb.preparedStatement.close();
                
            }catch(SQLException e) {
                e.printStackTrace();
                
            }
        } 
    }
    public Produto ProcurarProd(String ident){
        Produto Resultado = new Produto();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Produto WHERE NOME = '" + ident + "'";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
            Resultado.setCodigo(cb.resultSet.getInt("CODIGO"));
            Resultado.setNome(cb.resultSet.getString("NOME"));
            Resultado.setPcusto(cb.resultSet.getFloat("P_CUSTO"));
            Resultado.setPvenda(cb.resultSet.getFloat("P_VENDA"));
            Resultado.setQuantidade(cb.resultSet.getInt("QUANTIDADE"));
            Categoria c = new Categoria();
                c.setCodigo(cb.resultSet.getInt("IDENT"));
                NegociosCategoria nc = new NegociosCategoria();
                c = nc.ProcurarCategoria(c.getCodigo());
                Resultado.setCategoria(c);
            }
            cb.desconectar();
            return Resultado;
        }catch(SQLException e){
            cb.desconectar();
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
    
    public void UpdateProd(int quant, int codigo){
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "UPDATE Produto SET QUANTIDADE = "+quant+" WHERE CODIGO = " + codigo + "";
        try {
            cb.preparedStatement = cb.connection.prepareStatement(sql);
            cb.preparedStatement.executeUpdate();
            cb.desconectar();
        } catch (SQLException ex) {
            cb.desconectar();
            Logger.getLogger(BancoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try { 
                cb.preparedStatement.close();
                
            }catch(SQLException e) {
                e.printStackTrace();
                
            }
        }
        
    }
}
