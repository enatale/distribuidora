$(document).ready(function(){
	
	$("#formAltaProducto").submit(function() {
		var nro=validarNro();
		var stock= validarStock();
		if(nro&&stock){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtNro").focusout(function(e) {
		validarNum();
	});
	$("#txtStock").focusout(function(e) {
		validarStock();
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
function validarStock(){
	$("#errorStock").html("");
	$("#txtStock").attr("style","background:#FFF");	
	var cant = $("#txtStock").val();
	if(cant==""){
		$("#txtStock").attr("style","background:#f2dede");
		$("#errorStock").html("El Stock no puede estar en blanco");
		return false;
	} else return true;
}
