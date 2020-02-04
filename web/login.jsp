<%-- 
    Document   : login
    Created on : 03-feb-2020, 19:14:33
    Author     : blade
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <jsp:include page="head.jsp" flush="true" />
    <link rel="stylesheet" href="css/login.css">   
    <body class="text-center">
        <form class="form-signin">
            <img class="mb-4" src="/factura/img/users.svg" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Ingresa al Sistema</h1>
            <label for="inputUser" class="sr-only">Email address</label>
            <input type="text" id="inputUser" class="form-control" placeholder="Nombre de Usuario" required="" autofocus="">
            <br>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
            
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted">© 2020</p>
          </form>
    </body>

