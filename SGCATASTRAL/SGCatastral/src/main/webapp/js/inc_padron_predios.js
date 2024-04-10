// JavaScript Document

$(document).ready(function () {
    $(".pes_selector_1").click(function (evento) {

        var valor = $(this).val();

        if (valor == 'pes_1') {
            $("#visor_d").css("display", "block");
            $("#predios").css("display", "none");
            $("#propietarios").css("display", "none");
            $("#historico").css("display", "none");
            $("#propias").css("display", "none");
            $("#comunes").css("display", "none");
            $("#documentos").css("display", "none");
        }

        if (valor == 'pes_2') {
            $("#visor_d").css("display", "none");
            $("#predios").css("display", "block");
            $("#propietarios").css("display", "none");
            $("#historico").css("display", "none");
            $("#propias").css("display", "none");
            $("#comunes").css("display", "none");
            $("#documentos").css("display", "none");
        }

        if (valor == 'pes_3') {
            $("#visor_d").css("display", "none");
            $("#predios").css("display", "none");
            $("#propietarios").css("display", "block");
            $("#historico").css("display", "none");
            $("#propias").css("display", "none");
            $("#comunes").css("display", "none");
            $("#documentos").css("display", "none");
        }

        if (valor == 'pes_4') {
            $("#visor_d").css("display", "none");
            $("#predios").css("display", "none");
            $("#propietarios").css("display", "none");
            $("#historico").css("display", "block");
            $("#propias").css("display", "none");
            $("#comunes").css("display", "none");
            $("#documentos").css("display", "none");
        }

        if (valor == 'pes_5') {
            $("#visor_d").css("display", "none");
            $("#predios").css("display", "none");
            $("#propietarios").css("display", "none");
            $("#historico").css("display", "none");
            $("#propias").css("display", "block");
            $("#comunes").css("display", "none");
            $("#documentos").css("display", "none");
        }
        
          if (valor == 'pes_6') {
            $("#visor_d").css("display", "none");
            $("#predios").css("display", "none");
            $("#propietarios").css("display", "none");
            $("#historico").css("display", "none");
            $("#propias").css("display", "none");
            $("#comunes").css("display", "block");
            $("#documentos").css("display", "none");
        }
        
          if (valor == 'pes_7') {
            $("#visor_d").css("display", "none");
            $("#predios").css("display", "none");
            $("#propietarios").css("display", "none");
            $("#historico").css("display", "none");
            $("#propias").css("display", "none");
            $("#comunes").css("display", "none");
            $("#documentos").css("display", "block");
        }
        
    });
    
    
      $(".docto").change(function(){
        $("#id_doc").val($(this).attr('id'));
        var archivo = $(this).clone();
        $("#docArchivo").empty();
        archivo.appendTo($("#docArchivo"));
        $("#docArchivo input[type=file]").css("display","none");

        $.ajax({
            contentType : false,
            processData: false,
            type: "post",
            cache: false,
            url: "cargarDocumento.do",
            dataType : 'text',
            data : new FormData($("#frmDcto")[0]), 
            success : function(respuesta){
                alert(respuesta);
            },
            error : function(){                    
                alert("Error al subir el archivo");
            }
        });        
    }); 
    
});





