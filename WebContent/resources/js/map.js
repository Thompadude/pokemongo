var map;
var markers = [];

function initMap() {
    var lat_lng = {lat: 57.70887000, lng: 11.97456000};

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 18,
        center: lat_lng,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    });

    // This event listener will call addPokemonLatLong() when the map is clicked.
    map.addListener('click', function (event) {
        var pok_id = document.getElementById('savingPokemon:pokemonSelectMenuInner').value;
        addPokemonLatLong(parseFloat(event.latLng.lat()),
            parseFloat(event.latLng.lng()), pok_id);
    });
}

// Adds a marker to the map and push to the array.
function addPokemonLatLong(lati, longi, pokeIndex) {
    // Delete previous marker while adding pokemon
    deleteMarkers();

    // Set the coords in the hidden input fields
    setCoords(longi, lati);

    // Fetch and set the image for the marker
    var imageLink = setImageLink(pokeIndex);

    // Create the marker
    var marker = new google.maps.Marker({
        position: {
            lat: parseFloat(lati),
            lng: parseFloat(longi)
        },
        map: map,
        icon: imageLink,
        draggable: true,
        title: "Pokemon!"
    });

    // Push the marker to markers array
    markers.push(marker);

    // Attach a listener to the marker which updates coords when after dragging
    google.maps.event.addListener(marker, 'dragend', function (event) {
        setCoords(event.latLng.lng(), event.latLng.lat());
    })
}

function setCoords(longi, lati) {
    document.getElementById('input_savingPokemon:lng').value = longi;
    document.getElementById('input_savingPokemon:lat').value = lati;
}

function setImageLink(index) {
    return '/PokeMongo/javax.faces.resource/pokemonImages/' + index + '.png.xhtml?ln=img';
}

// Sets the map on all markers in the array
function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

// Removes the markers from the map, but keeps them in the array
function clearMarkers() {
    setMapOnAll(null);
}

// Shows any markers currently in the array
function showMarkers() {
    setMapOnAll(map);
}

// Deletes all markers in the array by removing references to them
function deleteMarkers() {
    clearMarkers();
    markers = [];
}