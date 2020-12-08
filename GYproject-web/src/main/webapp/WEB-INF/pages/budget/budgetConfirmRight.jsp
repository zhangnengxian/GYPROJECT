<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editBtn hidden">
       <a href="javascript:;" class="btn btn-query btn-sm m-l-7 saveBtn" >保存</a>
       <a href="javascript:;" class="btn btn-warn btn-sm m-l-7  cancelBtn" >放弃</a>
	</div>
	<div class="clearboth form-box">
		<input class="deptType" type="hidden" name="deptType"  value="${deptType}"/>
		<form class="form-horizontal" id="designDispatchForm" data-parsley-validate="true" action="">
			<input type="hidden" name="deptId"  id="deptId" value=""/>
			<input type="hidden"  name="deptDivide"  id="deptDivide" value=""/>
			<input type="hidden" name="designStationId"  id="designStationId" value=""/>
			<input type="hidden" name="unitId" id="unitId" value=""/>
			<input type="hidden" name="unitName" id="unitName" value=""/>
			<div class="form-group col-md-6">
            	<label class="control-label" for="projNo">工程编号</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo"/>
            	</div>
        	</div>
			<div class="form-group col-md-12">
            	<label class="control-label" for="projName">工程名称</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
            	<label class="control-label" for="projAddr">工程地点</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr"/>
            	</div>
        	</div>
        	<div class="form-group col-md-12">
            	<label class="control-label" for="projScaleDes">工程规模</label>
            	<div>
		            <textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="2" data-parsley-maxlength="200"></textarea>
		        </div>
        	</div>
        	<div class="form-group col-md-12">
            	<label class="control-label" for="custName">申报单位</label>
            	<div >
                	<input type="text" class="form-control input-sm field-not-editable" id="custName" name="custName"/>
            	</div>
        	</div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" data-parsley-required="true"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100"/>
		    		<!-- <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
						<option value="1" >居民户工程</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100"/>
		    		<!-- 
		    		<select class="form-control input-sm field-not-editable" id="investmentMethod"  name="investmentMethod" data-parsley-required="true" >
						<option value="1" >用户出资（自有资金）</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">踏勘员</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="surveyer" name="surveyer"  data-parsley-maxlength="100"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">踏勘日期</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="surveyDate" name="surveyDate"  data-parsley-maxlength="100"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">设计员</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-not-editable"  id="designer" name="designer"  data-parsley-maxlength="100"/>
		    		
		    		<!-- <select class="form-control input-sm field-not-editable" id="deptName"  name="deptName" data-parsley-required="true" >
						<option value="1" >民用组</option>
		             </select> -->
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">预算方式</label>
		    	<div>
		    		<select class="form-control input-sm field-editable" id="budgetMethod"  name="budgetMethod" data-parsley-required="true" data-parsley-maxlength="4">
						<option value="" >请选择预算方式</option>
						<c:forEach var="enums" items="${budgetMethodEnum }">
							<option value="${enums.value }" >${enums.message }</option>
						</c:forEach>
		             </select>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">备注</label>
		    	<div>
		    	<input type="text" class="form-control input-sm field-editable"  id="budgetMethodReMark" name="budgetMethodReMark"  data-parsley-maxlength="200"/>
		    	
		        </div>
		    </div>
        	<input type="hidden" name="projId" id="projId"/>
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
    $("#designDispatchForm").toggleEditState().styleFit();
    
    //查右侧工程详述
    trSData.t&&trSData.t.cgetDetail('designDispatchForm','budgetConfirm/viewProject','',detailBack);
   
    //点击派工
    $(".saveBtn").off().on("click",function(){
    	var projLen = $("#designDispatchTable").find("tr.selected").length;
    	if(projLen<1){
    		alertInfo("请先选择工程！");
    		return;
    	}
    	var budgetMethod = $("#budgetMethod").val();
    	if(budgetMethod!=''){
    		 $("body").cgetPopup({
          	    title: "提示信息",
             	content: "确认该预算方式吗？",
             	accept: function(){
          		var data=$("#designDispatchForm").serializeJson();
          		data.projId=$("#projId").val();
          		console.info("data");
          		console.info(data);
    	        	$.ajax({
    	                type: 'POST',
    	                url: 'budgetConfirm/saveProBudgetMethod',
    	                contentType: "application/json;charset=UTF-8",
    	                data: JSON.stringify(data),
    	                success: function (data) {
    	                	var content = "数据保存成功！";
    	                	if(data === "false"){
    	                		content = "数据保存失败！";
    	                	}else if(data === "true"){
    	                	  //  $('#budgeterTable').cgetData(true);
    	                	    $(".editBtn").addClass("hidden");
    	                	    $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
    	                	}
    	                	var myoptions = {
    	                        	title: "提示信息",
    	                        	content: content,
    	                        	accept: popClose,
    	                        	chide: true,
    	                        	newpop: 'new',
    	                        	icon: "fa-check-circle"
    	                    }
    	                    $("body").cgetPopup(myoptions);
    	                },
    	                error: function (jqXHR, textStatus, errorThrown) {
    	                    console.warn("预算方式保存失败！");
    	                }
    	            }); 
	          	},
	          	icon: "fa-check-circle",
	          	newpop: 'new'
      		});
    	}else{
    		$("body").cgetPopup({
					title: '提示',
					content: '请选择预算方式！',
					accept: popClose
		    });
    	}
    	
    })
    
    
    //点击确定
    var sureDone=function(){};
    var ensureDone=function(){};
  //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("budgetConfirm/main");
	});
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->