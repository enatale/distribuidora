$(document).ready(function(){
	
	$("#formAltaProducto").submit(function() {
		var nro=validarNro();
		if(nro){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtNro").focusout(function(e) {
		validarNum();
	});
	
	
//	$("#txtFecha").focusout(function(e) {
	//	validaFechaAAAAMMDD();
//	});
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY


function validarNum(){
	$("#errorNumero").html("");
	$("#txtNro").attr("style","background:#FFF");
	var nro = $("#txtNro").val();
	if(nro==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtNro").attr("style","background:#f2dede");
		$("#errorNumero").html("la descripcion del producto no puede estar en blanco");
		return false;
	} else return true;
}

