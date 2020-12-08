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
                   <h4 class="panel-title">计划</h4>
                </div>
                <div class="panel-body" >
                    <div class="clearboth form-box ">
                    	<form class="form-horizontal m-t-40" id="planEstablishDetailForm" action="">
							<div class="form-group  col-md-6 ">
						        <label class="control-label" for="">工程编号</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" />
						        </div>
						    </div>
						    
						    <div class="form-group col-md-12 ">
						        <label class="control-label" for="">工程名称</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" />
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="">工程地点</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />
						        </div>
						    </div>
						     <div class="form-group col-md-6 ">
						        <label class="control-label" for="projectTypeDes">工程类型</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="contributionModeDes">出资方式</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 ">
						        <label class="control-label" for="deptName">业务部门</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
						        <label class="control-label" for="">工程规模</label>
						        <div>
						            <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200"></textarea>
						        </div>
						    </div>
						    <div class="form-group col-md-6 clearboth noUser">
						        <label class="control-label" for="custContact">用户联系人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact"/>
						        </div>
						    </div>
						   	<div class="form-group col-md-6 noUser">
						        <label class="control-label" for="custPhone">联系电话</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 clearboth">
						        <label class="control-label" for="duName">设计单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="duName" name="duName"/>
						        </div>
						    </div>
						   	<div class="form-group col-md-6">
						        <label class="control-label" for="duDesigner">设计员</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="duDesigner" name="duDesigner"/>
						        </div>
						    </div>
				<!-- 		    <div class="form-group col-md-6 "> -->
				<!-- 		        <label class="control-label" for="drawingType">图纸类型</label> -->
				<!-- 		        <div> -->
				<!-- 		            <input type="text" class="form-control input-sm field-editable"  id="drawingType" name="drawingType"  value="庭院"/> -->
				<!-- 		        </div> -->
				<!-- 		    </div> -->
						   <!--  <div class="form-group col-md-6">
						        <label class="control-label" for="">开工日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-editable datepicker-default timestamp" id="plannedStartDate"  name="plannedStartDate" >
						        </div>
						    </div> -->
						     <div class="form-group col-md-6">
						        <label class="control-label" for="projTimeLimit">工期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable " id="projTimeLimit"  name="projTimeLimit">
						           <a href="javascript:;" class="btn btn-sm btn-default">天</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 clearboth noUser">
						        <label class="control-label" for="">合同金额</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="contractAmount" name="contractAmount"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 noUser">
						        <label class="control-label" for="firstPayment">应收首付款</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="firstPayment" name="firstPayment"/>
						        </div>
						    </div>
						   	<div class="form-group col-md-6 noUser">
						        <label class="control-label" for="">实收首付款</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="downPayment" name="downPayment"/>
						        </div>
						    </div>
						   <div class="form-group col-md-6 clearboth">
						        <label class="control-label" for="">现场代表</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable" id="builder" name="builder" data-parsley-required="true">
						        </div>
						    </div>
						    
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="">联系电话</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="bulTel" name="bulTel" />
						        </div>
						    </div>
						    <div class="form-group col-md-12 ">
						        <label class="control-label" for="">监理单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName" data-parsley-maxlength="100" data-parsley-required="true"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="">监理负责人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="suDirector" name="suDirector"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="">负责人电话</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="suPhone" name="suPhone" />
						        </div>
						    </div>
						    <div class="form-group col-md-12 col-sm-12 clearboth">
						        <label class="control-label" for="">施工单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="100" data-parsley-required="true"/>
						        </div>
						   </div>
						   <div class="form-group col-md-6">
						        <label class="control-label" for="">负责人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuLegalRepresent" name="cuLegalRepresent"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						        <label class="control-label" for="">负责人电话</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="cuPhone" name="cuPhone" />
						        </div>
						    </div>
						    <div class="form-group col-md-12 ">
						        <label class="control-label" for="remark">备注</label>
						        <div>
						            <textarea rows="4" class="form-control input-sm field-editable"  id="remark" name="remark">甲供材料、对外报竣工资料等特殊要求在备注里标注</textarea>
						        </div>
						    </div>
						</form>
                   </div>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" id="connect_gas_audit_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="planAuditRightForm" action="projectPlanAudit/auditSave">
			    			<input type="hidden" id="projId" name = "projId" value = "${constructionPlan.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${constructionPlan.projNo}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
                    		<input type="hidden" id="businessOrderId" name="businessOrderId" value = "${constructionPlan.cpId}"/>
                    		<input type="hidden" name="mrDeptId" value = "${loginInfo.deptId}"/>
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
						           <%-- <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}"> --%>
						        	<input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">审批日期</label>
						        <div>
 									<input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" >			
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
    App.setPageTitle('计划审核 - 工程管理系统');
    var obj=${constructionPlan};
    if(obj){
    	$("#planEstablishDetailForm").deserialize(obj);
    	
    	if($("#contractAmount").val()==""){
    		$(".noUser").addClass("hidden");
    	}else{
    		$(".noUser").removeClass("hidden");
    	}
    	
    }
    
    var date1='${constructionPlan.plannedStartDate}';
        date1=date1.substring(0,10);
        $("#plannedStartDate").val(date1);
        $("#planEstablishDetailForm").styleFit();
        $("#planAuditRightForm").styleFit();
    
    //表单不可编辑
    $("#planEstablishDetailForm,#planAuditRightForm").toggleEditState(false);
    $("#planAuditRightForm").toggleEditState(true);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    $("#mrTime").change();
    
    $.getScript('projectjs/plan/project-plan-audit_page.js').done(function () {
        gasAuditHistory.init();
	});
    
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("projectPlanAudit/main");
	});
    
    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
    	var val=$('#planAuditRightForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		alertInfo("请选择审批结果！");
    	}else{
    		//$("#planAuditRightForm").cformSave("auditHistoryTable",auditSaveCallBack);
    		if($("#planAuditRightForm").parsley().isValid()){
    			$("#connect_gas_audit_panel_box").loadMask("正在保存...", 1, 0.5);
            	var data=$("#planAuditRightForm").serializeJson();
            	data.menuId="110502";
            	$.ajax({
                    type: 'POST',
                    url: 'projectPlanAudit/auditSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    data: JSON.stringify(data),
                    beforeSend: function () {
        	              // 禁用按钮防止重复提交
                         $(".auditSaveBtn").attr({ disabled: "disabled" });
        	        },
                    complete: function () {
                    	//去掉禁用
                    	$(".auditSaveBtn").removeAttr("disabled");
                    },
                    success: function (data) {
                    	$("#connect_gas_audit_panel_box").hideMask();
                    	var content = "数据保存成功！";
                    	if(data.ret_type === "-1"){
                    		content = "数据保存失败！";
                    		alertInfo(content);
                    	}else if(data.ret_message === "true"){
                    		$(".auditSaveBtn").addClass("hidden");
                    		$(".auditCancelBtn").text("返回");
                    		$("#planAuditRightForm").toggleEditState(false);
                    		$(".auditFormDiv").addClass("hidden");
                    		$("#auditHistoryTable").cgetData(false);  //列表重新加载
                    		alertInfo(content);
                    	}else{//接口失败
                    		alertInfo(data.ret_message);
                    	}
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("计划审核保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#planAuditRightForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#planAuditRightForm").parsley().validate();
            }
    	}
    	
    });
    
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".auditSaveBtn").addClass("hidden");
    		$(".auditCancelBtn").text("返回");
    		$("#planAuditRightForm").toggleEditState(false);
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
    $("#planAuditRightForm input[name='mrResult']").on("change",function(){
        if($('#planAuditRightForm input:radio[name="mrResult"]:checked').val() == "1"){
            $("#mrAopinion").val("同意");
        }else{
            $("#mrAopinion").val("不同意");
        }
    })

</script>
<!-- ================== END PAGE LEVEL JS ================== -->