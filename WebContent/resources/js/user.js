/* user.js */

'use strict';

const MAX_FILE_SIZE = 1024 * 1024;
var selectedPokemon;

$(document).ready(function () {

    // Toggle showing the upload field
    $('.userInfo__uploadShower').click(function () {
        $('.userInfo__upload').slideToggle(200);
    });

    $(document).on('click', '.addPokemonButton', function () {
        initPokemon();
    });

});

$(document).ready(function() {

    // Toggle showing the upload field
    $('.userInfo__changeTeam').click(function() {
        $('.userInfo__teamInfo').slideToggle(200);
    });


});

function checkFileSize(input) {
    if (input.files && input.files[0].size > MAX_FILE_SIZE) {
        console.log("File too big!");
        input.value = null;
        document.getElementById("hiddenFileForm:hiddenFileButton").click();
        return false;
    }
}

function formCallback() {
    document.getElementById('pokemonForm:pokemonSelectMenuInner').value = selectedPokemon;
    fadeOutMessageBox();
}