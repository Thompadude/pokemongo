
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
console.log('hej from map.addListener()');
	var pokeIndex =document.getElementById('pokemonSelectMenu').value;
console.log(pokeIndex + 'hej då');
	var imageLink = "";
	
	if (pokeIndex.length == 0 || pokeIndex.length > 3 || pokeIndex == null) {
		imageLink = '0' + Math.floor((Math.random() * 89) + 10);
	} else {
		imageLink = fixPokedexNumber(pokeIndex);
	}
	  
	addPokemonLatLong(parseFloat(event.latLng.lat()),
				      parseFloat(event.latLng.lng()), imageLink);
  });      
}

function fixPokedexNumber(pokedexNumber) {

	while (pokedexNumber.length < 3) {
		pokedexNumber = '0' + pokedexNumber;
	}

	return pokedexNumber;
}

// Adds a marker to the map and push to the array.  
function addPokemonLatLong(lati, longi, pokeIndex) {
	
	var imageLink = "";
	
	if (pokeIndex.length == 0 || pokeIndex.length > 3 || pokeIndex == null) {
		imageLink = link('0' + Math.floor((Math.random() * 89) + 10));
	} else {
		imageLink = link(fixPokedexNumber(pokeIndex));
	}
	
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

// Adds a marker to the map and push to the array.
function addPokemon() {

	var lati = document.getElementById('latitude').value;
	var longi = document.getElementById('longitude').value;
	var pokeNumber = document.getElementById('pokemonSelectMenu').value.split(' - ')[0];
	
	addPokemonLatLong(lati, longi, pokeNumber);
}

function link(index) {
	return "http://www.serebii.net/pokearth/sprites/em/" + index + ".png";
}

function choosingPokemon(){
    console.log('börjarn');
    var pokeNumber = document.getElementById('pokemonSelectMenu').value.split(' - ')[0];
	document.getElementById('indexNumber').value = pokeNumber;
    console.log('fim');
}