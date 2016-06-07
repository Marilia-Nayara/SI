/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocios;

import Class.Categoria;
import Class.Produto;
import Utilitarios.BancoProduto;
import java.sql.SQLException;
import java.util.List;


public class NegociosProduto {
    public int salvar(String nome, float pcusto, float pvenda, String categ) throws SQLException {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        BancoProduto bp = new BancoProduto(); 
        NegociosCategoria nc = new NegociosCategoria();
        produto.setNome(nome);
        produto.setPcusto(pcusto);
        produto.setPvenda(pvenda);
        categoria = nc.ProcurarCategoria(categ);
        produto.setCategoria(categoria);
        bp.inserirProduto(produto);
        int codigo;
        produto = bp.ProcurarProd(produto.getNome());
        codigo = produto.getCodigo();
        return codigo;
    }
    
    public List<Produto> listaProdutos() { 
        BancoProduto bp = new BancoProduto(); 
        return bp.buscarTodosProdutos();
    }
    public List<Produto> listaProdutos(int categoria) { 
        BancoProduto bp = new BancoProduto(); 
        return bp.buscarProdutos(categoria);
    }
    
    public Produto ProcurarProduto(int ident){
        BancoProduto bp = new BancoProduto(); 
        Produto Resultado;
        Resultado = bp.ProcurarProd(ident);
        return Resultado;
    }
    public Produto ProcurarProduto(String ident){
        BancoProduto bp = new BancoProduto(); 
        Produto Resultado;
        Resultado = bp.ProcurarProd(ident);
        return Resultado;
    }
    public void UpdateProduto(int quant, int codigo){
        BancoProduto bp = new BancoProduto();
        bp.UpdateProd(quant, codigo);
    }
}
