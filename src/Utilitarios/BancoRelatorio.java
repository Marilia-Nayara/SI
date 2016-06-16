/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Class.Entrega;
import Class.EntregasRealizadas;
import Class.MaisVendidos;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Marilia Nayara, Gustavo Almeida, Patricia Lopes, Eddie
 */
public class BancoRelatorio {
    
    public List<MaisVendidos> MaisVendidos(String inicio, String fim){
        List<MaisVendidos> resultados = new ArrayList<MaisVendidos>(); 
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "SELECT MAX(P.IDENT) INDE, CAST(CO.DAT AS DATE) DIA, P.CODIGO COD, SUM(PC.QUANTIDADE) QUANT, SUM(P.P_VENDA) PFINAL FROM PRODUTO P, PRODUTOS_COMPRA PC, COMPRA CO WHERE CO.COD = PC.CODIGO_COMPRA AND PC.CODIGO_PRODUTO = P.CODIGO AND DAT BETWEEN '"+inicio+"' AND '"+fim+"' GROUP BY CO.DAT, P.CODIGO, P.IDENT ORDER BY CO.DAT, SUM(PC.QUANTIDADE)"; 
        try{
            System.out.println("Aki");
            cb.preparedStatement = cb.connection.prepareStatement(sql); 
            cb.resultSet = cb.preparedStatement.executeQuery();  
            System.out.println("Aki2");
            while (cb.resultSet.next()) {
                System.out.println("Aki3");
                MaisVendidos temp = new MaisVendidos();
                temp.setIdent(cb.resultSet.getInt("INDE"));
                System.out.println("Aki4");
                temp.setCodigo(cb.resultSet.getInt("COD"));
                System.out.println("Aki5");
                temp.setQuantidade(cb.resultSet.getInt("QUANT"));
                System.out.println("Aki6");
                temp.setData(cb.resultSet.getTimestamp("DIA"));
                System.out.println("Aki7");
                temp.setPtotal(cb.resultSet.getDouble("PFINAL"));
                System.out.println("Aki8");
                resultados.add(temp);
            }
            System.out.println("Aki4");
            cb.desconectar();
            return resultados;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            System.out.println("Saiu");
        }
    }
    
     public List<EntregasRealizadas> EntregasRealizadas(){
        List<EntregasRealizadas> resultado = new ArrayList<EntregasRealizadas>();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "SELECT COUNT(*), CAST(DAT AS DATE) DIA FROM COMPRA WHERE ISENTREGA = 'true' GROUP BY DAT";
        
        try {
            cb.preparedStatement = cb.connection.prepareStatement(sql);
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
                EntregasRealizadas temp = new EntregasRealizadas();
                temp.setQuant(cb.resultSet.getInt("COUNT(*)"));
                temp.setDatas(cb.resultSet.getTimestamp("DIA"));
                resultado.add(temp);
            }
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(BancoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            return resultado;
        }
    }
    
  public List<Entrega> Entregas(){
        List<Entrega> resultado = new ArrayList<Entrega>();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT to_char(DAT , 'DAY') dia_semana, COUNT(ISENTREGA) contador FROM COMPRA WHERE ISENTREGA = 'true' GROUP BY to_char(DAT, 'DAY') ORDER BY contador DESC";
        
        try {
            cb.preparedStatement = cb.connection.prepareStatement(sql);
            cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
                Entrega temp = new Entrega();
                temp.setDia(cb.resultSet.getString("dia_semana"));
                temp.setQuant(cb.resultSet.getInt("contador"));
                resultado.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
        
    }
      
   public List<Entrega> Mesa(){
        List<Entrega> resultado = new ArrayList<Entrega>();
        ConectaBanco cb = new ConectaBanco();
        cb.conectar();
        String sql = "SELECT to_char(DAT , 'DAY') dia_semana, COUNT(ISENTREGA) contador FROM COMPRA WHERE ISENTREGA = 'false' GROUP BY to_char(DAT, 'DAY') ORDER BY contador DESC";
        
        try {
            cb.preparedStatement = cb.connection.prepareStatement(sql);
        cb.resultSet = cb.preparedStatement.executeQuery();
            while(cb.resultSet.next()){
                Entrega temp = new Entrega();
                temp.setDia(cb.resultSet.getString("dia_semana"));
                temp.setQuant(cb.resultSet.getInt("contador"));
                resultado.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
        
    }
     
}
