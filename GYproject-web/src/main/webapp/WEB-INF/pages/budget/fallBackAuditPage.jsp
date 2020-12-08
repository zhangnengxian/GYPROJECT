<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
       <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">回退申请信息</h4>
			</div>
            <div class="panel-body">
	        	 <div class="toolBtn f-r p-b-10  hidden">
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewUpdataBtn" >修改</a>
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewSaveBtn" >保存</a>
				</div> 
	            <!-- 预算详述 -->
				<div class="clearboth form-box">
					<form class="form-horizontal" id="budgetSumForm" action=""  enctype="multipart/form-data" >
						<input type="hidden" name="projId" id="projId" />
						<div class="form-group col-md-6">
					   	<label class="control-label" for="projNo">工程编号</label>
					       <div>
					       	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12  ">
					       <label class="control-label" for="projName">工程名称</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12">
					          <label class="control-label" for="projScaleDes">工程规模</label>
					          <div>
					              <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
					          </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">工程类型</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="projectType" name="projectType"  data-parsley-maxlength="100"/>
					    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
									<option value="1" >居民户工程</option>
					             </select> -->
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">出资方式</label>
					    	<div>
					    	<input type="text" class="form-control input-sm field-not-editable"  id="constructionMode" name="constructionMode"  data-parsley-maxlength="100"/>
					    		<!-- 
					    		<select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
									<option value="1" >用户出资（自有资金）</option>
					             </select> -->
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="originalStepId">原始步骤</label>
					       <div>
					       		<input type="text" class="form-control input-sm field-not-editable"  id="originalStepId" name="originalStepId"  data-parsley-maxlength="100"/>
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="fallbackStepId">回退步骤</label>
					       <div>
					       		<input type="text" class="form-control input-sm field-not-editable"  id="fallbackStepId" name="fallbackStepId"  data-parsley-maxlength="100"/>
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12 clearboth">
					       <label class="control-label" for="applyOperator">申请人</label>
					       <div>
					          <input type="text" class=" form-control input-sm field-not-editable" id="applyOperator"  name="applyOperator" value="">
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="applyTime">申请时间</label>
					       <div>
					          <input type="text" class=" form-control input-sm field-not-editable" id="applyTime"  name="applyTime" value="">
					       </div>
					   </div>
					   <div class="form-group col-md-12 col-sm-12">
					       <label class="control-label" for="fallbackReason">回退原因</label>
					       <div>
					          <textarea type="text" class=" form-control input-sm field-not-editable" id="fallbackReason"  name="fallbackReason"  rows="3"></textarea>
					       </div>
					   </div>
					   
						</form>
					</div>
	            </div>
	        </div>
		</div>
		<!-- col-sm-6 end  -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
			<div class="panel-heading">
				<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
					<div id="budgetAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box budgetAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="budgetAuditForm" action="fallBackAudit/auditSave">
			    			<input type="hidden"  id="projId1" name = "projId" value = "${fa.projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo" />
                    		<input type="hidden"  id="businessOrderId1" name = "businessOrderId" value="${fa.faId}"/>
                    		<input type="hidden"  id="backStepId"  value="${fa.fallbackStepId}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0" />不通过
				            	</label>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">确认意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-md-6">
						        <label class="control-label" for="">确认人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">确认日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
		    		</div>
			    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
	       					<thead>
				            	<tr>
				                <th>确认日期</th>
				                <th>确认结果</th>
				                <th>确认意见</th>
				                <th>确认人</th>
		            			</tr>
	          				</thead>
						</table>
					</div>
			    </div>
	     	</div>
		</div>	    	
	</div>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('回退审核 - 工程管理系统');
    
    $("#budgetSumForm").toggleEditState();
    $("#budgetSumForm").styleFit();
    
    $("#budgetAuditForm").toggleEditState();
    $("#budgetAuditForm").styleFit();
   
    $("#budgetDispatchForm").toggleEditState();
    $("#budgetDispatchForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/budget/fallback-audit-page.js?v=1001').done(function () {
    	fallbackAudit.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("fallBackAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#budgetAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		//$("#budgetAuditForm").cformSave("auditHistoryTable",auditSaveCallBack);
    		
       		$(".projId").val($("#projId").val());
           	$(".projNo").val($("#projNo").val());
           	if($("#budgetAuditForm").parsley().isValid()){
           		$("#drawing_audit_panel_box").loadMask("正在保存...", 1, 0.5);
               	var data=$("#budgetAuditForm").serializeJson();
               	data.stepId=$("#backStepId").val();
               	$.ajax({
                       type: 'POST',
                       url: 'fallBackAudit/auditSave',
                       contentType: "application/json;charset=UTF-8",
                       dataType:"json",
                       data: JSON.stringify(data),
                       beforeSend: function () {
           	              // 禁用按钮防止重复提交
                          $(".saveBtn").attr({ disabled: "disabled" });
           	            },
                       complete: function () {
                       	//去掉禁用
                       		$(".saveBtn").removeAttr("disabled");
                       },
                       success: function (data) {
                    	   $("#drawing_audit_panel_box").hideMask();
                       	var content = "数据保存成功！";
                       	if(data.ret_message === "false"){
                       		content = "数据保存失败！";
                       		alertInfo(content);
                       	}else if(data.ret_message === "true"){
                       		$(".saveBtn").addClass("hidden");
                       		$(".cancelBtn").text("返回");
                       		$("#budgetAuditForm").toggleEditState(false);
                       		$(".budgetAuditFormDiv").addClass("hidden");
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
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.budgetAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#budgetAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#budgetAuditForm").toggleEditState(false); 
    	}
    	console.info("de..."+$("#budgeterId").val());
    	if($("#budgeterId").val()!=""){
    		//已派遣预算员
    		$(".dispatchBtnChange").addClass("hidden");
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//确认过
    		auditSaveCallBack("true");
    	}
    }();
    
    
        
</script>
<!-- ================== END PAGE LEVEL JS ================== -->