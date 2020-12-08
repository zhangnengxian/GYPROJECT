<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
    	<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                   <div class="panel-heading-btn">
                       <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                       <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                       <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                   </div>
                   <h4 class="panel-title">延期工程</h4>
                </div>
                <div class="panel-body" id="delayCheck_panelBox">
                    <form class="form-horizontal" id="applyDelayDetailForm"  data-parsley-validate="true" action="">
			       		<input type="hidden" name="projId" id="projId" value="${applyDelay.projId}"/>
			       		
					    <div class="form-group  col-md-6 ">
					        <label class="control-label" for="projNo">工程编号</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value="${applyDelay.projNo}"/>
					        </div>
					    </div>
					    <div class="form-group  col-md-6 ">
					        <label class="control-label" for="stepId">工程步骤</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="stepIdDes" name="stepIdDes" data-parsley-maxlength="100" value="${applyDelay.stepIdDes}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="projName">工程名称</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value="${applyDelay.projName}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="projAddr">工程地点</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value="${applyDelay.projAddr}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					        <label class="control-label" for="projScaleDes">工程规模</label>
					        <div>
					            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="100" rows="3">${applyDelay.projScaleDes}</textarea>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="projName">工程类型</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" data-parsley-maxlength="100" value="${applyDelay.projectTypeDes}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="projName">出资方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" data-parsley-maxlength="100" value="${applyDelay.contributionModeDes}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="originalPeriod">原时段</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="originalPeriod" name="originalPeriod" data-parsley-maxlength="100" value="${applyDelay.originalPeriod}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="delayPeriod">延期时段</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="delayPeriod" name="delayPeriod" data-parsley-maxlength="100" value="${applyDelay.delayPeriod}"/>
					        </div>
					    </div>
					     <div class="form-group col-md-12">
					        <label class="control-label" for="delayReason">延期原因</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="delayReasonDes" name="delayReasonDes" data-parsley-maxlength="100" value="${applyDelay.delayReasonDes}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-12">
					        <label class="control-label" for="delayRemark">备注</label>
					        <div>
					            <textarea type="text" class="form-control input-sm field-not-editable"  id="delayRemark" name="delayRemark" data-parsley-maxlength="100" rows="3">${applyDelay.delayRemark}</textarea>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="adOperator">操作人</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="adOperator" name="adOperator" data-parsley-maxlength="100" value="${applyDelay.adOperator}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="optTime">操作时间</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="optTime" name="optTime" data-parsley-maxlength="100" value="${applyDelay.optTime}"/>
					        </div>
					    </div>
					</form>
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
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" id="delay_check_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box checkFormDiv">
			    		<form class="form-horizontal" id="delayCheckRightForm" action="applyDelayAudit/auditSave">
			    			<input type="hidden" class="isAudit" id="isAudit" name = "isAudit" value = "${ isAudit}"/>
			    			<input type="hidden"  class="projId" name = "projId" value="${applyDelay.projId}"/>
			    			<input type="hidden"  class="projNo" name = "projNo" value="${applyDelay.projNo}"/>
                    		<input type="hidden"  class="businessOrderId" id="businessOrderId"name = "businessOrderId" value = "${applyDelay.adId}"/>
                    		<input type="hidden" class="currentLevel" name = "mrAuditLevel" value = "${currentLevel}"/> 
			    			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
			        			<label class="control-label" for="">审批结果</label>
				            	<div>
				            	 <label>
							            <input type="radio" name="mrResult" value="1" checked="checked" />通过
							            </label>
				            	 <label>
							            <input type="radio" name="mrResult" value="0" />未通过
							            </label>
						             
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
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="admin">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">审批日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm  field-not-editable timestamp all" id="mrTime" value="${auditTime}" name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
		    		</div>
		    		<table id="checkHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
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
    App.setPageTitle('延期工程审核 - 工程管理系统');
    $("#delayCheckRightForm").styleFit();
	$("#applyDelayDetailForm").styleFit();
    //表单不可编辑
    $("#applyDelayDetailForm").toggleEditState(false);
    $("#delayCheckRightForm").toggleEditState(true);
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/design/delay-check-page.js').done(function () {
        checkHistory.init();
	});
    
    //放弃
    $(".checkCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("applyDelayAudit/main");
	});
    
    $("#mrTime").change();
    
    //保存
    $(".checkSaveBtn").off("click").on("click",function(){
    	var val=$('#delayCheckRightForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		alertInfo("请选择审批结果！");
    	}else{
    		//$("#mrTime").val(timestamp($("#mrTime").val()));
	    	$("#delayCheckRightForm").cformSave("checkHistoryTable",auditSaveCallBack);
    	}
    });
    
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".checkSaveBtn").addClass("hidden");
    		$(".checkCancelBtn").text("返回");
    		$("#delayCheckRightForm").toggleEditState(false);
    		$(".checkFormDiv").addClass("hidden");
    		
    	}
    } 
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    	}
    }();

</script>
<!-- ================== END PAGE LEVEL JS ================== -->