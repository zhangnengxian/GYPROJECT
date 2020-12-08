<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
	    <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="contents">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			          <ul class="nav nav-tabs">
		                <li class="active"><a href="#default-tab-1" id="settlementInfo"  data-toggle="tab">终审信息</a></li>
		                <li class=""><a href="#default-tab-2" id="qualitiesTab"   data-toggle="tab">工程量</a></li>        
	                </ul>
			    </div>
			    <div class="panel-body" id="connect_gas_audit_panel_box">
			    <div class="tab-content">
						<div class="tab-pane fade active in btn-top" id="default-tab-1">
                        	<div id="endConfirm_audit_panel_box">
                        		<div class="infodetails">
                        			<div class="toolBtn f-r editbtn">
			    	<a href="javascript:;" class="btn btn-confirm btn-sm  auditSaveBtn">保存</a>
				</div>
                <div class="clearboth form-box ">
                 	<form class="form-horizontal" id="reportConfirmForm" action="">
                 		<input type="hidden" name="projId" />
                 		<input type="hidden" id="corpId" name="corpId" value=""/>
    					<input type="hidden" id="deptId" name="deptId" value=""/>
    					<input type="hidden" id="tenantId" name="tenantId" value=""/>
    					<input type="hidden" id="compilerId" name="compilerId" value=""/>
    					<input type="hidden" name="firstAuditerId" value=""/>
                 		<input type="hidden" name="sdId" id="sdId"/>
                 		<input type="hidden"  id="isPrint" name="isPrint" value=""/>
                 		<input type="hidden" name="finalAuditerId" />
                 			<!-- 审核信息 -->
			    	     	<%-- <input type="hidden" id="businessOrderId" name="businessOrderId" />			       	
                    		<input type="hidden" id="mrAuditLevel" name="mrAuditLevel" value="${currentLevel}" />
                    		<input type="hidden" name="mrDeptId" value="${loginInfo.deptId}"/>
                      		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/> --%>
                 		
                 	   	<div class="form-group col-md-6 ">
					    	<label class="control-label" for="projNo">工程编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 clearboth">
					        <label class="control-label" for="projName">工程名称</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="500"/>
					        </div>
					    </div>								       		
					    <div class="form-group col-md-12  ">
					        <label class="control-label" for="projAddr">工程地点</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100"/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					    	<label class="control-label" for="projScaleDes">工程规模</label>
					        <div>
					        	<textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
				           	<!-- 新加字段 -->
					    	<label class="control-label" for="">燃气公司</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value="贵州燃气集团股份有限公司"/>
					        </div>
						    </div>
						    		<div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="">工程类型</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="projTypeDesc" name="projTypeDesc"  data-parsley-maxlength="100" value=""/>
					    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
									<option value="1" >居民户工程</option>
					             </select> -->
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="">出资方式</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="projContributionModeDes" name="projContributionModeDes"  data-parsley-maxlength="100" value=""/>
					    		<!-- <select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
									<option value="1" >用户出资（自有资金）</option>
					             </select> -->
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<!-- 新加字段 -->
					        <label class="control-label" for="">业务部门</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100" value=""/>
					    		
					    		<!-- <select class="form-control input-sm field-not-editable" id="businessDeptName"  name="businessDeptName" data-parsley-required="true" >
									<option value="1" >民用组</option>
					             </select> -->
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					        <label class="control-label" for="drawingNo">工程图号</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="drawingNo" name="drawingNo"/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					        <label class="control-label" for="suName">监理单位</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"/>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					        <label class="control-label" for="cuName">施工单位</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="conNo">安装合同编号</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="conNo">安装合同金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="contractAmount" name="contractAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div>
					    <div class="form-group col-md-12 ">
					        <label class="control-label" for="supConAmount">补充协议金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="supConAmount" name="supConAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div>
					     <div class="form-group col-md-6 col-sm-12 col-sm-6" id="materialIsRegister">
					        	<label class="control-label" for="materialIsRegister">物资是否已收款</label>
					    		<div>
						            <label>
						              	<input type="radio" class="field-not-editable"  name="materialIsRegister"  value="1"  /> 是
						            </label>
						            <label>
						              	<input type="radio" class="field-not-editable"  name="materialIsRegister"  value="0" /> 否
						            </label>
					        	</div>
							</div>
				             <div class="form-group col-md-12" id="materialRemark">
					            <label class="control-label" for="materialRemark">物资登记备注</label>
					            <div>
					                <textarea class="form-control field-not-editable" name ="materialRemark" id="materialRemark" rows="2" data-parsley-maxlength="1000" >
					                </textarea>
					            </div>
				            </div>
					    <div class="form-group col-md-12 hidden ">
					        <label class="control-label" for="imcAmount">智能表合同金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="imcAmount" name="imcAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="totalAmount">工程预收款</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="totalAmount" name="totalAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="finalAmount">工程实收款</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="finalAmount" name="finalAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div>
					     <!-- <div class="form-group col-md-6 ">
					        <label class="control-label" for="subBudgetCost">施工预算审定价</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="subBudgetCost" name="subBudgetCost"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div> -->
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="subApplyAmount">工程预付款</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="subApplyAmount" name="subApplyAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div>
					    <!-- <div class="form-group col-md-6 ">
					        <label class="control-label" for="subBudgetCost">工程已付款</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="subAmount" name="subAmount"/>
					            <a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        </div>
					    </div> -->
					   <!--  <div class="form-group col-md-6 ">
					        <label class="control-label" for="cuPhone">联系方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone"/>
					        </div>
					    </div> -->
					    <div class="form-group col-md-12 ">
					    	<label class="control-label" for="scNo">分合同编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="scNo" name="scNo"/>
					        </div>
					    </div>
					    
					    <div class="form-group col-md-6 clearboth disabledbtn">
					        <label class="control-label" for="sendDeclarationCost">报审金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable  text-right fixed-number" id="sendDeclarationCost" name="sendDeclarationCost" data-parsley-type="number"/>
					        	<a href="javascript:;" class="btn btn-sm btn-default disabledbtn">元</a>
					        	</div>
					    </div>
					    <div class="form-group col-md-6 disabledbtn">
					        <label class="control-label" for="materialsCost">报审主材费</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number" id="materialsCost" name="materialsCost" data-parsley-type="number"/>
					       <a href="javascript:;" class="btn btn-sm btn-default ">元</a>
					        </div>
					    </div>
					    <!-- <div class="form-group col-md-6 clearboth">
				<label class="control-label" for="cuAdvanceCost">施工单位垫付费</label>
					<div>
						<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="cuAdvanceCost" name="cuAdvanceCost" data-parsley-type="number" data-parsley-maxlength="16"/>
					</div>
			 </div> -->
			<!--  <div class="form-group col-xs-12 selfcheckformtitle">
				 <i class="fa fa-angle-double-right"></i>施工单位垫付费
			</div> -->
		     <div class="form-group col-md-6 disabledbtn">
				<label class="control-label" for="auxiliaryMaterialAmount">破路费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="auxiliaryMaterialAmount" name="auxiliaryMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 disabledbtn">
				<label class="control-label" for="managementCost">协调费</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="managementCost" name="managementCost" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
				</div>
			</div>
		     <div class="form-group col-md-6 disabledbtn">
				<label class="control-label" for="recoveryCost">恢复费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="recoveryCost" name="recoveryCost" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 disabledbtn">
				<label class="control-label" for="jeevesCost">占道费</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="jeevesCost" name="jeevesCost" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 disabledbtn">
				<label class="control-label" for="compensateCost">赔偿费</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="compensateCost" name="compensateCost" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
				</div>
			</div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label">报审日期</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable timestamp" id="ocoDate" name="ocoDate"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 clearboth disabledbtn">
					        <label class="control-label" for="firstDeclarationCost">初审金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number" id="firstDeclarationCost" name="firstDeclarationCost" data-parsley-type="number"/>
					       		<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
					        </div>
					    </div>
					    <div class="form-group col-md-6 disabledbtn">
					        <label class="control-label" for="firstMaterialsCost">含初审主材费</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="firstMaterialsCost" name="firstMaterialsCost"  data-parsley-type="number" value=""/>
					       		<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label">初审日期</label>
					        <div>
					            <input type="text" class="form-control input-sm datepicker-default field-not-editable timestamp" id="firstAuditDate" name="firstAuditDate"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 clearboth disabledbtn">
					        <label class="control-label" for="auditMinusCost">初审核减金额</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="auditMinusCost" name="auditMinusCost" data-parsley-type="number"/>
					       		<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
					        </div>
					    </div>
					    <div class="form-group col-md-6 ">
					        <label class="control-label" for="auditMinusRate">初审核减率</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable" id="auditMinusRate" name="auditMinusRate"  data-parsley-maxlength="12"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 disabledbtn">
					        <label class="control-label" for="quantitiesTotal">合计</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="quantitiesTotal" name="quantitiesTotal"  data-parsley-type="number" value=""/>
					       		<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
					        </div>
					    </div>
					     <div class="endDeclaration">
						    <div class="form-group col-md-6 clearboth ">
						        <label class="control-label" for="endDeclarationCost">终审金额</label>
						        <div>
						            <input type="text" class="form-control input-sm fixed-number field-editable text-right" id="endDeclarationCost" name="endDeclarationCost" data-parsley-required="true" data-parsley-type="number"/>
						        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
						        </div>
						    </div>
						   
						    <div class="form-group col-md-6 ">
						        <label class="control-label" for="endMaterialsCost">终审主材费</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable text-right fixed-number"  id="endMaterialsCost" name="endMaterialsCost" data-parsley-required="true" data-parsley-type="number" value=""/>
						        	<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
						        </div>
						    </div>
						    <div class="form-group col-md-6 ">
								<label class="control-label" for="auxiliaryMaterialAmountAudit">破路费</label>
								<div>
									<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="auxiliaryMaterialAmountAudit" name="auxiliaryMaterialAmountAudit" data-parsley-type="number" data-parsley-maxlength="16"/>
									<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
								</div>
							</div>
							<div class="form-group col-md-6 ">
								<label class="control-label" for="managementCostAudit">协调费</label>
								<div>
									<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="managementCostAudit" name="managementCostAudit" data-parsley-type="number" data-parsley-maxlength="16"/>
									<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
								</div>
							</div>
						     <div class="form-group col-md-6 ">
								<label class="control-label" for="recoveryCostAudit">恢复费</label>
								<div>
									<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="recoveryCostAudit" name="recoveryCostAudit" data-parsley-type="number" data-parsley-maxlength="16"/>
									<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
								</div>
							</div>
							<div class="form-group col-md-6 ">
								<label class="control-label" for="jeevesCostAudit">占道费</label>
								<div>
									<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="jeevesCostAudit" name="jeevesCostAudit" data-parsley-type="number" data-parsley-maxlength="16"/>
									<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
								</div>
							</div>
							<div class="form-group col-md-6 ">
								<label class="control-label" for="compensateCostAudit">赔偿费</label>
								<div>
									<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="compensateCostAudit" name="compensateCostAudit" data-parsley-type="number" data-parsley-maxlength="16"/>
									<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
								</div>
							</div>
						     <div class="form-group col-md-6 disabledbtn">
						        <label class="control-label" for="endAuditMinusCost">核减金额</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number" id="endAuditMinusCost" name="endAuditMinusCost" data-parsley-required="true" data-parsley-type="number"/>
						        	<a href="javascript:;" class="btn btn-sm btn-default ">元</a>
						        </div>
						    </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="endAuditMinusRate">终审核减率</label>
					        <div>
					            <input type="text" class="form-control input-sm fixed-number text-right field-not-editable" id="endAuditMinusRate" name="endAuditMinusRate" />
					        </div>
					    </div>
					    <%-- <div class="form-group col-md-6 show1">
							<label class="control-label signature-tool" for="costControlAudit" id="costControlAuditLabel">预算员</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="costControlAudit" name="costControlAudit" data-parsley-required="true">
								<input type="hidden" id="costControlAudit_postType" name="costControlAudit_postType" value="${MANAGER }" >
								<img class="" alt="" src="" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-12  show2">
								<label class="control-label signature-tool" for="costControlPrincipal" id="costControlPrincipalLabel">预算组组长</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="costControlPrincipal" name="costControlPrincipal" value="">
									<input type="hidden"  id="costControlPrincipal_post" name="costControlPrincipal_post" value="${constPCpost }">
									<img class="costControlPrincipal" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12 clearboth show3">
								<label class="control-label signature-tool" for="costControlReAudit" id="costControlReAuditLabel">市场发展部副部长</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="costControlReAudit" name="costControlReAudit" value="">
									<input type="hidden"  id="costControlReAudit_post" name="costControlReAudit_post" value="${constPCpost }">
									<img class="costControlReAudit" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
								</div>
						    </div> --%>
					   <!--  <div class="form-group col-md-6 show4">
					        <label class="control-label" for="endDeclaraDate">终审日期</label>
					        <div>
					            <input type="text" class="form-control input-sm datepicker-default field-editable timestamp" id="endDeclaraDate" name="endDeclaraDate" data-parsley-required="true"/>
					        </div>
					    </div> -->
						<div class="form-group col-md-6 clearboth" >
					        <label class="control-label" for="compiler">编制人</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="compiler" name="compiler" data-parsley-maxlength="20" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="compilerPhone">联系方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="compilerPhone" name="compilerPhone" data-parsley-maxlength="13" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="firstAuditer">初审人</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="firstAuditer" name="firstAuditer" data-parsley-maxlength="20" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="firstAuditerPhone">联系方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="firstAuditerPhone" name="firstAuditerPhone" data-parsley-maxlength="13" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-12">
					     	<label class="control-label" for="remark">备注</label>
					     	<div> 
					        	<textarea class="form-control  field-editable" name="remark" id="remark" rows="3" cols="" data-parsley-maxlength="200" ></textarea>
				        	</div>
					    </div>
					    <!-- <div class="form-group col-md-6">
					        <label class="control-label" for="finalAuditer">终审人</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="finalAuditer" name="finalAuditer" data-parsley-maxlength="20" value=""/>
					        </div>
					    </div>
					    <div class="form-group col-md-6">
					        <label class="control-label" for="finalAuditerPhone">联系方式</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable"  id="finalAuditerPhone" name="finalAuditerPhone" data-parsley-maxlength="13" value=""/>
					        </div>
					    </div> -->
			    	</form>
                   </div>
                        		</div>
                        	</div>
                        </div>
		    		 <!-- <div id="qualities_list_box"> -->
		    		 <div class="tab-pane fade in btn-top" id="default-tab-2">
                      	<form id="qualitiesForm" data-parsley-validate="true">
                      		<input type="hidden" id="projId" name="projId" />
			    	     	<input type="hidden" name="projNo" />
			    	     	<input type="hidden" name="projName" />
			    	     	<input type="hidden" name="projScaleDes" />
                      		<input type="hidden" class="form-control field-editable" id="init_quantitiesTotal" name="quantitiesTotal" value=""/>
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
						</form>
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
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
				<div class="panel-body clearboth" id="audit_panel_box">
					<div id="budgetAuditTopBox">
					<div class="toolBtn f-r m-b-15  auditEditBtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 confirmSaveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 confirmCancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box auditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<input  type="hidden" id="signPicture" name = "signPicture" value = "${loginInfo.signPicture}"/>
			    		<form class="form-horizontal" id="setttlementAuditForm" action="settlementConfirm/endAuditSave">
			    			<input type="hidden" id="projId1" name = "projId" value = "${projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo" "/>
			    			<input type="hidden" id="menuId" name = "menuId" "/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" />
                    		<input type="hidden" id="mrAuditLevel" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0"/>不通过
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
						   <%--  <div class="form-group col-md-6">
							<label class="control-label signature-tool sign-require" for="suPrincipal" id="">签字</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="suPrincipal" name="suPrincipal${currentLevel }" data-parsley-required="true">
								<input type="hidden" id="suPrincipal_postType" name="suPrincipal_postType" value="${settlmenterPost }" >
								<img class="" alt="" src="" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
							</div>
						</div> --%>
						<div class="form-group col-md-6">
								<label class="control-label signature-tool" for="" style="width: 90px;">签字</label>
								<div>
									<input type="hidden" class="sign-data-input disabled" id="suPrincipal" name="suPrincipal${currentLevel}"value="">
									<img class="signPicture" alt="" src="" style="height: 30px"> 
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
    
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('终审确认 - 工程管理系统');
        var obj=${settlementDeclaration};
        $("#projId").val(obj.projId);
        //console.info(obj);
        //$("#endAuditRightForm [name='projNo']").val(obj.projNo);
        $("#businessOrderId").val(obj.sdId);
        $("#reportConfirmForm").deserialize(obj);
       
    	//签字加载方式
       /*  $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6, #signBtn_7, #signBtn_8, #signBtn_9").handleSignature(false);  
    		if($("#mrAuditLevel").val()=="1"){
    			$(".sign6").removeClass("hidden");
    		}else{
    			$(".sign5").removeClass("hidden");
    		} */
    	$("#reportConfirmForm").toggleEditState(false).styleFit();
    	$('#reportConfirmForm .disabledbtn').formReset();
        $("#setttlementAuditForm").toggleEditState(true).styleFit();
        
        /* var mrAuditLevel = $("#mrAuditLevel").val();
      	if(mrAuditLevel === "1"){
        	 $(".show2").addClass("hidden");
        	 $(".show3").addClass("hidden");
        	 $(".show4").addClass("hidden");
        	 $('.endDeclaration').toggleEditState(true);
        	 $("#costControlAuditLabel").addClass('sign-require');
        	 $('.show2').empty();
        	 $('.show3').empty();
        	 if(isAudit==='1'){
        		 $("#reportConfirmForm").toggleEditState(false);
        	 }else{
        		 $("#reportConfirmForm").toggleEditState(true);
        	 }
        	 
        }else if(mrAuditLevel === "2"){
        	$("#signBtn_1").toggleSign(false);
        	 $(".show3").addClass("hidden");
        	 $(".show4").addClass("hidden");
        	 $("#costControlPrincipalLabel").addClass('sign-require');
        	 $('.endDeclaration').toggleEditState(false);
        	 $('.show3').empty();
        	 $("#reportConfirmForm").toggleEditState(false);
        }else{
        	$("#signBtn_1").toggleSign(false);
        	$("#signBtn_2").toggleSign(false);
        	$('.endDeclaration').toggleEditState(false);
        	 $(".show2").toggleEditState(false);
        	 $("#costControlReAuditLabel").addClass('sign-require');
        	 $("#reportConfirmForm").toggleEditState(false);
        }  */
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });

    $("#mrTime").change();
    
    $.getScript('projectjs/settlement/confirm-audit-done.js?v='+Math.random()).done(function () {
    	sttlementAuditDone.init();
	});
    //终审信息点击保存
    $(".auditSaveBtn").on("click",function(){
    	/* if($("#quantitiesTotal").val()=='0.00'){
    		var myoptions = {
                   	title: "提示信息",
                   	content: "请导入报审工程量",
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
    	}else  */
    	/* if($("#quantitiesTotal").val()!==$("#init_quantitiesTotal").val()){
    		var myoptions = {
                   	title: "提示信息",
                   	content: "请先保存报审工程量",
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
    	}else{ */
    		//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
        	//表单验证
            if($("#reportConfirmForm").parsley().isValid()){
            	
            	//验证必签签字是否已签
    	        var signtools =$('#reportConfirmForm').find(".signature-tool.sign-require"),
    	        stl = signtools.length,
    	        sBlank = 0;
    	        for(var i=0; i<stl; i++){
    	        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
    	        	tsinput = tstool.siblings(".sign-data-input");
    	        	if(!tsinput.val() || tsinput.val().length < 312){
    	        		tstool.addClass("required-sign");
    	        		sBlank++;
    	        	}
    	        }
    	        if(sBlank){
   		        	$("body").cgetPopup({
   		            	title: "提示信息",
   		            	content: "请完成签字!",
   		            	accept: popClose,
   		            	chide: true,
   		            	icon: "fa-warning",
   		            	newpop: 'new'
   		            });
    	        	return false;
    	        }
          
            	//json字符串
            	var data=$("#reportConfirmForm").serializeJson();
            	data.projId = $("#projId").val();
            	$.ajax({
                    type: 'POST',
                    url: 'settlementAuditDone/saveAuditDone',
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(data),
                    beforeSend: function () {
        	              // 禁用按钮防止重复提交
        	              $(".auditSaveBtn").attr({ disabled: "disabled" });
        	           	},
                      complete: function () {
                      	$(".auditSaveBtn").removeAttr("disabled");
        	            },
                    success: function (data) {
                    	var content = "数据保存成功！";
                    	if(data === "false"){
                    		content = "数据保存失败！";
                    	}else if(data === "true"){
                    		//auditSaveCallBack("true");
                    		$("#reportConfirmForm").toggleEditState(false);
                    	}
                    	var myoptions = {
                            	title: "提示信息",
                            	content: content,
                            	accept: hiddenBtn,
                            	chide: true,
                            	icon: "fa-check-circle"
                        }
                        $("body").cgetPopup(myoptions);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("终审记录保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#reportConfirmForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#reportConfirmForm").parsley().validate();
            }
    	/* } */
    	
        
    });
	var hiddenBtn=function(){
    	$('.auditSaveBtn').addClass('hidden');
    	$('.right-btn').addClass('hidden');
    }
    //放弃
    $(".confirmCancelBtn").off("click").on("click",function(){
		$("#ajax-content").cgetContent("settlementConfirm/endConfirm");
	});
    //审核保存
    $(".confirmSaveBtn").on("click",function(){
    	if(!$(".auditSaveBtn").is(":hidden")){
    		$("body").cgetPopup({
				title: '提示',
				content: '请先保存终审信息！',
				accept: ensureDone
	    	});
    		return false;
    	}
    	var val=$('#setttlementAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择审批结果！',
				accept: ensureDone
	    	});
    	}else{
    		//验证必签签字是否已签
	        var signtools =$('#setttlementAuditForm').find(".signature-tool.sign-require"),
	        stl = signtools.length,
	        sBlank = 0;
	        for(var i=0; i<stl; i++){
	        	var tstool = signtools.eq(i).next("div").find("a.btn-white"),
	        	tsinput = tstool.siblings(".sign-data-input");
	        	if(!tsinput.val() || tsinput.val().length < 312){
	        		tstool.addClass("required-sign");
	        		sBlank++;
	        	}
	        }
	        if(sBlank){
		        	$("body").cgetPopup({
		            	title: "提示信息",
		            	content: "请完成签字!",
		            	accept: popClose,
		            	chide: true,
		            	icon: "fa-warning",
		            	newpop: 'new'
		            });
	        	return false;
	        }
    		//$("#setttlementAuditForm").cformSave("auditHistoryTable",auditSaveCallBack);
    		if($("#setttlementAuditForm").parsley().isValid()){
    			$("#audit_panel_box").loadMask("正在保存...", 1, 0.5);
    			$("#menuId").val(getStepId());
            	var data=$("#setttlementAuditForm").serializeJson();
            	$.ajax({
                    type: 'POST',
                    url: 'settlementConfirm/endAuditSave',
                    contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    data: JSON.stringify(data),
                    beforeSend: function () {
      	              // 禁用按钮防止重复提交
      	              $(".confirmSaveBtn").attr({ disabled: "disabled" });
      	           	},
                    complete: function () {
                    	$(".confirmSaveBtn").removeAttr("disabled");
      	            },
                    success: function (data) {
                    	$("#audit_panel_box").hideMask();
                    	console.info(data);
                    	var content = "数据保存成功！";
                    	if(data.ret_message=="true"){//成功
                    		$(".confirmSaveBtn,.auditFormDiv").addClass("hidden");
                    		$(".confirmCancelBtn").text("返回");
                    		$("#setttlementAuditForm").toggleEditState(false);
                    		$('#reportConfirmForm').toggleEditState(false);
                    		$(".auditSaveBtn").addClass("hidden");
                			$(".right-btn").addClass("hidden");
                			$("#auditHistoryTable").cgetData(true);  //列表重新加载
                        	alertInfo(content);
                    	}else if(data.ret_type==1){//接口异常
                    		alertInfo(data.ret_message);
                    	}else{
                    		content = "数据保存失败！";
                    		alertInfo(content);
                    	}
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.warn("终审确认保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#setttlementAuditForm").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#setttlementAuditForm").parsley().validate();
            }
    	}
    });
    
    var auditSaveCallBack= function(data){
    	if(data === "true"){
    		$(".confirmSaveBtn,.auditFormDiv").addClass("hidden");
    		$(".confirmCancelBtn").text("返回");
    		$("#setttlementAuditForm").toggleEditState(false);
    		$('#reportConfirmForm').toggleEditState(false);
    		$(".auditSaveBtn").addClass("hidden");
			$(".right-btn").addClass("hidden");
    	}
    }
 	
    /* $("#quantitiesTotal").on("change",function(){
    	$("#endDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
	}); */

    
  //核减金额输入框失去焦点时间
	$("#endDeclarationCost").on("change",function(){
		//计算核减
		$("#endAuditMinusCost").val((new Number($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val()))).toFixed(2));
		if($("#endAuditMinusCost").val()==0){
			$("#endAuditMinusRate").val($("#endAuditMinusCost").val());
		}else{
			$("#endAuditMinusRate").val(((new Number($("#endAuditMinusCost").val())/new Number($("#firstDeclarationCost").val()))*100).toFixed(2)+''+'%');
		}
		
	});
	if($("#mrAuditLevel").val()==1){
    	$("input:radio[name='mrResult']").eq(0).attr("checked",true);
    	$("input:radio[name='mrResult']").attr("disabled","disabled");
	}else{
		$("input:radio[name='mrResult']").attr("checked",false);
		$("input:radio[name='mrResult']").attr("disabled",false);
	}
	var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//审核过
    		auditSaveCallBack("true");
    		 $("#signBtn_4").toggleSign(false);
    	}else{
    		$(".signPicture").attr("src","attachments/sign/"+$("#signPicture").val())
    		$("#setttlementAuditForm").toggleEditState(true);
       		$("#signBtn_4").toggleSign(true);
    	}
    }();
    
    var ensureDone=function(){};
    /* 	输入数字校验 */ 
    $('.fixed-number').on('keyup', function(){
    	  $(this).parsley().validate();
    	});

    $("#setttlementAuditForm input[name='mrResult']").on("change",function(){
        if($('#setttlementAuditForm input:radio[name="mrResult"]:checked').val() == "1"){
            $("#mrAopinion").val("同意");
        }else{
            $("#mrAopinion").val("不同意");
        }
    })
</script>
<!-- ================== END PAGE LEVEL JS ================== -->