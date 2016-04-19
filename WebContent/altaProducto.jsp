<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Distribuidora Remar</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet"  href="css/bootstrap-theme.css" />
<link rel="stylesheet"  href="css/app.css"/>
<script source="js/bootstrap.js"type="text/javascript"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</head>
<body>
  <%@ include file="inicioEmp.jsp" %>
  
      <div class="container theme-showcase" role="main">
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3">
	            <form action="AltaProducto" method="post" id="formAltaProducto">
	                <h1>Alta Producto</h1>
	                <label for="txtDescripcion" class="sr-only">Descripcion del Producto</label>
	                <input type="text" id="txtDescripcion" name="txtDescripcion" class="form-control" placeholder="Descripcion del producto">
	                <label for="txtDescripcion" id="errorDescripcion" style="color:#FF0004"></label>
	                
	                <label for="txtStock" class="sr-only">Stock</label>
	                <input type="text" id="txtStock" name="txtStock" class="form-control" placeholder="Stock">
	                <label for="txtStock" id="errorStock" style="color:#FF0004"></label>
	                
	                <label for="txtFecha" class="sr-only">Fecha Importe</label>
	                <input type="text" id="txtFecha" name="txtFecha" class="form-control" placeholder="Fecha">
	                <label for="txtFecha" id="errorFecha" style="color:#FF0004"></label>
	                
	                <label for="txtImporte" class="sr-only">Importe</label>
	                <input type="text" id="txtImporte" name="txtImporte" class="form-control" placeholder="Importe">
	                <label for="txtImporte" id="errorImporte" style="color:#FF0004"></label>
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	            </form>
            <% 
      			String mensaje=(String)request.getAttribute("mensaje");
        		if(mensaje!=null){
        			//CAMBIAR EL AGREGADO CON EXITO. ABAJO Y EL CAMPO DE LA FECHA YYYY-MM-DD
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
</body>
</html>