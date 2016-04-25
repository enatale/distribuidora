<%@page import="entidades.Producto"%>
<%@page import="ui.Productos"%>
<%@page import="entidades.Linea_pedido"%>
<%@page import="negocio.CtrlPedidos"%>
<%@page import="appExceptions.ApplicationException"%>
<%@page import="negocio.CtrlModificarPedido"%>
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
    <script src="js/pedidoModificar2.js"></script>
  </head>
  <body>
  
    <%@ include file="header.jsp" %>
    <%
    Pedidos pedido;
    Cliente cliente = (Cliente)session.getAttribute("usuario");
    int nroPedido;
    try{
	    if(request.getParameter("txtNroPedido")!=null){
	    	nroPedido=Integer.parseInt(request.getParameter("txtNroPedido"));
	    } else throw new ApplicationException("Error al recepcionar el nro de pedido",null);
	    
	    pedido= new CtrlModificarPedido().getByNro(nroPedido);
	    if(cliente==null){
	    	throw new ApplicationException("Para ver esta página debe estar logueado como cliente", null);
	    }
	    if(pedido==null){
	    	throw new ApplicationException("El pedido seleccionado no existe",null);
	    }
	    if(pedido.getCliente().getDni()!=cliente.getDni()){
	    	throw new ApplicationException("El pedido seleccionado no corresponde al usuario loggeado",null);
	    }
	    if(pedido.getEstado().getId_estado_pedido()!=1){
	    	throw new ApplicationException("El pedido seleccionado no está \"pendiente\"",null);
	    }
	    for(int i=0; i+1<pedido.getLineas().size();i++){
	    	for(int j=i+1; j<pedido.getLineas().size();j++){
	    		Linea_pedido auxiliar;
	    		Linea_pedido pi= pedido.getLineas().get(i);
	    		Linea_pedido pj = pedido.getLineas().get(j);
	    		if(pi.getProducto().getDescripcion().compareToIgnoreCase(pj.getProducto().getDescripcion())>0){
	    			auxiliar = pi;
	    			pi = pj;
	    			pj = auxiliar;
	    			pedido.getLineas().set(i, pi);
	    			pedido.getLineas().set(j, pj);
	    		}
	    	}
	    }
	     
    	if(cliente==null||cliente.getCodCliente()==0){
    		throw new ApplicationException("Para ver esta página debe iniciar sesión como cliente.",null);
	    }else {%>		
	    <div class="container">
	    <div class="row" style="text-align: center;">
			<h1>SU PEDIDO</h1>
			<table id="tablaPedido" class="table table-bordered table-hover">
	            <thead>
	              <tr>
	                <th>#</th>
	                <th>Código</th>
	                <th>Descripción</th>
	                <th>Precio Unitario</th>
	                <th>Cantidad</th>
	                <th>Subtotal</th>
	                <th>Borrar</th>
	              </tr>
	            </thead>
	            <tbody>
	            
	            <%int i=1;
	            float total=0;
	            for(Linea_pedido item:pedido.getLineas()){
	            	float subtotal=item.getCantidad()*item.getProducto().getImporte();
	            %>            
	              <tr id="<%=i %>">
	                <td><%=i %></td>
	                <td id="cod<%=i %>"><%=item.getProducto().getCodProducto() %></td>
	                <td><%=item.getProducto().getDescripcion() %></td>
	                <td><%=item.getProducto().getImporte() %></td>
	                <td id="cant<%=i %>"><%=item.getCantidad() %></td>
	                <td><%=subtotal %></td>
	                <td>
		                <form action="pedido/borrarLineaGuardada" method="post">	                                
			                <input value="<%=pedido.getNumero_pedido() %>" type="hidden"  name="nroPedido" class="hidden" >
			                <input value="<%=item.getProducto().getCodProducto() %>" type="hidden"  name="codProducto" class="hidden" >
			                <input value="<%=item.getCantidad() %>" type="hidden"  name="cantidad" class="hidden" >
			                <button class="btn btn-danger" type="submit">X</button>
		            	</form>
		            </td>
	              </tr>
	            <%	i++;
	            	total+=subtotal;
	            }%>
	            <tr>
	                <td style="text-align: right;" colspan="5"><h4>IMPORTE TOTAL DEL PEDIDO</h4></td>
	                <td><h4><%=total %></h4></td>
	              </tr>
	            </tbody>
	          </table>
	         <div class="row" style="text-align: center;">
	         	<form action="pedido/borrarPedidoGuardado" method="post">	                                
	                <input type="hidden" name="nroPedido" class="hidden" value="<%=nroPedido %>" placeholder="nroPedido" >
	                <button class="btn btn-danger btn-lg" type="submit">CANCELAR PEDIDO</button>
	            </form>
			</div>
		</div>
	    <div class="row">
	    	<div class="col-sm-4">
	    		<h1 style="text-align:center">Modificar cantidad</h1>
	    		<form action="modificarLineaGuardada" method="post" id="formLinea">
	    			
	    			<input type="hidden" name="nroPedido" value="<%=nroPedido %>">
	    			
	                <label for="txtCodigoLinea" class="sr-only">Código</label>
	                <input type="text" id="txtCodigoLinea" name="codProducto" class="form-control codProducto" placeholder="Código de producto">
	                <label for="txtCodigoLinea" id="errortxtCodigoLinea" style="color:#FF0004"></label>
	                                
	                <label for="txtCantidadLinea" class="sr-only">Producto</label>
	                <input type="text" id="txtCantidadLinea" name="cantidad" class="form-control cantidad" placeholder="Cantidad">
	                <label for="txtCantidadLinea" id="errortxtCantidadLinea" style="color:#FF0004"></label>
	                
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Modificar</button>
	            </form>
	    		<% 
	      			String mensajeModificar=(String)request.getAttribute("mensajeModificar");
	        		if(mensajeModificar!=null){
	      		%>
	      			<div class="alert alert-danger" role="alert">
	        			<strong>Error!</strong> <%=mensajeModificar %>
	      			</div>
	      		<%
	        		}
	        	%>
	    	</div>
	    	<div class="col-sm-4">
	    		<h1 style="text-align:center">Agregar producto</h1>
	        	<form action="agregarLineaGuardada" method="post" id="formItem">
	        	
	        		<input type="hidden" name="nroPedido" value="<%=nroPedido %>">
	        	
	                <label for="txtDescripcion" class="sr-only">Producto</label>
	                <input type="text" id="txtDescripcion" name="descripcion" class="form-control" placeholder="Descripción">
	                <label for="txtDescripcion" id="errortxtDescripcion" style="color:#FF0004"></label>
	                
	                <label for="txtCod" class="sr-only">Código</label>
	                <input type="text" id="txtCod" name="codigo" class="form-control codProducto" placeholder="Código">
	                <label for="txtCod" id="errortxtCod" style="color:#FF0004"></label>
	                                
	                <label for="txtCantidad" class="sr-only">Producto</label>
	                <input type="text" id="txtCantidad" name="cantidad" class="form-control cantidad" placeholder="Cantidad">
	                <label for="txtCantidad" id="errortxtCantidad" style="color:#FF0004"></label>
	                
	                <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
	            </form>
	            <% 
	      			String mensajeAgregar=(String)request.getAttribute("mensajeAgregar");
	        		if(mensajeAgregar!=null){
	      		%>
	      			<div class="alert alert-danger" role="alert">
	        			<strong>Error!</strong> <%=mensajeAgregar %>
	      			</div>
	      		<%
	        		}
	        	%>
	        </div>
	        <div class="col-sm-4">
				<table id="tablaBusqueda" class="table table-hover">
					<thead>
						<tr>
							<th>Código</th>
							<th>Descripcion</th>
							<th>Precio</th>
						</tr>
					</thead>
					<tbody id="cuerpo">
						<tr>
							<td colspan="3"><h2>Comience a escribir para obtener los productos</h2></td>
						</tr>
					</tbody>
				</table>      
			</div>
		</div>
		</div><!-- /container -->
		<%}	
    } catch (ApplicationException e){%>
    	<h1 style="color: red; text-align: center;"><%=e.getMessage() %></h1>
    	<h4 style="text-align: center;"><a href="pedidoModificar1.jsp">Volver</a></h4>
    <%}catch(NumberFormatException e){
	   	request.setAttribute("error", "La cantidad debe ser un número entero");
	   	request.getRequestDispatcher("pedidoModificar1.jsp").forward(request, response);
    }%>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>