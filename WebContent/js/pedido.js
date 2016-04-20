$(document).ready(function(){
	$("#liPedido").addClass("active");
	
	$("#txtDescripcion").keyup(function(e) {
		if($("#txtDescripcion").val()==""){
			$("#cuerpo").html('<tr><td colspan="3"><h2>Comienze a escribir para obtener los productos</h2></td></tr>');
		} else{
	        var descripcion = {descripcion:$("#txtDescripcion").val()}
			$.post("ajaxBusquedaDesc",descripcion,llenarTabla);
		}
    });
});

function llenarTabla(respuesta){
	var productos = $.parseJSON(respuesta);
	$("#cuerpo").html("");
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

function fila_click(){
	var fila = $(this).attr("id");
    var cod = $("#cod"+fila).html();
	var descr = $("#desc"+fila).html();
	$("#txtCod").val(cod);
	$("#txtDescripcion").val(descr);
}