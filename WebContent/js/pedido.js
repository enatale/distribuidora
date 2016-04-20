$(document).ready(function(){
	$("#liPedido").addClass("active");
	$("tr").click(fila_click);
	
	$("#txtDescripcion").keyup(function(e) {
        var descripcion = {descripcion:$("#txtDescripcion").val()}
		$.post("ajaxBusquedaDesc",descripcion,llenarTabla);
    });
});

function llenarTabla(listado){
	var productos = new Array();
	productos=$.parseJSON(listado);
	//$("#cuerpo").html(listado);
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