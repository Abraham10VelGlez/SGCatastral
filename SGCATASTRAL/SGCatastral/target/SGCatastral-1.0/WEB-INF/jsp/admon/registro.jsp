<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width">
        <title>Sistema de Gestión Catastral</title>
        <link href="../images/favicon.png" rel="icon" type="image/png"  />
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="../css/non-responsive.css" rel="stylesheet" type="text/css">      
        <link href="../css/font-awesome.css" rel="stylesheet" type="text/css">
	<link href="../css/component.css" rel="stylesheet" type="text/css" />
        <link href="../css/estilo.css" rel="stylesheet" type="text/css">            
        <script type="text/javascript" src="../js/ext/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="../js/ext/jquery.validate.js"></script>
        <script src="../js/ext/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/registroUsuario.js"></script>
        <script>
            $(document).ready(function() {                
                jQuery.validator.setDefaults({
                    errorPlacement: function(error, element) {
                        error.appendTo('#error' + element.attr('name'));
                        $(".error").css("margin-left", "0px");
                    }
                });


                $("#frmAddRegistro").validate({
                    rules: {						
                            email: { email : true, required: true },
                            pwd: "required",
                            nombre: "required",
                            apaterno: "required",
                            amaterno: "required",                    
                            intereses: "required"  
                    },

                    messages: {
                            email: { email :'<spring:message code="Formato.Email"/>', required: '<spring:message code="Dato.Requerido"/>' },
                            pwd: '<spring:message code="Dato.Requerido"/>',
                            nombre: '<spring:message code="Dato.Requerido"/>',
                            apaterno: '<spring:message code="Dato.Requerido"/>',
                            amaterno: '<spring:message code="Dato.Requerido"/>',
                            intereses: '<spring:message code="Dato.Requerido"/>'
                    }
                });
        
        
        
            });
        </script>
    </head>
    <body>
        <div class="scroller"><!-- this is for emulating position fixed of the nav -->
            <div class="scroller-inner">
            <div id="header"></div>
            <div class="container banner"><h1 class="textBannerGray margin-top-50 margin-left-10">Registrarme</h1></div>
                <div class="container margin-bottom-20 margin-top-25">
                    <div id="resultado"></div>
                    <sec:authorize access="hasRole('ROLE_Administrador')">   
                    <div class="row">
                        <form:form id="frmAddRegistro" class="form-horizontal" method="POST" action="addRegistro.do" commandName="registro">
                            <div class="form-group"> 
                                <label for="email" class="col-sm-2 control-label"><i class="fa fa-asterisk req"></i>Correo:</label>
                                <div class="col-sm-10">
                                    <form:input id="email" name="email" path="email" type="text" placeholder="correo@dominio.xxx" class="form-control"  />
                                    <span id="erroremail" class="help-block error"></span>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <label for="pwd" class="col-sm-2 control-label"><i class="fa fa-asterisk req"></i>Contraseña:</label>
                                <div class="col-sm-10">
                                    <form:input id="pwd" name="pwd" path="pwd" type="password" class="form-control"  />
                                    <span id="errorpwd" class="help-block error"></span>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <label for="nombre" class="col-sm-2 control-label"><i class="fa fa-asterisk req"></i>Nombre:</label>
                                <div class="col-sm-10">
                                    <form:input id="nombre" name="nombre" path="nombre" type="text" class="form-control"  />
                                    <span id="errornombre" class="help-block error"></span>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <label for="apaterno" class="col-sm-2 control-label"><i class="fa fa-asterisk req"></i>Apellido Paterno:</label>
                                <div class="col-sm-10">
                                    <form:input id="apaterno" path="apaterno" name="apaterno" type="text" class="form-control"  />
                                    <span id="errorapaterno" class="help-block error"></span>
                                 </div>
                            </div>
                            <div class="form-group"> 
                                <label for="amaterno" class="col-sm-2 control-label"><i class="fa fa-asterisk req"></i>Apellido Materno:</label>
                                <div class="col-sm-10">
                                    <form:input id="amaterno" path="amaterno" name="amaterno" type="text" class="form-control"  />
                                    <span id="erroramaterno" class="help-block error"></span>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <label for="telefono" class="col-sm-2 control-label">Teléfono:</label>
                                <div class="col-sm-10">
                                    <form:input id="telefono" path="telefono" name="telefono" type="text" class="form-control"  />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <span><i class="fa fa-asterisk req"></i>Información requerida.</span> 
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" name="submit"  class="btn btn-primary btn-lg">Registrarme</button>
                                    <button id="resetFrmRegistro" type="reset" class="btn btn-default btn-lg" name="reset" >Limpiar datos</button>
                                </div>
                            </div> 
                        </form:form>   
                        </sec:authorize> 
                    </div>
                </div>                         
                <div id="loader" style="display:none">
                    <img src="../images/loader.gif" style="position: absolute; top: 40%;left: 50%;">
                </div>
                <div id="footer"></div>
            </div>
        </div>
    </body>
</html>