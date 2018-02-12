function showKeyCode(event) {
event = event || window.event;
var keyID = (event.which) ? event.which : event.keyCode;
if(( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || ( keyID >= 37 && keyID <= 40 ) ||keyID == 8 || keyID == 46){
          document.getElementById("keyinfo").innerHTML = keyID;
          return keyID;
        }
        else
        {
          document.getElementById("keyinfo").innerHTML = keyID + " = 숫자만 입력해주세요";
          return false;
        }

}

var primeReason = document.getElementById("primeReason")
console.log(primeReason.options[primeReason.selectedIndex].text);
