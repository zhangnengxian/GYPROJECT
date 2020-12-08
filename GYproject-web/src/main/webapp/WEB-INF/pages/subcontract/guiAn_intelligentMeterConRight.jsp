<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveButton temporarySaveBtn" >暂存</a>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushButton saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="imcDetailForm" data-parsley-validate="true" action="">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="imcId" name="imcId"/>
       		<input type="hidden" id="deptId" name="deptId"/>
       		<input type="hidden" id="deptName" name="deptName"/>
       		<input type="hidden" id="flag" name="flag"/>
       		<div class="form-group col-md-6 ">
		    	<label class="control-label" for="imcNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="imcNo" name="imcNo"  data-parsley-required="true" value="" data-parsley-maxlength="30"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="30" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="conName">合同名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="conName" name="conName" data-parsley-maxlength="200" data-parsley-required="true" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" id = "projScaleDes" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projContent">工程内容</label>
		        <div>
		        	<textarea class="form-control input-sm field-editable" name="projContent" id = "projContent" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    <!-- <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">是否是智能表</label>
		    	<div>
		        	<label>
		            	<input type="radio" class="field-not-editable" name="isIntelligentMeter" value="1" />是
		            </label>
		            <label>
		            	<input type="radio" class="field-not-editable" name="isIntelligentMeter" value="0" checked/>否
		            </label>
		        </div>
		    </div> -->
		     <div class="form-group col-md-6">
		        <label class="control-label" for="installNums">安装户数</label>
		        <div>
		        	<input type="number" class="form-control input-sm field-editable text-right" min="0" name="installNums" id = "installNums" rows="" cols="" data-parsley-maxlength="20" data-parsley-required="true"/>
		        	<a href="javascript:;" class="btn btn-sm btn-default">户</a>
		        </div>
		    </div>
		    
		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasCustName">用气单位</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="gasCustName" name="gasCustName" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		        	<!-- <a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>
		       		 -->
		        </div>
		       
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="gasConNo">用气合同编号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="gasConNo" name="gasConNo" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		    <!-- 贵安甲方为用户，乙方为燃气集团 -->
		    <div class="form-group col-md-12">
		        <label class="control-label" for="corpName">乙方</label>
		        <div>
		       		<input type="hidden"  id="corpId" name="corpId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="grantRepresent">授权代表人</label>
		        <div>
		       		<input type="hidden"  id="grantRepresentId" name="grantRepresentId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-editable"  id="grantRepresent" name="grantRepresent" data-parsley-maxlength="50"  value="" />
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyConAgent">经办人</label>
		        <div>
		       		<input type="hidden"  id="fPartyConAgentId" name="fPartyConAgentId" data-parsley-maxlength="30"  value="" />
		       		<input type="text" class="form-control input-sm field-not-editable"  id="fPartyConAgent" name="fPartyConAgent" data-parsley-maxlength="50"  value="" />
		        	<%--<a id="managePop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择甲方"><i class="fa fa-search"></i></a>--%>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyBankName">开户行</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="fPartyBankName" name="fPartyBankName" data-parsley-maxlength="50"  value="">
		        	<a id="bankPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择开户行"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyBankAccount">开户行帐号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="fPartyBankAccount" name="fPartyBankAccount" data-parsley-maxlength="50"  value="" data-parsley-required="true"/>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyTelNumber">联系电话</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyTelNumber" name="fPartyTelNumber" data-parsley-maxlength="20"  value="" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="fPartyAddr">地址</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="fPartyAddr" name="fPartyAddr" data-parsley-maxlength="50"  value="" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-12 clearboth">
		        <label class="control-label" for="sPartyName">甲方</label>
		        <div>
		        	<input type="hidden" id="sPartyId" name="sPartyId"/>
		            <input type="text" class="form-control input-sm field-not-editable"  id="sPartyName" name="sPartyName" data-parsley-maxlength="200" data-parsley-required="true"/>
		        	 <a id="custPop" class="asBtn btn btn-default btn-sm m-l-10 aspop" title="选择申报单位"><i class="fa fa-search"></i></a>
		        </div>
		    </div>
		   	<div class="form-group col-md-6 ">
		        <label class="control-label" for="sPartyLegalRepresent">法定代表人</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyLegalRepresent" name="sPartyLegalRepresent" data-parsley-maxlength="50"  value="" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyAgent">经办人</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyAgent" name="sPartyAgent" data-parsley-maxlength="50"  value="" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyBankName">开户行</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="sPartyBankName" name="sPartyBankName" data-parsley-maxlength="50"  value=""/>
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyBankAccount">开户行帐号</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="sPartyBankAccount" name="sPartyBankAccount" data-parsley-maxlength="50"  value="" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyTelNumber">联系电话</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-not-editable"  id="sPartyTelNumber" name="sPartyTelNumber" data-parsley-maxlength="20"  value="" />
		       		
		        </div>
		    </div>
		   	<div class="form-group col-md-6">
		        <label class="control-label" for="sPartyAddr">地址</label>
		        <div>
		       		<input type="text" class="form-control input-sm field-editable"  id="sPartyAddr" name="sPartyAddr" data-parsley-maxlength="50"  value="" />
		       		
		        </div>
		    </div>
			<!-- <div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType">
						<option value="" ></option>
					</select>
				</div>
			</div>
            <div class="form-group col-md-6">
            	承包方式改为合同价款方式
		        <label class="control-label" for="scType">合同价款方式</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="scType" name="scType">
						<option value=""></option>
					</select>
		        </div>
		    </div> -->
			<!-- <div class="form-group col-md-6">
				承包方式改为合同价款方式
				<label class="control-label" for="progressType">进度款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="progressType"  name="progressType">
						<option value="" ></option>
					</select>
				</div>
			</div> -->
			
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="unitCost">单价</label>
		        <div>
		        	<!-- <select class="form-control input-sm field-editable" id="unitCost"  name="unitCost" data-parsley-required="true">
						<option value="290" >290.00</option>
						<option value="630" >630.00</option>
					</select> -->
		            <input type="text" class="form-control input-sm field-editable fixed-number text-right" id="unitCost" name="unitCost" data-parsley-maxlength="16" value="" data-parsley-type="number" data-parsley-required="true" />
		        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
			<div class="form-group col-md-6">
				<label class="control-label" for="totalCost">合同金额</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="totalCost" name="totalCost" data-parsley-maxlength="16" value="" data-parsley-type="number" data-parsley-required="true"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 payType">
				<label class="control-label" for="payType">预付款方式</label>
				<div>
					<select class="form-control input-sm field-editable" id="payType"  name="payType" data-parsley-required="true">
						<c:forEach var="enumes" items="${payTypes }">
							<option value="${ enumes.ptId}" data-mode="${enumes.payTypeMode }">${enumes.payTypeDes }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 col-sm-6 paymentRatio1  clearboth">
		        <label class="control-label" for="paymentRatio1">首付比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="paymentRatio1"  readonly="readonly" name="paymentRatio1" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例" />
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
			<div class="form-group col-md-6 firstPayment hidden">
		        <label class="control-label" for="firstPayment">首付款</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="firstPayment"  name="firstPayment" data-parsley-type="number"
		            data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 paymentRatio2 hidden">
		        <label class="control-label" for="paymentRatio2 ">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="paymentRatio2" readonly="readonly" name="paymentRatio2" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例" />
					 <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 secondPayment hidden">
		        <label class="control-label" for="secondPayment">阶段款</label>
		        <div>
		           	<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="secondPayment"  name="secondPayment" data-parsley-type="number" data-parsley-maxlength="16" value="" data-parsley-required="true">
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		         </div>
		    </div>
		    <div class="form-group col-md-6 paymentRatio3 hidden ">
		        <label class="control-label" for="paymentRatio3">阶段款比列</label>
		        <div>
		           <input type="text" class=" form-control input-sm fixed-number field-not-editable text-right" id="paymentRatio3" readonly="readonly" name="paymentRatio3" data-parsley-type="number"
		            data-parsley-maxlength="6" value="" data-parsley-required="true" step="1" min="0" max="100" placeholder="请输入付款比例"  >
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
		    
		     <div class="form-group col-md-6">
		        <label class="control-label" for="increment">税率</label>
		        <div>
		        <select class=" form-control input-sm field-editable fixed-number text-right" id="increment"  name="increment" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-required="true">
		        		<option></option>
		        		<c:forEach var="enums" items="${increment }">
		        			<option value="${ enums.increment}">${ enums.increment}</option>
		        		</c:forEach>
		        	</select>
		            <a href="javascript:;" class="btn btn-sm btn-default selectDisabled disabled">%</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="incrementAmount">增值税</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="incrementAmount"  name="incrementAmount"  data-parsley-maxlength="16" value="" data-parsley-type="number">
		        </div>
		    </div>
		    <!-- <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="invoiceType" name="invoiceType" placeholder="" data-parsley-maxlength="20" value="增值税普通发票" data-parsley-maxlength="20">
		        </div>
		    </div> -->
		    <div class="form-group col-md-6">
		        <label class="control-label" for="invoiceType">发票类型</label>
		        <div>
		    		<select class="form-control input-sm field-editable  text-right" id="invoiceType" name="invoiceType" data-parsley-maxlength="16" data-parsley-required="true" data-parsley-id="6402">
		        		<option value=""></option>
		        		<option value="1">增值税专用发票</option>
		        		<option value="2">增值税普通发票</option>
		        	</select>
		        	</div>
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="supplementClause">补充条款</label>
		        <div>
		        	<textarea class="form-control input-sm field-editable" name="supplementClause" id = "supplementClause" rows="" cols="" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="">楼盘名称</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="houseName" name="houseName" placeholder="" data-parsley-maxlength="200" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">楼盘地址</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable " id="houseAddr" name="houseAddr" placeholder="" data-parsley-maxlength="200">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">预计完工日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="predictCompleteDate" name="predictCompleteDate" placeholder="">
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label">签订日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="imcSignDate" name="imcSignDate" value="" data-parsley-required="true">
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
    $('.editbtn').addClass('hidden');
    //表单样式适应
    $('#imcDetailForm').toggleEditState().styleFit();
    changeAText();
    
    function showPayTypeInfo(){
    	//付款方式1，2，3
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
        	$("#imcDetailForm").styleFit();
        }else if(payTypeMode=="2"){
        	$(".paymentRatio2").removeClass("hidden");
           	$(".paymentRatio3").addClass("hidden");
           
            $('.firstPayment').removeClass("hidden");
            $('.secondPayment').removeClass("hidden");
            $('.thirdPayment').addClass("hidden");
            $("#imcDetailForm").styleFit();
        }else {
           	$(".paymentRatio3").removeClass("hidden");
        	$(".paymentRatio2").removeClass("hidden");
        	
        	$('.firstPayment').removeClass("hidden");
            $('.secondPayment').removeClass("hidden");
            $('.thirdPayment').removeClass("hidden");
            $("#imcDetailForm").styleFit();
           
        }
    }
    /**详述回调*/
    var cgetDetailBack = function(data){
    	//合同签订日期
    	if(data.imcId==''||data.imcId==null){
    		$("#imcSignDate").val(format(data.imcSignDate,"default"));
    	}
    	
    	var src1=$("#projNo").val();
    	if($("#imcNo").val()==""){
    		$("#imcNo").val(src1.substring(0,4)+"02"+src1.substring(6,14));
    	}
    	
    	//合同名默认为工程名称
    	if($("#conName").val()==null || $("#conName").val() == ''){
    		$("#conName").val($("#projName").val());
    	}
    	
    	//付款方式
    	if(data.imcId==''||data.imcId==null){
    		$("#payType option:first").prop("selected", 'selected');
			$("#payType").change();
    	}else{
    		showPayTypeInfo();
    	}
    	//获取税率
    	getIncrementAmount();
    }
  	//查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('imcDetailForm','intelligentMeterCon/viewIntelligentMeterCon','',cgetDetailBack);
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    /**点击右侧维护区 保存/推送 按钮时*/
    $('.saveBtn').on('click',function(){
    	$("#flag").val("1");
        if($('#imcDetailForm').parsley().isValid()){
        	 $("body").cgetPopup({
 	        	title: "提示信息",
 	        	content: "推送后，将记录合同收款信息，您确定要推送合同吗？",
 	        	accept:saveBtnBack,
 	        	icon: "fa-warning",
 	        	//newpop: 'new'
 	        });
        	
        	//如果通过验证, 则移除验证UI
        	$("#imcDetailForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#imcDetailForm").parsley().validate();
        }
        
    }); 
    var saveBtnBack = function(){
    	var data=$('#imcDetailForm').serializeJson();
    	$('#imcDetailForm').parent().parent().loadMask("正在保存。。。",1,0.5);
    	$.ajax({
            type: 'POST',
            url: 'intelligentMeterCon/saveIMContract',
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (data) {
            	$('#imcDetailForm').parent().parent().hideMask();
            	var content = "数据保存成功！";
            	if(data.ret_type ==-1){
            		content = "数据保存失败！";
            		alertInfo(content);
            	}else if(data.ret_message ===  "exist"){
            		content = "合同编号已存在，请修改！";
            		alertInfo(content);
            	}else if(data.ret_message ===  "true"){
            		$("#imcDetailForm input").val('');
            		$("#imcDetailForm textarea").val('');
            		$("#imcDetailForm").formReset();//表单重置
            		//$("#subSafeDetailform").formReset();//表单重置
            		$("#imcTable").cgetData(true,tableCallBack);  //列表重新加载
            		$("#imcDetailForm").toggleEditState(false);
            		alertInfo(content);
            	}else{//接口失败
            		alertInfo(data.ret_message);
            	} 
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	$('#imcDetailForm').parent().parent().hideMask()
                console.warn("智能表合同签订区记录保存失败！");
            }
        });
    }
    /**点击右侧维护区 暂存 按钮时*/
    $('.temporarySaveBtn').on('click',function(){
    	$("#flag").val("0");
        if($('#imcDetailForm').parsley().isValid()){
        	//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
        	var data=$('#imcDetailForm').serializeJson();
        	$('#imcDetailForm').parent().parent().loadMask("正在保存。。。",1,0.5);
        	$.ajax({
                type: 'POST',
                url: 'intelligentMeterCon/saveIMContract',
                contentType: "application/json;charset=UTF-8",
                dataType:"json",
                data: JSON.stringify(data),
                success: function (data) {
                	$('#imcDetailForm').parent().parent().hideMask()
                	var content = "数据保存成功！";
                	if(data.ret_type ==-1){
                		content = "数据保存失败！";
                		alertInfo(content);
                	}else if(data.ret_message ===  "exist"){
                		content = "合同编号已存在，请修改！";
                		alertInfo(content);
                	}else if(data.ret_message ===  "true"){
                		$("#imcDetailForm input").val('');
                		$("#imcDetailForm textarea").val('');
                		$("#imcDetailForm").formReset();//表单重置
                		//$("#subSafeDetailform").formReset();//表单重置
                		$("#imcTable").cgetData(true,tableCallBack);  //列表重新加载
                		$("#imcDetailForm").toggleEditState(false);
                		alertInfo(content);
                	}else{//接口失败
                		alertInfo(data.ret_message);
                	} 
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	$('#imcDetailForm').parent().parent().hideMask()
                    console.warn("智能表合同签订区记录保存失败！");
                }
            });
        	//如果通过验证, 则移除验证UI
        	$("#imcDetailForm").parsley().reset();
        }else{
        	//如果未通过验证, 则加载验证UI
        	$("#imcDetailForm").parsley().validate();
        }
        
    }); 
    
    var accrualsRecordDone = function(){
    	
    	$("#imcDetailForm input").val('');
		$("#imcDetailForm textarea").val('');
		$("#imcTable").cgetData(true,tableCallBack);  //列表重新加载
		alertInfo('数据保存成功！');
    }
    var tableCallBack = function(){
    	var len = $('#imcTable').DataTable().ajax.json().data ? $('#imcTable').DataTable().ajax.json().data.length : $('#imcTable').DataTable().ajax.json().length;
    	if(len<1){
    		 //清空右侧记录
    		 $("#imcDetailForm").formReset();
    		// $("#subSafeDetailform").formReset();
    		 $(".editbtn").addClass("hidden");
    		 $('#imcDetailForm').toggleEditState().styleFit();
    		 //$('#subSafeDetailform').toggleEditState().styleFit();
    	 }
    }
    //放弃
    $(".cancelBtn").on("click",function(){
    	$("#imcDetailForm input").val('');
    	trSData.t.cgetDetail('imcDetailForm','intelligentMeterCon/viewIntelligentMeterCon',''); 
    	$("#imcDetailForm").toggleEditState(false);
    	$(".editbtn").addClass("hidden");
	   	selectTr["imcTable"] = 0;
	   	$('tbody tr:eq(0)').select();
	   	//移除验证
	   	$("#imcDetailForm").parsley().reset();
    });
 	//安装户数和每户金额 
    $("#unitCost,#installNums").on("change",function(){
    	var unitCost = $('#unitCost').val();
    	var installNums = $('#installNums').val();
    	if(installNums==''){
    		installNums=0;
    	}
    	if(unitCost==''){
    		unitCost=0;
    	}
    	$("#totalCost").val(new Number(installNums*unitCost).toFixed(2));
    	changePayTypeFunc();
    });
    /* //付款方式改变时 
    $("#payType").change(function(){
    	changePayTypeFunc();
	}); */
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
        	$("#imcDetailForm").styleFit();
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
                $("#imcDetailForm").styleFit();
			}else{
                $(".payPlat").addClass("hidden");
				$('.firstPayment').removeClass("hidden");
				$('.secondPayment').removeClass("hidden");
				$('.thirdPayment').addClass("hidden");
				$("#thirdPayment").val("");
				$("#imcDetailForm").styleFit();
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
        	$("#imcDetailForm").styleFit();
        } */else {
           	$(".paymentRatio3").removeClass("hidden");
        	$(".paymentRatio2").removeClass("hidden");
            $(".payPlat").addClass("hidden");
            if ($('[name="isSpecial"]:checked').val() == "1"){
                $('.firstPayment').removeClass("hidden");
                $('.secondPayment').removeClass("hidden");
                $('.thirdPayment').removeClass("hidden");
                $("#imcDetailForm").styleFit();
            }else{
				$('.firstPayment').removeClass("hidden");
				$('.secondPayment').removeClass("hidden");
				$('.thirdPayment').removeClass("hidden");
				$("#imcDetailForm").styleFit();
			}
        }
         $("#paymentRatio1").val('');
         var totaAmount = $("#totalCost").val();  //合同总金额
        //如果是一次性付清首付比列为100%
        if(payTypeMode=="1"){
        	$("#paymentRatio1").val('100');   //首付比例直接为100%
        	$("#firstPayment").val(totaAmount);  //金额为全款，即是合同总金额
        	$("#paymentRatio3").val('');
        	$("#paymentRatio2").val('');
        	$("#secondPayment").val('');
        	$("#thirdPayment").val("");
        	$("#paymentRatio1").attr("readonly",true);
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
    //失去焦点事件，用来计算付款比例和付款的多少
   /* var loseBlur=function(paymentRate){
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
    } */
    //此函数用于计算付款比例和所付金额
   var  CalculationFunction=function(){
	   var totaAmount = new Number($("#totalCost").val());  //合同总金额
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
   $("#firstPayment").on("change",function(){
   	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
   	if(payTypeMode=="2"){
       	$("#secondPayment").val((new Number($("#totalCost").val())-new Number($("#firstPayment").val())).toFixed(2));
       }else if(payTypeMode=="3"){
       	$("#secondPayment").val((new Number($("#totalCost").val())-new Number($("#firstPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
       	$("#thirdPayment").val((new Number($("#totalCost").val())-new Number($("#firstPayment").val())-new Number($("#secondPayment").val())).toFixed(2));
       }
	});
   $("#secondPayment").on("change",function(){
   		var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
   		if(payTypeMode=="2"){
   			$("#firstPayment").val((new Number($("#totalCost").val())-new Number($("#secondPayment").val())).toFixed(2));
       }else if(payTypeMode=="3"){
       	$("#thirdPayment").val((new Number($("#totalCost").val())-new Number($("#firstPayment").val())-new Number($("#secondPayment").val())).toFixed(2));
       	$("#firstPayment").val((new Number($("#totalCost").val())-new Number($("#secondPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
       }
	});
   $("#thirdPayment").on("change",function(){
   	var payTypeMode = $("#payType").find("option:selected").attr("data-mode");
   	if(payTypeMode=="3"){
       	$("#firstPayment").val((new Number($("#totalCost").val())-new Number($("#secondPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
       	$("#secondPayment").val((new Number($("#totalCost").val())-new Number($("#firstPayment").val())-new Number($("#thirdPayment").val())).toFixed(2));
       }
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
                    $("#fPartyBankName").val(data.bankAdress);
                    //开户账号
                    $("#fPartyBankAccount").val(data.bankNo);
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
    		    	$("#gasCustName").val(data.custName);
    		    	$("#sPartyName").val(data.custName);
    		    	$("#sPartyId").val(data.custId);
    		    	//联系人、联系电话、单位地址
    		    	$("#sPartyTelNumber").val(data.custPhone);
    		    	$("#sPartyAgent").val(data.custContcat);
    		    	$("#sPartyAddr").val(data.unitAddress);
    	    	}
    	    }
      });
  	});

  	//税率、合同金额改变
    $("#increment,#totalCost").on("change",function(){
    	getIncrementAmount();
    });

    var getIncrementAmount = function(){
    	//（公建）安装合同报表中的其中价为（工程总价/(1+税率)），税为（工程总价-其中价）
		$("#incrementAmount").val((new Number($("#totalCost").val())-(new Number($("#totalCost").val())/(1+(new Number($("#increment").val())/100)))).toFixed(2));
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->