/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Conexion.Conexion;
import DAO.ArticuloDao;
import DAO.ClienteDao;
import DAO.DetallefacturaDao;
import DAO.FacturaClienteDao;
import VO.Articulo;
import VO.Cajero;
import VO.Cliente;
import VO.DetalleFactura;
import VO.FacturaCliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blade
 */
public class Mediador implements IMediador {
ArticuloDao articuloDao;
private final  Conexion con;
private final ClienteDao clienteDao;
private final FacturaClienteDao facturaDao;
private final DetallefacturaDao detalleDao;
    public Mediador() {
        this.con=Conexion.getConexion();
        clienteDao=new ClienteDao(this.con);
        articuloDao=new ArticuloDao(this.con);
        facturaDao=new FacturaClienteDao(this.con);
        detalleDao=new DetallefacturaDao(this.con);
    }

    
    
    @Override
    public boolean agregarCliente(Cliente cliente, Cajero cajero) {
        boolean respuesta=false;
    try {
        con.ConexionPostgres();
        respuesta=clienteDao.registrar(cliente, cajero);
    } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        return respuesta;
    }

    @Override
    public boolean editarCliente(Cliente cliente, Cajero cajero) {
        boolean respuesta=false;
    try {
        con.ConexionPostgres();
        respuesta= clienteDao.actualizar(cliente, cajero);
    } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    return respuesta;
    }

    
    @Override
    public boolean eliminarCliente(Cliente cliente, Cajero cajero) {
        boolean respuesta=false;
    try {
        
        con.ConexionPostgres();
        respuesta= clienteDao.eliminar(cliente.getClieId(), cajero);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    return respuesta;
    }

   

    @Override
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista;
    try {
        
        con.ConexionPostgres();
        return clienteDao.obtener();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        return new ArrayList<Cliente>();
        
      }

    @Override
    public Cliente buscarCliente(int id) {
    try {
        this.con.ConexionPostgres();
        return clienteDao.buscar(id);
    } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }return null;
    }
    
    public Cliente buscarCliente(String id) {
    
    try {
        this.con.ConexionPostgres();
        return clienteDao.buscarPorCedula(id);
        
       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                if(con.getCon()!=null){
                    try {
                        con.cerrar();
                    } catch (SQLException ex) {
                             Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                           }
                    }
                }
     return null;
    }
    public FacturaCliente buscarFactura(int id) {
        try {
            this.con.ConexionPostgres();
            FacturaCliente factura=  facturaDao.buscar(id);
            if(factura==null){
                return null;
            }
            factura.setCliente(clienteDao.buscar(factura.getClieId()));
                factura.setDetallefacturas(detalleDao.obtener(factura));
                for(DetalleFactura detalle:factura.getDetallefacturas()){
                    detalle.setFacturacliente(factura);
                    detalle.setArticulo(articuloDao.buscar(detalle.getArtiId()));
                }
            return factura;
            
        } catch (NullPointerException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                if(con.getCon()!=null){
                    try {
                        con.cerrar();
                    } catch (SQLException ex) {
                             Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                           }
                    }
                }
        return null;
    }

    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean agregarArticulo(Articulo articulo, Cajero cajero) {
        try {
            this.con.ConexionPostgres();
            return articuloDao.registrar(articulo, cajero);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }return false;
    }
    
    public Articulo agregarArticulo2(Articulo articulo, Cajero cajero) {
    try {
        this.con.ConexionPostgres();
        return articuloDao.registrar2(articulo, cajero);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        if(con.getCon()!=null){
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }return null;
    }

  
    @Override
    public Articulo buscarArticulo(int id) {
    try {
        this.con.ConexionPostgres();
        return articuloDao.buscar(id);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
            if(con.getCon()!=null){
                try {
                    con.cerrar();
                    } catch (SQLException ex) {
                            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }   
        }
     return null;
    }

    @Override
    public ArrayList<Articulo> listarArticulos() {
        try {
            this.con.ConexionPostgres();
            return articuloDao.obtener();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con.getCon()!=null){
                try {
                    con.cerrar();
                    } catch (SQLException ex) {
                            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }   
        }
        return new ArrayList<Articulo>();
     }

    @Override
    public boolean agregarFactura(FacturaCliente factura, Cajero cajero) {
        try {
            this.con.ConexionPostgres();
            con.getCon().setAutoCommit(false);
            
            facturaDao.registrar(factura, cajero);
            
            for(DetalleFactura detalle:factura.getDetallefacturas()){
                detalleDao.registrar(detalle, cajero);
            }
            con.getCon().commit();
            return true;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            try {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                con.getCon().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(con.getCon()!=null){
                try {
                    con.cerrar();
                    } catch (SQLException ex) {
                            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                            }
          
                }
            }
        return false;
    }  
    public boolean eliminarFactura(int id, Cajero cajero){
        FacturaCliente factura=this.buscarFactura(id);
        return this.eliminarFactura(factura, cajero);
    }
    
    @Override
    public boolean eliminarFactura(FacturaCliente factura, Cajero cajero) {
    try {
        this.con.ConexionPostgres();
        con.getCon().setAutoCommit(false);
        for(DetalleFactura detalle:factura.getDetallefacturas()){
            detalleDao.eliminar(detalle.getArtiId(), cajero);
        }
        facturaDao.eliminar(factura.getFaclId(), cajero);
          
        
        
        this.con.getCon().commit();
        return true;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }
    
    

    @Override
    public ArrayList<FacturaCliente> listarFacturas() {
        try {
            this.con.ConexionPostgres();
            return facturaDao.obtener();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                if(con.getCon()!=null){
                    try {
                        con.cerrar();
                        } catch (SQLException ex) {
                                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                    }
                }
        return new ArrayList<FacturaCliente>();
    }

    
     public ArrayList<FacturaCliente> listarFacturas(int page) {
         ArrayList<FacturaCliente> lista;
        try {
            this.con.ConexionPostgres();
            lista=facturaDao.obtener(page*10);
            for(FacturaCliente factura:lista){
                factura.setCliente(clienteDao.buscar(factura.getClieId()));
                factura.setDetallefacturas(detalleDao.obtener(factura));
//                for(DetalleFactura detalle:factura.getDetallefacturas()){
//                    detalle.setFacturacliente(factura);
//                }
                
            }
            return lista;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                if(con.getCon()!=null){
                    try {
                        con.cerrar();
                        } catch (SQLException ex) {
                                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                    }
                }
        return new ArrayList<FacturaCliente>();
     }
    
    @Override
    public boolean editarArticulo(Articulo articulo, Cajero cajero) {
            try {
                this.con.ConexionPostgres();
                return articuloDao.actualizar(articulo, cajero);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(con.getCon()!=null){
                    try {
                        con.cerrar();
                        } catch (SQLException ex) {
                                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                    }
                }
            return false;
    }

    
    @Override
    public boolean eliminarArticulo(int id, Cajero cajero) {
            try {
                this.con.ConexionPostgres();
                return articuloDao.eliminar(id, cajero);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;    
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

   