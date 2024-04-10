
$(document).ready(function() {
    
        $("#header").load("../header.do"); 
        $("#footer").load("../footer.do");     
            
        $('#frmAddRegistro').submit(function(e) {
            
            if(!$('#frmAddRegistro').valid()) {
                return false;
            }
            
            $("#loader").show();
            e.preventDefault();
                        
            var data = {};

            $.each(this, function(i, v){
                var input = $(v);
                if(input.attr("name") == 'intereses') {
                    data[input.attr("name")] = $('input[name=intereses]:checked').val();
                } else {
                    data[input.attr("name")] = input.val();    
                }
               
                delete data["undefined"];
            });
            
            
            $.ajax({
                contentType : 'application/json; charset=utf-8',
                type: "post",
                cache: false,
                url: "addRegistro.do",
                dataType : 'json',
                data : JSON.stringify(data),
                success : function(callback){
                    $(".scroller-inner").scrollTop($("#resultado").position().top);
                    $("#resultado").attr("class","");
                    $("#resultado").html(callback.mensaje);
                    
                    if(callback.codigo == 200) {
                        $("#resultado").addClass("alert alert-success");    
                    } else {
                        $("#resultado").addClass("alert alert-danger");
                    }
                                        
                    $("#loader").hide();
                },
                error : function(){                    
                    $(".scroller-inner").scrollTop($("#resultado").position().top);
                    $("#resultado").html("Error, no fue posible realizar su registro, inténtelo nuevamente por favor.");
                    $("#resultado").attr("class","");
                    $("#resultado").addClass("alert alert-danger");
                    $("#loader").hide();
                }
            });
        });
                                 
});