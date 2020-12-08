<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
    	<!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12" >
			<div class="panel panel-inverse" >
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">预览区</h4>
				</div>
				<div class="panel-body" id="">
					<input type="hidden" id="evId" value = "${evId}">
					<input type="hidden" class="imgUrl" value="${imgUrl}"/>
					<input type="hidden" id="drawUrl1" value="${drawUrl1}"/>
					<input type="hidden" id="projId" value="${engineeringVisa.projId}"/>
					<input type="hidden" id="projNo" value="${engineeringVisa.projNo}"/>
					<div class="clearboth form-box">
						<div class="iframe-report-box">
							<iframe id="mainFrm" class="iframe-report" style="width: 1180px; height: 850px; border:1;overflow-y:auto;" scrolling="no"></iframe>
						</div>
					</div>
					<div class="clearboth form-box">
								<div class="form-group width-full attach-images-list" id="projectImagesList">
								     <h4><i class="fa fa-file-photo-o"></i> 照片列表
										 <!-- <a href="javascript:;" class="btn btn-primary btn-sm upload-images-btn pull-right"><i class="fa fa-upload"></i> 上传</a>
										 <a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a> -->
										 <!-- <a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a> --></h4>
								     <ul class="row"></ul>
								</div>
						 	</div>
				</div>
			</div>
		</div>
        <!-- 加载左屏签证信息 -->
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" id="stageProject_audit_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<form class="form-horizontal" id="stageProjectAuditRightForm" action="stageProjectAudit/auditSave">
			    			<input type="hidden"  class="projId" name = "projId" value = "${engineeringVisa.projId }"/>
			    			<input type="hidden"  class="projNo" name = "projNo" value = "${engineeringVisa.projNo }"/>
			    			<input type="hidden" class="isAudit" id="isAudit" name = "isAudit" value = "${ isAudit}"/>
                    		<input type="hidden"  class="evId" name = "evId" value = "${ evId}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${ evId}"/>
                    		<input type="hidden" class="currentLevel" name = "mrAuditLevel" value = "${ currentLevel}"/>
			    			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
			        			<label class="control-label" for="">审批结果</label>
				            	<div>
						             <c:forEach var="enums" items="${mrResult}">
					             		 <label>
							            	<input type="radio" name="mrResult" value="${enums.value}"/>${enums.message}
							            </label>
						             </c:forEach>
						        </div>
		    				</div>
		    				<div class="form-group col-lg-12 col-md-12 col-sm-12">
						     	<label class="control-label" for="">审批意见</label>
						     	<div> 
		        					<textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-lg-6 col-md-12 col-sm-6">
						        <label class="control-label" for="">审批人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">审批日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>审批日期</th>
			                <th>审批结果</th>
			                <th>审批意见</th>
			                <th>审批人</th>
	            			</tr>
          				</thead>
					</table>
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
    App.setPageTitle('签证审核 - 工程管理系统');
    $("#stageProjectAuditRightForm").styleFit();
    $("#stageProjectAuditRightForm").toggleEditState(true);
    $("#engineeringForm").styleFit();
    $("#engineeringForm").toggleEditState(false);
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $("#stepId").val(getStepId());
    $("#step").val(getStepId());
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
     //加载打印预览
     function cjkEncode(D){if(typeof D!=="string"){return D}var C="";for(var A=0;A<D.length;A++){var B=D.charCodeAt(A);if(B>=128||B==91||B==93){C+="["+B.toString(16)+"]"}else{C+=D.charAt(A)}}return C}

     //加载打印预览
     var showReport = function(){
     	var projId= $("#projId").val(),
     		imgUrl= $(".imgUrl").val(),
     		evId= $("#evId").val(),
     		drawUrl1= $("#drawUrl1").val();
     		
     	var src="<%=basePath%>/ReportServer?reportlet=constructmanage/engineering.cpt&projId="+projId+"&evId="+evId+"&imgUrl="+imgUrl+"&drawUrl1="+drawUrl1;
     	$("#mainFrm").attr("src",src); 
     };
     //加载报表
     showReport();
 
    //初始化上传文件控件
    $.getScript('projectjs/data/form-multiple-upload.demo.js?v='+Math.random()).done(function() {
    	FormMultipleUpload.init();
    });
    //加载现场照片
    $("#projectImagesList").getImagesList({
        "projId": $("#projId").val(),
        "stepId": '120206',//签证记录
        "projNo": $("#projNo").val(),
        "busRecordId": $("#evId").val() || '-1'
    });
    //加载js
    $.getScript('projectjs/constructmanage/engineering-visa-audit-page.js?v='+Math.random()).done(function () {
        auditHistory.init();
	});
   
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("engineeringQuantyAudit/main");
	});
    
    $("#mrTime").change();
    
    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
    	var val=$('#stageProjectAuditRightForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		alertInfo("请选择审批结果！");
    	}else{
    		/* $(".projId").val($("#projId").val());
        	$(".projNo").val($("#projNo").val()); */
        	if($("#stageProjectAuditRightForm").parsley().isValid()){
        		$("#stageProject_audit_panel_box").loadMask("正在保存...", 1, 0.5);
            	var data=$("#stageProjectAuditRightForm").serializeJson();
            	$.ajax({
                    type: 'POST',
                    url: 'engineeringQuantyAudit/auditSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    data: JSON.stringify(data),
                    success: function (data) {
                    	$("#stageProject_audit_panel_box").hideMask();
                    	var content = "数据保存成功！";
                    	if(data.ret_message === "false"){
                    		content = "数据保存失败！";
                    		alertInfo(content);
                    	}else if(data.ret_message === "true"){
                    		$(".auditSaveBtn").addClass("hidden");
                    		$(".auditCancelBtn").text("返回");
                    		$("#stageProjectAuditRightForm").toggleEditState(false);
                    		$(".auditFormDiv").addClass("hidden");
                    		$("#auditHistoryTable").cgetData(false);  //列表重新加载
                    		alertInfo(content);
                    	}else{//接口失败
                    		alertInfo(data.ret_message);
                    	}
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("审核记录保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#stageProjectAuditRightForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#stageProjectAuditRightForm").parsley().validate();
            }
    	}
    });
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".auditSaveBtn").addClass("hidden");
    		$(".auditCancelBtn").text("返回");
    		$("#stageProjectAuditRightForm").toggleEditState(false);
    		$(".auditFormDiv").addClass("hidden");
    	}
    } 
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    	}
    }();
    
   /*  var handleVisaInfoPage  = function (){
    	//加载左侧页面
    	$('#engineeringInfo').cgetContent('engineeringQuantyAudit/viewPage',null,touchPlanDetail);
    		
    }
	handleVisaInfoPage() ; */
</script>
<!-- ================== END PAGE LEVEL JS ================== -->