<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >暂存</a>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
       		<input type="hidden" name="projId" id="projId"/>
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="isPass" id="isPass"/>
       		<input type="hidden" name="isPrint" id="isPrint"/>
       		<input type="hidden" name="corpId" id="corpId"/>
       		<input type="hidden" name="changeFlag" id="changeFlag" value="0"/>
       		<input type="hidden" name="materialIsRegister" id="materialIsRegister"/>
       		<input type="hidden" name="flag" id="flag"/>
       		<input type="hidden"  id="cashFlows"/>
       		<input type="hidden"  id="accRecords"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable conNo"  id="conNo" name="conNo" value="" data-parsley-required="true"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
	            <label class="control-label" for="contractType">合同类型</label>
            	<div>
               	   <select class="form-control input-sm field-not-editable" id="contractType"  name="contractType"  data-parsley-required="true">
	               <c:forEach var="enums" items="${contractType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="surveyer">勘察员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="surveyer" name="surveyer" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" data-parsley-required="true" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contributionModeDes">出资方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="deptName">业务部门</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="4"></textarea>
	            </div>
            </div>
			<div class="form-group col-md-12">
				<label class="control-label" for="costRemark">造价备注</label>
				<div>
					<textarea class="form-control field-not-editable" name="costRemark" id="costRemark" rows="2" cols="" data-parsley-maxlength="500"></textarea>
				</div>
			</div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">燃气用户</label>
		        <!-- <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div> -->
		         <div>
		        	<input type="hidden" id="custId" name="custId"/>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        	 <a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custEntrustRepresent">甲方法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value="" />
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="unitAddress">单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="unitAddress"  name="unitAddress"  value="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="agentCustName">代建单位</label>
		        <!-- <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div> -->
		         <div>
		        	<input type="hidden" id="agentCustId" name="agentCustId"/>
		            <input type="text" class="form-control input-sm field-editable"  id="agentCustName" name="agentCustName" data-parsley-maxlength="200"/>
		        	<!--  <a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="agentCustPhone">代建单位联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="agentCustPhone" name="agentCustPhone" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="agentCustEntrustRepresent">代建单位法定代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="agentCustEntrustRepresent" name="agentCustEntrustRepresent" data-parsley-maxlength="50" value="" />
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="agentUnitAddress">代建单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="agentUnitAddress"  name="agentUnitAddress"  value="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-12 clearboth ">
		        <label class="control-label" for="gasComp">燃气经营企业</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasComp" name="gasComp" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCompPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="gasCompPhone" name="gasCompPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="gasCorpAddr">单位地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="gasCorpAddr"  name="gasCorpAddr"  value="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="conScope">承包范围</label>
	            <div>
	                <textarea class="form-control field-editable" name ="conScope" id="conScope" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6">
	            <label class="control-label" for="contractMethod">承包方式</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="contractMethod"  name="contractMethod"  data-parsley-required="true">
	 		      	<c:forEach var="enums" items="${contractMethod}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			 <div class="form-group col-md-6">
		        <label class="control-label" for="timeLimit">工期</label>
		        <div>
		        	<input type="text" class=" form-control input-sm field-editable" id="timeLimit"  name="timeLimit" data-parsley-maxlength="10" value="" >
		        	<a href="javascript:;" class="btn btn-sm btn-default">天</a>
		        </div>
		    </div>
		    <div class="form-group col-md-12 REMARK hidden">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div>
			 <div class="form-group col-md-6 resident">
		        <label class="control-label" for="houseDate">交房日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="houseDate"  name="houseDate" data-parsley-maxlength="100"  value="">
		        </div>
		    </div>
		    <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="budgetCost">确定总造价</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="budgetCost"  name="budgetCost" data-parsley-maxlength="100" value="" >
		       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="household">安装户数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable text-right" id="household"  name="household" data-parsley-type="number" data-parsley-maxlength="10" value="" data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-6 resident">
		        <label class="control-label" for="hoseAmount">每户金额</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="hoseAmount"  name="hoseAmount" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true"  >
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="contractAmount">合同金额</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="contractAmount"  name="contractAmount" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 col-sm-6  clearboth">
	            <label class="control-label" for="payType">付款方式</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="payType"  name="payType"  data-parsley-required="true">
	                <c:forEach var="enums" items="${payType}">
						<option value="${enums.ptId}" data-mode="${enums.payTypeMode }">${enums.payTypeDes}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div>
			<div class="form-group col-md-6 col-sm-12 col-sm-6">
				<label class="control-label">按比例付款</label>
				<div>
					<label>
						<input type="radio" class="field-editable" name="isRatio" value="1"  checked/> 是
					</label>
					<label>
						<input type="radio" class="field-editable" name="isRatio" value="0" /> 否
					</label>
				</div>
			</div>
		<!-- 	<div class="form-group col-md-6 hidden payPlat">
				<label class="control-label">付款比例</label>
				<div>
					<select class=" form-control input-sm field-editable fixed-number text-right" id="payPlat"  name="payPlat" data-parsley-required="true">
						<option></option>
						<option value="1">50%-50%</option>
						<option value="2">60%-40%</option>
						<option value="3">70%-30%</option>
						<option value="4">80%-20%</option>
					</select>
				</div>
			</div> -->
			<br/>
			 <div class="form-group col-md-6 col-sm-12 col-sm-6 paymentRatio1  clearboth">
		        <label class="control-label" for="paymentRatio1">首付比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio1"  readonly="readonly" name="paymentRatio1"  data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例" />
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
			<div class="form-group col-md-6 firstPayment hidden">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		          
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
		            data-parsley-maxlength="16" value="" data-parsley-required="true" size="10" />
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 paymentRatio2">
		        <label class="control-label" for="paymentRatio2 hidden">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio2" readonly="readonly" name="paymentRatio2" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  />
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 secondPayment hidden">
		        <label class="control-label" for="secondPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right"  id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
		       <div class="form-group col-md-6 paymentRatio3 hidden ">
		        <label class="control-label" for="paymentRatio3">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm fixed-number text-right" id="paymentRatio3" readonly="readonly" name="paymentRatio3" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  >
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
			<div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"   name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="incrementAmount">增值税</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="incrementAmount"  name="incrementAmount"  data-parsley-maxlength="16" value="" data-parsley-type="number">
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        	<select class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}">${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		         <!--   <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16">
		            -->
		            <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="invoiceType" name="invoiceType" data-parsley-maxlength="20" value="" data-parsley-maxlength="20">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="openBank">开户行</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="openBank"  name="openBank"  value="" data-parsley-maxlength="100" data-parsley-required="true">
					<a id="bankPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择开户行"><i class="fa fa-search"></i></a>
				</div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="account">开户帐号</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable" id="account"  name="account"  value="" data-parsley-maxlength="100" >
		        </div>
		    </div>
		   
		    <div class="form-group col-md-6">
		        <label class="control-label" for="conAgent">经办人</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable " id="conAgent"  name="conAgent" data-parsley-maxlength="20" value="${loginInfo.staffName}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="signDate"  name="signDate" data-parsley-maxlength="100" value="" data-parsley-required="true">
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="supplementClause">补充条款</label>
	            <div>
	                <textarea class="form-control field-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div>
		</form>
		<div class="clearboth form-box m-t-5 scaleTableForm hidden">
			<form id="scaleTableForm"  data-parsley-validate="true">
				<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%"">
		        	<thead>
			            <tr>
			                <th width="80px">细类</th>
			                <th width="50px">吨位</th>
			                <th width="50px">座数</th>
			                <th width="50px">台数</th>
			                <th width="50px">户数</th>
			                <th width="80px">预计用量(m³/h)</th>
			                <!-- <th width="30px"></th> -->
			            </tr>
		           </thead>
				</table>
			</form>
		</div>
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
    $("#contractDetailform").toggleEditState().styleFit();
    changeAText();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //查右侧工程详述
     trSData.t && trSData.t.cgetDetail('contractDetailform','contractModify/viewContract','',dataBack); 
    /**点击右侧维护区 推送 按钮时*/
    $(".saveBtn").on("click",function(){
    	$("#flag").val("1");
    	var tableform =$("#scaleTableForm"),scaleTable = $("#scaleTable");
        if($("#contractDetailform").parsley().isValid()){
        	//加遮罩
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	var data=$("#contractDetailform").serializeJson();
        	var mdata = tableform.getDTFormData();
        	var resultData = [];
        	for(var i=0;i<mdata.length;i++){
        		var data1 = mdata[i];
        		if(data1.scaleId !== undefined){
        			data1.projNo = $("#projNo").val();
           			data1.projId = $("#projId").val();	
           			if((data1.tonnage!==null&&data1.tonnage!==""&& data1.tonnage!==undefined) 
           					|| (data1.searNum!==null&&data1.searNum!==""&& data1.searNum!==undefined) 
           					|| (data1.num!==null&&data1.num!==""&& data1.num!==undefined) 
           					|| (data1.houseNum!==null&&data1.houseNum!==""&& data1.houseNum!==undefined) 
           					|| (data1.gasConsumption!==null&&data1.gasConsumption!==""&& data1.gasConsumption!==undefined)
           					/* ||(data1.pipeDiameter!==null&&data1.pipeDiameter!==""&& data1.pipeDiameter!==undefined)
           					||(data1.pipeLength!==null&&data1.pipeLength!==""&& data1.pipeLength!==undefined) */ ){
           				resultData.push(data1);
           			}
        		}
        	}
        	if(resultData.length<1 && $("#contractType").val()=='11'){
       			//取消遮罩
       			$(".infodetails").hideMask();	
	       		alertInfo("请填写用气规模明细记录！");
	       		return false;
	       	}
        	data.scaleDetails = resultData;
        	console.info(data);
        	if(data.payType == "6"){
        		if(new Number($("#firstPayment").val())<(new Number($("#contractAmount").val()))*0.5){
        			//取消遮罩
        			$(".infodetails").hideMask();	
        			alertInfo("首付款不能少于50%");
        			return false;
        		}
        	}
        	if(data.contractMethod!='4'){
        		data.remark='';
        	}
        	$.ajax({
                type: 'POST',
                url: 'contractModify/modifyContract',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                	}else if(data ===  "exist"){
                		content = "合同编号已存在，请修改！";
                		alertInfo(content);
                	}else if(data === "true"){
                 		$("#contractDetailform input,#contractDetailform textarea,#contractDetailform select").val('');
                        $("#contractDetailform").formReset();
                		//隐藏按钮
                		$(".editbtn").addClass("hidden");
                		$("#constructContractTable").cgetData(true,tableCallBack);  //列表重新加载
                		$("#contractDetailform").toggleEditState(false);
                		defaultValue();
                		alertInfo(content);
                	}else{
                		/* //弹出收付流水确认屏   暂完成
             			var url = "#constructContract/accrualsRecordPopPage?arId="+data;
             			var popoptions = {
             				title: '应收流水',
             				content: url,
             				chide: true,
             				nocache: true,
             				accept: accrualsRecordDone
             			};
             			//加载弹屏
             			$("body").cgetPopup(popoptions); */
                		alertInfo('数据保存成功！');
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.warn("合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#contractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#contractDetailform").parsley().validate();
        }
    }); 
    /**点击右侧维护区 保存 按钮时*/
    $(".temporarySaveBtn").on("click",function(){
    	$("#flag").val("0");
    	var tableform =$("#scaleTableForm"),scaleTable = $("#scaleTable");
        if($("#contractDetailform").parsley().isValid()){
        	var data=$("#contractDetailform").serializeJson();
        	var mdata = tableform.getDTFormData();
        	var resultData = [];
        	for(var i=0;i<mdata.length;i++){
        		var data1 = mdata[i];
        		if(data1.scaleId !== undefined){
        			data1.projNo = $("#projNo").val();
           			data1.projId = $("#projId").val();	
           			if((data1.tonnage!==null&&data1.tonnage!==""&& data1.tonnage!==undefined) 
           					|| (data1.searNum!==null&&data1.searNum!==""&& data1.searNum!==undefined) 
           					|| (data1.num!==null&&data1.num!==""&& data1.num!==undefined) 
           					|| (data1.houseNum!==null&&data1.houseNum!==""&& data1.houseNum!==undefined) 
           					|| (data1.gasConsumption!==null&&data1.gasConsumption!==""&& data1.gasConsumption!==undefined)
           					/* ||(data1.pipeDiameter!==null&&data1.pipeDiameter!==""&& data1.pipeDiameter!==undefined)
           					||(data1.pipeLength!==null&&data1.pipeLength!==""&& data1.pipeLength!==undefined)  */){
           				resultData.push(data1);
           			}
        		}
        	}
        	if(resultData.length<1 && $("#contractType").val()=='11'){
       			//取消遮罩
       			$(".infodetails").hideMask();	
	       		alertInfo("请填写用气规模明细记录！");
	       		return false;
	       	}
        	data.scaleDetails = resultData;
        	console.info(data);
        	if(data.payType == "6"){
        		if(new Number($("#firstPayment").val())<(new Number($("#contractAmount").val()))*0.5){
        			alertInfo("首付款不能少于50%");
        			return false;
        		}
        	}
        	if(data.contractMethod!='4'){
        		data.remark='';
        	}

        	//加遮罩
        	$(".infodetails").loadMask("正在保存...", 1, 0.5);
        	$.ajax({
                type: 'POST',
                url: 'constructContract/saveContract',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(data),
                success: function (data) {
                	//取消遮罩
                	$(".infodetails").hideMask();	
                	var content = "数据保存成功！";
                	if(data === "false"){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data ===  "exist"){
                		content = "合同编号已存在，请修改！";
                		alertInfo(content);
                	}else if(data === "true"){
//                		$("#contractDetailform input,#contractDetailform textarea,#contractDetailform select").val('');
                        $("#contractDetailform").formReset();
						//隐藏按钮
                		$(".editbtn").addClass("hidden");
                		$("#constructContractTable").cgetData(true,tableCallBack);  //列表重新加载
                		$("#contractDetailform").toggleEditState(false);
                		defaultValue();
                		alertInfo(content);
                	}else{
                		/* //弹出收付流水确认屏   暂完成
             			var url = "#constructContract/accrualsRecordPopPage?arId="+data;
             			var popoptions = {
             				title: '应收流水',
             				content: url,
             				chide: true,
             				nocache: true,
             				accept: accrualsRecordDone
             			};
             			//加载弹屏
             			$("body").cgetPopup(popoptions); */
                		alertInfo('数据保存成功！');
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {

                	//取消遮罩
                	$(".infodetails").hideMask();	
                    console.warn("合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#contractDetailform").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#contractDetailform").parsley().validate();
        }
    }); 
//     var accrualsRecordDone = function(){
//     	$("#contractDetailform").formReset();
// 		$('#contractDetailform').toggleEditState(false);
// 		//隐藏按钮
// 		$(".editbtn").addClass("hidden");
// 		$("#constructContractTable").cgetData();  //列表重新加载
// 		alertInfo('数据保存成功！');
//     }
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 //清空表单
    	 $("#surveyDetailform input").val('');
    	 //trSData.t.cgetDetail('contractDetailform','constructContract/viewContract','',dataBack); 
    	 $("#contractDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 selectTr["constructContractTable"] = 0;
    	 $('tbody tr:eq(0)').select();
    	//移除验证
    	 $("#contractDetailform").parsley().reset();
    	 $("#conNo").val($("#projNo").val());
    	 //$("#contractAmount").val($("#budgetCost").val());
    	 defaultValue();
    });
   	$("#contractType").change(function(){
   		if($("#contractType").val()=='11'){
   			$(".not-resident").addClass("hidden");
   			$(".resident").removeClass("hidden");
   		}else{
   			$("#contractAmount").val($("#budgetCost").val());
   			$(".resident").addClass("hidden");
   			$(".not-resident").removeClass("hidden");
   		}
   		$("#payType").empty();
    	var selectEl = $("#contractType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
        if ($('[name="isSpecial"]:checked').val() == 1) {
            data='21';
        }
    	$.ajax({
    		type: 'post',
    		url: 'constructContract/queryPayType?id='+data+"&corpId="+data.corpId,
    		dataType: 'json',
    		success: function(data){
    	        $("#payType").append('<option value=""></option>');
    			$.each(data,function(n, v){
    	            $("#payType").append('<option value='+v.ptId+' data-mode="' +v.payTypeMode+ '">' + v.payTypeDes + '</option>');
    	        });
                //如果业务部门是客服中心只有一次付清
                if($("#deptDivide").val()==$("#customerServiceSenter").val()){
                	$("#payType option[value='6']").remove();
                }
    		},
    		error: function(data){
    			console.warn("出资类型选框查询失败");
    		}
    	});
   	});



    //付款方式change监听
	$("#payType").change(function(){
		 $("input[name='isRatio'][value='1']").attr("checked",true);  //根据Value值设置Radio为选中状态
        if($(this).val()=="1"||$(this).val()=="3"||$(this).val()=="7"){
        	//首付款录入
        	//$('.firstPayment').show();
            $(".payPlat").addClass("hidden");
        	$('.firstPayment').removeClass("hidden");
        	$('.secondPayment').addClass("hidden");
        	$('.thirdPayment').addClass("hidden");
        	$(".paymentRatio1").removeClass("hidden");
        	$(".paymentRatio2").addClass("hidden");
        	$(".paymentRatio3").addClass("hidden");
        	$("#paymentRatio2").val("");
        	$("#paymentRatio3").val("");
        	$("#secondPayment").val("");
        	$("#thirdPayment").val("");
        	$("#contractDetailform").styleFit();
        }else if($(this).val()=="2"||$(this).val()=="4"){
        	$(".paymentRatio2").removeClass("hidden");
           	$(".paymentRatio3").addClass("hidden");
           	$("#paymentRatio3").val("");
            if ($('[name="isSpecial"]:checked').val() == "1"){
                $(".payPlat").removeClass("hidden");
                $('.firstPayment').removeClass("hidden");
                $('.secondPayment').removeClass("hidden");
                $('.thirdPayment').addClass("hidden");
                $("#thirdPayment").val("");
                $("#contractDetailform").styleFit();
             
			}else{
                $(".payPlat").addClass("hidden");
				$('.firstPayment').removeClass("hidden");
				$('.secondPayment').removeClass("hidden");
				$('.thirdPayment').addClass("hidden");
				$("#thirdPayment").val("");
				$("#contractDetailform").styleFit();
			}

        }else if($(this).val()=="6"){
        	$(".paymentRatio2").removeClass("hidden");
           	$(".paymentRatio3").addClass("hidden");
            $(".payPlat").addClass("hidden");
        	$('.firstPayment').removeClass("hidden");
        	$('.secondPayment').removeClass("hidden");
        	$(".paymentRatio2").removeClass("hidden");
        	$('.thirdPayment').addClass("hidden");
        	$("#thirdPayment").val("");
        	$("#contractDetailform").styleFit();
        }else {
           	$(".paymentRatio3").removeClass("hidden");
        	$(".paymentRatio2").removeClass("hidden");
            $(".payPlat").addClass("hidden");
            if ($('[name="isSpecial"]:checked').val() == "1"){
                $('.firstPayment').removeClass("hidden");
                $('.secondPayment').removeClass("hidden");
                $('.thirdPayment').removeClass("hidden");
                $("#contractDetailform").styleFit();
            }else{
				$('.firstPayment').removeClass("hidden");
				$('.secondPayment').removeClass("hidden");
				$('.thirdPayment').removeClass("hidden");
				$("#contractDetailform").styleFit();
			}
        }
         $("#paymentRatio1").val('');
         var totaAmount = $("#contractAmount").val();  //合同总金额
        //如果是一次性付清首付比列为100%
        if($(this).val()=='3'|| $(this).val()=='7'  || $(this).val()=='1'){
        	$("#paymentRatio1").val('100');   //首付比例直接为100%
        	$("#firstPayment").val(totaAmount);  //金额为全款，即是合同总金额
        	$("#paymentRatio3").val('');
        	$("#paymentRatio2").val('');
        	$("#secondPayment").val('');
        	$("#thirdPayment").val("");
        	$("#paymentRatio1").attr("readonly","readonly");
        }else if($(this).val()=='4' || $(this).val()=='6' || $(this).val()=='2'){   //若是两次付清
        	$("#paymentRatio1").val('');
        	$("#firstPayment").val('');
        	$("#paymentRatio3").val('');
        	$("#thirdPayment").val('');
        	$("#paymentRatio2").val('');
        	$("#secondPayment").val('');
        	$("#firstPayment").attr("readonly",true);
        	$("#secondPayment").attr("readonly",true);
        	$("#paymentRatio1").attr("readonly",false);
        	$("#paymentRatio2").attr("readonly",true);
        }else if($(this).val()=='5'){   //若是三次付清
        	$("#paymentRatio1").val('');
        	$("#paymentRatio2").val('');
        	$("#firstPayment").val('');
        	$("#secondPayment").val('');
        	$("#paymentRatio3").val('');
        	$("#thirdPayment").val("");
        	$("#paymentRatio1").attr("readonly",false);
        	$("#paymentRatio2").attr("readonly",false);
        	$("#paymentRatio3").attr("readonly",true);         
        }
	});
  //失去焦点事件，计算付款比例和付款的多少
    $("#paymentRatio1,#paymentRatio2,#paymentRatio3").on("change",function(){
    	var paymentRate = $(this).val();
    	//var reg=/^[0-9]+\.[0]+$/; //正则表达式,验证是否是整数
    	var reg=/^[0-9]+\.?[0-9]{0,2}$/; //正则表达式,最多带两位小数的数字
 	   var payNumber = $("#payType").val();    //分几期付款，1、7、3代表全款，6、2、4代表分两期，5代表分三期
 	  if((new Number(paymentRate).toFixed(0)>100 || !reg.test(paymentRate))  && paymentRate!=''){  //判断是是否在1到100之间且为整数
 			$("body").cgetPopup({
 				title: '提示',
 				content: '请输入1到100之间的整数!',
 				ahide:true,
 				atext:'确定'
 			});
 	   return false;  //终止程序
 	   }  
 	  CalculationFunction();  //调用函数计算付款比例和所付金额
    });
    //此函数用于计算付款比例和所付金额
   var  CalculationFunction=function(){
	   var totaAmount = new Number($("#contractAmount").val());  //合同总金额
	   var payNumber = $("#payType").val();    //分几期付款，3代表全款，4代表分两期，5代表分三期
	   /**根据分期比例和合同总金额自动计算每期付款多少*/
	   if(payNumber=='4' || payNumber=='6' || payNumber=='2'){  //分两期付款
			var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
			if(paymentRatio1>0){
				$("#paymentRatio2").removeClass("hidden");
				$("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱
				$("#paymentRatio2").val((100.00-paymentRatio1)+'.00');    //计算第二次付款的比列
				$("#secondPayment").val((((100.00-paymentRatio1)/100.00)*totaAmount).toFixed(2));  //计算第二期付款多少钱
			}
	/* 		if(payNumber=='6' && paymentRatio1<50){ //改管工程首付比列不能少于50%
				  $("body").cgetPopup({
						title: '提示',
						content: '首付比列不能少于50%!',
						ahide:true,
						atext:'确定'
					});
				  $("#paymentRatio1").select();  //选中首付款比例输入框
				  return false;  //终止程序
			} */
	   }else if(payNumber=='5'){ //分三期付清
		   var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
		   var paymentRatio2=new Number($("#paymentRatio2").val());  //得到二期付款比例
		   if(paymentRatio1>0){
			   $("#firstPayment").val(totaAmount*(paymentRatio1/100.00));  //根据首付比列计算首付多少钱 
		   }
		   if(paymentRatio2<1 && $("#paymentRatio2").attr("readonly")!='readonly'){
			   $("body").cgetPopup({
					title: '提示',
					content: '请输入二期阶段款比例!',
					ahide:true,
					atext:'确定'
				});
			   return false; //终止程序
		   }
		   if(paymentRatio1>0 && paymentRatio2>0){
			   if((paymentRatio1+paymentRatio2)<=100.00){  //判断前两期首付比例是否小于或者等于100
				   $("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱 
				   $("#secondPayment").val((totaAmount*(paymentRatio2/100.00)).toFixed(2));  //计算第二期付款多少钱
				   $("#paymentRatio3").val((100.00-paymentRatio1-paymentRatio2)+'.00');    //计算第三次付款的比列
				   $("#thirdPayment").val(((100.00-paymentRatio1-paymentRatio2)/100.00*totaAmount).toFixed(2));  //计算第三期付多少钱
			   }else{
				   $("body").cgetPopup({
						title: '提示',
						content: '总的付款比例不得超过100%!',
						ahide:true,
						atext:'确定'
					});
				   return false; //终止程序
			   }
			  
		   }
		   
	   }
   }
    var tableCallBack = function(){
    	var len = $('#constructContractTable').DataTable().ajax.json().data ? $('#constructContractTable').DataTable().ajax.json().data.length : $('#constructContractTable').DataTable().ajax.json().length;
    	if(len<1){
		   		 //清空右侧记录
		   		 $("#contractDetailform")[0].reset();
		   		 $(".editbtn").addClass("hidden");
		   		 $("#contractDetailform").toggleEditState(false);
   				}else{
   				// $(".editbtn").removeClass("hidden");
   		}
    }
    $("#contractAmount").on("change",function(){
    	if($("#payType").val()=="1"||$("#payType").val()=="3"||$("#payType").val()=="7"){
    		$("#firstPayment").val($("#contractAmount").val());
        }else if($("#payType").val()=="2"||$("#payType").val()=="4"){
        	CalculationFunction();  //调用函数计算付款比例和所付金额
        }else if($("#payType").val()=="6"){
        	CalculationFunction();  //调用函数计算付款比例和所付金额
        }else{
        	CalculationFunction();  //调用函数计算付款比例和所付金额
        }
    		});
    
    //比例付款方式监听
    $('[name="isRatio"]').on("change",function() {
        if ($('[name="isRatio"]:checked').val() == 0) {   //不按比例方式付款"paymentRatio1"
           $("#firstPayment").removeClass("field-not-editable");
           $("#secondPayment").removeClass("field-not-editable");
           $("#thirdPayment").removeClass("field-not-editable");
           $("#paymentRatio1").addClass("field-not-editable");
           $("#paymentRatio2").addClass("field-not-editable");
           $("#paymentRatio3").addClass("field-not-editable");
           $("#firstPayment").attr("readonly",false);
           $("#secondPayment").attr("readonly",false);
           $("#thirdPayment").attr("readonly",false);
           $("#paymentRatio3").attr("readonly",true);
           $("#paymentRatio1").attr("readonly",true);
           $("#paymentRatio2").attr("readonly",true);
        }else if ($('[name="isRatio"]:checked').val() == 1){  //按比例方式付款
           $("#firstPayment").addClass("field-editable");
           $("#secondPayment").addClass("field-editable");
           $("#thirdPayment").addClass("field-editable");
           $("#paymentRatio1").removeClass("field-editable");
           $("#paymentRatio2").removeClass("field-editable");
           $("#paymentRatio3").removeClass("field-editable");
           $("#paymentRatio3").attr("readonly",false);
           $("#paymentRatio1").attr("readonly",false);
           $("#paymentRatio2").attr("readonly",false);
           $("#firstPayment").attr("readonly",true);
           $("#secondPayment").attr("readonly",true);
           $("#thirdPayment").attr("readonly",true);
        }
    });

    //根据付款的多少自动计算金额和比例
    $("#firstPayment,#secondPayment,#thirdPayment ").on("change",function(){
  	if($("#payType").val()=="1"||$("#payType").val()=="3"||$("#payType").val()=="7"){   //一次付清
      	$("#firstPayment").val((new Number($("#contractAmount").val())));
      	$("#paymentRatio1").val((((new Number($("#firstPayment").val()))/(new Number($("#contractAmount").val())))*100).toFixed(2));
      }else if($("#payType").val()=="2"||$("#payType").val()=="4"||$("#payType").val()=="6"){ //二次付清
          $("#secondPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())));  //根据首付款得到阶段款二
      	$("#paymentRatio1").val((((new Number($("#firstPayment").val()))/(new Number($("#contractAmount").val())))*100).toFixed(2));
      	$("#paymentRatio2").val((100-new Number($("#paymentRatio1").val())));
      }else if($("#payType").val()=="5"){
      	  //根据首付款和阶段款2得到阶段款三
      	$("#paymentRatio1").val((((new Number($("#firstPayment").val()))/(new Number($("#contractAmount").val())))*100).toFixed(2));
      	$("#paymentRatio2").val((((new Number($("#secondPayment").val()))/(new Number($("#contractAmount").val())))*100).toFixed(2));
          $("#thirdPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())-new Number($("#secondPayment").val())));
          $("#paymentRatio3").val((100-new Number($("#paymentRatio1").val())-new Number($("#paymentRatio2").val())).toFixed(2));
       
      }
	}); 
    $("#household,#hoseAmount").on("change",function(){
    	if($("#contractAmount").val()!=''&&$("#changeFlag").val()=='0'){//如果不编辑页面且合同金额已存过则改change事件不起作用
    		return false;
    	}else{
    		$("#contractAmount").val((new Number($("#household").val())*new Number($("#hoseAmount").val())).toFixed(2));
    		$("#contractAmount").change();
    	}
	});

    $("#increment,#contractAmount").on("change",function(){
        //（公建）安装合同报表中的其中价为（工程总价/(1+税率)），税为（工程总价-其中价）
		$("#incrementAmount").val((new Number($("#contractAmount").val())-(new Number($("#contractAmount").val())/(1+(new Number($("#increment").val())/100)))).toFixed(2));
    });

    $("#contractMethod").on("change",function(){
    	if($("#contractMethod").val()=='4'){
    		$(".REMARK").removeClass("hidden");
    	}else{
    		$(".REMARK").addClass("hidden");
    	}
    	$("#contractDetailform").styleFit();
	});

    //选择开户行
    $("#bankPop").off().on("click",function(){
        $("body").cgetPopup({
            title: '开户行选择',
            content: '#popup/bankPop',
            size: 'large',
            callback:function(){
                bank.init();
            },
            accept: function(){
                if($("#bankTable tr.selected").length < 1){
                    $("body").cgetPopup({title:'提示',content:"请选择开户行！",newpop:'second',accept:innerClose});
                    return false;
                }else{
                    var data = trSData.bankTable.json;
                    //开户行
                    $("#openBank").val(data.bankAdress);
                    //开户账号
                    $("#account").val(data.bankNo);
                }
            }
        });
    });
  //选择申报单位
    $("#custPop").off().on("click",function(){
    	$("body").cgetPopup({
    		title: '申报单位选择',
    		content: '#popup/custListPop',
    		size: 'large',
    		callback:function(){
    			customer.init();
    		},
    	    accept: function(){
    	    	if($("#customerTable tr.selected").length < 1){
    	    		$("body").cgetPopup({title:'提示',content:"请选择申报单位！",newpop:'second',accept:innerClose});
    	    		return false;
    	    	}else{
    		    	var data = trSData.customerTable.json;
    		    	$("#custName").val(data.custName);
    		    	$("#custId").val(data.custId);
    		    	console.info(data.custId);
    		    	//联系人、联系电话、单位地址
    		    	$("#custPhone").val(data.custPhone);
    		    	$("#custEntrustRepresent").val(data.custContcat);
    		    	$("#unitAddress").val(data.unitAddress);
    	    	}
    	    }
      });
  	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->