/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import VO.Articulo;
import VO.Cajero;
import VO.DetalleFactura;
import VO.FacturaCliente;
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
public class DetallefacturaDao implements InterfazDao<DetalleFactura> {
    private Conexion con;
    private PreparedStatement pst;
    private ResultSet rs;
    private ArticuloDao ad;

    public DetallefacturaDao(Conexion con) {
        this.con = con;
    }
    public DetallefacturaDao() {
        this.con = Conexion.getConexion();
    }

    
    
    @Override
    public boolean registrar(DetalleFactura elemento, Cajero cajero) {
        
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
        }finally{
        }
            
            
            
       
        return false;
    
    
    }

    @Override
    public ArrayList<DetalleFactura> obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<DetalleFactura> obtener(FacturaCliente factura) throws SQLException {
            ArrayList<DetalleFactura> lista=new ArrayList<DetalleFactura>();
             DetalleFactura detalle;
             String consulta="select * FROM facturacion.detallefactura where facl_id=?;";
             
             try {
            
                    pst=con.getCon().prepareStatement(consulta);
                    pst.setInt(1,factura.getFaclId());
                    rs=pst.executeQuery();

                    while (rs.next()) {
                        
                         detalle=new DetalleFactura(rs.getInt(1), rs.getInt(2), rs.getInt(4),rs.getInt(5), rs.getDate(6), rs.getString(7));
                         //System.out.println(detalle);
                         lista.add(detalle);
                        //Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
                       // lista.add(c);
             }
                    return lista;
             
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return lista;
    }

    @Override
    public DetalleFactura buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(DetalleFactura articulo, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id, Cajero cajero) {
    try {
                con=Conexion.getConexion();
                String consulta="select pr_d_detallefactura(?,?);";

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

}
