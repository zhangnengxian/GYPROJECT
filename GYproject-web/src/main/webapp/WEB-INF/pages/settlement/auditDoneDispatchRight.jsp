<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 dispatchBtn" >派工</a>
	</div>
	<div class="clearboth form-box">

	
		<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
		<form class="form-horizontal" id="doneDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" name="duId"  id="duId" value=""/>
			<input type="hidden"  name="deptDivide"  id="deptDivide" value=""/>
			<input type="hidden" name="designStationId"  id="designStationId" value=""/>
			<input type="hidden" name="unitId" id="unitId" value=""/>
			<input type="hidden" name="unitName" id="unitName" value=""/>
			
			<input type="hidden" class="form-control field-editable" id="projId" name="projId" value=""/>
    		 <input type="hidden" class="form-control field-editable" id="sdId" name="sdId" value=""/>
    		 <div class="form-group col-md-6 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 clearboth">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"/>
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
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		    	<label class="control-label" for="">工程规模</label>
		        <div>
		        	<textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="2" /></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
            	<!-- 新加字段 -->
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"/>
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
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="drawingNo">工程图号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="drawingNo" name="drawingNo"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		    	<label class="control-label" for="scNo">分合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable" id="scNo" name="scNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="cuName">报审单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="compiler">编制人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="compiler" name="compiler"/>
		        </div>
		    </div>
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="compilerPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="compilerPhone" name="compilerPhone"/>
		        </div>
		    </div>
		   <!--  <div class="form-group col-md-6 ">
		        <label class="control-label" for="cuPhone">联系方式</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPhone" name="cuPhone"/>
		        </div>
		    </div> -->
		   <div class="form-group col-md-6 ">
		        <label class="control-label" for="ocoDate">报审日期</label>
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
			 <div class="form-group col-md-6 ">
		        <label class="control-label" for="firstAuditDate">初审日期</label>
		        <div>
		            <input type="text" class="form-control  field-not-editable " id="firstAuditDate" name="firstAuditDate" />
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
        	<input type="hidden" name="surveyer" id="surveyer"/>
        	<input type="hidden" name="surveyerId" id="surveyerId"/>
        	<div class="form-group col-md-6">
		        <label class="control-label" for="">分包审核人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="budgeterAudit" name="budgeterAudit" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="">安装合同金额</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable fixed-number"  id="contractAmount" name="contractAmount" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		</form>
    </div>
	<hr style="width:100%;"/>
	<div id="settlementer" style="text-align: center"></div>
	<div>
    <h4 class="m-t-20"><strong>结算员</strong></h4>
    </div>
    <table id="designerTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th class="hidden"></th>
            	<th>名称</th>
                <th>待终审任务</th>
<!--                 <th>待设计任务</th> -->
                <!-- <th></th> -->
            </tr>
        </thead>
	</table>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#doneDispatchForm").toggleEditState(false).styleFit();
    
    //查右侧工程详述
    //trSData.t.cgetDetail('doneDispatchForm','doneDispatch/viewDispatchResult','',detailBack);
    trSData.t&&trSData.t.cgetDetail('doneDispatchForm','auditDoneDispatch/viewDispatchResult','',detailBack);
   
    //点击派工按钮
    $(".dispatchBtn").on("click",function(){
    	var radioVal = $("#doneDispatchForm input[type='radio']:checked").val();
    	if($("#doneDispatchForm").parsley().isValid()){
    		//if(radioVal=="1") {   
       		    var len=$("#designerTable").find("tr.selected").length;
       		    if(len>0){
       		    	var surveyer = $("#designerTable").find("tr.selected td:eq(1)").text();
       		    	var surveyerId = $("#designerTable").find("tr.selected td:eq(0)").text();
       		    	$("#surveyer").val(surveyer);//选择的设计人员
       		    	$('#surveyerId').val(surveyerId);
       		    	console.info(surveyer);
       		    	console.info(surveyerId);
       		        $("body").cgetPopup({
	                 	    title: "提示信息",
	                    	content: '确认要派工给 <i class="fa fa-user"></i> '+surveyer+" 吗？",
	                    	accept: function(){
	                 		var data=$("#doneDispatchForm").serializeJson();
	           	        	$.ajax({
	           	                type: 'POST',
	           	                url: 'auditDoneDispatch/updateProject',
	           	                contentType: "application/json;charset=UTF-8",
	           	                data: JSON.stringify(data),
	           	                success: function (data) {
	           	                	var content = "派工成功！";
	           	                	if(data === "false"){
	           	                		content = "派工失败！";
	           	                	}else if(data === "true"){
	           	                		$("#doneDispatchForm").formReset();
	           	                		$("#doneDispatchForm").toggleEditState();
// 	           	                		updateDesignerBack();
	           	                		$("input[name='depositGet'][value='0']").attr("checked","checked");
	           	                		$('#designerTable').cgetData();
	           	                	 	$("#doneDispatchTable").cgetData(true,doneDispatchCallBack);
	           	                	    $('#designerTable').cgetData(true);
	           	                	}
	           	                	var myoptions = {
	           	                        	title: "提示信息",
	           	                        	content: content,
	           	                        	accept: popClose,
	           	                        	chide: true,
	           	                        	newpop:'new',
	           	                        	icon: "fa-check-circle"
	           	                    }
	           	                    $("body").cgetPopup(myoptions);
	           	                },
	           	                error: function (jqXHR, textStatus, errorThrown) {
	           	                    console.warn("设计派遣区派工失败！");
	           	                }
	           	            }); 
	                 	},
	                 	icon: "fa-check-circle",
	                 	newpop: 'new'
                 });
       		    
       		    }else{					//未选设计人员
       		    	$("body").cgetPopup({
       					title: '提示',
       					content: '请选择结算员！',
       					accept: ensureDone
       		    	});
       		    }
        	//}else{   //未缴设计定金
           		/* $("body").cgetPopup({     
       				title: '提示',
       				content: '请缴纳设计定金！',
       				accept: sureDone
       	    	}); */
        	//}
    		$("#doneDispatchForm").parsley().reset();
    		deptcallback();
    	}else{
    		$("#doneDispatchForm").parsley().validate();
    	}  
    })
    
    
    //点击确定
    var sureDone=function(){}
    var ensureDone=function(){}
    
  	//选中设计
    $("#cuPop").off('click').on("click",function(){
    	var deptType = $(".deptType").val();
    	deptPopup({'unitName':'deptName','unitId':'deptId'},deptType,deptcallback);
  	});
    var deptcallback =function(){
    	console.info("==========deptcallback==========="+designerData.deptId);
    	designerData.deptId = $('#unitId').val();
    	//$('#designerTable').cgetData(true);
    };
    
    designertableinit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->