<%-- 
    Document   : cliente_agr
    Created on : 29-ene-2020, 14:26:44
    Author     : blade
--%>
<%@page import="VO.FacturaCliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%ArrayList<FacturaCliente> facturas=(ArrayList<FacturaCliente> )(request.getAttribute("facturas"));%>
<%Integer pagina=Integer.parseInt(request.getAttribute("page").toString());%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" flush="true" />
    <body>
    <jsp:include page="header.jsp" flush="true" />
 <header>
    <div class="container bg-dark my-5 py-2 text-center">
      <h1 class="text-white">Listado de Facturas </h1>
    </div>
    
  </header>
<div class="container" id="app">

      
              <div class="col-12">
                <div class="card">
                  <div class="card-header container-luid">
                      <div class="row justify-content-between">
                           <div col-4>
                                <h4 class="card-title">Detalle de la compra</h4>
                            </div>
                          <div class="btn-group col-4" role="group" aria-label="Basic example">
                              <%if(pagina!=0){%>
                              <button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='Controller?controller=Factura&action=listar&page=<%=pagina-1%>'" >Atras</button>
                               <%}%>
                            <button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='Controller?controller=Factura&action=listar&page=<%=pagina+1%>'">Adelante</button>
                          </div>
                        </div>
                  </div>
                  <div class="card-body">
                    <table class="table table-hover table-striped table-bordered">
                      <thead class="thead-dark">
                        <tr >
                          <th scope="row">ID</th>
                          <th >Fecha</th>
                          <th >Cliente</th>
                          <th >Documento</th>
                          <th >Total</th>
                        </tr>
                      </thead>
                      
                      
                        <% for(FacturaCliente fa:facturas) { %>
                        <tr  >
                            <th scope="row"> <%=fa.getFaclId() %> <button type="button" class="btn btn-info" @click="ver(<%=fa.getFaclId() %>)">Ver</button></th>
                            <td > <%=fa.getFaclFecha() %></td>
                            <td > <%=fa.getCliente().getClieNombre()%> <%=fa.getCliente().getClieApellido() %></td>
                            <td > <%=fa.getCliente().getClieDocumento() %></td>
                            <td > $ <%=fa.getFaclTotal() %></td>
                                 
                        </tr>
                        <% } %>

                        
                        
                      </tbody>
                    </table>
                    
                  </div>
                </div>
              </div>
              <div class="col-4"></div>
          

          </div>
    
   
     
    
 
 
 
 
        <jsp:include page="footer.jsp" flush="true" />
        <script>
            new Vue({
                    el: '#app',
                    data () {
                      return {
                          
                        
                      }
                    },
                 
                  
                  methods:{     
                               ver: function (id) {
                                     
                                    window.location.href = "/factura/Controller?controller=Factura&action=ver&id="+id;
                                                   }

                              ,
                              
            
            
                         
            
            
                         
                           
                           
                           
                           
                         
                          
                          

                       
                  }  
     
            }      
            );
        </script>
    </body>
</html>
