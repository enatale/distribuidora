<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

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
				    <li><a href="buscarProducto.jsp">Modificar Producto</a></li>
				    <li><a href="actualizarStock.jsp">Actualizacion de Stock</a></li>
				    <li><a href="actualizarPrecio.jsp">Actualizacion de Precio</a></li>
				  </ul>
			  </li>
            <li class="dropdown">
				<a href="#" class="dropdown-toggle"  data-toggle="dropdown">
				    Clientes<span class="caret"></span>
				 </a>
				 <!-- dropdown-->
				  <ul class="dropdown-menu" role="menu">
				    <li><a href="ListadoCliPendientes">Confirmar clientes</a></li>
				  </ul>
			  </li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"  data-toggle="dropdown">
				    Pedidos<span class="caret"></span>
				 </a>
				 <!-- dropdown-->
				  <ul class="dropdown-menu" role="menu">
				    <li><a href="buscarPedido.jsp">Actualizar estado del pedido</a></li>
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
