<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/jedate/skin/jedate.css" rel="stylesheet" />
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">开通计划信息</h4>
			</div>
            <div class="panel-body">
	              	<form class="form-horizontal" id="openingPlanForm" action="">
			    		<!-- <button type="reset" class="hidden" id="reset"/> -->
			    		<input type="hidden" id="gprojId" name="gprojId" />
			        	<div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="">工程编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="" />
					        </div>
					    </div>
					    <div class="form-group col-md-12 col-sm-12">
					        <label class="control-label" for="">工程名称</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
					        </div>
					    </div>
					    <div class="form-group  col-md-12 col-sm-12">
					        <label class="control-label" for="">工程地点</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 col-sm-12">
					        <label class="control-label" for="projScaleDes">工程规模</label>
					        <div>
					            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="200" ></textarea>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">燃气公司</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="projectTypeDes">工程类型</label>
					    	<div>
								<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  value=""/>
							</div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">施工单位</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">施工合同号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					    	<label class="control-label" for="">现场代表</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="preparerDes">填报人</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="preparerDes" name="preparerDes"   value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="preparDate">填报日期</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="preparDate" name="preparDate"   value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="preparDeptDes">填报部门</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="preparDeptDes" name="preparDeptDes"   value=""/>
					        </div>
					    </div>
					    <%--<div class="form-group col-md-6 col-sm-12">--%>
					    	<%--<!-- 新加字段 -->--%>
					        <%--<label class="control-label" for="opNo">表单号</label>--%>
					    	<%--<div>--%>
					    		<%--<input type="text" class="form-control input-sm field-editable"  id="opNo" name="opNo"   value=""/>--%>
					        <%--</div>--%>
					    <%--</div>--%>
					    <!-- <div class="form-group col-md-6 col-sm-12">
					    	新加字段
					        <label class="control-label" for="planGasDate">计划开通时间</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable datepicker-default"  id="planGasDate" name="planGasDate"   value=""/>
					        </div>
					    </div> -->
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="pipeMaterial">管道材质</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable"  id="pipeMaterial" name="pipeMaterial"   value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="pipeSize">管径大小</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable"  id="pipeSize" name="pipeSize"   value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="pipeRating">压力等级</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable"  id="pipeRating" name="pipeRating" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="isStepDown">是否降压</label>
					    	<div>
					    		<label> 
									<input type="radio" class="field-not-editable" name="isStepDown" value="1"  />是
								</label>
								<label> 
									<input type="radio" class="field-not-editable" name="isStepDown" value="0" checked="checked" />否
								</label>
					        </div>
					    </div>
					   <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="gasPoint">带气点数</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable"  id="gasPoint" name="gasPoint" data-parsley-maxlength="30" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="gasContent">开通内容</label>
							<div>
								<textarea class="form-control input-sm field-editable" id="gasContent" name="gasContent" data-parsley-maxlength="200" ></textarea>
							</div>
					    </div>
						<div class="form-group col-md-12 ">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="gasRemark">备注</label>
							<div>
								<textarea class="form-control input-sm field-editable" id="gasRemark" name="gasRemark" data-parsley-maxlength="200" ></textarea>
							</div>
					    </div>
					</form>
	        </div>
		</div>
		</div>
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
					<div id="gasAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
						<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 hidden dispatchBtnChange ">派工</a>
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box gasAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<%--<input type="hidden"  id="budgeterId" name="budgeterId" value="${project.budgeterId}">--%>
			    		<form class="form-horizontal" id="gasAuditForm" action="gasAudit/auditSave">
			    			<input  type="hidden" id="menuId" name = "menuId" />
			    			<input  type="hidden" id="signPicture" name = "signPicture" value = "${loginInfo.signPicture}"/>
			    			<input type="hidden" id="projId" name = "projId" value = "${gasProject.projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${gasProject.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${gasProject.gprojId}"/>
                    		<input type="hidden" name = "mrAuditLevel" id="currentLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
								<div>
									<c:forEach var="enums" items="${mrResult}">
										<label>
											<input type="radio" name="mrResult" value="${enums.value}"/> ${enums.message}
										</label>
									</c:forEach>
								</div>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">确认意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<c:if  test="${ empty viewUrl}">
		   					       <!-- 如果为空，则显示计划通气时间的页面，即需要配置分公司页面数据 -->
		   						<c:import url="guiYangGasAuditPage.jsp"></c:import>
		   					</c:if>
		   					<c:if  test="${ not empty viewUrl}">
		   					       <!-- 如果不为空，显示置分公司页面数据 -->
		   						<c:import url="${viewUrl}"></c:import>
		   					</c:if>
		   					<!-- <div class="form-group col-md-6 col-sm-12 planDate">
							 	<label class="control-label">计划开通开始时间</label>
								<div>
								     <input type="text" class="form-control input-sm " id="planGasDate" name="planGasDate"  data-parsley-required="true"  />
								</div>
							</div>
		   					<div class="form-group col-md-6 col-sm-12 planDate">
							 	<label class="control-label">计划开通结束时间</label>
								<div>
								     <input type="text" class="form-control input-sm " id="planGasEndDate" name="planGasEndDate" data-parsley-required="true"  />
								</div>
							</div> -->
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

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('预算审核 - 工程管理系统');
    var obj = ${gasProject};
    if(obj){
        $("#openingPlanForm").deserialize(obj);
    }
    
    $("#gasAuditForm").toggleEditState();
    $("#gasAuditForm").styleFit();
   
    
    $("#openingPlanForm").toggleEditState();
    $("#openingPlanForm").styleFit();

    trSData.t && trSData.t.cgetDetail('openingPlanForm','','',nollBack);
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/complete/gas_audit_page.js').done(function () {
    	auditHistory.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("gasAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#gasAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#menuId").val(getStepId());
    		$("#gasAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.gasAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#gasAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#gasAuditForm").toggleEditState(false); 
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
    	}else{
    		$(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
    	}
    	console.info("currentLevel----"+$("#currentLevel").val());
    	if($("#currentLevel").val()=="2"){
    		$(".planDate").removeClass("hidden");
    	}else{
    		$(".planDate").addClass("hidden");
    	}
    	
    }();
    
    $.getScript('assets/plugins/jedate/jedate.js').done(function() {
    	jeDate({
    		dateCell:"#planGasDate",
    	    format:"YYYY-MM-DD hh:mm",
    	    isTime:true,
    	    festival: true
    	})
    	jeDate({
    		dateCell:"#planGasEndDate",
    	    format:"YYYY-MM-DD hh:mm",
    	    isTime:true,
    	    festival: true
    	})
    });
        
    $('#gasAuditForm input:radio[name="mrResult"]').on("change",function(){
    	//二级审核的时候
    	if($("#currentLevel").val()=="2"){
    		var val=$('#gasAuditForm input:radio[name="mrResult"]:checked').val();
        	if(val=='0'){
        		$(".planDate").addClass("hidden");
        	}else{
        		$(".planDate").removeClass("hidden");
        	}
    	}
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->