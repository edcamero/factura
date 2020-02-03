/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import VO.Articulo;
import VO.Cajero;
import VO.Detallefactura;
import VO.Facturacliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blade
 */
public class DetallefacturaDao implements InterfazDao<Detallefactura> {
    private Conexion con;
    private PreparedStatement pst;
    private ResultSet rs;

    public DetallefacturaDao(Conexion con) {
        this.con = con;
    }

    
    
    @Override
    public boolean registrar(Detallefactura elemento, Cajero cajero) {
        
        try {
            
            //String consulta="select now()";
            String consulta="INSERT INTO facturacion.detallefactura(arti_id,facl_id,defa_cantidad,defa_valorunitario,defa_registradopor)\n" +
                    "VALUES(?,?,?,?,?) returning *;";
            
            
            pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1,elemento.getArticulo().getArtiId());
            pst.setInt(2,elemento.getFacturacliente().getFaclId());
            pst.setInt(3, elemento.getDefaCantidad());
            pst.setInt(4, elemento.getArticulo().getArtiValorunitario());
            
            pst.setString(5, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
            
            
            rs=pst.executeQuery();
            while (rs.next()) {
                elemento.setDefaId(rs.getInt(1));
                
            }
            
            return true;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(DetallefacturaDao.class.getName()).log(Level.SEVERE, null, ex);
                con.getCon().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DetallefacturaDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
            
            
            
       
        return false;
    
    
    }

    @Override
    public ArrayList<Detallefactura> obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void obtener(Facturacliente factura) throws SQLException {
            ArticuloDao ad=new ArticuloDao();
            ArrayList<Detallefactura> lista=new ArrayList();
             con=Conexion.getConexion();
             String consulta="select * FROM facturacion.detallefactura where facl_id=?;";
             
             try {
            
                    con.ConexionPostgres();
                    pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    pst.setInt(1,factura.getFaclId());
                    rs=pst.executeQuery();

                    while (rs.next()) {
                        //Cliente(int clieId, String clieDocumento, String clieNombre, String clieApellido, String clieDireccion, String clieTelefono, String clieEmail, Date clieFechacambio, String clieRegistradopor) {
                         Articulo articulo=ad.buscar(rs.getInt(2));
                         Detallefactura detalle=new Detallefactura(rs.getInt(1), articulo, factura, rs.getInt(4),rs.getInt(5), rs.getDate(6), rs.getString(7));
                         factura.getDetallefacturas().add(detalle);
                        //Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
                       // lista.add(c);
             }
             
         } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
                con.cerrar(); 
             }
         //return lista;
    }

    @Override
    public Detallefactura buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Detallefactura articulo, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
