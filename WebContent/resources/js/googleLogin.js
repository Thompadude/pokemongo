'use strict';

$('#hiddenGoogleLoginForm').find('input[type=text]').val('');

function onSignIn(googleUser) {
    console.log("Script körs")

    var id_token = googleUser.getAuthResponse().id_token;

    console.log('User signed in.');

    document.getElementById("hiddenGoogleLoginForm:tokenId").value = id_token;

    $('.logInButton').click();
    // }

}

function signOut() {
    $('.logOutButton').click();
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}


