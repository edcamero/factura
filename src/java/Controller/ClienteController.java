/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logica.Fachada;
import VO.Cajero;
import VO.Cliente;
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
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {
RequestDispatcher rd = null;
 
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
        Gson gson=new Gson();
       
        Fachada fachada=Fachada.getInstancia();
        Cliente cliente;
        int  id;
        PrintWriter out;
        Cajero cajero=new Cajero(1, "enyerson", "camero ","123456");
        switch(action){
            case "agregar":
           
                    rd = request.getRequestDispatcher("/cliente_agr.jsp");
                     rd.forward(request, response);
             break;
             
             case "listar":
                    
                    //HttpSession session = request.getSession(true);
                    
                    rd = request.getRequestDispatcher("/cliente_lis.jsp");
                    
                    rd.forward(request, response);
             break;
             
             
              case "editar":
                  id=Integer.parseInt(request.getParameter("id").toString());
                    
                    cliente=fachada.buscarCliente(id);
                    request.setAttribute("cliente", cliente);
                    //HttpSession session = request.getSession(true);
                    
                    rd = request.getRequestDispatcher("/cliente_edi.jsp");
                    
                    rd.forward(request, response);
             break;
             
              case "buscar":
                   cliente=fachada.buscarCliente(request.getParameter("id").toString());
                   response.setContentType("text/html;charset=UTF-8");
                    out=response.getWriter();
                    
                        
                   //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                   if(cliente!=null){
                  out.print(gson.toJson(cliente));
                    }else{
                   
                   
                   response.sendError( response.SC_NOT_FOUND, "Error a eliminar cliente.");
                    
                   }
                    out.close();
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
        Gson gson=new Gson();
        String action= request.getParameter("action");
        Fachada fachada=Fachada.getInstancia();
        PrintWriter out;
        Cliente cliente;
        
        Cajero cajero=new Cajero(1, "enyerson", "camero ","123456");
        switch(action){
            case "agregar":
                
                    response.setContentType("text/html;charset=UTF-8");
                    
                    Cliente c=new Cliente(request.getParameter("documento"),request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("direccion"), request.getParameter("telefono"), request.getParameter("email"));
                    
                    if(fachada.AgregarCliente(c, cajero)){
                         response.setContentType("text/html;charset=UTF-8");
                         rd = request.getRequestDispatcher("/cliente_lis.jsp");
                    
                    rd.forward(request, response);
                    }else{
                        request.setAttribute("mensaje","eror");
                        response.setStatus(400);
                    }
                    
             break;
             
            case "listar":
                 ArrayList<Cliente> lista=fachada.ListarClientes();
                 response.setContentType("text/html;charset=UTF-8");
                 out=response.getWriter();
                //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                
                out.print(gson.toJson(lista));
                out.close();
            break;
            
            case "eliminar":
                Cliente cli=fachada.buscarCliente(Integer.parseInt(request.getParameter("id")));
                       
                
                 cajero=new Cajero(1, "enyerson", "camero ","123456");
                 if(fachada.eliminarCliente(cli, cajero)){
                      response.setContentType("text/html;charset=UTF-8");
                       out=response.getWriter();
                       out.print(gson.toJson(cli));
                       
                       //ArrayList<Pelicula> pelis=PeliculaDao.l
                       out.close();
                 }else{
                     response.sendError( response.SC_NOT_FOUND, "Error a eliminar cliente.");
                 }
                 
                 
                 
               
               
            break;
            
            
            case "editar":
               
                cliente=new Cliente(request.getParameter("documento"),request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("direccion"), request.getParameter("telefono"), request.getParameter("email"));       
                cliente.setClieId(Integer.parseInt(request.getParameter("id")));
                  cajero=new Cajero(1, "enyerson", "camero ","123456");
                 if(fachada.editarCliente(cliente, cajero)){
                      response.setContentType("text/html;charset=UTF-8");
                       rd = request.getRequestDispatcher("/cliente_lis.jsp");
                    
                    rd.forward(request, response);
                 }else{
                     response.sendError( response.SC_NOT_FOUND, "Error a eliminar cliente.");
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
