$(document).ready(function(){
	$("#liPedido").addClass("active");
	
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
	
	$("#formItem").submit(function() {
		var cod=validarCod();
		var cant= validarCant();
		if(cod&&cant){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtCod").focusout(function(e) {
		validarCod();
	});
	$("#txtCantidad").focusout(function(e) {
		validarCant();
	});
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY

function mostrarProducto(respuesta){
	var producto = $.parseJSON(respuesta);
	$("#cuerpo").html("");
	if(producto==null){
		$("#cuerpo").html('<tr><td colspan="3"><h2>No se encontró producto con el código ingresado.</h2></td></tr>');
	} else{
		$("#cuerpo").append(
			"<tr id='1'>"+
				"<td id='cod1'>"+producto.codProducto+"</td>"+
				"<td id='desc1'>"+producto.descripcion+"</td>"+
				"<td>"+producto.importe+"</td>"+
			"</tr>"
		);
		$("tr").click(fila_click);
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
					"<td id='cod"+i+"'>"+productos[i-1].codProducto+"</td>"+
					"<td id='desc"+i+"'>"+productos[i-1].descripcion+"</td>"+
					"<td>"+productos[i-1].importe+"</td>"+
				"</tr>"
			);
		}
		$("tr").click(fila_click);
	}
}

function fila_click(){
	var fila = $(this).attr("id");
    var cod = $("#cod"+fila).html();
	var descr = $("#desc"+fila).html();
	$("#txtCod").val(cod);
	$("#txtDescripcion").val(descr);
	$("#txtCantidad").focus();
}

function validarCod(){
	$("#errorCod").html("");
	$("#txtCod").attr("style","background:#FFF");
	var valido=true;
	var cod = $("#txtCod").val();
	
	if(cod==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtCod").attr("style","background:#f2dede");
		$("#errorCod").html("El codigo de producto no puede estar en blanco");
		valido=false;
	}else {
		if(!cod.match(/^([0-9])*$/)){
			$("#txtCod").attr("style","background:#f2dede");
			$("#errorCod").html("El codigo de producto debe ser un número entero");
			valido=false
		}
	}
	return valido;
}
function validarCant(){
	$("#errorCantidad").html("");
	$("#txtCantidad").attr("style","background:#FFF");	
	var cant = $("#txtCantidad").val();
	var valido=true;
	if(cant==""){
		$("#txtCantidad").attr("style","background:#f2dede");
		$("#errorCantidad").html("La cantidad no puede estar en blanco");
		valido = false;
	}else{ 
		if(!cant.match(/^([0-9])*$/)){
			$("#txtCantidad").attr("style","background:#f2dede");
			$("#errorCantidad").html("El la cantidad debe ser un número entero");
			valido=false;
		}
	}
	return valido;
}