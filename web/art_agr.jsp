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
      <h1 class="text-white">Articulos  </h1>
    </div>
    
  </header>
<div class="container" id="app">

        <div  class="container" >

            <div class="row">
                  <div class="col form-group">
                    <label for="nombre"><strong>Nombre del Articulo</strong></label>
                    <input v-model="articulo.artiNombre" type="text" class="form-control" name="nombre" id="nombre" aria-describedby="emailHelp" placeholder="Ingrese el nombre">
                    
                  </div>
                  <div class="col form-group">
                      <label for="valor"><strong>Valor a vender</strong></label>
                      <input v-model="articulo.artiValorunitario" type="text" class="form-control" name="valor" id="valor" aria-describedby="emailHelp" placeholder="Ingrese el valor del articulo">
                      
                  </div>
                <div class="col form-group">
                      <label for="existencia"><strong>Cantidad disponible</strong></label>
                      <input v-model="articulo.artiExistencia" type="text" class="form-control" name="existencia" id="existencia" aria-describedby="emailHelp" placeholder="Ingrese la cantidad de existencia">
                      
                  </div>
                <div class="col align-self-center">
                    <button v-if="editable" class="btn btn-warning btn-block "  @click="guardarCambios()"><strong>Guardar Cambios</strong></button>
                    <button v-else class="btn btn-success  btn-block "  @click="agregar()"><strong>Guardar</strong></button>
                </div>
             </div>
             
              <br>

          </div>
    
    <br>
    
    <table class="table table-hover table-striped">
        <thead class="thead-dark">
          <tr >
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Valor</th>
            <th scope="col">Existencia</th>
            <th scope="col">Acciones</th>
          </tr>
        </thead>
        
        <!--<tbody>-->
         
          <tr  v-for="(arti,index) in articulos">
              <th scope="row">{{ arti.artiId}}</th>
                    <td> {{ arti.artiNombre }} </td>
                    <td> {{ arti.artiValorunitario }} </td>
                    <td> {{ arti.artiExistencia}} </td>
                    
            
           
            <td>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary btn-sm" @click="editar(index)" >Editar</button>
                    <button type="button" class="eliminar btn btn-danger btn-sm" @click="eliminarCliente(cliente,index)"  >Eliminar</button>
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
                        articulos:[],
                        articulo:{'artiId':0,'artiNombre':'','artiValorunitario':'','artiExistencia':''},
                        aux:{},
                        editable:false
                      }
                    },
                  mounted() {
                      axios
                        .get('/factura/Controller?controller=Articulo&action=listar')
                        .then(response => (this.articulos= response.data))
                  },
                  
                  methods:{     
                               agregar: function () {
                            
                                     axios.post('/factura/Controller?controller=Articulo&action=agregar',this.createdFormData())
                                    .then(response => (this.aux=response.data,
                                                        this.articulos.push(this.aux),
                                                       this.articulo={'artiId':'','artiNombre':'','artiValorunitario':'','artiExistencia':''}))

                              },editar:function (index){
                                  this.editable=true;
                                 this.articulo=this.articulos[index];
                                 this.aux=index;
                              },
                              
            
            
                               guardarCambios:function(){
                                  this.editable=false;
                                  
                                  axios.post('/factura/Controller?controller=Articulo&action=editar',this.createdFormData())
                                    .then(response => (
                                                        this.articulos[aux]=response.data,
                                                       this.articulo={'artiId':'','artiNombre':'','artiValorunitario':'','artiExistencia':''}))

                              },

                          
                                
                          
                          
                          

                          createdFormData : function (){
                                const params = new URLSearchParams();
                                for(var key in this.articulo){
                                   params.append(key, this.articulo[key]);
                                }
                                return params;
                            }
                  }  
     
            }      
            );
        </script>
    </body>
</html>
