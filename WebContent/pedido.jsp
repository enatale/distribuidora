<%@page import="entidades.Linea_pedido"%>
<%@page import="entidades.Pedidos"%>
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
    
    <h1 style="text-align:center">Realice su pedido</h1>
    <div class="container">
    <div class="row" style="height:100%">
    	<div class="col-sm-6">
        	<form action="pedido" method="post" id="formItem">
                <label for="txtDescripcion" class="sr-only">Producto</label>
                <input type="text" id="txtDescripcion" name="txtDescripcion" class="form-control" placeholder="Descripción" autofocus="autofocus">
                <label for="txtDescripcion" id="errorDescripcion" style="color:#FF0004"></label>
                
                <label for="txtCod" class="sr-only">Código</label>
                <input type="text" id="txtCod" name="txtCod" class="form-control" placeholder="Código">
                <label for="txtCod" id="errorCod" style="color:#FF0004"></label>
                                
                <label for="txtCantidad" class="sr-only">Producto</label>
                <input type="text" id="txtCantidad" name="txtCantidad" class="form-control" placeholder="Cantidad">
                <label for="txtCantidad" id="errorCantidad" style="color:#FF0004"></label>
                
                <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
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
        <div class="col-sm-6">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Descripcion</th>
						<th>Precio</th>
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
	
	
	<%Pedidos pedido= (Pedidos)session.getAttribute("pedido"); 
	if(pedido!=null){	
	%>
	<div class="row" style="text-align: center;">
		<h1>SU PEDIDO</h1>
		<table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Código</th>
                <th>Descripción</th>
                <th>Precio Unitario</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Borrar</th>
              </tr>
            </thead>
            <tbody>
            
            <%int i=1;
            float total=0;
            for(Linea_pedido item:pedido.getLineas()){
            	float subtotal=item.getCantidad()*item.getProducto().getImporte();
            %>            
              <tr>
                <td><%=i %></td>
                <td><%=item.getProducto().getCodProducto() %></td>
                <td><%=item.getProducto().getDescripcion() %></td>
                <td><%=item.getProducto().getImporte() %></td>
                <td><%=item.getCantidad() %></td>
                <td><%=subtotal %></td>
                <td><a class="btn btn-danger" href="pedido/borrarLinea?nro=<%=i %>">X</a></td>
              </tr>
            <%	i++;
            	total+=subtotal;
            }%>
            <tr>
                <td style="text-align: right;" colspan="5"><h4>IMPORTE TOTAL DEL PEDIDO</h4></td>
                <td><h4><%=total %></h4></td>
              </tr>
            </tbody>
          </table>
	</div>
	<div class="row" style="float: right;">
		<a class="btn btn-danger" href="pedido/borrarPedido">BORRAR PEDIDO</a>
		<a class="btn btn-primary btn-lg" href="pedido/confirmarPedido">CONFIRMAR PEDIDO</a>
	</div>
	<%} %>
	</div><!-- /container -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>