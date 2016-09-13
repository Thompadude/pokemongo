<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Google Login</title>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="85390473584-aead42mt4mhtf5d2brevqd0dan46s0d7.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>
<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
<a href="#" onclick="signOut();">Sign out</a>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var id_token = googleUser.getAuthResponse().id_token;

        // Fetch the desired data from the profile.
        var user = {
            userName: profile.getName(),
            email: profile.getEmail(),
            tokenId: id_token
        };

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
</script>
</html>