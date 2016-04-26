<%@page import="entidades.Producto"%>
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
  <body>
  	
  
    <%@ include file="headerEmp.jsp" %>
    
    <h1 style="text-align:center">Actualizar precio</h1>
    <div class="container">
    <div class="row" style="height:100%">
    	<div class="col-sm-6">
        			<form action="ActualizarPrecio" method="post" id="formItem">
                    <label for="txtDescripcion" class="sr-only">Descripcion del Producto</label>
	                <input type="text" id="txtDescripcion" name="txtDescripcion" class="form-control" placeholder="Descripcion del producto">
	                <label for="txtDescripcion" id="errorDescripcion" style="color:#FF0004"></label>
	                
	                <label for="txtCod" class="sr-only">Código</label>
               	    <input type="text" id="txtCod" name="txtCod" class="form-control" placeholder="Código">
              	    <label for="txtCod" id="errorCod" style="color:#FF0004"></label>
	                
	                <label for="txtFecha" class="sr-only">Fecha Importe</label>
	                <input type="date" id="txtFecha" name="txtFecha" class="form-control" Placeholder="YYYY-MM-dd">
	                <label for="txtFecha" id="errorFecha" style="color:#FF0004"></label>
	                
	                <label for="txtImporte" class="sr-only">Importe</label>
	                <input type="text" id="txtImporte" name="txtImporte" class="form-control" placeholder="Importe">
	                <label for="txtImporte" id="errorImporte" style="color:#FF0004"></label>
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
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
        <div class="col-sm-6">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Descripcion</th>
					</tr>
				</thead>
				<tbody id="cuerpo">
					<tr>
						<td colspan="3"><h2>Comience a escribir para obtener los productos</h2></td>
					</tr>
				</tbody>
			</table>      
		</div>
	</div>
	
	
	
	</div><!-- /container -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>