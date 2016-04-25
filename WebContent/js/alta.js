<<<<<<< HEAD
$(document).ready(function(){
	
	$("#formAltaProducto").submit(function() {
		var desc=validarDesc();
		var stock=validarStock();
		var imp = validarImporte();
		var fecha=validarFecha();
		if(desc&&stock&&imp&&fecha&&form){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtDescripcion").focusout(function(e) {
		validarDesc();
	});
	$("#txtStock").focusout(function(e) {
		validarStock();
	});
	$("#txtImporte").focusout(function(e) {
		validarImporte();
	});
	$("#txtFecha").focusout(function(e) {
		validarFecha();
	});
	$("#txtFecha").focusout(function(e) {
		validaFechaAAAAMMDD();
	});
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY


function validarDesc(){
	$("#errorDescripcion").html("");
	$("#txtDescripcion").attr("style","background:#FFF");
	var desc = $("#txtDescripcion").val();
	if(desc==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtDescripcion").attr("style","background:#f2dede");
		$("#errorDescripcion").html("la descripcion del producto no puede estar en blanco");
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
function validarImporte(){
	$("#errorImporte").html("");
	$("#txtImporte").attr("style","background:#FFF");	
	var imp = $("#txtImporte").val();
	if(imp==""){
		$("#txtImporte").attr("style","background:#f2dede");
		$("#errorImporte").html("El importe no puede estar en blanco");
		return false;
	} else return true;
}
function validarFecha(){
	$("#errorFecha").html("");
	$("#txtFecha").attr("style","background:#FFF");	
	var fech = $("#txtFecha").val();
	if(!isValidDate(fech)){
		$("#txtFecha").attr("style","background:#f2dede");
		$("#errorFecha").html("Formato de fecha incorrecto o la fecha no puede estar en blanco");
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
=======
$(document).ready(function(){
	
	$("#formAltaProducto").submit(function() {
		var cod=validarCod();
		var cant= validarCant();
		if(cod&&cant){
			return true;
		} else {
			return false;
		}
	});
	
	$("#txtDescripcion").focusout(function(e) {
		validarDesc();
	});
	$("#txtStock").focusout(function(e) {
		validarStock();
	});
	$("#txtImporte").focusout(function(e) {
		validarImporte();
	});
	$("#txtFecha").focusout(function(e) {
		validarFecha();
	});
//	$("#txtFecha").focusout(function(e) {
	//	validaFechaAAAAMMDD();
//	});
	$("input").keydown(function(){
		$(this).attr("style","background:#FFF");
	})
	
	
});//DOCUMENT READY


function validarDesc(){
	$("#errorDescripcion").html("");
	$("#txtDescripcion").attr("style","background:#FFF");
	var desc = $("#txtDescripcion").val();
	if(desc==""){
		//Si el campo esta vacio muestro el mensaje y pongo el campo en rojo
		$("#txtDescripcion").attr("style","background:#f2dede");
		$("#errorDescripcion").html("La descripcion del producto no puede estar en blanco");
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
function validarImporte(){
	$("#errorImporte").html("");
	$("#txtImporte").attr("style","background:#FFF");	
	var imp = $("#txtImporte").val();
	if(imp==""){
		$("#txtImporte").attr("style","background:#f2dede");
		$("#errorImporte").html("El importe no puede estar en blanco");
		return false;
	} else return true;
}
function validarFecha(){
	$("#errorFecha").html("");
	$("#txtFecha").attr("style","background:#FFF");	
	var fech = $("#txtFecha").val();
	if(fech==""){
		$("#txtFecha").attr("style","background:#f2dede");
		$("#errorFecha").html("La fecha no puede estar en blanco");
		return false;
	} else return true;
}
/*
function validaFechaAAAAMMDD(){
	var dtch="-";
	var minYear=2010;
	var maxYear=2100;
	function isInteger(s){
		var i;
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (((c < "0") || (c > "9"))) return false;
		}
		return true;
	}
	function stripCharsInBag(s, bag){
		var i;
		var returnString = "";
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (bag.indexOf(c) == -1) returnString += c;
		}
		return returnString;
	}
	function daysInFebruary (year){
		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
		}
		return this
	}
	function isDate(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strYear=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strDay=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			return false
		}
		return true
	}
	$("#errorFecha").html("");
	$("#txtFecha").attr("style","background:#FFF");	
	var fech = $("#txtFecha").val();
	if(isDate(fech)){
		return true;
	}else{
		return false;
		$("#txtFecha").attr("style","background:#f2dede");
		$("#errorFecha").html("Formato de fecha incorrecto. Formato 'YYYY-MM-dd'");
	}
	
}
	*/
>>>>>>> b64814dfc498ba5409dddf0a989b391c77a24fca
