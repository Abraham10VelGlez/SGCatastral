$(document).ready(function() {
           //alert("JQUERY ALWAYS ACTIVO");
           /*
           var miJSON={"A1":{"valor":"100", "color":"azul", "caracteristica":{"tipo":"S1","ref":"MMM"}},
		"A2":{"valor":"110", "color":"rojo", "caracteristica":{"tipo":"S2","ref":"NNN"}},
		"A3":{"valor":"120", "color":"negro", "caracteristica":{"tipo":"S3","ref":"OOO"}},
		"A4":{"valor":"90", "color":"verde", "caracteristica":{"tipo":"S4","ref":"PPP"}},}
 */
 /*
   metodo para recuperar json y ver en pantalla...
        var miJSON=[{"cvigecem":"070","nombremunc":"Capulhuac","zona":1,"nommun2":"CAPULHUAC","clvigecem":"070","cvinegi":"15019","manzana":"215","clvmunp":"070","clavcaunida":"07001215"}]
       // var miJSON = jQuery.parseJSON(JSON); 
       alert(miJSON);
         $.each(miJSON, function(i,item){
         document.write("<br>"+i+" - "+miJSON[i].cvigecem+" - "+miJSON[i].nombremunc);
         //+" - "+miJSON[i].caracteristica.tipo+" - "+miJSON[i].caracteristica.ref
		});
          */
           
           
           $('#event').click(function (){
               //alert("poligono");
               var n="hola";
               $.post("eventoclavecat.do",{n:n},function(ww){
                   //alert(ww);
                   var jsonsaved = jQuery.parseJSON(ww);
                  // document.write(jsonsaved);
                   $.each(jsonsaved, function(i,item){
                       
                       $('#mun').val(jsonsaved[i].clavemuniciopio);
                       $('#nom_mun').val(jsonsaved[i].nombremun);
                       $('#zona').val(jsonsaved[i].zona);
                       $('#mza').val(jsonsaved[i].manzanaa);
        
		});
   
               });
               $.post("eventoclavecatlocal.do",{n:n},function(wwloc){
                   //alert(ww);
                   /* claveigecem numlocaldd nombrelocalidad*/
                   var jsonsaved = jQuery.parseJSON(wwloc); 
                  // document.write(jsonsaved);
                 // var cadena ="";
                //  var option = $(document.createElement('option'));
                  ////$('#loccc').append('');
                   $.each(jsonsaved, function(i,item){                                              
                       
                  //     cadena +='<option value="'
                  //             +jsonsaved[i].numlocaldd+
                  //             '" >'
                  //             +jsonsaved[i].nombrelocalidad+
                  //             '</option>';
                   //    option.val(this.numlocaldd); 
                    //   option.text(this.nombrelocalidad);
                        
                         $('#localll').append('<option value="'
                               +jsonsaved[i].numlocaldd+
                               '" >'
                               +jsonsaved[i].nombrelocalidad+
                               '</option>');
                       
        
		});
               // $('#loccc').append('<select id="loc" name="loc" >'+cadena+'</select>');
               // $('#local').append(option);
               /* alert(cadena);
                
                        var option = $(document.createElement('option'));

                        option.text(this.Nombre);
                        option.val(this.Codigo);

                        $("#cboEjemplo").append(option);
                 */
               });
            
           });
           
           /*Evento de regimen de propiedad*/
           $('#regimp').change(function(){
               //alert( $('#regimp').val());
               var selector=$(this).val();
               if(selector=="#"){
                   $('#tip_reg').val('');
               }else{
                   
                   if(selector == '2'){                       
                       //$('#sup_t_c').val('');
                       $('#sup_t_c').attr('disabled','disabled');

                   }else{
                       //$('#sup_t_c').attr('disabled','');
                       $('#sup_t_c').removeAttr('disabled');
                   }
                   
               $.post("regimenpropied.do",{opc:selector},function(valores){
                   var encapsula =jQuery.parseJSON(valores);
                   $.each(encapsula, function(contador,item){
                       $('#tip_reg').val('');
                       $('#tip_reg').val(encapsula[contador].descripcion);                       
                   });
               });
           }
           });
           
           /*Evento de tipo de vialidad  */
           $('#cve_t_vial').keyup(function()
           {
              var rexcaracter =$(this).val().toUpperCase();              
              //alert(rexcaracter);
              if(rexcaracter==" " || rexcaracter=="" ){
              //alert("no hay datos...");
              $('#nom_vialidad').val('');
              }else{
              $.post("tiposviadlid.do",{tipv:rexcaracter},function(resul){
                  //alert(resul);
                  var encapsularjson = jQuery.parseJSON(resul);                  
                  $.each(encapsularjson,function (contador1,item){
                      if(encapsularjson[contador1].tipvialdd ==null){
                          $('#nom_vialidad').val('S/N Vialidad');
                      }else{
                          $('#nom_vialidad').val(encapsularjson[contador1].tipvialdd);
                      }
                  });
              });
            }
                
           });
               /*evento de posicion....*/
           $('#posicion').change(function(){
               //alert($(this).val());
               var ii= $(this).val();
               if(ii != '#'){
               switch (ii){
                   case '1':
                       $('#pos').val('INTERMEDIO');
                       break;
                   case '2':
                       $('#pos').val('ESQUINERO');
                       break;
                       case '3':
                       $('#pos').val('CABECERO');
                       break;
                   case '4':
                       $('#pos').val('MANZANERO');
                       break;
                       case '5':
                       $('#pos').val('FRENTES NO CONTIGUOS');
                       break;
                   case '6':
                       $('#pos').val('INTERIOR');
                       break;   
               }
               
               }else{
                   $('#pos').val('');
               }
               
           });
           
           /*evento que guarda el formulario1*/
           $('#btnEnviarform').click(function(){
              // alert("formulario...1");
               //$('#Clave Catastral').val();
                                 var a =   $('#mun').val();
                                 var b =   $('#nom_mun').val();
                                 var c =   $('#zona').val();
                                 var d =   $('#mza').val();
                                 var e =   $('#predio').val();
                                 var fg= $('#localll').val();                                 
                                 var h =    $('#zona_o').val();
                                 var j =    $('#nom_calle').val();
                                 var k =    $('#cve_t_vial').val();
                                 var l =   $('#nom_vialidad').val();


                                 var m =   $('#n_ext').val();
                                 var n =    $('#cod_pos').val();                                 
                                 var p =    $('#nom_col').val();
                                 var q =    $('#nom_en_calle').val();
                                 var r =    $('#nom_en_calle2').val();                                          
                                 var s =    $('#regimp').val();                                       
                                 var t =    $('#tip_reg').val();                                         
                                 var u =   $('#sup_total').val();
                                 var v =   $('#frente').val();
                                 var w =   $('#fondo').val();
                                  
                                  
                                 var x =   $('#altura').val();
                                 var y =   $('#ar_ins').val();
                                 var z =   $('#posicion').val();
                                 var ax =   $('#pos').val();
                                 var bx =   $('#sup_apro').val();
                                 var cx =  $('#sup_t_c').val();
                                 var dx =  $('#motivo').val();
                                 var ex =   $('#fecha').val();
                                 var fx = $('#keypreed').val();
                                 
/*variable sustenta a cambiar*/
var dvavg = $('#formpred').val();
/*variable sustenta a cambiar*/
                                 
if(
a=='' || a==' ' || b=='' || b==' ' || c=='' || c==' ' || d=='' ||d==' ' || e=='' ||e==' '
||fg=='' || fg==' '||h=='' || h==' '||j==''|| j==' '|| k=='' ||k==' '||
l=='' || l==' ' ||m=='' ||m==' '|| n=='' || n==' '||p=='' ||p==' '|| q=='' ||q==' '||r==''||r==' '||s==''||s==' '||t==''||t==' '|| u=='' ||u==' ' ||
v=='' ||v==' ' || w==''||w==' '||x==''||x==' '||y==''||y==' '||z==''||z==' '||ax==''||ax==' '||bx==''||
bx==' '||cx==''||cx==' '||dx==''||dx==' '||ex==''||ex==' '||fx==''||fx==' '){
   alert("llenar todos los campos");    
   }   else{       
     /*
      *  validacion de campos vacios para input de ninguno....               
                   localwrite
              
                    nomcallwrite
            
                    nomcolwrite
             
                    nomcallwrite
             
                    nomcall2write
       }*/
      if( fg == 'A10' ){
          
         var fg = $('#localwrite').val();
      }
      if( j == 'A11' ){
          
          var j = $('#nomcallwrite').val();
      }
      if( p == 'A12' ){
          
           var p = $('#nomcolwrite').val();
      }
      if( q == 'A13' ){
          
          var q = $('#nomcallwrite').val();
      }
      if( r == 'A14' ){
          
           var r = $('#nomcall2write').val();
      }
      
      
       alert("continuar con el proceso");
                  $.post("guardandodatounos.do",{a:a,b:b,c:c,d:d,e:e,fg:fg,h:h,j:j,k:k,l:l,m:m,n:n,p:p,q:q,r:r,s:s,t:t,u:u,v:v,w:w,x:x,y:y,z:z,ax:ax,bx:bx,cx:cx,dx:dx,ex:ex,fx:fx,forpredio:dvavg},function(wy){
                  alert(wy);    
                   var encapsularjson = jQuery.parseJSON(wy);                  
                  $.each(encapsularjson,function (contador1,item){
                      
                                    $('#mun2').val(encapsularjson[contador1].mun);
                                    $('#zona2').val(encapsularjson[contador1].zona);
                                    $('#mza2').val(encapsularjson[contador1].manz);
                                    $('#predio2').val(encapsularjson[contador1].pre);
                                    $('#edificio').val('00');
                                    $('#depa').val('0000');
                                    
                                    $('#dom_fis').val(encapsularjson[contador1].domicil);
                                    $('#keypreed').val(encapsularjson[contador1].klabprdo)
                                    //klabprdo                                                                
                  });

                  });   
                  
                   /* Cargar los datos del combo de Destino del Predio: */
                  var desttinoPre = "23";
                  $.post("destodelp.do",{desttinoPre:desttinoPre},function(dy){
                      alert(dy);
                      var encapsularjson = jQuery.parseJSON(dy); 
                      
                              //$('#destinpre').html('');objetofor.accumulate("auso",vectorresul.getUsso());
                              //objetofor.accumulate("descripcionuso", vectorresul.getDescripuss()); 
                              $('#destinpre').html('');
                              $.each(encapsularjson, function(i,item){                                                  
                              $('#destinpre').append('<option value="'+encapsularjson[i].auso+'" >'+encapsularjson[i].auso+'</option>');
                              
		  });
                  });
   }         
                              
                    
           });
           
           
       
           
           
           $('#destinpre').change(function(){
                var tipodestino=$(this).val();
                //alert(tipodestino);
                $.post("destionopredios.do",{tipdes:tipodestino},function(combos){
                    alert(combos);
                     var jsonsaved = jQuery.parseJSON(combos);                 
                  /*
                   * objetofor.accumulate("usot",vectorresul.getUs());
            objetofor.accumulate("descripcionn", vectorresul.getDescripus());
            objetofor.accumulate("usodt", vectorresul.getBbuso());
            objetofor.accumulate("usoespefico", vectorresul.getUsoespecifico());
            objetofor.accumulate("usodescripcion", vectorresul.getDescrippp());
                   * 
                   */
                    $('#usespecf').html('');
                    $.each(jsonsaved, function(i,item){
                    $('#des_pre').val(jsonsaved[i].descripcionn);                    
                    $('#usespecf').append('<option value="'+jsonsaved[i].usoespefico+'" >'+jsonsaved[i].usoespefico+'</option>');
                    $('#uso_esp').val(jsonsaved[i].usodescripcion);
		});
                   
                });
           });
           
           $('#usespecf').change(function(){
               var usoespecif=$(this).val();
               $.post("combofinusoespfc.do",{usooepcf:usoespecif},function(rb){
                   var jsonsaved = jQuery.parseJSON(rb);
                   $.each(jsonsaved, function(i,item){                                        
                    $('#uso_esp').val(jsonsaved[i].descpusoespefico);
		     });
               });
           });
           
           
           
           $('#eventiniprd').click(function(){
               var drwplgn =$('#drawingpolygon').val();
               var d  = dDescOmpresionPOlyiGoN(drwplgn); 
               alert(d);
             /*  $.ajax({
                   beforeSend: function () {
                //$("#loader").css('display','block');
                alert("cargando");
            },
            type: 'POST',
            url: "eventopolygn.do",
            data: {prd:d},
            dataType: 'json',
            success: function (data) {
            $.each(data, function(i,item){                       
                        
                       $('#keypreed').val(data[i].valorresultante);
		 });
                       var STopPolygon=$('#keypreed').val();

                       alert(STopPolygon);
                       calculoclavecat(STopPolygon);
            } 
            
               });*/
                $.post("eventopolygn.do",{d:d},function(valink){
                   alert(valink);
                   //$('#keypreed').val('');
                   var jsonsavkyekey = jQuery.parseJSON(valink);                 
                     $.each(jsonsavkyekey, function(i,item){                       
                     $('#keypreed').val(jsonsavkyekey[i].valorresultante);
                      });
                                                                  
                           var f = new Date();
                           var dh=f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear();
                           $('#fecha').val(dh);                           
                           //$('#keypreed').val();
                           alert($('#keypreed').val());
                           calculoclavecat($('#keypreed').val());
                           calculoclavecatlocal($('#keypreed').val());
                           calculoclavecatscalle($('#keypreed').val());
                           calculocoloniaprdio($('#keypreed').val());                 
                           
                 });
                          
                          
                           
               
               
               /*
               $.post("eventopolygn.do",{prd:d},function(valink){
                   alert(valink);
                   var jsonsavkyekey = jQuery.parseJSON(valink);
                  // document.write(jsonsaved);
                   $.each(jsonsavkyekey, function(i,item){                       
                        
                       $('#keypreed').val(jsonsavkyekey[i].valorresultante);
		 });
                 });
                 var STopPolygon=$('#keypreed').val();
                                  
                           alert(STopPolygon);
                           calculoclavecat(STopPolygon);
                           */
                /* 
                 var windoous = valorvalidando(STopPolygon);
                 if(windoous=="123"){
                           var f = new Date();
                           var dh=f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear();
                           $('#fecha').val(dh);
                           var securityk=$('#keypreed').val();
                           alert(securityk);
                           calculoclavecat(securityk);
                           calculoclavecatlocal(securityk);
                           calculoclavecatscalle(securityk);
                           calculocoloniaprdio(securityk);
                 }else{
                     alert("error");
                 }
               */
           });
           
           
           /*Evento para seleccion de calle*/
           $('#nom_calle').change(function(){
             var ij= $('#keypreed').val();
               var opcc= $(this).val();
              var sss= filtreodecalles(opcc);
               /*entre calle*/
               
               $.post("eventoentrec.do",{ij:ij,oo:sss},function(ddd){
                           alert(ddd);
                           var jsonsaved = jQuery.parseJSON(ddd); 
                           $.each(jsonsaved, function(i,item){                                              
                           $('#nom_en_calle').append('<option value="'
                           +jsonsaved[i].callls+','+jsonsaved[i].badaa+
                           '" >'
                           +jsonsaved[i].callls+' '+jsonsaved[i].badaa+
                           '</option>');
                              
                           });
               });
               
           });
           
           $('#nom_en_calle').change(function(){
               var ij= $('#keypreed').val();
               var opcc= $(this).val();
                var wws= filtreodecalles(opcc);
               /*y calle*/
               $.post("eventoyclles.do",{ij:ij,oo:wws},function(dfd){
                           alert(dfd);
                           var jsonsaved = jQuery.parseJSON(dfd); 
                           $.each(jsonsaved, function(i,item){                                              
                           $('#nom_en_calle2').append('<option value="'
                           +jsonsaved[i].callls+','+jsonsaved[i].badaa+
                           '" >'
                           +jsonsaved[i].callls+' '+jsonsaved[i].badaa+
                           '</option>');
                              
                           });
               });
           });
           
           $('#localll').change(function(){
                var PL= $(this).val();
                if(PL=='A10'){
                    $('#lcl').html('');
                    $('#lcl').html('<input type="text" class="input" id="localwrite" name="localwrite" maxlength="20" size="20"/>');
                }else{
                    $('#lcl').html('');
                }
           });
           
           
           $('#nom_calle').change(function(){
               var gh= $(this).val();
               if(gh=="A11"){
                   $('#lcl2').html('');
                    $('#lcl2').html('<input type="text" class="input" id="nomcallwrite" name="localwrite" maxlength="20" size="20"/>');
               }else{
                   $('#lcl2').html('');
               }
           });
           
           $('#nom_col').change(function(){
               var ef= $(this).val();
               if(ef=="A12"){
                   $('#lcl3').html('');
                    $('#lcl3').html('<input type="text" class="input" id="nomcolwrite" name="localwrite" maxlength="20" size="20"/>');
               }else{
                   $('#lcl3').html('');
               }
           });
           $('#nom_en_calle').change(function(){
               var ef= $(this).val();
               if(ef=="A13"){
                   $('#lcl4').html('');
                    $('#lcl4').html('<input type="text" class="input" id="nomcallwrite" name="localwrite" maxlength="20" size="20"/>');
               }else{
                   $('#lcl4').html('');
               }
           });
           $('#nom_en_calle2').change(function(){
               var ef= $(this).val();
               if(ef=="A14"){
                   $('#lcl5').html('');
                    $('#lcl5').html('<input type="text" class="input" id="nomcall2write" name="localwrite" maxlength="20" size="20"/>');
               }else{
                   $('#lcl5').html('');
               }
           });
           
           /*Evento para guardar menu de propietario...*/
           $('#btnEnviar2').click(function(){
               alert("guardar todo");
               
var a = $('#mun2').val();
var b = $('#zona2').val();
var c = $('#mza2').val();
var d = $('#predio2').val();
var e = $('#edificio').val();
var f = $('#depa').val();
var g = $('#n_int').val();
var h = $('#ap_pat').val();
var i = $('#ap_mat').val();
var j = $('#nom_p').val();
var k =$('#per_col').val();
var l =$('#rfc').val();
var m =$('#u_rfc').val();
var n =$('#curp').val();
var o = $('#dom_fis').val();
var p =$('#destinpre').val();
var q =$('#des_pre').val();
var r = $('#usespecf').val();
var s =$('#uso_esp').val();
var identityPred = $('#keypreed').val();




if( $('#opc1').val() == '#$'){
var tu = $('#opc2').val();
}else if($('#opc2').val() == '#$'){    
    var tu = $('#opc1').val();
}


$.post("eventosavepropiet.do",{a:a,b:b,c:c,d:d,e:e,f:f,g:g,h:h,i:i,j:j,k:k,l:l,m:m,n:n,o:o,p:p,q:q,r:r,s:s,tu:tu,z:identityPred},function(gva){
    alert(gva);        
});
var identityPredio = $('#keypreed').val();               
               
$.post("eventofondd.do",{rbk:identityPredio},function(af){
    alert(af);
});
$.post("eventoarea.do",{rbks:identityPredio},function(af){
    alert(af);
});
$.post("eventovalorunseul.do",{ravk:identityPredio},function(gdv){
    alert(gdv);
});
$.post("eventovalorrestrig.do",{ravks:identityPredio},function(gdv){
    alert(gdv);
});
               
               
               
           });
           
           $('#opc1').click(function(){
               $('#opc2').val('#$');
               //baldio
               $('#opc1').val('A');
           });
           $('#opc2').click(function(){
               //construido
               $('#opc1').val('#$');
               $('#opc2').val('B');               
           });
           
           
            
           $('#eventocvtt').click(function(){
               var cvtpred = $('#keypreed').val();  
$.post("eventocvtral.do",{gcvtpredav:cvtpred},function(h){
alert(h);
                           var jsoncvtsaved = jQuery.parseJSON(h); 
                           $.each(jsoncvtsaved, function(i,item){                                              
                           $('#fff').html(jsoncvtsaved[i].resultadoFINAL);                              
                           });
//fff
});
           });
            });

function calculocoloniaprdio(aa){
$.post("eventocolprd.do",{qr:aa},function(as){
alert(as);
var jsonsavedcol = jQuery.parseJSON(as);                                  
$.each(jsonsavedcol, function(i,item1){                                              
$('#nom_col').append('<option value="'
+jsonsavedcol[i].clacol+
'" >'
+jsonsavedcol[i].nomclo+
'</option>');
 });                                      
 });
}

function calculoclavecatscalle(uu){
$.post("eventoclavecatstreet.do",{qw:uu},function(plstreet){
alert(plstreet);
var jsonsadcalle = jQuery.parseJSON(plstreet); 
$.each(jsonsadcalle, function(i,item2){                                              
$('#nom_calle').append('<option value="'
+jsonsadcalle[i].callles+','+jsonsadcalle[i].bandaa+
'" >'
+jsonsadcalle[i].callles+' '+jsonsadcalle[i].bandaa+
'</option>');                              
});
});
}

function calculoclavecatlocal(ee){
$.post("eventoclavecatlocal.do",{qa:ee},function(wwloc){
alert(wwloc);
var jsonsavedlocal = jQuery.parseJSON(wwloc); 
$.each(jsonsavedlocal, function(i,item3){                                              
$('#localll').append('<option value="'
+jsonsavedlocal[i].numlocaldd+
'" >'
+jsonsavedlocal[i].nombrelocalidad+
'</option>');
});
});
}

function calculoclavecat(ii){
$.post("eventoclavecat.do",{nn:ii},function(yt){
var jsonscla = jQuery.parseJSON(yt);
$.each(jsonscla, function(i,ite){
$('#mun').val(jsonscla[i].clavemuniciopio);
$('#nom_mun').val(jsonscla[i].nombremun);
$('#zona').val(jsonscla[i].zona);
$('#mza').val(jsonscla[i].manzanaa);        
});
});   
}


function valorvalidando(gg){
if(gg == "0"){
alert(gg);
alert("Es un poligono malo");
$('#keypreed').val(''); 
var o= "000";
return o;
}else{ 
var o= "123";    
alert(gg); 
$('#keypreed').val(''); 
$('#keypreed').val(gg); 
return o;
}
}


function dibjpavg(){
limpiezapolygon();
//inicializar el objeto de drawingManager
drawingManager.setDrawingMode(google.maps.drawing.OverlayType.POLYGON);
google.maps.event.addListener(drawingManager, 'polygoncomplete', function(polygon) {
var radius = polygon.getMap();
/*var objeto = radius;var respuesta="";
for (var i in objeto)
{respuesta+=i+": "+objeto[i]+"<br>";
}alert(respuesta); */
});
google.maps.event.addListener(drawingManager, 'overlaycomplete', function(event) {
if (event.type == 'polygon') {
var radius = event.overlay.getMap();
var geomavg = event.overlay;
var objeto = radius;
/* var respuesta="";
for (var i in objeto)
{respuesta+=i+": "+objeto[i]+"<br>";
}alert(respuesta);*/
var puntosys = geomavg.getPath();
extraergeometria(puntosys);
}
});
}

function extraergeometria(puntoextraidos){
//alert("extraergeometria");
var i = null;
var xy = null;
var xyi = null;
var contentString = null;
var polygonString = [];
var tipoavggeom = null;
contentString = "POLYGON((";
for (i = 0; i < puntoextraidos.length; i++) {
xy = puntoextraidos.getAt(i);
contentString += xy.lng() + " " + xy.lat();
contentString = contentString + ", ";
polygonString.push("{" + xy.lat() + ", " + xy.lng() + "}");
}
xyi = puntoextraidos.getAt(0);
var verticei = xyi.lng() + " " + xyi.lat();
contentString = contentString + verticei + "))";
polygonString.push("{" + xyi.lat() + ", " + xyi.lng() + "}");
tipoavggeom = 2;

//alert("#shape"+'       '+ '----------------: '+contentString);
//alert("#tipo de geom"+'       '+ '----------------: '+tipoavggeom);
//alert("#geom_poligono"+'       '+ '----------------: '+contentString);
//alert("#mvc_poligono"+'       '+ '----------------: '+polygonString);
/*var hb=derupandomedidas(contentString);*/
$('#drawingpolygon').val('');
$('#drawingpolygon').val(contentString);
}

function limpiezapolygon(){
        var contadorlimpia;

    for (contadorlimpia = 0; contadorlimpia < geometrias.length; contadorlimpia++) {
        geometrias[contadorlimpia].setMap(null);
    }

    for (contadorlimpia = 0; contadorlimpia < buffers.length; contadorlimpia++) {
        buffers[contadorlimpia].setMap(null);
    }

    if (markers) {
        for (contadorlimpia in markers) {
            markers[contadorlimpia].setMap(null);
        }
        markers.length = 0;
    }

    if (buff_c != null) {
        buff_c.setMap(null);
    }

    if (buff_g != null) {
        buff_g.setMap(null);
    }

    if (ObjMap.infowindow) {
        ObjMap.infowindow.close();
    }
}

function derupandomedidas(contentString){
    
var cadena = contentString,
    separador = "((", // un espacio en blanco
    arregloDeSubC = cadena.split(separador);
    
var caden2 = arregloDeSubC,
        separador2  = "))",
        arregloDeSubCadenas = caden2.split(separador2);
//console.log(arregloDeSubCadenas); // la consola devolverá: 

alert(arregloDeSubCadenas);
}


function dDescOmpresionPOlyiGoN(a){

var str3 = a;
str3=str3.replace("(("," ");
//alert(str3);
var str4 = str3;
str4=str4.replace("))"," ");
//alert(str4);
var str5 = str4;
str5=str5.replace("POLYGON"," ");
//alert("FINAL---------------:"+str5);
return str5;
}

function filtreodecalles(calllle){
var cadena=calllle,
separador = ",", // un espacio en blanco
    arregloavgDeSubCadenas = cadena.split(separador);
var a =arregloavgDeSubCadenas;
for (x=0;x< 1;x++){
        var ss=a[0];
}
alert(ss);
return ss;
}