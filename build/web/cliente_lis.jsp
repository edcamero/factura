<%-- 
    Document   : cliente_agr
    Created on : 29-ene-2020, 14:26:44
    Author     : blade
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" flush="true" />
    <body>
    <jsp:include page="header.jsp" flush="true" />
 <header>
    <div class="container bg-dark my-5 py-2 text-center">
         <h1 class="text-white">Lista de Clientes   
          
        </h1>
    </div>
    
  </header>
 
    <div class="container" id="app">

      <table class="table table-hover table-striped">
        <thead class="thead-dark">
          <tr >
            <th scope="col">ID</th>
            <th scope="col">Documento</th>
            <th scope="col">Nombres</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Direccion</th>
            <th scope="col">Telefono</th>
            <th scope="col">Email</th>
            <th scope="col">Acciones</th>
            
          </tr>
        </thead>
        
        <tbody>
         
          <tr  v-for="(cliente,index) in clientes">
              <th scope="row">{{ cliente.clieId }}</th>
                    <td> {{ cliente.clieDocumento }} </td>
                    <td> {{ cliente.clieNombre }} </td>
                    <td> {{ cliente.clieApellido}} </td>
                    <td> {{ cliente.clieDireccion}} </td>
                    <td> {{ cliente.clieTelefono }} </td>
                    <td> {{ cliente.clieEmail }} </td>
                    
            
            
            
            <td>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary btn-sm" @click="editarCliente( cliente.clieId )" >Editar</button>
                    <button type="button" class="eliminar btn btn-danger btn-sm"@click="eliminarCliente(cliente,index)"  >Eliminar</button>
                </div>
            </td>
          </tr>
          
          
        </tbody>
      </table>
      
     
    </div>
 
 
        <jsp:include page="footer.jsp" flush="true" />
        <script>
            new Vue({
              el: '#app',
              data () {
                return {
                  clientes: null,
                  cli:{'ClieNombre':''},
                  mensaje:"",
                }
              },
              mounted () {
                axios
                  .post('/factura/Controller?controller=Cliente&action=listar')
                  .then(response => (this.clientes= response.data))
              },
                methods: {
                            editarCliente: function (id) {
                              // `this` inside methods point to the Vue instance
                             //axios.get('/factura/Controller?controller=Cliente&action=editar')
                             window.location.href = "/factura/Controller?controller=Cliente&action=editar&id="+id; 
                                    

                            },
                             eliminarCliente: function (cliente,index) {
                              // `this` inside methods point to the Vue instance

                              axios
                                .post('/factura/Controller?controller=Cliente&action=eliminar&id='+cliente.clieId)
                                .then(response => {
                                                   this.cli=response.data;
                                                   this.clientes.splice(index, 1);
    
                                                    this.mensaje="Se elimino el Cliente:"+this.cli.clieNombre+" "+cli.clieApellido;
                                                } ).catch(function(err) {
                                         this.mensaje="Error al eliminar el Cliente:"+this.cli.clieNombre+" "+cli.clieApellido;
                                    }).then(function() {
                                            alert(this.mensaje);});
                            }
                     }
                                      
                    
                 
              
            }
            );
        </script>
    </body>
</html>
