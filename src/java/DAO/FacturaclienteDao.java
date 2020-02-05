/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import VO.Cajero;
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
public class FacturaclienteDao implements InterfazDao<FacturaCliente>{
 private Conexion con;
    private PreparedStatement pst;
    private ResultSet rs;

    public FacturaclienteDao(Conexion con) {
        this.con = con;
    }
    
    
    
    @Override
    public boolean registrar(FacturaCliente factura, Cajero cajero) {
            
            //String consulta="select now()";
            String consulta="insert into  facturacion.facturacliente(clie_id,facl_total,facl_registradopor)\n" +
                            "values (?,?,?) returning *;";
            
            
         try {
             
             pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             
             pst.setInt(1, factura.getCliente().getClieId());
             pst.setInt(2, factura.getFaclTotal());
             pst.setString(3, "cajero: "+cajero.getCajeId()+" "+cajero.getCajeNombre()+" "+cajero.getCajeApellido());
             
             
             rs=pst.executeQuery();
             while (rs.next()) {
                 factura.setFaclId(rs.getInt(1));
                 
             }
//             for (Iterator<Detallefactura> it = factura.getDetallefacturas().iterator(); it.hasNext();) {
//                Detallefactura a = it.next();
//                dd.registrar(a, cajero);
//            }


           
             
             return true;
             
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
             try {
                 con.getCon().rollback();
             } catch (SQLException ex1) {
                 Logger.getLogger(FacturaclienteDao.class.getName()).log(Level.SEVERE, null, ex1);
             }
         }
     
        return false;
    }

    @Override
    public ArrayList<FacturaCliente> obtener() {
         
        
            ArrayList<FacturaCliente> lista=new ArrayList();
             
             String consulta="select * FROM facturacion.facturacliente ORDER BY facl_id  DESC;";
             
             try {
            
                    pst=con.getCon().prepareStatement(consulta);
                    rs=pst.executeQuery();
                    
                    while (rs.next()) {
                   //     Cliente cliente=clienteDao.buscar(rs.getInt(2));
                         //Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
 //                       Facturacliente fc=new Facturacliente(rs.getInt(1), cliente,rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6));
                        //fc.setDetallefacturas(detallefacturas);
 //                       detalleDao.obtener(fc);
  //                      lista.add(fc);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return lista;
       
    
    }

    
    
    
      public ArrayList<FacturaCliente> obtener(int page) {
         
         
        
            ArrayList<FacturaCliente> lista=new ArrayList();
             con=Conexion.getConexion();
             String consulta="select * FROM facturacion.facturacliente ORDER BY facl_id  DESC LIMIT 10 OFFSET "+page+";";
             
             try {
            
                    con.ConexionPostgres();
                    pst=con.getCon().prepareStatement(consulta);
                    rs=pst.executeQuery();
                    
                   while (rs.next()) {
                        FacturaCliente factura=new FacturaCliente(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6));
                        lista.add(factura);
            }
             
         } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return lista;
       
    
    }

    @Override
    public FacturaCliente buscar(int id) {
        
        con=Conexion.getConexion();
             String consulta="select * FROM facturacion.facturacliente where  facl_id=?;";
             
             try {
            
                    con.ConexionPostgres();
                    pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                     pst.setInt(1, id);
                    rs=pst.executeQuery();
                    
                    while (rs.next()) {
                        return new FacturaCliente(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6));
                        
             }
            
         } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
             
         }
         return null;}
    

    @Override
    public boolean actualizar(FacturaCliente articulo, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   


  
   

   
}
