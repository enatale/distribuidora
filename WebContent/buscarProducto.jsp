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
    <script src="js/actuaPrecio.js"></script>
  </head>
</head>
<body>
 
     <%@ include file="headerEmp.jsp" %>
    <div class="container theme-showcase" role="main">
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3">
	            <form action="BuscarProducto" method="post" id="formAltaProducto">
	                <h1>Buscar Producto</h1>
	                
	               
	                
	                <label for="txtCod" class="sr-only">Código</label>
               	    <input type="text" id="txtCod" name="txtCod" class="form-control" placeholder="Código">
              	    <label for="txtCod" id="errorCod" style="color:#FF0004"></label>
	                 
	                 
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	                
	            </form>
            <% 
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
      		%>
      		
      			<div class="alert alert-danger" role="alert">
        			<strong>Error!</strong> <%=mensaje %>
      			</div>
      		<%} %>
      		
        </div>
    </div> 
	</div><!-- /container -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>