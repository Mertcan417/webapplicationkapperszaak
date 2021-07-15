document.querySelector("#inloggen").addEventListener("click",login);
function login() {
    var username = document.getElementById("gn1").value;
    var password = document.getElementById("ww1").value;
    console.log(username);
    console.log(password);

    //dummy data
    if (username === "Mertcan" && password === "Mertcan") {
        console.log("Dit klopt!")
        alert("U bent succesvol ingelogd!");
        document.location.href = "afspraakmaken.html";
    }

    else if((username === null && password === null) || (username === null || password === null) ) {
        alert("Vul alle gegevens in!");
        console.log("Er staat niets ingevuld!");
    }
    else{
        alert("Gebruikersnaam/wachtwoord onjuist/account bestaat niet")
    }
    // else if(username === "Mertcan" && password === "wachtwoord123"){
    //     window.alert("Succesvol ingelogd!");
    //     document.location = 'afspraakmaken.html';
    // // }
    // else{
    //     window.alert("De ingevoerde velden zijn onjuist/account bestaat niet!")
    // }
}

//Fetch API werkt niet, geen koppeling met de backend
// async function login() {
//     let formData = new FormData(document.querySelector("#formdata"));
//     let encData = new URLSearchParams(formData.entries());
//
//     let response = await fetch("account/inloggen", {method: "POST", body: encData});
//     let myJson = await response.json();
//
//     if (response.status === 200) {
//         window.alert("U bent succesvol ingelogd!");
//         console.log(myJson);
//         window.sessionStorage.setItem("key", myJson.JWT);
//         window.location.href = window.location.origin + "afspraakmaken.html?gebruikersnaam=" + myJson["gebruikersnaam"];
//     } else {
//         console.log("Inloggen mislukt probeer het opnieuw!");
//         window.alert("inloggen mislukt probeer het opnieuw!");
//     }
// }

// async function secure() {
//     console.log("secure");
//
//     var fetchoptions = {
//         method: "GET",
//         headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("key")}
//     }
//
//     let response = await fetch("inloggen", fetchoptions); //moet een andere resource zijn
//     let myJson = await response.json();
//
//     if (response.ok) {
//         console.log(myJson);
//     } else {
//         console.log("fail!")
//     }
// }

