/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import VO.Articulo;
import VO.Cajero;
import VO.Cliente;
import VO.Facturacliente;
import java.util.ArrayList;

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
    
    public ArrayList<Cliente>ListarClientes(){
        return mediador.listarClientes();
    }

    
   
    public boolean editarCliente(Cliente cliente, Cajero cajero) {
        return mediador.editarCliente(cliente, cajero);
    }

   
    public boolean eliminarCliente(Cliente cliente, Cajero cajero) {
         return this.mediador.eliminarCliente(cliente, cajero);
    }

  
   
   
    public Cliente buscarCliente(int id) {
       return this.mediador.buscarCliente(id);
//To change body of generated methods, choose Tools | Templates.
    }
    public Cliente buscarCliente(String id) {
       return this.mediador.buscarCliente(id);
//To change body of generated methods, choose Tools | Templates.
    }

   
    public boolean agregarArticulo(Articulo articulo, Cajero cajero) {
      return  mediador.agregarArticulo(articulo, cajero);
    }

     public Articulo agregarArticulo2(Articulo articulo, Cajero cajero) {
      return  mediador.agregarArticulo2(articulo, cajero);
    }

    public boolean editarArticulo(Articulo articulo, Cajero cajero) {
       return mediador.editarArticulo(articulo, cajero);
        //To change body of generated methods, choose Tools | Templates.
    }

  
    public boolean eliminarArticulo(int id, Cajero cajero) {
     return mediador.eliminarArticulo(id, cajero);
    }


    public Articulo buscarArticulo(int id) {
      return mediador.buscarArticulo(id); }

  
    public ArrayList<Articulo> listarArticulos() {
           return this.mediador.listarArticulos();
         }

  
    public boolean agregarFactura(Facturacliente factura, Cajero cajero) {
       return this.mediador.agregarFactura(factura, cajero);
    }

   
    public ArrayList<Facturacliente> listarFacturas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
