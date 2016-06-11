/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import Class.Compra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

       

public class BancoCompra {
    public int inserirCompra(Compra compra){
        int codigo = 0;
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        //String sql = "SELECT COD.CURRVAL FROM Compra";
        String sql = "SELECT COD FROM Compra";
        String sql2= "INSERT INTO PRODUTOS_COMPRA( CODIGO_COMPRA, CODIGO_PRODUTO, QUANTIDADE)VALUES(?,?,?)";
        try{
            cb.preparedStatement = cb.connection.prepareStatement("INSERT INTO Compra( CPF_C, DAT, PRECO_FINAL, ISENTREGA) VALUES (?,?,?,?)");
            cb.preparedStatement.setInt(1, compra.getCpf_c());
            cb.preparedStatement.setTimestamp(2, compra.getData());
            cb.preparedStatement.setFloat(3, compra.getPreco_final());
            if(compra.isIsEntrega()){
            cb.preparedStatement.setString(4, "true");
            }else cb.preparedStatement.setString(4, "false"); 
            cb.preparedStatement.executeUpdate();
            
            cb.preparedStatement = cb.connection.prepareStatement(sql);
            cb.resultSet = cb.preparedStatement.executeQuery();
            if(cb.resultSet.next()){
//            compra.setCodigo(cb.resultSet.getInt("CURRVAL"));
            codigo = compra.getCodigo();
            }
            System.out.println("Aki");
            cb.preparedStatement = cb.connection.prepareStatement(sql2);
            for(int x=0; x<compra.getLista_prod().size(); x++){
                System.out.println("Aki1");
                cb.preparedStatement.setInt(1, compra.getCodigo());
                cb.preparedStatement.setInt(2, compra.getLista_prod().get(x).intValue());
                cb.preparedStatement.setInt(3, compra.getQuantidades().get(x).intValue());
                cb.preparedStatement.executeUpdate();
            }
            cb.preparedStatement.close();
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
        return codigo;
    }
    
    public Compra ConsultarCompra(int codigo){
        List<Integer> list_prod = new ArrayList<Integer>();
        List<Integer> quantidades = new ArrayList<Integer>();
        Compra Resultado = new Compra();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT * FROM Compra WHERE COD = " +codigo+ "";
        String sql2 = "SELECT * FROM Produtos_Compra WHERE CODIGO_COMPRA = "+codigo+"";
        try{
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();
            if(cb.resultSet.next()){
                Resultado.setCodigo(cb.resultSet.getInt("COD"));
                Resultado.setCpf_c(cb.resultSet.getInt("CPF_C"));  
                Resultado.setData(cb.resultSet.getTimestamp("DAT"));  
                String teste = cb.resultSet.getString("ISENTREGA");
                if(teste.equals("true")){
                Resultado.setIsEntrega(true);
                }else Resultado.setIsEntrega(false);
                Resultado.setPreco_final(cb.resultSet.getFloat("PRECO_FINAL"));
                cb.preparedStatement = cb.connection.prepareStatement(sql2); 
                cb.resultSet = cb.preparedStatement.executeQuery();
                while(cb.resultSet.next()){
                    list_prod.add(cb.resultSet.getInt("CODIGO_PRODUTO"));
                    quantidades.add(cb.resultSet.getInt("QUANTIDADE"));
                }
                Resultado.setLista_prod(list_prod);
                Resultado.setQuantidades(quantidades);
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
