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
                   <h4 class="panel-title">补充协议</h4>
                </div>
                <div class="panel-body" id="contractAudit_panelBox">
                    <!-- 合同详述 -->
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
			    <div class="panel-body" id="contract_audit_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn66">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<form class="form-horizontal" id="contractAuditRightForm" action="">
			    			<input type="hidden" class="isAudit" id="isAudit" name = "isAudit" value = "${ isAudit}"/>
			    			<input type="hidden" class="projId" name = "projId" value = "${projId }"/>
			    			<input type="hidden" class="projNo" name = "projNo" value = "${projNo }"/>
                    		<input type="hidden" class="scId" name = "scId" value = "${ scId}"/>
							<input type="hidden" class="corpId" name = "corpId" value = "${ corpId}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${ scId}"/>
                    		<input type="hidden" class="mrcurrentLevel" name = "mrAuditLevel" value = "${ currentLevel}"/>
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
    App.setPageTitle('补充协议审核 - 工程管理系统');
    $("#contractAuditForm").styleFit();
    $("#contractAuditRightForm").styleFit();
    
    //表单不可编辑
    $("#contractAuditForm,#contractAuditRightForm").toggleEditState(false);
    $("#contractAuditRightForm").toggleEditState(true);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/contract/supplement-contract-modify-audit-page.js?v==7800').done(function () {
        auditHistory.init();
	});
     
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("supplementContractModifyAudit/main");
	});
    
    $("#mrTime").change();
    
    //保存
    $(".auditSaveBtn").off("click").on("click",function(){
    	var val=$('#contractAuditRightForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		alertInfo("请选择审批结果！");
    	}else{
        	if($("#contractAuditRightForm").parsley().isValid()){
        		$("#contract_audit_panel_box").loadMask("正在保存...", 1, 0.5);
            	var data=$("#contractAuditRightForm").serializeJson();
            	$.ajax({
                    type: 'POST',
                    url: 'supplementContractModifyAudit/auditSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    data: JSON.stringify(data),
                    success: function (data) {
                    	$("#contract_audit_panel_box").hideMask();
                    	//返回信息
             			console.info(data);
                    	var content = "数据保存成功！";
                    	if(data.ret_type == "-1"){
                    		content = "数据保存失败！";
                    		alertInfo(content);
                    	}else if(data.ret_message == "true"){
                    		$(".auditSaveBtn").addClass("hidden");
                    		$(".auditCancelBtn").text("返回");
                    		$("#contractAuditRightForm").toggleEditState(false);
                    		$(".auditFormDiv").addClass("hidden");
                    		$("#auditHistoryTable").cgetData(false);  //列表重新加载
                    		alertInfo(content);
                    	}else{//接口异常
                    		alertInfo(data.ret_message);
                    	}
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("协议签订区记录保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#contractAuditRightForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#contractAuditRightForm").parsley().validate();
            }
    	}
    });
    
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".auditSaveBtn").addClass("hidden");
    		$(".auditCancelBtn").text("返回");
    		$("#contractAuditRightForm").toggleEditState(false);
    		$(".auditFormDiv").addClass("hidden");
    	}
    } 
    
    var accrualsRecordDone = function(){
		$(".auditSaveBtn").addClass("hidden");
		$(".auditCancelBtn").text("返回");
		$("#contractAuditRightForm").toggleEditState(false);
		$(".auditFormDiv").addClass("hidden");
		$("#auditHistoryTable").cgetData(false);  //列表重新加载
		alertInfo('合同审核完成！');
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