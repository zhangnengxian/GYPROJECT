<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 auditSaveBtn">保存</a>
    	<a href="javascript:;" class="btn btn-default btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="settlementAuditDoneform" action="">
		    <input type="hidden" name="sdId" id="sdId"/>
		    <input type="hidden" name="projId" id="projId"/>
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
		    <!-- <div class="form-group col-md-6 ">
		    	<label class="control-label" for="custName">申报单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
		        </div>
		    </div> -->
       		
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		    	<label class="control-label" for="">工程规模</label>
		        <div>
		        	<textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cmoName">施工管理处</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cmoName" name="cmoName"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="sendDeclarationCost">报审金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number" id="sendDeclarationCost" name="sendDeclarationCost"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="ocoDate">报审日期</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="ocoDate" name="ocoDate"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="firstDeclarationCost">初审金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number" id="firstDeclarationCost" name="firstDeclarationCost"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="firstAuditDate">初审日期</label>
		        <div>
		            <input type="text" class="form-control input-sm datepicker-default field-not-editable" id="firstAuditDate" name="firstAuditDate"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="auditMinusCost">初审核减金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="auditMinusCost" name="auditMinusCost" data-parsley-type="number"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="auditMinusRate">初审核减率</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="auditMinusRate" name="auditMinusRate"  data-parsley-maxlength="10"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="quantitiesTotal">合计</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="quantitiesTotal" name="quantitiesTotal"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="sale">销售金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="sale" name="sale"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="purStuRate">采保费率</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="purStuRate" name="purStuRate" data-parsley-maxlength="100" value="2.5%"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="purStuCost">采保费</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="purStuCost" name="purStuCost"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="costType1">费用类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="costType1" name="costType1" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cost1">费用金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost1" name="cost1"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="costType2">费用类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="costType2" name="costType2" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cost2">费用金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost2" name="cost2"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="costType3">费用类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="costType3" name="costType3" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cost3">费用金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost3" name="cost3"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="costType4">费用类型</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="costType4" name="costType4" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="cost4">费用金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="cost4" name="cost4"  data-parsley-type="number" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="suCost">监理费</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number"  id="suCost" name="suCost"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="materialCutPayment">材料扣款</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable fixed-number" id="materialCutPayment" name="materialCutPayment"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="endDeclarationCost">终审金额</label>
		        <div>
		            <input type="text" class="form-control input-sm fixed-number field-not-editable" id="endDeclarationCost" name="endDeclarationCost" data-parsley-type="number"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="endAuditMinusRate">终审核减率</label>
		        <div>
		            <input type="text" class="form-control input-sm fixed-number field-not-editable" id="endAuditMinusRate" name="endAuditMinusRate" />
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="endAuditMinusCost">核减金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="endAuditMinusCost" name="endAuditMinusCost" data-parsley-type="number"/>
		        </div>
		    </div>
			 <div class="form-group col-md-6">
				<label class="control-label signature-tool" for="costControlAudit">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="costControlAudit" name="costControlAudit" data-parsley-required="true">
					<input type="hidden" id="costControlAudit_postType" name="costControlAudit_postType" value="${MANAGER }" >
					<img class="" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			 <div class="form-group col-md-6" style="display:none">
				<label class="control-label signature-tool" for="costContractPrincipal">造价合同处</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="costContractPrincipal" name="costContractPrincipal" data-parsley-required="true">
					<input type="hidden" id="costContractPrincipal_postType" name="costContractPrincipal_postType" value="${SETTLEMENT_CLERK }" >
					<img class="" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			 <div class="form-group col-md-6"style="display:none">
				<label class="control-label signature-tool" for="subBudgeter">编制人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="subBudgeter" name="subBudgeter" data-parsley-required="true">
					<input type="hidden" id="subBudgeter_postType" name="subBudgeter_postType" value="${SUB_BUDGETER }" >
					<img class="" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="endDeclaraDate">终审日期</label>
		        <div>
		            <input type="text" class="form-control input-sm datepicker-default field-editable" id="endDeclaraDate" name="endDeclaraDate" data-parsley-required="true"/>
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
    $("#settlementAuditDoneform").toggleEditState(false).styleFit();
    //按钮隐藏
    $(".editbtn").addClass("hidden");
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //点击保存
    $(".auditSaveBtn").on("click",function(){
    	if($("#quantitiesTotal").val()!==$("#init_quantitiesTotal").val()){
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
            if($("#settlementAuditDoneform").parsley().isValid()){
            	//json字符串
            	var data=$("#settlementAuditDoneform").serializeJson();
            	$.ajax({
                    type: 'POST',
                    url: 'settlementAuditDone/saveAuditDone',
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(data),
                    success: function (data) {
                    	var content = "数据保存成功！";
                    	if(data === "false"){
                    		content = "数据保存失败！";
                    	}else if(data === "true"){
                    		$("#settlementAuditDoneform").formReset();
                    		$("#settlementAuditDoneTable").cgetData();  //列表重新加载
                    		 $(".editbtn").addClass("hidden");
                    		 $("#settlementAuditDoneform").toggleEditState(false,dataBack);
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
                        console.warn("计划签订区记录保存失败！");
                    }
                });
            	//如果通过验证, 则移除验证UI
            	$("#settlementAuditDoneform").parsley().reset();
            }else{
            	//如果未通过验证, 则加载验证UI
            	$("#settlementAuditDoneform").parsley().validate();
            }
    	}
    	
        
    });
    //签字隐藏叉号图片
    $(".fieldPrincipal-a").handleSignature();
    //详述
	trSData.t&&trSData.t.cgetDetail('settlementAuditDoneform','settlementAuditDone/auditDoneDetail','',tableCallBack);
	// 签名不可编辑
    $(".nosign").toggleSign(false);
	
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#settlementAuditDoneform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 $(".right-btn").addClass("hidden");
    	 $("#settlementAuditDoneTable").cgetData("",dataBack);  //列表重新加载
    });
    $("#cost1,#cost2,#cost2,#cost3,#cost4").on("change",function(){
    	$("#endDeclarationCost").val((new Number($("#quantitiesTotal").val())+new Number($("#purStuCost").val())+new Number($("#cost1").val())+new Number($("#cost2").val())+new Number($("#cost3").val())+new Number($("#cost4").val())).toFixed(2));
    	//计算核减
    	$("#endAuditMinusCost").val(new Number($("#firstDeclarationCost").val())-(new Number($("#endDeclarationCost").val())).toFixed(2));
    	if($("#endAuditMinusCost").val()==0){
    		$("#endAuditMinusRate").val($("#endAuditMinusCost").val());
    	}else{
    		$("#endAuditMinusRate").val(((new Number($("#endAuditMinusCost").val())/new Number($("#firstDeclarationCost").val()))*100).toFixed(2)+''+'%');
    	}
	});
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_1");
        var signatures2 = $("#signBtn_2");
        var signatures3 = $("#signBtn_3");
        signatures.handleSignature();
        signatures2.handleSignature();
        signatures3.handleSignature();
    });
    
	</script>
<!-- ================== END PAGE LEVEL JS ================== -->