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
    <% Pedidos pedido; 
		pedido=(Pedidos) request.getAttribute("pedido"); 
	%>
	
      <div class="container theme-showcase" role="main">
        <div class="row">
    	   <div class="col-sm-6 col-sm-offset-3">
	            <form action="ActualizarEstadoPedido" method="post" >
	                <h2>Actualizar estado</h2>
	                <label for="txtNro" class="sr-only">Numero Pedido</label>
	                <input type="text" id="txtNro" name="txtNro" class="form-control" value="<%=pedido.getNumero_pedido()%>" readonly>
	                <label for="txtNro" id="errorCodigo" style="color:#FF0004"></label>
	                <select class="form-control" name="Estado">
					  <option value="Pendiente">Pendiente</option>
					  <option value="Preparado">Preparado</option>
					  <option value="Entregado">Entregado</option>
					  <option value="Pagado">Pagado</option>
					</select>
	                
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
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
      			String mensajeConfirmacion = (String)request.getAttribute("mensajeConfirmacion");
      			if(mensajeConfirmacion!=null){
      		%>
      			<div class="alert alert-success">
					  <a href="#" class="alert-link"><%=mensajeConfirmacion %></a>
				</div>
			<%
      			}
			%>
	 </div>
	 </div>
	 </div>
	 

</body>
</html>