<%@page import="entidades.Cliente"%>
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
	   <% ArrayList<Cliente> clientes; 
			clientes=(ArrayList<Cliente>) request.getAttribute("clientes"); %>
			

<div class="row" style="text-align: center">
<h1>Lista de Clientes Pendientes</h1>
</div>
<div class="row">
<div class="container">           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>CUIT</th>
        <th>Estado</th>
        <th>Cambiar</th>
      </tr>
    </thead>
    <tbody>
    <%  if(clientes!=null){
	    for (Cliente cli : clientes) {
    %>
      <tr>
        <form method="post" action="ListadoCliPendientes">
        <td><input type="text" id="txtDni" name="txtDni" class="form-control"  value="<%=cli.getDni()%>" size="11" style="visibility:hidden"></td>
        <td><%=cli.getNombre() %></td>
        <td><%=cli.getApellido() %></td>
        <td><%=cli.getCUIT() %></td>
        <td>
        <select class="form-control" name="Estado">
					  <option value="Pendiente">Pendiente</option>
					  <option value="Aprobado">Aprobado</option>
					  <option value="Rechazado">Rechazado</option>
			</select></td>
		<td><button class="btn" type="submit">Actualizar</button></td>
		</form>
      </tr> 
      <% }
	    }else{%>
	    <h2>No hay clientes con estado pendiente</h2>
	    <% } %>
    </tbody>
  </table>
</div>
</div>
</body>
</html>