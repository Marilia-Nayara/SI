/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import Class.Cliente;
import Class.Endereco;
import View.Aviso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */

public class BancoCliente {
    
    public void inserirCliente(Cliente cliente){
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "Insert into Cliente(CPF, NOME, EMAIL, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, TELEFONE) VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql);
            cb.preparedStatement.setString(1, cliente.getCPF());
            cb.preparedStatement.setString(2, cliente.getNome());
            cb.preparedStatement.setString(3, cliente.getEmail());
            cb.preparedStatement.setString(4, cliente.getEndereco().getRua());
            cb.preparedStatement.setInt(5, cliente.getEndereco().getNumero());
            cb.preparedStatement.setString(6, cliente.getEndereco().getBairro());
            cb.preparedStatement.setString(7, cliente.getEndereco().getCidade());
            cb.preparedStatement.setString(8, cliente.getEndereco().getEstado());
            cb.preparedStatement.setInt(9, cliente.getTelefone());
            cb.preparedStatement.executeUpdate(); 
            cb.desconectar();
            Aviso a = new Aviso();
            JOptionPane.showMessageDialog(a, "Banco de Dados aceito");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            try { 
                cb.preparedStatement.close();
                
            }catch(SQLException e) {
                e.printStackTrace();
                
            }
        }
    }
    
    public void Atualizar(Cliente cliente){
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "UPDATE Cliente SET CPF = '"+cliente.getCPF()+"', NOME ='"+cliente.getNome()+"', RUA ='"+cliente.getEndereco().getRua()+"', NUMERO = "+cliente.getEndereco().getNumero()+", BAIRRO = '"+cliente.getEndereco().getBairro()+"', CIDADE = '"+cliente.getEndereco().getCidade()+"', ESTADO ='"+cliente.getEndereco().getEstado()+"', TELEFONE = "+cliente.getTelefone()+", EMAIL = '"+cliente.getEmail()+"' WHERE CODIGO = "+cliente.getCodigo()+"";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql);
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
    
    public List<Cliente> buscarTodosClientes() {  
        List<Cliente> resultados = new ArrayList<Cliente>(); 
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Cliente ORDER BY NOME"; 
        try {  
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();  
            while (cb.resultSet.next()) { 
                Endereco temp1 = new Endereco();
                Cliente temp = new Cliente();  
                temp.setCodigo(cb.resultSet.getInt("CODIGO"));
                temp.setCPF(cb.resultSet.getString("CPF"));  
                temp.setNome(cb.resultSet.getString("NOME"));  
                temp.setEmail(cb.resultSet.getString("EMAIL")); 
                temp.setTelefone(cb.resultSet.getInt("TELEFONE"));
                temp1.setRua(cb.resultSet.getString("RUA"));
                temp1.setNumero(cb.resultSet.getInt("NUMERO"));
                temp1.setBairro(cb.resultSet.getString("BAIRRO"));
                temp1.setCidade(cb.resultSet.getString("CIDADE"));
                temp1.setEstado(cb.resultSet.getString("ESTADO"));
                temp.setEndereco(temp1);
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
    
    public List<Cliente> buscarClientes(String nome) {
        List<Cliente> resultados = new ArrayList<Cliente>(); 
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Cliente WHERE NOME LIKE '"+nome+"%' ORDER BY NOME"; 
        try {  
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();  
            while (cb.resultSet.next()) { 
                Cliente temp = new Cliente();  
                temp.setCodigo(cb.resultSet.getInt("CODIGO"));
                temp.setCPF(cb.resultSet.getString("CPF"));  
                temp.setNome(cb.resultSet.getString("NOME"));  
                temp.setEmail(cb.resultSet.getString("EMAIL"));
                temp.setTelefone(cb.resultSet.getInt("TELEFONE"));
                Endereco temp1 = new Endereco();
                temp1.setRua(cb.resultSet.getString("RUA"));
                temp1.setBairro(cb.resultSet.getString("BAIRRO"));
                temp1.setNumero(cb.resultSet.getInt("NUMERO"));
                temp1.setCidade(cb.resultSet.getString("CIDADE"));
                temp1.setEstado(cb.resultSet.getString("Estado"));
                temp.setEndereco(temp1);
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
  
    public Cliente ProcurarClient(String nome){
        Cliente Resultado = new Cliente();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Cliente WHERE NOME = '" +nome+ "'";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
                Resultado.setCodigo(cb.resultSet.getInt("CODIGO"));
                Resultado.setCPF(cb.resultSet.getString("CPF"));  
                Resultado.setNome(cb.resultSet.getString("NOME"));  
                Resultado.setEmail(cb.resultSet.getString("EMAIL"));
                Resultado.setTelefone(cb.resultSet.getInt("TELEFONE"));
                Endereco temp1 = new Endereco();
                temp1.setRua(cb.resultSet.getString("RUA"));
                temp1.setBairro(cb.resultSet.getString("BAIRRO"));
                temp1.setNumero(cb.resultSet.getInt("NUMERO"));
                temp1.setCidade(cb.resultSet.getString("CIDADE"));
                temp1.setEstado(cb.resultSet.getString("Estado"));
                Resultado.setEndereco(temp1);
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

    public Cliente ProcurarClient(int nome){
        Cliente Resultado = new Cliente();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Cliente WHERE CODIGO = " +nome+ "";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
                Resultado.setCodigo(cb.resultSet.getInt("CODIGO"));
                Resultado.setCPF(cb.resultSet.getString("CPF"));  
                Resultado.setNome(cb.resultSet.getString("NOME"));  
                Resultado.setEmail(cb.resultSet.getString("EMAIL"));
                Resultado.setTelefone(cb.resultSet.getInt("TELEFONE"));
                Endereco temp1 = new Endereco();
                temp1.setRua(cb.resultSet.getString("RUA"));
                temp1.setBairro(cb.resultSet.getString("BAIRRO"));
                temp1.setNumero(cb.resultSet.getInt("NUMERO"));
                temp1.setCidade(cb.resultSet.getString("CIDADE"));
                temp1.setEstado(cb.resultSet.getString("Estado"));
                Resultado.setEndereco(temp1);
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
