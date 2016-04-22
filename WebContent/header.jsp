<%@page import="entidades.Cliente"%>
<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%Persona p = (Persona) session.getAttribute("usuario"); %>
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
            <li id="liIndex"><a href="index.jsp">Inicio</a></li>
            <li id="liProductos"><a href="Productos">Productos</a></li>
          <%if(p!=null&&p instanceof Cliente){ %>
            <li id="liPedido"><a href="pedido.jsp">Realizar pedido</a></li>
            <li id="liModificarPedido"><a href="pedidoModificar.jsp">Modificar/eliminar pedido</a></li>	
          <%} %>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          <%
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
    