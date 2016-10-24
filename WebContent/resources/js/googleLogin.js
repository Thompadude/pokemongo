'use strict';

$('#hiddenGoogleLoginForm').find('input[type=text]').val('');

function onSignIn(googleUser) {
    console.log("Script körs")

    var profile = googleUser.getBasicProfile();
    var id_token = googleUser.getAuthResponse().id_token;

    console.log('User signed in.');

    document.getElementById("hiddenGoogleLoginForm:userName").value = profile.getName();
    document.getElementById("hiddenGoogleLoginForm:email").value = profile.getEmail();
    document.getElementById("hiddenGoogleLoginForm:tokenId").value = id_token;
    $('.logInButton').click();
    isThemeActive()
    // }

}

function signOut() {
    $('.logOutButton').click();
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}

function isThemeActive() {
    var currentTheme = document.getElementById("theme");

    if (currentTheme.value == null || currentTheme.value == ""){
        location.reload();
    }
}

