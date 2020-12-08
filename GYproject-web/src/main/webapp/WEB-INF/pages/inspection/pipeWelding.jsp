<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- pipeWelding.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
                       <!--  <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a> -->
                    </div>
                    <input type="hidden" id="actionName" value="${actionName }"/>
                     <input type="hidden" id="pcIdNew">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class=""><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<input type="hidden" id="pcIdNew">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectCheckListTable" width="100%">
	                			<thead>
	                				<tr>
	                					<th></th>
		                				<th>报验日期</th>
		                				<th>施工工序</th>
		                				<th>查验结果</th>
		                				<th>完成状态</th>
	                				</tr>
	                			</thead>
	                		</table>
	                	</div>
	                   	<div class="tab-pane fade btn-top" id="default-tab-2">
	                       	<div class="toolBtn f-r p-b-10 update-show">
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole savebtn" >保存</a>
			                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
						    </div>
						    <div class="clearboth form-box">
						    	<input type="hidden" class="imgUrl" value="${imgUrl}"/>
								<form class="form-horizontal" id="signForm" action="pipewelding/saveSign">
									<input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="projId" name="projId"/>
									<input type="hidden" id="recordsId" name="recordsId">
									<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
									<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projNo">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="projName" name="projName"/>
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									<%-- <div class="form-group col-md-6 col-sm-12">
										<!-- 项目经理改为施工员 -->
										<label class="control-label signature-tool" for="constructionPrincipal">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden" id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${constPCpost}" >
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div> --%>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear allText construction"  id="inspectionDate" name="inspectionDate" data-parsley-required="true" />
										</div>
									</div> 
						    		<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
								  <div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign welder">
										<!-- 新加字段 -->
										<label class="control-label signature-tool" for="welder">焊接人员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="welder" name="welder" value="">
											<input type="hidden" id="welder_postType" name="welder_postType" value="${welderPost }" >
											<img class="welder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="welderSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="welderSignDate" name="welderSignDate"/>
										</div>
									</div>  -->
						    		<div class="form-group col-md-6 col-sm-12 allSign construction">
						    			<!-- 新加字段 -->
										<label class="control-label signature-tool" for="checker">检查人员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="checker" name="checker" value="">
											<input type="hidden" id="checker_postType" name="checker_postType" value="${checkerPost }" >
											<img class="checker" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkerSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="checkerSignDate" name="checkerSignDate"/>
										</div>
									</div>  -->
						    		<div class="form-group col-md-6 col-sm-12 allSign cuPm">
						    			<!-- 新加字段 -->
										<label class="control-label signature-tool" for="projectLeader">项目负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="projectLeader" name="projectLeader" value="">
											<input type="hidden" id="projectLeader_postType" name="projectLeader_postType" value="${projectLeaderPost }" >
											<img class="projectLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<!-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="plSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="plSignDate" name="plSignDate"/>
										</div>
									</div> -->
								</form>
							   
								<!-- 照片 -->
								<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list " id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> 
							   
							</div>
	                   	</div>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 auditEditBtn" >
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole  hidden auditInpectBtn">报验</a>
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole hidden auditAddBtn2 ">新增</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditEditBtn auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole auditEditBtn deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    		<!-- 完成标记 -->
									<input type="hidden" id="flag1" class="addClear">
		                   		<form class="form-horizontal" id="auditForm" action="" data-parsley-validate="true">
		                   			<input type="hidden" id="pipeId" name="pipeId" class="addClear"/>
									<input type="hidden" id="version1" name="version" class="addClear"/>
										<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
										<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			
							         <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="pipeWeldingDate">日期</label>
							            <div>
							            	<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="pipeWeldingDate" name="pipeWeldingDate"  data-parsley-required="true"/>
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="pipePosition">管位</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="pipePosition" name="pipePosition" data-parsley-maxlength="50"/>
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="pipeSectionLen">管段长度</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="pipeSectionLen" name="pipeSectionLen" data-parsley-maxlength="30"/>
							            	
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="jointNum">焊口数</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="jointNum" name="jointNum" data-parsley-maxlength="30"/>
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="pipeWelderName">焊工姓名及编号</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="pipeWelderName" name="pipeWelderName" data-parsley-maxlength="20"/>
							            </div>
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="weldParams">焊接参数</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="weldParams" name="weldParams" data-parsley-maxlength="255"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
								        <label class="control-label" for="isInspect">是否探伤</label>
								    	<div>
								        	<label>
								            	<input type="radio" class="field-editable" name="isInspect" value="1" checked//>是
								            </label>
								            <label>
								            	<input type="radio" class="field-editable" name="isInspect" value="0" >否
								            </label>
								        </div>
								    </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label signature-tool" for="builder">施工员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden"  id="builder_post" name="builder_post" value="${CONSTRUCTIONPOST }">
											<img class="grBuilder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							           <!--  <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="grBuilder" name="grBuilder" data-parsley-maxlength="50"/>
							            </div> -->
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							             <label class="control-label signature-tool" for="firstParty">甲方</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_5"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="firstParty" name="firstParty" value="">
											<input type="hidden"  id="firstParty_post" name="firstParty_post" value="${CUST_REPRESENTPOST }">
											<img class="firstParty" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							            <!-- <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="firstParty" name="firstParty" data-parsley-maxlength="50"/>
							            </div> -->
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							             <label class="control-label signature-tool" for="supervision">监理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="supervision" name="supervision" value="">
											<input type="hidden"  id="supervision_post" name="supervision_post" value="${SUJGJPOST }">
											<img class="supervision" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
							           <!--  <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="supervision" name="supervision" data-parsley-maxlength="50"/>
							            </div> -->
							        </div>
							        <div class="form-group col-sm-6 col-xs-12">
							            <label class="control-label" for="remarks">备注</label>
							            <div>
							            	<input type="text" class="form-control input-sm field-editable addClear" value="" id="remarks" name="remarks" data-parsley-maxlength="255"/>
							            </div>
							        </div>
							        
			                       	<table id="recordListTable" class="table table-striped table-bordered nowrap" width="100%">
			                            <thead>
			                                <tr>
			                                    <th>操作</th>
			                                    <th>日期</th>
			                                    <th>管位</th>
			                                    <th>管段长度</th>
			                                    <th>焊口数</th>
			                                    <th>焊工姓名及编号</th>
			                                    <th>焊接参数(A、T、V)</th>
			                                    <th>是否探伤</th>
			                                    <th>备注</th>
			                                </tr>
			                            </thead>
			                       	</table>
		                       	</form>
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list" id="recordImagesList">
									     <input type="hidden" id="pipeId1" />
										<input type="hidden" id="pcId1" />
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div> 
	                       	</div>
	                   	</div>
                   </div>
               	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body" id="pipeWelding_audit_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 1200px; height: 850px;border:1;overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	                </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->
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
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //初始化上传文件控件
   /*  $.getScript('projectjs/data/form-multiple-upload.demo.js').done(function() {
    	FormMultipleUpload.init();
    }); */
    App.setPageTitle('钢管焊接 - 工程项目管理系统 ');
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();

    $('.update-show').addClass('hidden');
    $('.auditEditBtn').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				  	//
    	$('#projName').val(projJson.projName);				  	//工程名称
    	$('#projId').val(projJson.projId);					   	//工程ID
    	$('#projId1').val(projJson.projId);
    	$('#projNo').val(projJson.projNo);     	 			   	//工程编号
    	$('#constructionUnit').val(projJson.cuName);			//施工单位
    	$('#suName').val(projJson.suName);					   	//监理公司
    	
    }
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
		$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
  //加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    
    
   var showReport = function(){
    	var projName='',pcId='',constructionUnit='',suName='',projNo='';
	     if(trSData.projectCheckListTable.json){
	    	 projName=trSData.projectCheckListTable.json.projName;
	    	 constructionUnit= trSData.projectCheckListTable.json.constructionUnit;
	    	 suName= trSData.projectCheckListTable.json.suName;
	    	 projNo=trSData.projectCheckListTable.json.projNo;
	     }else{
	    	    projName=$("#projName").val();
	    	    suName = $("#suName").val();	    	
	    	    constructionUnit=$("#constructionUnit").val();	
	    	    projNo=$("#projNo").val();
	     }
   	    projName=encodeURIComponent(cjkEncode(projName)); 
   	 	projNo=encodeURIComponent(cjkEncode(projNo)); 
   	    constructionUnit=encodeURIComponent(cjkEncode(constructionUnit));
   	    suName=encodeURIComponent(cjkEncode(suName));
   	 	pcId=$('#pcIdNew').val();
   	 	if(!pcId){
   	 		pcId='-1';
   	 	}
   		var src="<%=basePath%>/ReportServer?reportlet=pipeWelding/pipeWelding1.cpt&pcId="+pcId+"&projName="+projName;
   		src=src+"&constructionUnit="+constructionUnit+"&suName="+suName+"&projNo="+projNo;
   		src = src+"&imgUrl="+$(".imgUrl").val();
   		$("#mainFrm").attr("src",src);
    }
    

  	//签字加载方式
  	$.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	$("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature();  
  	});

    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 

    $.getScript('projectjs/inspection/inspection-common.js?v=1011').done(function() {
    	$.getScript('projectjs/inspection/pipeWelding-audit.js?v=1011').done(function () {
    		pipeWeldingAndAudit.init();
    	});
    });
  //移动端点击上传
    $("#projectImagesList .uploadBtn").off("click").on("click",function(){
	    var pcId = $("#pcId").val(),
		projNo = $("#projNo").val(),
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=pcId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#pcId").length && !$("#pcId").val()) {
		        navigator.notification.alert('请先保存该条记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    if (files.length) {
		    	uploadImages(files, 0, files.length, projId, projNo, stepId, busRecordId);
		    } else {
		        navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		    }
		}else{
			 navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		};
	})
	
	 //移动端点击上传
    $("#recordImagesList .uploadBtn").off("click").on("click",function(){
    	var pcId = $('#pcId1').val();
		var projId = $("#projId").val(),projNo=$('#pipeId1').val(),
		stepId=getStepId();
	    var busRecordId=pcId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		     if (!projNo && $("#pipeId1").length && !$("#pipeId1").val()) {
		        navigator.notification.alert('请先保存记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    /* if (!busRecordId && $("#pcId").length && !$("#pcId").val()) {
		        navigator.notification.alert('请先保存记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    } */
		    if (files.length) {
		    	uploadImages(files, 0, files.length, projId, projNo, stepId, busRecordId);
		    } else {
		        navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		    }
		}else{
			 navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		};
	})
	//限制上传多个照片
	var surplusAccept=function(){
		$(".surplusDelBtn").removeClass("hidden");
	}
	
	//上传多个照片后删除
	$(".surplusDelBtn").off().on("click",function(){
		$("#filePreviews tbody").html("");
		$(".surplusDelBtn").addClass("hidden");
	})
</script>

<!-- ================== END PAGE LEVEL JS ================== -->