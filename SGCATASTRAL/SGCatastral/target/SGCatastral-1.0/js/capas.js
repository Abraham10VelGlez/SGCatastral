    
 $(document).ready(function () {
                $("#toggleStrView").click(function () {
                    $(".pano").toggle(1000);
                });
            });
var panorama;

            function initialize() {
                var origen = {lat: 19.2852, lng: -99.6568};

                panorama = new google.maps.StreetViewPanorama(
                        document.getElementById('pano'), {
                    position: origen,
                    addressControlOptions: {
                        position: google.maps.ControlPosition.BOTTOM_RIGHT
                    },
                    pov: {
                        heading: 34,
                        pitch: 10
                    }


                });
            }


            var estado = new ol.layer.Image({
                title: 'Nomenclator',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.ImageWMS({
                    url: 'http://ccg.edomex.gob.mx/cgi-bin/mapserv?map=/var/www/html/viSeig/map/ViSEIG.map',
                    params: {
                        'LAYERS': 'nomenclator',
                        'CRS': 'EPSG:900913',
                        'BBOX': '-11375159.94 1841290.99 -10910070.10 2306976.68',
                        'WIDTH': '800',
                        'HEIGHT': '500',
                        'FORMAT': 'image/png'
                    },
                    serverType: 'mapserver'
                })
            });

            var raster = new ol.layer.Image({
                title: 'Municipios',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                //extent: [-11200175.3426, 2080557.56274, -10975649.0411, 2307467.3914],
                source: new ol.source.ImageWMS({
                    url: 'http://ccg.edomex.gob.mx/cgi-bin/mapserv?map=/var/www/html/viSeig/map/ViSEIG.map',
                    // url: 'http://localhost/cgi-bin/mapserv.exe?map=/ms4w/Apache/htdocs/pruebasol3/prueba3.map',
                    params: {
                        'LAYERS': 'municipios',
                        'CRS': 'EPSG:900913',
                        //'BBOX': '-11200175.3426 2080557.56274 -10975649.0411 2307467.3914',
                        'BBOX': '-11375159.94 1841290.99 -10910070.10 2306976.68',
                        'WIDTH': '800',
                        'HEIGHT': '500',
                        'FORMAT': 'image/png'
                    },
                    serverType: 'mapserver'
                })
            });

            var estado1 = new ol.layer.Image({
                title: 'Elevaciones',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.ImageWMS({
                    url: 'http://ccg.edomex.gob.mx/cgi-bin/mapserv?map=/var/www/html/viSeig/map/ViSEIG.map',
                    params: {
                        'LAYERS': 'elevaciones',
                        'CRS': 'EPSG:900913',
                        'BBOX': '-11375159.94 1841290.99 -10910070.10 2306976.68',
                        'WIDTH': '800',
                        'HEIGHT': '500',
                        'FORMAT': 'image/png'
                    },
                    serverType: 'mapserver'
                })
            });

            var estado2 = new ol.layer.Image({
                title: 'Aeropuertos',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.ImageWMS({
                    url: 'http://ccg.edomex.gob.mx/cgi-bin/mapserv?map=/var/www/html/viSeig/map/ViSEIG.map',
                    params: {
                        'LAYERS': 'aeropuertos',
                        'CRS': 'EPSG:900913',
                        'BBOX': '-11375159.94 1841290.99 -10910070.10 2306976.68',
                        'WIDTH': '800',
                        'HEIGHT': '500',
                        'FORMAT': 'image/png'
                    },
                    serverType: 'mapserver'
                })
            });

            var estado3 = new ol.layer.Image({
                title: 'Humedad del suelo',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.ImageWMS({
                    url: 'http://ccg.edomex.gob.mx/cgi-bin/mapserv?map=/var/www/html/viSeig/map/ViSEIG.map',
                    params: {
                        'LAYERS': 'humedad_del_suelo',
                        'CRS': 'EPSG:900913',
                        'BBOX': '-11375159.94 1841290.99 -10910070.10 2306976.68',
                        'WIDTH': '800',
                        'HEIGHT': '500',
                        'FORMAT': 'image/png'
                    },
                    serverType: 'mapserver'
                })
            });
			
			var establecimientos = new ol.layer.Tile({
                title: 'Porcentaje de establecimientos',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Porcentajes de establecimientos comerciales 2015'},
                    serverType: 'geoserver'
                })
            });
			
				var hospedaje = new ol.layer.Tile({
                title: 'Establecimientos de Hospedaje',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Turismo:Establecimientos de hospedaje 2015'},
                    serverType: 'geoserver'
                })
            });
			
				var habitaciones = new ol.layer.Tile({
                title: 'Numero de Habitaciones',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Numero de Habitaciones 2015'},
                    serverType: 'geoserver'
                })
            });
			
				var agenciasmp = new ol.layer.Tile({
                title: 'Agencias del Ministerio Publico',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Seguridad:Agencias del Ministerio Publico 2015'},
                    serverType: 'geoserver'
                })
            });
			
			var pob_econ_activa = new ol.layer.Tile({
                title: 'Poblacion Economicamente Activa',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Economico:Poblacion Economicamente Activa'},
                    serverType: 'geoserver'
                })
            });
			
				var pob_ocupada = new ol.layer.Tile({
                title: 'Poblacion Ocupada',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Economico:Poblacion Ocupada 2015'},
                    serverType: 'geoserver'
                })
            });
			
			var porcentaje_pib = new ol.layer.Tile({
                title: 'Porcentaje del PIB',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Economico:Porcentaje de PIB'},
                    serverType: 'geoserver'
                })
            });
			
				var sup_sembradas = new ol.layer.Tile({
                title: 'Superficies Sembradas',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Agropecuario:Porcentaje de Superficies Sembradas 2015'},
                    serverType: 'geoserver'
                })
            });
			
							var sup_cosechadas = new ol.layer.Tile({
                title: 'Superficies Cosechadas',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Agropecuario:Porcentaje de Superficies Cosechadas 2015'},
                    serverType: 'geoserver'
                })
            });
			
						var areas_homogeneas = new ol.layer.Tile({
                title: 'Areas homogeneas',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Areas homogeneas del Estado de Mexico'},
                    serverType: 'geoserver'
                })
            });
			
			var p_ventas_electricas = new ol.layer.Tile({
                title: 'Porcentaje de volumen ventas electricas 2014-2015',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Porcentaje de volumen ventas electricas 2014-2015'},
                    serverType: 'geoserver'
                })
            });
			
				var agenciassp = new ol.layer.Tile({
                title: 'Agencias de Seguridad Publica 2015',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Seguridad:Agencias de Seguridad Publica 2015'},
                    serverType: 'geoserver'
                })
            });
			
			     var wmsSource = new ol.source.TileWMS({
  url: 'http://192.168.15.58:7070/geoserver/wms',
  params: {'LAYERS': 'Porcentaje de inversion publica 2015'},
  serverType: 'geoserver'
});
			
			var inversionPublica = new ol.layer.Tile({
                title: 'Porcentaje de inversion publica 2015',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: wmsSource /*new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Porcentaje de inversion publica 2015'},
                    serverType: 'geoserver'
                })*/
            });
			//añadida
			var cartageo = new ol.layer.Tile({
                title: 'Carta Geográfica',
                visible: true,
                extent: [-11375159.94, 1841290.99, -10910070.10, 2306976.68],
                source: new ol.source.TileWMS({
                    url: 'http://192.168.15.58:7070/geoserver/wms',
                    params: {'LAYERS': 'Carta geografica'},
                    serverType: 'geoserver'
                })
            });
			var view = new ol.View({
  projection: 'EPSG:900913',
  center: ol.proj.transform([-99.72, 19.30], 'EPSG:4326', 'EPSG:3857'),
  zoom: 9
});
			 //añadida fin
				/* Control de mouse-position*/
				var mousePositionControl = new ol.control.MousePosition({
				coordinateFormat: ol.coordinate.createStringXY(6),
				projection: 'EPSG:4326',
				// comment the following two lines to have the mouse position
				// be placed within the map.
				className: 'custom-mouse-position',
				target: document.getElementById('mouse_position'),
				undefinedHTML: '&nbsp;'
				});
				
				/* Controles de ol*/
            var map = new ol.Map({
                controls: ol.control.defaults().extend([mousePositionControl,
                    new ol.control.OverviewMap({view: new ol.View({projection: 'EPSG:900913'}), collapsed: true}),					
					new ol.control.ScaleLine({
						target: document.getElementById('esc_ac'),
					}
					),
                   /* new ol.control.Attribution({tipLabel: 'Fuente'}),*/
                    new ol.control.ZoomToExtent({extent: [-11233763.94, 2078322.99, -10960177.10, 2306976.68]})
                ]),
                target: 'map',
                view: view /*new ol.View({
                    projection: 'EPSG:900913',
                    center: ol.proj.transform([-99.72, 19.30], 'EPSG:4326', 'EPSG:3857'),
                    zoom: 9
                })*/,
                logo: 'v3.16.0-dist/igecem.png',
                layers: [/*new ol.layer.Group({
                        'title': 'Mapa base',
                        layers: [
                            new ol.layer.Tile({
                                title: 'OSM',
                                type: 'base',
                                visible: true,
                                source: new ol.source.OSM()
                            })*/new ol.layer.Group({
                        title: 'Capas de información',
                        layers: [
                            new ol.layer.Tile({
                                title: 'Carta Geográfica',
								type:'base',
                                visible: true,
                                source: new ol.source.TileWMS({
                                    url: 'http://192.168.15.58:7070/geoserver/wms',
                                    params: {'LAYERS': 'Cartografia Base'},
                                    serverType: 'geoserver'
                                })
                            }),
                            new ol.layer.Tile({
                                title: 'Satelite',
                                type: 'base',
                                visible: false,
                                source: new ol.source.MapQuest({layer: 'sat'})
                            })

                        ]
                    }),					
                    new ol.layer.Group({
                        title: 'Capas de información',
                        layers: [
                            new ol.layer.Tile({
                                title: 'Carta Geográfica',
                                visible: false,
                                source: new ol.source.TileWMS({
                                    url: 'http://192.168.15.58:7070/geoserver/wms',
                                    params: {'LAYERS': 'Carta geografica'},
                                    serverType: 'geoserver'
                                })
                            }),//agenciassp,agenciasmp,habitaciones,hospedaje,pob_ocupada,pob_econ_activa,inversionPublica,areas_homogeneas,p_ventas_electricas,sup_cosechadas,sup_sembradas,porcentaje_pib, establecimientos, estado, raster, estado1, estado2, estado3

                        ]
                    })
                ]

            });
			//Proyeccion
			/*var projectionSelect = document.getElementById('projection');
					projectionSelect.addEventListener('change', function(event) {
						mousePositionControl.setProjection(ol.proj.get(event.target.value));
					});


            //map.addLayer(estado);
           // var layerSwitcher = new ol.control.LayerSwitcher({
             //   tipLabel: 'Leyenda'
            //});
            //map.addControl(layerSwitcher);
            //layerSwitcher.showPanel();

            var numMarca = 0;
            var numCapas = map.getLayers();

            map.on('singleclick', function (evt) {
                var coordinate = evt.coordinate;
                var coordStr = ol.proj.transform(coordinate, 'EPSG:900913', 'EPSG:4326');
                coordStr = coordStr.toString();
                coordStr = coordStr.split(",");
                var coordX = parseFloat(coordStr[0].substring(0, 11));
                var coordY = parseFloat(coordStr[1].substring(0, 9));
                var lugar = {lat: coordY, lng: coordX};

                panorama.setPosition(lugar);

                var marker = new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([coordX, coordY]))
                });

                marker.setStyle(new ol.style.Style({
                    image: new ol.style.Icon(/* @type {olx.style.IconOptions} *//* ({
                        src: 'v3.16.0-dist/marca.png'
                    }))
                }));

                var vectorSource = new ol.source.Vector({
                    features: [marker]
                });

                var vectorLayer = new ol.layer.Vector({
                    source: vectorSource
                });



                if (numMarca == 0) {
                    map.addLayer(vectorLayer);
                    numMarca = 1;

                } else {
                    map.getLayers().removeAt(2);
                    map.addLayer(vectorLayer);
                }
            });*/
				

        
function mostrar(){
document.getElementById('navegador').style.display = 'block';
document.getElementById('indices').style.display = 'none';
document.getElementById('Ocultar').style.display = 'block';
document.getElementById('Mostrar').style.display = 'none';
document.getElementById('Ind_mos').style.display = 'inherit';}

function ocultar(){
document.getElementById('navegador').style.display = 'none'
document.getElementById('indices').style.display = 'none';
document.getElementById('Mostrar').style.display = 'block';
document.getElementById('Ocultar').style.display = 'none';
document.getElementById('Ind_mos').style.display = 'none';}

var value2;
var id2;
function funcion(id,value){
if (id!=id2){
switch(value2) {
    case 'agenciassp':
        	map.removeLayer(agenciassp);
			document.getElementById(id2).checked =false;
        break;
    case 'estado1':
        	map.removeLayer(estado1);
			document.getElementById(id2).checked =false;
        break;
    case 'estado2':
        	map.removeLayer(estado2);
			document.getElementById(id2).checked =false;
		break;
	case 'establecimientos':
        	map.removeLayer(establecimientos);
			document.getElementById(id2).checked =false;
        break;
    case 'estado3':
        	map.removeLayer(estado3);
			document.getElementById(id2).checked =false;
        break;
    case 'hospedaje':
        	map.removeLayer(hospedaje);
			document.getElementById(id2).checked =false;
		break;
	case 'habitaciones':
        	map.removeLayer(habitaciones);
			document.getElementById(id2).checked =false;
        break;
    case 'agenciasmp':
        	map.removeLayer(agenciasmp);
			document.getElementById(id2).checked =false;
        break;
    case 'pob_econ_activa':
        	map.removeLayer(pob_econ_activa);
			document.getElementById(id2).checked =false;
		break;
	 case 'pob_ocupada':
        	map.removeLayer(pob_ocupada);
			document.getElementById(id2).checked =false;
        break;
    case 'porcentaje_pib':
        	map.removeLayer(porcentaje_pib);
			document.getElementById(id2).checked =false;
        break;
    case 'sup_sembradas':
        	map.removeLayer(sup_sembradas);
			document.getElementById(id2).checked =false;
		break;
	case 'sup_cosechadas':
        	map.removeLayer(sup_cosechadas);
			document.getElementById(id2).checked =false;
        break;
    case 'areas_homogeneas':
        	map.removeLayer(areas_homogeneas);
			document.getElementById(id2).checked =false;
        break;
    case 'p_ventas_electricas':
        	map.removeLayer(p_ventas_electricas);
			document.getElementById(id2).checked =false;
		break;
	    case 'inversionPublica':
        	map.removeLayer(inversionPublica);
			document.getElementById(id2).checked =false;
        break;
    case 'estado':
        	map.removeLayer(estado);
			document.getElementById(id2).checked =false;
        break;
    case 'raster':
        	map.removeLayer(raster);
			document.getElementById(id2).checked =false;
		break;
	case 'cartageo':
        	map.removeLayer(cartageo);
			document.getElementById(id2).checked =false;
		break;
}
}

if(document.getElementById(id).checked){
	switch(value) {
    case 'agenciassp':
        	map.addLayer(agenciassp);
        break;
    case 'estado1':
        	map.addLayer(estado1);
        break;
    case 'estado2':
        	map.addLayer(estado2);
		break;
	case 'establecimientos':
        	map.addLayer(establecimientos);
        break;
    case 'estado3':
        	map.addLayer(estado3);
        break;
    case 'hospedaje':
        	map.addLayer(hospedaje);
		break;
	case 'habitaciones':
        	map.addLayer(habitaciones);
        break;
    case 'agenciasmp':
        	map.addLayer(agenciasmp);
        break;
    case 'pob_econ_activa':
        	map.addLayer(pob_econ_activa);
		break;
	 case 'pob_ocupada':
        	map.addLayer(pob_ocupada);
        break;
    case 'porcentaje_pib':
        	map.addLayer(porcentaje_pib);
        break;
    case 'sup_sembradas':
        	map.addLayer(sup_sembradas);
		break;
	case 'sup_cosechadas':
        	map.addLayer(sup_cosechadas);
        break;
    case 'areas_homogeneas':
        	map.addLayer(areas_homogeneas);
        break;
    case 'p_ventas_electricas':
        	map.addLayer(p_ventas_electricas);
		break;
	case 'inversionPublica':
        	map.addLayer(inversionPublica);
        break;
    case 'estado':
        	map.addLayer(estado);
        break;
    case 'raster':
        	map.addLayer(raster);
		break;
	case 'cartageo':
        	map.addLayer(cartageo);
		break;
}
document.getElementById('opcHerram').style.display='block';
document.getElementById('titulo').style.display='none';

}
else{
	switch(value) {
    case 'agenciassp':
        	map.removeLayer(agenciassp);
        break;
    case 'estado1':
        	map.removeLayer(estado1);
        break;
    case 'estado2':
        	map.removeLayer(estado2);
		break;
	case 'establecimientos':
        	map.removeLayer(establecimientos);
        break;
    case 'estado3':
        	map.removeLayer(estado3);
        break;
    case 'hospedaje':
        	map.removeLayer(hospedaje);
		break;
	case 'habitaciones':
        	map.removeLayer(habitaciones);
        break;
    case 'agenciasmp':
        	map.removeLayer(agenciasmp);
        break;
    case 'pob_econ_activa':
        	map.removeLayer(pob_econ_activa);
		break;
	 case 'pob_ocupada':
        	map.removeLayer(pob_ocupada);
        break;
    case 'porcentaje_pib':
        	map.removeLayer(porcentaje_pib);
        break;
    case 'sup_sembradas':
        	map.removeLayer(sup_sembradas);
		break;
	case 'sup_cosechadas':
        	map.removeLayer(sup_cosechadas);
        break;
    case 'areas_homogeneas':
        	map.removeLayer(areas_homogeneas);
        break;
    case 'p_ventas_electricas':
        	map.removeLayer(p_ventas_electricas);
		break;
	    case 'inversionPublica':
        	map.removeLayer(inversionPublica);
        break;
    case 'estado':
        	map.removeLayer(estado);
        break;
    case 'raster':
        	map.removeLayer(raster);
		break;
	case 'cartageo':
        	map.removeLayer(cartageo);
		break;
}
document.getElementById('opcHerram').style.display='none';
document.getElementById('titulo').style.display='block';
}   
value2= value;
id2=id;
}

var id_divant;
var contadiv=0;
function despliegadiv(id_selec){
var id_selec2=id_selec;
if (id_selec2!=id_divant){
document.getElementById(id_selec2).style.display = 'block';
document.getElementById('menuLY2').style.display='none';
document.getElementById('menuMB2').style.display='none';
if (contadiv>0){
document.getElementById(id_divant).style.display = 'none';}
}
contadiv++;
id_divant=id_selec2;
}
 
map.on('singleclick', function(evt) {
  document.getElementById('modal-content').innerHTML = '';
  var viewResolution = /** @type {number} */ (view.getResolution());
  var url = wmsSource.getGetFeatureInfoUrl(
      evt.coordinate, viewResolution, 'EPSG:3857',
      {'INFO_FORMAT': 'text/html','PropertyName':'num_reg,nom_reg,total,p_inver'});
  if (url) {
    document.getElementById('modal-content').innerHTML =
        '<iframe seamless style="width:100%; height:100%;" src="' + url + '"></iframe>';
  }
  document.getElementById('modal').style.display="block";
});

var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function mostrardivs2(clase2){
var clase=clase2;
	switch (clase){
	case 'capass':
		document.getElementById(id_divant).style.display='block';
		document.getElementById('menuLY2').style.display='none';
		document.getElementById('menuMB2').style.display='none';
		break;
	case 'leyendas':
		document.getElementById(id_divant).style.display='none';
		document.getElementById('menuLY2').style.display='block';
		document.getElementById('menuMB2').style.display='none';
		break;
	case 'bases':
		document.getElementById(id_divant).style.display='none';
		document.getElementById('menuLY2').style.display='none';
		document.getElementById('menuMB2').style.display='block';
		break;
		
}
}

	  