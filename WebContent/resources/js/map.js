
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

	map.addListener('click', function(event) {
        var pok_id = document.getElementById('pokemonForm:pokemonSelectMenuInner').value;
        console.log(pok_id);
	    addPokemonLatLong(parseFloat(event.latLng.lat()),
				      parseFloat(event.latLng.lng()), pok_id);
        document.getElementById('input_savingPokemon:lat').value = event.latLng.lat();
        document.getElementById('input_savingPokemon:lng').value = event.latLng.lng();
  });
}

// Add attributes to
function populateCreatePokemonFields(){
    document.getElementById('input_savingPokemon:pokedexNumber').value = document.getElementById('pokemonForm:pokemonSelectMenuInner').value;
    //var name = document.getElementById('pokemonForm:itemLabel').value.split(' - ')[1];

    document.getElementById('input_savingPokemon:pokemonName').value = document.getElementById('pokemonForm:selectedPokemonMenu.itemLabel').value;
}


// Adds a marker to the map and push to the array.
function addPokemonLatLong(lati, longi, pokeIndex) {
    var imageLink = link(pokeIndex);
    var marker = new google.maps.Marker({
    position: {
		lat: parseFloat(lati),
		lng: parseFloat(longi)
	},
    map: map,
	// randomize pokemon
	icon: imageLink,
	draggable: true,
	title: "Pokemon!"
  });
  markers.push(marker);
}


function link(index) {
	return "http://www.serebii.net/pokearth/sprites/em/" + index + ".png";
}