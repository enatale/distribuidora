<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Distribuidora Remar</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/app.css">
    <script src="../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$("#liModificarPedido").addClass("active");
    	});
    </script>
  </head>
  <body>
  
    <%@ include file="headerPedido.jsp" %>

    <div class="container" style="text-align: center;">
    <% 
      			String mensajeC=(String)request.getAttribute("mensajeConfirmacion");
        		if(mensajeC!=null){
      		%>
      			<div class="alert alert-success" role="alert">
        			<strong><%=mensajeC %></strong> 
      			</div>
      		<%
        		}
        		String mensajeE=(String)request.getAttribute("mensajeError");
        		if(mensajeE!=null){
              		%>
              			<div class="alert alert-danger" role="alert">
                			<strong>ERROR!</strong><%=mensajeE %> 
              			</div>
              		<%
                		}
      		%>
      		<a class="btn btn-default" href="../pedidoModificar1.jsp">Volver atrás</a>
    </div>   
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>