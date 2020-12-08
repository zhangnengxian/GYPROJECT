<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<input type="hidden" id="resident" value="${resident}"/>
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
    		<input type="hidden" name="projId" id="projId"/>
       		<input type="hidden" name="scId" id="scId"/>
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="scType" id="scType"/>
       		<!-- <input type="hidden" name="flag" id="flag"/> -->
       		<input type="hidden" name="budgetId" id="budgetId"/>
       		<input type="hidden" name="projNo" id="projNo"/>
       		<input type="hidden" name="isPrint" id="isPrint"/>
       		<input type="hidden" name="projType" id="projType" value="${projType}"/>
       		 <div class="form-group  col-md-6 ">
		        <label class="control-label" for="conNo">合同编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="conNo" name="conNo" value="${supplementalContract.conNo}"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="scNo">协议编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo" value="${supplementalContract.scNo}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value="${supplementalContract.projName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value="${supplementalContract.projAddr}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${supplementalContract.projectTypeDes}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contributionModeDes">出资方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${supplementalContract.contributionModeDes}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="deptName">业务部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value="${supplementalContract.deptName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" >${supplementalContract.projScaleDes}</textarea>
	            </div>
            </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">燃气用户</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value="${supplementalContract.custName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value="${supplementalContract.custPhone}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 clearboth ">
		        <label class="control-label" for="gasComp">燃气经营企业</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value="${supplementalContract.gasComp}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="20" value="${supplementalContract.gasCompPhone}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth resident">
		        <label class="control-label" for="houseNum">户数</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="houseNum" name="houseNum" data-parsley-maxlength="6" value="${supplementalContract.houseNum}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 resident">
		        <label class="control-label" for="houseAmount">每户金额</label>
		        <div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="houseAmount"  name="houseAmount" data-parsley-maxlength="13" value="2230"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="scAmount">协议价款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="scAmount"  name="scAmount" data-parsley-maxlength="13" value="${supplementalContract.scAmount}">
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="houseAddr">地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="houseAddr" name="houseAddr" data-parsley-maxlength="200" value="${supplementalContract.houseAddr}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="priceDocument">价格文件</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="priceDocument" name="priceDocument" data-parsley-maxlength="50" value="${supplementalContract.priceDocument}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="invoiceType" name="invoiceType" data-parsley-maxlength="30" value="${supplementalContract.invoiceType}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="increment" name="increment" data-parsley-maxlength="30" value="${supplementalContract.increment}"/>
		       		<a href="javascript:;" class="btn btn-sm btn-default">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="100" value="${supplementalContract.conAgent}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="signDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="${supplementalContract.signDate}" data-parsley-required="true">
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
    //if($("#resident").val()==$("#projType").val()){
    if("11"=="${projType}"){
		$(".resident").removeClass("hidden");
		$(".not-resident").addClass("hidden");
	}else{
		$(".resident").addClass("hidden");
		$(".not-resident").removeClass("hidden");
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->