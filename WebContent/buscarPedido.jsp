<%@page import="entidades.Pedidos"%>
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
  </head>
  <body>
  	
  
    <%@ include file="headerEmp.jsp" %>
<div class="container theme-showcase" role="main">
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3">
	            <form action="ActualizarEstadoPedido" method="post" >
	                <h2>Ingrese el codigo del pedido</h2>
	                <label for="txtCodigo" class="sr-only">Numero Pedido</label>
	                <input type="text" id="txtCodigo" name="txtCodigo" class="form-control" placeholder="Codigo del pedido">
	                <label for="txtCodigo" id="errorCodigo" style="color:#FF0004"></label>
	                
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Buscar</button>
	            </form>
	 </div>
	 </div>
	 </div>
</body>
</html>