<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<html class="no-js">
    <head>        
       <%//<sec:authorize access="isAuthenticated()">   %> 
            <script>                
                $(document).ready(function() { 
                    var flg = (window.location.pathname.indexOf('/portal/') != -1 || window.location.pathname.indexOf('/admon/') != -1 ) ? true: false;
                    if(flg) {                         
                        $("#signout").attr("href","../loggedout.do");                        
                        $("#acercade").attr("href","../acercade.do");
                    } else {                        
                        $("#linkMapa").attr("href","portal/mapa.do"); 
                        $("#miperfil").attr("href","portal/perfil.do");
                        $("#mispuntos").attr("href","portal/mispuntos.do");                        
                    }    
                    
                    $('.dropdown').click(function () {
                        
                        if($('.dropdown').attr("class").contains('open')) {
                            $('.dropdown').removeClass('open');
                        } else {
                            $('.dropdown').addClass('open');
                        }
                        
                        
                        
                    });
                });        
            </script>
      <%//  </sec:authorize>%>  
            <link rel="stylesheet" href="../css/prototipo.css" type="text/css">
            <link rel="stylesheet" type="text/css" href="../css/estiloVisor2.css">
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto">
    </head>        
    <body>         
            <!--<div>
			<div class="header1">
			<img class="g2" src="../img/G3.png"/>
			<img class="edo" src="../img/edo.png"/>
			<img class="bat" src="../img/BN.jpg"/>
                        <img class="bat2" src="../img/bn2.jpg"/>
			<img class="igc" src="../img/g_igecem.png"/>
			</div>  

            </div>     -->
            
            <div class="headerVisor">
			<div class="headerLogos">
				<img src="../img/h1.png">
			</div>
			<div class="headerTitulo">
				<h1>Sistema de Gestión Catastral</h1>
			</div>
                </div>
            
            
    </body>
</html>
