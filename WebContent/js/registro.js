$(document).ready(function(){
	
	$("#formAltaCliente").submit(function() {
		var cod=validarCod();
		var cant= validarCant();
		if(cod&&cant){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtNombre").focusout(function(e) {
		validarNombre();
	});
	$("#txtApellido").focusout(function(e) {
		validarApellido();
	});
	$("#txtDni").focusout(function(e) {
		validarDni();
	});
	$("#txtTelefono").focusout(function(e) {
		validarTelefono();
	});
	$("#txtDireccion").focusout(function(e) {
		validarDireccion();
	});
	$("#txtMail").focusout(function(e) {
		validarMail();
	});
	$("#txtUsuario").focusout(function(e) {
		validarUsuario();
	});
	$("#txtPass").focusout(function(e) {
		validarPass();
	});
	$("#txtCUIT").focusout(function(e) {
		validarCUIT();
	});

	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY


function validarNombre(){
	$("#errorNombre").html("");
	$("#txtNombre").attr("style","background:#FFF");
	var nomb = $("#txtNombre").val();
	if(nomb==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtNombre").attr("style","background:#f2dede");
		$("#errorNombre").html("El nombre no puede estar en blanco");
		return false;
	} else return true;
}
function validarApellido(){
	$("#errorApellido").html("");
	$("#txtApellido").attr("style","background:#FFF");	
	var apel = $("#txtApellido").val();
	if(apel==""){
		$("#txtApellido").attr("style","background:#f2dede");
		$("#errorApellido").html("El Apellido no puede estar en blanco");
		return false;
	} else return true;
}
function validarDni(){
	$("#errorDni").html("");
	$("#txtDni").attr("style","background:#FFF");	
	var dni = $("#txtDni").val();
	if(dni==""){
		$("#txtDni").attr("style","background:#f2dede");
		$("#errorDni").html("El dni no puede estar en blanco");
		return false;
	} else return true;
}
function validarTelefono(){
	$("#errorTelefono").html("");
	$("#txtTelefono").attr("style","background:#FFF");	
	var tel = $("#txtTelefono").val();
	if(tel==""){
		$("#txtTelefono").attr("style","background:#f2dede");
		$("#errorTelefono").html("El telefono no puede estar en blanco");
		return false;
	} else return true;
}
function validarDireccion(){
	$("#errorDireccion").html("");
	$("#txtDireccion").attr("style","background:#FFF");	
	var dir = $("#txtDireccion").val();
	if(dir==""){
		$("#txtDireccion").attr("style","background:#f2dede");
		$("#errorDireccion").html("La direccion no puede estar en blanco");
		return false;
	} else return true;
}
function validarMail(){
	$("#errorMail").html("");
	$("#txtMail").attr("style","background:#FFF");	
	var mail = $("#txtMail").val();
	if(mail==""){
		$("#txtMail").attr("style","background:#f2dede");
		$("#errorMail").html("El telefono no puede estar en blanco");
		return false;
	} else return true;
}
function validarCUIT(){
	$("#errorCUIT").html("");
	$("#txtCUIT").attr("style","background:#FFF");	
	var cuit = $("#txtCUIT").val();
	if(cuit==""){
		$("#txtCUIT").attr("style","background:#f2dede");
		$("#errorCUIT").html("El CUIT no puede estar en blanco");
		return false;
	} else return true;
}
function validarUsuario(){
	$("#errorUsuario").html("");
	$("#txtUsuario").attr("style","background:#FFF");	
	var us = $("#txtUsuario").val();
	if(us==""){
		$("#txtUsuario").attr("style","background:#f2dede");
		$("#errorUsuario").html("El usuario no puede estar en blanco");
		return false;
	} else return true;
}
function validarPass(){
	$("#errorPass").html("");
	$("#txtPass").attr("style","background:#FFF");	
	var pass = $("#txtPass").val();
	if(pass==""){
		$("#txtPass").attr("style","background:#f2dede");
		$("#errorPass").html("La contrasenia no puede estar en blanco");
		return false;
	} else return true;
}