<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- altimetricSurvey.jsp -->
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
                    </div>
                    <input type="hidden" id="actionName" value="${actionName }"/>
                    <input type="hidden" id="loginPost" value="${loginPost }"/>
					<%-- <input type="hidden" id="builderPost" value="${builderPost }"/>
					<input type="hidden" id="sujgjPost" value="${sujgjPost }"/> --%>
					<input type="hidden" id=acceptancePost value="${acceptancePost }"/>
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
               			    <!--<input type="hidden" id="addShow"> -->
	                		<table data-attach-table="all" class="table table-hover table-striped table-bordered nowrap" id="projectCheckListTable" width="100%">
	                			<thead>
		                			<tr>
		                				<th></th>
			                			<th>报验日期</th>
			                			<th>施工工序</th>
			                			<th>查验结果</th>
			                			<th></th>
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
						    	<%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
								<input type="hidden" id="changeType" value="2"/>
								<form class="form-horizontal" id="signForm"  enctype="multipart/form-data"  data-parsley-validate="true" action="${actionName }/saveSignFile">							
								    <input type="hidden" id="pcDesId" name="pcDesId" value="${checkType}"/> 
								    <!-- 缩略图上传路径 -->
								    <input type="hidden" id="drawUrl1" name="drawUrl1" value="${drawUrl}"/> 
								    <input type="hidden" id="version" name="version" class="addClear"/>
									<input type="hidden" id="pcId" name="pcId" class="addClear"/>
									<input type="hidden" id="projId" name="projId" >
									<input type="hidden" id="process" name="process" value="${process }">
								 	<input type="hidden" id="recordsId" name="recordsId">
								 	<input type="hidden" id="finishedDate" name="finishedDate">
									<input type="hidden" id="finishedOpr" name="finishedOpr">
									<input type="hidden" id="resetDate" name="resetDate">
									<input type="hidden" id="resetReason" name="resetReason">
									<input type="hidden" id="isFinishSign" name="isFinishSign">
								 	<!-- 完成标记 -->
									<input type="hidden" id="flag" name="flag">
								 	<!-- 附件路径 -->
									<input type="hidden" id="alPath" name="alPath">
								 	<input type="hidden" id="result" name="result">
									<input type="hidden" id="menuDes" name="menuDes">
									<input type="hidden" id="menuId" name="menuId">
									 <!-- 缩略图路径 -->
									<input type="hidden" id="drawUrl" name="drawUrl" value="${drawUrl }">
									<input type="hidden" id="width" name="width"/>
									<input type="hidden" id="height" name="height"/>
									<input type="hidden" id="stepId" name="stepId"/>
									<input type="hidden" id="step" name="step"/>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projNoe">工程编号</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projNo" name="projNo"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projName">工程名称</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projName" name="projName"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="projAddr">工程地点</label>
										<div>
											<input type="text" class="form-control field-not-editable input-sm"  id="projAddr" name="projAddr"  />
										</div>
									</div>
									
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="inspectionDate">报验日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear "  id="inspectionDate" name="inspectionDate" data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-md-12 col-sm-12">
										<label class="control-label" for="stMedium">用气性质</label>
										<div>
											<input type="text" class="form-control input-sm  field-editable addClear "  id="stMedium" name="stMedium"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="stRange">调压器</label>
										<div>
											<input type="text" class="form-control input-sm  field-editable addClear "  id="stRange" name="stRange"  />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="stInstruction">报警器</label>
										<div>
											<input type="text" class="form-control input-sm  field-editable addClear "  id="stInstruction" name="stInstruction"  />
										</div>
									</div>
									
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="pipeManufactor">备注</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear " name="pipeManufactor" id="pipeManufactor" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
									
									<div class="form-group  col-sm-12">
				                    	<label class="control-label" for="inspectionResult">查验结果</label>
					                    <div> 
				                        	<textarea class="form-control field-editable addClear " name="inspectionResult" id="inspectionResult" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
					                    </div>
					  			    </div>
									<div class="form-group col-md-6 col-sm-12  ">
										<label class="control-label signature-tool sign-all" for="builder">验收人员</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="builder" name="builder" value="">
											<input type="hidden" class="signPost"  id="builder_postType" name="builder_postType" value="${construction }">
											<img class="builder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
								    <div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label" for="builderSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText construction" value="" id="builderSignDate" name="builderSignDate"  />
										</div>
									</div>

									<div class="form-group col-md-6 col-sm-12  ">
										<label class="control-label signature-tool  sign-all" for="welder">客户签字</label>
										<div>
											<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
											<input type="hidden" class="sign-data-input" id="welder" name="welder" value="">
											<input type="hidden" class="signPost"  id="welder_postType" name="welder_postType" value="${welderPost }">
											<img class="welder" alt="" src="" style="height: 30px">
											<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
										</div>
						    		</div>
									<div class="form-group col-md-6 col-sm-12 hidden selectSignDate">
										<label class="control-label" for="welderSignDate">签字日期</label>
										<div>
											<input type="text" class="form-control input-sm datepicker-default field-editable addClear allText welder"  id="welderSignDate" name="welderSignDate"  />
										</div>
									</div>
								</form>
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
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole   auditInpectBtn">报验</a>
			                    <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole hidden  auditAddBtn2 ">新增</a> 
			                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole  auditSavebtn2">保存</a>
						   		 <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole  deleteBtn2">删除</a>
						    </div>
						    <div class="clearboth form-box">
						    	<!-- 完成标记 -->
									<input type="hidden" id="flag1" class="addClear">
								 	
		                   		<form class="form-horizontal" id="auditForm" action="">
		                   			<input type="hidden" name="arId" id="arId" class="addClear"/>
		                   			<input type="hidden" id="recordPcId" name="pcId" class="addClear"/>
		                   			<input type="hidden" id="isFinishSign" name="isFinishSign" class="addClear">
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="meterNo">表编号</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear" id="meterNo"   name="meterNo"   data-parsley-maxlength="100"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="meterModel">表型号</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear" id="meterModel"   name="meterModel" data-parsley-maxlength="200"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="manufactor">厂家</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear " id="manufactor"   name="manufactor"   data-parsley-maxlength="200"/>
							            </div>
									</div>
		                   			<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="baseNumber">表底数</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear " id="baseNumber"   name="baseNumber"   data-parsley-maxlength="200"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="sealNo">铅封号</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear " id="sealNo"   name="sealNo"   data-parsley-maxlength="200"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="equipment">设备</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear " id="equipment"   name="equipment"   data-parsley-maxlength="200"/>
							            </div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for="validityTerm">有效期</label>
										<div>
							                <input type="text" class="form-control  input-sm field-editable addClear " id="validityTerm"   name="validityTerm"   data-parsley-maxlength="200"/>
							            </div>
									</div>
		                   			
		                   			<!-- <div class="form-group col-xs-12 "><i class="fa fa-angle-double-right"></i>强度试验 </div> -->
			                       	<table id="recordListTable" class="table table-striped table-bordered nowrap " width="100%">
			                            <thead >
			                                <tr>
			                                	<th></th>
			                                	<th>表编号</th>
			                                    <th>表型号</th>
			                                    <th>厂家</th>
			                                    <th>表底数</th>
			                                    <th>铅封号</th>
			                                    <th>设备</th>
			                                    <th>有效期</th>
			                                </tr>
			                            </thead>
			                            
			                       	</table>
			                       
		                       	</form>
		                       	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list hidden" id="recordImagesList">
										 <input type="hidden" id="arId1" />
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
			    <div class="panel-body " id="purge_panel_box">
	                 <div class="clearboth form-box " >
	                 	<!-- <div class="iframe-report-box hidden" id="RecordReportBox">
	                  		<iframe id="mainFrmRecord" class="iframe-report " style="width:1100px; height:795px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div> -->
	                 	<div class="iframe-report-box " id="">
	                  		<iframe id="mainFrm" class="iframe-report " style="width:794px;height:1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	               </div>
			    </div>
			    
			   <!--  <div class="panel-body " id="ReportBox">
	                 <div class="iframe-report-box " >
	                 	<div class="iframe-report-box " id="">
	                  		<iframe id="mainFrm" class="iframe-report " style="width:795px;height:1123px;border:1; overflow-y:auto;" scrolling="no"></iframe>
	                  	</div>
	                </div>
	              
			    </div> -->
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('验收登记- 工程项目管理系统 ');
    $("#signForm").toggleEditState().styleFit();
    $("#auditForm").toggleEditState().styleFit();
   
    $('.update-show').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projAddr').val(projJson.projAddr);				  
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    	$('#constructionUnit').val(projJson.cuName); //施工单位
    	$('#suName').val(projJson.suName);					   //监理公司
    	$("#custName").val(projJson.corpName);
    	/* 
    	$("#designUnit").val(projJson.duName);
    	$("#projAddr").val(projJson.projAddr);	 */	
    	//附件路径
	   	 $("#alPath").val(getProjectInfo().projNo+"/"+getStepId());
	   	 $("#stepId").val($('[data-mid="' + getStepId() + '"]').text());
	   	 $("#step").val(getStepId());
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
    $.getScript('projectjs/inspection/inspection-common.js?'+Math.random()).done(function() {
    	$.getScript('projectjs/inspection/acceptance-register-record.js?'+Math.random()).done(function () {
    		pipeWeldRecord.init();
 	   });
    });
 
	
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
  	//加载列表区记录报表
    var showReport = function(){
  		var menuDesc = $("#menuDes").val();
        var json=trSData.projectCheckListTable.json;
        //console.log(json);
        var pcId = $("#pcIdNew").val();
        //列表区的cpt文件数据从trSData中获取
        if(json){
        	projNo = json.projNo;
        	projId = json.projId;
        	projName=json.projName;
       	    suName =json.suName;
       	    inspectionDate = json.inspectionDate;
            constructionUnit =json.constructionUnit;
            custName = json.custName;
        }else{
        	//签字区保存后不刷新页面,cpt文件中数据从签字区获取
        	projNo = $('#projNo').val();
        	projId = $('#projId').val();
        	projName=$('#projName').val();
    	    suName = $("#suName").val();
            constructionUnit = $("#constructionUnit").val();
            custName = $("#custName").val();
            inspectionDate = $('#inspectionDate').val();
        }
		if(!pcId){
			pcId='-1';
		}
    	    //以下处理为解决中文乱码
    	    projName=encodeURIComponent(cjkEncode(projName));
        	suName = encodeURIComponent(cjkEncode(suName));
        	constructionUnit = encodeURIComponent(cjkEncode(constructionUnit));
        	custName = encodeURIComponent(cjkEncode(custName));
        	menuDes = encodeURIComponent(cjkEncode(menuDesc));
       
        //cpt路径及参数
        var src=cptPath+"/ReportServer?reportlet=inspection/acceptanceRegister.cpt&projName="+projName+"&inspectionDate="+inspectionDate;
    	src = src + "&pcId="+pcId+"&imgUrl="+$('.imgUrl').val();
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
    	var recordJson = trSData.recordListTable.json;
    	var pcId,projNo;
    	if(recordJson.pcId){
    		pcId = recordJson.pcId;
    	}
	    if(recordJson.arId){
	    	//传递记录ID,用工程编号存储
	    	projNo = recordJson.arId;
	    }
		projId = $("#projId").val(),
		stepId=getStepId();
	    busRecordId=pcId;
	    console.info(projNo);
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), pcId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		     if (!projNo && $("#arId1").length && !$("#arId1").val()) {
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
	
</script>

<!-- ================== END PAGE LEVEL JS ================== -->