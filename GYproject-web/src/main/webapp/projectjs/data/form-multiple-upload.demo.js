/*
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/

var handleJqueryFileUpload = function() {
     // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        autoUpload: false,
        disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
        maxFileSize: 100000000,
        //acceptFileTypes: /(\.|\/)(xlsx|xls|docx|png|jpg|jpeg|pdf|zip|rar|7z|doc|txt|dwg|gpb9|gpb|gpb8)$/i,
        acceptFileTypes: /(\.|\/)(xlsx|xls|docx|png|jpg|jpeg|pdf|zip|rar|7z|doc|txt|dwg|gbg[0-9]*$|gpb[0-9]*$|GBQ[0-9]*$|gbq[0-9]*$)$/i,
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},                
    });
    $('#fileupload').on("fileuploadfail",function(){
    });
   
    // Enable iframe cross-domain access via redirect option:
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

    // Upload server status check for browsers with CORS support:
    if ($.support.cors) {
        $.ajax({
            type: 'HEAD'
        }).fail(function () {
            $('<div class="alert alert-danger"/>').text('Upload server currently unavailable - ' + new Date()).appendTo('#fileupload');
        });
    }

    // Load & display existing files:
    $('#fileupload').addClass('fileupload-processing');
    $.ajax({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        url: $('#fileupload').fileupload('option', 'url'),
        dataType: 'json',
        context: $('#fileupload')
    }).always(function () {
        $(this).removeClass('fileupload-processing');
    }).done(function (result) {
        $(this).fileupload('option', 'done').call(this, $.Event('done'), {result: result});
    });
    $(".upload-btn").off("click").on("click",function(){
    	if($(".template-upload .start").length>0){
    		if($(".template-upload .start").length<=$(".template-upload .start[disabled]").length)return false;
    	}else{
    		return false;
    	}

    	var file = $(".filename").text(),
            file = file.substring(0, file.lastIndexOf('.')),
            filename = $("#dataPopTableSecond").DataTable().ajax.json().data;

        reNameValidation(filename,file);

        // var  content="<form id='encryptionForm' data-parsley-validate='true'><div><label>是否对文档加密&nbsp; </label>"
        // $("body").cgetPopup({
		// 	title: '加密',
		// 	content: content+ " &nbsp; 是&nbsp; <input type='radio' name='encryption' value='1'/>"+"&nbsp; &nbsp; 否&nbsp; <input type='radio' name='encryption' value='0' checked/>"+"</div></form>",
		// 	accept: acceptFun,
		// 	chide: true
		// });
    });

    function reNameValidation(filename,file) {
        if(filename.length > 0){
            var name = new Array() ;
            $.each(filename,function (n,v) {
                name.push(v.alName);
            })
            if ( $.inArray(file, name)!= -1 ) {
                $("body").cgetPopup({
                    title: '提示',
                    content: "文件名已存在",
                    accept: function (){
                        $("#filePreviews tbody").html("");
                    },
                    chide: true
                });
                return false;
            }else{
                acceptFun();
            }
        }else{
            acceptFun();
        }
    };

    function acceptFun(){
    	console.info("stepId--"+$("#stepId").val());
    	console.info("corpId--"+$(".corpId").val());
    	var encryptionVal="0";
    	if($("#stepId").val()=="1303" && $(".corpId").val()=="1102"){
    		encryptionVal="1";
    	}
    	
    	//var encryptionVal=$("#encryptionForm input[type='radio']:checked").val();
    	
    	if(encryptionVal!==undefined){
    		$("#encryption").val(encryptionVal);
    		$(".start").click();
    	}
    	
    	
    	
    }
    $(".upload-btn-no-limit").off("click").on("click",function(){
    	$(".start").click();
    });
    $('#fileupload').off('fileuploaddone').on('fileuploaddone', function(e, data){
    	if(typeof saveImportBack === 'function') saveImportBack(data);
    	$("#dataPopTableSecond").DataTable().ajax.reload(function(){
			$("#dataPopTableSecond").trigger("reload.dt");
		});
    	if($("#caiId")){
     	   $("#caiId").val('');
        }
    	$(this).closest(".modal-content").find(".pop-confirm").removeClass("disabled");
    });
    
};


var FormMultipleUpload = function () {
	"use strict";
    return {
        //main function
        init: function () {
            $.getScript('assets/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js').done(function() {
                $.getScript('assets/plugins/jquery-file-upload/js/vendor/tmpl.min.js').done(function() {
                    $.getScript('assets/plugins/jquery-file-upload/js/vendor/load-image.min.js').done(function() {
                        $.getScript('assets/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js').done(function() {
                            $.getScript('assets/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js').done(function() {
                                $.getScript('assets/plugins/jquery-file-upload/js/jquery.iframe-transport.js').done(function() {
                                    $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload.js').done(function() {
                                        $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload-process.js').done(function() {
                                            $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload-image.js').done(function() {
                                                $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload-audio.js').done(function() {
                                                    $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload-video.js').done(function() {
                                                        $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload-validate.js').done(function() {
                                                            $.getScript('assets/plugins/jquery-file-upload/js/jquery.fileupload-ui.js').done(function() {
                                                                if ($.browser.msie && parseFloat($.browser.version) >= 8 && parseFloat($.browser.version) < 10) {
                                                                    $.getScript('assets/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js').done(function() {
                                                                        handleJqueryFileUpload();
                                                                    });
                                                                } else {
                                                                    handleJqueryFileUpload();
                                                                }
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        }
    };
}();