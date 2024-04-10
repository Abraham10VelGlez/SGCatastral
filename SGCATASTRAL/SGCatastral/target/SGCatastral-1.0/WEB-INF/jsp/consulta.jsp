<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">                
        <link href="css/bootstrap.css" rel="stylesheet">        
        <link href="css/style_sgc.css" rel="stylesheet" type="text/css" >        
        <!--link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet"-->
        <link rel="stylesheet" href="css/datatables.css">
        <script src="js/jquery-1.9.1.min.js"></script> 
        <script src="js/bootstrap.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
        <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" />
        <link rel="stylesheet" href="css/themes/default/default.css" type="text/css" />
        <!--script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script-->
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBNRHBwtT9VWjgsBR5OsrrPvpjy9mzdL4&v=3.21&libraries=places,drawing"></script>   

        <style>
            label, select{
                font-size: 100% !important;
            }                        

            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
                border-top: none;
            }

            .modal-body h4 {
                color:green;
            }

            .modal-header .close {
                margin-top: -20px;
                font-size: 30px;
            }

            #map { 
                width: 80%;
                min-height: 400px;                    
            }
        </style>
    </head>
    <body>

        <div id="page-wrapper">

            <div id="page">
                <!--div class="container-fluid"-->
                <!-- Header -->
                <header class="container">
                    <div id="containers" >
                        <!--img src="img/header.png"-->
                    </div>             

                </header>

                <!-- Menu -->
                <div class="container" id="menu">

                    <nav style="min-height: 36px;" class="navbar navbar-default ">
                        <!-- We use the fluid option here to avoid overriding the fixed width of a normal container within the narrow content columns. -->
                        <div class="container-fluid">
                            <div class="navbar-header"><a class="navbar-brand hidden-md hidden-lg" href="#"></a>
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#barranavegacion">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>

                            </div>

                            <div class="collapse navbar-collapse" id="barranavegacion">
                                <ul class="links nav navbar-nav" id="main-menu"><li class="menu-291 first"><a class="active" href="inicio.do">Inicio</a></li>
                                    <!--li class="menu-1289"><a href="acercade.do">Acerca de SGC</a></li-->
                                    <li class="menu-1289"><a href="catalogo_mun.do">Catálogos</a></li>
                                    <li><a href="map_sgc.do">Alta</a></li>                                    
                                    <li><a href="consulta.do">Consulta</a></li>
                                    <li><a id="signout" href="loggedout.do">Salir</a></li>
                                    <!--li><a href="contacto.do">Contacto</a></li-->

                                </ul>  
                            </div><!-- /.navbar-collapse -->
                        </div>
                    </nav>
                </div>
                <div class="container" >
                    <div class="row">
                        <div class="col-xs-4">
                            <label>Fecha Inicial</label>
                            <input type="date" id="finicial" required>
                        </div>
                        <div class="col-xs-4">
                            <label>Fecha Término</label>
                            <input type="date" id="ffinal" required>
                        </div>

                        <div class="col-xs-4">
                            <button class="btn btn-info" onclick="consultarTramites();">Consultar</button>
                        </div>                        
                    </div>

                    <div class="row" style="margin-top:50px;">
                        <div class="col-xs-12">
                            <table id="tabla" class="display" cellspacing="0" width="100%"></table>
                        </div>
                    </div>


                </div>
                <div class="container" >
                    <hr>
                    <div>
                        <footer  class="col-xs-12 col-sm-12 col-md-12 col-lg-12 fondofooter" >

                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="letrerofooter">ACERCA DEL SITIO</div>
                                <div>
                                    <ul>
                                        <li><a href="contacto.do" style="color:#FFFFFF">Cóntactanos</a></li>            
                                        <li><a href="acercade.do" style="color:#FFFFFF">Acerca del sitio</a></li>
                                    </ul>   
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="letrerofooter">CONTACTANOS</div>
                                <div>
                                    <ul>
                                        <li style="list-style-image:url(img/footer/direccion.png)">
                                            Instituto de Información e Investigación Geográfica, Estadística y Catastra.</li>
                                        <li style="list-style-image:url(img/footer/ubicacion.png)">Lerdo Poniente número 101, primer piso, puerta 303 Toluca, centro.</li>
                                        <li style="list-style-image:url(img/footer/telefono.png)">(722) 2133021 2159481</li>
                                        <li style="list-style-image:url(img/footer/email.png)">geografia.igecem@edomex.gob.mx</li>
                                    </ul>   
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="letrerofooter">ENLACES DE INTERÉS</div>

                                <div>
                                    <ul>
                                        <li><a href="http://edomex.gob.mx/" target="new" style="color:#FFFFFF">Gobierno del Estado de México</a></li>
                                        <li><a href="http://igecem.edomex.gob.mx/" target="new" style="color:#FFFFFF">IGECEM</a></li>
                                        <li><a href="http://ccg.edomex.gob.mx/" target="new" style="color:#FFFFFF">Centro de Colaboración Geoespacial del Estado de México</a></li>
                                        <li><a href="http://atlascibernetico.edomex.gob.mx/" target="new" style="color:#FFFFFF">Atlas Cibernético del Estado de México</a></li>                
                                    </ul> 
                                </div>
                            </div>
                        </footer>

                        <div style="margin-bottom:10px; padding-bottom:10px;" class="footerbottom col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <span class="bottom-header">Gobierno del Estado de México 2017</span><br><span>Esta página esta diseñada para verse mejor en resolucóon 1280 X 768 o superior, Firefox &amp; Chrome V30, Safari V5, IE 10 </span><br> Este sitio esta optimizado para visualizarse en dispositivos moviles, smartphones  y tabletas</div>

                    </div>
                </div>

                <!-- EOF #footer -->



            </div>

        </div>


        <div id="modal" class="modal fade">
            <div class="modal-dialog" role="document" style="width:60%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title">Ticket: <span id="ticket"></span></h2>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="overflow-y: scroll;max-height: 70vh;">
                        <div class="row">
                            <div class="col-xs-12">
                                <h4>Clave Catastral</h4>
                                <table class="table table-striped">
                                    <thead>
                                    <th style="width: 40%;"></th>
                                    <th style="width: 60%;"></th>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><strong>Municipio:</strong></td>
                                            <td><span id="municipio"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Zona:</strong></td>
                                            <td><span id="zona"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Manzana:</strong></td>
                                            <td><span id="manzana"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Predio:</strong></td>
                                            <td><span id="idpredio"></span></td>
                                        </tr>
                                    </tbody>
                                </table>                            
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-xs-12">
                                <h4>Propietario</h4>
                                <table class="table table-striped">
                                    <thead>
                                    <th style="width: 40%;"></th>
                                    <th style="width: 60%;"></th>
                                    </thead>
                                    <tbody>                                  
                                        <tr>
                                            <td><strong>Propietario:</strong></td>
                                            <td><span id="propietario"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>RFC:</strong></td>
                                            <td><span id="rfc"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>CURP:</strong></td>
                                            <td><span id="curp"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Domicilio Fiscal:</strong></td>
                                            <td><span id="domfis"></span></td>
                                        </tr>
                                    </tbody>
                                </table>                            
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12">
                                <h4>Localización</h4>
                                <table class="table table-striped">
                                    <thead>
                                    <th style="width: 40%;"></th>
                                    <th style="width: 60%;"></th>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><strong>Localidad:</strong></td>
                                            <td><span id="localidad"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Zona Origen:</strong></td>
                                            <td><span id="zonaorigen"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Calle:</strong></td>
                                            <td><span id="calle"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Número Exterior:</strong></td>
                                            <td><span id="numext"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Código Postal:</strong></td>
                                            <td><span id="cp"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Colonia:</strong></td>
                                            <td><span id="colonia"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Entre Calle:</strong></td>
                                            <td><span id="entrecalle"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Y Calle:</strong></td>
                                            <td><span id="ycalle"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Regimen de Propiedad:</strong></td>
                                            <td><span id="regpropiedad"></span></td>
                                        </tr>
                                    </tbody>
                                </table>                            
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-xs-12">
                                <h4>Terreno</h4>
                                <table class="table table-striped">
                                    <thead>
                                    <th style="width: 40%;"></th>
                                    <th style="width: 60%;"></th>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><strong>Superficie total:</strong></td>
                                            <td><span id="supterrtot"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Frente:</strong></td>
                                            <td><span id="frente"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Fondo:</strong></td>
                                            <td><span id="fondo"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Área inscrita:</strong></td>
                                            <td><span id="areainscr"></span></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Superficie de Aprovechamiento:</strong></td>
                                            <td><span id="supaprov"></span></td>
                                        </tr>    
                                        <tr>
                                            <td><strong>Superficie de Terreno Comun:</strong></td>
                                            <td><span id="supterrcom"></span></td>
                                        </tr>                                   
                                    </tbody>
                                </table>                            
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12">                    
                                <div id="map"></div>
                            </div>
                        </div>


                        <div id="apartadoDctos" class="row" style="display:none;">
                            <div class="col-xs-12">
                                <h4>Documentos</h4>
                                <table class="table table-striped">                                
                                    <tbody id="doctos">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Descargar</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>


        <script>
            var tabla = null;
            var map;

            $(document).ready(function () {
                $.extend(true, $.fn.dataTable.defaults, {
                    columns: [
                        {title: "Ticket", data: 'cve_cat'},
                        {title: "Fecha Solicitud", data: 'fcaptura'},
                        {title: "Solicita", data: 'propietario'},
                    ],
                    "language": {
                        "lengthMenu": "Mostrar _MENU_ por Página",
                        "zeroRecords": "NO hay información disponible",
                        "info": "_PAGE_ / _PAGES_",
                        "infoEmpty": "No hay información disponible",
                        "emptyTable": "No hay información disponible",
                        "infoFiltered": "(Filtro de _MAX_ total registros)",
                        "sSearch": "Buscar",
                        "oPaginate": {
                            "sFirst": "Siguiente",
                            "sPrevious": "Anterior",
                            "sNext": "Siguiente",
                            "sLast": "Anterior"
                        }
                    },
                    "aoColumnDefs": [
                        {
                            "aTargets": [0],
                            "mRender": function (data, type, full) {
                                return "<a href='javascript:consultaTramite(" + full.id + ");' ><span style='color:green'>" + data + "</span></a>";
                            }
                        }
                    ]
                });
            });

            function consultarTramites() {

                if ($("#finicial").val() == "") {
                    alert('Ingrese la Fecha Inicial');
                    return;
                }

                if ($("#ffinal").val() == "") {
                    alert('Ingrese la Fecha Final');
                    return;
                }

                var d1 = new Date($("#finicial").val());
                var d2 = new Date($("#ffinal").val());

                if (d2 - d1 <= 0) {
                    alert('La fecha final debe ser mayor a la fecha inicial');
                    return;
                }

                $.ajax({
                    url: "ctramites.do",
                    type: 'POST',
                    data: {finicial: $("#finicial").val(),
                        ffinal: $("#ffinal").val(),
                    },
                    dataType: 'text',
                    timeout: 60000
                }).done(function (dataSrv) {
                    var datos = jQuery.parseJSON(dataSrv);

                    if (tabla != null) {
                        tabla.destroy();
                    }

                    tabla = $('#tabla').DataTable({
                        data: datos
                    });

                }).fail(function (jqXHR, status, thrownError) {
                    alert("Error: " + thrownError);
                });
            }



            function consultaTramite(id) {
                $("#apartadoDctos").hide();
                $("#modal").modal("show");
                $.ajax({
                    url: "tramite.do",
                    type: 'POST',
                    data: {id: id},
                    dataType: 'text',
                    timeout: 60000
                }).done(function (dataSrv) {
                    var datos = jQuery.parseJSON(dataSrv);

                    $("#ticket").html(datos.cve_cat);
                    $("#municipio").html(datos.municipio);
                    $("#manzana").html(datos.manzana);
                    $("#zona").html(datos.zona);
                    $("#idpredio").html(datos.idpredio);


                    $("#propietario").html(datos.propietario);
                    $("#rfc").html(datos.rfc);
                    $("#curp").html(datos.curp);
                    $("#domfis").html(datos.domfis);

                    $("#localidad").html(datos.localidad);
                    $("#zonaorigen").html(datos.zonaorigen);
                    $("#calle").html(datos.calle);
                    $("#numext").html(datos.numext);
                    $("#cp").html(datos.cp);
                    $("#colonia").html(datos.colonia);
                    $("#entrecalle").html(datos.entrecalle);
                    $("#ycalle").html(datos.ycalle);
                    $("#regpropiedad").html(datos.regpropiedad);



                    $("#supterrtot").html(datos.supterrtot);
                    $("#frente").html(datos.frente);
                    $("#fondo").html(datos.fondo);
                    $("#areainscr").html(datos.areainscr);
                    $("#supaprov").html(datos.supaprov);

                    $("#supterrcom").html(datos.supterrcom);

                    var hd = '{"type": "FeatureCollection","features": [ {"type": "Feature", "style": {"stroke": "green","fill":"red"},"geometry": ';
                    setTimeout(function () {
                        initMap(datos.lon, datos.lat, hd + datos.geo + '}]}');
                    }, 300);


                    if (datos.documentos.length > 0) {
                        $("#apartadoDctos").show();
                        $("#doctos").empty();

                        $.each(datos.documentos, function (idx, item) {
                            $("#doctos").append("<tr><td><a href='descargaDocumento.do?id=" + item.id + "'><strong>" + item.documento + "</strong></a></td></tr>");
                        });

                    }


                }).fail(function (jqXHR, status, thrownError) {
                    alert("Error: " + thrownError);
                });
            }


            function initMap(lon, lat, geo) {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: new google.maps.LatLng(lat, lon),
                    zoom: 12
                });

                loadGeoJsonString(geo);
            }


            function loadGeoJsonString(geoString) {
                var geojson = JSON.parse(geoString);
                map.data.addGeoJson(geojson);
                zoom(map);
            }


            function zoom(map) {
                var bounds = new google.maps.LatLngBounds();
                map.data.forEach(function (feature) {
                    processPoints(feature.getGeometry(), bounds.extend, bounds);
                });
                map.fitBounds(bounds);
                map.setZoom(map.getZoom() - 2);
            }


            function processPoints(geometry, callback, thisArg) {
                if (geometry instanceof google.maps.LatLng) {
                    callback.call(thisArg, geometry);
                } else if (geometry instanceof google.maps.Data.Point) {
                    callback.call(thisArg, geometry.get());
                } else {
                    geometry.getArray().forEach(function (g) {
                        processPoints(g, callback, thisArg);
                    });
                }
            }
        </script>


    </body>
</html>
