var mapAllPokemon;
var markers = [];
var pokemonList = [];

$.get('http://localhost:8080/PokeMongo/rest/pokemon', 'application/json',
    function (response) {
        pokemonList = response;
        console.log('*LOG* pokemonList withing get function');
        console.log(pokemonList);
        populateMap();
    });

function initMap() {
    var lat_lng = {lat: 57.70887000, lng: 11.97456000};

    mapAllPokemon = new google.maps.Map(document.getElementById('mapAllPokemon'), {
        zoom: 18,
        center: lat_lng,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    });
}

function populateMap() {
    console.log('*LOG* pokemonList withing populateMap function');
    console.log(pokemonList);
    var marker, icon;
    for (var i = 0; i < pokemonList.length; i++) {

        icon = {
            url: getImageLink(pokemonList[i].pokedexNumber),
            scaledSize: new google.maps.Size(40, 40)
        };

        marker = new google.maps.Marker({
            position: {
                lat: parseFloat(pokemonList[i].lat),
                lng: parseFloat(pokemonList[i].lng)
            },
            mapAllPokemon: mapAllPokemon,
            icon: icon,
            draggable: false,
            title: pokemonList[i].name
        });
        markers.push(marker);
    }
    setMapOnAll(mapAllPokemon);
}

function getImageLink(index) {
    console.log("Getting image with index: " + index);
    return '/PokeMongo/javax.faces.resource/pokemonImages/' + index + '.png.xhtml?ln=img';
}
// Sets the map on all markers in the array
function setMapOnAll(mapAllPokemon) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(mapAllPokemon);
    }
}

// Removes the markers from the mapAllPokemon, but keeps them in the array
function clearMarkers() {
    setMapOnAll(null);
}

// Shows any markers currently in the array
function showMarkers() {
    setMapOnAll(mapAllPokemon);
}

// Deletes all markers in the array by removing references to them
function deleteMarkers() {
    clearMarkers();
    markers = [];
}