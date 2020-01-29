/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import DAO.ArticuloDao;
import DAO.ClienteDao;
import DAO.FacturaclienteDao;
import VO.Detallefactura;
import VO.Facturacliente;

/**
 *
 * @author blade
 */
public class prueba {
   
    
    public static void main(String []arg){
       ArticuloDao ad=new ArticuloDao();
       ClienteDao cd=new ClienteDao();
       FacturaclienteDao fcd=new FacturaclienteDao();
       
       
       //Cliente cliente=new Cliente("1092343350","vanessa","sayago", "villa del rosario", "3123496942", "vanessasayago@hotmail.com");
       //Articulo c=new Articulo("AZ",7000, 100);
//       Cajero cajero=new Cajero(1, "enyerson", "camero ","123456");
//         Articulo c=ad.buscar(1);
//      
//       Cliente cliente=cd.buscar(1);
//       
//       Facturacliente factura=new Facturacliente(cliente);
//       factura.AgregarArticulo(5, c);
//       fcd.registrar(factura, cajero);
       //cliente.setClieApellido("Sayago Martinez");
       //System.out.println(cd.buscar(3).getClieApellido());
       //cd.registrar(cliente, cajero);
        //System.out.println(cd.eliminar(3, cajero));
        //ad.registrar(c, cajero);
       // ad.eliminar(5, cajero);
//       for(Cliente a:cd.obtener()){
//           System.out.println(a.getClieNombre());
//       }
       

            for (Facturacliente fc:fcd.obtener()){
                    System.out.println(fc.toString());
                    for(Detallefactura detalle:fc.getDetallefacturas()){
                        System.out.println("                  "+detalle.toString()+"\n");
                    }
            }
       }
        
       
    
}
