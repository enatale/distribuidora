<%@page import="entidades.Producto"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% ArrayList<Producto> productos = new ArrayList<Producto>(); 
	productos=(ArrayList<Producto>) request.getParameter("Producto");
	Producto pr; %>
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
    <% for(int i=0;i<= productos.size();i++){
    	pr = new Producto();
    	pr= productos.get(i);
    
    %>
      <tr>
        <td><%= pr.getCodProducto() %></td>
        <td><%= pr.getDescripcion() %></td>
        <td><%= pr.getStock() %></td>
      </tr> 
      <% } %>
    </tbody>
  </table>
</div>

</body>
</html>