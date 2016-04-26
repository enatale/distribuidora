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
    <script type="text/javascript">
    	$(document).ready(function(){
    		$("#liIndex").addClass("active");
    	});
    </script>
  </head>
  <body>
  
    <%@ include file="header.jsp" %>

    <div class="jumbotron">
	    <div class="container">
	        <div class="row">
	            <div class="col-md-6">
	                <img src="imagen.jpg" class="img-responsive img-rounded" alt="edificio">
	            </div>
	            <div class="col-md-6">
	                <h3 style="text-align:center">Bienvenido al sitio de:</h3>
	                <h1 style="color: #00D3ED;text-align:center">Distribuidora Remar</h1>
	                <p>Nuestra empresa se dedica a la comercialización de productos de almacén, tales como alimentos, bebidas, productos de limpieza, etc.</p>
	            <p>Si usted desea <a href="registro.jsp">registrarse</a> como cliente visite el apartado específico y aguarde a ser aprobado como tal.</p>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="container">
	    <div class="row">
	        <div class="col-md-4 col-md-offset-1">
	            <h2>Consulte los productos</h2>
	            <p>Usted puede consultar un listado de los productos que se encuentran a la venta en nuestro negocio actualmente. El mismo también incluye el precio de venta de cada uno.</p>
	            <p><a class="btn btn-default" href="Productos" role="button">Productos &raquo;</a></p>
	        </div>
	        <div class="col-md-4 col-md-offset-2">
	          <h2>Realice su pedido</h2>
	          <p>Luego de ser aprobado como cliente, usted tiene la posibilidad de realizar pedidos que luego serán entregados en su domicilio. Para esto deberá <a href="iniciarSesion.jsp">Iniciar sesión</a>.</p>
	          <p><a class="btn btn-default" href="pedido.jsp" role="button">Realizar Pedido &raquo;</a></p>
	        </div>
	    </div> 
	</div>
    
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>