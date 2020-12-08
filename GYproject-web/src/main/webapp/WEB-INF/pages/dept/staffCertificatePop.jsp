<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<div>
	<div class="p-t-6">
		<div class="mask-html text-center">
			<div>
				<i class="fa fa-4x fa-spinner"></i><br>
				<p class="text-ellipsis">加载中</p>
			</div>
		</div>
		<div class="clearboth form-box">
			<form id="fileupload" action="accessoryCollect/uploadFile" method="POST" enctype="multipart/form-data">
		    <input type="hidden" name="alPath" id="alPath" value="焊工证"/>
		    <input type="hidden" name="encryption" id="encryption" />
		    <input type="hidden" name="caiId" id="caiId" />
		    <input type="hidden" name="stepId" id="stepId" value="111301"/>
		    <input type="hidden" name="step" id="step" value="111301"/>
		    <input type="hidden" name="busRecordId" id="busRecordId" />
		    <input type="hidden" name="projLtypeId" id="projLtypeId" value="111301"/>
		   <input type="hidden" id="loginId" name="loginId" value="${loginName.staffId}"/>
		   <input type="hidden" id="aspectRatio" value="1.25" />
			<div class="fileupload-buttonbar">
		        <div class="pull-right toolBtn">
		            <span class="btn btn-success btn-sm fileinput-button">
		                <i class="fa fa-plus"></i>
		                <span>浏览文件...</span>
		                <input type="file" name="files[]" multiple/>	             	          
		            </span>
		            <button type="submit" class="btn btn-primary btn-sm start hidden">
	                   <!--  <i class="fa fa-upload"></i>
	                    <span>上传</span> -->
	                </button>
	                <button type="button" class="btn btn-primary btn-sm upload-btn">
	                    <i class="fa fa-upload"></i>
	                    <span>上传</span>
	                </button>
	               <!--  <a href='accessoryCollect1/T-2016081202/www/ceshi1.pdf'>查看</a> -->
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
		<table id="staffCertificateTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
     		    <th></th>
           		<th>资料名称</th>
           		<th>资料类型</th>
            	<th>上传日期</th>
            	<th>上传人</th>
            	<th width='40'>操作</th>
           	</tr>
       	</thead>
	</table>
	</div>
</div>
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="col-md-1 hidden">
                <span class="preview"></span>
            </td>
            <td width="60%">
                <p class="name filename">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td width="20%">
                <p class="size">Processing...</p>
            </td>
            <td width="20%">
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
            <td class="hidden">
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                        <!--<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>-->
                    {% } %}
                </span>
            </td>
            <td width="60%">
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
            <td width="20%">
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td width="20%">
                <div class="progress progress-striped text-center"><div class="progress-bar progress-bar-success" style="width:100%;">已上传</div></div>
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
<script>
	App.restartGlobalFunction();
	$("#deptpopform").styleFit();
	$.getScript('projectjs/dept/staff-certificate-pop.js?v='+Math.random()).done(function () {
		staffCertificatePop.init();
	}); 
	
	$.getScript('projectjs/dept/form-multiple-upload.demo1.js?v='+Math.random()).done(function() {
    	FormMultipleUpload.init($('#fileupload'));
    });
	function saveBack(data){
		var content = "上传成功！";
	    if(data === "false"){
	    	content = "数据保存失败！";
	    }else {
       		$("#filePreviews tbody").html("");
       		var step = $("#step").val();
        	if(step==''){
        		ste=getStepId();
        	}
        	accessoryData.step = step;
    		accessoryData.busRecordId=$("#staffId").val();
    		$('#staffCertificateTable').cgetData(true); 
	    }
	   /*  var myoptions = {
               	title: "提示信息",
               	content: content,
               	accept: popClose,
               	chide: true,
               	icon: "fa-check-circle",
               	newpop:'new'
	     }
	 	 $("body").cgetPopup(myoptions); */
	}

</script>
