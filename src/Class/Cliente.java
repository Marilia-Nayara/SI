/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;

/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */
public class Cliente {
    private int codigo;
    private String CPF;
    private String nome;
    private Endereco endereco;
    private String email;
    private int telefone;
    
    //métodos
    public Cliente(){
        
    }
    
    public Cliente(int codigo, String cpf, String n, Endereco e, String em, int tel){
        this.codigo = codigo;
        CPF=cpf;
        nome = n;
        endereco = e;
        email = em;
        telefone = tel;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
