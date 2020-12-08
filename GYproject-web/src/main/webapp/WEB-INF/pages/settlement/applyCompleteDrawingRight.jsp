<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
	<div class="toolBtn f-r p-b-10  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 btn-query saveBtn" >保存</a>
    	<!-- <a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 btn-query saveBtn" >保存</a> -->
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="applyform" data-parsley-validate="true" action="applyCompleteDrawing/saveDrawingApply">
		    <input type="hidden" name="projId" id="projId"/>
		    <input type="hidden" name="acdId" id="acdId"/>
       		<div class="form-group col-md-6 col-sm-12 ">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" />
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" />
		        </div>
		    </div>
		    <div class="form-group col-md-12  ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" />
		        </div>
		    </div>
		    <div class="form-group col-md-12">
	            <label class="control-label" for="projScaleDes">工程规模</label>
	            <div>
	                <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
	            </div>
            </div> 
            <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" />
		        </div>
		    </div>
        	<div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">工程类型</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">出资方式</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="">业务部门</label>
		    	<div>
		    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value=""/>        
		        </div>
		    </div>
             <div class="form-group col-md-12  ">
		        <label class="control-label" for="cuName">施工单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"   id="cuName" name="cuName" value=""/>
		        </div>
		    </div>
             <div class="form-group col-md-12  ">
		        <label class="control-label" for="suName">监理单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable" id="suName" name="suName" value=""/>
		        </div>
		    </div>
             <div class="form-group col-md-12  ">
		        <label class="control-label" for="duName">设计单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"   id="duName" name="duName" value=""/>
		        </div>
		    </div>
             <div class="form-group col-md-6  ">
		        <label class="control-label" for="applyer">申请人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="applyer" name="applyer" value=""/>
		        </div>
		    </div>
            
		    
 			
		    <div class="form-group col-md-6">
		        <label class="control-label" >申请日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="applyDate"  name="applyDate" data-parsley-required="true" value="" >
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="applyNo">申请编号</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable" id="applyNo"  name="applyNo" data-parsley-required="true" value="" data-parsley-maxlength="500">
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="applyer">代办人</label>
				<div >
					<input type="text" class="form-control input-sm field-not-editable" id="todoer" name="todoer"/>
				</div>
			</div>
		     
		   <div class="form-group col-md-12 ">
		        <label class="control-label" for="">申请事由</label>
		    	<div>
		    		<textarea class="form-control input-sm field-editable" id="applyReason"  name="applyReason"  rows="4" data-parsley-maxlength="500"></textarea>  
		 		      
		        </div>
		    </div>
		    
		    
		</form>
		
    </div>
</div>
<div class="clearboth p-t-15">
	<div >
		<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
	</div>
	<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
		<thead>
		<tr>
			<th>审批步骤</th>
			<th>审批级别</th>
			<th>审批结果</th>
			<th>审批意见</th>
			<th>审批人</th>
			<th>审批日期</th>
		</tr>
		</thead>
	</table>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#applyform").toggleEditState(false);
  	//表单样式适应
   	$("#applyform").styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $(".editbtn").addClass("hidden");
    
    changeAText();
    
    $('.backReason').addClass('hidden');
    //查右侧工程详述
    trSData.t && trSData.t.cgetDetail('applyform','applyCompleteDrawing/viewDrawingApply','',queryDetailBack); 
    
    /**点击右侧维护区 保存 按钮时*/
    $(".saveBtn").on("click",function(){
    	//要展示数据的表格的id  保存后回调函数 表单保存成功后，是否默认选中设置行
    	var popoption={
    			title:'提示',
    			content:'是否确认竣工图审批申请？',
    			accept:saveData
    	};
    	$("body").cgetPopup(popoption);
    }); 
    var saveData = function(){
		$('#applyform').cformSave('applyCompleteDrawingTable',saveApplyBack,false);
    };
    var saveApplyBack = function(data){
        $("#applyCompleteDrawingTable").cgetData(true,queryDetailBack);  //列表重新加载
    }
    var saveDone = function(){
    	
    }
     
    /**右侧维护区 放弃 按钮点击后*/
    $(".cancelBtn").on("click",function(){
		//表单不可编辑
    	$("#applyform").toggleEditState(false);
		//按钮隐藏
		$(".editbtn").addClass("hidden");
		//选中第一行
		selectTr["applyCompleteDrawingTable"] = 0;
   	 	$('tbody tr:eq(0)').select(); 
   	 	$("#applyCompleteDrawingTable").cgetData();
    });






</script>
<!-- ================== END PAGE LEVEL JS ================== -->