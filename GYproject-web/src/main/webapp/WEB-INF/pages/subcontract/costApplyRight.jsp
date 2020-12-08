<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn hidden">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<input type="hidden" name="loginUnit"  id="loginUnit" value="${loginInfo.deptName}"/>
		<input type="hidden" name="loginUnitId"  id="loginUnitId" value="${loginInfo.deptId}"/>
		<input type="hidden" name="loginName"  id="loginName" value="${loginInfo.staffName}"/>
		<input type="hidden" name="loginId"  id="loginId" value="${loginInfo.staffId}"/>
		
		<input type="hidden" name="loginUnitType"  id="loginUnitType" value="${loginUnitType}"/>
		<!-- 设计单位 -->
		<input type="hidden" name="designUnit"  id="designUnit" value="${designUnit}"/>
		<!-- 检测单位 -->
		<input type="hidden" name="checkUnit"  id="checkUnit" value="${checkUnit}"/>
		<!-- 监理单位 -->
		<input type="hidden" name="suUnit"  id="suUnit" value="${suUnit}"/>
		
		
		
		<input type="hidden" name="returnMessage"  id="returnMessage" />
		<input type="hidden" name="businessOrderId"  id="businessOrderId" class="accBusRecordId" value="-1"/>
		<form class="form-horizontal" id="costApplyForm" data-parsley-validate="true" action="costApply/savePaymentApply">
			<input type="hidden"  name="paId"  id="paId" value=""/>
			<input type="hidden"  name="corpId"  id="corpId" value=""/>
			<input type="hidden"  name="projId"  id="projId" value=""/>
			<input type="hidden"  name="applyDeptId"  id="applyDeptId" value=""/>
			<input type="hidden" name="orgId"  id="orgId" value=""/>
			<input type="hidden" name="isDispatch"  id="isDispatch" value=""/>
			<input type="hidden" name="applyReason"  id="applyReason" value=""/>
			<div class="form-group col-md-6">
            	<label class="control-label" for="applyNo">请款编号</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="applyNo" name="applyNo"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 noUser">
            	<label class="control-label" for="feeType">费用类型</label>
            	<div>
		    		<select class="form-control input-sm field-not-editable" id="feeType"  name="feeType"  >
		 		      	<option value=""></option>
		                <c:forEach var="enums" items="${feeType}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
        	</div>
			<div class="form-group col-md-6 col-sm-12">
            	<label class="control-label" for="applyDeptName">请款单位</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="applyDeptName" name="applyDeptName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
            	<label class="control-label" for="applyer">请款人</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="applyer" name="applyer"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6">
            	<label class="control-label" for="applyAmount">请款金额</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable  fixed-number text-right" id="applyAmount" name="applyAmount"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 auditUnit hidden">
            	<label class="control-label" for="auditUnit">审核部门</label>
            	<div >
                	<select class="form-control input-sm field-editable" id="auditUnit"  name="auditUnit"  >
		                <c:forEach var="enums" items="${auditUnit}">
							   	<option value="${enums.value}" >${enums.message}</option>
		                </c:forEach>
		             </select>
            	</div>
        	</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="applyer">代办人</label>
				<div >
					<input type="text" class="form-control input-sm field-not-editable" id="todoer" name="todoer"/>
				</div>
			</div>

			<div class="form-group col-md-12">
				<label class="control-label" for="applyRemark">请款备注</label>
				<div>
					<textarea class="form-control field-editable" name="applyRemark" id="applyRemark" rows="3" cols="" data-parsley-maxlength="500"></textarea>
				</div>
			</div>
		</form>
    </div>

    <table id="applyProjectTable" class="table table-hover table-bordered nowrap" width="100%">
        <thead>
            <tr>
            	<th></th>
            	<th>工程编号</th>
                <th>工程名称</th>
                <th>工程地点</th>
                <th>工程规模</th>
                <th>请款金额</th>
                <th>质保金</th>
                <th></th>
            </tr>
        </thead>
	</table>
</div>
<div class="clearboth p-t-15">
	<div >
		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
	</div>
	<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
		<thead>
		<tr>
			<th>审批日期</th>
			<th>审批结果</th>
			<th>审批意见</th>
			<th>审批人</th>
		</tr>
		</thead>
	</table>
</div>
<%--<div class="clearboth p-t-15"></div>--%>
<!-- end #content -->
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#costApplyForm").toggleEditState().styleFit();
    
  //查右侧详述
    trSData.t&&trSData.t.cgetDetail('costApplyForm','','',detailBack1);



     var detailBack1=function(data){
		Base.subimt("costApply/queryTodoer","POST",{businessOrderId:$('#paId').val()},function (data) {
			$('#todoer').val(data);
        });
		auditHistoryInit();
    } 

    
    tableInit();
	auditHistoryInit();

	//点击放弃
    $(".cancelBtn").off("click").on("click",function(){
    	//清空表单
    	$("#costApplyForm").formReset();
    	$("#costApplyForm").toggleEditState(false).styleFit();
    	$(".importBtn,.deleteBtn,.editbtn,.addProjBtn").addClass("hidden");
    	$("#costApplyTable").cgetData(true);//列表重新加载
    })
    
    //点击保存按钮
    $(".saveBtn").off("click").on("click",function(){
    	//$('#costApplyForm').cformSave('costApplyTable',saveCostApplyBack,true);
    	var dataObj={};
    	var json=$("#applyProjectTable").DataTable().rows().data();
    	
    	console.info("json---");
    	console.info(json);
    	
    	dataObj.list = []
		$.each(json, function(k,v){
			dataObj.list.push(v);
		})
		dataObj.paId=$("#paId").val();
    	dataObj.applyNo=$("#applyNo").val();
    	dataObj.feeType=$("#feeType option:selected").val();
    	dataObj.applyDeptName=$("#applyDeptName").val();
    	dataObj.applyer=$("#applyer").val();
    	dataObj.applyer=$("#applyer").val();
    	dataObj.auditUnit=$("#auditUnit option:selected").val();
    	dataObj.applyRemark=$("#applyRemark").val();
    	dataObj.applyAmount=$("#applyAmount").val();
    	console.info("dataObj---");
    	console.info(dataObj);
    	
		var data=JSON.stringify(dataObj);
    	
    	
		if(response){
			response.abort();
  	    }
 		var response = $.ajax({
			url: "costApply/savePaymentApply",
	        type: "POST",
	        timeout : 5000,
	        contentType: "application/json;charset=UTF-8",
	        data: data,
	        success: function (data) {
				if (data === "true") {   	        		  
					$("body").cgetPopup({
	                  	title: "提示信息",
	                  	content: "数据保存成功!",
	                  	accept: saveCostApplyBack,
	                  	chide: true,
	                  	icon: "fa-check-circle",
	                  	newpop: 'new'
	                 }); 
				}else{
	       			$("body").cgetPopup({
	                  	title: "提示信息",
	                  	content: "数据保存失败, 请重试! <br>" + data,
	                  	accept: popClose,
	                  	chide: true,
	                  	icon: "fa-exclamation-circle",
	                  	newpop: 'new'
					});  
				}
			}
		});
    })
    
    var saveCostApplyBack=function(){
    	$("#costApplyForm").toggleEditState(false).styleFit();
    	$(".importBtn,.deleteBtn,.editbtn,.addProjBtn").addClass("hidden");
    	$("#costApplyTable").cgetData(true);//列表重新加载
	}
    
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    //点击确定
    var sureDone=function(){}
    
    //导入返回
    var saveBack=function(data){
    	console.info(data);
    	$("#returnMessage").val(data);
    	var content="导入成功!";
    	var str=data;
    	if(data!=="true"||data!=="false"){
    		var lastOne=str.charAt(str.length-1);
    		console.info("lastOne--"+lastOne);
    		var reremoveLastOne=removeLastOne(str);
    		console.info("reremoveLastOne--"+reremoveLastOne);
    		if(lastOne=="1"){
    			//无此工程
    			content="工程编号为: "+reremoveLastOne+" 的工程，无此工程！";
    		}else if(lastOne=="2"){
    			//未下工程计划
    			content="工程编号为: "+reremoveLastOne+" 的工程，未下工程计划，不能申请设计费！";
    		}else if(lastOne=="3"){
    			//未结算
    			content="工程编号为: "+reremoveLastOne+" 的工程，未结算，不能申请监理费！";
    		}else if(lastOne=="4"){
    			//无探伤委托
    			content="工程编号为: "+reremoveLastOne+" 的工程，无探伤委托，不能申请检测费！";
    		}else if(lastOne=="5"){
    			//工程编号为空
    			content="存在工程编号为空的数据，请重新检查后导入！";
    		}else if(lastOne=="6"){
    			content="工程编号为: "+reremoveLastOne+" 的工程，不是"+$("#auditUnit option:selected").text()+"的工程,请重新导入！";
    		}
    	}else if(data=="false"){
    		content="导入失败";
    	}
    	
    	
    	var myoptions = {
               	title: "提示信息",
               	content: content,
               	accept: importBackDone,
               	chide:'true',
               	icon: "fa-check-circle",
               	newpop: 'new'
		}
        $("body").cgetPopup(myoptions);
    }
    
    var removeLastOne=function (str){
        return str.substring(0,str.length - 1);
    }
    
    var importBackDone=function(){
    	if($("#returnMessage").val()=="true"){
    		$("#costApplyForm").toggleEditState(false).styleFit();
        	$(".importBtn,.deleteBtn,.editbtn").addClass("hidden");
        	$("#costApplyTable").cgetData();//列表重新加载
    	}
    }



    
    
</script>
