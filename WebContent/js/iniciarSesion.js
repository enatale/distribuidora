$(document).ready(function() {
			
			//VALIDACION AL ENVIAR FORMULARIO
            $("#formLogin").submit(function() {
				var usuario=validarUsuario();
				var pass= validarPass();
				if(pass&&usuario){
					return true;
				} else {
					return false;
				}

            });//formLogin Submit
			
			//VALIDACION CUANDO CAMBIO EL FOCO
			$("#txtUsuario").focusout(function(e) {
				validarUsuario();
            });
			$("#txtPass").focusout(function(e) {
				validarPass();
            });
			//Pongo en blanco los campos cuando empiezo a escribir
			$("input").keydown(function(){
				$(this).attr("style","background:#FFF");
			})
			
        });//Document Ready
		
	function validarUsuario(){
		//Limpio mensaje de error y pongo en blanco el campo
		$("#errorUsuario").html("");
		$("#txtUsuario").attr("style","background:#FFF");
		var usuario = $("#txtUsuario").val();
		if(usuario==""){
			//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
			$("#txtUsuario").attr("style","background:#FF0000");
			$("#errorUsuario").html("El nombre de usuario no puede estar en blanco");
			return false;
		} else return true;
	}
	function validarPass(){
		$("#errorPass").html("");
		$("#txtPass").attr("style","background:#FFF");	
		var cont = $("#txtPass").val();
		if(cont==""){
			$("#txtPass").attr("style","background:#FF0000");
			$("#errorPass").html("La contraseña no puede estar en blanco");
			return false;
		} else return true;
	}