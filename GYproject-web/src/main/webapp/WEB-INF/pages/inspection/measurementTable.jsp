<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<ul class="nav nav-tabs">
						<li class="active"><a href="#default-tab-1" id="listTab"
							data-toggle="tab">列表区</a></li>
						<li class="" id="addTab"><a href="#default-tab-2"
							id="signTab" data-toggle="tab">签字区</a></li>
						<li class="hidden"><a href="#default-tab-3" id="auditTab"
							data-toggle="tab">记录区</a></li>
					</ul>
				</div>
				<div class="panel-body" id="">
					<div class="tab-content">
						<div class="tab-pane fade active in btn-top" id="default-tab-1">
							<input type="hidden" id="post" name="post" value="${loginInfo.post}">
							<table data-attach-table="all"
								class="table table-hover table-striped table-bordered nowrap"
								id="measurementTable" width="100%">
								<thead>
									<tr>
										 <th>id</th>
										<th>计量表铭牌号</th>
										<th>计量表类型</th>
										<th>计量表类型编号</th>
										<th>制造商</th>
										<th>制造商编号</th>
										<th>首检日期</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="tab-pane fade btn-top" id="default-tab-2">
							<div class="toolBtn f-r p-b-10 update-show">
			                	
								<a href="javascript:;"
									class="btn btn-confirm btn-sm m-l-5 checkRole savebtn">保存</a>
								<a href="javascript:;"
									class="btn btn-warn btn-sm m-l-5 checkRole giveupbtn">放弃</a>
							</div>
							<div class="clearboth form-box">
								<%-- <input type="hidden" class="imgUrl" value="${imgUrl}" />
 --%>								<form  class="form-horizontal" id="measurementForm"
									data-parsley-validate="true"
									action="">
								<input type="hidden" id="projId" name= "projId"/>
								<input type="hidden" id="projNo" name= "projNo"/>
								<input type="hidden" id="msId" name= "msId" class="addClear"/>
								<input type="hidden" id="version" name= "version" class="addClear"/>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="tableNumber">计量表铭牌号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction" id="tableNumber"
												name="tableNumber" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="tableType">计量表类型</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="tableType" name="tableType" />
										</div>
									</div>
										<div class="form-group  col-sm-6">
										<label class="control-label" for="tableTypeNumber">计量表类型编号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="tableTypeNumber" name="tableTypeNumber" />
										</div>
									</div>
								
										<div class="form-group  col-sm-6">
										<label class="control-label" for="producers">制造商</label>
										<div>
											<input type="text"
												class="form-control field-editable addClear allText construction input-sm"
												id="producers" name="producers" />
										</div>
									</div>
									
									<div class="form-group  col-sm-6">
										<label class="control-label" for="model">型号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="model" name="model" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="producersNumber">制造商编号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="producersNumber" name="producersNumber" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="modelNumber">型号编号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="modelNumber" name="modelNumber" />
										</div>
									</div>
								
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for= "firstCheckDate">首检日期</label>
										<div>
											<input type="text"
												class="form-control input-sm datepicker-default field-editable addClear allText construction"
												value="" id="firstCheckDate" name="firstCheckDate"
												data-parsley-required="true" />
										</div>
									</div>
									<div class="form-group col-md-6 col-sm-12">
										<label class="control-label" for= "dateOfProduction">生产日期</label>
										<div>
											<input type="text"
												class="form-control input-sm datepicker-default field-editable addClear allText construction"
												value="" id="dateOfProduction" name="dateOfProduction"
												data-parsley-required="true" />
										</div>
									</div>
								  <div class="form-group  col-sm-6">
										<label class="control-label" for="leftDigits">左侧位数</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="leftDigits" name="leftDigits" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="rightDigits">右侧位数</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="rightDigits" name="rightDigits" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="readType">读出类型</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="readType" name="readType" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="readTypeNumber">读出类型编号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="readTypeNumber" name="readTypeNumber" />
										</div>
									</div>
								 	<div class="form-group  col-sm-6">
										<label class="control-label" for="airInletDirection">进气方向</label>
										 <div>
												<select class="form-control field-editable input-sm addClear allText construction" id="airInletDirection" name="airInletDirection">
											    <option value="" checked>==请选择进气方式==</option>
												 <option value="1">左进</option>
												 <option value="2">右进</option>
												</select>
										</div> 
									</div> 
									
										<div class="form-group  col-sm-6">
										<label class="control-label" for="paintNumber">油漆编号</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="paintNumber" name="paintNumber" />
										</div>
									</div>
									
									<div class="form-group col-sm-6">
										<label class="control-label">是否智能表</label>
										<div>
											<label> <input type="radio" class="field-editable addClear allText construction"
												name="intelligentTable" value="1" />是
											</label> <label> <input type="radio" class="field-editable addClear allText construction"
												name="intelligentTable" value="0" checked="checked" />否
											</label>
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="tableCode">表具传输式代码</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="tableCode" name="tableCode" />
										</div>
									</div>
									<div class="form-group  col-sm-6">
										<label class="control-label" for="other">其他</label>
										<div>
											<input type="text"
												class="form-control field-editable input-sm addClear allText construction"
												id="other" name="other" />
										</div>
									</div>
									</form>
								<!-- 照片 -->
							<!-- 	<div class="clearboth form-box  photoBox">
									<div class="form-group width-full attach-images-list " id="projectImagesList">
									     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a></h4>
									     <ul class="row">
									     </ul>
									</div>
								</div>  -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">预览区</h4>
				</div>
				<div class="panel-body" id="altimetric_survey_audit_panel_box">
					<div class="clearboth form-box">
						<div class="iframe-report-box">
							<iframe id="mainFrm" class="iframe-report"
								style="width: 850px; height: 1150px; border: 1; overflow-y: auto;"
								scrolling="no"></iframe>
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
    App.setPageTitle('防腐检查- 工程项目管理系统 ');
    $("#measurementForm").toggleEditState().styleFit();
    $('.update-show').addClass('hidden');
    $('.editBtn').addClass('hidden');
    
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	var projJson = getProjectInfo();
    	$('#projName').val(projJson.projName);				   //工程名称
    	$('#projId').val(projJson.projId);					   //工程ID
    	$('#projNo').val(projJson.projNo);     	 			   //工程编号
    }
    
  	//签字加载方式
   /*  $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5").handleSignature();   */
    
   //打印预览窗口缩放调整
    if($(".iframe-report").length > 0){
    	var fr = $(".iframe-report");
    	for(var i=0; i<fr.length; i++){
    		fr.eq(i).rescaleReportPanel();
    	}
    } 
    /**点击 保存 按钮时*/
    $(".savebtn").on("click",function(){
    	if($("#measurementForm").parsley().isValid()){
    		//加遮罩
    	   $(".savebtn").parent().parent().loadMask("正在保存...", 1, 0.5);
    		//表单序列化
        	var data=$("#measurementForm").serializeJson();
        	$.ajax({
                type: 'POST',
                url: 'measurementTable/savaMeasurement',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                  $(".savebtn").parent().parent().hideMask();
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data === "true"){
                		saveBack(data);
                	}else if(data === "already"){
                   		content = "此信息已被修改，请刷新页面！";
                   	}
                	var myoptions = {
                        	title: "提示信息",
                        	content: content,
                        	accept: sureClose,
                        	chide: true,
                        	icon: "fa-check-circle"
                    }
                    $("body").cgetPopup(myoptions);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	//取消遮罩
                	$(".savebtn").parent().parent().hideMask();	
                    console.warn("保存失败！");
                }
            });
    		//如果通过验证, 则移除验证UI
        	$("#measurementForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#measurementForm").parsley().validate();
        }
    });
    var sureClose=function(){
    	
    }
    function saveBack(data){
    	$('.update-show').addClass("hidden"); 
    	$('#measurementForm').toggleEditState(false);
    	deleteCallBack();  //保存成功之后加载列表和报表
    }
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
   
    $.getScript('projectjs/inspection/measurement-table.js?'+Math.random()).done(function () {
    	preservativeInpect.init();
	});
   
  	//加载打印预览
    function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}
    var cptPath = '<%=basePath%>';
	//加载列表区和签字区的cpt文件
	var showReport = function() {
		var json = trSData.measurementTable.json;
		//列表区的cpt文件数据从trSData中获取
		//以下处理为解决中文乱码
		var projId="";
	if(json.projId){
		 projId = encodeURIComponent(cjkEncode(json.projId));
	}else{
		 projId = $("#projId").val();
	}
		//cpt路径及参数
		var src = "";
		src = cptPath
		+ "/ReportServer?reportlet=inspection/measurementTable.cpt";
		src = src + "&projId=" + json.projId;
		$("#mainFrm").attr("src", src);
	};
	var showReportDefalat = function(){
		var src = "";
		src = cptPath
		+ "/ReportServer?reportlet=inspection/measurementTable.cpt";
		$("#mainFrm").attr("src", src);
	}
	showReportDefalat();
/* 	 //移动端点击上传
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
	}); */
 /*  //限制上传多个照片
	var surplusAccept=function(){
		$(".surplusDelBtn").removeClass("hidden");
	}
	
	//上传多个照片后删除
	$(".surplusDelBtn").off().on("click",function(){
		$("#filePreviews tbody").html("");
		$(".surplusDelBtn").addClass("hidden");
	}) */
</script>

<!-- ================== END PAGE LEVEL JS ================== -->