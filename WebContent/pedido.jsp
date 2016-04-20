<%@page import="negocio.CtrlPedidos"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
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
    <script src="js/pedido.js"></script>
  </head>
  <body>
  	
  
    <%@ include file="header.jsp" %>
    <%ArrayList<Producto> productos = new CtrlPedidos().getAll(); %>
    
    <h1 style="text-align:center">Realice su pedido</h1>
    
    <div class="container">
    <div class="row" style="height:100%">
    	<div class="col-sm-6">
        	<form action="">
            	<label for="txtCod" class="sr-only">Código</label>
                <input type="text" id="txtCod" name="txtCod" class="form-control" placeholder="Código">
                <label for="txtCod" id="errorCod" style="color:#FF0004"></label>
                
                <label for="txtDescripcion" class="sr-only">Producto</label>
                <input type="text" id="txtDescripcion" name="txtDescripcion" class="form-control" placeholder="Descripción">
                <label for="txtDescripcion" id="errorDescripcion" style="color:#FF0004"></label>
                
                <label for="txtCantidad" class="sr-only">Producto</label>
                <input type="text" id="txtCantidad" name="txtCantidad" class="form-control" placeholder="Cantidad">
                <label for="txtCantidad" id="errorCantidad" style="color:#FF0004"></label>
                
                <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
            </form>
        </div>
        <div class="col-sm-6">
        	<h4>Seleccione un producto para rellenar los campos</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Descripcion</th>
						<th>Precio</th>
					</tr>
				</thead>
				<tbody id="cuerpo">
				<% int nro=0;
					for(Producto prod:productos){
						nro++;
						%>
					
					<tr id="<%=nro%>">
						<td id="cod<%=nro%>"+><%=prod.getCodProducto()%></td>
						<td id="desc<%=nro%>"><%=prod.getDescripcion()%></td>
						<td><%=prod.getImporte()%></td>
					</tr>
				<%} %>
				</tbody>
			</table>	      
		</div>
	</div> 
	</div><!-- /container -->

    
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>