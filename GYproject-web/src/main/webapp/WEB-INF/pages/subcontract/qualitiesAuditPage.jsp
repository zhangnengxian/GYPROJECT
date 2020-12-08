<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <h4 class="panel-title">工程量清单</h4>
			</div>
            <div class="panel-body">
					<div class="toolBtn f-r p-b-10  editbtn">
						<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 acceptSaveBtn" >保存</a>
					</div>
				    <div class="clearboth form-box">
				    	<form class="form-horizontal" id="planEstablishDetailform" action="">
				    		<input type="hidden" class="form-control field-editable" id="projId1" name="projId" value="${subBudget.projId }"/>
				    		<input type="hidden" class="form-control field-editable" id="sbId" name="sbId" value="${subBudget.sbId }"/>
				    		<input type="hidden" class="form-control field-editable" id="mainMaterialAmountListAudit" name="mainMaterialAmountListAudit" value="${subBudget.mainMaterialAmountListAudit }"/>
				    		<input type="hidden" class="form-control field-editable" id="mainMaterialAmountAudit" name="mainMaterialAmountAudit" value="${subBudget.mainMaterialAmountAudit }"/>
				    		<input type="hidden" id="flag" name="flag" value="" >
				    		<input type="hidden" id="audit" name="audit" value="" >
							<input type="hidden" id="compilerSign" name="compilerSign" value="${subBudget.compilerSign}" >
				    		<input type="hidden" id="suBudgeterId" name="suBudgeterId" value="${subBudget.suBudgeterId }" >
							<input type="hidden" id="cuAudit" name="cuAudit" value="${subBudget.cuAudit }" >
							<input type="hidden" id="cuPrincipal" name="cuPrincipal" value="${subBudget.cuPrincipal }" >
				    		<input type="hidden" id="suBudgeter" name="suBudgeter" value="${subBudget.suBudgeter }" >
				    		<div class="form-group  col-md-6 ">
						        <label class="control-label" for="projNo">工程编号</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value="${subBudget.projNo }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
								<label class="control-label" for="costType">造价类型</label>
						        <div>
							        <select class="form-control input-sm field-not-editable" id="costType"  name="costType"  data-parsley-required="true">
							        	<c:forEach var="enums" items="${costType}">
									        <c:choose>
									        	<c:when test="${enums.value==subBudget.costType}">
									        		<option value="${enums.value}" selected >${enums.message}</option>
									        	</c:when>
									        	<c:otherwise>
									        		<option value="${enums.value}" >${enums.message}</option>
									        	</c:otherwise>
									        </c:choose>
								        </c:forEach>
							        </select>
						        </div>
							</div>
				    		<div class="form-group col-md-12 ">
						        <label class="control-label" for="projName">工程名称</label>
						        <div> 
						            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value="${subBudget.projName }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
						        <label class="control-label" for="projAddr">工程地点</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr"value="${subBudget.projAddr }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-12">
					            <label class="control-label" for="projScaleDes">工程规模</label>
					            <div>
					                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2">${subBudget.projScaleDes }</textarea>
					            </div>
				            </div>
						     <div class="form-group col-md-12 ">
						        <label class="control-label" for="cuName">施工单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" value="${subBudget.cuName }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="cuLegalRepresent">负责人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuLegalRepresent" name="cuLegalRepresent" value="${subBudget.cuLegalRepresent }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="cuPhone">联系方式</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone" value="${subBudget.cuPhone }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="conNo">安装合同编号</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo" value="${contract.conNo }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="contractAmount">安装合同金额</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="contractAmount" name="contractAmount" value="${contract.contractAmount }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="supConAmount">补充协议金额</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="supConAmount" name="supConAmount" value="${supConAmount }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						       	 </div>
						    </div>
						    <div class="form-group col-md-6 hidden">
						        <label class="control-label" for="imcAmount">智能表合同金额</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="imcAmount" name="imcAmount" value="${imcAmount }"/>
						        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						     <div class="form-group col-md-6 clearboth">
						        <label class="control-label" >预算日期</label>
						        <div>
						        	<input type="text" class="form-control field-not-editable input-sm datepicker-default"  id="sbDate" name="sbDate"  data-parsley-required="true"value="${subBudget.sbDate }"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden DETAILED_LIST">
						    	<label class="control-label" for="totalCostAudit">初始造价</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="totalCostAudit" name="totalCostAudit" data-parsley-type="number" value="${subBudget.totalCostAudit }"/>
						        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						     <div class="form-group col-md-6 hidden QUOTA">
						    	<label class="control-label" for="totalQuotaAudit">初始造价</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="totalQuotaAudit" name="totalQuotaAudit" data-parsley-type="number" value="${subBudget.totalQuotaAudit }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden DETAILED_LIST clearboth">
						    	<label class="control-label" for="totalAmount">合计</label>
						        <div>
						        	<input type="text" class="DETAILED_LIST form-control input-sm field-not-editable fixed-number text-right" id="totalAmount" name="totalAmount" value="${subBudget.totalAmount }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden DETAILED_LIST">
						    	<label class="control-label" for="mainMaterialAmountList">主材费</label>
						        <div>
						        	<input type="text" class="DETAILED_LIST form-control input-sm field-editable fixed-number text-right" id="mainMaterialAmountList" name="mainMaterialAmountList" data-parsley-type="number"  data-parsley-maxlength="16" value="${subBudget.mainMaterialAmountList }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						     <div class="form-group col-md-6 hidden DETAILED_LIST">
						    	<label class="control-label" for="totalCost">总造价</label>
						        <div>
						        	<input type="text" class="DETAILED_LIST form-control input-sm field-editable fixed-number text-right" id="totalCost" name="totalCost" data-parsley-type="number" data-parsley-required="true" data-parsley-maxlength="16" value="${subBudget.totalCost }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden QUOTA">
						    	<label class="control-label" for="mainMaterialAmount">主材费</label>
						        <div>
						        	<input type="text" class="QUOTA form-control input-sm field-editable fixed-number text-right" id="mainMaterialAmount" name="mainMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16" value="${subBudget.mainMaterialAmount }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						    	<label class="control-label" for="laborCost">工程费</label>
						        <div>
						        	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="laborCost" name="laborCost" data-parsley-type="number" data-parsley-maxlength="16" value="${subBudget.laborCost }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
						    	<label class="control-label" for="auxiliaryMaterialAmount">破路费</label>
						        <div>
						        	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="auxiliaryMaterialAmount" name="auxiliaryMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16" value="${subBudget.auxiliaryMaterialAmount }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						    	<label class="control-label" for="managementCost">协调费</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="managementCost" name="managementCost" data-parsley-type="number" data-parsley-maxlength="16" value="${subBudget.managementCost }"/>
						       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
						    	<label class="control-label" for="profit">其他费用</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="profit" name="profit" data-parsley-type="number" data-parsley-maxlength="16" value="${subBudget.profit }"/>
						        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 hidden QUOTA">
						    	<label class="control-label" for="totalQuota">总造价</label>
						        <div>
						        	<input type="text" class="QUOTA form-control input-sm field-editable fixed-number text-right" id="totalQuota" name="totalQuota" data-parsley-required="true" data-parsley-type="number" data-parsley-maxlength="16" value="${subBudget.totalQuota }"/>
						        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						    
						    <div class="form-group col-md-12">
					            <label class="control-label" for="remark">备注</label>
					            <div>
					                <textarea class="form-control field-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="200">${subBudget.remark }</textarea>
					            </div>
				            </div>
				            <div class="form-group col-md-6 col-sm-12 col-sm-6" id="materialIsRegister">
					        	<label class="control-label" for="materialIsRegister">物资是否已收款</label>
					    		<div>
						            <label>
						              	<input type="radio" class="field-not-editable"  name="materialIsRegister"  value="1" <c:if test="${contract.materialIsRegister==1}"> checked </c:if> /> 是
						            </label>
						            <label>
						              	<input type="radio" class="field-not-editable"  name="materialIsRegister"  value="0" <c:if test="${contract.materialIsRegister==0}"> checked </c:if>/> 否
						            </label>
					        	</div>
							</div>
				             <div class="form-group col-md-12" id="materialRemark">
					            <label class="control-label" for="materialRemark">物资登记备注</label>
					            <div>
					                <textarea class="form-control field-not-editable" name ="materialRemark" id="materialRemark" rows="2" data-parsley-maxlength="1000" >${contract.materialRemark }
					                </textarea>
					            </div>
				            </div>
						</form>
				    </div>
				    <div class="DETAILED_LIST">
						<table id="qualitiesTable" class="table table-hover table-bordered nowrap" width="100%">
					    	<thead>
						        <tr>
						            <th>id</th>
						            <th>id</th>
						            <th>分部分项工程名称</th>
						            <th width="60">单位</th>
						            <th width="60">单价</th>
						            <th width="60">数量</th>
						            <th width="60">金额</th>
						        </tr>
					      </thead>
					      <tfoot>
					      		<tr>
					      		    <td></td>
					      		    <td></td>
					      			<td>合计</td>
					      			<td></td>
					      			<td></td>
					      			<td></td>
					      			<td class="total-amount"></td>
					      		</tr>
					      </tfoot>
						</table>
					</div>
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
				<h4 class="panel-title">审批区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
				<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box qualitiesAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="qualitiesAuditForm" action="qualitiesJudgement/auditSave">
			    			<input  type="hidden" id="projId" name = "projId" value = "${projId}"/>
			    			<input  type="hidden" id="menuId" name = "menuId" />
			    			<input  type="hidden" id="signPicture" name = "signPicture" value = "${loginInfo.signPicture}"/>
			    			<input type="hidden"  name = "projNo" value = "${subBudget.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${subBudget.sbId}"/>
                    		<input type="hidden" id = "mrAuditLevel" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">审批结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0" />不通过
				            	</label>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">审批意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-md-6">
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
							<div class="form-group col-md-6">
								<label class="control-label signature-tool" for="" style="width: 90px;">签字</label>
								<div>
									<input type="hidden" class="sign-data-input disabled" id="signPicturePath" name=signPicturePath value="">
									<img class="signPicture" alt="" src="" style="height: 30px"> 
									<span class="clear-sign disabled"><i class="fa ion-close-circled"></i></span>
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
					<div class="hidden">
				    	<form method="post" action="qualitiesDeclaration/exportExcel" id="subQualitiesForm">
				    		<input id="projId1" name="projId" value = "${projId}"/>
				    		<input id="sbId1" name="sbId" value="${subBudget.sbId }"/>
				    	</form>
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
    App.setPageTitle('工程量审定 - 工程管理系统');
    
    $('#qualitiesAuditForm').toggleEditState();
    $('#qualitiesAuditForm').styleFit();
    $('#planEstablishDetailform').toggleEditState(true).styleFit();
    
    if($("#costType").val()=='1'){
		$(".QUOTA").addClass("hidden");
		$(".DETAILED_LIST").removeClass("hidden");
	}else{
		$(".QUOTA").removeClass("hidden");
		$(".DETAILED_LIST").addClass("hidden");
	}

    $("#totalAmount").on("change",function(){
    	$("#totalCost").val($("#totalAmount").val());
    	
    });
    $("#mainMaterialAmount,#profit,#laborCost,#auxiliaryMaterialAmount,#managementCost,#statuteCost,#taxCost").on("change",function(){
    	if($("#costType").val()=='2'){
    		$("#totalQuota").val((new Number($("#mainMaterialAmount").val())+new Number($("#profit").val())+new Number($("#auxiliaryMaterialAmount").val())+new Number($("#managementCost").val())+new Number($("#laborCost").val())).toFixed(2));
    	}
    });
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/subcontract/qualities-audit-page.js?'+Math.random()).done(function () {
        qualitiesJudgement.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("qualitiesJudgement/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	if(!$(".acceptSaveBtn").is(":hidden")){
    		$("body").cgetPopup({
				title: '提示',
				content: '请先保存预算审核信息！',
				accept: ensureDone
	    	});
    		return false;
    	}
    	var val=$('#qualitiesAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择审批结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#menuId").val(getStepId());
    		$("#qualitiesAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    var ensureDone=function(){};
    if($("#mrAuditLevel").val()==1){
    	$("input:radio[name='mrResult']").eq(0).attr("checked",true);
    	$("input:radio[name='mrResult']").attr("disabled","disabled");
	}else{
		$("input:radio[name='mrResult']").attr("checked",false);
		$("input:radio[name='mrResult']").attr("disabled",false);
	}
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.qualitiesAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		$("#qualitiesAuditForm").toggleEditState(false);
    		$('#planEstablishDetailform').toggleEditState(false);
    		$(".acceptSaveBtn").addClass("hidden");
			$(".right-btn").addClass("hidden");
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    	}else{
    		$(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
    	}
    }();
    $("#qualitiesAuditForm input[name='mrResult']").on("change",function(){
        if($('#qualitiesAuditForm input:radio[name="mrResult"]:checked').val() == "1"){
            $("#mrAopinion").val("同意");
        }else{
            $("#mrAopinion").val("不同意");
        }
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->