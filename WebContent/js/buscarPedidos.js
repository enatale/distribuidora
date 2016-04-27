$(document).ready(function(){
	
	$("#formListado").submit(function() {
		var nro=validarNro();
		var fIni=validarFechaIni();
		var fFin=validarFechaFin();
		if(nro&&fIni&&fFin){
			return true;
		} else {
			return false;
		}
	});
	$("#txtFechaIni").focusout(function(e) {
		validarFechaIni();
	});
	$("#txtFechaFin").focusout(function(e) {
		validarFechaFin();
	});
	$("#txtNro").focusout(function(e) {
		validarNum();
	});
	

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

function validarFechaIni(){
	$("#errorFechaIni").html("");
	$("#txtFechaIni").attr("style","background:#FFF");	
	var fech = $("#txtFechaIni").val();
	if(!isValidDate(fech)){
		$("#txtFechaIni").attr("style","background:#f2dede");
		$("#errorFechIni").html("Formato de fecha incorrecto o la fecha no puede estar en blanco");
		return false;
	} else return true;
}
function validarFechaFin(){
	$("#errorFechaFin").html("");
	$("#txtFechaFin").attr("style","background:#FFF");	
	var fech = $("#txtFechaIni").val();
	if(!isValidDate(fech)){
		$("#txtFechaFin").attr("style","background:#f2dede");
		$("#errorFechFin").html("Formato de fecha incorrecto o la fecha no puede estar en blanco");
		return false;
	} else return true;
}
function isValidDate(str){
	// STRING FORMAT yyyy-mm-dd
	if(str=="" || str==null){return false;}								
	
	// m[1] is year 'YYYY' * m[2] is month 'MM' * m[3] is day 'DD'					
	var m = str.match(/(\d{4})-(\d{2})-(\d{2})/);
	
	// STR IS NOT FIT m IS NOT OBJECT
	if( m === null || typeof m !== 'object'){return false;}				
	
	// CHECK m TYPE
	if (typeof m !== 'object' && m !== null && m.size!==3){return false;}
				
	var ret = true; //RETURN VALUE						
	var maxYear = 2200; //YEAR NOW
	var minYear = 1999; //MIN YEAR
	
	// YEAR CHECK
	if( (m[1].length < 4) || m[1] < minYear || m[1] > maxYear){ret = false;}
	// MONTH CHECK			
	if( (m[2].length < 2) || m[2] < 1 || m[2] > 12){ret = false;}
	// DAY CHECK
	if( (m[3].length < 2) || m[3] < 1 || m[3] > 31){ret = false;}
	
	return ret;			
}
