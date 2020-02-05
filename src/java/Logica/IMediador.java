/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import VO.Articulo;
import VO.Cajero;
import VO.Cliente;
import VO.FacturaCliente;
import java.util.ArrayList;

/**
 *
 * @author blade
 */
public interface IMediador {
    
    
   public boolean agregarCliente(Cliente cliente,Cajero cajero);
   public boolean editarCliente(Cliente cliente,Cajero cajero);
   public boolean eliminarCliente(Cliente cliente,Cajero cajero);
   public ArrayList<Cliente> listarClientes();
   public Cliente buscarCliente(int id);
   
   
   public boolean agregarArticulo(Articulo articulo,Cajero cajero);
   public boolean editarArticulo(Articulo articulo,Cajero cajero);
   public boolean eliminarArticulo(int id,Cajero cajero);
   public Articulo buscarArticulo(int id);
   public ArrayList<Articulo> listarArticulos();
   
   
   public boolean agregarFactura(FacturaCliente factura,Cajero cajero);
   public ArrayList<FacturaCliente> listarFacturas();
   public boolean eliminarFactura(FacturaCliente factura,Cajero cajero);
   
   
  // public boolean eliminarDetalle(int id,Cajero cajero);
}
