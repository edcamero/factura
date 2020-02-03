/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import DAO.ArticuloDao;
import DAO.ClienteDao;
import DAO.FacturaclienteDao;
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
FacturaclienteDao facturaDao;
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
    
    public Cliente buscarCliente(String id) {
        ClienteDao clienteDao=new ClienteDao();
        return clienteDao.buscarPorCedula(id);
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
         ArticuloDao articuloDao=new ArticuloDao();
         return articuloDao.buscar(id);
    }

    @Override
    public ArrayList<Articulo> listarArticulos() {
        articuloDao=new ArticuloDao();
        return articuloDao.obtener();
         }

    @Override
    public boolean agregarFactura(Facturacliente factura, Cajero cajero) {
       facturaDao=new FacturaclienteDao();
       return facturaDao.registrar(factura, cajero);
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
        articuloDao=new ArticuloDao();
        return articuloDao.eliminar(id, cajero);
        }

   
    
    
     private  String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public  String md5(String txt) {
        return getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA1 a partir de un texto */
    public  String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}