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
 <div id="app">
      <div class="container" id="cliente">
            <div class="bg-dark my-5 py-2 text-center">
              <h5 class="text-white">Datos de Cliente</h5>
            </div>
            <div  class="row" id="datos-cli">
              
              <div  v-if="clientee" class="container-fluid" id="buscar-cli">
                <div class="row justify-content-center">
                  <div class="col-md-4 mb-3 align-self-end">
                    <h4 class="mb-3">Datos del Cliente</h4>
                  </div>
                  <div class="col-md-4 mb-3">
                  
                  <input type="text" v-model="cliente.clieDocumento" class="form-control" id="documento" placeholder="Documento" value="" required="">
                  <div class="invalid-feedback">
                    Documento es requerido.
                  </div>
                </div>
                <div class="col-md-4 mb-3 ">
                  <button v-on:click="buscarcli()" class="btn btn-primary btn-lg btn-block" >
                          <span class="badge badge-primary"></span>Buscar</span>
                  </button>
                  
                </div>
                
                
              </div>
            </div>




              <div v-else class="container-fluid" id="ver-cli">
              <div   role="form" data-toggle="validator">

                <div class="row">
                      <div class="col form-group">
                        <label for="nombre"><strong>Nombre del Cliente</strong></label>
                        <input type="text" class="form-control" name="nombre" id="nombre" 
                        v-model="cliente.clieNombre" disabled required aria-describedby="emailHelp" placeholder="Ingrese el nombre">
                        
                      </div>
                      <div class="col form-group">
                          <label for="Apellido"><strong>Apellido del Cliente</strong></label>
                          <input type="text" class="form-control" name="apellido" id="apellido"
                          v-model="cliente.clieApellido" disabled required aria-describedby="emailHelp" placeholder="Ingrese el apellido">
                          
                      </div>
                </div>
                
                  <br>

                <div class="row">
                  <div class="col form-group">
                    <label for="documento"><strong>Documento del Cliente</strong></label>
                    <input type="text" class="form-control" name="documento" id="documento" 
                          v-model="cliente.clieDocumento" disabled required aria-describedby="emailHelp" placeholder="Ingrese el documento">
                    
                  </div>
                  <div class="col form-group">
                      <label for="telefono"><strong>Telefono del Cliente</strong></label>
                      <input type="text" class="form-control" name="telefono" id="telefono" 
                      v-model="cliente.clieTelefono" disabled  required aria-describedby="emailHelp" placeholder="Ingrese el telefono">
                      
                  </div>
            </div>



            <br>

            <div class="row">
              <div class="col form-group">
                <label for="direccio"><strong>Direccion  del Cliente</strong></label>
                <input type="text" class="form-control" name="direccion" id="direccion" 
                v-model="cliente.clieDireccion" disabled  require daria-describedby="emailHelp" placeholder="Ingrese el direccion">
                
              </div>
              <div class="col form-group">
                  <label for="email"><strong>E-mail del Cliente</strong></label>
                  <input type="email" class="form-control" name="email" id="email" 
                  v-model="cliente.clieEmail" disabled  required aria-describedby="emailHelp" placeholder="Ingrese el email">
                  
              </div>

            
        </div>  
                <div>
                  <button type="submit" class="btn btn-primary btn-lg btn-block">Enviar</button>
                </div>
                
        
            </div>


            <div class="" id="cliente">
              <div class="bg-secondary my-5 py-2 text-center">
                <h5 class="text-white">Detalle de factura</h5>
              </div>
            </div>
          </div>
            </div>
            <div class="row" v-if="!clientee">


              <div class="col-md-8 order-md-1 mb-8">
                  <div class=" form-group row bg-dark my-2 py-2 text-center form-group">
                   
                     <div class="col-sm-8">
                      <input type="text" id="buscarl" class="form-control" placeholder="Buscar articulo">
                    </div>
                    <div class="col-sm-3">
                      <button type="button" name="" id="" class="btn btn-primary btn-sm btn-block">Buscar</button>
                    </div>
                  </div>

                  <div class="my-4">
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
                                  <button type="button" class="btn btn-primary btn-sm" @click="agregarDetalle(index)" >Agregar a factura</button>
                              </div>
                          </td>
                        </tr>
                        
                        
                      </tbody>
                    </table>
                    
                   
                  </div>
                  
                </div>
          
          
          
              <div class="col-md-4 order-md-2 mb-4">
                  <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Detalle de factura</span>
                    <span class="badge badge-secondary badge-pill">{{cant}}</span>
                  </h4>
                  <ul class="list-group mb-3">
                    <li v-for="deta in detalles" class="list-group-item d-flex justify-content-between lh-condensed">
                      <div>
                        <h6 class="my-0">{{deta.articulo.artiNombre}}</h6>
                        <small class="text-muted">(Costo de producto: $ {{deta.valor}} x {{deta.cantidad}} unidades)</small>
                      </div>
                      <span class="text-muted">$ {{deta.valorTotal}}</span>
                    </li>
                    
                    
                    
                    <li class="list-group-item d-flex justify-content-between">
                      <span>Total (COP)</span>
                      <strong>$ {{total}}</strong>
                    </li>
                  </ul>
            
                  <div class="card p-2">
                    <div class="input-group">
                      
                      <div class="input-group-append">
                        <button @click="guardarFactura()" class="btn btn-secondary">Guardar Factura</button>
                      </div>
                    </div>
                  </div>
                </div>
                
          
          
          </div>
      </div>

    </div>
 
    <jsp:include page="footer.jsp" flush="true" />
        <script>
            new Vue({
                    el: '#app',
                    data () {
                      return {
                        factura:{
                              faclFecha:'',
                              cliente:null,
                              detalles:[],
                          },
                        articulos:[],
                        cliente:{"clieId":'',"clieDocumento":"","clieNombre":"","clieApellido":"","clieDireccion":"","clieTelefono":"","clieEmail":"","clieFechacambio":"","clieRegistradopor":""},
                        articulo:{'artiId':'','artiNombre':'','artiValorunitario':'','artiExistencia':''},
                        aux:{},
                        detalle:{id:'',articulo:{},cantidad:'',valor:'',valorTotal:''},
                        detalles:[],
                        clientee:true,//muesta el  input que requiere el documento para buscar el cliente y mostrar sus datos
                        cant:0, //cantidad de productos de la venta
                        total:parseInt(0),//total d la factura
                        
                        
                      }
                    },
                  mounted() {
                      axios
                        .get('/factura/Controller?controller=Articulo&action=listar')
                        .then(response => (this.articulos= response.data))
                        
                  },
                  
                  methods:{     
                              buscarcli: function () {
                                  
                                  
                                  axios
                                  .get('/factura/Controller?controller=Cliente&action=buscar&id='+this.cliente.clieDocumento)
                                  .then(response => (this.cliente= response.data,
                                  console.log(response.data),
                                  this.clientee=false,
                                  this.iniciarFactura()//llamado a la funcion que crea una instancia de factura en el servlet
                                    ))
                                    
                                    .catch(function (error) {
                                        alert("El cliente no existe. Crear cliente");
                                        location.href="/factura/Controller?controller=Cliente&action=agregar";
                                      })
                              },
                      
                              
                               
                               guardarDetalle_bank: function (id,cant) {
                                const params = new URLSearchParams();
                                params.append('id_articulo', id);
                                params.append('cantidad', cant);


                                     axios.post('/factura/Controller?controller=Factura&action=agregar_detalle',params)
                                    .then(response => (aux2=response.data,
                                    console.log(aux2)))

                              },
                              


                              iniciarFactura: function () {
                                const params = new URLSearchParams();
                                params.append('id_cliente', this.cliente.clieId);


                                     axios.post('/factura/Controller?controller=Factura&action=iniciar',params)
                                    .then(response => (aux=response.data,
                                    console.log(aux)))
                              },
                             
            
                              agregarDetalle:function (index){
                                  
                                 var aux=this.articulos[index];
                                // detalle:{id:'',cantidad:'',valor:'',valorTotal:''}
                                var cantidad=prompt("Ingresar cantidad");
                                if(isNaN(cantidad)){
                                  alert("No es un cantidad validad")
                                }else{
                                  let total=cantidad*aux.artiValorunitario;
                                  if(cantidad<=aux.artiExistencia&&cantidad>0){
                                    
                                    this.guardarDetalle_bank(aux.artiId,cantidad);


                                            this.detalles.push({'articulo':aux,'cantidad':cantidad,'valor':aux.artiValorunitario,'valorTotal':total});
                                          //this.cant=cantidad+new Number(this.cant);
                                          aux.artiExistencia=aux.artiExistencia-cantidad
                                          this.cant=parseInt(this.cant, 10)+parseInt(cantidad, 10);
                                          this.total=parseInt(this.total)+parseInt(total);


                                          }else{
                                            alert("No es un cantidad validad \nLa cantidad disponible es: "+aux.artiExistencia)
                                            }
                                          }

                              },
                              
            
            
                               guardarFactura:function(){
                                  this.editable=false;
                                  
                                  axios.post('/factura/Controller?controller=Factura&action=guardar')
                                    .then(response => (console.log(response.data)));
                           
                            
                                    

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
                                for(var key in this.detalles){
                                   params.append(key, this.detalles[key]);
                                }
                                return params;
                            },
                       
                  }  
     
            }      
            );
        </script>
    </body>
</html>
