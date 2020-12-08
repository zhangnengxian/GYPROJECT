<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
       		
		    <input type="hidden" id="projId" name="projId" value="${subContract.projId}"/>
       		<input type="hidden" id="scId" name="scId" value="${subContract.scId}"/>
			<input type="hidden" id="projLtypeId" name="projLtypeId" value="${subContract.projLtypeId}"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  data-parsley-required="true" value="${subContract.scNo}"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value="${subContract.projNo}"/>
		        </div>
		    </div>
			<div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="${subContract.projName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value="${subContract.projAddr}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" id = "projScaleDes" rows="" cols="" data-parsley-maxlength="200">${subContract.projScaleDes}</textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value="${subContract.corpName}"/>
		        </div>
		    </div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${subContract.projectTypeDes}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${subContract.contributionModeDes}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="departmentName" name="departmentName" value="${subContract.departmentName}"/>        
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="deptName">发包人</label>
		        <div>
		       		<input type="hidden"  id="deptId" name="deptId" data-parsley-maxlength="20"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="50"  value="${subContract.deptName}" />
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6">
		    	<!-- 委托代表改为"委托代理人" -->
		        <label class="control-label" for="projCompDirector">委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projCompDirector" name="projCompDirector" data-parsley-maxlength="20" value="${subContract.projCompDirector}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasComLegalRepresent">甲方现场代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" data-parsley-maxlength="20"  value="${subContract.gasComLegalRepresent}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		    	<!-- 乙方改为承包人 -->
		        <label class="control-label" for="cuName">承包人</label>
		        <div>
		            <input type="hidden" id="cuId" name="cuId" data-parsley-maxlength="100"  value="" />
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName" data-parsley-maxlength="200"  value="${subContract.cuName}" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		    	<!-- 乙方委托代表改为委托代理人 -->
		        <label class="control-label" for="cuDirector">委托代理人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="cuDirector" name="cuDirector" data-parsley-maxlength="20" value="${subContract.cuDirector}" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuPm">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm" data-parsley-maxlength="20" value="${subContract.cuPm}" />
		        </div>
		    </div> 
		    <div class="form-group col-md-6 clearboth">
	            <label class="control-label" for="contractMethod">建筑服务</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="contractMethod"  name="contractMethod"  data-parsley-required="true" >
	 		      	<c:forEach var="enums" items="${contractMethod}">
						<option value="${enums.value}" <c:if test="${enums.value==subContract.contractMethod }"> selected</c:if> >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group col-md-6">
		    	<!-- 现场负责人改为施工员 -->
		        <label class="control-label" for="cuResponsiblePerson">施工员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuResponsiblePerson" name="cuResponsiblePerson" data-parsley-maxlength="20" value="${subContract.cuResponsiblePerson }"/>
		        </div>
		    </div>
			<div class="form-group col-md-12 hidden artery">
				<label class="control-label" for="contractMode">承包方式</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"  id="contractMode" name="contractMode" data-parsley-maxlength="200" value="${subContract.contractMode }"/>
				</div>
			</div>
		    <div class="form-group col-md-12">
		    	<!-- 承包范围改为工程内容 -->
	            <label class="control-label" for="scScope">工程内容</label>
	            <div>
	                <textarea class="form-control field-editable" name ="scScope" id="scScope" rows="3" data-parsley-maxlength="200">${subContract.scScope }</textarea>
	            </div>
            </div>
             <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        	<select class=" form-control input-sm field-not-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
		        		<option></option>
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}" <c:if test="${enums.increment==subContract.increment}">selected="selected"</c:if> >${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		         <!--   <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
		            -->
		            <a href="javascript:;" class="btn btn-sm btn-default">%</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" >开工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="scPlannedStartDate"  name="scPlannedStartDate" value="${subContract.scPlannedStartDate }">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" >竣工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default timestamp" id="scPlannedEndDate"  name="scPlannedEndDate" value="${subContract.scPlannedEndDate }">
		        </div>
		    </div>
			<div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType">
						<c:forEach var="enums" items="${payType}">
						<option value="${enums.ptId}" <c:if test="${enums.ptId==subContract.payType }"> selected</c:if> >${enums.payTypeDes}</option>
	                </c:forEach>
					</select>
				</div>
			</div>
            <div class="form-group col-md-6">
            	<!-- 承包方式改为合同价款方式 -->
		        <label class="control-label" for="scType">合同价款方式</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="scType" name="scType">
						<c:forEach var="enums" items="${payType}">
						<option value="${enums.ptId}" <c:if test="${enums.ptId==subContract.scType }"> selected</c:if> >${enums.scType}</option>
	                </c:forEach>
					</select>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<!-- 承包方式改为合同价款方式 -->
				<label class="control-label" for="progressType">进度款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="progressType"  name="progressType">
						<c:forEach var="enums" items="${payType}">
						<option value="${enums.ptId}" <c:if test="${enums.ptId==subContract.progressType }"> selected</c:if> >${enums.progressType}</option>
	                </c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="scAmount">合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="scAmount" name="scAmount" data-parsley-maxlength="16" value="${subContract.scAmount }" data-parsley-required="true"/>
				</div>
			</div>
		    <div class="form-group col-md-6">
		        <label class="control-label" >签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="scSignDate" name="scSignDate" value="${subContract.scSignDate }" data-parsley-required="true">
		        </div>
		    </div>
            <div class="form-group col-md-12">
	            <label class="control-label" for="">更改内容</label>
	            <div>
	                <textarea class="form-control field-editable" name ="operateContent" id="operateContent" rows="2" data-parsley-maxlength="1000">${operateContent}
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