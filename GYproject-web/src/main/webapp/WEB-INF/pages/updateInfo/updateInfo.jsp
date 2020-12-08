<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectAccept.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
   		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">  
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab" data-toggle="tab">更新列表</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">附件列表</a></li>
                	</ul>
                    
                </div>
                <div class="panel-body">
                	<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               				<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
		                    <table id="updateInfoTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
		                    	<thead>
		                            <tr>
		                            	<th>工程ID</th>
		                                <th>更新编号</th>
		                                <th>更新标题</th>
		                                <th>更新时间</th>
		                            </tr>
		                        </thead>
		                    </table>
               			</div>
               			<div class="tab-pane fade in btn-top" id="default-tab-2">
               				<div class="importtool">
               					 <input type="hidden" name="changeType" id="changeType" value="2"/>
								<form id="fileupload" action="accessoryCollect/uploadFile" method="POST" enctype="multipart/form-data">
								    <input type="hidden" name="alPath" id="alPath" value="0104"/>
								    <input type="hidden" name="encryption" id="encryption" />
								    <input type="hidden" name="caiId" id="caiId" />
								    <input type="hidden" name="stepId" id="stepId" />
								    <input type="hidden" name="step" id="step" />
								    <input type="hidden" name="projLtypeId" id="projLtypeId" />
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
							<table id="dataPopTableSecond" class="table table-striped table-bordered nowrap" width="100%">
						   		<thead>
						     		<tr>
						     			<th></th>
						     		    <th></th>
						           		<th>资料名称</th>
						           		<th>资料类型</th>
						            	<th>签收日期</th>
						            	<th>签收人</th>
						            	<th width='40'>操作</th>
						           	</tr>
						       	</thead>
							</table>
               			</div>
               		</div>		
                    
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			        	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">更新区</h4>
			    </div>
			    <div class="panel-body" id="update_info_panel_box">
			    </div>
			</div>
        </div>
      <!-- end col-6 -->
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
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('更新信息 - 工程项目管理系统 ');
  //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo6.js?v='+Math.random()).done(function() {
    	FormMultipleUpload.init($('#fileupload'));
    });
    $.getScript('projectjs/updateInfo/update-info.js').done(function () {
    	updateInfo.init();
    });  
	
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->