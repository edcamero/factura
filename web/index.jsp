<%-- 
    Document   : index
    Created on : 29-ene-2020, 14:25:11
    Author     : blade
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <html>
    <head>
     <script src="https://unpkg.com/vue/dist/vue.js"></script>
    </head  >
    <body>
     <div id="app">
     <p v-for="per in personas">
     {{ per.nombre }}
     </p>
     <input type="text" v-model="persona.nombre"/ />
     <input type="button" value="nuevo" v-on:click="nuevo" />
     </div>
    </body>
    <script type="text/javascript">
    var app = new Vue({
     el: '#app',
     data:{
     personas: [
     { nombre: 'pedro' },
     { nombre: 'juan' },
     { nombre: 'ana' }
     ]
     ,
     persona: {nombre:""}
     }
     , methods: {
     nuevo: function () {
     var personaO={ nombre: this.persona.nombre }
     this.personas.push(personaO);
     this.persona.nombre='';
     }
     }
    })
    </script>
    </html>