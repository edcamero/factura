/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logica.Fachada;
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

/**
 *
 * @author blade
 */
@WebServlet(name = "FacturaController", urlPatterns = {"/FacturaController"})
public class FacturaController extends HttpServlet {
    private RequestDispatcher rd = null;
    private Facturacliente factura;
    private int id;

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
                    response.setContentType("text/html;charset=UTF-8");
                    out=response.getWriter();
                //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                
                out.print(gson.toJson(lista));
                out.close();
             break;
             
             
              case "editar":
                  id=Integer.parseInt(request.getParameter("id"));
                    //articulo=fachada.buscarArticulo(id);
                    //factura=fachad
                    
                   // request.setAttribute("articulo", articulo);
                    //HttpSession session = request.getSession(true);
                    
                    rd = request.getRequestDispatcher("/art_edi.jsp");
                    
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
