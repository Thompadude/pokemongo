var mapAllPokemon;
var markers = [];
var pokemonList = [];

// Get the list of user added pokemon via RESTful
$.get('http://localhost:8080/PokeMongo/rest/pokemon', 'application/json',
    function (response) {
        pokemonList = response;
        populateMap();
    });

// Initialize the Google map
function initMap() {
    var lat_lng = {lat: 57.70887000, lng: 11.97456000};

    mapAllPokemon = new google.maps.Map(document.getElementById('mapAllPokemon'), {
        zoom: 18,
        center: lat_lng,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    });
}

// Get google pin icon for the pokemon
function getIcon(pokemon) {
    return {
        url: getImageLink(pokemon),
        scaledSize: new google.maps.Size(40, 40)
    };
}

// Get google marker for the pokemon
function getMarker(pokemon, icon) {
    return new google.maps.Marker({
        position: {
            lat: parseFloat(pokemon.lat),
            lng: parseFloat(pokemon.lng)
        },
        mapAllPokemon: mapAllPokemon,
        icon: icon,
        draggable: false,
        title: pokemon.name + "\nHP: " + pokemon.healthPoints + "\nCP: " + pokemon.combatPower
    });
}

// Populate Google map with all user added pokemon
function populateMap() {
    var marker, icon;
    if (pokemonList != undefined) {
        for (var i = 0; i < pokemonList.length; i++) {
            icon = getIcon(pokemonList[i]);
            marker = getMarker(pokemonList[i], icon);
            markers.push(marker);
        }
        setMapOnAll(mapAllPokemon);
    }
}

// Get image link for the pokemon
function getImageLink(pokemon) {
    return '/PokeMongo/javax.faces.resource/pokemonImages/' + pokemon.pokedexNumber + '.png.xhtml?ln=img';
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