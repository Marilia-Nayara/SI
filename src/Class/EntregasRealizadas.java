/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author  Marilia Nayara, Gustavo Almeida, Patricia Lopes, Eddie

 */
public class EntregasRealizadas {
    private int quant;
    private Timestamp datas;

    public EntregasRealizadas() {
    }

    public int getQuant() {
        return quant;
    }
public void setQuant(int quant) {
        this.quant = quant;
    }

    public Timestamp getDatas() {
        return datas;
    }

    public void setDatas(Timestamp datas) {
        this.datas = datas;
    }
    

    
}
