var mapAllPokemon;
var markers = [];

function initMap() {
    var lat_lng = {lat: 57.70887000, lng: 11.97456000};

    mapAllPokemon = new google.maps.Map(document.getElementById('mapAllPokemon'), {
        zoom: 18,
        center: lat_lng,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    });

    /*
     mapAllPokemon.addListener('click', function(event) {
        var pok_id = document.getElementById('pokemonForm:pokemonSelectMenuInner').value;
        addPokemonLatLong(parseFloat(event.latLng.lat()),
            parseFloat(event.latLng.lng()), pok_id);
        document.getElementById('input_pokemonForm:lat').value = event.latLng.lat();
        document.getElementById('input_pokemonForm:lng').value = event.latLng.lng();
    });
    */
}

/*
function addPokemonLatLong(lati, longi, pokeIndex) {
    deleteMarkers();
    setCoords(longi, lati);
    var imageLink = setImageLink(pokeIndex);
    var marker = new google.maps.Marker({
        position: {
            lat: parseFloat(lati),
            lng: parseFloat(longi)
        },
 mapAllPokemon: mapAllPokemon,
        icon: imageLink,
        draggable: true,
        title: "Pokemon!"
    });
    markers.push(marker);
    google.maps.event.addListener(marker, 'dragend', function (event) {
        setCoords(event.latLng.lng(), event.latLng.lat());
    })
}
*/

/*
function setCoords(longi, lati) {
    document.getElementById('input_pokemonForm:lng').value = longi;
    document.getElementById('input_pokemonForm:lat').value = lati;
}
*/

/*
function setImageLink(index) {
    return '/PokeMongo/javax.faces.resource/pokemonImages/' + index + '.png.xhtml?ln=img';
}
*/

// Sets the map on all markers in the array
/*
function setMapOnAll(mapAllPokemon) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(mapAllPokemon);
    }
}
*/

// Removes the markers from the mapAllPokemon, but keeps them in the array
/*
function clearMarkers() {
    setMapOnAll(null);
}
*/

// Shows any markers currently in the array
/*
function showMarkers() {
    setMapOnAll(mapAllPokemon);
}
*/

// Deletes all markers in the array by removing references to them
/*
function deleteMarkers() {
    clearMarkers();
    markers = [];
}*/