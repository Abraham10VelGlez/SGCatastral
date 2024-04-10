<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <meta http-equiv="cache-control" content="no-cache" />
         <title>Sistema de Gestión Catastral</title>
         <link rel="stylesheet" type="text/css" href="css/cat.css"></link>
                 <link rel="stylesheet" type="text/css" href="../css/jquery-ui.css"></link>
                     <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto"></link>
         <script src="js/ext/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="js/ext/jquery.validate.js" type="text/javascript"></script>
        <script type="text/javascript">
                $(document).ready(function() {
                jQuery.validator.setDefaults({
                    errorPlacement: function(error, element) {
                        $('#error' + element.attr('name')).html("");
                        error.appendTo('#error' + element.attr('name'));
                        $(".error").css("margin-left", "0px");
                    }
                });

                var msg = '${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}';
                console.log(msg);
                $("#errorj_username").html(msg != null && msg != '' ? "<i class='fa fa-info-circle red'></i>" + msg : '');

                $('#frmLogin').validate({         
                    onKeyup: true,
                    eachValidField: function() {
                        $("#error" + $(this).attr("name")).hide();
                    },
                    eachInvalidField: function() {
                        $("#error" + $(this).attr("name")).show();
                    },
                    rules: {
                        j_username: {required: true, email: true},
                        j_password: {required: true}
                    },
                    messages: {
                        j_username: {
                            required: '<spring:message code="Dato.Requerido"/>', email:'<spring:message code="Formato.Email"/>'
                        },
                        j_password: {
                            required: '<spring:message code="Dato.Requerido"/>'
                        }
                    }
                });
            });
        </script>
    </head>

    <body>
                 <div class="contenedor" >
        <div class="headerVisor">
                <div class="headerLogos">
                    <img src="img/H1I.png"/>
                </div>
                <div class="headerTitulo">
                    <h1>Sistema de Gestión Catastral</h1>
                </div>
                </div>

        <div id="page" style="padding-top:5%; padding-left:40%;">
            <!--div class="container-fluid"-->
            <!-- Header -->
            
        
                <div class="row">        
                    <div> 
                      
                            <form id="frmLogin" class="form-horizontal" action="<c:url value='j_spring_security_check' />"   method='POST'>
                                <div class="form-group">                                   
                                    <div class="col-sm-8 col-sm-offset-2 input-group">
                                        <span class="input-group-addon w50" id="sizing-addon1"><i class="icon-user icons fs20"></i></span>
                                        <input type="email" class="form-control input-lg" id="usuario" name='j_username' placeholder="Escriba su usuario">                                        
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-8 col-sm-offset-2 input-group">
                                        <span class="input-group-addon w50" id="basic-addon1"><i class="icon-key icons fs20"></i></span>
                                        <input name='j_password' type="password" style="margin-top:10px;" class="form-control input-lg" id="password"  placeholder="Escriba su contraseña">
                                    </div>
                                </div>
                                <div class="form-group">
                                        <button type="submit" name="submit"  style="margin-top:10px;" class="boton">Ingresar</button>
                                </div>
                                <div class="col-sm-8 col-sm-offset-2 input-group">
                                    <span id="errorj_username" class="help-block error"></span>
                                </div>
                                <div class="col-sm-8 col-sm-offset-2 input-group">
                                    <span id="errorj_password" class="help-block error"></span>
                                </div>
                            </form:form>                                                        
                    </div>   
                </div>    
                                
             <div id="contenedor_footer">
          <!-- #footer -->
  
            
            </div>
                                  
        </div>
         </div>
      
    </body>
</html>
