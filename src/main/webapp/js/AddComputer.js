/**
 * 
 */
 
 function limitDate(value) {
   var input = document.getElementsByName("dateDiscontinued");
   input[0].setAttribute("min", value);
}


 function verifNameComputer(value) {
   var input = document.getElementsByName("computerName");
   if(!value.match(/^([a-zA-Z1-9 _-]+)$/)){
   		input[0].setAttribute("placeholder", "erreurSaisie");
   		input[0].value = "";
   		input[0].style.borderColor = "red";
   }else{
   		input[0].style.borderColor = "black";
   		input[0].setAttribute("placeholder", "");
   }
}