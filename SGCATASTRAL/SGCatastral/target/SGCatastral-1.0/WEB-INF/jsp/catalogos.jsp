<%-- 
    Document   : catalogos
    Created on : 1/11/2017, 05:48:53 PM
    Author     : Fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catálogos</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/style_sgc.css" rel="stylesheet" type="text/css" >
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/sgc_catalogos.js"></script>

        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
        <script>
            var myApp = angular.module("app", []);
            myApp.factory("jsonObject", function($http){
            return $http.get("cat_municipios.do");
            });
            myApp.controller("mainCtrl", function($scope, jsonObject){

            jsonObject.success(function(data){
            console.log(data.Municipios);
            $scope.mun = data.Municipios;
            });
            $scope.currentPage = 0;
            $scope.pageSize = 20;
            $scope.pages = [];
            $scope.configPages = function() {
            $scope.pages.length = 0;
            var ini = $scope.currentPage - 4;
            var fin = $scope.currentPage + 5;
            if (ini < 1) {
            ini = 1;
            if (Math.ceil($scope.mun.length / $scope.pageSize) > 10)
                    fin = 10;
            else
                    fin = Math.ceil($scope.mun.length / $scope.pageSize);
            } else {
            if (ini >= Math.ceil($scope.mun.length / $scope.pageSize) - 10) {
            ini = Math.ceil($scope.mun.length / $scope.pageSize) - 10;
            fin = Math.ceil($scope.mun.length / $scope.pageSize);
            }
            }
            if (ini < 1) ini = 1;
            for (var i = ini; i <= fin; i++) {
            $scope.pages.push({
            no: i
            });
            }

            if ($scope.currentPage >= $scope.pages.length)
                    $scope.currentPage = $scope.pages.length - 1;
            };
            $scope.setPage = function(index) {
            $scope.currentPage = index - 1;
            };
            });
            myApp.filter('startFromGrid', function() {
            return function(input, start) {
            start = + start;
            return input.slice(start);
            };
            });
        </script>



    </head>




    <body ng-app="app" ng-controller="mainCtrl" ng-cloack>             








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
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#barranavegacion"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                            </div>
                            <div class="collapse navbar-collapse" id="barranavegacion">
                                <ul class="links nav navbar-nav" id="main-menu">
                                    <li class="menu-291 first active"><a class="active" href="inicio.do">Inicio</a></li>
                                    <!--li class="menu-1289"><a href="acercade.do">Acerca de SGC</a></li-->
                                    <li class="menu-1290"><a href="catalogos.do">Catálogos</a></li>
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
                    <div id="menu_izq">
                        <h2 class="nomh2">Catálogos</h2>
                        <div class="content_menu_izq">
                            <ul class="menu_ul">
                                <li class="menu_li"><a href="catalogos.do">Catálogos</a></li>                           

                            </ul>  
                        </div>
                    </div>
                    <div id="acercade">

                        <h3 class="nomh3">Catálogos</h3>

                        <div id="textos">




                            <p>



                            </p>

                            <form>
                                Cadena a buscar <input id="searchTerm" type="text" onkeyup="doSearch()" />
                            </form>


                            <table id="cat_mun" class="table table-hover"  ng-init="configPages()">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Clave</th>
                                        <th>Perímetro</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="mun in mun| startFromGrid: currentPage * pageSize | limitTo: pageSize">
                                        <td>{{mun.ID}}</td>
                                        <td>{{mun.Clave}}</td>
                                        <td>{{mun.Perimetro}}</td>

                                    </tr>                                    

                                </tbody>
                            </table>  

                            <!--ul class="pagination pagination-lg pager" id="myPager"></ul-->

                            <div class="btn-group">
                                <button type="button" class="btn btn-default" ng-disabled="currentPage == 0" ng-click="currentPage = currentPage - 1">&laquo;</button>
                                <button type="button" class="btn btn-default" ng-disabled="currentPage == page.no - 1" ng-click="setPage(page.no)" ng-repeat="page in pages">{{page.no}}</button>
                                <button type="button" class="btn btn-default" ng-disabled="currentPage >= mun.length / pageSize - 1" , ng-click="currentPage = currentPage + 1">&raquo;</button>
                            </div>

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




    </body>
</html>
