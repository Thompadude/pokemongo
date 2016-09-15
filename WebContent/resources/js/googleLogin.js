'use strict';

$('#hiddenGoogleLoginForm').find('input[type=text]').val('');

function onSignIn(googleUser) {
    if (sessionStorage.getItem('isSignedIn') === 'false' || sessionStorage.getItem('isSignedIn') === null) {
        sessionStorage.setItem('isSignedIn', "true");

        var profile = googleUser.getBasicProfile();
        var id_token = googleUser.getAuthResponse().id_token;

        console.log('User signed in.');

        document.getElementById("hiddenGoogleLoginForm:userName").value = profile.getName();
        document.getElementById("hiddenGoogleLoginForm:email").value = profile.getEmail();
        document.getElementById("hiddenGoogleLoginForm:tokenId").value = id_token;
        $('.submitButton').click();
    }
}

function signOut() {
    sessionStorage.setItem('isSignedIn', "false");
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}