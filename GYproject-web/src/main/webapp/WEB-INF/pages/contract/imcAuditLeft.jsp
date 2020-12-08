<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />

<div class="infodetails">
	<%--<div class="toolBtn f-r p-b-10  editbtn">--%>
		<%--<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >暂存</a>--%>
    	<%--<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton saveBtn" >保存</a>--%>
        <%--<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>--%>
	<%--</div>--%>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="contractDetailform" data-parsley-validate="true" action="">
			<input type="hidden" name="projId" id="projId" value="${contract.projId}"/>
			<input type="hidden" name="imcId" id="imcId" value="${contract.imcId}"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="imcNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="imcNo" name="imcNo"  data-parsley-required="true" value="${contract.imcNo}" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		    	<label class="control-label" for="conName">合同名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id=""conName"" name=""conName""  data-parsley-required="true" value="${contract.conName}" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="30" value="${contract.imcNo}"/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="${contract.projName}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value="${contract.projAddr}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" id = "projScaleDes" rows="" cols="" data-parsley-maxlength="200">${contract.projScaleDes}</textarea>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="installNums">安装户数</label>
		        <div>
		        	<input type="number" class="form-control input-sm field-editable text-right" min="0" name="installNums" id = "installNums" value="${contract.installNums}" cols="" data-parsley-maxlength="20" data-parsley-required="true"/>
		        	<a href="javascript:;" class="btn btn-sm btn-default">户</a>
		        </div>
		    </div>
		    
		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasCustName">用气单位</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="gasCustName" name="gasCustName" data-parsley-maxlength="50"  value="${contract.gasCustName}" data-parsley-required="true"/>
		        </div>
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasConNo">用气合同编号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="gasConNo" name="gasConNo" data-parsley-maxlength="50"  value="${contract.gasConNo}" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="corpName">甲方</label>
		        <div>
		       		<input type="hidden"  id="corpId" name="corpId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" data-parsley-maxlength="50"  value="${contract.corpName}" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="grantRepresent">授权代表人</label>
		        <div>
		       		<input type="hidden"  id="grantRepresentId" name="grantRepresentId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-editable"  id="grantRepresent" name="grantRepresent" data-parsley-maxlength="50"  value="${contract.grantRepresent}" />
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyConAgent">经办人</label>
		        <div>
		       		<input type="hidden"  id="fPartyConAgentId" name="fPartyConAgentId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="fPartyConAgent" name="fPartyConAgent" data-parsley-maxlength="50"  value="${contract.fPartyConAgent}" />
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyBankName">开户行</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyBankName" name="fPartyBankName" data-parsley-maxlength="50"  value="${contract.fPartyBankName}">
		        	<a id="bankPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择开户行"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyBankAccount">开户行帐号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyBankAccount" name="fPartyBankAccount" data-parsley-maxlength="50"  value="${contract.fPartyBankAccount}"/>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyTelNumber">联系电话</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyTelNumber" name="fPartyTelNumber" data-parsley-maxlength="20"  value="${contract.fPartyTelNumber}" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyAddr">地址</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyAddr" name="fPartyAddr" data-parsley-maxlength="50"  value="${contract.fPartyAddr}" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-12 clearboth">
		        <label class="control-label" for="sPartyName">乙方</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyName" name="sPartyName" data-parsley-maxlength="50"  value="${contract.sPartyName}" data-parsley-required="true"/>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6 ">
		        <label class="control-label" for="sPartyLegalRepresent">法定代表人</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyLegalRepresent" name="sPartyLegalRepresent" data-parsley-maxlength="50"  value="${contract.sPartyLegalRepresent}" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyAgent">经办人</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyAgent" name="sPartyAgent" data-parsley-maxlength="50"  value="${contract.sPartyAgent}" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyBankName">开户行</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyBankName" name="sPartyBankName" data-parsley-maxlength="50"  value="${contract.sPartyBankName}"/>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyBankAccount">开户行帐号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyBankAccount" name="sPartyBankAccount" data-parsley-maxlength="50"  value="${contract.sPartyBankAccount}" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyTelNumber">联系电话</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyTelNumber" name="sPartyTelNumber" data-parsley-maxlength="20"  value="${contract.sPartyTelNumber}" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyAddr">地址</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyAddr" name="sPartyAddr" data-parsley-maxlength="50"  value="${contract.sPartyAddr}" />
		       		
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="unitCost">单价</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number text-right" id="unitCost" name="unitCost" data-parsley-maxlength="16" value="${contract.unitCost}" data-parsley-type="number" data-parsley-required="true" />
		        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<label class="control-label" for="totalCost">合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="totalCost" name="totalCost" data-parsley-maxlength="16" value="${contract.totalCost}" data-parsley-type="number" data-parsley-required="true"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType" data-parsley-required="true">
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
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
		            data-parsley-maxlength="16" value="${contract.firstPayment}" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 secondPayment hidden">
		        <label class="control-label" for="secondPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="${contract.secondPayment}" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
			<div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"  name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="${contract.thirdPayment}" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="incrementAmount">增值税</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="incrementAmount"  name="incrementAmount"  data-parsley-maxlength="16" value="${contract.incrementAmount}" data-parsley-type="number">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" value="${contract.increment}">
		           <a href="javascript:;" class="btn btn-sm btn-default">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="invoiceType" name="invoiceType" placeholder="" data-parsley-maxlength="20" value="${contract.invoiceType}" data-parsley-maxlength="20">
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="">楼盘名称</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="houseName" name="houseName" placeholder="" data-parsley-maxlength="200" value="${contract.houseName}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">楼盘地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="houseAddr" name="houseAddr" placeholder="" value="${contract.houseAddr}" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">预计完工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="predictCompleteDate" name="predictCompleteDate" placeholder="" value="${contract.predictCompleteDate}">
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="imcSignDate">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="imcSignDate" name="imcSignDate" value="${contract.imcSignDate}" data-parsley-required="true">
		        </div>
		    </div>
			<%--<div class="form-group col-md-12">--%>
				<%--<label class="control-label" for="supplementClause">补充条款</label>--%>
				<%--<div>--%>
	                <%--<textarea class="form-control field-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">${contract.supplementClause}--%>
					<%--</textarea>--%>
				<%--</div>--%>
			<%--</div>--%>
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
    $('.infodetails').hideMask();
    //表单样式适应
    $('#contractDetailform').toggleEditState(false).styleFit();

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