function Altitud() {
    google.maps.event.clearListeners(ObjMap.map, 'click');
    //agregarWmsMapInfo();
    var elevator = new google.maps.ElevationService;
    var infowindow = new google.maps.InfoWindow({map: ObjMap.map});

    // Add a listener for the click event. Display the elevation for the LatLng of
    // the click inside the infowindow.
    ObjMap.map.addListener('click', function (event) {
        displayLocationElevation(event.latLng, elevator, infowindow);
    });
}

function displayLocationElevation(location, elevator, infowindow) {
    // Initiate the location request
    elevator.getElevationForLocations({
        'locations': [location]
    }, function (results, status) {
        infowindow.setPosition(location);
        if (status === google.maps.ElevationStatus.OK) {
            // Retrieve the first result
            if (results[0]) {
                // Open the infowindow indicating the elevation at the clicked position.
                infowindow.setContent('La elevación en este punto <br>es ' +
                        results[0].elevation + ' msnm.');
            } else {
                infowindow.setContent('No se encontraron resultados');
            }
        } else {
            infowindow.setContent('Falló el servicio de elevación: ' + status);
        }
    });
    document.getElementById("altit").style.display = 'inline-block';
        document.getElementById("altit").addEventListener("click", function () {
            infowindow.close();
        detenera();
        

    });
    Altitud()
}

var geocoder = null;
var image = "../img/marcador1.png";
function Geocod() {
    google.maps.event.clearListeners(ObjMap.map, 'click');
    //agregarWmsMapInfo();
    document.getElementById("geoc").style.display = 'inline-block';
    geocoder = new google.maps.Geocoder();

    ObjMap.map.addListener('click', function (event) {
        cleanMark()//Eliminar un marcador cuando se inicia otro
        var marker = new google.maps.Marker({
            position: event.latLng,
            map: ObjMap.map,
            animation: google.maps.Animation.DROP, //BOUNCE
            icon: image
        });
        markers.push(marker);
        geocoder.geocode({latLng: event.latLng}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var infowindow = new google.maps.InfoWindow({
                    content: results[0].formatted_address
                });
                infowindow.open(ObjMap.map, marker);
            }
        });
    })

}

function cleanMark() {
    clearMarkers();
    markers = [];
}

function clearMarkers() {
    setMapOnAll(null);
}

function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

var start;
var end;
var renderer = null;

function Ruteo(opcion) {
    //marker=null;

    google.maps.event.clearListeners(ObjMap.map, 'click');

    var directionsDisplay = new google.maps.DirectionsRenderer;
    var directionsService = new google.maps.DirectionsService;

    renderer = new google.maps.DirectionsRenderer();
    renderer.setMap(ObjMap.map);
    renderer.setPanel(document.getElementById("panel"));

    ObjMap.map.addListener("click", function (event) {
        if (start == null) {
            start = event.latLng;
            marker = new google.maps.Marker({
                position: event.latLng,
                map: ObjMap.map,
                icon: image
            });
        } else {
            marker.setMap(null);
            //setMapOnAll(null);
            end = event.latLng;
            directionsDisplay.setMap(ObjMap.map);
            calculateAndDisplayRoute(directionsService, directionsDisplay, opcion);
            start = null;
            renderObjects.push(renderer);
            document.getElementById("cleanR").style.display = 'inline-block';

        }
    })

}

function calculateAndDisplayRoute(directionsService, directionsDisplay, opcion) {

    var selectedMode = opcion;

    directionsService.route({
        origin: start,
        destination: end,
        travelMode: google.maps.TravelMode[selectedMode]
    }, function (response, status) {
        if (status == 'OK') {
            directionsDisplay.setDirections(response);
            renderer.setDirections(response);
        } else {
            window.alert('Fallo debido a: ' + status);
        }
    });
    document.getElementById("cleanR").addEventListener("click", function () {
        document.getElementById("panel").innerHTML = "";
        directionsDisplay.setMap(null);
        clear();
        agregarWmsMapInfo();
        //document.getElementById("panel").innerHTML="";

    });
    //agregarWmsMapInfo()
}

var renderObjects = [];

function clearRenderObjects() {
    for (var i in renderObjects) {
        renderObjects[i].setMap(null);
    }
}

function clear() {
    // clear previous
    clearRenderObjects();

    var directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(ObjMap.map);
    // add to the array
    renderObjects.push(directionsRenderer);
    google.maps.event.clearListeners(ObjMap.map, 'click');
    agregarWmsMapInfo();
    document.getElementById("cleanR").style.display = 'none';
    //home();

}

function detener() {
    google.maps.event.clearListeners(ObjMap.map, 'click');
    agregarWmsMapInfo();
    document.getElementById("geoc").style.display = 'none';
}

function detenera() {
    google.maps.event.clearListeners(ObjMap.map, 'click');
    agregarWmsMapInfo();
    document.getElementById("altit").style.display = 'none';
}