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
	            <form action="ListadoPedidos" method="post" id="formListado">
	                <h1>Buscar Pedido</h1>
	                
	                <label for="txtFechaIni" class="sr-only">Fecha Inicio</label>
               	    <input type="date" id="txtFechaIni" name="txtFechaIni" class="form-control" placeholder="Fecha de Inicio">
              	    <label for="txtFechaIni" id="errorCodIni" style="color:#FF0004"></label>
	                 
	                <label for="txtFechaFin" class="sr-only">Fecha Fin</label>
               	    <input type="date" id="txtFechaFin" name="txtFechaFin" class="form-control" placeholder="Fecha de Fin">
              	    <label for="txtFechaFin" id="errorCodFin" style="color:#FF0004"></label>
              	    
              	     <select class="form-control" name="Estado">
					  <option value="Pendiente">Pendiente</option>
					  <option value="Preparado">Preparado</option>
					  <option value="Entregado">Entregado</option>
					  <option value="Pagado">Pagado</option>
					</select>
              	    
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