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
	                <h4 class="panel-title">受理信息</h4>
				</div>
            	<div class="panel-body">
	           		 <!-- 受理详述 -->
					<div class="clearboth form-box">
						<form class="form-horizontal" id="acceptForm"  >
							<div class="form-group col-md-6 col-sm-12">
						    	<label class="control-label" for="">工程编号</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  value="${data.projNo}"/>
						        </div>
						    </div>
						    <div class="form-group col-sm-12">
						        <label class="control-label" for="">工程名称</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-editable"  id="projName" name="projName" data-parsley-maxlength="200" value="${data.projName}"/>
						        </div>
						    </div>
						    <div class="form-group col-sm-12">
						        <label class="control-label" for="">工程规模</label>
						        <div>
						        	<textarea class="form-control input-sm field-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="200"  rows="3">${data.projScaleDes}</textarea>
						        </div>
						    </div>
						    <div class="form-group col-md-6">
				            	<label class="control-label" for="projSourceDes">受理来源</label>
				            	<div>
						            <input type="text" class="form-control field-not-editable"  id="projSourceDes" name="projSourceDes"  data-parsley-maxlength="200" value="${data.projSourceDes}"/>
						        </div>
				        	</div>
						    <div class="form-group col-sm-12">
						        <label class="control-label" for="custName">申报单位</label>
						        <div>
						            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" data-parsley-maxlength="200"  value="${data.custName}"/>
						        </div>
						    </div>
						    <div class="form-group col-sm-12">
						        <label class="control-label" for="">工程地点</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value="${data.projAddr}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">联系人</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="custContact" name="custContact" data-parsley-maxlength="200" value="${data.custContact}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">联系电话</label>
						        <div>
						            <input type="text" class="form-control input-sm field-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="20" value="${data.custPhone}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						    	<label class="control-label" for="">燃气公司</label>
						        <div>
						        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName"  data-parsley-maxlength="100" value="${data.corpName}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">业务部门</label>
						    	<div>
						    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100" value="${data.deptName}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">工程类型</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  data-parsley-maxlength="100" value="${data.projectTypeDes}"/>
						        </div>
						    </div>
						    <div class="form-group col-md-6 col-sm-12">
						        <label class="control-label" for="">出资方式</label>
						    	<div>
						    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes"  data-parsley-maxlength="100" value="${data.contributionModeDes}"/>
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
					<div id="budgetAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box acceptAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="acceptAuditForm" action="acceptAudit/auditSave">
			    			<input type="hidden" id="projId1" name = "projId" value = "${data.projId}"/>
			    			<input type="hidden" id="projNo1" name = "projNo" value = "${data.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId"  value = "${data.projId}"/>
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
    App.setPageTitle('立项审核 - 工程管理系统');
    
    $("#acceptForm").toggleEditState();
    $("#acceptForm").styleFit();
    
    $("#acceptAuditForm").toggleEditState();
    $("#acceptAuditForm").styleFit();
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/accept/accept-audit-page.js').done(function () {
    	auditHistory.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("acceptAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#acceptAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#acceptAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.acceptAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#acceptAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#acceptAuditForm").toggleEditState(false); 
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