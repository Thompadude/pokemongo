$('.messageBox').delay(5000).fadeOut('slow');

$('.button--addPokemon').on('click', function () {
    $('.messageBox').hide();
});

function fadeOutMessageBox() {
    $('.messageBox').delay(5000).fadeOut('slow');
}