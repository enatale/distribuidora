<%@page import="negocio.CtrlPedidos"%>
<%@page import="negocio.CtrlProducto"%>
<%@page import="entidades.Empleado"%>
<%@page import="appExceptions.ApplicationException"%>
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
  </head>
  <body>
  
    <%@ include file="headerEmp.jsp" %>
	
	
	<% 	ArrayList<Producto> productos;
		Empleado emp = (Empleado)session.getAttribute("usuario");
		Integer totalPaginas;
		Integer pagina;
		//TODO aumentar cant por pag
		int cant_por_pagina=2;
	try{
		if(emp==null||emp.getLegajo()==0){
			throw new ApplicationException("Para ver esta página debe iniciar sesión como empleado. ",null);
		}
		if (request.getAttribute("productos")!=null){
			productos=(ArrayList<Producto>) request.getAttribute("productos");
			totalPaginas=(Integer)request.getAttribute("totalPaginas");
			pagina = (Integer)request.getAttribute("pagina");
		} else {
			CtrlPedidos ctrl = new CtrlPedidos();
			int totalProductos= ctrl.getCantProductos();
			totalPaginas=  (int) Math.ceil((float)totalProductos/(float)cant_por_pagina);
			productos = ctrl.getAll(0, cant_por_pagina);
			pagina = 1;
		}
		%>
		<div class="row">
			<div class="col-md-2 col-md-offset-5">
			<form action="reportePrecios" method="get" id="formFecha">
				<input type="date" id="txtFecha" name="fecha" class="form-control" placeholder="AAAA-MM-DD">
				<button type="submit" class="btn btn-large btn-primary">Consultar</button>
			</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
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
		</div>
		<div class="row" style="text-align: center">
			<h1>Lista de Precios</h1>
		</div>
		<div class="row">
		<div class="container">           
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>Id</th>
		        <th>Descripcion</th>
		        <th>Precio</th>
		      </tr>
		    </thead>
		    <tbody>
		    <% 
			    for (Producto pr : productos) {
		    %>
		      <tr>
		        <td><%=pr.getCodProducto() %></td>
		        <td><%=pr.getDescripcion() %></td>
		        <td>$ <%=pr.getImporte() %></td>
		      </tr> 
		      <% } %>
		    </tbody>
		  </table>
		</div>
		</div>
		<div class="row" style="text-align: center;">
			<h1>
		<%if(totalPaginas>1){
			for(int i=1;i<=totalPaginas;i++){
				if(i==pagina){%>
				<span class="label label-primary"><%=Integer.toString(pagina)%></span>
				<%
				} else{
					%>
					
					<a class="btn btn-default" href="reportePrecios?pagina=<%=i%>&fecha="><%=i%></a>
					<%
				}
			}
			}
			%></h1>
			</div>
	<%
		} catch (ApplicationException e){%>
			<h1 style="color: red; text-align: center;"><%=e.getMessage() %></h1>
	  	<%} %>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>