document.querySelector("#accountaanmaakbutton").addEventListener("click", aanmaken);

function aanmaken(){

    var voornaam = document.getElementById("vn").value;
    var achternaam = document.getElementById("an").value;
    var emailadres= document.getElementById("ea").value;
    var telefoon = document.getElementById("tel").value;
    var gebruikersnaam = document.getElementById("gn").value;
    var wachtwoord= document.getElementById("ww").value;

    if(voornaam === null || achternaam === null || emailadres == null ||
    telefoon === null || gebruikersnaam === null || wachtwoord === null){
        window.alert("Niet alle velden zijn ingevuld!")
    }

    else{
        window.alert("Account succesvol aangemaakt!");
        document.location = "login.html";
    }

}

// document.addEventListener("load", event => {
//     document.querySelector("#button").addEventListener("click", login);});
//
// async function login() {
//     let formData = new FormData(document.querySelector("#aanmaak-form"));
//     let encoded = new URLSearchParams(formData.entries())
//     let response = await fetch("accounts", {method: "POST", body: encoded});
//     let myJson = await response.json();
//     if (response.ok) {
//         window.alert("Account is succesvol aangemaakt!");
//         console.log(myJson);
//         window.sessionStorage.setItem("gebruikersnaam", myJson["gebruikersnaam"]);
//         window.location.href = window.location.origin + "/afspraakmaken.html?gebruikersnaam=" + myJson["gebruikersnaam"];
//     }
// }
