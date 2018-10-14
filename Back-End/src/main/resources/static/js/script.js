function validateForm() {
    var usernameLogin=document.forms["login"]["username"].value;
    var passwordLogin=document.forms["login"]["password"].value;
    if(usernameLogin=="") {
        alert(("Username must be filled out"));
        return false;
    }
    if(passwordLogin==""){
        alert(("Password must be filled out"));
        return false;
    }


}