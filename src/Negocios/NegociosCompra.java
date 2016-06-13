/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocios;

import Class.Compra;
import Utilitarios.BancoCompra;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */

public class NegociosCompra {
    public int salvar(int cpf_c, Timestamp data, boolean isEntrega, float preco_final, List<Integer> lista_prod, List<Integer> quantidades) {
        Compra compra = new Compra();
        BancoCompra bc = new BancoCompra(); 
        compra.setCpf_c(cpf_c);
        compra.setData(data);
        compra.setIsEntrega(isEntrega);
        compra.setPreco_final(preco_final);
        compra.setLista_prod(lista_prod);
        compra.setQuantidades(quantidades);
        int codigo = bc.inserirCompra(compra);
        return codigo;
    }
    public Compra ConsultarCompra(int codigo){
        BancoCompra bc = new BancoCompra(); 
        return bc.ConsultarCompra(codigo);
    }
}
