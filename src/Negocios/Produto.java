/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocios;

/**
 *
 * @author ElissonRocha
 */
public class Produto {
    private int codigo;
    private String nome;
    private float pcusto;
    private float pvenda;
    private int quantidade;
    private Categoria categoria;
    
    public Produto(){
    quantidade = 0;
    }
    public Produto(int codigo, String nome, float pcusto, float pvenda, Categoria categoria){
        this.codigo=codigo;
        this.nome=nome;
        this.pcusto = pcusto;
        this.pvenda=pvenda;
        this.categoria = categoria;
        this.quantidade = 0;
    }
    //métodos
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPcusto() {
        return pcusto;
    }

    public void setPcusto(float pcusto) {
        this.pcusto = pcusto;
    }

    public float getPvenda() {
        return pvenda;
    }

    public void setPvenda(float pvenda) {
        this.pvenda = pvenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
}
