var tableUsuarios;

$(document).ready(function() {
    
    $("#header").load("../header.do");
    $("#footer").load("../footer.do");
    
    tableUsuarios = $('#usuarios').dataTable( {
        "bprocessing": true,
        "search": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "sAjaxSource": "lstUsuarios.do",
        "order": [[ 0, "desc" ]],
        "oLanguage": {
            "oPaginate": {
                "sFirst": "Inicio",
                "sLast": "Final",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
              },
              "sSearch": "Búsqueda",
              "sInfo": "_START_ / _END_ de _TOTAL_",
              "sInfoEmpty": "_START_ / _END_ de _TOTAL_",
              "sLengthMenu": 'Mostrar <select>'+
                    '<option value="10">10</option>'+
                    '<option value="20">20</option>'+
                    '<option value="-1">Todos</option>'+
                    '</select> registros',
              "sZeroRecords": "No se encontraron registros",
              "sLoadingRecords": "<img src='../images/loader.gif'>",
              "sEmptyTable": "No hay datos disponibles",
              "sInfoFiltered": "(Filtrado de un total de _MAX_ registros)"
        },
        "columns": [
            { data: "id" },
            { data: "perfil"},
            { data: "login" },
            { data: "nombreCompleto" },
            { data: "situacion" },
            { data: "fecha" },
            { data: "claveSituacion" }           
        ],
        "columnDefs": [
            {
              "render": function ( data, type, row ) {                 
                  if(data.split("-")[1] == 0 || data.split("-")[1] == 2) {
                    return '<a href="#" title="Activar" class="btn btn-default btn-xs" onclick="activarUsuario(\'' + data.split("-")[0] + '\', 1)"><i class="fa fa-arrow-circle-up"></i>Activar</a>';
                  } else if(data.split("-")[1] == 1) {                      
                    return '<a href="#" title="Desactivar" class="btn btn-default btn-xs" onclick="activarUsuario(\'' + data.split("-")[0] + '\', 0)"><i class="fa fa-arrow-circle-down"></i>Desactivar</a>';
                  }                                 
              },
              "targets": 6
            }
        ]
    } );
    
        
    $('#frmActivarUsuario').submit(function(e) {
            $("#loader").show();
            e.preventDefault();

            $.ajax({
                contentType : false, 
                processData: false,
                type: "post",
                cache: false,
                url: "usUsuario.do",
                dataType : 'json',
                data : new FormData($(this)[0]), 
                success : function(callback){
                    $(".scroller-inner").scrollTop($("#resultado").position().top);
                    $("#resultado").attr("class","");                    
                    
                    if(callback.codigo == 200) {
                        $("#resultado").addClass("alert alert-success");
                        $("#resultado").html("Se ha actualizado el usuario");
                        recargarUsuarios();
                    
                    } else {
                        $("#resultado").addClass("alert alert-danger");
                        $("#resultado").html("Error al actualzar el usuario");
                    }
                                        
                    $("#loader").hide();
                    
                    setTimeout(function() {$("#resultado").html("");$("#resultado").attr("class","");}, 5000);
                },
                error : function(){                    
                    $(".scroller-inner").scrollTop($("#resultado").position().top);
                    $("#resultado").html("Error al actualzar el usuario, inténtelo nuevamente por favor.");
                    $("#resultado").attr("class","");
                    $("#resultado").addClass("alert alert-danger");
                    $("#loader").hide();
                    setTimeout(function() {$("#resultado").html("");$("#resultado").attr("class","");}, 5000);
                }
            });            
        });    
    
});

function recargarUsuarios() {
    tableUsuarios.fnReloadAjax("lstUsuarios.do");
    tableUsuarios.fnReloadAjax();
}


function activarUsuario(idUsuario, idAccion) {
    $("#idUsuario").val(idUsuario);
    $("#idAccion").val(idAccion);
    
    $("#aviso").dialog({
       resizable: false,
       height:280,
       width: 500,
       modal: true,
       buttons: {
         "send": {
            text: 'Aceptar',
            class: 'btn btn-primary margin-right-20',
            click: function() {                        
                    $( this ).dialog("close");
                    $("#frmActivarUsuario").submit();
                  }
        },
        "cancel": {
            text: 'Cancelar',
            class: 'btn btn-default',
            click: function() {
                  $( this ).dialog( "close" );
             }
        }
       }
    }); 
}