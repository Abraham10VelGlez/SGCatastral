$(document).ready(function() {
    
    $("#header").load("../header.do"); 
    $("#footer").load("../footer.do");     
    
    $('#frmEditPerfil').submit(function(e) {
        
        var data = {};

        $.each(this, function(i, v){
            var input = $(v);            
            data[input.attr("name")] = input.val();    
            delete data["undefined"];
        });
    
        data = JSON.stringify(data);
            
        e.preventDefault();

        if(!$('#frmEditPerfil').valid()) {
            return false;
        }
                
        if($("#pwd").val().trim() == "") {
            editPerfil(data);
        } else {            
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
                             editPerfil(data);
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
    });
});

function editPerfil(data) {
    
    $("#loader").show();

    $.ajax({
        contentType : 'application/json; charset=utf-8',
        type: "post",
        cache: false,
        url: "editPerfil.do",
        dataType : 'json',
        data : data,
        success : function(callback){
            $(".scroller-inner").scrollTop($("#resultado").position().top);
            $("#resultado").attr("class","");
            $("#resultado").html(callback.mensaje);

            if(callback.codigo == 200) {
                $("#resultado").addClass("alert alert-success");
                $("#header").load("../header.do"); 
            } else {
                $("#resultado").addClass("alert alert-danger");
            }

            $("#loader").hide();
        },
        error : function(){                    
            $(".scroller-inner").scrollTop($("#resultado").position().top);
            $("#resultado").html("Error, no fue actualizar la información, inténtelo nuevamente por favor.");
            $("#resultado").attr("class","");
            $("#resultado").addClass("alert alert-danger");
            $("#loader").hide();
        }
    });    
}