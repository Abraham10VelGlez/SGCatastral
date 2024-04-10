var MERCATOR_RANGE = 256;
var projection = new MercatorProjection();
var ProjectionXYZ = new OpenLayers.Projection("EPSG:4326");
var ProjectionWMS = new OpenLayers.Projection("EPSG:900913");

function bound(value, opt_min, opt_max) {
    if (opt_min != null)
        value = Math.max(value, opt_min);
    if (opt_max != null)
        value = Math.min(value, opt_max);
    return value;
}

function degreesToRadians(deg) {
    return deg * (Math.PI / 180);
}

function radiansToDegrees(rad) {
    return rad / (Math.PI / 180);
}

function CoordMapType(tileSize) {
    this.tileSize = tileSize;
}


function MercatorProjection() {
    this.pixelOrigin_ = new google.maps.Point(MERCATOR_RANGE / 2, MERCATOR_RANGE / 2);
    this.pixelsPerLonDegree_ = MERCATOR_RANGE / 360;
    this.pixelsPerLonRadian_ = MERCATOR_RANGE / (2 * Math.PI);
}

MercatorProjection.prototype.fromLatLngToPoint = function(latLng, opt_point) {
    var me = this;

    var point = opt_point || new google.maps.Point(0, 0);

    var origin = me.pixelOrigin_;
    point.x = origin.x + latLng.lng() * me.pixelsPerLonDegree_;

    var siny = bound(Math.sin(degreesToRadians(latLng.lat())), -0.9999, 0.9999);
    point.y = origin.y + 0.5 * Math.log((1 + siny) / (1 - siny)) * -me.pixelsPerLonRadian_;

    return point;
};


MercatorProjection.prototype.fromPointToLatLng = function(point) {
    var me = this;

    var origin = me.pixelOrigin_;
    var lng = (point.x - origin.x) / me.pixelsPerLonDegree_;
    var latRadians = (point.y - origin.y) / -me.pixelsPerLonRadian_;
    var lat = radiansToDegrees(2 * Math.atan(Math.exp(latRadians)) - Math.PI / 2);
    return new google.maps.LatLng(lat, lng);
};

function GPSControl(controlDiv, map) {

  var controlUI = document.createElement('div');
  controlUI.style.backgroundColor = '#fff';
  controlUI.style.border = '2px solid #fff';
  controlUI.style.borderRadius = '3px';
  controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
  controlUI.style.cursor = 'pointer';
  controlUI.style.display = 'block';  
  controlUI.style.marginLeft = '13px';
  controlUI.style.marginRight = '13px';    
  controlUI.style.textAlign = 'center';
  controlUI.title = 'Mi ubicación';
  controlDiv.appendChild(controlUI);
  
  var controlImage = document.createElement('div');
  controlImage.style.color = 'rgb(25,25,25)';
  controlImage.style.backgroundImage = "url(../images/my_location.png)";
  controlImage.style.height = '18px';
  controlImage.style.width = '18px';  
  controlUI.appendChild(controlImage);
    
  // Setup the click event listeners:
  controlUI.addEventListener('click', function() {
      
    var infoWindow = new google.maps.InfoWindow({map: map});
    
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            infoWindow.setPosition(pos);
            infoWindow.setContent('Estoy aquí');
            map.setCenter(pos);
            map.setZoom(15);
        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());
    }        
  });

}

function StreetViewControl(controlDiv, map) {

  var controlUI = document.createElement('div');
  controlUI.style.backgroundColor = '#fff';
  controlUI.style.border = '2px solid #fff';
  controlUI.style.borderRadius = '3px';
  controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
  controlUI.style.cursor = 'pointer';
  controlUI.style.display = 'block';  
  controlUI.style.marginLeft = '23px';
  controlUI.style.marginRight = '23px';    
  controlUI.style.textAlign = 'center';
  controlUI.title = 'Cerrar';
  controlDiv.appendChild(controlUI);
  
  var controlImage = document.createElement('div');
  controlImage.style.color = 'rgb(25,25,25)';
  controlImage.style.backgroundImage = "url(../images/close_click.png)";
  controlImage.style.height = '18px';
  controlImage.style.width = '18px';  
  controlUI.appendChild(controlImage);
    
  // Setup the click event listeners:
  controlUI.addEventListener('click', function() {
      
    var panorama = map.getStreetView();
    if(panorama){
        panorama.setVisible(false)
    }
    
  });

}

function Map(divMap, lat, lon, zoom) {            
        
    this.mapOptions = {
        zoom: zoom,
        //mapTypeId: google.maps.MapTypeId.HYBRID,
        center: new google.maps.LatLng(lat, lon),        
        mapTypeControlOptions: {            
            position: google.maps.ControlPosition.LEFT_BOTTOM
        },
        zoomControlOptions: {
            position: google.maps.ControlPosition.LEFT_CENTER
        },
        streetViewControlOptions: {
            position: google.maps.ControlPosition.LEFT_CENTER
        }
    };        
    
    this.map = new google.maps.Map(document.getElementById(divMap), this.mapOptions);
    this.zoom = zoom;
    this.lat = lat;
    this.lon = lon;
    this.div = divMap;
    this.layers = [];
    this.configLayerIWnd = [];
    
    var panorama = this.map.getStreetView();
    panorama.setOptions({
        fullscreenControl: false
    });
    
    var gpsControlDiv = document.createElement('div');
    var gpsControl = new GPSControl(gpsControlDiv, this.map);        

    gpsControlDiv.index = 1;    
    
    var streetViewControlDiv = document.createElement('div');
    var streetViewControl = new StreetViewControl(streetViewControlDiv, this.map);        

    streetViewControlDiv.index = 1;    
    
    this.map.controls[google.maps.ControlPosition.LEFT_CENTER].push(gpsControlDiv);  
    panorama.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(streetViewControlDiv);  
    
}
var cve_cat=" ";
Map.prototype.getMapInfoContentByLayer = function (jsonInfo) {
    var content = "<div style='max-height:200px; max-width:400px;'>";        
    if(jsonInfo.properties != null) {
        var configIWnd = ObjMap.getConfigCveLayerIWnd(jsonInfo.id.split(".")[0]);
        if(configIWnd != null) {
            content+="<h3>" + configIWnd.titulo + "</h3>";
            $.each(configIWnd.infownd , function(key, item) {
                key=key.split('-');
                //if (item=="Clave"){
                  // cve_cat=jsonInfo.properties[key[0]]; 
                   //alert(cve_cat);
                //}
                if(jsonInfo.properties[key[0]] != null) {
                    if (key[1]==null){
                    content+="<p><strong>" + item + ":  </strong>" + jsonInfo.properties[key[0]] +'</p>';}
                else{
                    var clase=key[1];//agregar
                    clase=clase.replace(/ /g,"_"); //agregar
                    content+="<p id='medida' class="+clase+"><strong>" + item + ":  </strong>" + jsonInfo.properties[key[0]] +" "+key[1]+ '</p>'; //modificar
                }
                }
            });
        } else {           
          content+="<p><strong>No fue posible recueprar la información</p>";                        
        }         
    } else {            
       content+="<p><strong>No existe información disponible</p>";
    }
    content+="<a id='descripcion' onclick='descripcion(" + configIWnd.id + ")' style='float:right;' href='#'>Ver Descripción...</a>";
   content+="</div>";  
   content+="<div id='info' style='display: none; height:250px;'>";
   content+='<div class="w3-bar w3-border-bottom"><button class="tablink w3-bar-item w3-button" onclick="Mostdesc()">Descripcion</button><button id="estadis" class="tablink w3-bar-item w3-button" onclick="Mostest()">Estadística Municipal</button><button id="grafico" class="tablink w3-bar-item w3-button" onclick="Mostgra()">Gráficos</button><button class="tablink w3-bar-item w3-button" onclick="Mostley()">Leyenda</button></div>';
    content+='<div id="London" style="display: none; max-width:400px; font-family:Roboto,Arial,sans-serif;" class="w3-container city"></div>';
    content+='<div id="Paris" style="display: none;" class="w3-container city"></div>';
    content+='<div id="Grafic" style="display: none;" class="w3-container city"><div id="grafico2" style="max-width: 310px; height: 400px; margin: 0 auto"></div><div id="grafico3" style="max-width: 310px; height: 400px; margin: 0 auto"></div></div>';
    content+='<div id="tabs-4" class="controlCapa" style="display:none;"><div class="contenedorCapa" ><h3>Leyenda de Mapa</h3><div id="leyenda"></div></div></div>';
     content+="</div>"
      content+='<div id="fuente" style="display:none; width:100%;font-weight: bold; position:relative; padding:0; max-width:400px;"> </div>';
   return content;
}


Map.prototype.getMapInfo = function(jsonInfo,latLng) {
    
    if(ObjMap.layers.length == 0) return;
    
    if (ObjMap.infowindow == null) {
        ObjMap.infowindow = new google.maps.InfoWindow();
    }
	
    ObjMap.infowindow.open(ObjMap.map);
    ObjMap.infowindow.setContent(ObjMap.getMapInfoContentByLayer(jsonInfo));
    ObjMap.infowindow.setPosition(latLng);
}

Map.prototype.getLayers = function() {
    var layers = new Array();
    this.layers.forEach(function(val, index, theArray) {
        layers.push(val.name);
    });
    return layers.toString();
}


Map.prototype.getQueryLayers = function() {
    var layers = new Array();
    this.layers.forEach(function(val, index, theArray) {
        layers.push(val.name);
    });
    return layers.reverse().toString();
}


Map.prototype.getStyleLayers = function() {
    if(this.layers.length == 0) return "";
    var styleLayers = new Array();
    this.layers.forEach(function(val, index, theArray) {
        styleLayers.push(val.style);
    });
    return styleLayers.toString();
}

Map.prototype.getFilterLayers = function() {
    var filterLayers = new Array();
    this.layers.forEach(function(val, index, theArray) {		
        filterLayers.push(val.filter.replace(/,/g,"<>"));
    });
	
    var filter = filterLayers.toString().replace(/,/g,";")
    filter = filter.replace(/<>/g,",");	
    return filter;
}


Map.prototype.setFilterAllLayers = function(filter) {
    $.each(this.layers,function(key,layer) {
       layer.filter = filter; 
    });    
}


Map.prototype.setFilterLayer = function(id, filter) {
    $.each(this.layers,function(key,layer) {
        if(layer.id == id) {
            layer.filter = filter; 
        }
    });    
}



Map.prototype.getQueryFilterLayers = function() {
    var filterLayers = new Array();
    this.layers.forEach(function(val, index, theArray) {		
        filterLayers.push(val.filter.replace(/,/g,"<>"));
    });
	
    var filter = filterLayers.reverse().toString().replace(/,/g,";")
    filter = filter.replace(/<>/g,",");	
    return filter;
}


Map.prototype.findLayer = function(id) {
    var resultado = null;
    this.layers.forEach(function(val, index, theArray) {	
	var vId = val.id.toString();
	var idLyr = vId.split("_");
        if (idLyr[0] == id) {
            resultado = val.name;
            return resultado;
        }
    });
    return resultado;
}


Map.prototype.addlayer = function(idLyr, name, style, filter) {
    if (this.findLayer(idLyr) != null)
        return;
    
    this.layers.push({
        id: idLyr,
        name: name,
        style: style,
        filter: filter
    });
}



Map.prototype.deleteLayer = function(id) {
    var resultado = false;
    this.layers.forEach(function(val, index, theArray) {
	if (val.id == id) {
            ObjMap.layers.splice(index,1)
            resultado = true;
            return resultado;
	}
    });
    return resultado;
}


Map.prototype.getTileUrl = function(coord, zoom) {
    
    if(ObjMap.layers.length == 0) return;
    
    var p1x = (coord.x) * 256;
    var p1y = (coord.y) * 256;
    var p2x = (coord.x + 1) * 256;
    var p2y = (coord.y + 1) * 256;
    var mypoint1 = projection.fromPointToLatLng(new google.maps.Point(p1x / Math.pow(2, this.map.getZoom()), p1y / Math.pow(2, this.map.getZoom())));
    var mypoint22 = projection.fromPointToLatLng(new google.maps.Point(p2x / Math.pow(2, this.map.getZoom()), p2y / Math.pow(2, this.map.getZoom())));
    var apoint1 = new OpenLayers.LonLat(mypoint1.lng(), mypoint1.lat());
    var apoint2 = new OpenLayers.LonLat(mypoint22.lng(), mypoint22.lat());
    wmspoint1 = apoint1.transform(ProjectionXYZ, ProjectionWMS);
    wmspoint2 = apoint2.transform(ProjectionXYZ, ProjectionWMS);

    var layers = ObjMap.getLayers(); 
    var styles = ObjMap.getStyleLayers();
    var filters = ObjMap.getFilterLayers(); 

    var auxURL = WmsMap + "LAYERS=" + layers 
			+ "&bbox=" + wmspoint1.lon + "," + wmspoint2.lat + "," + wmspoint2.lon + "," + wmspoint1.lat 
			+ "&x=" + this.map.getBounds().getSouthWest().lng() + "&y=" + this.map.getBounds().getSouthWest().lat()
			+ "&cql_filter=" + filters + "&styles=" + styles;
    return auxURL;
}

Map.prototype.resetLayers = function() {    
    this.configLayerIWnd=[];
    this.layers = [];
}


Map.prototype.findConfigIdLayerIWnd = function(idLayer) {
    var resultado = false;
    $.each(this.configLayerIWnd, function(idx, item)  {    
        if(item.id == idLayer) {
            resultado = true;
            return resultado;
        }        
    });    
    return resultado;
}


Map.prototype.findConfigCveLayerIWnd = function(cveLayer) {
    var resultado = false;
    $.each(this.configLayerIWnd, function(idx, item)  {    
        if(item.clave == cveLayer) {
            resultado = true;
            return resultado;
        }        
    });    
    return resultado;
}


Map.prototype.getConfigCveLayerIWnd = function(cveLayer) {
    var resultado = null;
    $.each(this.configLayerIWnd, function(idx, item)  {    
        if(item.clave == cveLayer) {
            resultado = item;
            return resultado;
        }        
    });
    return resultado;
}


Map.prototype.addConfigLayerIWnd = function(configLayer) {    
    if(!this.findConfigIdLayerIWnd(configLayer.id)) {
        this.configLayerIWnd.push({
            id: configLayer.id,
            titulo: configLayer.capa,
            clave: configLayer.alias,
            infownd: configLayer.infownd
        });
    }        
}
