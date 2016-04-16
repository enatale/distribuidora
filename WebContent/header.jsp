<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
            <li class="active"><a href="#">Inicio</a></li>
            <li><a href="#about">About</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          <%
          	String nombreUsuario = (String) session.getAttribute("nombreUsuario");
          	if(nombreUsuario==null){
          %>
          	<li><a href="iniciarSesion.jsp">Iniciar sesión</a></li>
          <%}%>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    