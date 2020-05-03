/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisdistribuidosrelogio;

import java.util.Calendar;

/**
 *
 * @author jean.scherer
 */
public class Main {
    
    public static int qtdServidores = 7;
    
    public static void main(String[] args) {
        prepareAndExecuteBerkeley();
    }

    public static void prepareAndExecuteBerkeley() {
        
        System.out.println("------------ INICIANDO ------------");
        System.out.println("Criando mock servidores");
        
        var controle = new Controle();
        for (int i = 0; i < qtdServidores; i++) {
            var data = Calendar.getInstance();
            data.add(Calendar.MINUTE, (int)Math.round(randomMinutes()));
            
            controle.servidores.add(new Servidor(i, data));
        }
        System.out.println("Servidores criados e adicionados");
        
        System.out.println("Inicio da execução do algoritmo");
        controle.executeBerkeley();
        System.out.println("Fim da execução do algoritmo");
        
        System.out.println("------------ FINALIZANDO ------------");
    }
    
    public static double randomMinutes(){
        double random = Math.random();
        double calculated = 100.0 * random;
        
        return calculated;
    }
}
