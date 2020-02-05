/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import VO.Cajero;
import VO.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blade
 */
public class ClienteDao implements InterfazDao<Cliente>{
     private Conexion con;
    private PreparedStatement pst;
    private ResultSet rs;

    public ClienteDao(Conexion con) {
        this.con = con;
    }

    @Override
    public boolean registrar(Cliente cliente, Cajero cajero) {
         
         try {
             boolean res=false;
             String consulta="INSERT INTO facturacion.cliente(clie_documento,clie_nombre,clie_apellido,clie_direccion,clie_telefono,clie_email,clie_registradopor      )\n" +
                     "    VALUES (?,?,?,?,?,?,?) returning clie_id;";
             
             
             
             
             pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             pst.setString(1,cliente.getClieDocumento());
             pst.setString(2,cliente.getClieNombre());
             pst.setString(3,cliente.getClieApellido());
             pst.setString(4,cliente.getClieDireccion());
             pst.setString(5,cliente.getClieTelefono());
             pst.setString(6,cliente.getClieEmail());
             pst.setString(7, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
             
             
             rs=pst.executeQuery();
             while (rs.next()) {
                 cliente.setClieId(1);
                 
             }
             
             return true;
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
            if(con.getCon()!=null){
                try {
                    con.cerrar();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         
         
          return false;  
    }

    @Override
    public ArrayList<Cliente> obtener() {
            ArrayList<Cliente> lista=new ArrayList();
             
             String consulta="select * FROM facturacion.cliente ORDER BY clie_id;";
             
             try {
            
                    pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    rs=pst.executeQuery();

                    while (rs.next()) {
                        //Cliente(int clieId, String clieDocumento, String clieNombre, String clieApellido, String clieDireccion, String clieTelefono, String clieEmail, Date clieFechacambio, String clieRegistradopor) {
        
                         Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
                        lista.add(c);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return lista;
    }

    @Override
    public boolean actualizar(Cliente cliente, Cajero cajero) {
       try {
            String consulta="update facturacion.cliente set\n" +
                                                "clie_documento=?,\n" +
                                                "clie_nombre=?,\n" +
                                                "clie_apellido=?,\n" +
                                                "clie_direccion=?,\n" +
                                                "clie_telefono=?,\n" +
                                                "clie_email=?,\n" +
                                                "clie_registradopor=?\n" +
                                                "where clie_id=? returning *";
            
            
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, cliente.getClieDocumento());
            pst.setString(2, cliente.getClieNombre());
            pst.setString(3, cliente.getClieApellido());
            pst.setString(4, cliente.getClieDireccion());
            pst.setString(5, cliente.getClieTelefono());
            pst.setString(6, cliente.getClieEmail());
            pst.setString(7,"cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
            pst.setInt(8, cliente.getClieId());
            
            rs=pst.executeQuery();
            
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id, Cajero cajero) {
        try {
                con=Conexion.getConexion();
                String consulta="select pr_d_cliente(?,?);";

                con.ConexionPostgres();
                pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                pst.setInt(1, id);
                pst.setString(2, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());


                rs=pst.executeQuery();
                
                    return true;
                
                
            
            
            
          
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
          return false;
    }

    @Override
    public Cliente buscar(int id) {
          Cliente cliente=null;
        
            
             String consulta="select * FROM facturacion.cliente where clie_id=?;";
             
             try {
            
                   
                    pst=con.getCon().prepareStatement(consulta);
                    pst.setInt(1, id);
                    rs=pst.executeQuery();

                    while (rs.next()) {
                        //Cliente(int clieId, String clieDocumento, String clieNombre, String clieApellido, String clieDireccion, String clieTelefono, String clieEmail, Date clieFechacambio, String clieRegistradopor) {
        
                         cliente= new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
                        break;
                        
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  cliente;
    }
    
    
    
    public Cliente buscarPorCedula(String id) {
       
        
            
             String consulta="select * FROM facturacion.cliente where clie_documento=?;";
             
             try {
            
                    
                    pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    pst.setString(1, id);
                    rs=pst.executeQuery();

                    while (rs.next()) {
                        //Cliente(int clieId, String clieDocumento, String clieNombre, String clieApellido, String clieDireccion, String clieTelefono, String clieEmail, Date clieFechacambio, String clieRegistradopor) {
        
                         return new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
                        
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  null;
    }
}
