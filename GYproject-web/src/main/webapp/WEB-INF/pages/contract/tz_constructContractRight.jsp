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
		<input type="hidden" id="unitCost" value="${unitCost}"/>
		<input type="hidden" id="customerServiceSenter" value="${customerServiceSenter}"/><!-- 客服中心 -->
		<input type="hidden" id="modificationGroup" value="${modificationGroup}"/><!-- 改管组 -->
    	<form class="form-horizontal" id="contractDetailform"  data-parsley-validate="true" action="">
			<input type="hidden" name="differenceId" id="differenceId" value="${differenceId}"/>
			<input type="hidden" name="isPass" id="isPass"/>
			<input type="hidden" name="isPrint" id="isPrint"/>
       		<input type="hidden" name="projId" id="projId"/>
       		<input type="hidden" name="projectType" id="projectType"/>
       		<input type="hidden" name="conId" id="conId"/>
       		<input type="hidden" name="corpId" id="corpId"/>
       		<input type="hidden" name="deptDivide" id="deptDivide"/>
       		<input type="hidden" name="changeFlag" id="changeFlag" value="0"/>
       		<input type="hidden" name="materialIsRegister" id="materialIsRegister" value="0"/>
       		<input type="hidden" name="flag" id="flag"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable conNo"  id="conNo" name="conNo" value="" />
		        </div>
		    </div>
		     <div class="form-group col-md-6">
	            <label class="control-label" for="contractType">合同类型</label>
            	<div>
               	   <select class="form-control input-sm field-not-editable" id="contractType"  name="contractType"   data-parsley-required="true">
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
		        <label class="control-label" for="surveyer">踏勘员</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="surveyer" name="surveyer" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
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
			<div class="form-group col-md-12 not-resident">
				<label class="control-label" for="costRemark">造价备注</label>
				<div>
					<textarea class="form-control field-not-editable" name="costRemark" id="costRemark" rows="2" cols="" data-parsley-maxlength="500"></textarea>
				</div>
			</div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="custName">用户单位</label>
		        <!-- <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div> -->
		         <div>
		        	<input type="hidden" id="custId" name="custId"/>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        	 <a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custEntrustRepresent">经办人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custEntrustRepresent" name="custEntrustRepresent" data-parsley-maxlength="20" value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="custPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-6">
		        <label class="control-label" for="unitAddress">地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="unitAddress"  name="unitAddress"  value="" data-parsley-maxlength="200">
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
	            <label class="control-label" for="conScope">工程内容补充</label>
	            <div>
	                <textarea class="form-control field-editable" name ="conScope" id="conScope" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            <div class="form-group col-md-12 not-resident">
	            <label class="control-label" for="contractContent">用气设备</label>
	            <div>
		            <input type="text" class="form-control input-sm field-editable"  id="contractContent" name="contractContent" data-parsley-maxlength="200" value=""/>
		        </div>
            </div>
            <div class="form-group col-md-12 not-resident">
	            <label class="control-label" for="responsibility">职责与义务</label>
	            <div>
	                <textarea class="form-control field-editable" name ="responsibility" id="responsibility" rows="2" data-parsley-maxlength="500"></textarea>
	            </div>
            </div>
            <%-- <div class="form-group col-md-6">
	            <label class="control-label" for="contractMethod">承包方式</label>
            	<div>
               	   <select class="form-control input-sm field-editable" id="contractMethod"  name="contractMethod"  data-parsley-required="true">
	 		      	<c:forEach var="enums" items="${contractMethod}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
	            </div>
			</div> --%>
			 <div class="form-group col-md-6">
		        <label class="control-label" for="timeLimit">工期</label>
		        <div>
		        	<input type="text" class=" form-control input-sm field-editable" id="timeLimit"  name="timeLimit" data-parsley-maxlength="10" value="" >
		        	<a href="javascript:;" class="btn btn-sm btn-default">天</a>
		        </div>
		    </div>
		    <!-- <div class="form-group col-md-12 REMARK hidden">
	            <label class="control-label" for="remark">备注</label>
	            <div>
	                <textarea class="form-control field-editable" name ="remark" id="remark" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div> -->
            <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="budgetCost">确定总造价</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="budgetCost"  name="budgetCost" data-parsley-maxlength="100" value="" >
		       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		     <div class="form-group col-md-6 not-resident govAuditCost hidden">
		        <label class="control-label" for="govAuditCost">政府审定价</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="govAuditCost"  name="govAuditCost" data-parsley-maxlength="100" value="" >
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
		     <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="budgetRule">预算制度</label>
		        <div>
		           	<select class="form-control input-sm field-editable" id="budgetRule"  name="budgetRule"  data-parsley-required="true">
	 		      		<option></option>
	 		      		<c:forEach var ="enums" items="${budgetRuleEnum }">
	 		      		<option value="${enums.value}" >${enums.message}</option>
	 		      		</c:forEach>
	              </select>
				</div>
		    </div>
		     <div class="form-group col-md-6 not-resident">
		        <label class="control-label" for="gasLoss">气损费</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="gasLoss"  name="gasLoss" data-parsley-type="number" data-parsley-maxlength="16" value="">
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
			<br/>
			 <div class="form-group col-md-6 col-sm-12 col-sm-6 paymentRatio1  clearboth">
		        <label class="control-label" for="paymentRatio1">首付比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio1"  readonly="readonly" name="paymentRatio1" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例" onblur="loseBlur(this.value)"/>
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
		    <div class="form-group col-md-6 paymentRatio2 hidden">
		        <label class="control-label" for="paymentRatio2 ">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm  fixed-number text-right" id="paymentRatio2" readonly="readonly" name="paymentRatio2" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  onblur="loseBlur(this.value)"/>
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
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  onblur="loseBlur(this.value)">
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
			<div class="form-group col-md-6 thirdPayment hidden">
		        <label class="control-label" for="thirdPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="thirdPayment"  name="thirdPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
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
		           <select class=" form-control input-sm field-editable  text-right" id="invoiceType"  name="invoiceType" data-parsley-maxlength="16" data-parsley-required="true">
		        		<option value=""></option>
		        		<option value="1">增值税专用发票</option>
		        		<option value="2">增值税普通发票</option>
		        	</select>
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
		        <label class="control-label" for="contractCopies">合同份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="contractCopies"  name="contractCopies" data-parsley-maxlength="30" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="fisrtPartyCopies">甲方份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="fisrtPartyCopies"  name="fisrtPartyCopies" data-parsley-maxlength="20" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="secondPartyCopies">乙方份数</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="secondPartyCopies"  name="secondPartyCopies" data-parsley-maxlength="20" >
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
	            <label class="control-label" for="supplementClause">其他条款</label>
	            <div>
	                <textarea class="form-control field-editable" name ="supplementClause" id="supplementClause" rows="2" data-parsley-maxlength="1000">
	                </textarea>
	            </div>
            </div>
		</form>
		<div class="clearboth form-box m-t-5 scaleTableForm hidden">
			<form id="scaleTableForm"  data-parsley-validate="true">
				<table id="scaleTable" class="table table-striped table-bordered nowrap" width="100%">
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
function showPayTypeInfo(){
	//付款方式1，2，3
	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
	var isSpecial = $("[name='isSpecial']:checked").val();
	if(payTypeMode=="1"){
    	//首付款录入
    	//$('.firstPayment').show();
        $(".payPlat").addClass("hidden");
    	$('.firstPayment').removeClass("hidden");
    	$('.secondPayment').addClass("hidden");
    	$('.thirdPayment').addClass("hidden");
    	$(".paymentRatio1").removeClass("hidden");
    	$(".paymentRatio2").addClass("hidden");
    	$(".paymentRatio3").addClass("hidden");
    	$("#contractDetailform").styleFit();
    }else if(payTypeMode=="2"){
    	$(".paymentRatio2").removeClass("hidden");
       	$(".paymentRatio3").addClass("hidden");
        if ($('[name="isSpecial"]:checked').val() == "1"){
            $(".payPlat").removeClass("hidden");
		}else{
            $(".payPlat").addClass("hidden");
		}
        $('.firstPayment').removeClass("hidden");
        $('.secondPayment').removeClass("hidden");
        $('.thirdPayment').addClass("hidden");
        $("#contractDetailform").styleFit();
    }else {
       	$(".paymentRatio3").removeClass("hidden");
    	$(".paymentRatio2").removeClass("hidden");
    	if ($('[name="isSpecial"]:checked').val() == "1"){
            $(".payPlat").removeClass("hidden");
		}else{
            $(".payPlat").addClass("hidden");
		} 
    	$('.firstPayment').removeClass("hidden");
        $('.secondPayment').removeClass("hidden");
        $('.thirdPayment').removeClass("hidden");
        $("#contractDetailform").styleFit();
       
    }
}
    var detailSearchData={};

    //审核表单回显
    var contractJsonStr = '${contractJsonStr}';
    if(contractJsonStr!=null&&contractJsonStr!=''){
        var jsonObj = eval('(' + contractJsonStr + ')');
       $('#contractDetailform').deserialize(jsonObj);
       //$('#contractDetailform').initFixedNumber();
        dataBack(jsonObj);
    }



function dataBack(data){
	var payType = data.payType,
		conType = data.contractType,
        isSpecial = data.isSpecial;
	var contractType = data.contractType;
	detailSearchData.projLtypeId = data.projectType;
	detailSearchData.projId = data.projId;
	//判断是否显示工程规模，如果是民用的则显示
	if(contractType == "11"){
		$(".scaleTableForm").removeClass("hidden");
	}else{
		$(".scaleTableForm").addClass("hidden");
	}
	if(!isSpecial){
        $("[name='isSpecial'][value='0']").attr("checked",true);
	}
	if($("#contractType").val()=='11'){
		if($("#hoseAmount").val()==''){
			$("#hoseAmount").val($("#unitCost").val());
		}
		$(".not-resident").addClass("hidden");
		$(".resident").removeClass("hidden");
	}else{
		$(".resident").addClass("hidden");
		$(".not-resident").removeClass("hidden");
	}
	
	if($(".conNo").val()==''){
		$(".conNo").val($("#projNo").val());
	}
	var sedata;
	if($("#contractType").val()){
		if(isSpecial=="1"){
            sedata ="21";
		}else{
			// var selectEl = $("#contractType"),
			// index = selectEl[0].selectedIndex,
			// selectOption = selectEl[0].options[index];
			// sedata = $(selectOption).attr("value");
            sedata = conType;
		}
	}else{
		sedata = '-1';
	}
	$.ajax({
		type: 'post',
		url: 'constructContract/queryPayType?id='+sedata,
		dataType: 'json',
		success: function(data){
			$("#payType").empty();
//				$("#payType").append('<option value=""></option>');
			$.each(data,function(n, v){
	            if(payType==v.ptId){
	            	$("#payType").append('<option value='+v.ptId+' selected data-mode="'+v.payTypeMode+'">' + v.payTypeDes + '</option>');
	            }else{
	            	 $("#payType").append('<option value='+v.ptId+' data-mode="'+v.payTypeMode+'">' + v.payTypeDes + '</option>');
	            }
	        });
			if($("#deptDivide").val()==$("#customerServiceSenter").val()){
                $("#payType option[value='6']").remove();
            }
			if($("#conId").val()==''){
				$("#payType").removeAttr("disabled");
				$("#payType option:first").prop("selected", 'selected');
				$("#payType").attr("disabled","disabled");
				$("#payType").change();
			}else{
				showPayTypeInfo();
			}
		},
		error: function(data){
			console.warn("付款方式选框查询失败");
		}
	});
	
// 	defaultValue();
	/*if($("#contractMethod").val()=='4'){
		$(".REMARK").removeClass("hidden");
	}else{
		$(".REMARK").addClass("hidden");
	}*/
	if($("#contractType").val()=='21'||$("#contractType").val()=='31'){
		$(".REMARK").removeClass("hidden");
	}else{
		$(".REMARK").addClass("hidden");
	}
	if($("#deptDivide").val()==$("#modificationGroup").val()){
		$("#firstPayment").removeClass("field-not-editable");
		$("#firstPayment").addClass("field-editable");
		$("#secondPayment").removeClass("field-not-editable");
		$("#secondPayment").addClass("field-editable");
	}else{
		$("#secondPayment").removeClass("field-editable");
		$("#secondPayment").addClass("field-not-editable");
		$("#firstPayment").removeClass("field-editable");
		$("#firstPayment").addClass("field-not-editable");
	}
	$("#contractDetailform").styleFit();
	if($.fn.DataTable.isDataTable('#scaleTable')){
		//初始化过
		$("#scaleTable").cgetData(false,scaleTableCallBack);//列表重新加载
	}else{
		sacletableinit();
	
	}
	getIncrementAmount();
	if(data.govAuditCost!=null  && data.govAuditCost!=''){
		$(".govAuditCost").removeClass("hidden");
	}else{
		$(".govAuditCost").addClass("hidden");
	}
}
	var scaleTableCallBack = function(){
		$('#scaleTableForm').toggleEditState(false);
	}
/**
 * 签订按钮监听方法
 */
var signMonitor=function(){
	$('.signBtn').off('click').on('click',function(){
		if($('#constructContractTable').find('tr.selected').length>0){
			$("#contractDetailform a").attr("disabled",false);
            $(".selectDisabled").removeClass("disabled");
			$("#changeFlag").val("1");//使户数和每户金额change事件生效
			//表单可编辑
			$('#contractDetailform').toggleEditState(true);
			$('#scaleTableForm').toggleEditState(true);
			//按钮显示
			$('.editbtn').removeClass('hidden');
            if(!$("#signDate").val()){
                var sysDate = timestamp($("#sysDate").val());
                $("#signDate").val(format(sysDate,"default"));
            }
			defaultValue();
		}else{
			alertInfo('请选择要签订合同的工程记录！');
		}
	});
};

//默认值
function defaultValue(){
 	if($("#responsibility").val()===""){
 		$("#responsibility").text("3、本工程调压器前（中压）的燃气管线及设施，由乙方进行维护，不向甲方收取维护费用。调压器后（含调压器）属甲方资产，如甲方需要，可委托乙方进行有偿维护，具体见《燃气设施维修维护协议》");
 	}
} 




/**
 * 初始化规模列表
 */
 function sacletableinit() {
	"use strict";
    if ($('#scaleTable').length !== 0) {
    	 var scaleTable= $('#scaleTable').on( 'init.dt',function(){
    		$('#scaleTable').hideMask();
	        $('#scaleTableForm').toggleEditState(false);
	        inputMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
            ajax: {
            	url: 'constructContract/queryScaleDetail',
            	type:'post',
            	data: function(d){
                  	$.each(detailSearchData,function(i,k){
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
            },//自适应
            columns: [
	  			{"data":"projStypeDes", responsivePriority: 1},
	  			{"data":"tonnage",className:"text-right", responsivePriority: 3},
	  			{"data":"searNum",className:"text-right", responsivePriority: 6},
	  			{"data":"num",className:"text-right", responsivePriority: 5},
	  			{"data":"houseNum",className:"text-right", responsivePriority: 4},
	  			{"data":"gasConsumption",className:"text-right", responsivePriority: 7}
			],
			columnDefs: [{
				"targets":0,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				targets: 1,
				/*
				 * render属性
				 * 方法携带四个参数
				 * data: 该单元格的原始数据，也就是默认显示的那些数据
				 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
				 * row: 但前行的所有原始数据
				 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
				 */
				render: function ( data, type, row, meta ) {
					if(type === "display"){
						if(data === null){
							data = "";
						}
						var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_tonnage' id='" + row.projStypeId + "_tonnage'  data-parsley-maxlength='16' data-parsley-type='number' value="+data+"></div>"
						return tdcon;
					}else{
						return data;
					}}
			    },{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_searNum' id='" + row.projStypeId + "_searNum' data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    },{
			    	targets: 3,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_num' id='" + row.projStypeId + "_num'  data-parsley-maxlength='16' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}	}
			    },{
			    	targets: 4,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}
							var  tdcon="<div class='tdInnerInput'><input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_houseNum' id='" + row.projStypeId + "_houseNum' data-parsley-maxlength='9' data-parsley-type='integer' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    },{
			    	targets: 5,
					render: function ( data, type, row, meta ) {
						if(type === "display"){
							if(data === null){
								data = "";
							}else if(data !==null && data!==""){
								data = data.toFixed(2);
							}
							var  tdcon="<div class='tdInnerInput'><input type='hidden' name='"+row.projStypeId+"_projStypeId' id='"+row.projStypeId+"_projStypeId' value="+row.projStypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projLtypeId' id='" + row.projStypeId + "_projLtypeId' data-parsley-maxlength='9' data-parsley-type='number' value="+row.projLtypeId+">" +
									    "<input class='hidden' name='" + row.projStypeId + "_projStypeDes' id='" + row.projStypeId + "_projStypeDes'   value="+row.projStypeDes+">"+
									    "<input class='hidden' name='" + row.projStypeId + "_scaleId' id='" + row.projStypeId + "_scaleId'   value="+row.scaleId+">"+
									    "<input class='form-control input-sm text-right field-editable' name='" + row.projStypeId + "_gasConsumption' id='" + row.projStypeId + "_gasConsumption'  data-parsley-type='number' value="+data+"></div>"
							return tdcon;
						}else{
							return data;
						}}
			    }],
			ordering:false
       });
   }
};
/**
 * 输入监听
 */
var inputMonitor = function(){
	$(".tdInnerInput input").on("change",function(){
		var name = $(this).attr("name");
		var checkBoxId = name.split("_")[0]+"_scaleId";
		if($(this).val()){
			$("#"+checkBoxId).attr("checked","checked");
		}else{
			 var input = $(this).parents("tr").find(".tdInnerInput input").not("[type='radio']");
			 for(var i=0;i<input.length;i++){
				 if(input.eq(i).val()){
					 return false;
				 }
			 }
			 $("#"+checkBoxId).removeAttr("checked");
		}
	});
};

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
     //trSData.t && trSData.t.cgetDetail('contractDetailform','constructContract/viewContract','',dataBack);

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
           					||(data1.pipeLength!==null&&data1.pipeLength!==""&& data1.pipeLength!==undefined) */){
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

        	var url;
        	if($('#differenceId').val()=='sign'){//安装合同:签订推送
        	    url='constructContract/saveContract';

			}else if($('#differenceId').val()=='modify'){//合同修改推送
        	    url='contractModify/modifyContract';
			}
        	$.ajax({
                type: 'POST',
                url: url,
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
        			alertInfo("首付款不能少于50%");
        			return false;
        		}
        	}
        	if(data.contractMethod!='4'){
        		data.remark='';
        	}
            var url;
            if($('#differenceId').val()=='sign'){//安装合同:签订推送
                url='constructContract/saveContract';

            }else if($('#differenceId').val()=='modify'){//合同修改推送
                url='contractModify/modifyContract';
            }
        	$.ajax({
                type: 'POST',
                url: url,
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

    //是否特殊工程监听
    $('[name="isSpecial"]').on("change",function() {
            $("#contractType").change();
        if ($('[name="isSpecial"]:checked').val() == 0) {
            $(".payPlat").addClass("hidden");
        }
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
    	var selectEl = $("#contractType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
        if ($('[name="isSpecial"]:checked').val() == 1) {
            data='21';
        }
    	$.ajax({
    		type: 'post',
    		url: 'constructContract/queryPayType?id='+data,
    		dataType: 'json',
    		success: function(data){
    			$("#payType").empty();
    	        $("#payType").append('<option value=""></option>');
    			$.each(data,function(n, v){
    	            $("#payType").append('<option value='+v.ptId+' data-mode="'+v.payTypeMode+'">' + v.payTypeDes + '</option>');
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

   	   //特殊工程两次付款比例change监听--作废让其自由填写
/*     $("#payPlat").change(function(){
        if($(this).val()=="1"){
            $("#firstPayment").val((new Number($("#contractAmount").val())*0.5).toFixed(2));
            $("#secondPayment").val((new Number($("#contractAmount").val())*0.5).toFixed(2));
        }else if($(this).val()=="2"){
            $("#firstPayment").val((new Number($("#contractAmount").val())*0.6).toFixed(2));
            $("#secondPayment").val((new Number($("#contractAmount").val())*0.4).toFixed(2));
        }else if($(this).val()=="3"){
            $("#firstPayment").val((new Number($("#contractAmount").val())*0.7).toFixed(2));
            $("#secondPayment").val((new Number($("#contractAmount").val())*0.3).toFixed(2));
        }else if($(this).val()=="4"){
            $("#firstPayment").val((new Number($("#contractAmount").val())*0.8).toFixed(2));
            $("#secondPayment").val((new Number($("#contractAmount").val())*0.2).toFixed(2));
        }
    });  */

    //付款方式change监听
	$("#payType").change(function(){
		var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
        if(payTypeMode=="1"){
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
        }else if(payTypeMode=="2"){
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

        }/* else if($(this).val()=="6"){
        	$(".paymentRatio2").removeClass("hidden");
           	$(".paymentRatio3").addClass("hidden");
            $(".payPlat").addClass("hidden");
        	$('.firstPayment').removeClass("hidden");
        	$('.secondPayment').removeClass("hidden");
        	$(".paymentRatio2").removeClass("hidden");
        	$('.thirdPayment').addClass("hidden");
        	$("#thirdPayment").val("");
        	$("#contractDetailform").styleFit();
        } */else {
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
        if(payTypeMode=="1"){
        	$("#paymentRatio1").val('100');   //首付比例直接为100%
        	$("#firstPayment").val(totaAmount);  //金额为全款，即是合同总金额
        	$("#paymentRatio3").val('');
        	$("#paymentRatio2").val('');
        	$("#secondPayment").val('');
        	$("#thirdPayment").val("");
        	$("#paymentRatio1").attr("readonly","readonly");
        }else if(payTypeMode=="2"){   //若是两次付清
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
        }else{   //若是三次付清
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
    /* $("#paymentRatio1,#paymentRatio2,#paymentRatio3").on("change",function(){
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
    }); */
    //失去焦点事件，用来计算付款比例和付款的多少
   var loseBlur=function(paymentRate){
	   var reg=/^[0-9]+\.[0]+$/; //正则表达式,验证是否是整数
	  if((new Number(paymentRate))>100 || !reg.test(paymentRate)){  //判断是是否在1到100之间且为整数
			$("body").cgetPopup({
				title: '提示',
				content: '请输入1到100之间的整数!',
				ahide:true,
				atext:'确定'
			});
	   return false;  //终止程序
	   }  
	  CalculationFunction();  //调用函数计算付款比例和所付金额
    }
    //此函数用于计算付款比例和所付金额
   var  CalculationFunction=function(){
	   var totaAmount = new Number($("#contractAmount").val());  //合同总金额
	   var payNumber = $("#payType").val();    //分几期付款，3代表全款，4代表分两期，5代表分三期
	   var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
	   /**根据分期比例和合同总金额自动计算每期付款多少*/
	   if(payTypeMode=='2'){  //分两期付款
			var paymentRatio1=new Number($("#paymentRatio1").val());  //得到首付比列
			if(paymentRatio1>0){
				$("#paymentRatio2").removeClass("hidden");
				$("#firstPayment").val((totaAmount*(paymentRatio1/100.00)).toFixed(2));  //根据首付比列计算首付多少钱
				$("#paymentRatio2").val((100.00-paymentRatio1)+'.00');    //计算第二次付款的比列
				$("#secondPayment").val((((100.00-paymentRatio1)/100.00)*totaAmount).toFixed(2));  //计算第二期付款多少钱
			}
			if(payNumber=='6' && paymentRatio1<50 && $("#paymentRatio1").attr("readonly")!='readonly'){ //改管工程首付比列不能少于50%
				  $("body").cgetPopup({
						title: '提示',
						content: '首付比列不能少于50%!',
						ahide:true,
						atext:'确定'
					});
				  $("#paymentRatio1").select();  //选中首付款比例输入框
				  return false;  //终止程序
			}
	   }else if(payTypeMode=='3'){ //分三期付清
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
    	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
    	if(payTypeMode=="1"){
    		$("#firstPayment").val($("#contractAmount").val());
        }else if(payTypeMode=="2"){
        	CalculationFunction();  //调用函数计算付款比例和所付金额
        }else if($("#payType").val()=="6"){
        	CalculationFunction();  //调用函数计算付款比例和所付金额
        }else{
        	CalculationFunction();  //调用函数计算付款比例和所付金额
        }
    	
	});
    $("#firstPayment").on("change",function(){
    	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
    	if(payTypeMode=="2"){
        	$("#secondPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())).toFixed(2));
        }else if(payTypeMode=="3"){
        	$("#secondPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
        	$("#thirdPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())-new Number($("#secondPayment").val())).toFixed(2));
        }
	});
    $("#secondPayment").on("change",function(){
    	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
    	if(payTypeMode=="2"){
    		$("#firstPayment").val((new Number($("#contractAmount").val())-new Number($("#secondPayment").val())).toFixed(2));
        }else if(payTypeMode=="3"){
        	$("#thirdPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())-new Number($("#secondPayment").val())).toFixed(2));
        	$("#firstPayment").val((new Number($("#contractAmount").val())-new Number($("#secondPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
        }
	});
    $("#thirdPayment").on("change",function(){
    	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
    	if(payTypeMode=="3"){
        	$("#firstPayment").val((new Number($("#contractAmount").val())-new Number($("#secondPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
        	$("#secondPayment").val((new Number($("#contractAmount").val())-new Number($("#firstPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
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
    	getIncrementAmount();
    });

     function getIncrementAmount(){
    	//（公建）安装合同报表中的其中价为（工程总价/(1+税率)），税为（工程总价-其中价）
		$("#incrementAmount").val((new Number($("#contractAmount").val())-(new Number($("#contractAmount").val())/(1+(new Number($("#increment").val())/100)))).toFixed(2));
    }

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