<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
       <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">白图信息</h4>
			</div>
            <div class="panel-body">
	        	 <div class="toolBtn f-r p-b-10  hidden">
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewUpdataBtn" >修改</a>
					<a href="javascript:;" class="btn btn-confirm btn-sm checkRole drawingReviewSaveBtn" >保存</a>
				</div> 
	            <!-- 白图详述 -->
				<div class="clearboth form-box">
					<%--//值为2时文件格式支持多种（不影响其他页面功能写死）--%>
					<input type="hidden" id="changeType" value="2"/>
					<form class="form-horizontal" id="budgetSumForm" action="budgetResultRegister/updateBudgetFile"  enctype="multipart/form-data" >
						<input type="hidden" name="projId" id="projId" />
						<input type="hidden" name="stepId" id="stepId" />
						<input type="hidden" name ="corpId" />
						<div class="form-group col-md-6">
					   	<label class="control-label" for="projNo">工程编号</label>
					       <div>
					       	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="50" value="${project.projNo}"/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12  ">
					       <label class="control-label" for="projName">工程名称</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12  ">
					       <label class="control-label" for="projAddr">工程地点</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-sm-12">
					          <label class="control-label" for="projScaleDes">工程规模</label>
					          <div>
					              <textarea class="form-control field-not-editable" name ="projScaleDes" id="projScaleDes" rows="3" data-parsley-maxlength="200" value=''></textarea>
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
					   <div class="form-group col-sm-12 ">
					       <label class="control-label" for="custName">申报单位</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="custContact">联系人</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="custPhone">联系电话</label>
					       <div>
					           <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="12" value=""/>
					      
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="surveyer">勘察人</label>
					       <div>
					          <input type="text" class=" form-control input-sm field-not-editable" id="surveyer"  name="surveyer" data-parsley-maxlength="20" value="">
					       </div>
					   </div>
					   <div class="form-group col-md-6 col-sm-12">
					       <label class="control-label" for="designer">设计人</label>
					       <div>
					          <input type="text" class=" form-control input-sm field-not-editable" id="designer"  name="designer" data-parsley-maxlength="20" value="">
					       </div>
					   </div>
						<div class="form-group col-md-12 col-sm-12">
					       <label class="control-label" for="whiteMapRegisterRemark">登记备注</label>
					       <div>
					          <textarea type="text" class=" form-control input-sm field-not-editable" id="whiteMapRegisterRemark" rows="3" name="whiteMapRegisterRemark" data-parsley-maxlength="20" >${project.whiteMapRegisterRemark}</textarea>
					       </div>
					   </div>
						</form>
					</div>
	            </div>
	        </div>
		</div>
		<!-- col-sm-6 end  -->
		<div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
			<div class="panel-heading">
				<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	            </div>
				<h4 class="panel-title">确认区</h4>
			</div>
			<div class="panel-body" id="drawing_audit_panel_box">
					<div id="whiteMapAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box whiteMapAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="whiteMapAuditForm" action="whiteMapAudit/auditSave">
			    			<input type="hidden" id="projId1" name = "projId" value = "${project.projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo" value = "${project.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" />
                    		<input type="hidden" name = "mrAuditLevel" value = "${currentLevel}"/>
			    			<div class="form-group col-md-12">
			        			<label class="control-label" for="">确认结果</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="1" />通过
				            	</label>
				            	<label>
				            		<input type="radio" name="mrResult" value="0" />不通过
				            	</label>
		    				</div>
		    				<div class="form-group col-md-12">
						     	<label class="control-label" for="">确认意见</label>
						     	<div> 
		        					<textarea class="form-control "  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		   					</div>
		   					<div class="form-group col-md-6">
						        <label class="control-label" for="">确认人</label>
						        <div>
						           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
						           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
						        </div>
						    </div>
						    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
						        <label class="control-label" for="">确认日期</label>
						        <div>
						           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
						        </div>
						    </div>
			    		</form>
			    	</div>
			    	<div >
		    		<h4 class="m-t-15 m-l-7"><strong>确认历史</strong></h4>
		    		</div>
		    		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
       					<thead>
			            	<tr>
			                <th>确认日期</th>
			                <th>确认结果</th>
			                <th>确认意见</th>
			                <th>确认人</th>
	            			</tr>
          				</thead>
					</table>
					</div>
			    </div>
	     	</div>
		</div>	    	
	</div>
</div>



<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('白图审核 - 工程管理系统');
    
    $("#budgetSumForm").toggleEditState();
    $("#budgetSumForm").styleFit();
    
    $("#whiteMapAuditForm").toggleEditState();
    $("#whiteMapAuditForm").styleFit();
   
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/design/white-map-audit-page.js?v='+Math.random()).done(function () {
        whiteMapAudit.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("whiteMapAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#whiteMapAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#whiteMapAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.whiteMapAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#whiteMapAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#whiteMapAuditForm").toggleEditState(false); 
    	}
    }
    
    var isAuditFunction = function(){
    	var isAudit = $("#isAudit").val();
    	if(isAudit === "1"){
    		//确认过
    		auditSaveCallBack("true");
    	}
    }();
    
    
        
</script>
<!-- ================== END PAGE LEVEL JS ================== -->