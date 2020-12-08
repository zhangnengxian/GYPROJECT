/*
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/

var handleJqueryFileUpload = function(formObj) {
	var FileTypes =/(\.|\/)(png|jpg)$/i;
	if($("#changeType").val()==2){
		FileTypes =/(\.|\/)(xlsx|xls|docx|png|jpg|pdf|zip|rar|7z|doc|txt|dwg)$/i;
	}
	console.log("file upload....");
     // Initialize the jQuery File Upload widget:
	    formObj.fileupload({
        autoUpload: false,
        disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
        maxFileSize: 500000000,
        acceptFileTypes: FileTypes,
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},                
    });

    // Enable iframe cross-domain access via redirect option:
	    formObj.fileupload(
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
            $('<div class="alert alert-danger"/>').text('Upload server currently unavailable - ' + new Date()).appendTo('#designAlterationForm');
        });
    }

    // Load & display existing files:
    formObj.addClass('fileupload-processing');
    $.ajax({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        url: formObj.fileupload('option', 'url'),
        dataType: 'json',
        context: formObj
    }).always(function () {
        $(this).removeClass('fileupload-processing');
    }).done(function (result) {
        $(this).fileupload('option', 'done').call(this, $.Event('done'), {result: result});
    });
    
    $(".upload-btn").off("click").on("click",function(){
    	if($("#staffId").val()==""){
    		alertInfo("请先保存员工记录，再上传资质证书！");
    	}else{
    		console.info("---"+$("#welderId").val());
    		$("#projLtypeId").val(getStepId());
    		$('#busRecordId').val($("#staffId").val());
        	$("#encryption").val("0");
        	$(".start").click();
    	}
    });
    
    
   
    
    formObj.off('fileuploaddone').on('fileuploaddone', function(e, data){
    	console.log("fileuploaddone***********************************");
    	console.log(data.result.result);
    	console.log(data.result);
    	$("#drawName").val(data.files[0].name);
    	$("#layoutDiagram").val(data.files[0].name);
    	saveBack(data.result);
    	/*$("#dataPopTableSecond").DataTable().ajax.reload(function(){
			$("#dataPopTableSecond").trigger("reload.dt");
		});
    	if($("#caiId")){
     	   $("#caiId").val('');
        }
    	$(this).closest(".modal-content").find(".pop-confirm").removeClass("disabled");*/
    });
    
};


var FormMultipleUpload = function () {
	"use strict";
    return {
        //main function
    
        init: function (formObj) {
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
                                                                        handleJqueryFileUpload(formObj);
                                                                    });
                                                                } else {
                                                                    handleJqueryFileUpload(formObj);
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