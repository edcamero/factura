/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import VO.Cajero;
import VO.Cliente;
import VO.Detallefactura;
import VO.Facturacliente;
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
public class FacturaclienteDao implements InterfazDao<Facturacliente>{
 private Conexion con;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public boolean registrar(Facturacliente factura, Cajero cajero) {
            boolean res=false;
            con=Conexion.getConexion();
            DetallefacturaDao dd=new DetallefacturaDao(con);
            //String consulta="select now()";
            String consulta="insert into  facturacion.facturacliente(clie_id,facl_total,facl_registradopor)\n" +
                            "values (?,?,?) returning *;";
            
            
         try {
             con.ConexionPostgres();
             con.getCon().setAutoCommit(false);
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


            for(Detallefactura df:factura.getDetallefacturas()){
                dd.registrar(df, cajero);
            }
             
             con.getCon().commit();
             return true;
             
             
             
             
         } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
             Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
             try {
                 con.getCon().rollback();
             } catch (SQLException ex1) {
                 Logger.getLogger(FacturaclienteDao.class.getName()).log(Level.SEVERE, null, ex1);
             }
         }
         finally{
             try {
                 con.cerrar();
             } catch (SQLException ex) {
                 Logger.getLogger(FacturaclienteDao.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     
        return res;
    }

    @Override
    public ArrayList<Facturacliente> obtener() {
        
        
            ArrayList<Facturacliente> lista=new ArrayList();
             con=Conexion.getConexion();
             String consulta="select * FROM facturacion.facturacliente ORDER BY clie_id;";
             
             try {
            
                    con.ConexionPostgres();
                    pst=con.getCon().prepareStatement(consulta,ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    rs=pst.executeQuery();
                    
                    while (rs.next()) {
                        Cliente cliente=new ClienteDao().buscar(rs.getInt(2));
                         //Cliente c=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                        //System.out.println(c.toString());
                        Facturacliente fc=new Facturacliente(rs.getInt(1), cliente,rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6));
                        //fc.setDetallefacturas(detallefacturas);
                        new DetallefacturaDao(con).obtener(fc);
                        lista.add(fc);
             }
             con.cerrar();
         } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
             Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return lista;
       
    
    }

    @Override
    public Facturacliente buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Facturacliente articulo, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id, Cajero cajero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   


  
   

   
}
