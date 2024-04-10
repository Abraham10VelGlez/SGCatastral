var tablePuntos;
$(document).ready(function() {
                
    $("#header").load("../header.do");
    $("#footer").load("../footer.do"); 
    
    tablePuntos = $('#puntos').dataTable({
        "bprocessing": true,
        "search": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "sAjaxSource": "lstPuntos.do",                
        "order": [[ 0, "asc" ]],
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
            { data: "folio"},            
            { data: "icono" },            
            { data: "fecha" },            
            { data: "folio" },            
            { data: "folio" }
        ],
        "columnDefs": [ 
            {
              "render": function ( data, type, row ) {                                                       
                    return '<img src="../images/REPOSITORIO_WINGSTOP/' + data + '" width="25" height="25">';                    
              },
              "targets": 1
            },
            {
              "render": function ( data, type, row ) {                                                                           
                    return '<a href="#" title="editar" onclick="editarPunto(' + data + ')" class="btn btn-default btn-xs"><i class="fa fa-edit"></i>Ver/Editar</a>';                    
              },
              "targets": 3
            },
            {
              "render": function ( data, type, row ) {                                                       
                    return '<a href="#" title="eliminar" onclick="eliminarPunto(' + data + ')" class="btn btn-default btn-xs"><i class="fa fa-trash"></i>Eliminar</a>';
              },
              "targets": 4
            }
        ]
    });
    
    $('#frmDelPunto').submit(function(e) {
        $("#loader").show();
            $("#resultado").html("");
            e.preventDefault();

            $.ajax({
                contentType : false, 
                processData: false,
                type: "post",
                cache: false,
                url: "delPunto.do",
                dataType : 'json',
                data : new FormData($(this)[0]), 
                success : function(callback){                    
                    $("#resultado").attr("class","");
                    $("#resultado").html(callback.mensaje);
                    
                    if(callback.codigo == 200) {
                        $("#resultado").addClass("alert alert-success");                                              
                        tablePuntos.fnReloadAjax( 'lstPuntos.do' );
                        tablePuntos.fnReloadAjax();
                    
                    } else {
                        $("#resultado").addClass("alert alert-danger");                        
                    }
                                        
                    $("#loader").hide();
                    setTimeout(function(){$("#resultado").html("");$("#resultado").attr("class","");}, 5000);
                },
                error : function(){                                        
                    $("#resultado").html("Error, no fue posible eliminar el punto, inténtelo nuevamente por favor.");
                    $("#resultado").attr("class","");
                    $("#resultado").addClass("alert alert-danger");
                    $("#loader").hide();
                    setTimeout(function(){$("#resultado").html("");$("#resultado").attr("class","");}, 5000);
                }
            });            
        });    
    
});

function editarPunto(idPunto) {    
    $("#idPunto").val(idPunto);    
    $("#frmEditarPuntoById").submit();        
}

function eliminarPunto(idPunto) {    
    $("#delIdPunto").val(idPunto);
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
                    $("#frmDelPunto").submit();
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
