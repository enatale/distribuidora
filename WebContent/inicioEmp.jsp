<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Distribuidora Remar</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet"  href="css/bootstrap-theme.css" />
<link rel="stylesheet"  href="css/app.css"/>
<script source="js/bootstrap.js"type="text/javascript"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a id="marca" class="navbar-brand" href="#">Distribuidora REMAR</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
				<a href="#" class="dropdown-toggle"  data-toggle="dropdown">
				    Productos<span class="caret"></span>
				 </a>
				 <!-- dropdown-->
				  <ul class="dropdown-menu" role="menu">
				    <li><a href="altaProducto.jsp">Alta Productos</a></li>
				    <li><a href="">Baja Productos</a></li>
				    <li><a href="">Actualizacion de Stock</a></li>
				  </ul>
			  </li>
            <li class="dropdown">
				<a href="#" class="dropdown-toggle"  data-toggle="dropdown">
				    Clientes<span class="caret"></span>
				 </a>
				 <!-- dropdown-->
				  <ul class="dropdown-menu" role="menu">
				    <li><a href="">Confirmar clientes</a></li>
				  </ul>
			  </li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"  data-toggle="dropdown">
				    Pedidos<span class="caret"></span>
				 </a>
				 <!-- dropdown-->
				  <ul class="dropdown-menu" role="menu">
				    <li><a href="">Actualizar estado del pedido</a></li>
				  </ul>
			  </li>
          </ul>
           <ul class="nav navbar-nav navbar-right">
          <%
          	Persona p = (Persona) session.getAttribute("usuario");
          	if(p==null){
          %>
          	<li id="liIniciarSesion"><a href="iniciarSesion.jsp">Iniciar sesión</a></li>
          <%} else {%>
          	<li id="liCerrarSesion"><a href="CerrarSesion">Cerrar Sesion</a></li>
          <%}%>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
</body>
</html>
