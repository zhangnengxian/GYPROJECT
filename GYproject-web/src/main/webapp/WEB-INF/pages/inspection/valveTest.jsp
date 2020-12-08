<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
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
                    <input type="hidden" id="actionName" value="${actionName }"/>
                    <input type="hidden" id="pcIdNew">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a href="#default-tab-1" id="listTab"  data-toggle="tab">列表区</a></li>
                    	 <li class=""><a href="#default-tab-3" id="auditTab"   data-toggle="tab">记录区</a></li>
		                <li class="" id="addTab"><a href="#default-tab-2" id="signTab"  data-toggle="tab">签字区</a></li>
                	</ul>
                </div>
               	<div class="panel-body" id="">
               		<div class="tab-content">
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
								<form class="form-horizontal" id="signForm"  enctype="multipart/form-data" data-parsley-validate="true" action="valveTest/saveSign">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
									<input type="hidden" id="version" name="version" class="addClear"/>
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
									<div class="form-group  col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm "  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">验收日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText construction" value="" id="inspectionDate" name="inspectionDate" data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear allText construction" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
									<!-- 
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="process">施工工序</label>
										<div> 
											<input type="text" class="form-control input-sm field-editable addClear"  id="process" name="process"  />
										</div>
									</div>
									 -->
									<div class="form-group col-sm-12">
										<label class="control-label" for="constructionUnit">施工单位</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" id="constructionUnit" name="constructionUnit"  />
										</div>
									</div>
									  <!-- <div class="form-group  col-sm-12">
										<label class="control-label" for="suName">监理公司</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm" value="" id="suName" name="suName"/>
										</div>
								    </div> -->
						    		<!-- <div class="form-group col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear" name="inspectionResult" id="inspectionResult" rows="2" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div> --> 
					  			    
								    <div class="form-group col-md-6 col-sm-12 allSign">
										<label class="control-label signature-tool" for="technicalLeader">技术负责人</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="technicalLeader" name="technicalLeader" value="">
											<input type="hidden"  id="technicalLeader_post" name="technicalLeader_post" value="${technicalLeaderPost }">
											<img class="technicalLeader" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="tlSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText" value="" id="tlSignDate" name="tlSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign">
										<label class="control-label signature-tool" for="testLeader">试验班（组）长</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="testLeader" name="testLeader" value="">
											<input type="hidden"  id="testLeader_post" name="testLeader_post" value="${ testLeaderPost}">
											<img class="suJgj" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="testlSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText" value="" id="testlSignDate" name="testlSignDate"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12 allSign qualitativeCheckMember">
										<label class="control-label signature-tool" for="constructionQc">质量检查员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="constructionQc" name="constructionQc" value="">
											<input type="hidden"  id="constructionQc_post" name="constructionQc_post" value="${constructionQcPost }">
											<img class="constructionQc" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectorSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText qualitativeCheckMember" value="" id="inspectorSignDate" name="inspectorSignDate"  />
										</div>
									</div>
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
			                     <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole hidden  auditAddBtn2 ">新增</a> 
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole auditEditBtn auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole auditEditBtn deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 完成标记 -->
									<input type="hidden" id="flag1" class="addClear">
		                   		<form class="form-horizontal" id="auditForm" data-parsley-validate="true" action="">
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
		                   			<input type="hidden" id="version" name="version" class="addClear"/>
		                   			<input type="hidden" id="vtId" name="vtId" class="addClear">
		                   			<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="valveName">名称</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="valveName"   name="valveName"  data-parsley-required="true" data-parsley-maxlength="50"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="typeSpecification">型号规格</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="typeSpecification"   name="typeSpecification"  data-parsley-required="true" data-parsley-maxlength="50"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="filler">填料</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="filler"   name="filler"  data-parsley-required="true" data-parsley-maxlength="30"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="number">数量</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="number"   name="number"  data-parsley-required="true" data-parsley-maxlength="20"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="nominalPressure">公称压力(MPa)</label>
										<div>
											<input type="text" class="form-control field-editable input-sm addClear" id="nominalPressure" name="nominalPressure" value="" data-parsley-maxlength="20" />
			   							</div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="strengthMedium">强度试验介质</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="strengthMedium"   name="strengthMedium"  data-parsley-maxlength="30"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="strengthPressure">强度试验压力(MPa)</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="strengthPressure"   name="strengthPressure" data-parsley-maxlength="20"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="strengthTime">强度试验时间(分钟)</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="strengthTime"   name="strengthTime" data-parsley-maxlength="20"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="tightnessMedium">严密性试验介质</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="tightnessMedium"   name="tightnessMedium" data-parsley-maxlength="30"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="tightnessPressure">严密性试验压力(MPa)</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="tightnessPressure"   name="tightnessPressure" data-parsley-maxlength="20"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="tightnessTime">严密性试验时间(分钟)</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="tightnessTime"   name="tightnessTime"  data-parsley-maxlength="20"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="visualInspcet">试验外观检查</label>
										<div>
							                <input type="text" class="form-control field-editable input-sm addClear" id="visualInspcet"   name="visualInspcet"  data-parsley-maxlength="20"/>
							            </div>
									</div>
			                       	<table id="recordListTable" class="table table-striped table-bordered nowrap hotMeltTable" width="100%">
			                            <thead >
			                                <tr>
			                                	<th>操作</th>
			                                    <th>名称</th>
			                                    <th>型号规格</th>
			                                    <th>填料</th>
			                                    <th>数量</th>
			                                    <th>公称压力(MPa)</th>
			                                    <th>强度试验介质</th>
			                                    <th>强度试验压力(MPa)</th>
			                                    <th>强度试验时间(分钟)</th>
			                                    <th>严密性试验介质</th>
			                                    <th>严密性试验压力(MPa)</th>
			                                    <th>严密性试验时间(分钟)</th>
			                                    <th>试验情况外观检查</th>
			                                    
			                                </tr>
			                            </thead>
			                            
			                       	</table>
		                       	</form>
		                       		
		                       	<!-- 照片 -->
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										<input type="hidden" id="vtId1" />
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
			    <div class="panel-body" id="valveTest_panel_box">
	                 <div class="clearboth form-box">
	                 	<div class="iframe-report-box">
	                  		<iframe id="mainFrm" class="iframe-report" style="width: 1123px; height: 794px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	                </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('阀门试验- 工程项目管理系统 ');
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();
   
    $('.update-show').addClass('hidden');
    $('.auditEditBtn').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				   //
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	
    }
    
  	//签字加载方式
  	 $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	$("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature(); 
  	 });
    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
   
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $.getScript('projectjs/inspection/inspection-common.js?v=1011').done(function() {
    	$.getScript('projectjs/inspection/valve-test.js?v=1011').done(function () {
    		valveTest.init();
		});
	});
   
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区和签字区的cpt文件
    var showReport = function(){
        var json=trSData.projectCheckListTable.json;
        var  pcId = $("#pcIdNew").val();
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projName=json.projName;
       	    suName =json.suName;
            constructionUnit =json.constructionUnit;
            inspectionDate=json.inspectionDate;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
            constructionUnit = $("#constructionUnit").val();
            inspectionDate=$("#inspectionDate").val(); 
        }
        if(!pcId){
        	pcId='-1';
        }
    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/valveTest.cpt&projName="+projName+"&pcId="+pcId+"&inspectionDate="+inspectionDate+"&imgUrl="+$(".imgUrl").val();
        
    	$("#mainFrm").attr("src",src); 
    }; 
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
		var projId = $("#projId").val(),projNo=$('#vtId1').val(),
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
		     if (!projNo && $("#vtId1").length && !$("#vtId1").val()) {
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