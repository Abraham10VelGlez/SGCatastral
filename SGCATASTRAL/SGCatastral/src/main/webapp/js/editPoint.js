var myMarker = null;

$(document).ready(function() {
    
    $("#header").load("../header.do");
    $("#footer").load("../footer.do");
    initMap = $(initMap);
    inicializarMapa('contenedorMapa', initMap);          
    
    $("#imgPreview1").attr("src", "../images/REPOSITORIO_WINGSTOP/" + document.getElementById("contenido").value);      
    
    $(".imgPreview").change(function(){
            uploadFile(this);            
    });
    
     $('#frmEditPoint').submit(function(e) {                                            
            
        $("#loader").show();
        e.preventDefault();            

        $.ajax({                
            contentType : false,
            processData: false,
            type: "post",
            cache: false,
            url: "editPoint.do",
            dataType : 'json',                
            data : new FormData($(this)[0]),
            success : function(callback){                                  
                $("#resultado").attr("class","");    
                setTimeout(function(){                   
                    if(callback.contenido!= null){
                        myMarker.setIcon({url:'../images/REPOSITORIO_WINGSTOP/' + callback.contenido, scaledSize:new google.maps.Size(25, 25)});
                    }                    
                    $("#loader").hide();        
                    $("#resultado").html(callback.mensaje);                     
                    if(callback.codigo == 200) {
                        $("#resultado").addClass("alert alert-success");    
                    } else {
                        $("#resultado").addClass("alert alert-danger");
                    }
                }, 2000);                                                                                    
                    
            },
            error : function(){                                  
                $("#resultado").html("Error de servidor, no fue posible guardar el punto");                    
                $("#resultado").attr("class","");
                $("#resultado").addClass("alert alert-danger");
                $("#loader").hide();
            }
        });

    });
    
});

function uploadFile(input) {        
    
    if (input.files && input.files[0]) {                
        var reader = new FileReader();              
            reader.onload = function (e) {                    
                $('#' + $(input).attr('preview')).attr('src', e.target.result);                
        };               
        reader.readAsDataURL(input.files[0]);                        
        
    }     
}

function inicializarMapa(divMap) {
    ObjMap = new Map(divMap, iniLatitud, iniLongitud, iniZoom); 
    getMarker();
}

function inicializarMapa(divMap, initMap) {
    if( initMap.lng != null) { iniLongitud =  parseFloat(initMap.lng); }
    if( initMap.lat != null) { iniLatitud = parseFloat(initMap.lat); }
    if( initMap.zoom != null) { iniZoom = parseInt(initMap.zoom); }
    
    ObjMap = new Map(divMap, iniLatitud, iniLongitud, iniZoom);    
    getMarker();
}

function Map(divMap, lat, lon, zoom) {
    this.mapOptions = {
        zoom: zoom,        
        center: new google.maps.LatLng(lat, lon)
    };

    this.map = new google.maps.Map(document.getElementById(divMap), this.mapOptions);
    this.zoom = zoom;
    this.lat = lat;
    this.lon = lon;
    this.div = divMap;
    this.layers = [];
    this.configLayerIWnd = [];
}
    
function getMarker(){
    
    var myLat = parseFloat(document.getElementById("plat").value);
    var myLng = parseFloat(document.getElementById("plng").value);        
    var imagen = document.getElementById("contenido").value;
    
    var myLatLng = {lat: myLat, lng: myLng};
    
    myMarker = new google.maps.Marker({
        position: myLatLng,
	draggable: true,
	clickable: false,
	icon: {url:'../images/REPOSITORIO_WINGSTOP/' + imagen, scaledSize:new google.maps.Size(25, 25)},        
        map: ObjMap.map

    });        
    
    google.maps.event.addListener(myMarker, 'dragend', function() {
        var updatedPosition = myMarker.getPosition();                
        $("#plat").val(updatedPosition.lat());            
        $("#plng").val(updatedPosition.lng());        
    });            
    
    ObjMap.map.setCenter(new google.maps.LatLng(myLatLng));
    ObjMap.map.setZoom(16);
        
}