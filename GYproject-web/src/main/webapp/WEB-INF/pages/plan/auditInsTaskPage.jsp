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
                   <h4 class="panel-title">安装任务</h4>
                </div>
                <div class="panel-body" >
                    <div class="clearboth form-box ">
                    	<form class="form-horizontal m-t-40" id="planEstablishDetailForm" action="">
							<div class="form-group  col-md-6 ">
						        <label class="control-label" for="">工程编号</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value = "${data.projNo }"/>
						        </div>
						    </div>
						    
						    <div class="form-group col-md-12 ">
						        <label class="control-label" for="">工程名称</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"  value ="${data.projName }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="">工程地点</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value="${data.projAddr }"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 ">
						        <label class="control-label" for="projectTypeDes">工程类型</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${data.projectTypeDes }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="contributionModeDes">出资方式</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${data.contributionModeDes }"/>
						        </div>
						    </div>
						     <div class="form-group col-md-6 ">
						        <label class="control-label" for="">联系人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable" value="${data.custContact }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
						        <label class="control-label" for="">申报单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable" value="${data.custName }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6  ">
						        <label class="control-label" for="">合同金额</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="contractAmount" name="contractAmount" value="${data.contractAmount }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="firstPayment">应收首付款</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="firstPayment" name="firstPayment" value="${data.budgetCost }"/>
						        </div>
						    </div>
						   	<div class="form-group col-md-6 ">
						        <label class="control-label" for="">实收首付款</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="downPayment" name="downPayment" value="${data.confirmTotalCost }"/>
						        </div>
						    </div>
							<div class="form-group col-md-12 ">
								<label class="control-label" for="meterType">表具类型</label>
								<div>
									<textarea rows="4" class="form-control input-sm field-editable"  id="meterType" name="meterType">${instTasks.meterType }</textarea>
								</div>
							</div>
						    <div class="form-group col-md-12 ">
						        <label class="control-label" for="remark">备注</label>
						        <div>
						            <textarea rows="4" class="form-control input-sm field-editable"  id="remark" name="remark">${instTasks.remark }</textarea>
						        </div>
						    </div>
						</form>
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
			         <h4 class="panel-title">审批区</h4>
			    </div>
			    <div class="panel-body" id="connect_gas_audit_panel_box">
			    	<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 auditCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="planAuditRightForm" action="auditInstTasks/auditSave">
			    			<input type="hidden" id="projId" name = "projId" value = "${data.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${data.projNo}"/>
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
                    		<input type="hidden" id="businessOrderId" name="businessOrderId" value = "${data.projId}"/>
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
    var obj=${data};
    if(obj){
    	
    	if($("#contractAmount").val()==""){
    		$(".noUser").addClass("hidden");
    	}else{
    		$(".noUser").removeClass("hidden");
    	}
    	
    }
    
    var date1='${data.plannedStartDate}';
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
    
    //放弃
    $(".auditCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("auditInstTasks/main");
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
            	$.ajax({
                    type: 'POST',
                    url: 'auditInstTasks/auditSave',
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
    
    /**
     * 初始化历史审批列表
     */
    var projId = $("#projId").val();
    var businessOrderId = $("#businessOrderId").val();
    var histSearchData = {"projId":projId,"businessOrderId":businessOrderId};
    var handleGasAuditHistory = function() {
    	"use strict";
        if ($('#auditHistoryTable').length !== 0) {
        	$('#auditHistoryTable').on( 'init.dt',function(){
       			//隐藏遮罩
       			$('#auditHistoryTable').hideMask();
       			
            }).DataTable({
            	language: language_CN,
                lengthMenu: [18],
                dom: 'Brt',
                buttons: [
                ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'auditInstTasks/queryManageRecord',
                    type:'post',
                    data: function(d){
                       	$.each(histSearchData,function(i,k){
                       		d[i] = k;
                       	});
                       	
                    },
                    dataSrc: 'data'
                },
                responsive: {
                	details: {
                		renderer: function ( api, rowIdx, columns ){
                			return renderChild(api, rowIdx, columns);
                		}
                	}
                },
                select: true,  //支持多选
                columns: [
    				{"data":"mrTime"},
    				{"data":"mrResult"},
    				{"data":"mrAopinion"},
    				{"data":"mrCsr"}
    			],
    			columnDefs: [{
    				"targets": 0,
    				"render": function ( data, type, row, meta ) {
    					if(type === "display"){
    						return format(data,'all');
    					}else{
    						return data;
    					}
    				},
    			},{
    				"targets": 1,
    				"render": function ( data, type, row, meta ) {
    					if(data === "1"){
    						return "通过";
    					}else if(data === "0"){
    						return "不通过";
    					}else{
    						return "";
    					}
    				},
    			}]
            });
        }
    };

    
    $(function(){
    	handleGasAuditHistory();
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->