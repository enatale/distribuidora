<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.CtrlProducto"%>
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
	   
	   <% ArrayList<Producto> productos = new ArrayList<Producto>();
	   	CtrlProducto ctrl = new CtrlProducto();
			productos=(ArrayList<Producto>)ctrl.getProdsinStock(); %>
			

<div class="row" style="text-align: center">
<h1>Listado de Productos sin stock</h1>
</div>
<div class="row">
<div class="container">           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Producto</th>
        <th>Descripcion</th>
      </tr>
    </thead>
    <tbody>
    <%  if(productos.size()!=0){
	    for (Producto pr : productos) {
    %>
      <tr>
         <td><%=pr.getCodProducto() %></td>
         <td><%=pr.getDescripcion() %></td>
      </tr> 
      <% }
	    }else{%>
	    <h2>No hay hay productos sin stock</h2>
	    <% } %>
    </tbody>
  </table>
</div>
</div>
</body>
<script src="js/bootstrap.min.js"></script>
</html>