<%-- 
    Document   : map_sgc
    Created on : 15/11/2017, 04:17:10 PM
    Author     : Fernando
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Sistema de Gestión Catastral</title>
        <link rel="shortcut icon" href="img/igecem.jpg">

        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
        <!--link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'-->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/style_sgc.css" rel="stylesheet" type="text/css" >
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>


        <!--link rel="stylesheet" type="text/css" href="css/estiloVisor_sgc.css"-->
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
        <!--link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto"-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script-->
        <!--<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-sidebar/3.3.2/jquery.sidebar.min.js"></script> <!--sidebar -->
        <script src="js/jquery.sidebar.js"></script>


        <script src="js/ext/jquery-ui.js"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="js/ext/OpenLayers.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBNRHBwtT9VWjgsBR5OsrrPvpjy9mzdL4&v=3.21&libraries=places,drawing"></script>   
        <script src="js/global.js"></script>
        <script src="js/herramientas.js"></script>
        <script src="js/mapaUtil.js"></script>
        <script src="js/mapa.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>


        <script src="js/pdfobject.js"></script>
        <script src="js/inc_padron_predios.js"></script>



        <link rel="stylesheet" type="text/css" href="css/elastislide.css" />
        <script src="js/modernizr.custom.17475.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css" />


        <script type="text/javascript">
            $(document).ready(function () {


                initMap = ${initMap};
                inicializarMapa('contenedorMapa', initMap);
                $("#panelMapa").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
                $("#tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
                //checkCookie()
                //mostrarControlMapa();
            });
        </script>
    </head>
    <body>

        <div id="page-wrapper">

            <div id="page">
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
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#barranavegacion"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                            </div>
                            <div class="collapse navbar-collapse" id="barranavegacion">
                                <ul class="links nav navbar-nav" id="main-menu">
                                    <li class="menu-291 first active"><a class="active" href="inicio.do">Inicio</a></li>
                                    <!--li class="menu-1289"><a href="acercade.do">Acerca de SGC</a></li-->
                                    <li class="menu-1290"><a href="catalogo_mun.do">Catálogos</a></li>
                                    <li><a href="map_sgc.do">Alta</a></li>
                                    <li><a href="consulta.do">Consulta</a></li>
                                    <li><a id="signout" href="loggedout.do">Salir</a></li>
                                    <!--li><a href="contacto.do">Contacto</a></li-->
                                </ul>
                            </div>
                            <!-- /.navbar-collapse -->
                        </div>
                    </nav>

                </div>







                <div class="container">
                    <!--div id="menu_izq">
                        <h2 class="nomh2">Acerca de SGC</h2>
                        <div class="content_menu_izq">
                            <ul class="menu_ul">
                                <li class="menu_li"><a href="acercade.do">Acerca de SGC</a></li>                           

                            </ul>  
                        </div>
                    </div-->
                    <div id="contenidos_map">

                        <h3 class="nomh3">Incorporación al padrón</h3>
                        <input type="hidden" id="drawingpolygon" name="drawingpolygon" />
                        <input type="hidden" id="keypreed" name="keypreed"/>

                        <div id="textos">


                            <p>



                            </p>

                        </div>


                        <div id="contenedor_alta">

                            <input id="pes_1" type="radio" name="radio_alt" class="pes_selector_1" checked="checked" value="pes_1"/>
                            <label for="pes_1" class="pes_label_1">Mapa</label>

                            <input id="pes_2" type="radio" name="radio_alt" class="pes_selector_1" value="pes_2"/>
                            <label for="pes_2" class="pes_label_2">Predios</label>

                            <input id="pes_3" type="radio" name="radio_alt" class="pes_selector_1" value="pes_3"/>
                            <label for="pes_3" class="pes_label_3">Propietarios</label>

                            <input id="pes_4" type="radio" name="radio_alt" class="pes_selector_1" value="pes_4"/>
                            <label for="pes_4" class="pes-label_4">Histórico</label>

                            <input id="pes_5" type="radio" name="radio_alt" class="pes_selector_1" value="pes_5"/>
                            <label for="pes_5" class="pes_label_5">Propias</label>

                            <input id="pes_6" type="radio" name="radio_alt" class="pes_selector_1" value="pes_6"/>
                            <label for="pes_6" class="pes_label_6">Comunes</label>

                            <input id="pes_7" type="radio" name="radio_alt" class="pes_selector_1" value="pes_7"/>
                            <label for="pes_7" class="pes_label_7">Documentos</label>





                        </div>  


                        <div id="visor_d" style="display:block;">

                            <button id="eventiniprd" class="btn btn-success" title="Iniciar">Continuar</button>


                            <div id="map_sgc">

                                <div id="buscar" class="searchbox" role="search">
                                    <div class="form-group">            
                                        <input type="search" id="search_address" class="busqueda" placeholder="Buscar lugar" style="width:300px;">
                                        <!--button onclick="drawPolygon()" class="btn btn-default" title="Área"><i class="material-icons" style="color:gray; font-size:.7em;">format_shapes</i></button-->
                                        <button onclick="dibjpavg()" class="btn btn " title="Dibujar Predio"><i class="material-icons" style="color:gray; font-size:.7em;">format_shapes</i></button>
                                        <input id="btnlim" type="button" value="LIMPIAR" onclick="cleanDraws()" class="botonl" style="display:none;"/>
                                    </div>

                                </div>
                                <div id="contenedorMapa"></div>
                                <div id="dialog" title="Sistema de Gestión Catastral"></div>
                                <!--div class="sidebars">	            
                                    <div class="sidebar right">
                                        <div class="divArea">
                                            <div class="divAreaTitulo">
                                                <div id="divAreaSec" class="divAreaSec"><a href="#" id="icono"><i class=""></i></a></div>
                                                <div id="divAreaSec2" class="divAreaSec2"><h1 id="titulo">Sistema de Gestión Catastral</h1></div>



                                            </div>
                                            <div class="divControles" id="divControles">
                                                <h2 class="subtitulos">Controles</h2>
                                                <label class="texto">Transparencia</label>
                                                <input id="range1" type="range" min="0" max="100" value="90" style="width:60%;" onchange="setOpacity(value)" />
                                                <button id="limpiar" name="limpiar" class="boton">DESACTIVAR CAPAS</button><button id="home" name="home" class="boton" onclick="home()" style="font-size:.2em; float:right; margin-right: 21%;"><i class="material-icons">home</i></button>


                                            </div>

                                            <div id="tabs">
                                                <ul>
                                                    <li class="active"><a href="#tabs-1">CAPAS
                                                        </a></li>
                                                    <li><a class="Agropecuario" onclick="despliegadiv(this.className)" title="Agropecuario">CAPAS</a></li>
                                                    <li><a href="#tabs-2"><i class="material-icons">description</i>LEYENDA</a></li>
                                                    <li><a href="#tabs-3"><i class="material-icons">find_in_page</i>MUNICIPIOS</a></li>
                                                    <li><a href="#tabs-4"><i class="material-icons">build</i>HERRAMIENTAS</a></li>
                                                </ul>
                                                <div id="tabs-1">

                                                    <h2 class="subtitulos">Capas de información</h2>               
                                                    <div id="Agropecuario" class="controlCapa caja" style="display:none; padding:-1%;">

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




                                    </div>            

                                </div-->

                                <div id="sgc" class="sidebars2">	            
                                    <div class="divArea2">
                                        <div class="divAreaTitulo2">

                                            <div id="divAreaSec2" class="AreaSec2"><h1 id="titulo">Sistema de Gestión Catastral</h1></div>



                                        </div> 


                                        <!--div class="col-xs-12"--> 
                                        <!-- design process steps--> 
                                        <!-- Nav tabs -->
                                        <!--ul class="nav nav-tabs process-model more-icon-preocess" role="tablist">
                                            <li role="presentation" class="active"><a href="#discover" aria-controls="discover" role="tab" data-toggle="tab"><i class="fa fa-search" aria-hidden="true"></i>
                                                    <p>Discover</p>
                                                </a></li>
                                            <li role="presentation"><a href="#strategy" aria-controls="strategy" role="tab" data-toggle="tab"><i class="fa fa-send-o" aria-hidden="true"></i>
                                                    <p>Strategy</p>
                                                </a></li>
                                            <li role="presentation"><a href="#optimization" aria-controls="optimization" role="tab" data-toggle="tab"><i class="fa fa-qrcode" aria-hidden="true"></i>
                                                    <p>Optimization</p>
                                                </a></li>
                                            <li role="presentation"><a href="#content" aria-controls="content" role="tab" data-toggle="tab"><i class="fa fa-newspaper-o" aria-hidden="true"></i>
                                                    <p>Content</p>
                                                </a></li>
                                            <li role="presentation"><a href="#reporting" aria-controls="reporting" role="tab" data-toggle="tab"><i class="fa fa-clipboard" aria-hidden="true"></i>
                                                    <p>Reporting</p>
                                                </a></li>
                                        </ul>
                                        <!-- end design process steps--> 
                                        <!-- Tab panes -->
                                        <!--div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="discover">
                                                <div class="design-process-content">
                                                    <h3 class="semi-bold">Discovery</h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincid unt ut laoreet dolore magna aliquam erat volutpat</p>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane" id="strategy">
                                                <div class="design-process-content">
                                                    <h3 class="semi-bold">Strategy</h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincid unt ut laoreet dolore magna aliquam erat volutpat</p>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane" id="optimization">
                                                <div class="design-process-content">
                                                    <h3 class="semi-bold">Optimization</h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincid unt ut laoreet dolore magna aliquam erat volutpat</p>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane" id="content">
                                                <div class="design-process-content">
                                                    <h3 class="semi-bold">Content</h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincid unt ut laoreet dolore magna aliquam erat volutpat</p>              
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane" id="reporting">
                                                <div class="design-process-content">
                                                    <h3>Reporting</h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincid unt ut laoreet dolore magna aliquam erat volutpat. </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div-->






                                        <div id="tabs">
                                            <ul class="process-model" role="tablist">
                                                <li class="active"><a href="#tabs-1" data-toggle="tab"><i class="material-icons">description</i>CAPAS</a></li>
                                                <li><a href="#tabs-2" data-toggle="tab"><i class="material-icons">description</i>LEYENDA</a></li>
                                                <li><a href="#tabs-3" data-toggle="tab"><i class="material-icons">find_in_page</i>MUNICIPIOS</a></li>
                                                <li><a href="#tabs-4" data-toggle="tab"><i class="material-icons">build</i>HERRAMIENTAS</a></li>
                                            </ul>
                                            <div id="tabs-1">

                                                <h2 class="subtitulos">Capas de información</h2>               
                                                <div id="SGCatastral" class="controlCapa caja" style="display:block; padding:-1%;">

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
                                                        <!--button onclick="drawLine()" class="btn btn-default" title="Distancia"><i class="material-icons" style="color:gray; font-size:.7em;">linear_scale</i></button-->
                                                        <!--button onclick="drawPolygon()" class="btn btn-default" title="Área"><i class="material-icons" style="color:gray; font-size:.7em;">format_shapes</i></button-->
                                                        <button onclick="dibjpavg()" class="btn btn-default" title="Área"><i class="material-icons" style="color:gray; font-size:.7em;">format_shapes</i></button>
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

                                                <!--h2 class="subtitulos">Altitud</h2>
                                                <div id="altitud">
                                                    <span class="span-4">Marca un punto en el mapa:</span>
                                                    <div>
                                                        <button onclick="Altitud()" id="altitud" class="btn btn-default" title="Inversa"><i class="material-icons" style="color:gray; font-size:.7em;">terrain</i></button>
                                                        <input id="altit" type="button" value="LIMPIAR///DESACTIVAR"  class="botonl" style="display:none;"/>      
                                                    </div>
                                                </div-->
                                            </div>

                                        </div>





                                        <div class="divControles2" id="divControles">
                                            <h2 class="subtitulos">Controles</h2>
                                            <label class="texto">Transparencia</label>
                                            <input id="range1" type="range" min="0" max="100" value="90" style="width:60%;" onchange="setOpacity(value)" />
                                            <button id="limpiar" name="limpiar" class="boton4">DESACTIVAR CAPAS</button><button id="home" name="home" class="boton4" onclick="home()" style="font-size:.2em; float:right; margin-right: 21%; padding-top:10px;"><i class="material-icons">home</i></button>


                                        </div>
                                    </div>
                                </div>




                                <div class="toggle">
                                    <i id="izquierda" class="material-icons toggleFlecha clickme" data-action="open" data-side="right">keyboard_arrow_left</i>
                                    <i id="derecha" style="display: none;" class="material-icons toggleFlecha clickme" data-action="close" data-side="right">keyboard_arrow_right</i>
                                </div>



                                <script type="text/javascript">
                                    $(".clickme").click(function () {
                                        $("#sgc").slideToggle("slow", function () {
                                            // Animation complete.
                                        }).css("display", "block");
                                    });
                                </script>

                                <!--script type="text/javascript">
                                    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            
                                        var href = $(e.target).attr('href');
                                        var $curr = $(".process-model  a[href='" + href + "']").parent();
            
                                        $('.process-model li').removeClass();
            
                                        $curr.addClass("active");
                                        $curr.prevAll().addClass("visited");
                                    });
                                </script-->   


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

                                            $(".sidebar.right").trigger("sidebar:open").css("display", "block");

                                        });

                                        $("#derecha").on("click", function () {
                                            $("#derecha").css("display", "none");
                                            $("#izquierda").css("display", "block");
                                            $(".toggle").css("right", "0");

                                            $(".sidebar.right").trigger("sidebar:close").css("display", "none");

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
                                        PDFObject.embed("pdf/" + valor + ".pdf", "#dialog");
                                    }</script>

                                <script type="text/javascript" src="js/jquerypp.custom.js"></script>
                                <script type="text/javascript" src="js/jquery.elastislide.js"></script>
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


















                            </div>







                        </div>



                        <div id="predios" style="display:none;">
                            <form id="alta_predio" name="alta_predio" method="post" action="#">

                                <fieldset>

                                    <legend>Clave Catastral<br>
                                        <div>
                                            <div style="float: left">Valor Catastral del Terreno: $ </div>
                                            <div id="fff" style="float: left"></div>
                                        </div>
                                    </legend>
                                    <ul>
                                        <li class="li_sgc"><label>Municipio:</label> 
                                        <li class="input_1i"><input type="text" class="input" id="mun" name="mun" maxlength="3" size="3"/>
                                            <input type="text" class="input" id="nom_mun" name="nom_mun" maxlength="50" size="30"/></li>

                                        <br><br>
                                    </ul>
                                    <ul>
                                        <li class="li_sgc_1"><label>Zona:</label></li>
                                        <li class="input_1i_1"><input type="text" class="input" id="zona" name="zona" maxlength="2" size="2"/></li>
                                        <li class="li_sgc_1"><label>Manzana:</label></li>
                                        <li class="li_sgc_1"><input type="text" class="input" id="mza" name="mza" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_1"><label>Predio:</label></li>
                                        <li class="li_sgc_1"><input type="text" class="input" id="predio" name="predio" maxlength="2" size="2" /></li><br><br>
                                    </ul>

                                </fieldset>

                                <fieldset>
                                    <legend>Localización</legend>
                                    <ul>
                                        <li class="li_sgc"><label>Localidad:</label></li>
                                        <li class="input_1i"><!--<input type="text" class="input" id="cve_loc" name="cve_loc" maxlength="3" size="3"/>-->
                                            <select id="localll" name="localll" class="select" style="width: 210px;"><option value="#">Selecciona Localidad</option><option value="A10">NINGUNA</option></select></li>
                                        <div id="lcl"></div>

                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc"><label>Zona origen:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="zona_o" name="zona_o" maxlength="3" size="3"/>
                                        </li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc"><label>Código de calle:</label></li>
                                        <li class="input_1i"><!--<input type="text" class="input" id="cod_calle" name="cod_calle" maxlength="4" size="4"/>
                                            <input type="text" class="input" id="nom_calle" name="nom_calle" maxlength="50" size="30"/>-->
                                            <select id="nom_calle" name="nom_calle" class="select" style="width: 210px;">
                                                <option value="#">Selecciona opcion</option>
                                                <option value="A11">NINGUNA</option>
                                            </select>                                            
                                            <div id="lcl2"></div>
                                        </li>
                                    </ul>
                                    <br><br>

                                    <ul>
                                        <li class="li_sgc"><label>Tipo de vialidad:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="cve_t_vial" name="cve_t_vial" maxlength="1" size="1"/>
                                            <input type="text" class="input" id="nom_vialidad" name="nom_vialidad" maxlength="50" size="30"/>
                                        </li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc_1"><label>Número exterior:</label></li>
                                        <li class="li_sgc_1"> <input type="text" class="input" id="n_ext" name="n_ext" maxlength="5" size="5"/></li>
                                        <li class="li_sgc_1"><label>Código postal:</label></li>
                                        <li class="li_sgc_1"><input type="text" class="input" id="cod_pos" name="cod_pos" maxlength="5" size="5"/></li>

                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc"><label>Colonia:</label></li>
                                        <li class="input_1i">
                                            <!-- <input type="text" class="input" id="n_col" name="n_col" maxlength="3" size="3"/>
                                             <input type="text" class="input" id="nom_col" name="nom_col" maxlength="50" size="30"/>-->
                                            <select id="nom_col" name="nom_col" class="select" style="width: 200px;"><option value="#">Selecciona opcion</option>
                                                <option value="A12">NINGUNA</option>
                                            </select>                                           
                                            <div id="lcl3"></div>
                                        </li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc"><label>Entre calle:</label></li>
                                        <li class="input_1i">
                                            <!--<input type="text" class="input" id="nom_en_calle" name="nom_en_calle" maxlength="50" size="30"/>-->
                                            <select id="nom_en_calle" name="nom_en_calle" class="select" style="width: 200px;"><option value="#">Selecciona opcion</option>
                                                <option value="A13">NINGUNA</option>
                                            </select>
                                            <div id="lcl4"></div>
                                        </li>
                                    </ul>
                                    <br><br>

                                    <ul>
                                        <li class="li_sgc"><label>y calle:</label></li>
                                        <li class="input_1i">
                                            <!--- <input type="text" class="input" id="nom_en_calle2" name="nom_en_calle2" maxlength="50" size="30"/>-->
                                            <select id="nom_en_calle2" name="nom_en_calle2" class="select" style="width: 200px;"><option value="#">Selecciona opcion</option>
                                                <option value="A14">NINGUNA</option>
                                            </select>
                                            <div id="lcl5"></div>
                                        </li>
                                    </ul>
                                    <br><br>

                                </fieldset>

                                <fieldset> 
                                    <legend></legend> 
                                    <ul>
                                        <li class="li_sgc"><label>Regimen de propiedad:</label></li>

                                        <select id="regimp" name="regimp" class="select" style="width: 210px;">
                                            <option value="#">Selecciona opcion</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                            <option value="7">7</option>
                                            <option value="8">8</option>
                                            <!--<option value="0">0</option>-->
                                        </select>
                                        <input type="text" class="input" id="tip_reg" name="tip_reg" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br>                

                                </fieldset>

                                <fieldset>
                                    <legend>Terreno</legend>

                                    <ul>
                                        <li class="li_sgc"><label>Superficie total:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="sup_total" name="sup_total" maxlength="20" size="20"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc"><label>Frente:</label></li> 
                                        <li class="input_1i"><input type="text" class="input" id="frente" name="frente" maxlength="20" size="20"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc"><label>fondo:</label></li>
                                        <input type="text" class="input" id="fondo" name="fondo" maxlength="20" size="20"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc"><label>Altura:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="altura" name="altura" maxlength="20" size="20"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc"><label>Área inscrita:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="ar_ins" name="ar_ins" maxlength="20" size="20"/></li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc"><label>Posición:</label></li>
                                        <select id="posicion" name="posicion" class="select" style="width: 210px;">
                                            <option value="#">Selecciona opcion</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                        </select>


                                        <input type="text" class="input" id="pos" name="pos" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br>
                                    <ul>

                                        <li class="li_sgc"><label>Forma de predio:</label></li>
                                        <select id="formpred" name="formpred" class="select" style="width: 210px;">
                                            <option value="#">Selecciona opcion</option>
                                            <option value="R">Regular</option>
                                            <option value="IR">Irregular</option>                                            
                                        </select>


                                        <!--input type="text" class="input" id="pos" name="pos" maxlength="50" size="30"/-->
                                    </ul>
                                    <br><br>

                                    <ul>
                                        <li class="li_sgc"><label>Sup. de aprovechamiento:</label></li>
                                        <li class="input_1i"><input class="input" type="text" id="sup_apro" name="sup_apro" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>


                                </fieldset>

                                <fieldset>

                                    <legend>Unicamente Condominios</legend>
                                    <ul>
                                        <li class="li_sgc"><label>Sup. terreno comun:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="sup_t_c" name="sup_t_c" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>




                                </fieldset>


                                <fieldset>


                                    <legend>Movimiento</legend>
                                    <ul>
                                        <li class="li_sgc_2"><label>Motivo:</label></li>
                                        <li class="input_1i_2"><input type="text" class="input" id="motivo" name="motivo" maxlength="50" size="30"/></li>
                                        <li class="li_sgc_2"><label>fecha:</label></li>
                                        <li class="input_1i_2"><input type="text" class="input" id="fecha" name="fecha" maxlength="10" size="10"/></li>
                                    </ul>
                                    <br><br>


                                    <input type="reset" />
                                    <input type="button" id="btnEnviarform" name="btnEnviarform" value="Enviar el formulario" />

                                </fieldset>
                            </form>


                        </div>

                        <div id="propietarios" style="display:none;">
                            <form id="alta_propietario" name="alta_propietario" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral<br>
                                        <div>
                                            <div style="float: left">Valor Catastral del Terreno: $ </div>
                                            <div id="fff" style="float: left"></div>
                                        </div>
                                    </legend>
                                    <ul>
                                        <li class="li_sgc_1"><label>Municipio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="mun2" name="mun2" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_3"><label>Zona:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="zona2" name="zona2" maxlength="2" size="2"/></li>
                                        <li class="li_sgc_3"><label>Manzana:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="mza2" name="mza2" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_3"><label>Predio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="predio2" name="predio2" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Edificio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="edificio" name="edificio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Departamento:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="depa" name="depa" maxlength="4" size="4" /></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Número Interior:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="n_int" name="n_int" maxlength="4" size="4" /></li>
                                    </ul>
                                    <br><br>               


                                </fieldset>

                                <fieldset>
                                    <legend>Propietario</legend>
                                    <ul>
                                        <li class="li_sgc_1"><label>Apellido paterno:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="ap_pat" name="ap_pat" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Apellido materno:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="ap_mat" name="ap_mat" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label> Nombre(s):</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="nom_p" name="nom_p" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>


                                    <ul>
                                        <li class="li_sgc_1"><label>Personas Jurídicas Colectivas:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="per_col" name="per_col" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>RFC:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="rfc" name="rfc" maxlength="13" size="13"/>
                                            <!--input type="text" class="input" id="u_rfc" name="u_rfc" maxlength="50" size="30"/></li-->
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>CURP:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="curp" name="curp" maxlength="18" size="18"/></li>
                                    </ul>
                                    <br><br>                              



                                </fieldset>

                                <fieldset>
                                    <legend>Domicilio Fiscal</legend>
                                    <ul>
                                        <li class="input_1i"><input type="text" class="input" id="dom_fis" name="dom_fis" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>              

                                </fieldset>



                                <fieldset> 
                                    <legend></legend> 
                                    <ul>
                                        <li class="li_sgc_1"><label>Destino del Predio:</label></li>
                                        <select id="destinpre" name="destinpre" class="select">
                                            <option value="#">Selecciona opcion</option>


                                        </select>
                                        <input type="text" class="input" id="des_pre" name="des_pre" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br> 
                                    <ul>
                                        <li class="li_sgc_1"><label>Uso Específico:</label></li>
                                        <select id="usespecf" name="usespecf" class="select">
                                            <option value="#">Selecciona opcion</option>


                                        </select>
                                        <input type="text" class="input" id="uso_esp" name="uso_es" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br> 

                                    <input type="radio" id="opc1" name="grupo" /> Baldío  <br><br>
                                    <input type="radio" id="opc2" name="grupo" /> Construido <br><br>


                                </fieldset>



                                <!-- <fieldset>
                                     <legend>Únicamente en caso de condominio</legend>
                                     <ul>
                                         <li class="li_sgc_1"><label> Superficie terreno propio:</label></li>
                                         <li class="input_1i"><input type="text" class="input" id="sup_t_prop" name="sup_t_prop" maxlength="20" size="20"/></li>
                                     </ul>
                                     <br><br><br><br>
                                     <ul>
                                         <li class="li_sgc_1"><label>% en condominio:</label></li>
                                         <li class="input_1i"><input type="text" class="input" id="porsen_con" name="porsen_con" maxlength="20" size="20"/></li>
                                     </ul>
                                     <br><br>      
 
 
 
                                 </fieldset>-->

                                <!-- <fieldset>
                                     <legend></legend>
                                     <ul>
                                         <li class="li_sgc_1"><label>Suma terreno propio:</label></li>
                                         <li class="input_1i"><input type="text" class="input" id="sum_t_prop" name="sum_t_prop" maxlength="20" size="20"/></li>
                                     </ul>
                                     <br><br>
 
                                     <ul>
 
                                         <li class="li_sgc_1"><label>Suma de indivisos:</label></li>
                                         <li class="input_1i"><input type="text" class="input" id="sum_ind" name="sum_ind" maxlength="20" size="20"/> % </li>
                                     </ul>
                                     <br><br>              
 
 
                                 </fieldset>-->


                                <fieldset>
                                    <!-- <legend>Únicamente para predios construidos</legend>
                                     <ul>
                                         <li class="li_sgc_1"><label>Comunes </label></li>
                                         <li class="li_sgc_1"><label>Propias</label></li>
                                     </ul>-->
                                    <br><br>


                                    <input type="reset" />
                                    <input type="button" id="btnEnviar2" name="btnEnviar2" value="Enviar el formulario" />
                                    <input type="button" id="eventocvtt" name="eventocvtt" value="CALCULAR CVT" />

                                </fieldset>
                            </form>


                        </div>

                        <div id="historico" style="display:none;">
                            <form id="alta_historico" name="alta_historico" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral</legend>
                                    <ul>
                                        <li class="li_sgc"><label>Municipio:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="mun" name="mun" maxlength="3" size="3"/>
                                            <input type="text" class="input" id="nom_mun" name="nom_mun" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc_1"><label>Zona:</label></li> 
                                        <li class="input_1i_3"> <input type="text" class="input" id="zona" name="zona" maxlength="2" size="2"/></li>
                                        <li class="li_sgc_3"><label>Manzana:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="mza" name="mza" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_3"><label>Predio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="predio" name="predio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Edificio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="edificio" name="edificio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Departamento:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="depa" name="depa" maxlength="4" size="4" /></li>
                                    </ul>
                                    <br><br>



                                </fieldset>        




                                <fieldset> 
                                    <legend></legend>  

                                    <ul>

                                        <li class="li_sgc_2"><label>Folio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="folio" name="folio" maxlength="6" size="6"/></li>
                                        <li class="li_sgc_2"><label>Fecha del movimiento:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="fecha_mov" name="fecha_mov" maxlength="10" size="10"/></li>
                                    </ul>
                                    <br><br> 
                                    <ul>
                                        <li class="li_sgc"><label>Fundamento:</label></li> 
                                        <li class="input_1i"><input type="text" class="input" id="fun" name="fun" maxlength="50" size="50"/></li>
                                    </ul>
                                    <br><br> 

                                </fieldset>

                                <fieldset> 
                                    <legend></legend> 

                                    <ul>
                                        <li class="li_sgc"><label>Clave anterior </label></li>
                                        <input type="checkbox" class="inputsgc" id="cve_ant" name="cve_ant"/> 
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc"><label>Municipio:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="mun_ant" name="mun_ant" maxlength="3" size="3"/>
                                            <input type="text" class="input" id="nom_mun_ant" name="nom_mun_ant" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc_1"><label>Zona:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="zona_ant" name="zona_ant" maxlength="2" size="2"/></li>
                                        <li class="li_sgc_3"><label>Manzana:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="mza_ant" name="mza_ant" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_3"><label>Predio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="predio_ant" name="predio_ant" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Edificio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="edificio_ant" name="edificio_ant" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Departamento:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="depa_ant" name="depa_ant" maxlength="4" size="4" /></li>
                                    </ul>
                                    <br><br>           




                                </fieldset>








                                <fieldset>                            

                                    <input type="reset" />
                                    <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar el formulario" />

                                </fieldset>
                            </form>



                        </div>


                        <div id="propias" style="display:none;">
                            <form id="alta_c_propias" name="alta_c_propias" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral</legend>
                                    <ul>
                                        <li class="li_sgc"><label>Municipio:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="mun" name="mun" maxlength="3" size="3"/>
                                            <input type="text" class="input" id="nom_mun" name="nom_mun" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>

                                    <ul>

                                        <li class="li_sgc_1"><label>Zona:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="zona" name="zona" maxlength="2" size="2"/></li>
                                        <li class="li_sgc_3"><label>Manzana:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="mza" name="mza" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_3"><label>Predio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="predio" name="predio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Edificio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="edificio" name="edificio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Departamento:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="depa" name="depa" maxlength="4" size="4" /></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Unidad constructiva:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="un_cons" name="u_cons" maxlength="3" size="3" /></li>
                                    </ul>
                                    <br><br>               


                                </fieldset>        




                                <fieldset> 
                                    <legend>Construcción</legend> 
                                    <ul>
                                        <li class="li_sgc_1"><label>Uso:</label></li>
                                        <select class="select">
                                            <option value="H">H</option>
                                            <option value="I">I</option>
                                            <option value="E">E</option>
                                            <option value="C">C</option>
                                            <option value="Q">Q</option>

                                        </select>
                                        <input type="text" class="input" id="uso" name="uso" maxlength="30" size="30"/>
                                        <input type="text" class="input" id="uso_comp" name="uso_comp" maxlength="40" size="40"/>
                                    </ul>
                                    <br><br> 

                                    <ul>

                                        <li class="li_sgc_1"><label>Clase: </label></li>
                                        <select class="select">
                                            <option value="A">A</option>
                                            <option value="B">B</option>
                                            <option value="C">C</option>
                                            <option value="D">D</option>
                                            <option value="E">E</option>
                                            <option value="F">F</option>
                                            <option value="G">G</option>

                                        </select>
                                        <input type="text" class="input" id="uso_clase" name="uso_clase" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Categoría:</label></li>
                                        <select class="select">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>

                                        </select>
                                        <input type="text" class="input" id="uso_cat" name="uso_cat" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br> 
                                    <ul>
                                        <li class="li_sgc_1"><label>Superficie:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="sup" name="sup" maxlength="20" size="20"/> m2</li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Año de construcción o últimaremodelación:</label></li>
                                        <li class="input_1i"><input type="date" class="input" id="fecha_cons" name="fecha_cons"/></li>
                                    </ul>
                                    <br><br><br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Grado de conservación:</label></li>
                                        <select class="select">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>

                                        </select>

                                        <input type="text" class="input" id="grad_con" name="grad_con" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Total de niveles existentes en laconstrucción:</label></li> 
                                        <li class="input_1i"><input type="text" class="input" id="tot_n" name="tot_n" maxlength="2" size="2"/></li>
                                    </ul>
                                    <br><br>


                                </fieldset>

                                <fieldset>                            

                                    <input type="reset" />
                                    <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar el formulario" />

                                </fieldset>
                            </form>

                        </div>

                        <div id="comunes" style="display:none;">
                            <form id="alta_c_comunes" name="alta_c_comunes" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral</legend>
                                    <ul>
                                        <li class="li_sgc"><label>Municipio:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="mun" name="mun" maxlength="3" size="3"/>
                                            <input type="text" class="input" id="nom_mun" name="nom_mun" maxlength="50" size="30"/></li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Zona:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="zona" name="zona" maxlength="2" size="2"/></li>
                                        <li class="li_sgc_3"><label>Manzana:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="mza" name="mza" maxlength="3" size="3"/></li>
                                        <li class="li_sgc_3"><label>Predio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="predio" name="predio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Edificio:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="edificio" name="edificio" maxlength="2" size="2" /></li>
                                        <li class="li_sgc_3"><label>Departamento:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="depa" name="depa" maxlength="4" size="4" /></li>
                                    </ul>
                                    <br><br>

                                    <ul>
                                        <li class="li_sgc_1"><label>Unidad constructiva:</label></li>
                                        <li class="input_1i_3"><input type="text" class="input" id="un_cons" name="u_cons" maxlength="3" size="3" /></li>
                                    </ul>

                                    <br><br>               


                                </fieldset>        


                                <fieldset> 
                                    <legend>Construcción</legend>  
                                    <ul>
                                        <li class="li_sgc_1"><label>Uso:</label></li>
                                        <select class="select">
                                            <option value="H">H</option>
                                            <option value="I">I</option>
                                            <option value="E">E</option>
                                            <option value="C">C</option>
                                            <option value="Q">Q</option>

                                        </select>
                                        <input type="text" class="input" id="uso" name="uso" maxlength="30" size="30"/>
                                        <input type="text" class="input" id="uso_comp" name="uso_comp" maxlength="40" size="40"/>
                                    </ul>
                                    <br><br> 
                                    <ul>
                                        <li class="li_sgc_1"><label>Clase:</label></li>
                                        <select class="select">
                                            <option value="A">A</option>
                                            <option value="B">B</option>
                                            <option value="C">C</option>
                                            <option value="D">D</option>
                                            <option value="E">E</option>
                                            <option value="F">F</option>
                                            <option value="G">G</option>

                                        </select>
                                        <input type="text" class="input" id="uso_clase" name="uso_clase" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Categoría:</label></li>
                                        <select class="select">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>

                                        </select>
                                        <input type="text" class="input" id="uso_cat" name="uso_cat" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br> 
                                    <ul>
                                        <li class="li_sgc_1"><label>Superficie:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="sup" name="sup" maxlength="20" size="20"/> m2</li>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Año de construcción o últimaremodelación:</label></li>
                                        <li class="input_1i"><input type="date" class="input" id="fecha_cons" name="fecha_cons"/></li>
                                    </ul>
                                    <br><br><br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Grado de conservación:</label></li>
                                        <select class="select">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>

                                        </select>

                                        <input type="text" class="input" id="grad_con" name="grad_con" maxlength="50" size="30"/>
                                    </ul>
                                    <br><br>
                                    <ul>
                                        <li class="li_sgc_1"><label>Total de niveles existentes en laconstrucción:</label></li>
                                        <li class="input_1i"><input type="text" class="input" id="tot_n" name="tot_n" maxlength="2" size="2"/></li>
                                    </ul>
                                    <br><br>







                                </fieldset>








                                <fieldset>                            

                                    <input type="reset" />
                                    <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar el formulario" />

                                </fieldset>
                            </form>



                        </div>

                        <div id="documentos" style="display:none;">
                            <h1>Documentos</h1>
                            <form id="frmDcto" method="POST" enctype="multipart/form-data"  modelAttribute="FDocumento">
                                <!--input type="hidden" name="cve_cat" id="cve_cat" value="0680110307000000" /-->
                                <input type="hidden" name="cve_cat" id="cve_cat" />

                                <input type="hidden" name="id_doc" id="id_doc" />
                                <div id="docArchivo"></div> 
                            </form>                            
                            <table class="table table-striped">
                                <thead>
                                <th style="width: 50%;"></th>
                                <th style="width: 50%;"></th>
                                </thead>
                                <tbody>
                                    <c:if test="${not empty documentos}">
                                        <c:forEach var="doctos" items="${documentos}">
                                            <tr>
                                                <td>${doctos.documento}</td>
                                                <td><input class="docto" type="file" id="${doctos.id}" name="documento"/></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
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







            </div>
        </div>

    </body>
</html>
