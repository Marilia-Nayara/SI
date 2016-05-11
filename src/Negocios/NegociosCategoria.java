/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocios;

import Class.Categoria;
import Utilitarios.BancoCategoria;
import java.util.List;

/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */
public class NegociosCategoria {
    public void salvar(String nome){
        Categoria c = new Categoria();
        BancoCategoria bc = new BancoCategoria();
        c.setNome(nome);
        bc.inserirCategoria(c);
    }
    
    public List<Categoria> listaCategorias() { 
        BancoCategoria bc = new BancoCategoria(); 
        return bc.buscarTodasCategorias(); 
    }
    
    public Categoria ProcurarCategoria(int ident){
        BancoCategoria bc = new BancoCategoria(); 
        Categoria Resultado = new Categoria();
        Resultado = bc.ProcurarCat(ident);
        return Resultado;
    }
    public Categoria ProcurarCategoria(String ident){
        BancoCategoria bc = new BancoCategoria(); 
        Categoria Resultado = new Categoria();
        Resultado = bc.ProcurarCat(ident);
        return Resultado;
    }
}
