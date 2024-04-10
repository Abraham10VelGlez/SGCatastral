<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Sistema de Gestión Catastral</title>
        <link rel="shortcut icon" href="../img/igecem.jpg">
        <link rel="stylesheet" type="text/css" href="../css/estiloVisor.css">
        <link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!--<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-sidebar/3.3.2/jquery.sidebar.min.js"></script> <!--sidebar -->
        <script src="../js/jquery.sidebar.js"></script>


        <script src="../js/ext/jquery-ui.js"></script>
        <script src="../js/ext/OpenLayers.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBNRHBwtT9VWjgsBR5OsrrPvpjy9mzdL4&v=3.21&libraries=places,drawing"></script>   
        <script src="../js/global.js"></script>
        <script src="../js/herramientas.js"></script>
        <script src="../js/mapaUtil.js"></script>
        <script src="../js/mapa.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>


        <script src="../js/pdfobject.js"></script>


        <link rel="stylesheet" type="text/css" href="../css/elastislide.css" />
        <script src="../js/modernizr.custom.17475.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/style.css" />


        <script type="text/javascript">
            $(document).ready(function () {

                $("#header").load("../header.do");
                $("#footer").load("../footer.do");
                initMap = ${initMap};
                inicializarMapa('contenedorMapa', initMap);
                $("#panelMapa").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
                $("#tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
                checkCookie()
                //mostrarControlMapa();
            });
        </script>
    </head>
    <body>
        <div class="contenedor">
            <div class="headerVisor">
                <div class="headerLogos">
                    <img src="../img/H1I.png"/>
                </div>
                <div class="headerTitulo">
                    <h1>Visor del Atlas Cibernético del Estado de México</h1>
                </div>
            </div>
            <div id="buscar" class="searchbox" role="search">
                <div class="form-group">            
                    <input type="search" id="search_address" class="busqueda" placeholder="Buscar lugar" style="width:300px;">
                </div>
                <!-- <button class="boton" id="btn_borrar_busqueda" style="display:none" onclick="borrar_busqueda()"><span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:white;"></span></button>
                 <button class="boton boton2" onclick="obtenerDireccion()"><span class="glyphicon glyphicon-search" aria-hidden="true" style="color:white"></span></button>-->
            </div>
            <div id="contenedorMapa"></div>
            <div id="dialog" title="Atlas Cibernético"></div>
            <div class="sidebars">	            
                <div class="sidebar right">
                    <div class="divArea">
                        <div class="divAreaTitulo">
                            <div id="divAreaSec" class="divAreaSec"><a href="#" id="icono"><i class="icon-limites_territoriales"></i></a></div>
                            <div id="divAreaSec2" class="divAreaSec2"><h1 id="titulo">Atlas Cibernético</h1></div>



                        </div>
                        <div class="divControles" id="divControles">
                            <h2 class="subtitulos">Controles</h2>
                            <label class="texto">Transparencia</label>
                            <input id="range1" type="range" min="0" max="100" value="90" style="width:60%;" onchange="setOpacity(value)" />
                            <button id="limpiar" name="limpiar" class="boton">DESACTIVAR CAPAS</button><button id="home" name="home" class="boton" onclick="home()" style="font-size:.2em; float:right; margin-right: 21%;"><i class="material-icons">home</i></button>
                            <p id="visitas"><b><a class="descripcion" href="#" onclick= pdf(this.className) id="tema">Descripción de área temática</a></b><b id="visit" hidden>Visitas:</b>
                             <!--<input type="text" id="busca" onKeyUp="buscador(this.value);"  placeholder="Buscar..." style="width:400px;">--></p>
                            
                        </div>

                        <div id="tabs">
                            <ul>
                                <li class="active"><a href="#tabs-1"><i class="material-icons">flip_to_back</i>CAPAS</a></li>
                                <li><a href="#tabs-2"><i class="material-icons">description</i>LEYENDA</a></li>
                                <li><a href="#tabs-3"><i class="material-icons">find_in_page</i>MUNICIPIOS</a></li>
                                <li><a href="#tabs-4"><i class="material-icons">build</i>HERRAMIENTAS</a></li>
                            </ul>
                            <div id="tabs-1">
                                
                                <h2 class="subtitulos">Capas de información</h2>               
                                <div id="Agropecuario" class="controlCapa caja" style="display:none; padding:-1%;">

                                </div>
                                <div id="Desarrollo_Urbano" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Económico" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Educación" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Infraestructura" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Límites_Territoriales" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Medio_Ambiente" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Movilidad" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Población" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Protección_Civil" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Seguridad" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Trabajo" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Turismo" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Salud" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Finanzas" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Consejería_Jurídica" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Límites_Históricos" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Productos" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Cultura" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Territorial" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Urbano_Regional" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Sociodemográfico" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Socioeconómico" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Desarrollo_Social" class="controlCapa caja" style="display:none;">

                                </div>
                                <div id="Estadística" class="controlCapa caja" style="display:none;">

                                </div>

                            </div>
                            <div id="tabs-2">
                                <h2 class="subtitulos">Leyenda</h2>
                                <div id="leyenda_mapa" class="leyendas">
                                </div>
                            </div>
                            <div id="tabs-3">
                                <h2 class="subtitulos">Buscar municipio</h2>
                                <select id="selMunicipios">
                                    <option value="">Seleccione</option>
                                </select>
                                <br>
                                <h2 class="subtitulos">Buscar localidad</h2>
                                <select id="selLocalidades"  disabled="true">
                                    <option value="">Seleccione</option>
                                </select>

                            </div>
                            <div id="tabs-4">
                                <h2 class="subtitulos">Medición</h2>
                                <div id="medicion">                            
                                    <span class="span-4">Para dibujar sobre el mapa seleccione una opción:</span>
                                    <div>                                                                   
                                        <button onclick="drawLine()" class="btn btn-default" title="Distancia"><i class="material-icons" style="color:gray; font-size:.7em;">linear_scale</i></button>
                                        <button onclick="drawPolygon()" class="btn btn-default" title="Área"><i class="material-icons" style="color:gray; font-size:.7em;">format_shapes</i></button>                                  
                                    </div>
                                    <span class="span-4 vin2" id="inst1" style="display:none; padding-left:5%;">Para calcular haga doble clic sobre el último punto.</span>
                                    <span class="span-4 vin2" id="inst2" style="display:none; padding-left:5%;">Para medir distancia se necesitan mínimo 2 puntos.</span>
                                    <span class="span-4 vin2" id="inst3" style="display:none; padding-left:5%;">Para obtener el área se necesitan mínimo 3 puntos.</span>
                                    <div>
                                        <div id="dist" class="span-4" style="padding-top:.3em; display:none; font-weight:bold;">Distancia:</div>
                                        <div id="peri" class="span-4" style="padding-top:.3em; display:none; font-weight:bold;">Perímetro:</div>
                                        <input id="txt_dist" type="text" class="texto" style="display:none;"/>
                                    </div>
                                    <div>                                    
                                        <div id="area" class="span-4" style="padding-top:1em; display:none; font-weight: bold;">Área:</div>
                                        <textarea id="txt_area" name="area" rows="3" cols="20" class="texto" style="display:none;"></textarea>
                                    </div>
                                    <div>
                                        <input id="btnlim" type="button" value="LIMPIAR" onclick="cleanDraws()" class="botonl" style="display:none;"/>
                                    </div>
                                </div>
                              <h2 class="subtitulos">Ruta</h2>
                             <div id="ruta">
                                 <span class="span-4">Selecciona un botón para elegir la ruta y dibuje los puntos sobre el mapa:</span>
                                 <div>
                                  <button onclick="Ruteo('DRIVING')" class="btn btn-default" title="Automóvil"><i class="material-icons" style="color:gray; font-size:.7em;">directions_car</i></button>
                               <button onclick="Ruteo('WALKING')" class="btn btn-default" title="Caminando"><i class="material-icons" style="color:gray; font-size:.7em;">directions_walk</i></button>
                               <button onclick="Ruteo('BICYCLING')" class="btn btn-default" title="Bicicleta"><i class="material-icons" style="color:gray; font-size:.7em;">directions_bike</i></button>
                               <button onclick="Ruteo('TRANSIT')" class="btn btn-default" title="Transporte Público"><i class="material-icons" style="color:gray; font-size:.7em;">directions_bus</i></button>
                               <input id="cleanR" type="button" value="LIMPIAR///DESACTIVAR"  class="botonl" style="display:none;"/>
                                 </div>
                                 <div id="panel"></div>
                             </div>
                              <h2 class="subtitulos">Geocodificación inversa</h2>
                             <div id="geocinv">
                                 <span class="span-4">Marca un punto en el mapa:</span>
                                 <div>
                                  <button onclick="Geocod()" class="btn btn-default" title="Inversa"><i class="material-icons" style="color:gray; font-size:.7em;">place</i></button>
                                  <input id="geoc" type="button" value="LIMPIAR///DESACTIVAR" onclick="cleanMark();detener()" class="botonl" style="display:none;"/>
                                 </div>
                             </div>
                               <h2 class="subtitulos">Altitud</h2>
                             <div id="altitud">
                                 <span class="span-4">Marca un punto en el mapa:</span>
                                 <div>
                                  <button onclick="Altitud()" id="altitud" class="btn btn-default" title="Inversa"><i class="material-icons" style="color:gray; font-size:.7em;">terrain</i></button>
                                  <input id="altit" type="button" value="LIMPIAR///DESACTIVAR"  class="botonl" style="display:none;"/>      
                                 </div>
                             </div>
                            </div>

                        </div>

                    </div>

                    <div class="column">
                        <!-- Elastislide Carousel -->
                        <ul id="carousel" class="elastislide-list bloque">
                            <!--<li ><a href="#"><img src="#" /></a></li>-->

                            <li><a  class="Agropecuario" onclick="despliegadiv(this.className)" title="Agropecuario"><i class="icon-agropecuario"></i></a></li>
                            <li><a class= "Cultura" onclick="despliegadiv(this.className)"  title="Cultura"><i class="icon-cultura"></a></i></li>
                            <li><a class= "Económico" onclick="despliegadiv(this.className)"  title="Desarrollo Económico"><i class="icon-economico"></i></a></li>
                            <li><a class="Educación" onclick="despliegadiv(this.className)"  title="Educación"><i class="icon-educacion"></i></a></li>
                            <li><a class="Estadística" onclick="despliegadiv(this.className)"  title="Estadística"><i class="icon-estadistica"></i></a></li>
                            <li><a class= "Finanzas" onclick="despliegadiv(this.className)" title="Finanzas"><i class="icon-finanzas"></i></a></li>
                            <li><a class= "Infraestructura" onclick="despliegadiv(this.className)" title="Infraestructura"><i class="icon-infraestructura"></i></a></li>
                            <li><a class= "Consejería_Jurídica" onclick="despliegadiv(this.className)"  title="Consejería Jurídica"><i class="icon-juridico"></i></a></li>
                            <li><a class= "Límites_Históricos" onclick="despliegadiv(this.className)" title="Límites Históricos"><i class="icon-limites_historicos"></a></i></li>
                            <li><a class= "Límites_Territoriales" onclick="despliegadiv(this.className)" title="Límites Territoriales"><i class="icon-limites_territoriales"></i></a></li>
                            <li><a class="Medio_Ambiente" onclick="despliegadiv(this.className)"  title="Medio Ambiente"><i class="icon-medio_ambiente"></i></a></li>
                            <li><a class= "Movilidad" onclick="despliegadiv(this.className)"  title="Movilidad"><i class="icon-movilidad"></i></a></li>
                            <li><a class= "Población" onclick="despliegadiv(this.className)" title="Población"><i class="icon-poblacion"></i></a></li>
                            <li><a class= "Salud" onclick="despliegadiv(this.className)"  title="Salud"><i class="icon-salud"></i></a></li>
                            <li><a class= "Seguridad" onclick="despliegadiv(this.className)"  title="Seguridad"><i class="icon-seguridad"></i></a></li>
                           <!-- <li><a class= "Desarrollo_Social" onclick="despliegadiv(this.className)"  title="Desarrollo Social"><i class="icon-desarrollo_social"></i></a></li>-->
                            <li><a class= "Trabajo" onclick="despliegadiv(this.className)"  title="Trabajo"><i class="icon-trabajo"></i></a></li>
                            <li><a class= "Turismo" onclick="despliegadiv(this.className)" title="Turismo"><i class="icon-turismo"></i></a></li>
                            <li><a class= "Desarrollo_Urbano" onclick="despliegadiv(this.className)" title="Desarrollo Urbano"><i class="icon-desarrollo_urbano"></i></a></li>
                            <!--<li><a class= "Desarrollo_Social" onclick="despliegadiv(this.className)" data-toggle="tooltip" data-placement="bottom" title="Desarrollo Social"><i class="icon-desarrollo_social2"></i></a></li>-->
                            <li><a class= "Productos" onclick="despliegadiv(this.className)"  title="Productos"><i class="material-icons" style="font-size:1em;">layers</i></a></li>
                            <li><a href="#"><img src="../img/espacios2.png"  style="width:33px; height:33px;"/></a></li>
                            <li><a class= "Socioeconómico" onclick="despliegadiv(this.className)" title="Socioecnómico" style="color:#b50d0d;"><i class="icon-socioeconomico"></a></i></li>
                            <li><a class= "Sociodemográfico" onclick="despliegadiv(this.className)" title="Sociodemográfico" style="color:#b50d0d;"><i class="icon-sociodemografico"></a></i></li>
                            <li><a class= "Territorial" onclick="despliegadiv(this.className)" title="Territorial" style="color:#b50d0d;"><i class="icon-territorial"></a></i></li>
                            <li><a class= "Urbano_Regional" onclick="despliegadiv(this.className)" title="Urbano Regional" style="color:#b50d0d;"><i class="icon-urbano_regional"></a></i></li>

                        </ul>
                        <!-- End Elastislide Carousel -->
                    </div>


                </div>            

            </div>
            <div class="toggle">
                <i id="izquierda" class="material-icons toggleFlecha" data-action="open" data-side="right">keyboard_arrow_left</i>
                <i id="derecha" style="display: none;" class="material-icons toggleFlecha" data-action="close" data-side="right">keyboard_arrow_right</i>
            </div>
            <div class="footerVisor">
                <h4>Derechos Reservados. IGECEM 2017.</h4>
            </div>



            <script type="text/javascript">
                $(document).ready(function () {


                    // All sides
                    var sides = ["left", "top", "right", "bottom"];
                    $("h1 span.version").text($.fn.sidebar.version);

                    // Initialize sidebars
                    for (var i = 0; i < sides.length; ++i) {
                        var cSide = sides[i];
                        $(".sidebar." + cSide).sidebar({side: cSide});
                    }

                    $("#izquierda").on("click", function () {
                        $("#derecha").css("display", "block");
                        $("#izquierda").css("display", "none");
                        $(".toggle").css("right", "40%");

                        $(".sidebar.right").trigger("sidebar:open");

                    });

                    $("#derecha").on("click", function () {
                        $("#derecha").css("display", "none");
                        $("#izquierda").css("display", "block");
                        $(".toggle").css("right", "0");

                        $(".sidebar.right").trigger("sidebar:close");

                    });

                    $(function () {
                        $("#tabs").tabs();
                    });

                    $('#tabs li').click(function () {
                        $(this).siblings('li').removeClass('active');
                        $(this).addClass('active');
                    });


                });
            </script>


            <script>
                $(function () {
                    $("#dialog").dialog({
                        autoOpen: false,
                        show: {
                            effect: "blind",
                            duration: 1000
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        }
                    });
                    $("#tema").on("click", function () {
                        $("#dialog").dialog("open");
                    });

                });

            </script>


            <script>function pdf(valor) {
                    valor = valor.replace("á", "a");
                    valor = valor.replace("é", "e");
                    valor = valor.replace("í", "i");
                    valor = valor.replace("ó", "o");
                    valor = valor.replace("ú", "u");
                    PDFObject.embed("../pdf/" + valor + ".pdf", "#dialog");
    }</script>

            <script type="text/javascript" src="../js/jquerypp.custom.js"></script>
            <script type="text/javascript" src="../js/jquery.elastislide.js"></script>
            <script type="text/javascript">

$('#carousel').elastislide({
    orientation: 'vertical'
});

            </script>

            <script>
                $("#limpiar").click(function () {
                    $("input[type=checkbox]").each(function () {  //se puede con class=ctrlCapa             
                        if ($(this).prop('checked') == true) {
                            $(this).prop('checked', false);
                            activateLayer(this);
                        }

                    });


                });
            </script>
            
         <!--   <script>
                $("#altitud").click(function () {
                    $("input[type=checkbox]").each(function () {  //se puede con class=ctrlCapa             
                        if ($(this).prop('checked') == true) {
                            $(this).prop('checked', false);
                            activateLayer(this);
                        }

                    });


                });
            </script>-->


    </body>
</html>