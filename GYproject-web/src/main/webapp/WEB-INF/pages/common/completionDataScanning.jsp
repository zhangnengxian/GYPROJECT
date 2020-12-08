<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="/assets/css/animate.min.css" rel="stylesheet" />
<link href="/assets/css/style.min.css" rel="stylesheet" />
<link href="/assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="/assets/plugins/ionicons/css/ionicons.min.css" rel="stylesheet" />
<link href="/css/ecloud.css" rel="stylesheet" type="text/css"/>
<!-- ================== BEGIN BASE JS ================== -->
<script src="/assets/plugins/pace/pace.min.js"></script>
<!-- ================== END BASE JS ================== -->
<style>
body{
	background: #fff;
	margin: 10px;
	height: auto;
}
.pace-progress, .pace:before {
    top: 0;
}
.form-group:not(:first-child){
	margin-bottom: 0;
	padding: 5px 0;
}
.form-group label{
	margin-bottom: 0;
    padding-top: 6px;
}

.form-style-fit .form-group > label + div {
    padding: 5px 10px;
    border: #f2f2f2 1px solid;
    border-radius: 4px;
}
</style>
<div class="infodetails">
	<div class="clearboth form-box">
    	<form class="form-horizontal" id="completionDataScanningForm" >
   			
   			<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm" readonly id="projNo" name="projNo"  value="${pro.projNo}"/>
		        </div>
		    </div>
   			<div class="form-group  col-sm-12">
				<label class="control-label" for="projName">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm" readonly id="projName" name="projName"  value="${pro.projName}"/>
				</div>
			</div>
			<div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm" readonly id="projAddr" name="projAddr" value="${pro.projAddr}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control" name ="projScaleDes" readonly id="projScaleDes" rows="3" >${pro.projScaleDes}</textarea>
	            </div>
            </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="duName">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm" readonly id="custName" name="custName" value="${pro.custName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custContact">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm" readonly id="custContact" name="custContact" value="${pro.custContact}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm" readonly id="custPhone" name="custPhone" value="${pro.custPhone}"/>
		       
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyer">勘察人</label>
		        <div>
		           <input type="text" class=" form-control input-sm" readonly id="surveyer"  name="surveyer" value="${pro.surveyer}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conNo">合同编号</label>
		        <div>
		           <input type="text" class=" form-control input-sm" readonly id="conNo"  name="conNo" value="${contract.conNo}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="contractAmount">合同金额</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="contractAmount" readonly name="contractAmount" value="${contract.contractAmount}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="managementOffice">施工管理处</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="managementOffice" readonly name="managementOffice" value="${plan.managementOffice}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="builder">甲方代表</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="builder" readonly name="builder" value="${plan.builder}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuName">分包单位</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="cuName" readonly name="cuName" value="${plan.cuName}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="cuLegalRepresent">项目经理</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="cuLegalRepresent" readonly name="cuLegalRepresent" value="${plan.cuLegalRepresent}">
		        </div>
		    </div>
		    <div class="form-group  col-sm-12">
				<label class="control-label" for="suName">监理公司</label>
				<div>
					<input type="text" class="form-control field-editable input-sm" readonly id="suName" name="suName" value="${plan.suName}" />
				</div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="slPart">放线部位</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="slPart" readonly name="slPart" value="${checkList.slPart}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="process">施工工序</label>
		        <div>
		           <input type="text" class=" form-control input-sm" id="process" readonly name="process" value="${checkList.process}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
				<label class="control-label signature-tool" for="constructionQae">质保师</label>
				<div>
					<input type="hidden" class="sign-data-input" id="constructionQae" name="constructionQae" value="${checkList.constructionQae}">
					<input type="hidden" id="constructionQae_postType" name="constructionQae_postType" >
					<img class="" alt="" src="" style="height: 30px">
				</div>
	    	</div>
	    	<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="inspectionDate">质保日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default  "  id="inspectionDate" name="inspectionDate"  value="${checkList.inspectionDate}"/>
				</div>
			</div>
	    	<div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="asPerson">测量员</label>
				<div>
					<input type="hidden" class="sign-data-input" id="asPerson" name="asPerson" value="${checkList.asPerson}">
					<input type="hidden" id="asPerson_postType" name="asPerson_postType" >
					<img class="asPerson" alt="" src=""" style="height: 30px">
				</div>
	    	</div>
	    	<div class="form-group col-md-6">
				<label class="control-label" for="liningDate">测量日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default  "  id="liningDate" name="liningDate"  value="${checkList.liningDate}"/>
				</div>
			</div>	
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label signature-tool" for="suJgj">监理人</label>
				<div>
					<input type="hidden" class="sign-data-input" id="suJgj" name="suJgj" value="${checkList.suJgj}">
					<input type="hidden" id="suJgj_postType" name="suJgj_postType"  >
					<img class="suJgj" alt="" src="" style="height: 30px">
				</div>
	    	</div>
		    <div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="checkDate">检查日期</label>
				<div>
					<input type="text" class="form-control  input-sm datepicker-default  " value="" id="checkDate" name="checkDate"  value="${checkList.checkDate}"/>
				</div>
			</div>
		    
		    		
    	</form>
    </div>	
</div>

        
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script include-on-mobile="false" src="/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script include-on-mobile="false" src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script include-on-mobile="false" src="/assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js"></script>
<script include-on-mobile="false" src="/assets/plugins/jSignature/jSignature.min.js" async></script>
<script include-on-mobile="true" src="/assets/js/apps.js"></script>
<script include-on-mobile="true" src="/js/ecloud.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
<script>
App.restartGlobalFunction();
App.setPageTitle('竣工资料 - 工程项目管理系统 ');
$("#completionDataScanningForm").styleFit();
$(".sign-data-input").trigger("change");

var inputs = $(':input:visible');
for(var i = 0; i < inputs.length; i++){
	var input = inputs.eq(i);
	if(input.val() === "" || input.val() === undefined) {
		input.closest('.form-group').hide();
	}else{
		input.after(input.val()).hide();
	}
}

</script>

