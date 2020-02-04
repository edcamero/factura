/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logica.Fachada;
import VO.Articulo;
import VO.Cajero;
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
@WebServlet(name = "ArticuloController", urlPatterns = {"/ArticuloController"})
public class ArticuloController extends HttpServlet {
    private RequestDispatcher rd = null;
    private Articulo articulo;
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
                                 
                    rd = request.getRequestDispatcher("/art_agr.jsp");
                     rd.forward(request, response);
             break;
             
             case "listar":
                    
                    ArrayList<Articulo> lista=fachada.listarArticulos();
                    response.setContentType("text/html;charset=UTF-8");
                    out=response.getWriter();
                //ArrayList<Pelicula> pelis=PeliculaDao.listar(Integer.valueOf(request.getParameter("id")));
                
                out.print(gson.toJson(lista));
                out.close();
             break;
             
             
              case "editar":
                  id=Integer.parseInt(request.getParameter("id"));
                    articulo=fachada.buscarArticulo(id);
                    
                    request.setAttribute("articulo", articulo);
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
        HttpSession sesion = (HttpSession)request.getSession(false);
        String action= request.getParameter("action");
        String nombre="";
        Fachada fachada=Fachada.getInstancia();
        PrintWriter out;
        int valor;
        int exist, id;
        Gson gson=new Gson();
        
        //Cajero cajero=new Cajero(1, "enyerson", "camero ","123456");
        Cajero cajero=(Cajero)sesion.getAttribute("cajero");
        switch(action){
            case "agregar":
                //   BufferedReader reader = request.getReader(); //Se toma la fuente de datos de la solicitud
         //Se crea un objeto JSON con la librería de Google
                 // articulo = gson.fromJson(reader, Articulo.class);
                    response.setContentType("text/html;charset=UTF-8");
                    nombre=request.getParameter("artiNombre");
                    valor=Integer.parseInt(request.getParameter("artiValorunitario"));
                    exist=Integer.parseInt(request.getParameter("artiExistencia"));
                    articulo=new Articulo(nombre, valor, exist);
                   // Cliente c=new Cliente(request.getParameter("documento"),request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("direccion"), request.getParameter("telefono"), request.getParameter("email"));
                    articulo=fachada.agregarArticulo2(articulo, cajero);
                    if(articulo.getArtiId()!=0){
                        out=response.getWriter();
                        out.print(gson.toJson(articulo));

                        //ArrayList<Pelicula> pelis=PeliculaDao.l
                        out.close();
                    }else{
                        request.setAttribute("mensaje","eror");
                        response.setStatus(400);
                    }
                    
             break;
             
             
             
             
             case "editar":
                
                    response.setContentType("text/html;charset=UTF-8");
                    nombre=request.getParameter("artiNombre");
                    //Integer.parseInt(request.getParameter("artiId"))
                    id=Integer.parseInt(request.getParameter("artiId"));
                    
//                    nombre=request.getParameter("artiNombre");
                    valor=Integer.parseInt(request.getParameter("artiValorunitario"));
                    exist=Integer.parseInt(request.getParameter("artiExistencia"));
                    articulo=new Articulo(id,nombre, valor, exist);
                   // Cliente c=new Cliente(request.getParameter("documento"),request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("direccion"), request.getParameter("telefono"), request.getParameter("email"));
                    
//                    out=response.getWriter();
//                        out.print(gson.toJson(articulo));
//
//                        //ArrayList<Pelicula> pelis=PeliculaDao.l
//                        out.close();
                   
                    if(fachada.editarArticulo(articulo, cajero)){
                         out=response.getWriter();
                        out.print(gson.toJson(articulo));

                        //ArrayList<Pelicula> pelis=PeliculaDao.l
                        out.close();
                    }else{
                        out=response.getWriter();
                        out.print("error");
                       

                        //ArrayList<Pelicula> pelis=PeliculaDao.l
                        out.close();
                     }
                    
             break;
             
             
             
             
             case "eliminar":
                
                    response.setContentType("text/html;charset=UTF-8");
                    nombre=request.getParameter("artiNombre");
                    //Integer.parseInt(request.getParameter("artiId"))
                    id=Integer.parseInt(request.getParameter("artiId"));
                    
//                    nombre=request.getParameter("artiNombre");
                    valor=Integer.parseInt(request.getParameter("artiValorunitario"));
                    exist=Integer.parseInt(request.getParameter("artiExistencia"));
                    articulo=new Articulo(id,nombre, valor, exist);
                   // Cliente c=new Cliente(request.getParameter("documento"),request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("direccion"), request.getParameter("telefono"), request.getParameter("email"));
                    
//                    out=response.getWriter();
//                        out.print(gson.toJson(articulo));
//
//                        //ArrayList<Pelicula> pelis=PeliculaDao.l
//                        out.close();
                   
                    if(fachada.eliminarArticulo(id, cajero)){
                         out=response.getWriter();
                        out.print(gson.toJson(articulo));

                        //ArrayList<Pelicula> pelis=PeliculaDao.l
                        out.close();
                    }else{
                        out=response.getWriter();
                        out.print("error");
                       

                        //ArrayList<Pelicula> pelis=PeliculaDao.l
                        out.close();
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
