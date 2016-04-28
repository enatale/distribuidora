<%@page import="appExceptions.ApplicationException"%>
<%@page import="negocio.CtrlModificarPedido"%>
<%@page import="entidades.Pedidos"%>
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
    <script type="text/javascript">
    	$(document).ready(function(){
    		$("#liModificarPedido").addClass("active");
    	});
    </script>
  </head>
  <body>
  
    <%@ include file="header.jsp" %>
    <%
    	String msj="";
    	ArrayList<Integer> nrosPendientes=new ArrayList<Integer>();
    	Cliente cliente = (Cliente)session.getAttribute("usuario");
    	if(cliente==null||cliente.getCodCliente()==0){%>
    		<h1 style="color: red; text-align: center;">Para ver esta página debe iniciar sesión como cliente.</h1>
    	<%
    	}else {
    		try{
    			nrosPendientes = new CtrlModificarPedido().getNrosPedidosPendientes(cliente.getDni());
    			
    		}catch (ApplicationException e){
    			msj=e.getMessage();
    		}
    	
    %>
	
	<h1 style="text-align:center">Ingrese número de pedido a modificar</h1>
    
	<div class="container">
	<div class="row" style="height:100%">
		<div class="col-sm-3 col-sm-offset-2">
			<form action="pedidoModificar2.jsp" id="formNroPedido">
				<label for="txtNroPedido" class="sr-only">Código</label>
				<input type="text" id="txtNroPedido" name="txtNroPedido" class="form-control" 
					placeholder="Número de pedido" autofocus="autofocus">
				<label for="txtNroPedido" id="errorNro" style="color:#FF0004"></label>
				                
				<button class="btn btn-lg btn-primary btn-block" type="submit">Modificar</button>
			</form>
			<% 
				String error = (String) request.getAttribute("error");
        		if(error!=null){
      		%>
      			<div class="alert alert-danger" role="alert">
        			<strong>Error!</strong> <%=error %>
      			</div>
      		<%
        		}
      		%>
		</div>
		<div class="col-sm-4 col-sm-offset-1">
			<h4 style="text-align:center">Lista de números de pedidos pendientes</h4>
			<table class="table table-hover">
				<tbody>
				<%if(!msj.equals("")){%>
					<tr >
						<td colspan="3"><h2 style="text-align: center; color: red;"><%=msj %></h2></td>
					</tr>
				<%}else{
					if(nrosPendientes.size()==0){
					%>
					<tr >
						<td colspan="3"><h2>No tiene pedidos pendientes</h2></td>
					</tr>
				<%} else{ 
						for(Integer nro:nrosPendientes){%>
					<tr>
						<td><%=nro %></td>
					</tr>
					<%	}
					}
				}%>
				</tbody>
			</table>	
		       
		        
		</div>
	</div> 
	</div><!-- /container -->
	
	<%}//Validacion de usuario %>
       
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>