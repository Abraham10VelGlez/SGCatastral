<%-- 
    Document   : inc_padron_predios
    Created on : 8/11/2017, 04:51:54 PM
    Author     : Fernando
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incorporación al padrón</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/style_sgc.css" rel="stylesheet" type="text/css" >
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script src="js/inc_padron_predios.js"></script>
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
                    <div id="menu_izq">
                        <h2 class="nomh2">Acerca de SGC</h2>
                        <div class="content_menu_izq">
                            <ul class="menu_ul">
                                <li class="menu_li"><a href="acercade.do">Acerca de SGC</a></li>                           

                            </ul>  
                        </div>
                    </div>
                    <div id="contenidos">

                        <h3 class="nomh3">Incorporación al padrón</h3>

                        <div id="textos">


                            <p>



                            </p>

                        </div>


                        <div id="contenedor_alta">

                            <input id="pes_1" type="radio" name="radio_alt" class="pes_selector_1" checked="checked" value="pes_1"/>
                            <label for="pes_1" class="pes_label_1">visor</label>
                            
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





                        </div>  
                        
                        
                        <div id="visor_d" style="display:block;">
                           
                        

                        </div>
                        
                        

                        <div id="predios" style="display:none;">
                            <form id="alta_predio" name="alta_predio" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral</legend>
                                    Municipio: <input type="text" id="mun" name="mun" maxlength="3" size="3"/>
                                    <input type="text" id="nom_mun" name="nom_mun" maxlength="50" size="30"/><br><br>

                                    Zona: <input type="text" id="zona" name="zona" maxlength="2" size="2"/>
                                    Manzana: <input type="text" id="mza" name="mza" maxlength="3" size="3"/>
                                    Predio: <input type="text" id="predio" name="predio" maxlength="2" size="2" /><br><br>


                                </fieldset>

                                <fieldset>
                                    <legend>Localización</legend>
                                    Localidad: <input type="text" id="cve_loc" name="cve_loc" maxlength="3" size="3"/>
                                    <input type="text" id="loc" name="loc" maxlength="50" size="30"/><br><br>
                                    Zona origen: <input type="text" id="zona_o" name="zona_o" maxlength="3" size="3"/><br><br>

                                    Código de calle: <input type="text" id="cod_calle" name="cod_calle" maxlength="4" size="4"/>
                                    <input type="text" id="nom_calle" name="nom_calle" maxlength="50" size="30"/><br><br>

                                    Tipo de vialidad: <input type="text" id="cve_t_vial" name="cve_t_vial" maxlength="1" size="1"/>
                                    <input type="text" id="nom_vialidad" name="nom_vialidad" maxlength="50" size="30"/><br><br>

                                    Número exterior: <input type="text" id="n_ext" name="n_ext" maxlength="5" size="5"/>
                                    Código postal: <input type="text" id="cod_pos" name="cod_pos" maxlength="5" size="5"/><br><br>

                                    Colonia: <input type="text" id="n_col" name="n_col" maxlength="3" size="3"/>
                                    <input type="text" id="nom_col" name="nom_col" maxlength="50" size="30"/><br><br>

                                    Entre calle: <input type="text" id="nom_en_calle" name="nom_en_calle" maxlength="50" size="30"/><br><br>
                                    y calle: <input type="text" id="nom_en_calle2" name="nom_en_calle2" maxlength="50" size="30"/><br><br>

                                </fieldset>

                                <fieldset> 
                                    <legend></legend>               
                                    Regimen de propiedad:   <select>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="0">0</option>
                                    </select>
                                    <input type="text" id="tip_reg" name="tip_reg" maxlength="50" size="30"/><br><br>                

                                </fieldset>

                                <fieldset>
                                    <legend>Terreno</legend>
                                    Superficie total: <input type="text" id="sup_total" name="sup_total" maxlength="20" size="20"/><br><br>
                                    Frente: <input type="text" id="frente" name="frente" maxlength="20" size="20"/><br><br>

                                    fondo: <input type="text" id="fondo" name="fondo" maxlength="20" size="20"/><br><br>
                                    Altura: <input type="text" id="altura" name="altura" maxlength="20" size="20"/><br><br>

                                    Área inscrita: <input type="text" id="ar_ins" name="ar_ins" maxlength="20" size="20"/><br><br>

                                    Posición:   <select>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>                                           
                                    </select>


                                    <input type="text" id="pos" name="pos" maxlength="50" size="30"/><br><br>


                                    Superficie de aprovechamineto: <input type="text" id="sup_apro" name="sup_apro" maxlength="50" size="30"/><br><br>


                                </fieldset>

                                <fieldset>
                                    <legend>Unicamente Condominios</legend>
                                    Sup. terreno comun: <input type="text" id="sup_t_c" name="sup_t_c" maxlength="50" size="30"/><br><br>




                                </fieldset>


                                <fieldset>
                                    <legend>Movimiento</legend>
                                    Motivo: <input type="text" id="motivo" name="motivo" maxlength="50" size="30"/>
                                    fecha: <input type="text" id="fecha" name="fecha" maxlength="10" size="10"/><br><br>


                                    <input type="reset" />
                                    <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar el formulario" />

                                </fieldset>
                            </form>


                        </div>

                        <div id="propietarios" style="display:none;">
                            <form id="alta_propietario" name="alta_propietario" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral</legend>
                                    Municipio: <input type="text" id="mun" name="mun" maxlength="3" size="3"/>
                                    Zona: <input type="text" id="zona" name="zona" maxlength="2" size="2"/>
                                    Manzana: <input type="text" id="mza" name="mza" maxlength="3" size="3"/>
                                    Predio: <input type="text" id="predio" name="predio" maxlength="2" size="2" />
                                    Edificio: <input type="text" id="edificio" name="edificio" maxlength="2" size="2" />
                                    Departamento: <input type="text" id="depa" name="depa" maxlength="4" size="4" /><br><br>
                                    Número Interior: <input type="text" id="n_int" name="n_int" maxlength="4" size="4" /><br><br>               


                                </fieldset>

                                <fieldset>
                                    <legend>Propietario</legend>
                                    Apellido paterno: <input type="text" id="ap_pat" name="ap_pat" maxlength="50" size="30"/><br><br>
                                    Apellido materno: <input type="text" id="ap_mat" name="ap_mat" maxlength="50" size="30"/><br><br>
                                    Nombre(s): <input type="text" id="nom_p" name="nom_p" maxlength="50" size="30"/><br><br>
                                    Personas Jurídicas Colectivas: <input type="text" id="per_col" name="per_col" maxlength="50" size="30"/><br><br>
                                    RFC: <input type="text" id="rfc" name="rfc" maxlength="13" size="13"/>
                                    <input type="text" id="u_rfc" name="u_rfc" maxlength="50" size="30"/><br><br>

                                    CURP: <input type="text" id="curp" name="curp" maxlength="18" size="18"/><br><br>                              



                                </fieldset>

                                <fieldset>
                                    <legend>Domicilio Fiscal</legend>
                                    <input type="text" id="dom_fis" name="som_fis" maxlength="50" size="30"/><br><br>              

                                </fieldset>



                                <fieldset> 
                                    <legend></legend>               
                                    Destino del Predio:   <select>
                                        <option value="A">A</option>
                                        <option value="C">C</option>
                                        <option value="H">H</option>
                                        <option value="I">I</option>
                                        <option value="Q">Q</option>

                                    </select>
                                    <input type="text" id="des_pre" name="des_pre" maxlength="50" size="30"/><br><br> 

                                    Uso Específico:   <select>
                                        <option value="A">A</option>
                                        <option value="C">C</option>
                                        <option value="H">H</option>
                                        <option value="I">I</option>
                                        <option value="Q">Q</option>

                                    </select>
                                    <input type="text" id="uso_esp" name="uso_es" maxlength="50" size="30"/><br><br> 

                                    <input type="radio" id="opc1" name="grupo" value="A" /> Baldío  <br><br>
                                    <input type="radio" id="opc2" name="grupo" value="B" checked /> Construido <br><br>


                                </fieldset>



                                <fieldset>
                                    <legend>Únicamente en caso de condominio</legend>
                                    Superficie terreno propio: <input type="text" id="sup_t_prop" name="sup_t_prop" maxlength="20" size="20"/><br><br>
                                    % en condominio: <input type="text" id="porsen_con" name="porsen_con" maxlength="20" size="20"/><br><br>      



                                </fieldset>

                                <fieldset>
                                    <legend></legend>
                                    Suma terreno propio: <input type="text" id="sum_t_prop" name="sum_t_prop" maxlength="20" size="20"/><br><br>
                                    Suma de indivisos: <input type="text" id="sum_ind" name="sum_ind" maxlength="20" size="20"/> % <br><br>              


                                </fieldset>


                                <fieldset>
                                    <legend>Únicamente para predios construidos</legend>
                                    Comunes 
                                    Propias <br><br>


                                    <input type="reset" />
                                    <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar el formulario" />

                                </fieldset>
                            </form>


                        </div>

                        <div id="historico" style="display:none;">
                            <form id="alta_historico" name="alta_historico" method="post" action="#">

                                <fieldset>
                                    <legend>Clave Catastral</legend>
                                    Municipio: <input type="text" id="mun" name="mun" maxlength="3" size="3"/>
                                    <input type="text" id="nom_mun" name="nom_mun" maxlength="50" size="30"/><br><br>

                                    Zona: <input type="text" id="zona" name="zona" maxlength="2" size="2"/>
                                    Manzana: <input type="text" id="mza" name="mza" maxlength="3" size="3"/>
                                    Predio: <input type="text" id="predio" name="predio" maxlength="2" size="2" />
                                    Edificio: <input type="text" id="edificio" name="edificio" maxlength="2" size="2" />
                                    Departamento: <input type="text" id="depa" name="depa" maxlength="4" size="4" /><br><br>



                                </fieldset>        




                                <fieldset> 
                                    <legend></legend>               

                                    Folio: <input type="text" id="folio" name="folio" maxlength="6" size="6"/>
                                    Fecha del movimiento: <input type="text" id="fecha_mov" name="fecha_mov" maxlength="10" size="10"/><br><br> 
                                    Fundamento: <input type="text" id="fun" name="fun" maxlength="50" size="50"/><br><br> 

                                </fieldset>

                                <fieldset> 
                                    <legend></legend> 

                                    <input type="checkbox" id="cve_ant" name="cve_ant"/> Clave anterior <br><br>

                                    Municipio: <input type="text" id="mun_ant" name="mun_ant" maxlength="3" size="3"/>
                                    <input type="text" id="nom_mun_ant" name="nom_mun_ant" maxlength="50" size="30"/><br><br>

                                    Zona: <input type="text" id="zona_ant" name="zona_ant" maxlength="2" size="2"/>
                                    Manzana: <input type="text" id="mza_ant" name="mza_ant" maxlength="3" size="3"/>
                                    Predio: <input type="text" id="predio_ant" name="predio_ant" maxlength="2" size="2" />
                                    Edificio: <input type="text" id="edificio_ant" name="edificio_ant" maxlength="2" size="2" />
                                    Departamento: <input type="text" id="depa_ant" name="depa_ant" maxlength="4" size="4" /><br><br>           




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
                                    Municipio: <input type="text" id="mun" name="mun" maxlength="3" size="3"/>
                                    <input type="text" id="nom_mun" name="nom_mun" maxlength="50" size="30"/><br><br>

                                    Zona: <input type="text" id="zona" name="zona" maxlength="2" size="2"/>
                                    Manzana: <input type="text" id="mza" name="mza" maxlength="3" size="3"/>
                                    Predio: <input type="text" id="predio" name="predio" maxlength="2" size="2" />
                                    Edificio: <input type="text" id="edificio" name="edificio" maxlength="2" size="2" />
                                    Departamento: <input type="text" id="depa" name="depa" maxlength="4" size="4" /><br><br>
                                    Unidad constructiva: <input type="text" id="un_cons" name="u_cons" maxlength="3" size="3" /><br><br>               


                                </fieldset>        




                                <fieldset> 
                                    <legend>Construcción</legend>               
                                    Uso:   <select>
                                        <option value="H">H</option>
                                        <option value="I">I</option>
                                        <option value="E">E</option>
                                        <option value="C">C</option>
                                        <option value="Q">Q</option>

                                    </select>
                                    <input type="text" id="uso" name="uso" maxlength="30" size="30"/>
                                    <input type="text" id="uso_comp" name="uso_comp" maxlength="40" size="40"/><br><br> 

                                    Clase:   <select>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                        <option value="G">G</option>

                                    </select>
                                    <input type="text" id="uso_clase" name="uso_clase" maxlength="50" size="30"/><br><br>

                                    Categoría:<select>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>

                                    </select>
                                    <input type="text" id="uso_cat" name="uso_cat" maxlength="50" size="30"/><br><br> 

                                    Superficie: <input type="text" id="sup" name="sup" maxlength="20" size="20"/> m2<br><br>
                                    Año de construcción o últimaremodelación: <input type="date" id="fecha_cons" name="fecha_cons"/><br><br>

                                    Grado de conservación: <select>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>

                                    </select>

                                    <input type="text" id="grad_con" name="grad_con" maxlength="50" size="30"/><br><br>
                                    Total de niveles existentes en laconstrucción: <input type="text" id="tot_n" name="tot_n" maxlength="2" size="2"/><br><br>







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
                                    Municipio: <input type="text" id="mun" name="mun" maxlength="3" size="3"/>
                                    <input type="text" id="nom_mun" name="nom_mun" maxlength="50" size="30"/><br><br>

                                    Zona: <input type="text" id="zona" name="zona" maxlength="2" size="2"/>
                                    Manzana: <input type="text" id="mza" name="mza" maxlength="3" size="3"/>
                                    Predio: <input type="text" id="predio" name="predio" maxlength="2" size="2" />
                                    Edificio: <input type="text" id="edificio" name="edificio" maxlength="2" size="2" />
                                    Departamento: <input type="text" id="depa" name="depa" maxlength="4" size="4" /><br><br>
                                    Unidad constructiva: <input type="text" id="un_cons" name="u_cons" maxlength="3" size="3" /><br><br>               


                                </fieldset>        




                                <fieldset> 
                                    <legend>Construcción</legend>               
                                    Uso:   <select>
                                        <option value="H">H</option>
                                        <option value="I">I</option>
                                        <option value="E">E</option>
                                        <option value="C">C</option>
                                        <option value="Q">Q</option>

                                    </select>
                                    <input type="text" id="uso" name="uso" maxlength="30" size="30"/>
                                    <input type="text" id="uso_comp" name="uso_comp" maxlength="40" size="40"/><br><br> 

                                    Clase:   <select>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                        <option value="G">G</option>

                                    </select>
                                    <input type="text" id="uso_clase" name="uso_clase" maxlength="50" size="30"/><br><br>

                                    Categoría:<select>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>

                                    </select>
                                    <input type="text" id="uso_cat" name="uso_cat" maxlength="50" size="30"/><br><br> 

                                    Superficie: <input type="text" id="sup" name="sup" maxlength="20" size="20"/> m2<br><br>
                                    Año de construcción o últimaremodelación: <input type="date" id="fecha_cons" name="fecha_cons"/><br><br>

                                    Grado de conservación: <select>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>

                                    </select>

                                    <input type="text" id="grad_con" name="grad_con" maxlength="50" size="30"/><br><br>
                                    Total de niveles existentes en laconstrucción: <input type="text" id="tot_n" name="tot_n" maxlength="2" size="2"/><br><br>







                                </fieldset>








                                <fieldset>                            

                                    <input type="reset" />
                                    <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar el formulario" />

                                </fieldset>
                            </form>



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
