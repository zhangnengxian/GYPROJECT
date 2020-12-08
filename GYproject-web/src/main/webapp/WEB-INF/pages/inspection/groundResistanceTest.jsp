<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
                    </div>
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">报验区</a></li>
		                <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
               			<div class="tab-pane fade active in btn-top" id="default-tab-1" >
               			    <input type="hidden" id="pcIdNew">
               			    <!--<input type="hidden" id="addShow"> -->
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="altimetricTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
			                			<th>报验日期</th>
			                			<th>施工工序</th>
			                			<th>查验结果</th>
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
								<form class="form-horizontal" id="altimetricSurveyForm" data-parsley-validate="true" action="groundResistanceTest/saveGroundResistanceTestFile">
									<input type="hidden" id="result" name="result">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="drawUrl" name="drawUrl">
									<input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl1}">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${pcDesId}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
								 	<input type="hidden" id="projNo" name="projNo" > 
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="groundingType">接地种类</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" id="groundingType" name="groundingType"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="underLinearType">引下线型式</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear" id="underLinearType" name="underLinearType"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="slResultPage">附件数量</label>
										<div>
											<input type="text" class="form-control input-sm field-editable addClear"  id="slResultPage" name="slResultPage"  />
										</div>
									</div>
									
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionPrincipal">项目经理</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionPrincipal" name="constructionPrincipal" value="">
											<input type="hidden"  id="constructionPrincipal_postType" name="constructionPrincipal_postType" value="${constPCpost }">
											<img class="constructionPrincipal" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
						    		<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm field-editable datepicker-default addClear"  id="inspectionDate" name="inspectionDate"  />
										</div>
									</div> 
									<div class="form-group col-sm-12">
										<label class="control-label" for="">布置简图</label>
										<div>
										<div class="hidden hasVal"> 
						         			<input type="text" class="form-control input-sm field-not-editable" id="layoutDiagram" name="layoutDiagram"/>
					         		        <a class="btn btn-sm btn-primary Search_Button" href="javascript:">查看</a>
					         		        <a class="del_btn btn btn-sm btn-danger"><i class="fa fa-times"></i> 删除</a>
					         		    </div>
					         		    
										<div class="fileupload-buttonbar noVal hidden">
									        <div class="pull-right toolBtn">
									            <span class="btn btn-success btn-sm fileinput-button">
									                <i class="fa fa-plus"></i>
									                <span>浏览文件...</span>
									                <input type="file" name="files[]" multiple/>	             	          
									            </span>
									            <button type="submit" class="btn btn-primary btn-sm start hidden">
								                    <i class="fa fa-upload"></i>
								                    <span>上传</span>
								                </button>
									        </div>
									    </div>
									    <!-- The table listing the files available for upload/download -->
									    <table role="presentation" id="filePreviews" class="table table-striped "><tbody class="files"></tbody></table>
										</div>
									</div>
						    		<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQc">质检员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden"  id="constructionQc_post" name="constructionQc_post" value="${constructionQcpost }">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="selfCheckDate">自检日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear"  id="selfCheckDate" name="selfCheckDate"  />
										</div>
									</div> 
									 <%-- <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="asPerson">测量员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="asPerson" name="asPerson" value="">
											<input type="hidden"  id="asPerson_postType" name="asPerson_postType" value="${asPersonpost }">
											<img class="asPerson" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div> --%>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="constructionQae">质保师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQae" name="constructionQae" value="">
											<input type="hidden"  id="constructionQae_postType" name="constructionQae_postType" value="${constructionQaePost}">
											<img class="constructionQae" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="suOpinion">监理意见</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="suOpinion" id="suOpinion" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
					  			    <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  value="" id="suName" name="suName"  />
										</div>
								    </div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label signature-tool" for="">监理工程师</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_6"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="">
											<input type="hidden"  id="suJgj_postType" name="suJgj_postType" value="${suJgjpost }">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="checkDate">日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear" value="" id="checkDate" name="checkDate"  />
										</div>
									</div>
									
								</form>
								<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list" id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div>
							</div>
	                   	</div>
	                   	<div class="tab-pane fade  btn-top" id="default-tab-3">
	                   		<div class="toolBtn f-r p-b-10 editbtn" >
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole altimeAddbtn">增加</a>
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole altimeSavebtn">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole deleteBtn">删除</a>
						    </div>
						    <div class="clearboth form-box">
		                   		<form class="form-horizontal" id="altimetricSurveyForm2" data-parsley-validate="true" action="">
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="testPoint">测试点</label>
							            <div >
							                <input type="text" class="form-control input-sm"  id="testPoint"  name="testPoint" />
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="specifiedResistance">规定阻值</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="specifiedResistance"  name="specifiedResistance" data-parsley-type="number"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="testResistance">实测阻值</label>
							            <div>
							            	<input type="text" class="form-control input-sm"  id="testResistance"  name="testResistance" data-parsley-type="number"/>
							            </div>
							        </div>
							        <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="testDate">实测日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default " value="" id="testDate" name="testDate"  />
										</div>
									</div>
									 <div class="form-group col-sm-6 col-xs-12 ">
								    	<label class="control-label" for="">天气</label>
								        <div class="weather-tool">
									        <label weather="晴天"><img src="images/weather/sunshine.png"><img class="weather-on" src="images/weather/sunshine-on.png"></label>
									        <label weather="阴天"><img src="images/weather/cloudy.png"><img class="weather-on" src="images/weather/cloudy-on.png"></label>
									        <label weather="下雨"><img src="images/weather/rain.png"><img class="weather-on" src="images/weather/rain-on.png"></label>
									        <label weather="下雪"><img src="images/weather/snow.png"><img class="weather-on" src="images/weather/snow-on.png"></label>
									        <input type="hidden" id="dlWeather" name="dlWeather" value="">
								        </div>
								    </div>
							        <div class="form-group col-md-6 col-sm-12">
							            <label class="control-label" for="testResult">测试结果</label>
							            <div>
							            	<input type="text" class="form-control  input-sm"  id="testResult" name="testResult" value=""/>
							            </div>
							        </div>
			                       	<table id="altimetricSurveyAuditTable" class="table table-striped table-bordered nowrap" width="100%">
			                            <thead>
			                                <tr>
			                                    <th>测试点</th>
			                                    <th>规定阻值</th>
			                                    <th>实测阻值</th>
			                                    <th>实测日期</th>
			                                    <th>天气情况</th>
			                                    <th>测试结果</th>
			                                </tr>
			                            </thead>
			                       	</table>
		                       	</form>
		                    </div>
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
			         <h4 class="panel-title">预览区</h4>
			    </div>
			    <div class="panel-body" id="altimetric_survey_audit_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 795px; height: 1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
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
    App.setPageTitle('高程测量 - 工程项目管理系统 ');
    $.getScript('projectjs/data/form-multiple-upload.demo3.js').done(function() {
    	FormMultipleUpload.init($('#altimetricSurveyForm'));
    });
    $("#altimetricSurveyForm").toggleEditState().styleFit();
    $("#altimetricSurveyForm2").toggleEditState().styleFit();
    
    $('.update-show').addClass('hidden');
    $('.editBtn').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    }
    
  	//签字加载方式
    $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6").handleSignature();  
    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    var savedone = function(){
   	 if($("#layoutDiagram").val()){
   		 $(".hasVal").removeClass("hidden");
   		 $(".noVal").addClass("hidden");
   		 $(".noVal+#filePreviews tr").remove();
   	 }else{
   		 $(".noVal").removeClass("hidden");
   		 
   		 $(".hasVal").addClass("hidden");
   	 }
   }
    function saveBack(data){
       	var content = "数据保存成功！";
       	if(data === "false"){
       		content = "数据保存失败！";
       	}else {
       		$('#pcIdNew').val(data.operateId);
        	$('#pcId').val(data.operateId);
        	$('.update-show').addClass("hidden");
        	$('#altimetricSurveyForm').toggleEditState(false);
        	var pcId = $('#pcId').val(),
    		projNo = $("#projNo").val();
    		projId = $("#projId").val();
    		
    		if(_inApk && $(".attach-images-list").find(".new-image").length){
    			preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
    		};
        	showReport1();
       		
       	}
       	var myoptions = {
               	title: "提示信息",
               	content: content,
               	accept: savedone,
               	chide: true,
               	icon: "fa-check-circle"
           }
           $("body").cgetPopup(myoptions);
    }
   //报验区保存功能
    $(".savebtn").on("click",function(){
    	//验证
    	if($("#altimetricSurveyForm").parsley().isValid()){
    		if($(".noVal+#filePreviews tr").length){
    		 	//序列化
    	       	var menuDesc = $('[data-mid="' + getStepId() + '"]').text();
    	       	$("#menuDes").val(menuDesc);
    	       	var data=$("#altimetricSurveyForm").serializeJsonString();
    	       	$("#result").val(data);
    	       	$(".start").click(); 
    	       	//保存图片
    	       	var pcId = $("#pcId").val(),
    			projNo = $("#projNo").val();
    			projId = $("#projId").val();
    			
    			if(_inApk && $(".attach-images-list").find(".new-image").length){
    				preImagesUpload($(".attach-images-list .uploadBtn"), projId, projNo, getStepId(), pcId);
    			};
    		}else{
    		 	var dataStr=$("#altimetricSurveyForm").serializeJsonString();
    		 	var data={};
    		 	data.result=dataStr
    			$.ajax({
    	               type: 'POST',
    	               url: 'groundResistanceTest/saveGroundResistanceTest',
    	               contentType: "application/json;charset=UTF-8",
    	               data: JSON.stringify(data),
    	               success: function (data) {
    	            	var data=JSON.parse(data);
    	               	var content = "数据保存成功！";
    	               	if(data.result === "false"){
    	               	   content = "数据保存失败！";
    	               	}else if(data.result === "true"){
    	               		//$(".toolBtn").addClass("hidden");
    	               		saveBack(data);
    	               	}
    	               	var myoptions = {
    	                       	title: "提示信息",
    	                       	content: content,
    	                       	accept: savedone,
    	                       	chide: true,
    	                       	icon: "fa-check-circle"
    	                   }
    	                   $("body").cgetPopup(myoptions);
    	               },
    	               error: function (jqXHR, textStatus, errorThrown) {
    	                   console.warn("签证信息保存失败！");
    	               }
    	           });
    		}
       	//如果通过验证, 则移除验证UI
       	$("#altimetricSurveyForm").parsley().reset();
       	/* $('ul.nav>li').removeClass("active");
		$('#visaTab').click(); */
       }else{
       	//如果未通过验证, 则加载验证UI
       	$("#altimetricSurveyForm").parsley().validate();
       }    
    	$("#altimetricSurveyForm").toggleEditState(false);
    	//$("#altimetricSurveyForm").cformSave("altimetricTable",saveBack,false);
    });
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $.getScript('projectjs/inspection/ground-resistance-test.js').done(function () {
        altimetricSurveyAndAudit.init();
	});
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区和报验区的cpt文件
    var showReport1 = function(){
        var projName='',suName='',process='',inspectionResult='',constructionUnit='',suOpinion='',suJgj='',projName='',slResultPage='',selfCheckDate='',checkDate='',inspectionDate='';
        var json=trSData.altimetricTable.json;
        var pcId;
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
       	    suName =json.suName;
    	    process =json.process;
            constructionUnit =json.constructionUnit;
            inspectionResult=json.inspectionResult;
            suOpinion=json.suOpinion;
            slResultPage=json.slResultPage;
        	checkDate=json.checkDate;
        	selfCheckDate=json.selfCheckDate;
        	inspectionDate=json.inspectionDate;
        	pcId = json.pcId;
        }else{
        	//报验区保存后不刷新页面，cpt文件中数据从报验区获取
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
    	    process = $("#process").val();
            constructionUnit = $("#constructionUnit").val();
            inspectionResult=$("#inspectionResult").val();
            suOpinion=$("#suOpinion").val();
            slResultPage=$("#slResultPage").val();
    	    inspectionDate=$("#inspectionDate").val();    	   
    	    checkDate=$("#checkDate").val();
    	    selfCheckDate=$("#selfCheckDate").val();
    	    pcId = $("#pcId").val();
        }

    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	process = encodeURIComponent(cjkEncode(process));
        	inspectionResult=encodeURIComponent(cjkEncode(inspectionResult));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	suOpinion= encodeURIComponent(cjkEncode(suOpinion));   	
        //cpt路径及参数
    	var src=cptPath+"/ReportServer?reportlet=altimetricSurvey/projectSurvey.cpt&suName="+suName+"&process="+process+"&constructionUnit="+constructionUnit+"&selfCheckDate="+selfCheckDate;
    	src=src+"&checkDate="+checkDate+"&inspectionResult="+inspectionResult+"&inspectionDate="+inspectionDate+"&suOpinion="+suOpinion+"&slResultPage="+slResultPage+"&projName="+projName;
    	src = src+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }; 
    //加载记录区的cpt文件
    showReport2 = function(){
    	var pcId='',projName='',groundingType='',underLinearType='',constructionUnit='',inspectionDate='',suName='',menuDes,drawUrl1;
	    if(trSData.altimetricTable.json){
	    	 pcId=trSData.altimetricTable.json.pcId;
	    	 projName =trSData.altimetricTable.json.projName;
	    	 groundingType= trSData.altimetricTable.json.groundingType;
	    	 underLinearType= trSData.altimetricTable.json.underLinearType;
	    	 inspectionDate=trSData.altimetricTable.json.inspectionDate;
	    	
	    }else{
	    	pcId=$('#pcId').val();
	    	projName=$('#projName').val();
	    	inspectionDate=$('#inspectionDate').val();
	    }
	    constructionUnit=$('#constructionUnit').val();//施工单位
	    suName=$('#suName').val(),				      //监理单位
	    
    	projName=encodeURIComponent(cjkEncode(projName));    	    
    	groundingType=encodeURIComponent(cjkEncode(groundingType));
    	underLinearType=encodeURIComponent(cjkEncode(underLinearType)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit));
    	suName=encodeURIComponent(cjkEncode(suName));
    	menuDes= encodeURIComponent(cjkEncode($("#menuDes").val()));
    	drawUrl1= $("#drawUrl1").val();
    	var src=cptPath+"/ReportServer?reportlet=groundResistanceTest/groundResistanceTest.cpt"+"&projName="+projName+"&groundingType="+groundingType+"&underLinearType="+underLinearType+"&constructionUnit="+constructionUnit+"&suName="+suName+"&menuDes="+menuDes+"&drawUrl1="+drawUrl1;
    	src=src+"&checkDate="+checkDate+"&inspectionDate="+inspectionDate+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src);
    };
    
    
    var showReport3 = function(){
    	var projName='',constructionUnit='',suName='';
   		projName =$('#projName').val(),			      //工程名称
   		suName=$('#suName').val(),				      //监理单位
   		constructionUnit=$('#constructionUnit').val();//施工单位
   		var pcId = $("#pcId").val();
    	projName=encodeURIComponent(cjkEncode(projName)); 
    	constructionUnit=encodeURIComponent(cjkEncode(constructionUnit)); 
    	suName=encodeURIComponent(cjkEncode(suName)); 
    	var src = cptPath+"/ReportServer?reportlet=altimetricSurvey/projectSurvey.cpt&projName="+projName+"&suName="+suName+"&constructionUnit="+constructionUnit+"&pcId="+pcId+"&imgUrl="+$(".imgUrl").val();
    	$("#mainFrm").attr("src",src); 
    }
  	//删除附件列表记录
	$(".del_btn").on("click",function(){
		$("body").cgetPopup({
		title: '提示',
		content: '您确定删除该文件信息吗？',
	    accept: {
			func: deleteDone,	//函数名
			singleArgs: $(this)	//参数，用于向func传入多个参数，不设置该属性或设置为[]，则传入不带参数的函数
		}
	});
		});
	function deleteDone(){
		$("#layoutDiagram").val("");
		console.info($("#layoutDiagram").val());
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
		
	}
	 //查看图片
    $('.Search_Button').off("click").on("click",function(){  
    	console.info("========================"+$("#drawUrl").val());
    	$("body").cgetPopup({
			title: '查看签名',
			content: '<div class="preview-box"><img src="attachments/' +$("#drawUrl").val() + '" class="preview-image"></div>',
			accept: function(){},
			atext: '关闭',
			chide: true,
			size: 'large',
			icon: 'fa-pencil-square-o'
		});
    });
	 
  //移动端点击上传
    $(".uploadBtn").off("click").on("click",function(){
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
</script>

<!-- ================== END PAGE LEVEL JS ================== -->