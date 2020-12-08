<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
	<div class="toolBtn f-r p-b-10 editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5  saveBtn" >保存</a>
        <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
    	<input type="hidden" id="flag" name="flag"/> 
    	<input type="hidden" id="sysDate" value="${sysDate}"/>
    	<input type="hidden" id="loginName" name="loginName" value="${loginInfo.staffName}"/> 
    	<form class="form-horizontal" id="designChangeApplyForm" data-parsley-validate="true" action="designChangeApply/saveChangeManagement">
       		<input type="hidden" id="projId" name="projId"/>
       		<input type="hidden" id="cmId" name="cmId"/>
       		<input type="hidden" id="version" name="version"/>
         	<input type="hidden" id="designChangeTypeMark" name="designChangeTypeMark"/>  <!-- 废弃标志 -->
         	<input type="hidden" id="designChangeType" name="designChangeType"/>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" value=""/>
		        </div>
		    </div>
		     <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		        	<textarea class="form-control input-sm field-not-editable" name="projScaleDes" rows="4" ></textarea>
		        </div>
		    </div>
			<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="corpName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  />
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
		    <div class="form-group col-sm-12">
 				<label class="control-label" for="">变更原因</label>
				<div> 
     				<textarea class="form-control field-editable" name="applyReason" id="applyReason" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
    			</div>
   		    </div>
   		      <div class="form-group col-sm-12  hidden cancelRemark">
 				<label class="control-label" for="">废弃原因</label>
				<div> 
     				<textarea class="form-control field-editable" name="cancelRemark" id="cancelRemark" required="required" rows="4" cols="" data-parsley-maxlength="500" ></textarea>
    			</div>
   		    </div>
		     <div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">变更编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="cmNo" name="cmNo" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12  clearboth">
				<label class="control-label" for="">申请日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="applyDate" name="applyDate"  />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 ">
				<label class="control-label" for="">申请人</label>
				<div>
					<input type="text" class="form-control input-sm  field-not-editable" id="cmAdvanceStaffName" name="cmAdvanceStaffName"  />
				</div>
			</div>
				<div class="form-group col-md-6 col-sm-12  clearboth cancelDate hidden">
				<label class="control-label" for="">废弃日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-not-editable" id="cancelDate" name="cancelDate" />
				</div>
			</div>
				<div class="form-group col-md-6 col-sm-12 cancelStaffName hidden">
				<label class="control-label" for="">废弃人</label>
				<div>
					<input type="text" class="form-control input-sm  field-not-editable" id="cancelStaffName" name="cancelStaffName"  />
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
    $('#designChangeApplyForm').toggleEditState(false).styleFit();
  	//查右侧工程详述
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    }); 
    //保存
    $(".saveBtn").off().on("click",function(){
    	if($('#designChangeTypeMark').val()=='-1'){ //点击废弃按钮
    		$('#designChangeType').val('-1');
    		$("body").cgetPopup({
    			title: '提示',
    			content: '你确定要废弃此条记录吗？',
    		    accept:deleteSave
    		});
    	}else{
    		$("#cancelDate").val("");
    		$("#cancelStaffName").val("");  //不是废弃保存时，令废弃时间和废弃人姓名为空
    		$('#designChangeApplyForm').cformSave('designChangeApplyTable',saveAcceptanceApplyBack,true);
    	}
    	
    })
    var deleteSave = function(){
    	
    	$('#designChangeApplyForm').cformSave('designChangeApplyTable',saveAcceptanceApplyBack,true);
    }
  	
    var saveAcceptanceApplyBack=function(){
    	$(".editbtn").addClass("hidden");
    	$('#designChangeApplyForm').toggleEditState(false);
    	$("#designChangeApplyTable").cgetData(true);
    }
   
    //放弃
    $(".cancelBtn ").on("click",function(){
    	$("#flag").val("");
    	
    	if($("#cmId").val()==""){
    		//返回列表区
    		$('ul.nav-tabs>li.active').removeClass("active");
    		$('#listTab').tab("show");
    		$('#designChangeApplyTable').cgetData(true,queryTableBack);
    	}else{
    		//返回列表区
    		$('ul.nav-tabs>li.active').removeClass("active");
    		$('#listTab').tab("show");
    	}
    });
 	
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->