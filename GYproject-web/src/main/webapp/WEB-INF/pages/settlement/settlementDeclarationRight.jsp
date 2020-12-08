<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 acceptSaveBtn" >保存</a>
    	<a href="javascript:;" class="btn btn-info btn-sm m-l-5 pushBtn" >推送</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="loginPost" value="${loginPost }"/>
    	<form class="form-horizontal" id="planEstablishDetailform" action="" data-parsley-validate="true">
    		 <input type="hidden" class="form-control field-editable" id="projId" name="projId" value=""/>
    		 <input type="hidden" class="form-control field-editable" id="corpId" name="corpId" value=""/>
    		 <input type="hidden" class="form-control field-editable" id="deptId" name="deptId" value=""/>
    		 <input type="hidden" class="form-control field-editable" id="sdId" name="sdId" value=""/>
    		 <input type="hidden"  id="isPrint" name="isPrint" value=""/>
    		 <input type="hidden"  id="pushStatus" name="pushStatus" value=""/>
    		 <input type="hidden"  id="isHaveCM" name="isHaveCM" value=""/>
    		 <input type="hidden"  id="isHaveEngi" name="isHaveEngi" value=""/>
    		 <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
    		 <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="500" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
	            </div>
            </div>
            
            <div class="form-group col-md-6 col-sm-12">
            	<!-- 新加字段 -->
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value=""/>
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
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100" value=""/>
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
		    <div class="form-group col-md-12">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName" data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 cuNameDiv">
		        <label class="control-label" for="cuName">施工单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
		        </div>
		    </div>
		    <!-- <div class="form-group col-md-6 ">
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
		     <!-- <div class="form-group col-md-6 ">
		    	<label class="control-label" for="sscNo">协议编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="sscNo" name="sscNo"/>
		        </div>
		    </div> -->
		    <div class="form-group col-md-6">
		        <label class="control-label">报审日期</label>
		        <div>
		        	<input type="text" class="form-control field-editable input-sm datepicker-default timestamp "  id="ocoDate" name="ocoDate"  data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="quantitiesTotal">合计</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable text-right fixed-number"  id="quantitiesTotal" name="quantitiesTotal"  data-parsley-type="number" value=""/>
		       		<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="sendDeclarationCost">报审金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right fixed-number"   id="sendDeclarationCost" name="sendDeclarationCost" data-parsley-maxlength="16"  data-parsley-type="number" data-parsley-required="true" value=""/>
		        	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="materialsCost">主材费</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable text-right fixed-number"  id="materialsCost" name="materialsCost" data-parsley-maxlength="16"  data-parsley-type="number" data-parsley-required="true" value=""/>
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
					<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="auxiliaryMaterialAmount" name="auxiliaryMaterialAmount" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="managementCost">协调费</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="managementCost" name="managementCost" data-parsley-type="number" data-parsley-maxlength="16"/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
		     <div class="form-group col-md-6">
				<label class="control-label" for="recoveryCost">恢复费</label>
				<div>
					<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="recoveryCost" name="recoveryCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="jeevesCost">占道费</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="jeevesCost" name="jeevesCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="compensateCost">赔偿费</label>
				<div>
					<input type="text" class=" form-control input-sm field-editable fixed-number text-right" id="compensateCost" name="compensateCost" data-parsley-type="number" data-parsley-maxlength="16"/>
				<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			
		    <div class="form-group col-md-6 clearboth allSign budgetMember ">
				<label class="control-label signature-tool sign-require" for="compilerSign">编制人签字</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="compilerSign" name="compilerSign" data-parsley-required="true">
					<input type="hidden" id="compilerSign_postType" name="compilerSign_postType" value="${compilerSignPost }" >
					<img class="" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 ">
		        <label class="control-label" for="compiler">编制人</label>
		        <div>
		        	<!-- 编制人ID -->
		        	<input type="hidden" id="compilerId" name="compilerId"/>
		            <input type="text" class="form-control input-sm field-not-editable"  id="compiler" name="compiler" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="compilerPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="compilerPhone" name="compilerPhone" data-parsley-maxlength="13" value=""/>
		        </div>
		    </div>
		    <!-- 去掉post签字 -->
		    <%-- <div class="form-group col-md-6 clearboth allSign construction minister">
				<label class="control-label signature-tool " for="cuAudit">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuAudit" name="cuAudit" data-parsley-required="true">
					<input type="hidden" id="cuAudit_postType" name="cuAudit_postType" value="${cuAuditPost }" >
					<img class="cuAudit" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6 allSign cuPm viceGeneralManager">
				<label class="control-label signature-tool " for="cuPrincipal">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPrincipal" name="cuPrincipal" data-parsley-required="true">
					<input type="hidden" id="cuPrincipal_postType" name="cuPrincipal_postType" value="${cuPrincipalPost }" >
					<img class="cuPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div> --%>
		    <div class="form-group col-md-6 clearboth">
				<label class="control-label signature-tool " for="cuAudit">审核人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuAudit" name="cuAudit" data-parsley-required="true">
					<input type="hidden" id="cuAudit_postType" name="cuAudit_postType" value="${cuAuditPost }" >
					<img class="cuAudit" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label signature-tool " for="cuPrincipal">负责人</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_3"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPrincipal" name="cuPrincipal" data-parsley-required="true">
					<input type="hidden" id="cuPrincipal_postType" name="cuPrincipal_postType" value="${cuPrincipalPost }" >
					<img class="cuPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div>
			<%-- <div class="form-group col-md-12 clearboth">
		        <label class="control-label" for="custName">建设单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
				<label class="control-label signature-tool sign-require" for="cuPrincipal">现场代表</label>
				<div>
					<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_4"><i class="fa fa-pencil"></i></a>
					<input type="hidden" class="sign-data-input" id="cuPrincipal" name="cuPrincipal" data-parsley-required="true">
					<input type="hidden" id="cuPrincipal_postType" name="cuPrincipal_postType" value="${cuPrincipalPost }" >
					<img class="cuPrincipal" alt="" src="" style="height: 30px">
					<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
				</div>
			</div> --%>
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
    trSData.t&&trSData.t.cgetDetail('planEstablishDetailform','settlementDeclaration/viewCost','',budgetCallback);
    $("#qualitiesForm").toggleEditState(false).styleFit();
    var firstInit = function(){
    	if($.fn.DataTable.isDataTable('#qualitiesTable')){
    		dataBack();
    	}else{
    		qualitiesInit();
    	}	
    }();
    
  	//参数传false时，表单不可编辑
    $(".editbtn").addClass("hidden");
    //表单样式适应
    $("#planEstablishDetailform").toggleEditState(false).styleFit();
    if(!trSData.settlementDeclarationTable.t){
    	 $(".updateBtn").addClass("hidden");
    }else{
    	 $(".updateBtn").removeClass("hidden");
    }
    
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
    	 $("#planEstablishDetailform").toggleEditState(false);
    	 $(".editbtn").addClass("hidden");
    	 $(".right-btn").addClass("hidden");
    	 $("#settlementDeclarationTable").cgetData("",dataBack);  //列表重新加载
    	 $(".field-editable").val('');
    	 $(".clear-sign").click();
    });
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        var signatures = $("#signBtn_1");
        var signBtn_2 = $("#signBtn_2");
        var signBtn_3 = $("#signBtn_3");
        var signBtn_4 = $("#signBtn_4");
        signatures.handleSignature(false);
        signBtn_2.handleSignature(false);
        signBtn_3.handleSignature(false);
        signBtn_4.handleSignature(false);
    });
    
    $(".acceptSaveBtn").on("click",function(){
    	var viewform = $("#planEstablishDetailform");
    	console.info($("#quantitiesTotal").val());
    	console.info($("#init_quantitiesTotal").val());
    	if($("#isHaveEngi").val()>0){
	        	$("body").cgetPopup({
            	title: "提示信息",
            	content: "还有没审核完成的签证，请先完成签证!",
            	accept: popClose,
            	chide: true,
            	icon: "fa-warning",
            	newpop: 'new'
            });
        	return false;
	     }
    	/* if($("#quantitiesTotal").val()=='0.00'){
    		var myoptions = {
                   	title: "提示信息",
                   	content: "请导入报审工程量",
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle"
               }
               $("body").cgetPopup(myoptions);
    	}else if($("#quantitiesTotal").val()!==$("#init_quantitiesTotal").val()){
    			var myoptions = {
                   	title: "提示信息",
                   	content: "请先保存报审工程量",
                   	accept: popClose,
                   	chide: true,
                   	icon: "fa-check-circle"
              	 }
               	$("body").cgetPopup(myoptions);
    	
    	}else{ */
    		if(viewform.parsley().isValid()){
    			var len=$("#qualitiesTable").DataTable().rows().data().length;
    			var t = $("#qualitiesTable"),
    			flag = true,
    			trs = $('#qualitiesTable tbody tr');
    			//验证必签签字是否已签
    	        var signtools =$('#planEstablishDetailform').find(".signature-tool.sign-require"),
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
    	        
    	        //验证是否填写0
    	        if(len>0){
    	        	for(var i=0,l=trs.length; i<l; i++){
    	    			var v = trs.eq(i).find("input.numbers").val();
    	    			/*if(v && v !== "0") {
    	    				flag = true;
    	    				break;
    	    			}*/
    	    			if(isNaN(v) ||  v == "0") {
    	    				console.info('v...'+v);
    	    				alertInfo("请输入正确的工程量！");
    	    				return false;
    	    			}	
    	            };
    	        }
    	        
    	        /* if($("#sqStatus").val()=='1'){//施工预算工程量,先保存工程量
    	        	alertInfo("请先保存报审工程量!")
    	        	return false;
    	        } */
    	        
        		var viewdata = $('#planEstablishDetailform').serializeJson();
        		viewform.parent().parent().loadMask("正在保存。。。",1,0.5);
        		$.ajax({
        			type : "POST",
        			url : "settlementDeclaration/totalSave",
        			contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(viewdata),
                    success : function(data){
                    	viewform.parent().parent().hideMask();
                    	var content = "数据保存成功！";
                    	if(data === "false"){
                    		content = "数据保存失败！";
                    	}else if(data === "exist"){
                    		content = "请先保存报审工程量";
                    	}else if(data === "true"){
                    		$(".editbtn").addClass("hidden");
                    		$("#planEstablishDetailform").formReset();
                    		 $('.clear-sign').click();
                    		 $("#planEstablishDetailform").toggleEditState(false);
                    		$("#settlementDeclarationTable").cgetData(true,dataBack);//列表重新加载
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
                    error : function(){
                    	viewform.parent().parent().hideMask();
                    	console.warn("送审区记录保存失败 ！");
                    }
        		});
        	}else{
        		//如果未通过验证，则加载验证UI
        		viewform.parsley().validate();
        	}
    	/* } */
    	
    
    });
    //推送事件 
    $(".pushBtn").on("click",function(){
    	var t=$("#planEstablishDetailform");
    	//开启表单验证
    	if (typeof t.parsley === 'function' && t.parsley().isValid() || typeof t.parsley !== 'function') {
    		var signtools =$('#planEstablishDetailform').find(".signature-tool.sign-require"),
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
 	        if($("#isHaveCM").val()>0){
 	        	$("body").cgetPopup({
	            	title: "提示信息",
	            	content: "还有没签订补充协议的用户变更，不能进行推送!",
	            	accept: popClose,
	            	chide: true,
	            	icon: "fa-warning",
	            	newpop: 'new'
	            });
	        	return false;
 	        }
	    	$("body").cgetPopup({
	           	title: "提示信息",
	           	content: "确定推送结算报审信息吗？",
	           	accept: pushBtnBack,
	           //	chide: true,
	           	icon: "fa-warning",
	          // 	newpop: 'new'
	           });
	     //如果通过验证, 则移除验证UI
	    t.parsley().validate();
	   } else {
	        //如果未通过验证, 则加载验证UI
	        t.parsley().validate();
	   };
    	
    });
    
    var pushBtnBack = function(){
    	var sdId = $('#sdId').val();
		   	if(sdId){
		   		var dataJson = $('#planEstablishDetailform').serializeJson();
		   		$.ajax({
		   			type : "POST",
		   			url : "settlementDeclaration/pushSettlement",
		   			contentType: "application/json;charset=UTF-8",
		            data: JSON.stringify(dataJson),
		            success : function(data){
		               	var content = "数据推送成功！";
		               	if(data === "false"){
		               		content = "数据推送失败！";
		               	}else if(data==="signatureIsNull"){
		               		content = "签字未完成！";
		               	}else{
		               		$(".editbtn").addClass("hidden");
		               		$("#planEstablishDetailform").formReset();
		               		 $('.clear-sign').click();
		               		 $("#planEstablishDetailform").toggleEditState(false);
		               		$("#settlementDeclarationTable").cgetData(true,dataBack);//列表重新加载
		               	}
		               	var myoptions = {
		                          	title: "提示信息",
		                          	content: content,
		                          	accept: popClose,
		                          	chide: true,
		                          	icon: "fa-check-circle",
		                          	newpop: 'new'
		                      }
		                      $("body").cgetPopup(myoptions);
		               },
		               error : function(){
		               	console.warn("结算报审推送失败 ！");
		               }
		   		});
		   	}else{
		   		alertInfo("该工程还没有报审!");
		   	}
    }
   /*  $("#sale,#cost1,#cost2,#cost2,#cost3,#cost4,#quantitiesTotal").on("change",function(){
    	$("#purStuCost").val((new Number($("#sale").val())*parseFloat($("#purStuRate").val())*0.01).toFixed(2));
    	$("#sendDeclarationCost").val((new Number($("#quantitiesTotal").val())+new Number($("#purStuCost").val())+new Number($("#cost1").val())+new Number($("#cost2").val())+new Number($("#cost3").val())+new Number($("#cost4").val())).toFixed(2));
	}); */
	console.info("quantitiesTotal==="+$('#quantitiesTotal').val());
	 $("#quantitiesTotal").on("change",function(){
	    	$("#sendDeclarationCost").val(new Number($("#quantitiesTotal").val()).toFixed(2));
		});
	 /* 	输入数字校验 */ 
     $('.fixed-number').on('keyup', function(){
     	  $(this).parsley().validate();
     	}); 
</script>
<!-- ================== END PAGE LEVEL JS ================== -->