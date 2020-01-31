/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import DAO.ArticuloDao;
import DAO.ClienteDao;
import VO.Articulo;
import VO.Cajero;
import VO.Cliente;
import VO.Facturacliente;
import java.util.ArrayList;

/**
 *
 * @author blade
 */
public class Mediador implements IMediador {
ArticuloDao articuloDao;
    public Mediador() {
    }

    
    
    @Override
    public boolean agregarCliente(Cliente cliente, Cajero cajero) {
       ClienteDao clienteDao=new ClienteDao();
       return clienteDao.registrar(cliente, cajero);
    }

    @Override
    public boolean editarCliente(Cliente cliente, Cajero cajero) {
       ClienteDao clienteDao=new ClienteDao();
       return clienteDao.actualizar(cliente, cajero);
    }

    
    @Override
    public boolean eliminarCliente(Cliente cliente, Cajero cajero) {
     ClienteDao clienteDao=new ClienteDao();
       return clienteDao.eliminar(cliente.getClieId(), cajero);
    }

   

    @Override
    public ArrayList<Cliente> listarClientes() {
        ClienteDao clienteDao=new ClienteDao();
        return clienteDao.obtener();
        
        
      }

    @Override
    public Cliente buscarCliente(int id) {
        ClienteDao clienteDao=new ClienteDao();
        return clienteDao.buscar(id);
    }

    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean agregarArticulo(Articulo articulo, Cajero cajero) {
        ArticuloDao articuloDao=new ArticuloDao();
        return articuloDao.registrar(articulo, cajero);
    }
    
    public Articulo agregarArticulo2(Articulo articulo, Cajero cajero) {
        ArticuloDao articuloDao=new ArticuloDao();
        return articuloDao.registrar2(articulo, cajero);
    }

  
    @Override
    public Articulo buscarArticulo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Articulo> listarArticulos() {
        articuloDao=new ArticuloDao();
        return articuloDao.obtener();
         }

    @Override
    public boolean agregarFactura(Facturacliente factura, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Facturacliente> listarFacturas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean editarArticulo(Articulo articulo, Cajero cajero) {
        articuloDao=new ArticuloDao();
        return articuloDao.actualizar(articulo, cajero);
    }

    
    @Override
    public boolean eliminarArticulo(int id, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}