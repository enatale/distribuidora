$(document).ready(function(){
	$("#liModificarPedido").addClass("active");
	
	$("#txtDescripcion").keyup(function(e) {
		if($("#txtDescripcion").val()==""){
			$("#cuerpo").html('<tr><td colspan="3"><h2>Comience a escribir para obtener los productos</h2></td></tr>');
		} else{
	        var descripcion = {descripcion:$("#txtDescripcion").val()}
			$.post("ajaxBusquedaDesc",descripcion,llenarTabla);
		}
    });
	$("#txtCod").keyup(function(e) {
		if($("#txtCod").val()==""){
			$("#cuerpo").html('<tr><td colspan="3"><h2>Comience a escribir para obtener los productos</h2></td></tr>');
		} else{
	        var codigo = {codigo:$("#txtCod").val()}
			$.post("ajaxBusquedaCod",codigo,mostrarProducto);
		}
    });
	
	$("#tablaPedido tr").click(function(){
		var fila = $(this).attr("id");
		var cod = $("#cod"+fila).html();
	    var cant = $("#cant"+fila).html();
		$("#txtCodigoLinea").val(cod);
		$("#txtCantidadLinea").val(cant);
		$("#txtCantidadLinea").focus();
		$("#txtCantidadLinea").select()
	});
});//Cierro ready

/////////////////
//Para la busqueda para agregar productos
function mostrarProducto(respuesta){
	var producto = $.parseJSON(respuesta);
	$("#cuerpo").html("");
	if(producto==null){
		$("#cuerpo").html('<tr><td colspan="3"><h2>No se encontró producto con el código ingresado.</h2></td></tr>');
	} else{
		$("#cuerpo").append(
			"<tr id='1'>"+
				"<td id='codBusqueda"+i+"'>"+producto.codProducto+"</td>"+
				"<td id='descBusqueda"+i+"'>"+producto.descripcion+"</td>"+
				"<td>"+producto.importe+"</td>"+
			"</tr>"
		);
		$("#tablaBusqueda tr").click(filaBusqueda_click);
	}
}

function llenarTabla(respuesta){
	var productos = $.parseJSON(respuesta);
	$("#cuerpo").html("");
	if(productos.length==0){
		$("#cuerpo").html('<tr><td colspan="3"><h2>No se encontraron productos que coincidan con la descripción.</h2></td></tr>');
	}else {
		for (i=1;i<=productos.length;i++){
			$("#cuerpo").append(
				"<tr id='"+i+"'>"+
					"<td id='codBusqueda"+i+"'>"+productos[i-1].codProducto+"</td>"+
					"<td id='descBusqueda"+i+"'>"+productos[i-1].descripcion+"</td>"+
					"<td>"+productos[i-1].importe+"</td>"+
				"</tr>"
			);
		}
		$("#tablaBusqueda tr").click(filaBusqueda_click);
	}
}

function filaBusqueda_click(){
	var fila = $(this).attr("id");
    var cod = $("#codBusqueda"+fila).html();
	var descr = $("#descBusqueda"+fila).html();
	$("#txtCod").val(cod);
	$("#txtDescripcion").val(descr);
	$("#txtCantidad").focus();
}

////////////////////////////