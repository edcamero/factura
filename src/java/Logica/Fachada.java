/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import VO.Cajero;
import VO.Cliente;

/**
 *
 * @author blade
 */
public class Fachada {
    static private Fachada fachada;
    private Mediador mediador;
    private Fachada(){
        mediador =new Mediador();
    }
    static public Fachada getInstancia(){
        if(fachada==null)
        {
            fachada =new Fachada();
        }
        return fachada;
    }
    
    public boolean AgregarCliente(Cliente cliente,Cajero Cajero){
        return mediador.agregarCliente(cliente, Cajero);
    }
}
