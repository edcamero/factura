 
<%@page import="VO.Cajero"%>
<%HttpSession sesion = request.getSession();
       sesion.removeAttribute("cajero");
       Cajero cajero=null;
      if(sesion.getAttribute("cajero") == null){
             cajero=new Cajero(2, "Camila", "correa ","123456");
            sesion.setAttribute("cajero", cajero);
         //response.sendRedirect("/factura/Sesion");
    }%>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Cajero <%=cajero.getCajeNombre() %> </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropmenufactura" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Facturas
        </a>
        <div class="dropdown-menu" aria-labelledby="dropmenufactura">
          <a class="dropdown-item" href="Controller?controller=Factura&action=agregar" id="dropmenuarticul">Agregar</a>
          <a class="dropdown-item" href="Controller?controller=Factura&action=listar&page=0">Listar</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link " href="Controller?controller=Articulo&action=agregar" id="dropmenuarticulo"   >
          Articulo
        </a>
        
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropmenucliente" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cliente
        </a>
        <div class="dropdown-menu" aria-labelledby="dropmenucliente">
          <a class="dropdown-item" href="Controller?controller=Cliente&action=agregar">Agregar</a>
          <a class="dropdown-item" href="Controller?controller=Cliente&action=listar">Listar</a>
        </div>
      </li>
    </ul>
  </div>
</nav>
    
  </header>
