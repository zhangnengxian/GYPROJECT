<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

 <div class="importtool">
     <form id="fileupload" action="${url}" method="POST" enctype="multipart/form-data">
     	<input type="hidden" id="costType" name="costType"/>
     	<input type="hidden" id="projId5" name="projId"/>
     	<input type="hidden" id="cmId1" name="cmId"/>
     	<input type="hidden" id="mcType1" name="mcType"/>
         <div class="fileupload-buttonbar">
			 <div class="pull-right toolBtn">
				 <span class="btn btn-success btn-sm fileinput-button">
				    <i class="fa fa-plus"></i>
				    <span>浏览文件...</span>
				    <input type="file" name="files[]" multiple/>	             	          
			     </span>
				 <button type="submit" class="btn btn-primary btn-sm start">
				    <i class="fa fa-upload"></i>
				    <span>上传</span>
				 </button>
			 </div>
			 <!-- The global progress state -->
			 <div class="col-md-12 fileupload-progress fade hidden">
			 <!-- The global progress bar -->
			     <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
					  <div class="progress-bar progress-bar-success" style="width:0%;"></div>
				 </div>
				 <!-- The extended global progress state -->
				 <div class="progress-extended">&nbsp;</div>
			</div>
		</div>
		<!-- The table listing the files available for upload/download -->
		<table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
	 </form>
</div>


<!-- end #content -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td>
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td>
                <p class="size">Processing...</p>
                <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="hidden">
                {% if (!i && !o.options.autoUpload) { %}
                    <button class="btn btn-primary btn-sm start" disabled>
                        <i class="fa fa-upload"></i>
                        <span>Start</span>
                    </button>
                {% } %}
                {% if (!i) { %}
                    <button class="btn btn-white btn-sm cancel">
                        <i class="fa fa-ban"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td>
                <p class="name">
                    {% if (file.url) { %}
                        <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                        <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                    <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td>
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td class="hidden">
                {% if (file.deleteUrl) { %}
                    <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" name="delete" value="1" class="toggle">
                {% } else { %}
                    <button class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                {% } %}
            </td>
        </tr>
    {% } %}
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
$.getScript('projectjs/data/form-multiple-upload.demo.js').done(function() {
	FormMultipleUpload.init();
	$("#projId5").val($("#projId1").val());
	console.info("projId1--"+$("#projId1").val());
	console.info("projId5---"+$("#projId5").val());
	
	$("#costType").val($("#costType1").val());
	if($("#cmId").val()!=undefined){
		$("#cmId1").val($("#cmId").val());
	}
	if($("#mcType").val()!=undefined){
	$("#mcType1").val($("#mcType").val());
	}
});
$("#filePreviews").hideMask();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->