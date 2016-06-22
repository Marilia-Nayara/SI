/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocios;

import Class.Entrega;
import Class.EntregasRealizadas;
import Class.MaisVendidos;
import Utilitarios.BancoRelatorio;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author  Marilia Nayara, Gustavo Almeida, Patricia Lopes, Eddie
 */
public class NegocioRelatorio {
    private BancoRelatorio br;

    public NegocioRelatorio() {
        br = new BancoRelatorio();
    }
    public List<MaisVendidos> MaisVendidos(String inicio, String fim){
        List<MaisVendidos> mv = br.MaisVendidos(inicio, fim);
        System.out.println("OPA");
        return mv;
    }
    public List<EntregasRealizadas> EntregasRealizadas(){
        return br.EntregasRealizadas();
    }
    public List<Entrega> Entregas(){
        return br.Entregas();
    }
    public List<Entrega> Mesa(){
        return br.Mesa();
    }
    public List<Entrega> Localidades(){
        return br.Localidades();
    }
}
