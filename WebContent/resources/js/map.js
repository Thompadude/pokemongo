
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
	var pokeIndex =document.getElementById(('psm').itemValue);
	console.log(pokeIndex + 'hej då');
	console.log(pokeIndex);

	addPokemonLatLong(parseFloat(event.latLng.lat()),
				      parseFloat(event.latLng.lng()), pokeIndex);
  });      
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
	return "http://www.serebii.net/pokearth/sprites/em/" + "025" + ".png";
}