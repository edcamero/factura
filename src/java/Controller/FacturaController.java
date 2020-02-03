/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logica.Fachada;
import VO.Articulo;
import VO.Cajero;
import VO.Cliente;
import VO.Detallefactura;
import VO.Facturacliente;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author blade
 */
@WebServlet(name = "FacturaController", urlPatterns = {"/FacturaController"})
public class FacturaController extends HttpServlet {
    private RequestDispatcher rd = null;
    private Facturacliente factura;
    private int id;
    HttpSession misession;
    private Cliente cliente;
    private Articulo articulo;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        String action= request.getParameter("action");
        PrintWriter out;
        Gson gson=new Gson();
        
        Fachada fachada=Fachada.getInstancia();
        
        int  id;
        switch(action){
            case "agregar":
                                   
                    rd = request.getRequestDispatcher("/fac_agr.jsp");
                     rd.forward(request, response);
             break;
             
             case "listar":
                    
                    ArrayList<Facturacliente> lista=fachada.listarFacturas();
                    request.setAttribute("facturas", lista);
                    rd = request.getRequestDispatcher("/fac_lis.jsp");
                    rd.forward(request, response);
                    
             break;
             
             
             case "ver":
                    id=Integer.parseInt(request.getParameter("id"));
                    factura=fachada.buscarFactura(id);
                    request.getSession().setAttribute("factura", factura);
                    rd = request.getRequestDispatcher("/fac_res.jsp");
                     rd.forward(request, response);
             break;
             
            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action= request.getParameter("action");
        PrintWriter out;
        Gson gson=new Gson();
        Fachada fachada=Fachada.getInstancia();
        
        switch(action){
            case "iniciar":
                cliente=fachada.buscarCliente(Integer.parseInt(request.getParameter("id_cliente")));
                factura=new Facturacliente(cliente);
                misession= request.getSession(true);
                misession.setAttribute("factura",factura);
                 out=response.getWriter();
                //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                
                out.print(gson.toJson(factura));
                out.close();
             
             break;
            case "agregar_detalle":
                articulo=fachada.buscarArticulo(Integer.parseInt(request.getParameter("id_articulo")));
                int cant=Integer.parseInt(request.getParameter("cantidad"));
                misession= (HttpSession) request.getSession();
                factura=(Facturacliente)misession.getAttribute("factura");
                factura.AgregarArticulo(cant, articulo);
                misession.setAttribute("factura",factura);
                out=response.getWriter();
                //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                
                out.print(gson.toJson(articulo));
                out.close();
             
             break;
            case "eliminar_deta":
                int  index=Integer.parseInt(request.getParameter("index"));
                misession= (HttpSession) request.getSession();
                factura=(Facturacliente)misession.getAttribute("factura");
                Detallefactura detalle=factura.getDetallefacturas().remove(0);
                misession.setAttribute("factura",factura);
                out=response.getWriter();
                //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                
                out.print(gson.toJson(detalle.getArticulo()));
                out.close();
             
             break;
             case "guardar":
                Cajero cajero=new Cajero(1, "enyerson", "camero ","123456");
                misession= (HttpSession) request.getSession();
                factura=(Facturacliente)misession.getAttribute("factura");
                if(fachada.agregarFactura(factura, cajero)){
                    
                    request.getSession().setAttribute("factura", factura);
                    // rd = request.getRequestDispatcher("/fac_res.jsp");
                    // rd.forward(request, response);
                     response.setStatus(202);
                }else{
                        response.sendError( response.SC_NOT_FOUND, "No se pudo crear ña factura");
                }
                 
             
             break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
