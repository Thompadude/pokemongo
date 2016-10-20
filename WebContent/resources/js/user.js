/* user.js */

'use strict';

const MAX_FILE_SIZE = 1024 * 1024;

$(document).ready(function () {

    // Toggle showing the upload field
    $('.userInfo__uploadShower').click(function () {
        $('.userInfo__upload').slideToggle(200);
    });

    $(document).on('click', '.addPokemonButton', function(){
        initPokemon();
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