<%-- 
    Document   : cliente_agr
    Created on : 29-ene-2020, 14:26:44
    Author     : blade
--%>

<%@page import="VO.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Cliente cliente=(Cliente)(request.getAttribute("cliente"));%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" flush="true" />
    <body>
    <jsp:include page="header.jsp" flush="true" />
 <header>
    <div class="container bg-dark my-5 py-2 text-center">
      <h1 class="text-white">Editar Cliente  

</h1>
    </div>
    
  </header>
    <div class="container">


        <form action="/factura/Controller?controller=Cliente&action=editar&id=<%=cliente.getClieId()%>" method="POST"  role="form" data-toggle="validator">

            <div class="row">
                  <div class="col form-group">
                    <label for="nombre"><strong>Nombre del Cliente</strong></label>
                    <input type="text" class="form-control" name="nombre" id="nombre" 
                           value="<%=cliente.getClieNombre() %>" required aria-describedby="emailHelp" placeholder="Ingrese el nombre">
                    
                  </div>
                  <div class="col form-group">
                      <label for="Apellido"><strong>Apellido del Cliente</strong></label>
                      <input type="text" class="form-control" name="apellido" id="apellido"
                             value="<%=cliente.getClieApellido()%>" required aria-describedby="emailHelp" placeholder="Ingrese el apellido">
                      
                  </div>
             </div>
             
              <br>

             <div class="row">
              <div class="col form-group">
                <label for="documento"><strong>Documento del Cliente</strong></label>
                <input type="text" class="form-control" name="documento" id="documento" 
                       value="<%=cliente.getClieDocumento()%>" required aria-describedby="emailHelp" placeholder="Ingrese el documento">
                
              </div>
              <div class="col form-group">
                  <label for="telefono"><strong>Telefono del Cliente</strong></label>
                  <input type="text" class="form-control" name="telefono" id="telefono" 
                       value="<%=cliente.getClieTelefono()%>"  required aria-describedby="emailHelp" placeholder="Ingrese el telefono">
                  
              </div>
         </div>



         <br>

         <div class="row">
          <div class="col form-group">
            <label for="direccio"><strong>Direccion  del Cliente</strong></label>
            <input type="text" class="form-control" name="direccion" id="direccion" 
                   value="<%=cliente.getClieDireccion()%>"  require daria-describedby="emailHelp" placeholder="Ingrese el direccion">
            
          </div>
          <div class="col form-group">
              <label for="email"><strong>E-mail del Cliente</strong></label>
              <input type="email" class="form-control" name="email" id="email" 
                     value="<%=cliente.getClieEmail()%>"  required aria-describedby="emailHelp" placeholder="Ingrese el email">
              
          </div>

         
     </div>

     <br>
            <div>
              <button type="submit" class="btn btn-primary btn-lg btn-block">Enviar</button>
            </div>
            
          </form>
    </div>
 
 
 
 
        <jsp:include page="footer.jsp" flush="true" />
                <script>
            new Vue({
              el: '#app',
              data () {
                return {
                 Cliente:{}
                
                }
              },
              mounted () {
               
              },
                methods: {
                     
                    }
                                      
                    
                 
              
            }
            );
        </script>
    </body>
</html>
