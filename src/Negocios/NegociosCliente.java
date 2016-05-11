/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocios;

import Class.Cliente;
import Class.Endereco;
import Utilitarios.BancoCliente;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marilia Nayara, Patricia Lopes, Gustavo Almeida, Eddie
 */
public class NegociosCliente{
    
    public void salvar(String cpf, String nome, String email, String rua, int numero, String bairro, String cidade, String estado, int telefone) throws SQLException {
    Cliente cliente = new Cliente();
    Endereco endereco = new Endereco();
    BancoCliente bc = new BancoCliente(); 
    cliente.setCPF(cpf);
    cliente.setNome(nome);
    cliente.setEmail(email);
    endereco.setRua(rua);
    endereco.setNumero(numero);
    endereco.setBairro(bairro);
    endereco.setCidade(cidade);
    endereco.setEstado(estado);
    cliente.setEndereco(endereco);
    cliente.setTelefone(telefone);
    bc.inserirCliente(cliente);
    }
    public void Atualizar(String cpf, String nome, String email, String rua, int numero, String bairro, String cidade, String estado, int telefone) throws SQLException {
        Cliente cliente = ProcurarCliente(nome);
 
               
        if(cliente.getNome().length()!=0){
            BancoCliente cb = new BancoCliente();
            Endereco end = new Endereco(rua, numero, bairro, cidade, estado);
            Cliente cliente2 = new Cliente(cliente.getCodigo(), cpf, nome, end, email, telefone);
            cb.Atualizar(cliente2);
        }
        else{
        JOptionPane.showMessageDialog(null, "Cliente nao encontrado");
        }
    }
    public List<Cliente> listaCliente() { 
        BancoCliente bc = new BancoCliente(); 
        return bc.buscarTodosClientes(); 
    }
    public List<Cliente> listaCliente(String nome) { 
        BancoCliente bc = new BancoCliente(); 
        return bc.buscarClientes(nome);
    }
    public Cliente ProcurarCliente(String nome){
        BancoCliente bc = new BancoCliente(); 
        Cliente Resultado;
        Resultado = bc.ProcurarClient(nome);
        return Resultado;
    }
    public Cliente ProcurarCliente(int nome){
        BancoCliente bc = new BancoCliente(); 
        Cliente Resultado;
        Resultado = bc.ProcurarClient(nome);
        return Resultado;
    }
}
