<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn">保存</a>
    	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="settlementAuditStartform" action="">
		    <input type="hidden" name="sdId" id="sdId"/>
		    <input type="hidden" name="projId" id="projId"/>
    		<input type="hidden" id="corpId" name="corpId" value=""/>
    		<input type="hidden" id="deptId" name="deptId" value=""/>
    		<input type="hidden" id="tenantId" name="tenantId" value=""/>
    		<input type="hidden" id="compilerId" name="compilerId" value=""/>
		    <input type="hidden"  id="isPrint" name="isPrint" value=""/>
		    <div class="form-group col-md-6 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 clearboth">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    
		    <!-- <div class="form-group col-md-12 ">
		    	<label class="control-label" for="custName">申报单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
		        </div>
		    </div> -->
       		
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地址</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		    	<label class="control-label" for="">工程规模</label>
		        <div>
		        	<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" data-parsley-maxlength="200"/></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
            	<!-- 新加字段 -->
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100" value=""/>
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
		   <!--  <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone"/>
		        </div>
		    </div> -->
		    <div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">分合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="scNo" name="scNo"/>
		        </div>
		    </div>
		 
		    <div class="form-group col-md-6 ">
		        <label class="control-label">报审日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="ocoDate" name="ocoDate"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth ">
		        <label class="control-label" for="sendDeclarationCost">报审金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable  text-right fixed-number" id="sendDeclarationCost" name="sendDeclarationCost"/>
		            <a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="materialsCost">含主材费</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="materialsCost" name="materialsCost"  data-parsley-type="number" value=""/>
		            <a href="javascript:;" class="btn btn-sm btn-default">元</a>
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
		     <div class="form-group col-md-6">
				<label class="control-label" for="auxiliaryMaterialAmount">破路费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="auxiliaryMaterialAmount" name="auxiliaryMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
					</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="managementCost">协调费</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="managementCost" name="managementCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
		     <div class="form-group col-md-6">
				<label class="control-label" for="recoveryCost">恢复费</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable fixed-number text-right" id="recoveryCost" name="recoveryCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="jeevesCost">占道费</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="jeevesCost" name="jeevesCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="compensateCost">赔偿费</label>
				<div>
					<input type="text" class=" form-control input-sm field-not-editable fixed-number text-right" id="compensateCost" name="compensateCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
		     <div class="form-group col-md-6" style="display:none">
				<label class="control-label signature-tool " for="compilerSign">编制人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="compilerSign" name="compilerSign" data-parsley-required="true">
					<input type="hidden" id="compilerSign_postType" name="compilerSign_postType" value="${compilerSignPost }" >
					<img class="" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 clearboth">
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
		    <div class="form-group col-md-6 clearboth">
				<label class="control-label signature-tool " for="cuAudit">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuAudit" name="cuAudit" data-parsley-required="true">
					<input type="hidden" id="cuAudit_postType" name="cuAudit_postType" value="${cuAuditPost }" >
					<img class="cuAudit" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label signature-tool " for="cuPrincipal">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPrincipal" name="cuPrincipal" data-parsley-required="true">
					<input type="hidden" id="cuPrincipal_postType" name="cuPrincipal_postType" value="${cuPrincipalPost }" >
					<img class="cuPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			 <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="quantitiesTotal">合计</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="quantitiesTotal" name="quantitiesTotal"  data-parsley-type="number" value=""/>
		        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="firstDeclarationCost">初审金额</label>
		        <div>
		            <input type="text" class="form-control input-sm fixed-number field-not-editable  text-right cost" id="firstDeclarationCost" name="firstDeclarationCost" data-parsley-required="true" data-parsley-type="number"/>
		        <a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="firstMaterialsCost">主材费</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="firstMaterialsCost" name="firstMaterialsCost" data-parsley-required="true"  data-parsley-type="number" value=""/>
		       <a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="auditMinusCost">核减金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right cost" id="auditMinusCost" name="auditMinusCost" data-parsley-required="true" data-parsley-type="number"/>
		       <a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="auditMinusRate">核减率</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable text-right" id="auditMinusRate" name="auditMinusRate"  data-parsley-maxlength="10"/>
		        </div>
		    </div>
			
		    <div class="form-group col-md-6 ">
		        <label class="control-label">初审日期</label>
		        <div>
		            <input type="text" class="form-control datepicker-default field-editable timestamp" id="firstAuditDate" name="firstAuditDate" data-parsley-required="true"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
				<label class="control-label signature-tool sign-require" for="firstAuditPerson">初审人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="firstAuditPerson" name="firstAuditPerson" data-parsley-required="true">
					<input type="hidden" id="firstAuditPerson_postType" name="firstAuditPerson_postType" value="" >
					<img class="" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
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
    //初始化工程量列表
    $("#qualitiesForm").toggleEditState(false).styleFit();
    var firstInit = function(){
    	if($.fn.DataTable.isDataTable('#qualitiesTable')){
    		dataBack();
    	}else{
    		qualitiesInit();
    	}	
    }();
    //参数传false时，表单不可编辑
    $("#settlementAuditStartform").toggleEditState(false).styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //点击保存
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
    	}else  */if($("#quantitiesTotal").val()!==$("#init_quantitiesTotal").val()){
    		var myoptions = {
                   	title: "提示信息",
                   	content: "请先保存报审工程量",
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
    	}else{
    		//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
        	//表单验证
            if($("#settlementAuditStartform").parsley().isValid()){
            	//json字符串
            	
            	//验证必签签字是否已签
    	        var signtools =$('#settlementAuditStartform').find(".signature-tool.sign-require"),
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
            	
            	var viewdata=$("#settlementAuditStartform").serializeJson();
            	//加遮罩
     	        $(".infodetails").loadMask("正在保存...", 1, 0.5);
            	$.ajax({
                    type: 'POST',
                    url: 'settlementAuditStart/saveAuditStart',
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(viewdata),
                    success: function (data) {
                    	//取消遮罩
                    	$(".infodetails").hideMask();	
                    	var content = "数据保存成功！";
                    	if(data === "false"){
                    		content = "数据保存失败！";
                    	}else if(data === "true"){
//                     		$('#settlementAuditStartform')[0].reset();
//                             $('.clear-sign').click();
//                             $('.clear-sign').resetSign();
                   		 $(".editbtn").addClass("hidden");
                   		 $("#settlementAuditStartform").toggleEditState(false);
                    		$("#settlementAuditStartform").formReset();
                    		$("#settlementAuditStartTable").cgetData("",dataBack);  //列表重新加载
                    	}
                    	var myoptions = {
                            	title: "提示信息",
                            	content: content,
                            	accept: popClose,
                            	chide: true,
                            	icon: "fa-check-circle"
                        }
                        $("body").cgetPopup(myoptions);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                    	//取消遮罩
                    	$(".infodetails").hideMask();	
                        console.warn("结算初审信息保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#settlementAuditStartform").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#settlementAuditStartform").parsley().validate();
            }
    	}
    
         
    });
    //签字隐藏叉号图片
    $(".fieldPrincipal-a").handleSignature(false);
    //详述
	trSData.t&&trSData.t.cgetDetail('settlementAuditStartform','settlementAuditStart/auditStartDetail','',tableCallBack);
	// 签名不可编辑
    $("#fieldPrincipal-a,#fieldPrincipal-b").toggleSign(false);
	
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#settlementAuditStartform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 $(".right-btn").addClass("hidden");
    	 $("#settlementAuditStartTable").cgetData("",dataBack);  //列表重新加载
    });
     $("#quantitiesTotal").on("change",function(){
    	//$("#firstDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
    	//计算核减
    	$("#auditMinusCost").val((new Number($("#sendDeclarationCost").val())-(new Number($("#firstDeclarationCost").val()))).toFixed(2));
    	if($("#auditMinusCost").val()==0){
    		$("#auditMinusRate").val($("#auditMinusCost").val());
    	}else{
    		$("#auditMinusRate").val(((new Number($("#auditMinusCost").val())/new Number($("#sendDeclarationCost").val()))*100).toFixed(2)+''+'%');
    	}
    	
	}); 
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_1");
        var signatures2 = $("#signBtn_2");
        var signatures3 = $("#signBtn_3");
        var signatures4 = $("#signBtn_4");
        signatures.handleSignature(false);
        signatures2.handleSignature(false);
        signatures3.handleSignature(false);
        signatures4.handleSignature(false);
    });
	
	//核减金额输入框失去焦点时间
	$("#firstDeclarationCost").on("change",function(){
		var firstDeclarationCost = $("#firstDeclarationCost").val();
		console.info(firstDeclarationCost+"=====firstDeclarationCost");
		$("#auditMinusCost").val((new Number($("#sendDeclarationCost").val())-(new Number($("#firstDeclarationCost").val()))).toFixed(2));
		if($("#auditMinusCost").val()==0){
			$("#auditMinusRate").val($("#auditMinusCost").val());
		}else{
			$("#auditMinusRate").val(((new Number($("#auditMinusCost").val())/new Number($("#sendDeclarationCost").val()))*100).toFixed(2)+''+'%');
		}
		
	});
	
	 /* 	输入数字校验 */ 
    $('.fixed-number').on('keyup', function(){
    	  $(this).parsley().validate();
    	}); 
	</script>
<!-- ================== END PAGE LEVEL JS ================== -->