<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Distribuidora Remar</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/app.css">
    <script src="js/jquery-1.12.3.min.js"></script>
    <script src="js/iniciarSesion.js"></script>
  </head>
  <body>
  
    <%@ include file="header.jsp"%>

	
    <div class="container theme-showcase" role="main">
    <div class="row">
    	<div class="col-sm-6 col-sm-offset-3">
            <form action="iniciarSesion" method="post" id="formLogin">
                <h1>Iniciar sesión</h1>
                <label for="txtUsuario" class="sr-only">Nombre de usuario</label>
                <input type="text" id="txtUsuario" name="txtUsuario" class="form-control" placeholder="Nombre de usuario" >
                <label for="txtUsuario" id="errorUsuario" style="color:#FF0004"></label>
                
                <label for="txtPass" class="sr-only">Contraseña</label>
                <input type="password" id="txtPass" name="txtPass" class="form-control" placeholder="Contraseña">
                <label for="txtPass" id="errorPass" style="color:#FF0004"></label>
                
                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                 <p align="center"><a href="registro.jsp">Registrate</a></p>
					  
            </form>
            <% 
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
      		%>
      			<div class="alert alert-danger" role="alert">
        			<strong>Error!</strong> <%=mensaje %>
      			</div>
      		<%
        		}
      		%>
        </div>
    </div> 
	</div><!-- /container -->
       
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>