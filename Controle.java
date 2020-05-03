/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisdistribuidosrelogio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jean.scherer
 */
public class Controle {
    
    Random rand = new Random();
    
    public Controle() {
        this.servidores = new ArrayList<>();
    }
    
    public List<Servidor> servidores;
    public long mediaGeral;
    
    public void executeBerkeley(){
        //poderia ser pego de forma randomica o index do servidor deamon;
        this.servidores.get(rand.nextInt(this.servidores.size())).executeBerkeley(this);
    }
}
