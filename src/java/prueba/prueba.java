/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import DAO.ArticuloDao;
import DAO.ClienteDao;
import DAO.FacturaclienteDao;
import Logica.Fachada;
import VO.Articulo;
import VO.Cajero;
import VO.Cliente;
import VO.Detallefactura;
import VO.Facturacliente;
import com.google.gson.Gson;

/**
 *
 * @author blade
 */
public class prueba {
   
    
    public static void main(String []arg){
       ArticuloDao ad=new ArticuloDao();
       ClienteDao cd=new ClienteDao();
       FacturaclienteDao fcd=new FacturaclienteDao();
       
        Gson gson=new Gson();
       
       //Cliente cliente=new Cliente("1092343350","vanessa","sayago", "villa del rosario", "3123496942", "vanessasayago@hotmail.com");
      Articulo c=new Articulo("AZ carta",7000, 100);
       Cajero cajero=new Cajero(1, "enyerson", "camero ","123456");
       //c=Fachada.getInstancia().agregarArticulo2(c, cajero);
//        System.out.println(c.toString());
//        Articulo c=ad.buscar(9);
//        c.setArtiExistencia(666);
//        Fachada.getInstancia().editarArticulo(c, cajero);
//        System.out.println(Fachada.getInstancia().editarArticulo(c, cajero));
        
        //ad.actualizar(c, cajero);
//      
//       Cliente cliente=Fachada.getInstancia().buscarCliente("10909090");
        System.out.println(Fachada.getInstancia().listarFacturas());
//       
//       Facturacliente factura=new Facturacliente(cliente);
//        factura.AgregarArticulo(5, c);
//        System.out.println(gson.toJson(factura.toString()));
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
       

//            for (Facturacliente fc:fcd.obtener()){
//                    System.out.println(fc.toString());
//                    for(Detallefactura detalle:fc.getDetallefacturas()){
//                        System.out.println("                  "+detalle.toString()+"\n");
//                    }
//            }
       }
        
       
    
}
