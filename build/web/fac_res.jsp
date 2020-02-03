<%-- 
    Document   : cliente_agr
    Created on : 29-ene-2020, 14:26:44
    Author     : blade
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="VO.Detallefactura"%>
<%@page import="VO.Facturacliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Facturacliente factura=(Facturacliente)(request.getSession().getAttribute("factura"));%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" flush="true" />
    <body>
    <jsp:include page="header.jsp" flush="true" />
 <header>
    <div class="container bg-dark my-5 py-2 text-center">
      <h1 class="text-white">Factura N°  <%=factura.getFaclId()%> </h1>
    </div>
    
  </header>
<div class="container" id="app">

        <div  class="row" >
              <div class="col-12 ">
                 <div class="card " >
                     
                     
                  <div class="card-header container-fluid">
                      <div class="row">
                          <div class="col-8"><h4 class="card-title"><strong>Datos del Cliente</strong></h4></div>
                    
                    <div class="col-3"><h4 class="card-title"> <strong>Fecha:   </strong></h4> <%= new SimpleDateFormat("dd-MM-yyyy").format(factura.getFaclFecha()) %> </div>
                    
                    </div>
                  </div>
                   <div class="card-body container-fluid">
                       <div class="row">
                              <div class="col-4">
                                <ul class="list-group list-group-flush">
                                  <li class="list-group-item text-capitalize">
                                    <strong class="card-title">Documento:</strong> <%=factura.getCliente().getClieDocumento() %>  </li>
                                  <li class="list-group-item text-capitalize">
                                    <strong class="card-title">Direccion:</strong> <%=factura.getCliente().getClieDireccion() %>   </li>
                                  
                                </ul>
                              </div>
                              <div class="col-4">
                                <ul class="list-group list-group-flush">
                                  <li class="list-group-item text-capitalize">
                                    <strong class="card-title">Nombre:</strong> <%=factura.getCliente().getClieNombre() %>   </li>
                                  <li class="list-group-item text-capitalize">
                                    <strong class="card-title">Telefono:</strong> <%=factura.getCliente().getClieTelefono() %>   </li>
                                  
                                </ul>
                              </div>
                              <div class="col-4">
                                <ul class="list-group list-group-flush">
                                  <li class="list-group-item text-capitalize">
                                    <strong class="card-title">Apellido:</strong>   <%=factura.getCliente().getClieApellido() %></li>
                                  <li class="list-group-item text-capitalize">
                                    <strong class="card-title">Email:</strong> <%=factura.getCliente().getClieEmail() %>   </li>
                                  
                                </ul>
                              </div>

                       </div>
                       
                     
                   </div>
                      
                   
                 </div>
              </div>
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4 class="card-title">Detalle de la compra</h4>
                  </div>
                  <div class="card-body">
                    <table class="table table-hover table-striped table-bordered">
                      <thead class="thead-dark">
                        <tr >
                          <th scope="row">ID</th>
                          <th >Articulo</th>
                          <th >Precio por Unidad</th>
                          <th >Cantidad</th>
                          <th >Total</th>
                        </tr>
                      </thead>
                      
                      <% int x=1; %>
                        <% for(Detallefactura fa:factura.getDetallefacturas()) { %>
                        <tr  >
                            <th scope="row"> <%=x++%></th>
                            <td > <%=fa.getArticulo().getArtiNombre() %></td>
                            <td > $ <%=fa.getDefaValorunitario() %></td>
                            <td > <%=fa.getDefaCantidad()%></td>
                            <td > $ <%=fa.getDefaCantidad()*fa.getDefaValorunitario() %></td>
                                 
                        </tr>
                        <% } %>

                        <tr  >
                          <th scope="row"> </th>
                          <th scope="row"> </th>
                          <th scope="row"> </th>
                          <th scope="row"> Total de la Venta</th>
                          <th scope="row"> $ <%=factura.getFaclTotal() %></th>
                               
                      </tr>
                        
                      </tbody>
                    </table>
                    
                  </div>
                </div>
              </div>
              <div class="col-4"></div>
          

          </div>
    
   
     
    </div>
 
 
 
 
        <jsp:include page="footer.jsp" flush="true" />
        
    </body>
</html>
