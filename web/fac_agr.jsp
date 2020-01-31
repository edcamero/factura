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
      <h1 class="text-white">Factura </h1>
    </div>
    
  </header>
<div class="container" id="app">

    
    
    
    
        <div  class="container" id="articulo">

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
    <div class="row">
                            <div class="col-md-4 order-md-2 mb-4">
                                  <h4 class="d-flex justify-content-between align-items-center mb-3">
                                    <span class="text-muted">Your cart</span>
                                    <span class="badge badge-secondary badge-pill">3</span>
                                  </h4>
                                  <ul class="list-group mb-3">
                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                      <div>
                                        <h6 class="my-0">Product name</h6>
                                        <small class="text-muted">Brief description</small>
                                      </div>
                                      <span class="text-muted">$12</span>
                                    </li>

                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                      <div>
                                        <h6 class="my-0">Third item</h6>
                                        <small class="text-muted">Brief description</small>
                                      </div>
                                      <span class="text-muted">$5</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                      <div class="text-success">
                                        <h6 class="my-0">Promo code</h6>
                                        <small>EXAMPLECODE</small>
                                      </div>
                                      <span class="text-success">-$5</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between">
                                      <span>Total (USD)</span>
                                      <strong>$20</strong>
                                    </li>
                                  </ul>

                                  <form class="card p-2">
                                    <div class="input-group">
                                      <input type="text" class="form-control" placeholder="Promo code">
                                      <div class="input-group-append">
                                        <button type="submit" class="btn btn-secondary">Redeem</button>
                                      </div>
                                    </div>
                                  </form>
                                </div>
        
        
        
        
        
        
        
        <div class="col-md-8 order-md-1 mb-4">
                    <table class="table table-hover table-striped">
                <thead class="thead-dark">
                  <tr >
                    <th scope="col">ID</th>
                    <th scope="col">Nombre y Valor</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col">Total de articulo</th>
                    <th scope="col">Acciones</th>
                  </tr>
                </thead>

                <!--<tbody>-->

                  <tr  v-for="(arti,index) in articulos">
                      <th scope="row">{{ arti.artiId}}</th>
                            <td class="d-flex justify-content-between lh-condensed"> 
                                <div>
                                    <h6 class="my-0">{{ arti.artiNombre }}</h6>
                                    <small class="text-muted">{{ arti.artiValorunitario }}</small>
                                  </div>
                            </td>  
                            <td> {{ arti.artiValorunitario }}*5 </td>
                            <td> {{ arti.artiExistencia}} </td>



                    <td>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary btn-sm" @click="editar(index)" >Editar</button>
                            <button type="button" class="eliminar btn btn-danger btn-sm" @click="eliminar(index)"  >Eliminar</button>
                        </div>
                    </td>
                  </tr>


                </tbody>
              </table>
        </div>
        
        
    </div>
    
      
     
    </div>
 
 
 
 
        <jsp:include page="footer.jsp" flush="true" />
        <script>
            new Vue({
                    el: '#app',
                    data () {
                      return {
                        articulos:[],
                        cliente:{"clieId":0,"clieDocumento":"","clieNombre":"","clieApellido":"","clieDireccion":"","clieTelefono":"","clieEmail":"","clieFechacambio":"","clieRegistradopor":""},
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

                              },
                              
            
            
                                editar:function (index){
                                  this.editable=true;
                                 this.articulo=this.articulos[index];
                                 this.aux=index;
                              },
                              
            
            
                               guardarCambios:function(){
                                  this.editable=false;
                                  
                                  axios.post('/factura/Controller?controller=Articulo&action=editar',this.createdFormData())
                                    .then(response => (this.articulos[aux]=response.data));
                              this.limpiar();
                            
                                    

                              }
                              ,
                              
                            
                            limpiar : function (){
                               //this.articulo.artiNombre="";
                               this.articulo={'artiId':'','artiNombre':'','artiValorunitario':'','artiExistencia':''};
                            },
                           
                           
                           
                           
                           eliminar : function (index){
                               //this.articulo.artiNombre="";
                               this.articulo=this.articulos[index];
                               axios.post('/factura/Controller?controller=Articulo&action=eliminar',this.createdFormData())
                                    .then(response => (aux=response.data,
                                    this.articulos.splice( index, 1 )
                                    ));

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
