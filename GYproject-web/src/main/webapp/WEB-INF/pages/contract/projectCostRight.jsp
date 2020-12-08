<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn hidden">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
		<!-- 增加表单 -->
		<input type="hidden" name="customerServiceCenter" id="customerServiceCenter"  value="${customerServiceCenter}"/>
		<form class="form-horizontal" id="sureBudgetForm"  data-parsley-validate="true" action="">
			<input type="hidden" name="projId" id="projId"/>
			<input type="hidden" name="stepId" id="stepId" value="${thisStepId}"/>
			<input type="hidden" name="projCostConfig" id="projCostConfig"/>
			<input type="hidden"id="deptDivide"name="deptDivide" value="">
			<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
			<div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
	            </div>
            </div>
            <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable" name="projectTypeDes"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable" name="contributionModeDes"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable" name="deptName"/>
		        </div>
		    </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="budgetTotalCost">总造价</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-not-editable cost-i fixed-number text-right"  id="budgetTotalCost" name="budgetTotalCost" data-parsley-maxlength="16"  data-parsley-type="number" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6">
				<label class="control-label" for="storeCost">工程费</label>
				<div>
					<input type="text" class="form-control input-sm  fixed-number field-not-editable text-right" data-parsley-min="0.00" data-parsley-maxlength="16" id="storeCost" name="storeCost" value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 clearboth">
	            <label class="control-label" for="materialCost">主材费</label>
	            <div>
	           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="materialCost" name="materialCost" value=""/>
	             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
	        </div> 
	        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	        	<label class="control-label" for="suCost">监理费</label>
	            <div>
	            <input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="suCost" name="suCost" value="" data-parsley-maxlength="16"  data-parsley-type="number"/>
	            <a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
	        </div>
	       <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	        	<label class="control-label" for="inspectionCost">带气作业费</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-not-editable fixed-number text-right"  id="inspectionCost" name="inspectionCost" value="" data-parsley-maxlength="16"  data-parsley-type="number"/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
	        </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="gasTimes">带气次数</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="6" id="gasTimes" name="gasTimes" value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">次</a>
				</div>
			</div>
	        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	            <label class="control-label" for="designCost">设计费</label>
	            <div>
	           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="designCost" name="designCost" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
	        </div>	
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
	             <label class="control-label" for="unforeseenCost">不可预见费</label>
	             <div>
	           		<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="unforeseenCost" name="unforeseenCost" value=""/>
	             	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
	        </div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="euAmount">报警器费用</label>
				<div>
					<input type="text" class="form-control input-sm fixed-number field-not-editable text-right" data-parsley-type="number" data-parsley-maxlength="16" data-parsley-min="0.00"  id="euAmount" name="euAmount" value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">元</a>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 col-sm-6 hidden">
	        	<label class="control-label" for="">是否退单</label>
	    		<div>
		            <label>
		              	<input type="radio" class="field-editable"  name="isBack"  value="1" /> 是
		            </label>
		            <label>
		              	<input type="radio" class="field-editable"  name="isBack"  value="0" checked/> 否
		            </label>
	        	</div>
			</div>
	        <div class="form-group col-md-6 back-hid clearboth">
				<label class="control-label " for="confirmTotalCost">确定造价</label>
	            <div>
	            	<input type="text" class="form-control input-sm field-editable fixed-number " data-parsley-maxlength="16"  data-parsley-type="number" id="confirmTotalCost" name="confirmTotalCost"  data-parsley-required="true" value=""/>
	            	<a href="javascript:;" class="btn btn-sm btn-default">元</a>
	            </div>
			</div>
			<div class="form-group col-md-6 back-hid">
	            <label class="control-label" for="changeReason">变动原因</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="changeReason"   name="changeReason"  >
		        	  <option value=""></option>
		 		      	<c:forEach var="enums" items="${changeReasonEnum}">
							<option value="${enums.supSql}" >${enums.remark}</option>
	                	</c:forEach>
		            </select>
		        </div>
			</div> 
			<div class="form-group col-md-12 hidden back-hid projCostAuditType auditReason clearboth">
	            <label class="control-label" for="projCostAuditType">审批范围</label>
		        <div>
		        	<select class="form-control input-sm field-editable" id="projCostAuditType"   name="projCostAuditType"  >
		        	  <option value=""></option>
		 		      	<c:forEach var="enums" items="${auditRange}">
							<option value="${enums.supSql}">${enums.remark}</option>
	                	</c:forEach>
		            </select>
		        </div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 auditReason">
				<label class="control-label" for="reduceGasTimes">减免次数</label>
				<div>
					<input type="text" class="form-control input-sm field-editable text-right" onkeyup="calculationCost(this.value,this.id)" data-parsley-type="number" data-parsley-maxlength="6" id="reduceGasTimes" name="reduceGasTimes" value=""/>
					<a href="javascript:;" class="btn btn-sm btn-default">次</a>
				</div>
			</div>
			<%--<div class="form-group col-md-6 backReason hidden">
		        <label class="control-label" for="">退单原因</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="backReason"  name="backReason" data-parsley-required="true" >
		                <option value=""></option>
		                <c:forEach var="enums" items="${backReason}">
							   	<option value="${enums.value}">${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
		    </div>--%>
			<div class="form-group col-md-6 backRemarks hidden">
				<label class="control-label" for="backRemarks">退单备注</label>
				<div>
					<input type="text" class="form-control input-sm field-editable" id="backRemarks"  name="backRemarks" data-parsley-required="true" data-parsley-maxlength="200" />

				</div>
			</div>
			<div class="form-group col-md-12 back-hid">
	            <label class="control-label" for="costRemark">造价备注</label>
		        <div>
		        	<textarea class="form-control input-sm field-editable" rows="2" id="costRemark"   name="costRemark"  data-parsley-required="true" ></textarea>
		        </div>
			</div>
		</form>
	</div>
	<div class="clearboth form-box m-t-5">
		<form id="scaleTableForm" data-parsley-validate="true">
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
    $("#sureBudgetForm").toggleEditState(false);
    //表单样式适应
    $("#sureBudgetForm").styleFit();
    $('.adjustMethod').addClass('hidden');
    
    //步骤
    //$("#stepId").val(getStepId());
  	//查右侧工程详述
  	trSData.t&&trSData.t.cgetDetail('sureBudgetForm','projectCost/viewCost?menuId='+menuId,'',sureBudgetBack); 
 
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    		
    	var confirmcost = $("#confirmTotalCost").val();
    	var budgecost = $("#budgetTotalCost").val();
    	//如果确定造价与工程总造价不同
		if(confirmcost != budgecost && confirmcost != ''){
			//添加变更结果验证
			$("#sureBudgetForm select[id='changeReason']").attr("data-parsley-required",true);
			if($("#deptDivide").val()==$("#customerServiceCenter").val() || $("#projCostConfig").val()!=''){
				$("#sureBudgetForm select[id='projCostAuditType']").attr("data-parsley-required",false);
			}else{
				$("#sureBudgetForm select[id='projCostAuditType']").attr("data-parsley-required",true);
			}
		}else{
			$("#sureBudgetForm select[id='changeReason']").attr("data-parsley-required",false);
			$("#sureBudgetForm select[id='projCostAuditType']").attr("data-parsley-required",false);
		}
    	if($("#sureBudgetForm").parsley().isValid()&&$("#scaleTableForm").parsley().isValid()){
    		//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    		var data=$("#sureBudgetForm").serializeJson();
    		if(data.isBack === "1"){
    			var popoptions = {
	       				title: '提示',
	       				content: '您确定退单吗？',
	       				accept: backDone1
	       		};
	       		$("body").cgetPopup(popoptions);
    		}else{
           		var tipsHtml="<span style='background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;'>造价审核</span>"
    			var popoptions = {
    	   				title: '提示',
                    	content: "确认后,将推送到下一步->"+tipsHtml+", 您确定要提交吗？",
                    accept: backDone1
    	   		};
    	   		$("body").cgetPopup(popoptions);
    		}
    		
    	}else{
    		//如果未通过验证, 则加载验证UI
        	$("#sureBudgetForm").parsley().validate();
        	$("#scaleTableForm").parsley().validate();
    	}
    }); 
    
    var getLtype = function(){
    	var projLtype = $("input[name='projLtype']:checked");
    	var projLtypeId="";
    	projLtype.each(function(){
    	    projLtypeId = projLtypeId +$(this).val()+",";
    	});
    	if(projLtypeId!==""){
    		projLtypeId = projLtypeId.substring(0,projLtypeId.length-1); 
    	}else{
    		projLtypeId = '-1';
    	}
    	return projLtypeId;
    };
    //去掉用气规模
    var backDone1 = function(){
    	//加遮罩
    	$(".infodetails").loadMask("正在保存...", 1, 0.5);
    	var viewdata=$("#sureBudgetForm").serializeJson();
    	//有配置则去掉
    	if($("#projCostConfig").val() !=null && $("#projCostConfig").val() !=""){
    		viewdata.projCostAuditType='';
    	}
		$.ajax({
            type: 'POST',
            url: 'projectCost/saveProject',
            contentType: 'application/json;charset=UTF-8',
            dataType:"json",
            data: JSON.stringify(viewdata),
            beforeSend: function () {
	              // 禁用按钮防止重复提交
                $(".saveBtn").attr({ disabled: "disabled" });
	        },
            complete: function () {
            	//去掉禁用
            	$(".saveBtn").removeAttr("disabled");
            },
            success: function (data) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
            	var content = "数据保存成功！";
            	if(data.ret_message === "false"){
            		content = "数据保存失败！";
            	}else if(data.ret_message === "true"){
            		$("#sureBudgetForm")[0].reset();
            		$("#projectCostTable").cgetData(true,projectCoseCallBack);  //列表重新加载
            	}else{//接口失败
            		alertInfo(data.ret_message);
            	}
            	var myoptions = {
                    	title: "提示信息",
                    	content: content,
                    	accept: popClose,
                    	chide: true,
                    	icon: "fa-check-circle",
                    	newpop:"new"
                }
                $("body").cgetPopup(myoptions);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	//取消遮罩
            	$(".infodetails").hideMask();	
                console.warn("造价确认记录保存失败！");
            }
        });
    	//如果通过验证, 则移除验证UI
    	$("#sureBudgetForm").parsley().reset();
    	//$("#scaleTableForm").parsley().reset();
    }
    //弃
	var backDone = function(){
		var projLtypeId = getLtype();
		//console.info('projLtypeId1....'+projLtypeId);
		$(".projLtypeId").val(projLtypeId);
		var tableform = $("#scaleTableForm").clone();
		tableform = tableform.find("tr.child").prev().children("td[style]").remove().end().end().end();
		var mdata = tableform.getDTFormData();
		var viewdata=$("#sureBudgetForm").serializeJson();
		//选中的工程规模明细
       	var resultData = [];
       	for(var i=0;i<mdata.length;i++){
       		var data = mdata[i]; 
       		if(data.scaleId !== undefined){
       			data.projNo = $("#projNo").val();
       			data.projId = $("#projId").val();
       			if(data.tonnage!=="" || data.searNum!=="" || data.num!=="" || data.houseNum!=="" || data.gasConsumption!=="" || data.scaleAmount!==""){
       				resultData.push(data);
       			}
       		}
       	}
       	if(resultData.length<1){
       		alertInfo("请填写用气规模明细记录！");
       		return false;
       	}
       	//调用方法，用户选择用气规模大类是否与明细相对应
       	var typeDes = getInfo(projLtypeId,resultData);
		
       	if(typeDes!== ''){
			alertInfo(typeDes);
	   	}else{
	   		viewdata.scaleDetails = resultData;
	   		if($(""))
			$.ajax({
	            type: 'POST',
	            url: 'projectCost/saveProject',
	            contentType: 'application/json;charset=UTF-8',
	            data: JSON.stringify(viewdata),
	            success: function (data) {
	            	var content = "数据保存成功！";
	            	if(data === "false"){
	            		content = "数据保存失败！";
	            	}else if(data === "true"){
	            		$("#sureBudgetForm")[0].reset();
	            		$("#projectCostTable").cgetData(true,projectCoseCallBack);  //列表重新加载
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
	                console.warn("造价确认记录保存失败！");
	            }
	        });
	   	}
		//如果通过验证, 则移除验证UI
    	$("#sureBudgetForm").parsley().reset();
    	$("#scaleTableForm").parsley().reset();
	} 
//     var saveProjectBack=function(){
//     	//清空输入信息
//     	$("#sureBudgetForm input").val("");
//     	//列表重新加载
//     	$("#projectCostTable").cgetData();
//     	//不可编辑
//     	$("#sureBudgetForm").toggleEditState(false);
//     	//隐藏按钮
//     	$(".editbtn").addClass("hidden"); 
    	
//     }
    
   /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
   		$("#sureBudgetForm")[0].reset();
   	    $('#projectCostTable').cgetData(true,projectCoseCallBack);
   	    $("#backReason").val('');
   	    if($("#contractType").val()=="2"){
   	    	//暂控
   	    	$('.adjustMethod').removeClass('hidden');
    		$('.riskRange').addClass('hidden');
    		$('.adjustmentMethod').addClass('hidden');
   	    }else{
   	    	//包干
   	    	$('.adjustMethod').addClass('hidden');
    		$('.riskRange').removeClass('hidden');
    		$('.adjustmentMethod').removeClass('hidden');
   	    }
    }); 
    //写着的时候注意一点，查询详述的时候绑也定了一个一样监听，但还是有些不一样的方法，没敢改
    $('[name="isBack"]').on("change",function(){
    	$("#sureBudgetForm select[id='changeReason']").attr("data-parsley-required",false);
    	if($('[name="isBack"]:checked').val()==1){
    		$(".back-hid").addClass("hidden");
    		$(".backReason").removeClass("hidden");
    		$("#confirmTotalCost,changeReason").val('');
    		$('.contractType').addClass('hidden');
    		$('#contractType').val('');
    	}else{
    		$(".back-hid").removeClass("hidden");
    		$(".backReason").addClass("hidden");
    		$("#backReason").val('');
            $('.backRemarks').addClass('hidden');
            $('.backRemarks').val("");
    		$('.contractType').removeClass('hidden');
    	}
    	//工程造价审核范围
    	costConfirm();
    	//表单样式适应
        $("#sureBudgetForm").styleFit();
    });

    //退单原因为其他时写退单备注
    $("#backReason").change(function(){
        if($(this).val()=='5'){//退单
            $('.backRemarks').removeClass('hidden');
        }else{
            $('.backRemarks').addClass('hidden');
            $('.backRemarks').val("");
        }
    });

    //合同类型选择
    $('[name="contractType"]').on("change",function(){
    	//暂控
    	if($(this).val()=="2"){
    		$('.adjustMethod').removeClass('hidden');
    		$('.riskRange').addClass('hidden');
    		$('#note').val('');
    		$('.adjustmentMethod').addClass('hidden');
    		$('#adjustmentMethod').val('');
    	}else{
    		//包干
    		$('.adjustMethod').addClass('hidden');
    		$('.riskRange').removeClass('hidden');
    		$('.adjustmentMethod').removeClass('hidden');
    		$('#adjustMethod').val('');
    	}
    	//表单样式适应
        $("#sureBudgetForm").styleFit();
    });
    
    //工程规模切换
     $("input[name='projLtype']").on("change",function(){
    	 detailSearchData.projId="-1";
     	$('#scaleTable').cgetData(true,qback);
    });
    
     var qback=function(){
     	var projLtypeId = $("input[name='projLtype']:checked").val();
 		//选中
 		detailSearchData.projLtypeId = projLtypeId;
     	detailSearchData.projId = $("#projId").val();
     	if($.fn.DataTable.isDataTable('#scaleTable')){
     		$.ajax({
                 type: 'POST',
                 url: 'projectAccept/queryScaleDetailAjax',
                 contentType: "application/json;charset=UTF-8",
                 data: JSON.stringify(detailSearchData),
                 success: function (data) {
                 	var len = JSON.parse(data).length;
                 	for(var m=0;m<len;m++){
                     	var rows = [];
             			if(JSON.parse(data)[m]!=""){
                 			var json = {};
                 			json = JSON.parse(data)[m];
                 			rows.push(json);
             		    }
             			$('#scaleTable').DataTable().rows.add(rows).draw();
             	    }
                 	scaleTableCallBack2();
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.warn("ajax queryScaleDetail...fail");
                 }
             }); 
     	}
     }

     var scaleTableCallBack2 = function(){
     	if(detailFlag){
 	    	 $('#scaleTableForm').toggleEditState(true);
     	}else{
     		$('#scaleTableForm').toggleEditState(false);
     	}
     }
    
     /*
 	* 该方法用于判断用气规模大类与规模明细是否对应存在，例如：选中公用、车用，则必须填入了公用、车用明细的记录
 	*/
 	var getInfo = function(projLtypeId,resultData){
 		var typeIds = projLtypeId.split(",");//12
        	for(var i=0;i<resultData.length;i++){
        		for(var k=0;k<typeIds.length;k++){
        			if(typeIds[k] === resultData[i].projLtypeId){
        				//从第k个位置删除1个
        				typeIds.splice(k, 1);  
        			}
        		}
	      		if(typeIds.length<1){
	      			break;
	      		}
        	}
        	
        	if(typeIds.length>0){
        		var typeDes = '';
        		for(var n=0;n<typeIds.length;n++){
        			$.each($('[name="projLtype"]:checked'),function(i, el){
        			    //console.log($(el).val()+"......"+$(el).attr("data-text"));
            		    if($(el).val() === typeIds[n]){
            		    	typeDes = typeDes+$(el).attr("data-text");
            		    	if(n<typeIds.length-1){
            		    		typeDes = typeDes+'、'
            		    	}
            		    }
            		})
	        	}
	 	       	typeDes = "请输入用气规模为"+typeDes+"的明细记录！";
	 	        return typeDes;
        	}
        	return '';
 	}
    
     /*确认造价改变时
     *如果确认造价与预算造价(总造价)不一致时须审批
     */
     
     $("#confirmTotalCost").on("change",function(){
    	 costConfirm();
     });
     var costConfirm = function(){
    	 var isBack = $("input[name='isBack']:checked").val();
    	 var confirmTotalCost=$("#confirmTotalCost").val();
    	 var budgetTotalCost = $("#budgetTotalCost").val();
    	 if(budgetTotalCost!=confirmTotalCost && isBack=='0'){
    		 $(".projCostAuditType").removeClass("hidden");
    	 }else{
    		 $(".projCostAuditType").addClass("hidden");
    		 $("#projCostAuditType").val('');
    	 }
    	 if($("#deptDivide").val()==$("#customerServiceCenter").val()){
   			$(".auditReason").addClass("hidden");
   		 }else{
   		 	$(".auditReason").removeClass("hidden");
   		 }
    	 
     }
     //变动原因
     $('#changeReason').on("change",function(){
    	 if($(this).val()=='5'){
    		 $(".remark").removeClass("hidden");
    	 }else{
    		 $(".remark").addClass("hidden");
    	 }
     })

   /* $("#projCostAuditType,#reduceGasTimes").on("change",function(){
            var reduceGasTimes = new Number($("#reduceGasTimes").val() || 0);
        if($("#projCostAuditType").val()=="1"||$("#projCostAuditType").val()=="2"){
            $("#confirmTotalCost").val((new Number($("#budgetTotalCost").val()-
				(new Number($("#inspectionCost").val())*reduceGasTimes)- new Number($("#unforeseenCost").val()))).toFixed(2));
		}else{
            $("#confirmTotalCost").val($("#budgetTotalCost").val());
		}
    });*/

     /* 	输入数字校验 */ 
     $('.fixed-number').on('keyup', function(){
     	  $(this).parsley().validate();
     	});


     function calculationCost(value,id) {
         var budgetTotalCost= $("#budgetTotalCost").val();
         var remissionNumber=$("#inspectionCost").val()* value;
         if (budgetTotalCost<remissionNumber){
             alertInfo("减免费大于总造价");
             $("#"+id).val("");
             $("#budgetTotalCost").val(budgetTotalCost);
             return;
		 }else {
             $("#confirmTotalCost").val(budgetTotalCost-remissionNumber);

		 }

     }

</script>
<!-- ================== END PAGE LEVEL JS ================== -->