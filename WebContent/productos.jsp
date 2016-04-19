<%@page import="entidades.Producto"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%
   
    Integer limit = (Integer)request.getAttribute("limitHasta");
    request.setAttribute("limit", limit);
   %>
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
    		$("#liProductos").addClass("active");
    	});
    </script>
  </head>

<%@ include file="header.jsp" %>
<% ArrayList<Producto> productos = new ArrayList<Producto>(); 
	productos=(ArrayList<Producto>) request.getAttribute("productos");
	//Producto pr; %>
<div class="container">           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Descripcion</th>
        <th>Stock</th>
      </tr>
    </thead>
    <tbody>
    <% 
	    for (Producto pr : productos) {
    	/*for(int i=0;i< productos.size();i++){
    	pr= productos.get(i+1);*/
    %>
      <tr>
        <td><%=limit %></td>
        <td><%=pr.getCodProducto() %></td>
        <td><%=pr.getDescripcion() %></td>
        <td><%=pr.getImporte() %></td>
      </tr> 
      <% } %>
    </tbody>
     <form action="Productos" method="post">
      <input name="siguiente" type="submit" value="Siguiente" type="btn" >
    
     </form>
  </table>
</div>

</body>
</html>