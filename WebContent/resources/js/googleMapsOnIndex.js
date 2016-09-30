/* googleMapsOnIndex.js */

'use strict';

//start point set in Gothenburg
function initMap() {
    var mapDiv = document.getElementById('map');
    var map = new google.maps.Map(mapDiv, {
        center: {lat: 57.708, lng: 11.974},
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
  }
