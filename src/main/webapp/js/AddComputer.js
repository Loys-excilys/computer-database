/**
 * 
 */
 
 function limitMinDate(value) {
   var input = document.getElementsByName("dateDiscontinued");
   input[0].setAttribute("min", value);
}

 function limitMaxDate(value) {
   var input = document.getElementsByName("dateIntroduced");
   input[0].setAttribute("max", value);
}


 function verifNameComputer(value) {
   var input = document.getElementsByName("computerName");
   if(!value.match(/^([a-zA-Z ]+)$/)){
   		input[0].setAttribute("placeholder", "erreurSaisie");
   		input[0].value = "";
   		input[0].style.borderColor = "red";
   }else{
   		input[0].style.borderColor = "black";
   		input[0].setAttribute("placeholder", "");
   }
}