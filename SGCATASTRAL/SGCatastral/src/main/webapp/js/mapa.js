
$(document).ready(function () {

    $('#frmAddPoint').submit(function (e) {

        if (($('#plng').val() == null || $('#plng').val().trim() == "") && ($('#plat').val() == null || $('#plat').val().trim() == "")) {
            $("#resultado").html("Información requerida<br>Por favor coloque un marcador en el mapa");
            $("#resultado").attr("class", "");
            $("#resultado").addClass("alert alert-danger");
            return false;
        }

        if ($('#imgRecurso1').val() == null || $('#imgRecurso1').val().trim() == "") {
            $("#resultado").html("Imagen requerida<br>Por favor carque una imagen");
            $("#resultado").attr("class", "");
            $("#resultado").addClass("alert alert-danger");
            return false;
        }

        $("#loaderP").show();
        e.preventDefault();

        $.ajax({
            contentType: false,
            processData: false,
            type: "post",
            cache: false,
            url: "addPoint.do",
            dataType: 'json',
            data: new FormData($(this)[0]),
            success: function (callback) {
                $("#resultado").attr("class", "");
                setTimeout(function () {
                    usrPunto.setIcon({url: '../images/REPOSITORIO_MAGIC/' + callback.contenido, scaledSize: new google.maps.Size(25, 25)});
                    $("#loaderP").hide();
                    $("#resultado").html(callback.mensaje);
                    if (callback.codigo == 200) {
                        $("#resultado").addClass("alert alert-success");
                    } else {
                        $("#resultado").addClass("alert alert-danger");
                    }
                }, 2000);

            },
            error: function () {
                $("#resultado").html("Error de servidor, no fue posible guardar el punto");
                $("#resultado").attr("class", "");
                $("#resultado").addClass("alert alert-danger");
                $("#loaderP").hide();
            }
        });

    });

    $(".imgPreview").change(function () {
        uploadFile(this);
    });

    $('#frmExtraerCapas').submit(function (e) {

        e.preventDefault();

        $("#loaderExtractor").show();
        $.ajax({
            contentType: false,
            processData: false,
            type: "post",
            cache: false,
            url: "extraerCapas.do",
            dataType: 'json',
            data: new FormData($(this)[0]),
            success: function (callback) {
                $("#resultadoBuff").attr("class", "");
                $("#resultadoBuff").html(callback.mensaje);
                if (callback.codigo == 200) {
                    $("#resultadoBuff").addClass("alert alert-success");
                    descargarDocumento();
                } else {
                    $("#resultadoBuff").addClass("alert alert-danger");
                }
                //$("#loaderExtractor").hide();
            },
            error: function () {
                $("#resultadoBuff").html("Error de servidor, no fue posible extraer los datos");
                $("#resultadoBuff").attr("class", "");
                $("#resultadoBuff").addClass("alert alert-danger");
                $("#loaderExtractor").hide();
            }
        });

    });

});



/////////////////////////////////////////////////////////////////////////////////
//Cargar imagen
function uploadFile(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#' + $(input).attr('preview')).attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);

    }
}

//Guardar puntos del usuario
////////////////////////////////////////////////////////////////////////////////

function setOpacity(opacity) {
    opacityNS = opacity / 100;
    if (ObjMap.layers.length > 0) {
        ObjMap.overlayMapNS.setOpacity(opacityNS);
    }
}

function mostrarControlMapa() {
    var effect = 'slide';
    var options = {direction: 'rigth'};
    var duration = 600;
    var flg = false;
    if ($("#closePanelMapa").css("display") != "none") {
        $("#closePanelMapa").hide();
        flg = true;
    }

    $("#panelMapa").toggle(effect, options, duration);

    setTimeout(function () {
        if (!flg && $("#closePanelMapa").css("display") == "none") {
            $("#closePanelMapa").toggle(effect, options, 200);
        }
    }, duration + 50);
}


function inicializarMapa(divMap) {
    ObjMap = new Map(divMap, iniLatitud, iniLongitud, iniZoom);
    setDrawingManager();
    agregarWmsMapas();
    agregarWmsMapInfo();
    agregaBusquedaMapas();
    agregarBusquedaFiltros();
}

function inicializarMapa(divMap, initMap) {
    if (initMap.lng != null) {
        iniLongitud = parseFloat(initMap.lng);
    }
    if (initMap.lat != null) {
        iniLatitud = parseFloat(initMap.lat);
    }
    if (initMap.zoom != null) {
        iniZoom = parseInt(initMap.zoom);
    }

    ObjMap = new Map(divMap, iniLatitud, iniLongitud, iniZoom);
    setDrawingManager();
    agregarWmsMapas();
    agregarWmsMapInfo();
    agregaBusquedaMapas();
    agregarBusquedaFiltros();
}

function agregaBusquedaMapas() {
    geocoder = new google.maps.Geocoder();
    var search = document.getElementById("search_address");
    //var autocomplete = new google.maps.places.Autocomplete(search);
    searchPlace(search);
}


/*function agregarWmsMapas() {
 obtenerCapas();
 obtenerEspacios();
 ObjMap.overlayMapNS = new google.maps.ImageMapType({
 getTileUrl: function (coord, zoom) {
 return ObjMap.getTileUrl(coord, zoom);
 },
 tileSize: new google.maps.Size(256, 256),
 opacity: opacityNS,
 isPng: true
 });
 
 ObjMap.map.overlayMapTypes.setAt(0, ObjMap.overlayMapNS);
 }*/

function agregarWmsMapas() {
    obtenerCapasSgc();
    obtenerEspacios();
    ObjMap.overlayMapNS = new google.maps.ImageMapType({
        getTileUrl: function (coord, zoom) {
            return ObjMap.getTileUrl(coord, zoom);
        },
        tileSize: new google.maps.Size(256, 256),
        opacity: opacityNS,
        isPng: true
    });

    ObjMap.map.overlayMapTypes.setAt(0, ObjMap.overlayMapNS);
}

function agregarWmsMapInfo() {

    google.maps.event.addListener(ObjMap.map, 'click', function (evt) {
        if (ObjMap.layers.length == 0) {
            return;
        }
        var numTiles = 1 << ObjMap.map.getZoom();
        var projection = new MercatorProjection();
        var worldCoordinate = projection.fromLatLngToPoint(evt.latLng);
        var pixelCoordinate = new google.maps.Point(worldCoordinate.x * numTiles, worldCoordinate.y * numTiles);
        var tileCoordinate = new google.maps.Point(Math.floor(pixelCoordinate.x / 256), Math.floor(pixelCoordinate.y / 256));
        var x = Math.floor(pixelCoordinate.x) - (tileCoordinate.x * 256);
        var y = Math.floor(pixelCoordinate.y) - (tileCoordinate.y * 256);
        var p1x = (tileCoordinate.x) * 256;
        var p1y = (tileCoordinate.y) * 256;
        var p2x = (tileCoordinate.x + 1) * 256;
        var p2y = (tileCoordinate.y + 1) * 256;
        var mypoint1 = projection.fromPointToLatLng(new google.maps.Point(p1x / Math.pow(2, ObjMap.map.getZoom()), p1y / Math.pow(2, ObjMap.map.getZoom())));
        var mypoint22 = projection.fromPointToLatLng(new google.maps.Point(p2x / Math.pow(2, ObjMap.map.getZoom()), p2y / Math.pow(2, ObjMap.map.getZoom())));
        var apoint1 = new OpenLayers.LonLat(mypoint1.lng(), mypoint1.lat());
        var apoint2 = new OpenLayers.LonLat(mypoint22.lng(), mypoint22.lat());
        var wmspoint1 = apoint1.transform(ProjectionXYZ, ProjectionWMS);
        var wmspoint2 = apoint2.transform(ProjectionXYZ, ProjectionWMS);

        $.getJSON(WmsMapInfo, {LAYERS: encodeURIComponent(ObjMap.getQueryLayers()), bbox: wmspoint1.lon + "," + wmspoint2.lat + "," + wmspoint2.lon + "," + wmspoint1.lat,
            INFO_FORMAT: encodeURIComponent("application/json"), FEATURE_COUNT: 1, QUERY_LAYERS: encodeURIComponent(ObjMap.getQueryLayers()),
            x: x, y: y, buffer: 10, cql_filter: encodeURIComponent(ObjMap.getQueryFilterLayers()),
            propertyName: ""}, function (data) {
            data.features.forEach(function (val, index, array) {
                ObjMap.getMapInfo(val, evt.latLng);
            });
        }).fail(function () {
            var error = $.parseJSON('{"error": false}');
            alert("No fue posible recuperar la informaci??n del mapa");
        });
    });
}

function agregarBusquedaFiltros() {
    obtenerMunicipios();
    activarInicio(205);
    var zoom;

    $("#selMunicipios").on("change", function (e) {
        zoom = 9;
        $("#selLocalidades").attr("disabled", "disabled");
        var selMpio = $("#selMunicipios option:selected").val().split("|");
        if (selMpio[0] != 0) {
            obtenerLocalidades(selMpio);
            zoom = 13;
        }
        ObjMap.map.setCenter(new google.maps.LatLng(parseFloat(selMpio[1]), parseFloat(selMpio[2])));
        ObjMap.map.setZoom(zoom);
    });

    $("#selLocalidades").on("change", function (e) {
        var selLocalidad = $("#selLocalidades option:selected").val().split("|");
        ObjMap.map.setCenter(new google.maps.LatLng(parseFloat(selLocalidad[1]), parseFloat(selLocalidad[2])));
        ObjMap.map.setZoom(17);
    });

    //Cambiar ObjMap.setFilterAllLayers("EDO_CVEINE=" + sel.split('|')[0]); con el campo correspondiente para el filtro
    $("#filtrarCapas").on("click", function (e) {
        if ($('#filtrarCapas').is(':checked')) {
            var sel = $("#selEstados").val();
            if (sel != null || sel.split("|")[0] != "0") {
                ObjMap.setFilterAllLayers("EDO_CVEINE=" + sel.split('|')[0]);
            }
        } else {
            ObjMap.setFilterAllLayers("INCLUDE");
        }

        var zoom = ObjMap.map.getZoom();
        ObjMap.map.setZoom(zoom + 1);
        ObjMap.map.setZoom(zoom);
    });

}

function validarFiltroCapas() {
    if ($('#filtrarCapas').is(':checked')) {
        var sel = $("#selEstados").val();
        if (sel != null || sel.split("|")[0] != "0") {
            ObjMap.setFilterAllLayers("EDO_CVEINE=" + sel.split('|')[0]);
        }
    } else {
        ObjMap.setFilterAllLayers("INCLUDE");
    }
}

function obtenerCapas() {
    $("#accordion").html($("#loader").html());
    ObjMap.resetLayers();
    $.ajax({
        url: "obtenerCapas.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true
    }).done(function (dataSrv) {
        $("#accordion").html("");
        var idx = 0;

        var data = jQuery.parseJSON(dataSrv);
        $.each(data.capas, function (index, item) {
            ObjMap.addConfigLayerIWnd(item);
            var cat_cat = item.Categoria.replace(" ", "_");
            //alert(cat_cat); 
            if ($("#lstCapas_" + item.idCategoria).length == 0) {
                // $("#" + cat_cat).append($('<h4>' + item.Categoria + '</h4>'));                                
                $("#" + cat_cat).append($('<div>', {//puede modificar
                    "id": "lstCapas_" + item.idCategoria,
                    "class": "controlCapa cajalista"
                }));



                $("#lstCapas_" + item.idCategoria)
                        .append($('<div id="div' + item.alias + '">').append($('<input>', {
                            "id": item.id + "_" + item.alias,
                            "idx": idx,
                            "type": "checkbox",
                            "class": "ctrlCapa",
                            "name": 'check' + item.idCategoria
                        })).append($('<label>', {
                            "text": item.capa,
                            "for": item.id + "_" + item.alias
                        })));
                idx++;
            } else {
                $("#lstCapas_" + item.idCategoria).append($('<div id="div' + item.alias + '">').append($('<input>', {
                    "id": item.id + "_" + item.alias,
                    "idx": idx,
                    "type": "checkbox",
                    "class": "ctrlCapa",
                    "name": 'check' + item.idCategoria
                })).append($('<label>', {
                    "text": item.capa,
                    "for": item.id + "_" + item.alias
                })));
            }

        });

        $("#accordion").accordion({
            collapsible: true,
            active: false
        });

        $(".ctrlCapa").click(function (evt) {
            activateLayer(this);
        });

        $("#categoria1").change(function () {
            if ($("#categoria1").prop('checked') == true) {
                $("input[name=check1]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check1]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria3").change(function () {
            if ($("#categoria3").prop('checked') == true) {
                $("input[name=check3]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check3]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria5").change(function () {
            if ($("#categoria5").prop('checked') == true) {
                $("input[name=check5]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check5]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria6").change(function () {
            if ($("#categoria6").prop('checked') == true) {
                $("input[name=check6]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check6]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria7").change(function () {
            if ($("#categoria7").prop('checked') == true) {
                $("input[name=check7]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check7]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        if (initMap.idCapa != null && initMap.aliasMapa != null) {
            $("#" + initMap.idCapa + "_" + initMap.aliasMapa).click();
            $("#accordion").accordion("option", "active", parseInt($("#" + initMap.idCapa + "_" + initMap.aliasMapa).attr("idx")));
            if (initMap.filtro != null) {
                ObjMap.setFilterLayer(initMap.idCapa, decodeURIComponent(initMap.filtro));
                var zoom = ObjMap.map.getZoom();
                ObjMap.map.setZoom(zoom - 1);
                ObjMap.map.setZoom(zoom);
            }
        }

    }).fail(function (jqXHR, status, thrownError) {
        $("#accordion").html("");
        $("#accordion").append($('<p>No fue posible obtener catálogo de capas.</p>'));
        $("#accordion").append($('<div>')).append($('<a>', {
            "href": "javascript:obtenerCapas();",
            "text": "Volver a intentar"
        }));
    });
}


function obtenerCapasSgc() {
    $("#accordion").html($("#loader").html());
    ObjMap.resetLayers();
    $.ajax({
        url: "obtenerCapasSgc.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true
    }).done(function (dataSrv) {
        $("#accordion").html("");
        var idx = 0;

        var data = jQuery.parseJSON(dataSrv);
        $.each(data.capas, function (index, item) {
            ObjMap.addConfigLayerIWnd(item);
            var cat_cat = item.Categoria.replace(" ", "_");
            //alert(cat_cat); 
            if ($("#lstCapas_" + item.idCategoria).length == 0) {
                // $("#" + cat_cat).append($('<h4>' + item.Categoria + '</h4>'));                                
                $("#" + cat_cat).append($('<div>', {//puede modificar
                    "id": "lstCapas_" + item.idCategoria,
                    "class": "controlCapa cajalista"
                }));



                $("#lstCapas_" + item.idCategoria)
                        .append($('<div id="div' + item.alias + '">').append($('<input>', {
                            "id": item.id + "_" + item.alias,
                            "idx": idx,
                            "type": "checkbox",
                            "class": "ctrlCapa",
                            "name": 'check' + item.idCategoria
                        })).append($('<label>', {
                            "text": item.capa,
                            "for": item.id + "_" + item.alias
                        })));
                idx++;
            } else {
                $("#lstCapas_" + item.idCategoria).append($('<div id="div' + item.alias + '">').append($('<input>', {
                    "id": item.id + "_" + item.alias,
                    "idx": idx,
                    "type": "checkbox",
                    "class": "ctrlCapa",
                    "name": 'check' + item.idCategoria
                })).append($('<label>', {
                    "text": item.capa,
                    "for": item.id + "_" + item.alias
                })));
            }

        });

        $("#accordion").accordion({
            collapsible: true,
            active: false
        });

        $(".ctrlCapa").click(function (evt) {
            activateLayer(this);
        });

        $("#categoria1").change(function () {
            if ($("#categoria1").prop('checked') == true) {
                $("input[name=check1]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check1]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria3").change(function () {
            if ($("#categoria3").prop('checked') == true) {
                $("input[name=check3]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check3]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria5").change(function () {
            if ($("#categoria5").prop('checked') == true) {
                $("input[name=check5]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check5]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria6").change(function () {
            if ($("#categoria6").prop('checked') == true) {
                $("input[name=check6]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check6]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        $("#categoria7").change(function () {
            if ($("#categoria7").prop('checked') == true) {
                $("input[name=check7]").each(function () {
                    if ($(this).prop('checked') == false) {
                        $(this).prop('checked', true);
                        activateLayer(this);
                    }

                });
            } else {
                $("input[name=check7]").each(function () {
                    if ($(this).prop('checked') == true) {
                        $(this).prop('checked', false);
                        activateLayer(this);
                    }

                });
            }

        });

        if (initMap.idCapa != null && initMap.aliasMapa != null) {
            $("#" + initMap.idCapa + "_" + initMap.aliasMapa).click();
            $("#accordion").accordion("option", "active", parseInt($("#" + initMap.idCapa + "_" + initMap.aliasMapa).attr("idx")));
            if (initMap.filtro != null) {
                ObjMap.setFilterLayer(initMap.idCapa, decodeURIComponent(initMap.filtro));
                var zoom = ObjMap.map.getZoom();
                ObjMap.map.setZoom(zoom - 1);
                ObjMap.map.setZoom(zoom);
            }
        }

    }).fail(function (jqXHR, status, thrownError) {
        $("#accordion").html("");
        $("#accordion").append($('<p>No fue posible obtener catálogo de capas.</p>'));
        $("#accordion").append($('<div>')).append($('<a>', {
            "href": "javascript:obtenerCapasSgc();",
            "text": "Volver a intentar"
        }));
    });
}


var z = 0;
function activateLayer(elem) {
    $(".ctrlCapa").attr("disabled", "disabled");
    var id = $(elem).attr("id").split("_")[0];
    var alias = $(elem).attr("id").split("_")[1];
    if (ObjMap.findLayer(id)) {
        ObjMap.deleteLayer(id);
        $("#leyenda_" + id).remove();
        if (alias != "igecemCartaGeograficaCg2016") {
            if (alias != "igecemCartageograficaCg2015") {
                if (alias != "igecemCartografiaBaseCg2016") {
                    if (alias != "igecemAreashA15CgAh") {
                        $("#" + alias).remove();
                    }
                }
            }
        }
        if (id == 284 || id == 285 || id == 286) {
            z = z - 1;
        }

    } else {
        ObjMap.addlayer(id, WMS + ":" + alias, "", "INCLUDE");
        $("#leyenda_mapa").append($("<div>", {
            id: "leyenda_" + id,
            class: "leyendas"
        }).append($("<span>", {
            class: "nombre",
            text: $(elem).next().text()
        })).append("<br>").append($("<img>", {
            src: "obtenerLeyenda.do?id=" + id
        })));
        if (alias != "igecemCartaGeograficaCg2016") {
            if (alias != "igecemCartageograficaCg2015") {
                if (alias != "igecemCartografiaBaseCg2016") {
                    if (alias != "igecemAreashA15CgAh") {
                        $("#div" + alias).append($('<a>', {
                            id: alias,
                            style: "text-decoration:none;",
                            title: "Descargar",
                            //href:"http://192.168.15.64:7070/geoserver/SGCatastral/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=SGCatastral:"+alias+"&maxFeatures=200&outputFormat=SHAPE-ZIP",
                            onclick: "Descargashp(this.id)",
                            href: "#",
                        }).append($('<i>', {
                            class: "material-icons",
                            text: "file_download",
                            style: "display: inline;  padding-left:5px;  font-size:.6em;"

                        })));

                    }
                }
            }
        }
        contarC(id);
        if (id == 284 || id == 285 || id == 286) {
            //alert(z);
            if (z == 0) {
                neza();
            }
            z = z + 1;
        }
    }

    validarFiltroCapas();

    var czoom = ObjMap.map.getZoom();
    ObjMap.map.setZoom(czoom + 0.0001);
    setTimeout(function () {
        ObjMap.map.setZoom(Math.round(czoom));
    }, 1);

    $(".ctrlCapa").removeAttr("disabled");
}

function activateLayerOtros(elem) {
    $(".ctrlCapaOtros").attr("disabled", "disabled");
    var id = $(elem).attr("id").split("_")[0];
    var alias = $(elem).attr("id").split("_")[1];

    if (alias == "Clima") {
        if (weatherLayer == null) {
            weatherLayer = new google.maps.weather.WeatherLayer({
                temperatureUnits: google.maps.weather.TemperatureUnit.CELSIUS
            });
        }

        if ($("#0_Clima").is(':checked')) {
            weatherLayer.setMap(ObjMap.map);
        } else {
            weatherLayer.setMap(null);
        }

    } else if (alias = "Nublado") {
        if (cloudLayer == null) {
            cloudLayer = new google.maps.weather.CloudLayer();
        }

        if ($("#0_Nublado").is(':checked')) {
            cloudLayer.setMap(ObjMap.map);
        } else {
            cloudLayer.setMap(null);
        }
    }

    $(".ctrlCapaOtros").removeAttr("disabled");
}

function obtenerDireccion() {
    var direccion = $("#search_address").val();

    if (direccion.indexOf(",") != -1) {
        direccion = direccion.split(",");
    } else if (direccion.indexOf(" ") != -1) {
        direccion = direccion.split(" ");
    }

    var latitud = parseFloat(direccion[0]);
    var longitud = parseFloat(direccion[1]);

    if (isNaN(latitud) && isNaN(longitud)) {
        buscarDireccion();
    } else {
        obtenerDireccionInversa(latitud, longitud);
    }

}

function buscarDireccion() {
    //$("#resBusqueda").html("");

    if (marker != null) {
        marker.setMap(null);
    }

    if (infowindow == null) {
        infowindow = new google.maps.InfoWindow();
    } else {
        infowindow.close();
    }

    var address = $("#search_address").val();
    geocoder.geocode({'address': address}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            ObjMap.map.setCenter(results[0].geometry.location);
            ObjMap.map.fitBounds(results[0].geometry.viewport);
            $("#lng").val(results[0].geometry.location.lng());
            $("#lat").val(results[0].geometry.location.lat());
            marker = new google.maps.Marker({
                map: ObjMap.map,
                position: results[0].geometry.location
            });
            infowindow.setContent('<div><strong>' + results[0].formatted_address + '</strong><br><a onclick="closeMarker();">Quitar</a>');
            infowindow.open(ObjMap.map, marker);

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
            });

            google.maps.event.addListener(infowindow, 'closeclick', function () {
                marker.setMap(null);
            });
            $("#btn_borrar_busqueda").show();

        } else {
            //$("#resBusqueda").html("No se encontraron resultados");
            alert("No se encontraron resultados");
        }
    });
}

function obtenerDireccionInversa(latitude, longitude) {

    if (marker != null) {
        marker.setMap(null);
    }

    if (infowindow == null) {
        infowindow = new google.maps.InfoWindow();
    } else {
        infowindow.close();
    }

    var latlng = new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude));
    geocoder.geocode({'latLng': latlng}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            ObjMap.map.setCenter(results[0].geometry.location);
            ObjMap.map.fitBounds(results[0].geometry.viewport);
            var address = results[0].formatted_address;
            $("#direccion").val(address);
            if (marker != null) {
                marker.setMap(null);
            }
            marker = new google.maps.Marker({
                map: ObjMap.map,
                position: results[0].geometry.location
            });

            infowindow.setContent('<div><strong>' + address + '</strong><br><a onclick="closeMarker();">Quitar</a>');
            infowindow.open(ObjMap.map, marker);
            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
            });

            google.maps.event.addListener(infowindow, 'closeclick', function () {
                marker.setMap(null);
            });

            $("#btn_borrar_busqueda").show();

        } else {
            alert("No se encontraron resultados");
        }
    });
}

function obtenerEstados() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtenerEstados.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selEstados").html("");
        $("#selEstados").append("<option value='0|19.374|-99.188'>Nacional</option>");
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.estados, function (index, item) {
            $("#selEstados").append("<option value='" + item.clave + "'>" + item.estado + "</option>");
        });

    }).fail(function (jqXHR, status, thrownError) {
        onEstadosError();
    });
}

function onEstadosError() {
    $("#loaderBQAvanzada").hide();
    $("#selEstados").html("");
    $("#selMunicipios").html("");
    $("#selLocalidades").html("");
    $("#resultadoFiltros").append($('<p>No fue posible obtener catálogo de estados.</p>'));
    $("#resultadoFiltros").append($('<div>')).append($('<a>', {
        "href": "javascript:obtenerEstados();",
        "text": "Volver a intentar"
    }));
}


function obtenerCatMunicipios() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtenerMunicipios.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatMunicipios").removeAttr('disabled');
        $("#selCatMunicipios").html("");
        $("#selCatMunicipios").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Clave IGECEM' + '</th><th>' + 'Municipio' + '</th><th>' + 'Delegación' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.municipios, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.clave_igecem + '</td><td>' + item.municipio + '</td><td>' + item.delegacion + '</td></tr>');
            c = c + 1;
        });
        $("#selCatMunicipios").append('</tbody>');
        $("#selCatMunicipios").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}





function obtenerCatLocalidades() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtenerLocalidades.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatLocalidades").removeAttr('disabled');
        $("#selCatLocalidades").html("");
        $("#selCatLocalidades").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Localidad' + '</th><th>' + 'Categoría Política' + '</th><th>' + 'Categoría Administrativa' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.localidades, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.localidad + '</td><td>' + item.cat_pol + '</td><td>' + item.cat_admin + '</td></tr>');
            c = c + 1;
        });
        $("#selCatLocalidades").append('</tbody>');
        $("#selCatLocalidades").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}



function obtenerCatZonas() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtener_zonas.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatZonas").removeAttr('disabled');
        $("#selCatZonas").html("");
        $("#selCatZonas").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Zona' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.zonas, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.zona + '</td></tr>');
            c = c + 1;
        });
        $("#selCatZonas").append('</tbody>');
        $("#selCatZonas").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}


function obtenerCatMznas() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtener_manzanas.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatMznas").removeAttr('disabled');
        $("#selCatMznas").html("");
        $("#selCatMznas").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Municipio' + '</th><th>' + 'Zona' + '</th><th>' + 'Clave Manzana' + '</th><th>' + 'Clave Catastral' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.manzanas, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.municipio + '</td><td>' + item.zona + '</td><td>' + item.manzana + '</td><td>' + item.cve_cat + '</td></tr>');
            c = c + 1;
        });
        $("#selCatMznas").append('</tbody>');
        $("#selCatMznas").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}


function obtenerCatPredios() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtener_predios.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatPredios").removeAttr('disabled');
        $("#selCatPredios").html("");
        $("#selCatPredios").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Municipio' + '</th><th>' + 'Zona' + '</th><th>' + 'Clave Manzana' + '</th><th>' + 'Lote' + '</th><th>' + 'Clave Catastral' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.predios, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.municipio + '</td><td>' + item.zona + '</td><td>' + item.manzana + '</td><td>' + item.lote + '</td><td>' + item.cve_cat + '</td></tr>');
            c = c + 1;
        });
        $("#selCatPredios").append('</tbody>');
        $("#selCatPredios").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}



function obtenerCatColonias() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtener_colonias.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatColonias").removeAttr('disabled');
        $("#selCatColonias").html("");
        $("#selCatColonias").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Colonia' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.colonias, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.colonia + '</td></tr>');
            c = c + 1;
        });
        $("#selCatColonias").append('</tbody>');
        $("#selCatColonias").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}



function obtenerCatAreash() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtener_areash.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatAreash").removeAttr('disabled');
        $("#selCatAreash").html("");
        $("#selCatAreash").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Identificador' + '</th><th>' + 'Clasificación' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.areash, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.identificador + '</td><td>' + item.clasificacion + '</td></tr>');
            c = c + 1;
        });
        $("#selCatAreash").append('</tbody>');
        $("#selCatAreash").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}


function obtenerCatEcalles() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtener_ecalle.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selCatEcalle").removeAttr('disabled');
        $("#selCatEcalle").html("");
        $("#selCatEcalle").append('<table id="tabla" class="display" cellspacing="0" width="100%">');
        $("#tabla").append('<thead><tr><th>' + 'N°' + '</th><th>' + 'Calle' + '</th><th>' + 'Banda' + '</th></tr></thead><tbody>');
        var c = 1;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.ecalles, function (index, item) {
            $("#tabla").append('<tr><td>' + c + '</td><td>' + item.calle + '</td><td>' + item.banda + '</td></tr>');
            c = c + 1;
        });
        $("#selCatEcalle").append('</tbody>');
        $("#selCatEcalle").append('</table>');
        $(document).ready(function() {
    	$('#tabla').dataTable(
                
                {

    "language": {

    "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Registros del _START_ al _END_  Total _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {

            "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"

            },
            "oAria": {

            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"

            }

    }



    }
                    
                    );
	} );
       // $("#selCatMunicipios").append('<ul class="pagination pagination-lg pager" id="myPager"></ul>');
        
        
        //$('#selCatMunicipios').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:10});
        
    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}



function obtenerMunicipios() {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtenerMunicipios.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selMunicipios").removeAttr('disabled');
        $("#selMunicipios").html("");
        $("#selMunicipios").append("<option value=''>Municipio</option>");
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.municipios, function (index, item) {
            $("#selMunicipios").append("<option value='" + item.clave + "'>" + item.municipio + "</option>");
        });

    }).fail(function (jqXHR, status, thrownError) {
        onMunicipioError();
    });
}

function onMunicipioError(selEdo) {
    $("#loaderBQAvanzada").hide();
    $("#selMunicipios").html("");
    $("#selLocalidades").html("");
    $("#selLocalidades").attr('disabled', 'disabled');
    $("#resultadoFiltros").append($('<p>No fue posible obtener catálogo de municipios.</p>'));
    $("#resultadoFiltros").append($('<div>')).append($('<a>', {
        "href": "javascript:obtenerMunicipios(" + selEdo + ");",
        "text": "Volver a intentar"
    }));
}

function obtenerLocalidades(selMpio) {
    $("#loaderBQAvanzada").show();
    $.ajax({
        url: "obtenerLocalidades.do",
        type: 'POST',
        data: {cveMpio: selMpio[0]},
        dataType: 'text',
        timeout: 60000,
    }).done(function (dataSrv) {
        $("#loaderBQAvanzada").hide();
        $("#selLocalidades").removeAttr('disabled');
        $("#selLocalidades").html("");
        $("#selLocalidades").append("<option value='0|" + selMpio[1] + "|" + selMpio[2] + "'>Localidad</option>");
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.localidades, function (index, item) {
            $("#selLocalidades").append("<option value='" + item.clave + "'>" + item.localidad + "</option>");
        });

    }).fail(function (jqXHR, status, thrownError) {
        onLocalidadError(selMpio);
    });
}

function onLocalidadError(selMpio) {
    $("#loaderBQAvanzada").hide();
    $("#selLocalidades").html("");
    $("#resultadoFiltros").append($('<p>No fue posible obtener catálogo de localidades.</p>'));
    $("#resultadoFiltros").append($('<div>')).append($('<a>', {
        "href": "javascript:obtenerLocalidades(" + selMpio + ");",
        "text": "Volver a intentar"
    }));
}

function closeMarker() {
    if (infowindow != null) {
        infowindow.close();
    }
    if (marker != null) {
        marker.setMap(null);
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//FUNCIONES PARA TRAZO DE GEOMETRIAS
function setDrawingManager() {
    drawingManager = new google.maps.drawing.DrawingManager({
        drawingControl: false,
        drawingControlOptions: {
            drawingModes: [
                google.maps.drawing.OverlayType.MARKER,
                google.maps.drawing.OverlayType.POLYGON,
                google.maps.drawing.OverlayType.POLYLINE
            ]
        },
        markerOptions: {
            draggable: true,
            clickable: false,
            icon: "../images/icnpunto.png"
        },
        polylineOptions: {
            editable: true,
            strokeColor: '#FF0000'
        },
        polygonOptions: {
            editable: true,
            fillColor: '#FF0000',
            strokeColor: '#FF0000'
        }
    });

    drawingManager.setMap(ObjMap.map);

    google.maps.event.addListener(drawingManager, 'overlaycomplete', function (event) {
        drawingManager.setDrawingMode(null);
        geometria = event.overlay;
        if (event.type == google.maps.drawing.OverlayType.MARKER) {
            tipo = "punto";
            position = geometria.getPosition();
            usrPunto = geometria;
            $("#plng").val(position.lng());
            $("#plat").val(position.lat());
            google.maps.event.addListener(geometria, 'dragend', function () {
                position = geometria.getPosition();
                $("#plng").val(position.lng());
                $("#plat").val(position.lat());
            });
        } else if (event.type == google.maps.drawing.OverlayType.POLYLINE) {
            tipo = "linea";
            vertices = geometria.getPath();
            calcDist();
            google.maps.event.addListener(vertices, 'insert_at', function (vertex) {
                calcDist();
            });
            google.maps.event.addListener(vertices, 'set_at', function (vertex) {
                calcDist();
            });
            google.maps.event.addListener(vertices, 'remove_at', function (vertex) {
                calcDist();
            });
        } else if (event.type == google.maps.drawing.OverlayType.POLYGON) {
            tipo = "poligono";
            vertices = geometria.getPath();
            calcDist();
            calcArea();
            google.maps.event.addListener(vertices, 'insert_at', function (vertex) {
                calcDist();
                calcArea();
            });
            google.maps.event.addListener(vertices, 'set_at', function (vertex) {
                calcDist();
                calcArea();
            });
            google.maps.event.addListener(vertices, 'remove_at', function (vertex) {
                calcDist();
                calcArea();
            });

        } else {
            tipo = "";
        }
        geometrias.push(geometria);
    });

}

function drawMarker() {
    cleanDraws();
    drawingManager.setDrawingMode(google.maps.drawing.OverlayType.MARKER);
}

function drawLine() {
    cleanDraws();
    /*document.getElementById('inst1').style.display = 'block';
    document.getElementById('inst2').style.display = 'block';*/
    drawingManager.setDrawingMode(google.maps.drawing.OverlayType.POLYLINE);
}

function drawPolygon() {
    cleanDraws();
    /*document.getElementById('inst1').style.display = 'block';
    document.getElementById('inst3').style.display = 'block';*/
    drawingManager.setDrawingMode(google.maps.drawing.OverlayType.POLYGON);
}

function cleanDraws() {
    //google.maps.event.clearListeners(ObjMap.map, 'click');
    document.getElementById('txt_dist').style.display = 'none';
    document.getElementById('txt_area').style.display = 'none';
    document.getElementById('inst1').style.display = 'none';
    document.getElementById('inst2').style.display = 'none';
    document.getElementById('inst3').style.display = 'none';
    document.getElementById('area').style.display = 'none';
    document.getElementById('dist').style.display = 'none';
    document.getElementById('btnlim').style.display = 'none';
    document.getElementById('peri').style.display = 'none'; //cambio
    var i;

    for (i = 0; i < geometrias.length; i++) {
        geometrias[i].setMap(null);
    }

    for (i = 0; i < buffers.length; i++) {
        buffers[i].setMap(null);
    }

    if (markers) {
        for (i in markers) {
            markers[i].setMap(null);
        }
        markers.length = 0;
    }

    if (buff_c != null) {
        buff_c.setMap(null);
    }

    if (buff_g != null) {
        buff_g.setMap(null);
    }

    if (ObjMap.infowindow) {
        ObjMap.infowindow.close();
    }

    $("#txt_dist").val("");
    $("#txt_area").val("");
    $("#txt_areaBuff").val("");
    $("#txt_areaBuffCustom").val("");
    $("#txt_buff").val("50");
    $("#shape").val("");
    $("#buffDistance").val("");
    $("#plat").val("");
    $("#plng").val("");
    $("#resultado").html("");
    $("#resultado").attr("class", "");
    $("#resultadoBuff").html("");
    $("#resultadoBuff").attr("class", "");
    $("#imgPreview1").attr("src", "../images/ic_add_a_photo_black_48dp_2x.png");

}

//////////////////////////////////////////////////////////////////////////////////////////////////
//CALCULO DE DISTANCIAS
function calcDist() {

    var inicio, fin, distancia = 0;
    var i;

    for (i = 0; i < vertices.length - 1; i++) {
        inicio = vertices.getAt(i);
        fin = vertices.getAt(i + 1);
        distancia += mide(inicio, fin);
    }

    if (tipo == "linea") {
        $("#txt_dist").val(distancia.toFixed(3) + " km");
        document.getElementById('dist').style.display = 'block';
        document.getElementById('txt_dist').style.display = 'block';
        document.getElementById('btnlim').style.display = 'block';
    }
    if (tipo == "poligono") {

        inicio = vertices.getAt(0);
        fin = vertices.getAt(vertices.length - 1);
        distancia += mide(inicio, fin);

        $("#txt_dist").val(distancia.toFixed(3) + " km");
        document.getElementById('peri').style.display = 'block';
        document.getElementById('txt_dist').style.display = 'block';
        document.getElementById('btnlim').style.display = 'block';
    }

}

function mide(start, end) {
    var R = 6371; // earth's mean radius in km
    var dLat = degreesToRadians(end.lat() - start.lat());
    var dLon = degreesToRadians(end.lng() - start.lng());
    lat1 = degreesToRadians(start.lat()), lat2 = degreesToRadians(end.lat());
    var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(lat1) * Math.cos(lat2) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var d = R * c;
    return d;
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//CALCULO DE AREAS
function calcArea() {

    if (tipo == "poligono" || tipo == "linea") {
        var areaMeters2 = PlanarPolygonAreaMeters2();
        var areas = Areas(areaMeters2);
        var areasBuffer = AreasBuffer(areaMeters2);
        $("#txt_area").val(areas);
        $("#txt_areaBuff").val(areas);
        $("#txt_areaBuffCustom").val(areas);
        $("#a_poligono").val(areasBuffer);
        $("#area").val((areasBuffer / 1000000).toFixed(2));
        document.getElementById('area').style.display = 'block';
        document.getElementById('txt_area').style.display = 'block';
    } else {
        $("#txt_area").val("Polígono inválido");
        $("#txt_areaBuff").val("Polígono inválido");
    }
}

function PlanarPolygonAreaMeters2() {

    var a = 0.0;

    if (tipo == "poligono") {
        for (var i = 0; i < vertices.length; ++i) {
            var j = (i + 1) % vertices.length;
            var xi = vertices.getAt(i).lng() * metersPerDegree * Math.cos(vertices.getAt(i).lat() * radiansPerDegree);
            var yi = vertices.getAt(i).lat() * metersPerDegree;
            var xj = vertices.getAt(j).lng() * metersPerDegree * Math.cos(vertices.getAt(j).lat() * radiansPerDegree);
            var yj = vertices.getAt(j).lat() * metersPerDegree;
            a += xi * yj - xj * yi;
        }
    } else if (tipo == "linea") {
        for (var i = 0; i < verticesBuff.length; ++i) {
            var j = (i + 1) % verticesBuff.length;
            var xi = verticesBuff.getAt(i).lng() * metersPerDegree * Math.cos(verticesBuff.getAt(i).lat() * radiansPerDegree);
            var yi = verticesBuff.getAt(i).lat() * metersPerDegree;
            var xj = verticesBuff.getAt(j).lng() * metersPerDegree * Math.cos(verticesBuff.getAt(j).lat() * radiansPerDegree);
            var yj = verticesBuff.getAt(j).lat() * metersPerDegree;
            a += xi * yj - xj * yi;
        }
    }

    return Math.abs(a / 2.0);
}

function Angle(p1, p2, p3) {
    var bearing21 = Bearing(p2, p1);
    var bearing23 = Bearing(p2, p3);
    var angle = bearing21 - bearing23;
    if (angle < 0.0)
        angle += 360.0;
    return angle;
}
function Bearing(from, to) {
    var lat1 = from.lat() * radiansPerDegree;
    var lon1 = from.lng() * radiansPerDegree;
    var lat2 = to.lat() * radiansPerDegree;
    var lon2 = to.lng() * radiansPerDegree;
    var angle = -Math.atan2(Math.sin(lon1 - lon2) * Math.cos(lat2), Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    if (angle < 0.0)
        angle += Math.PI * 2.0;
    angle = angle * degreesPerRadian;
    return angle;

}

function Areas(areaMeters2) {
    var areaHectares = new oNumero(areaMeters2 / meters2PerHectare);
    var areaKm2 = new oNumero(areaMeters2 / metersPerKm / metersPerKm);
    var areametros2 = new oNumero(areaMeters2);
    var area = areametros2.formato(4, true) + ' m\u00B2 -- ' + areaHectares.formato(4, true) + ' ha -- ' + areaKm2.formato(4, true) + ' km\u00B2';
    return area;
}
///////////////////////////////////
//Área para enviar como parámetro al calificador
function AreasBuffer(areaMeters2) {
    var areametros2 = new oNumero(areaMeters2);
    var area = areametros2.formato(4, false);
    return area;
}

function oNumero(numero) {
    //Propiedades 
    this.valor = numero || 0;
    this.dec = -1;
    //Métodos 
    this.formato = numFormat;
    this.ponValor = ponValor;

    //Definición de los métodos
    function ponValor(cad) {
        if (cad == '-' || cad == '+')
            return
        if (cad.length == 0)
            return
        if (cad.indexOf('.') >= 0)
            this.valor = parseFloat(cad);
        else
            this.valor = parseInt(cad);
    }

    function numFormat(dec, miles) {
        var num = this.valor, signo = 3, expr;
        var cad = "" + this.valor;
        var ceros = "", pos, pdec, i;
        for (i = 0; i < dec; i++)
            ceros += '0';
        pos = cad.indexOf('.');
        if (pos < 0)
            cad = cad + "." + ceros;
        else {
            pdec = cad.length - pos - 1;
            if (pdec <= dec) {
                for (i = 0; i < (dec - pdec); i++)
                    cad += '0';
            } else {
                num = num * Math.pow(10, dec);
                num = Math.round(num);
                num = num / Math.pow(10, dec);
                cad = new String(num);
            }
        }
        pos = cad.indexOf('.');
        if (pos < 0)
            pos = cad.lentgh
        if (cad.substr(0, 1) == '-' || cad.substr(0, 1) == '+')
            signo = 4;
        if (miles && pos > signo)
            do {
                expr = /([+-]?\d)(\d{3}[\.\,]\d*)/;
                cad.match(expr);
                cad = cad.replace(expr, RegExp.$1 + ',' + RegExp.$2);
            } while (cad.indexOf(',') > signo);
        if (dec < 0)
            cad = cad.replace(/\./, '')
        return cad;
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//GENERACION DE BUFFERS
function doBuffer() {

    svc = new gmaps.ags.GeometryService('http://sampleserver3.arcgisonline.com/arcgis/rest/services/Geometry/GeometryServer');
    var i;

    for (i = 0; i < buffers.length; i++) {
        buffers[i].setMap(null);
    }

    for (i = 0; i < geometrias.length - 1; i++) {
        geometrias[i].setMap(null);
    }

    radius = parseFloat(document.getElementById("txt_buff").value);

    if (radius == "") {
        radius = 0;
    }

    buffers.length = 0;
    var g = null;

    if (tipo == "punto" || tipo == "linea") {
        var params = {
            geometries: [geometria],
            inSR: 4326,
            outSR: 4326,
            bufferSpatialReference: 102113,
            distances: [radius],
            unit: 9001,
            unionResults: true,
            overlayOptions: {
                fillColor: '#FF0000',
                strokeColor: '#FF0000'
            }
        };

        svc.buffer(params, function (results, err) {
            if (!err) {

                for (var i = 0, I = results.geometries.length; i < I; i++) {
                    for (var j = 0, J = results.geometries[i].length; j < J; j++) {
                        g = results.geometries[i][j];
                        g.setMap(ObjMap.map);
                        buffers.push(g);
                    }
                }

                verticesBuff = g.getPath();

                if (tipo == "linea" || tipo == "poligono") {
                    calcArea();
                }

            } else {
                alert(err.message + err.details.join(','));
            }
        });
    }

    $("#buffDistance").val(radius);
    getCheckedCapas();
    getBuffGeom();
    //$("#frmExtraerCapas").submit();

}

function extractor() {
    $("#frmExtraerCapas").submit();
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//EXTRACCION DE GEOMETRIAS PARA INTERSECCION DE CAPAS
function getBuffGeom() {

    var i = null;
    var xy = null;
    var xyi = null;
    var contentString = null;
    var polygonString = [];
    var tipo_geom = null;

    switch (tipo) {
        case "punto":
            contentString = "POINT(" + position.lng() + " " + position.lat() + ")";
            tipo_geom = 1;
            break;
        case "linea":

            contentString = "LINESTRING(";
            for (i = 0; i < vertices.length; i++) {
                xy = vertices.getAt(i);
                contentString += xy.lng() + " " + xy.lat();
                contentString = contentString + ", ";
            }
            contentString = contentString + ")";
            contentString = contentString.replace(", )", ")");
            tipo_geom = 1;
            break;
        case "poligono":
            contentString = "POLYGON((";
            for (i = 0; i < vertices.length; i++) {
                xy = vertices.getAt(i);
                contentString += xy.lng() + " " + xy.lat();
                contentString = contentString + ", ";
                polygonString.push("{" + xy.lat() + ", " + xy.lng() + "}");
            }
            xyi = vertices.getAt(0);
            var verticei = xyi.lng() + " " + xyi.lat();
            contentString = contentString + verticei + "))";
            polygonString.push("{" + xyi.lat() + ", " + xyi.lng() + "}");
            tipo_geom = 2;
            break;
        case "":
            contentString = "";
            tipo_geom = "";
            break;
    }

    $("#shape").val(contentString);
    $("#tipo_geom").val(tipo_geom);
    $("#geom_poligono").val(contentString);
    $("#mvc_poligono").val(polygonString);
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//LIMPIAR BÚSQUEDA DE DIRECCIONES DE GOOGLE
function borrar_busqueda() {
    $("#search_address").val("");
    marker.setMap(null);
    $("#btn_borrar_busqueda").hide();
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//OBTENER IDENTIFICADORES DE CHECKBOX PARA EL EXTRACTOR
function getCheckedCapas() {
    var lst_checked_capas = [];
    $("input[class=ctrlCapa]:checked").each(function () {
        lst_checked_capas.push($(this).attr('id'));
    });

    $("#lst_capas").val(lst_checked_capas);

}
//////////////////////////////////////////////////////////////////////////////////////////////////
//DESCARGAR RESULTADOS DE EXTRACCIÓN
function descargarDocumento() {
    var url = "descargarExtractor.do";
    document.cookie = "fileDownload=true; path=/";
    $("#loaderExtractor").show();
    $.fileDownload(url, {
        successCallback: function (urlr) {
            setTimeout(function () {
                $("#loaderExtractor").hide();
                $("#resultadoBuff").html("Generando archivo...");
            }, 1000);
        },
        failCallback: function (responseHtml, url) {
            $("#loaderExtractor").hide();
            $("#resultadoBuff").html("Error al descargar el archivo, por favor intente más tarde");
        }
    });
}
//Funciones Creadas
var id_divant;
var contadiv = 0;
function despliegadiv(id_selec) {
    var id_selec2 = id_selec;
    if (id_selec2 != id_divant) {
        document.getElementById(id_selec2).style.display = 'block';
        var x = document.getElementsByClassName(id_selec2);
        x[0].style.color = "#8bc34a";
        var y = document.getElementsByClassName(id_selec2);
        y[0].style.fontSize = "1.2em";
        document.getElementById("tema").className = id_selec2;
        $("#icono").remove();
        $("#titulo").remove();
        var icono = id_selec2;
        icono = icono.toLowerCase();
        icono = icono.replace("á", "a");
        icono = icono.replace("é", "e");
        icono = icono.replace("í", "i");
        icono = icono.replace("ó", "o");
        icono = icono.replace("ú", "u");
        if (icono != 'productos' && icono != 'consejeria_jurídica') {
            $("#divAreaSec").append('<a href="#" id="icono"><i class="icon-' + icono + '"></i></a>');
        } else if (icono = 'productos' && icono != 'consejeria_jurídica') {
            $("#divAreaSec").append('<a href="#" id="icono"><i class="material-icons" style="font-size:1em;">layers</i></a>');
        } else if (icono = 'consejeria_jurídica') {
            $("#divAreaSec").append('<a href="#" id="icono"><i class="icon-juridico"></i></a>');
        }

        var titulo = id_selec2;
        titulo = titulo.replace("_", " ");
        $("#divAreaSec2").append('<h1 id="titulo">' + titulo + '</h1>');



        if (contadiv > 0) {
            document.getElementById(id_divant).style.display = 'none';
            if (id_divant != "Territorial" && id_divant != "Sociodemográfico" && id_divant != "Socioeconómico" && id_divant != "Urbano_Regional") {
                var z = document.getElementsByClassName(id_divant);
                z[0].style.color = "gray";
            } else {
                var z = document.getElementsByClassName(id_divant);
                z[0].style.color = "#b50d0d";
            }
            var l = document.getElementsByClassName(id_divant);
            l[0].style.fontSize = "1em";
        }

    }
    contadiv++;
    id_divant = id_selec2;
}
//FIN

function descripcion(id) {
    $("#descm").remove();
    $("#imagen").remove();
    $("#pie").remove();
    $("#tabm").remove();
    $("#imgley").remove();
    $.ajax({
        url: "descripcion.do?id=" + id,
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true}).done(function (dataSrv) {
        var data = jQuery.parseJSON(dataSrv);

        $.each(data.dess, function (index, item) {
            var descrip = item.descripcion;
            descrip = descrip.split('->');
            var descrip2 = descrip;
            $("#London").append('<p id="descm" style="text-align:justify;"><i>' + descrip2[0] + '</i></p>');
            if (descrip2[1] != null) {
                var descrip3 = descrip2[1].split('<-');
                $("#London").append('<img id="imagen" src="../img/' + descrip3[0] + '" style="max-width:300px; max-height:200px;display:block;margin:0 auto 0 auto;"/>');
                $("#London").append('<p id="pie" style="text-align:center; font-size:10px;"><b>' + descrip3[1] + '</b></p><br>');
            }
        });

    });


    $.ajax({
        url: "tabular.do?id=" + id,
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true}).done(function (dataSrv) {
        var data = jQuery.parseJSON(dataSrv);

        if (dataSrv != '{"tabus":[]}') {
            var unidad = document.getElementById('medida').className;//agregar
            unidad = unidad.replace(/_/g, " ");//agregar
            $("#Paris").append('<h4>Unidad de medida: ' + unidad + '</h4>');//agregar
            $("#Paris").append('<table id="tabm">');
            $("#tabm").append('<tr><th>' + 'N°' + '</th><th>' + 'Municipio' + '</th><th>' + 'Valor' + '</th></tr>');
            var c = 1;

            $.each(data.tabus, function (index, item) {
                $("#tabm").append('<tr><td>' + c + '</td><td>' + item.municipio + '</td><td>' + item.campo + '</td></tr>');
                c = c + 1;
            });
            $("#Paris").append('</table>');
        } else {
            $("#estadis").remove();
            $("#grafico").remove();
        }
    });


    $("#leyenda").append($("<div>", {
        id: "leyenda_" + id,
        class: "leyendas"
    }).append($("<span>", {
        class: "nombre",
        //text: $(elem).next().text()
    })).append($("<img>", {
        src: "obtenerLeyenda.do?id=" + id
    })));

    $.ajax({
        url: "fuente.do?id=" + id,
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true}).done(function (dataSrv) {
        var data = jQuery.parseJSON(dataSrv);

        $.each(data.fuen, function (index, item) {
            $("#fuente").append('<p id="descm" style="text-align:justify;"><i>Fuente:' + item.fuente + '</i></p>');
        })
    });
    /*$.ajax({
     url: "fuente.do?id=" + id,
     type: 'POST',
     dataType: 'text',
     timeout: 60000,
     async: true,
     success: function (data) {
     var fuente = data;
     //alert(fuente);
     fuente = fuente.replace('{"fuen":[{"fuente":"', " ");
     fuente = fuente.replace('"}]}', " ");
     
     $("#fuente").append('<p id="descm" style="text-align:justify;"><i>Fuente:' + fuente + '</i></p>');
     }});*/
    document.getElementById('info').style.display = 'block';
    document.getElementById('London').style.display = 'block';
    document.getElementById('fuente').style.display = 'block';
    document.getElementById('descripcion').style.display = 'none';
    grafica(id);
}


function Mostdesc() {
    document.getElementById('London').style.display = 'block';
    document.getElementById('Paris').style.display = 'none';
    document.getElementById('tabs-4').style.display = 'none';
    document.getElementById('Grafic').style.display = 'none';
}
function Mostest() {
    document.getElementById('London').style.display = 'none';
    document.getElementById('Paris').style.display = 'block';
    document.getElementById('tabs-4').style.display = 'none';
    document.getElementById('Grafic').style.display = 'none';
}

function Mostley() {
    document.getElementById('London').style.display = 'none';
    document.getElementById('tabs-4').style.display = 'block';
    document.getElementById('Paris').style.display = 'none';
    document.getElementById('Grafic').style.display = 'none';

}
function Mostgra() {
    document.getElementById('London').style.display = 'none';
    document.getElementById('tabs-4').style.display = 'none';
    document.getElementById('Paris').style.display = 'none';
    document.getElementById('Grafic').style.display = 'block';

}

function activarInicio(id2) {
    var id23 = id2;
    var alias2 = "Atizapan";
    ObjMap.addlayer(id23, WMS + ":" + alias2, "", "INCLUDE");

}

function searchPlace(search) {

    var autocomplete = new google.maps.places.Autocomplete(search);

    autocomplete.bindTo('bounds', ObjMap.map);

    //Se activa la búsqueda
    autocomplete.addListener('place_changed', function () {
        var place = autocomplete.getPlace();
        //Evalúa si encuentra resultados de la búsqueda con Google Places
        //Si encentra resultados, recupera las coordenadas y se ejecuta la geocodificación inversa        
        if (!place.geometry) {
            //return;          
            //Si no encuentra resultsados, se ejecuta la búsqueda con el API de direcciones
            obtenerDireccion();
        } else {
            if (marker != null) {
                marker.setMap(null);
            }

            if (infowindow == null) {
                infowindow = new google.maps.InfoWindow();
            } else {
                infowindow.close();
            }

            if (place.geometry.viewport) {
                ObjMap.map.fitBounds(place.geometry.viewport);
            } else {
                ObjMap.map.setCenter(place.geometry.location);
                ObjMap.map.setZoom(17);
            }

            marker = new google.maps.Marker({
                map: ObjMap.map,
                position: place.geometry.location
            });

            $("#lat").val(marker.getPosition().lat());
            $("#lng").val(marker.getPosition().lng());
            $("#direccion").val(place.formatted_address);

            infowindow.setContent('<div><strong>' + place.name + '</strong><br><a onclick="closeMarker();">Quitar</a>');
            infowindow.open(ObjMap.map, marker);

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(ObjMap.map, marker);
            });

            google.maps.event.addListener(infowindow, 'closeclick', function () {
                marker.setMap(null);
            });

            $("#btn_borrar_busqueda").show();

        }
    });

}

function obtenerEspacios() {
    $("#accordion").html($("#loader").html());
    ObjMap.resetLayers();
    $.ajax({
        url: "obtenerEspacios.do",
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true
    }).done(function (dataSrv) {
        $("#accordion").html("");
        var idx = 0;
        var data = jQuery.parseJSON(dataSrv);
        $.each(data.capas, function (index, item) {
            ObjMap.addConfigLayerIWnd(item);
            var cat_cat2 = item.Espacio.replace(" ", "_");

            //alert(cat_cat); 
            if ($("#lstCapae_" + item.idCategoria).length == 0) {
                $("#" + cat_cat2).append($('<h7>' + item.Categoria + '</h7>'));
                $("#" + cat_cat2).append($('<div>', {//puede modificar
                    "id": "lstCapae_" + item.idCategoria,
                    "class": "controlCapa cajalista"
                }));



                $("#lstCapae_" + item.idCategoria)
                        .append($('<li class="vin">').append($('<label>', {
                            "text": item.capa,
                            "for": item.id + "_" + item.alias
                        })));
                idx++;
            } else {
                $("#lstCapae_" + item.idCategoria).append($('<li class="vin">')
                        .append($('<label>', {
                            "text": item.capa,
                            "for": item.id + "_" + item.alias
                        })));
            }

        });

        $("#accordion").accordion({
            collapsible: true,
            active: false
        });



    })
}

function grafica(id) {
    var cate = " ";
    var serie = " ";
    $.ajax({
        url: "grafica.do?id=" + id,
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true,
        success: function (data) {
            var graf = data;
            if (graf != '{"grafs":[]}') {
                graf = graf.replace('{"grafs":[{"municipio":"', " ");
                graf = graf.split('},{"municipio":"');
                var unidad2 = document.getElementById('medida').className;//agregar
                unidad2 = unidad2.replace(/_/g, " ");//agregar
                // $("#Grafic").append('<h4>Unidad de medida: '+unidad+'</h4>');//agregar
                for (l = 0; l <= 10; l++) {
                    graf[l] = graf[l].split('","campo":');
                    var graf2 = graf[l];
                    if (l == 9) {
                        graf2[1] = graf2[1].replace('}]}', " ");
                    }
                    //alert(graf2[1]+graf2[0]);

                    cate = cate + "," + graf2[0];
                    serie = serie + "," + graf2[1];
                    if (l == 9) {
                        // alert(cate);
                        cate = cate.split(',');
                        serie = serie.split(',');
                        //alert(cate[1]+serie[1]+cate[2]+serie[2]+cate[3]+serie[3]+cate[4]+serie[4]+cate[5]+serie[5]+cate[6]+serie[6]+cate[7]+serie[7]+cate[8]+serie[8]+cate[9]+serie[9]+cate[10]+serie[10]);
                        Highcharts.theme = {
                            colors: ['#8BC34A']

                        };

// Apply the theme
                        Highcharts.setOptions(Highcharts.theme);
//---------------------------------------

                        Highcharts.chart('grafico2', {
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: 'Municipios con mayor valor'
                            },
                            xAxis: {
                                categories: [cate[1], cate[2], cate[3], cate[4], cate[5], cate[6], cate[7], cate[8], cate[9], cate[10]]
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: unidad2
                                }
                            },
                            lang: {
                                printChart: 'Imprimir gráfico',
                                downloadPNG: 'Descarga en PNG',
                                downloadJPEG: 'Descarga en JPEG',
                                downloadPDF: 'Descarga en PDF',
                                downloadSVG: 'Descarga en SVG',
                                contextButtonTitle: 'Menú de descarga'
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{
                                    name: unidad2,
                                    data: [Number(serie[1]), Number(serie[2]), Number(serie[3]), Number(serie[4]), Number(serie[5]), Number(serie[6]), Number(serie[7]), Number(serie[8]), Number(serie[9]), Number(serie[10])]
                                }]
                        });
                        graficam(id);
                    }
                }
            }
        }});
}

function graficam(id) {
    var catem = " ";
    var seriem = " ";
    $.ajax({
        url: "graficam.do?id=" + id,
        type: 'POST',
        dataType: 'text',
        timeout: 60000,
        async: true,
        success: function (data) {
            var grafm = data;
            if (grafm != '{"grafms":[]}') {
                grafm = grafm.replace('{"grafms":[{"municipio":"', " ");
                grafm = grafm.split('},{"municipio":"');
                var unidad2m = document.getElementById('medida').className;//agregar
                unidad2m = unidad2m.replace(/_/g, " ");//agregar
                // $("#Grafic").append('<h4>Unidad de medida: '+unidad+'</h4>');//agregar
                for (d = 0; d <= 10; d++) {
                    grafm[d] = grafm[d].split('","campo":');
                    var graf2m = grafm[d];
                    if (d == 9) {
                        graf2m[1] = graf2m[1].replace('}]}', " ");
                    }
                    //alert(graf2[1]+graf2[0]);

                    catem = catem + "," + graf2m[0];
                    seriem = seriem + "," + graf2m[1];
                    if (d == 9) {

                        catem = catem.split(',');
                        seriem = seriem.split(',');

                        Highcharts.theme = {
                            colors: ['#b50d0d']

                        };

// Apply the theme
                        Highcharts.setOptions(Highcharts.theme);
//---------------------------------------

                        Highcharts.chart('grafico3', {
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: 'Municipios con menor valor'
                            },
                            xAxis: {
                                categories: [catem[1], catem[2], catem[3], catem[4], catem[5], catem[6], catem[7], catem[8], catem[9], catem[10]]
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: unidad2m
                                }
                            },
                            lang: {
                                printChart: 'Imprimir gráfico',
                                downloadPNG: 'Descarga en PNG',
                                downloadJPEG: 'Descarga en JPEG',
                                downloadPDF: 'Descarga en PDF',
                                downloadSVG: 'Descarga en SVG',
                                contextButtonTitle: 'Menú de descarga'
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{
                                    name: unidad2m,
                                    data: [Number(seriem[1]), Number(seriem[2]), Number(seriem[3]), Number(seriem[4]), Number(seriem[5]), Number(seriem[6]), Number(seriem[7]), Number(seriem[8]), Number(seriem[9]), Number(seriem[10])]
                                }]
                        });
                    }
                }
            }
        }});

}

function Descargashp(alias) {
    location.href = "http://acvisor.edomex.gob.mx:8080/geoserver/Atlas/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=Atlas:" + alias + "&maxFeatures=200&outputFormat=SHAPE-ZIP";
}

function home() {
    var latlng = new google.maps.LatLng(iniLatitud, iniLongitud);
    ObjMap.map.setZoom(9);
    ObjMap.map.setCenter(latlng);
}

function contaVisitas() {
    $.ajax({

        type: "post",
        cache: false,
        url: "Visitas.do",
        dataType: 'json',
        async: true,

        success: function (callback) {
            verVisitas();
        }
    });
}

/*function verVisitas() {
    $.ajax({

        type: "post",
        cache: false,
        url: "Impvis.do",
        dataType: 'text',
        async: true,

        success: function (data) {
            // alert(data);
            $("#visit").append(data);
        }
    });
}*/

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

/*function checkCookie() {
    var user = getCookie("username");
    if (user != "") {
        verVisitas()
    } else {
        contaVisitas();
        user = "visitas";
        setCookie("username", user, 365);
    }
}*/

function contarC(id) {
    $.ajax({

        type: "post",
        cache: false,
        url: "contarCapas.do?id=" + id,
        dataType: 'json',
        async: true
    });
}

function buscador(valor) {
    //alert(valor);
    $("#buscador").remove();
    var n = valor.length;
    var bus2 = " ";
    var bus3 = " ";
    if (n > 0) {
        $.ajax({

            type: "post",
            cache: false,
            url: "obtenerbus.do?nombre=" + valor,
            dataType: 'text',
            async: true,

            success: function (data) {
                var busque = data;
                busque = busque.replace('{"capasb":[{"id":', "");
                busque = busque.replace(/"capa":"/g, "");
                busque = busque.replace(/"alias":"/g, "");
                busque = busque.replace(/"}/g, "");
                busque = busque.replace(/"/g, "");
                busque = busque.replace(/]}/g, "");
                //busque=busque.replace("\t","");
                var busqued = busque.split("{id:");
                $("#divControles").append("<ul id='buscador' style='display:block;'>");

                for (i = 0; i <= 15; i++) {
                    if (busqued[i] == null) {
                        i = 15;
                        $("#buscador").append("</ul>");
                    } else {
                        var spl = busqued[i];
                        spl = spl.split(",");
                        //alert(spl[1]+","+spl[2]);
                        bus2 = spl[1].replace(/ /g, "_");
                        bus3 = spl[2].replace(/ /g, "_");
                        $("#buscador").append("<li id=" + bus2 + "," + bus3 + " value=" + spl[i] + " onclick='cambiacom(this.id)'><label for=" + spl[0] + "_" + spl[3] + ">" + spl[1] + ", " + spl[2] + "</label></li>");
                    }
                }
                //alert(busque);
                //alert(data);
            }


        });
    }


}

function cambiacom(value) {
    value = value.replace(/_/g, " ");
    //alert(value);
    document.getElementById("busca").value = value;
    document.getElementById("buscador").style.display = 'none';
}

function neza() {
    iniLatitud = 19.422721;
    iniLongitud = -99.022649;
    var latlng = new google.maps.LatLng(iniLatitud, iniLongitud);
    ObjMap.map.setZoom(13);
    ObjMap.map.setCenter(latlng);
}

