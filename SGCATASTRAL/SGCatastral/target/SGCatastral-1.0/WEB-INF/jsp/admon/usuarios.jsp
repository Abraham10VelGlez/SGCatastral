<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Sistema de Gestión Catastral</title>
        <link href="../images/favicon.png" rel="icon" type="image/png"  />
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="../css/non-responsive.css" rel="stylesheet" type="text/css">      
        <link href="../css/font-awesome.css" rel="stylesheet" type="text/css">
	<link href="../css/component.css" rel="stylesheet" type="text/css" />
        <link href="../css/estilo.css" rel="stylesheet" type="text/css">        
        <script src="../js/ext/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="../js/ext/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
        <script src="../js/ext/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/ext/jquery.dataTables.min.js" type="text/javascript"></script>        
        <script src="../js/ext/fnReloadAjax.js" type="text/javascript"></script>        
        <script src="../js/usuarios.js" type="text/javascript"></script> 
    </head>
    <body>
       <div class="scroller"><!-- this is for emulating position fixed of the nav -->
            <div class="scroller-inner">
                <div id="header"></div>
                <div class="container banner"><h1 class="textBannerGray margin-top-50 margin-left-10">Administrador de Usuarios</h1></div>
                <div class="container margin-bottom-20 margin-top-25" id="ultimosAnuncios">
                    <sec:authorize access="hasRole('ROLE_Administrador')">   
                        <div class="row">
                           <div class="col-md-12">
                                <div class="panel panel-default">
                                    <div class="panel-body "> 
                                        <div id="resultado"></div>            
                                        <table id="usuarios" class="table" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Folio</th>
                                                    <th>Tipo</th>
                                                    <th>Usuario</th>
                                                    <th>Nombre</th>
                                                    <th>Situacion</th>
                                                    <th>Fecha</th>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>
                                        </table>
                                        <form style="display: none" id="frmActivarUsuario" method="POST" action="usUsuario.do">
                                            <input type="hidden" name="idUsuario" id="idUsuario">
                                            <input type="hidden" name="idAccion" id="idAccion">
                                        </form>                                                     
                                        <div id="aviso" style="display:none" title="Aviso">
                                            <p>¿Desea cambiar el estado del usuario?</p>
                                        </div>                                                
                                        <div id="loader" style="display:none">
                                            <img src="../images/loader.gif" style="position: absolute; top: 40%;left: 50%;">
                                        </div>                                                
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>  
                </div>                        
                <div id="footer"></div>
            </div>
        </div>  
    </body>
</html>