// TODO bind this script to a button or setting

console.log('theme.js loaded...');

function changeTheme() {
    console.log('changeTheme function executed...');

    var fileUrl = '/PokeMongo/javax.faces.resource/blue.css.xhtml?ln=css';

    var fileReference = document.createElement('link');
    fileReference.setAttribute('type', 'text/css');
    fileReference.setAttribute('rel', 'stylesheet');
    fileReference.setAttribute('href', fileUrl);

    document.getElementsByTagName('head')[0].appendChild(fileReference);

    console.log('changeTheme function finished...');
}

changeTheme();