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
    <script src="js/actuaPrecio.js"></script>
  </head>
</head>
<body>
 
     <%@ include file="headerEmp.jsp" %>
     <% 	ArrayList<Pedidos> pedidos; 
	pedidos=(ArrayList<Pedidos>) request.getAttribute("pedidos");%>
   <div class="row" style="text-align: center">
<h1>Listado de Pedidos</h1>
</div>
<div class="row">
<div class="container">           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Codigo del Pedido</th>
        <th>Fecha del Pedido</th>
      </tr>
    </thead>
    <tbody>
    <%  if(pedidos.size()!=0){
	    for (Pedidos pr : pedidos) {
    %>
      <tr>
         <td><%=pr.getNumero_pedido() %></td>
         <td><%=pr.getFecha_pedido() %></td>
      </tr> 
      <% }
	    }else{%>
	    <h2>No hay pedidos con las fechas ingresadas</h2>
	    <% } %>
    </tbody>
  </table>
</div>
</div>
</body>
<script src="js/bootstrap.min.js"></script>
</html>