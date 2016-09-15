/* googleLogin.js */

'use strict';

console.log(sessionStorage.getItem('isSignedIn'));

function onSignIn(googleUser) {
    debugger;
    if (sessionStorage.getItem('isSignedIn') === 'false') {
        sessionStorage.setItem('isSignedIn', "true");

        var profile = googleUser.getBasicProfile();
        var id_token = googleUser.getAuthResponse().id_token;

        document.getElementById("hiddenGoogleLoginForm:userName").value = profile.getName();
        document.getElementById("hiddenGoogleLoginForm:email").value = profile.getEmail();
        document.getElementById("hiddenGoogleLoginForm:tokenId").value = id_token;
        // TODO fix this. Won't work :-(
        document.getElementById("hiddenGoogleLoginForm").submit();
    }
}

function signOut() {
    debugger;
    sessionStorage.setItem('isSignedIn', "false");
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}
