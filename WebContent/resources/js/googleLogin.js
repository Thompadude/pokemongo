/* googleLogin.js */

'use strict';

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var id_token = googleUser.getAuthResponse().id_token;

    // Fetch the desired data from the profile.
    var user = {
        userName: profile.getName(),
        email: profile.getEmail(),
        tokenId: id_token
    };

    console.log(user);

    // AJAX POST call to backend.
    var basicUrl = 'http://localhost:8080/PokeMongo/api/';
    $.ajax({
        url: basicUrl + 'user/login',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user)
    });
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}
