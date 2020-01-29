/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import VO.Articulo;
import VO.Cajero;
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
public class ArticuloDao implements InterfazDao<Articulo>{

    private Conexion con;
    private PreparedStatement pst;
    private ResultSet rs;
    Articulo c;

    @Override
    public boolean registrar(Articulo articulo,Cajero cajero) {
        
            boolean res=false;
            con=Conexion.getConexion();
            //String consulta="select now()";
            String consulta="	INSERT INTO facturacion.articulo( arti_nombre, arti_valorunitario, arti_existencia, \n" +
                    "            arti_resgistradopor)\n" +
                    "    VALUES (?,?, ?,?) returning arti_id;";
            try {
            con.ConexionPostgres();
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, articulo.getArtiNombre());
            pst.setInt(2, articulo.getArtiValorunitario());
            pst.setInt(3, articulo.getArtiExistencia());
            pst.setString(4, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
            
            
            rs=pst.executeQuery();
            while (rs.next()) {
                articulo.setArtiId(rs.getInt(1));
                
            }
            con.cerrar();
            return true;
            
            
            
            
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public ArrayList<Articulo> obtener() {
       ArrayList<Articulo> lista=new ArrayList();
        con=Conexion.getConexion();
        String consulta="select * FROM facturacion.articulo ORDER BY arti_id;";
        
        try {
            con.ConexionPostgres();
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
            rs=pst.executeQuery();
            
            while (rs.next()) {
                Articulo c=new Articulo(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getDate(5),rs.getString(6));
                   // Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                    //System.out.println(c.toString());
                    lista.add(c);
            }
            con.cerrar();
        
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        }
       return lista;
    }

    @Override
    public boolean actualizar(Articulo articulo, Cajero cajero) {
        try {
            con=Conexion.getConexion();
            String consulta="update facturacion.articulo\n" +
                            "set \n" +
                            "arti_nombre=?,\n" +
                            "arti_valorunitario=?,\n" +
                            "arti_existencia=?,\n" +
                            "arti_registradopor=?\n" +
                            "where arti_id=? returning *";
            
            con.ConexionPostgres();
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, articulo.getArtiNombre());
            pst.setInt(2, articulo.getArtiValorunitario());
            pst.setInt(3, articulo.getArtiExistencia());
            pst.setString(4, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
            pst.setInt(5, articulo.getArtiId());
            
            rs=pst.executeQuery();
            
            
            return true;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id, Cajero cajero) {
        try {
            con=Conexion.getConexion();
            String consulta="select pr_d_articulo(?,?)";
            
            con.ConexionPostgres();
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            pst.setInt(1, id);
            pst.setString(2, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
            
            
            rs=pst.executeQuery();
            if(rs.getRow()>0){
                return true;
            }
            
            
            
            
            
            
          
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return false;
         }

    @Override
    public Articulo buscar(int id) {
        
        
      
        con=Conexion.getConexion();
        String consulta="select * FROM facturacion.articulo where arti_id=?;";
        
        try {
            con.ConexionPostgres();
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            
            rs=pst.executeQuery();
            
            while (rs.next()) {
                //System.out.println(rs.getInt(1));
                 c=new Articulo(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getDate(5),rs.getString(6));
                   // Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                    //System.out.println(c.toString());
                
            }
             
            return c;
            
        
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        }
        finally{
            try {
                con.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return null;
    }

 
}
