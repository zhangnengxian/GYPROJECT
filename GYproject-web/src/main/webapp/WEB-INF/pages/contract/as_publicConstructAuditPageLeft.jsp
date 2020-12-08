<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
       		<input type="hidden" name="projId" id="projId" value="${contract.projId}"/>
       		<input type="hidden" name="conId" id="conId" value="${contract.conId}"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable conNo"  id="conNo" name="conNo" value="${contract.conNo}" />
		        </div>
		    </div>
		     <div class="form-group col-md-6">
	            <label class="control-label" for="contractType">合同类型</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="contractType"  name="contractType"  >
	               <c:forEach var="enums" items="${contractType}">
	               	<c:choose>
	               		<c:when test="${enums.value==contract.contractType}">
	               			<option value="${enums.value}" selected>${enums.message}</option>
	               		</c:when>
	               		<c:otherwise>
	               			<option value="${enums.value}" >${enums.message}</option>
	               		</c:otherwise>
	               	</c:choose>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value="${contract.projNo}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value="${contract.projName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value="${contract.projAddr}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" data-parsley-maxlength="100" value="${contract.projectTypeDes}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contributionModeDes">出资方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" data-parsley-maxlength="100" value="${contract.contributionModeDes}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="deptName">业务部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="100" value="${contract.deptName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" data-parsley-maxlength="200">${contract.projScaleDes}</textarea>
	            </div>
            </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">燃气用户</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value="${contract.custName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="13" value="${contract.custPhone}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custEntrustRepresent">经办人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value="${contract.custEntrustRepresent}" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth ">
		        <label class="control-label" for="gasComp">燃气经营企业</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value="${contract.gasComp}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="13" value="${contract.gasCompPhone}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="gasCorpAddr">单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="gasCorpAddr"  name="gasCorpAddr" data-parsley-maxlength="200" value="${contract.gasCorpAddr}">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="conScope">工程内容补充</label>
	            <div>
	                <textarea class="form-control field-editable" name ="conScope" id="conScope" rows="2" data-parsley-maxlength="200">${contract.conScope}</textarea>
	            </div>
            </div>
            <div class="form-group col-md-12 not-resident">
	            <label class="control-label" for="contractContent">用气设备</label>
	            <div>
		            <input type="text" class="form-control input-sm field-editable"  id="contractContent" name="contractContent" data-parsley-maxlength="200" value="${contract.contractContent}"/>
		        </div>
            </div>
            <div class="form-group col-md-12 not-resident">
	            <label class="control-label" for="responsibility">职责与义务</label>
	            <div>
	                <textarea class="form-control field-editable" name ="responsibility" id="responsibility" rows="2" data-parsley-maxlength="500">${contract.responsibility}</textarea>
	            </div>
            </div>
		    
			 <div class="form-group col-md-6">
		        <label class="control-label" for="timeLimit">工期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="timeLimit"  name="timeLimit" data-parsley-maxlength="10" value="${contract.timeLimit}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="budgetCost">确定总造价</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="budgetCost"  name="budgetCost" data-parsley-maxlength="100" value="${contract.budgetCost}" >
		       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="hoseAmount">每户金额</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="hoseAmount"  name="hoseAmount" data-parsley-type="number" data-parsley-maxlength="17" value="${contract.hoseAmount}"  >
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contractAmount">合同金额</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="contractAmount"  name="contractAmount" data-parsley-type="number" data-parsley-maxlength="13" value="${contract.contractAmount}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
	            <label class="control-label" for="payType">预算制度</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="budgetRule"  name="budgetRule"  >
	                <c:forEach var="enums" items="${budgetRuleEnum}">
	                	<c:choose>
	                		<c:when test="${enums.ptId == contract.budgetRule }">
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
		     <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="gasLoss">气损费</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="gasLoss"  name="gasLoss" data-parsley-type="number" data-parsley-maxlength="16" value="${contract.gasLoss}">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6">
	            <label class="control-label" for="payType">付款方式</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="payType"  name="payType"  >
	                <c:forEach var="enums" items="${payType}">
	                	<c:choose>
	                		<c:when test="${enums.ptId == contract.payType }">
	                			<option value="${enums.ptId}" selected >${enums.payTypeDes}</option>
	                		</c:when>
	                		<c:otherwise>
	                			<option value="${enums.ptId}" >${enums.payTypeDes}</option>
	                		</c:otherwise>
	                	</c:choose>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			<div class="form-group col-md-6 firstPayment hidden">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number" data-parsley-maxlength="13" value="${contract.firstPayment}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6 secondPayment hidden">
		        <label class="control-label" for="secondPayment">阶段款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="13" value="${contract.secondPayment}" >
		        </div>
		    </div>
			<div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="thirdPayment"  name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="13" value="${contract.thirdPayment}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="incrementAmount">增值税金额</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="incrementAmount"  name="incrementAmount"  data-parsley-maxlength="15" value="${contract.incrementAmount}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable  text-right" id="invoiceType" name="invoiceType" data-parsley-maxlength="20" value="${contract.invoiceType}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="openBank">开户行</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable  text-right" id="openBank"  name="openBank"  value="${contract.openBank}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="account">开户帐号</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable text-right" id="account"  name="account"  value="${contract.account}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="unitAddress">单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable  text-right" id="unitAddress"  name="unitAddress"  value="${contract.unitAddress}" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="contractCopies">合同份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="contractCopies"  name="contractCopies" data-parsley-maxlength="30" value="${contract.contractCopies}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="fisrtPartyCopies">甲方份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="fisrtPartyCopies"  name="fisrtPartyCopies" data-parsley-maxlength="20" value="${contract.fisrtPartyCopies}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="secondPartyCopies">乙方份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="secondPartyCopies"  name="secondPartyCopies" data-parsley-maxlength="20" value="${contract.secondPartyCopies}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="20" value="${contract.conAgent}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="signDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="${contract.signDate}" >
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="supplementClause">其他条款</label>
	            <div>
	                <textarea class="form-control field-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">${contract.supplementClause}
	                </textarea>
	            </div>
            </div>
		</form>
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    //表单样式适应
    $("#contractDetailform").styleFit();
    //参数传false时，表单不可编辑
    $("#contractDetailform").toggleEditState(false);
    if($("#contractType").val()=='11'){
		$(".not-resident").addClass("hidden");
		$(".resident").removeClass("hidden");
	}else{
		$(".resident").addClass("hidden");
		$(".not-resident").removeClass("hidden");
	}
    if($("#payType").val()=="1"||$("#payType").val()=="3"||$("#payType").val()=="7"){
    	//首付款录入
    	//$('.firstPayment').show();
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#secondPayment").val("");
    	$("#thirdPayment").val("");
    	$("#contractDetailform").styleFit();
    }else if($("#payType").val()=="2"||$("#payType").val()=="4"||$("#payType").val()=="6"){
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$("#thirdPayment").val("");
    	$("#contractDetailform").styleFit();
    }else {
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').removeClass("hidden");
    	$('.thirdPayment').removeClass("hidden");
    	$("#contractDetailform").styleFit();
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->