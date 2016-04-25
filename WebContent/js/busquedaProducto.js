$(document).ready(function(){
	
	$("#formAltaProducto").submit(function() {
		var num=validarNum();
		if(num){
			return true;
		} else return false;
	});
	
	$("#txtCod").focusout(function(e) {
		validarDesc();
	});

	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY


function validarNum(){
	$("#errorCod").html("");
	$("#txtCod").attr("style","background:#FFF");
	var nro = $("#txtCod").val();
	if(nro==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtCod").attr("style","background:#f2dede");
		$("#errorCod").html("la descripcion del producto no puede estar en blanco");
		return false;
	} else return true;
}
