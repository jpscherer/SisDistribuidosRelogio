/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisdistribuidosrelogio;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jean.scherer
 */
public class Servidor {
    
    public Servidor(int identificador, Calendar hora){
        this.identificador = identificador;
        this.hora = hora;
    }
    
    private Calendar hora;
    private int identificador;
    
    /**
     * @return the hora
     */
    public Calendar getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    @Override
    public String toString(){
        return "- Servidor " + this.identificador + " - " + this.hora.getTime().toString();
    }
    
    public void executeBerkeley(Controle controle) {
        buscaMediaTempo(controle);
        atualizaClientes(controle);
    }
    
    private void buscaMediaTempo(Controle controle){
        
        long total = 0 ;
        
        System.out.println("Deamon: ");
        System.out.println(this.toString());
        
        System.out.println("Clientes:");
                
        for (Servidor cliente : controle.servidores) {                        
            //printa data primaria
            System.out.println(cliente.toString());
            
            long diff = cliente.diferencaRelogio(this.getHora().getTime().getTime());
            
            //capta média
            total += diff;
        }
        
        controle.mediaGeral = total / (controle.servidores.size());
    }
    
    private void atualizaClientes(Controle controle){
        
        System.out.println("---------------- CALCULADO --------------");
        
        long horaServidor = controle.servidores.get(0).getHora().getTime().getTime() + (controle.mediaGeral);
        var horaCalculada = Calendar.getInstance();
        horaCalculada.setTime(new Date(horaServidor));
        controle.servidores.get(0).setHora(horaCalculada);
        System.out.println(controle.servidores.get(0).toString());
        
        for (Servidor cliente : controle.servidores) {
            if (cliente.identificador == this.identificador) continue;
            
            
            long qtdParaAjustar = cliente.getHora().getTime().getTime() - (controle.servidores.get(0).getHora().getTime().getTime());
            
            //hora cliente/servidor + inversão da diferença de tempo medio
            long calculada = cliente.getHora().getTime().getTime() + (-1 * qtdParaAjustar);
            
            var horaCalculadax = Calendar.getInstance();
            horaCalculadax.setTime(new Date(calculada));
            cliente.setHora(horaCalculadax);
            System.out.println(cliente.toString());
        }
        
    }
    
    public long diferencaRelogio(long horaRequisitante){
        return this.getHora().getTime().getTime() - horaRequisitante;
    }
}
