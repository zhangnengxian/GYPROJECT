<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">工程信息</h4>
			</div>
            <div class="panel-body">
              	<form class="form-horizontal" id="completionDataAuditLeftForm" action="">
		    		<!-- <button type="reset" class="hidden" id="reset"/> -->
		        	<div class="form-group col-md-6 col-sm-12">
				    	<label class="control-label" for="">工程编号</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  value="${project.projNo}" />
				        </div>
				    </div>
				    <div class="form-group col-md-12 col-sm-12">
				        <label class="control-label" for="">工程名称</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"  value="${project.projName}"/>
				        </div>
				    </div>
				    <div class="form-group  col-md-12 col-sm-12">
				        <label class="control-label" for="">工程地点</label>
				        <div>
				            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value="${project.projAddr}"/>
				        </div>
				    </div>
				    <div class="form-group col-md-12 col-sm-12">
				        <label class="control-label" for="projScaleDes">工程规模</label>
				        <div>
				            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" >${project.projScaleDes}</textarea>
				        </div>
				    </div>
				  	<div class="form-group col-md-6 col-sm-12">
				    	<label class="control-label" for="corpName">燃气公司</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable"  id="corpName" name="corpName" value="${project.corpName}" />
				        </div>
				    </div>
		        	<div class="form-group col-md-6 col-sm-12">
				        <label class="control-label" for="">工程类型</label>
				    	<div>
				    		<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes" value="${project.projectTypeDes}" />
				        </div>
				    </div>
				    <div class="form-group col-md-6 col-sm-12">
				        <label class="control-label" for="">出资方式</label>
				    	<div>
				    		<input type="text" class="form-control input-sm field-not-editable"  id="contributionModeDes" name="contributionModeDes" value="${project.contributionModeDes}" />
				        </div>
				    </div>
				    <div class="form-group col-md-6 col-sm-12">
				        <label class="control-label" for="">业务部门</label>
				    	<div>
				    		 <input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName" value="${project.deptName}" />        
				        </div>
				    </div>
				    <div class="form-group col-md-12 col-sm-12">
				    	<!-- 新加字段 -->
				    	<label class="control-label" for="">施工单位</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value="${project.cuName}" />
				        </div>
				    </div>
				    <div class="form-group col-md-12 col-sm-12 ">
				    	<!-- 新加字段 -->
				    	<label class="control-label" for="suName">监理单位</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable"  id="suName" name="suName"  value="${project.suName}" />
				        </div>
				    </div>
				    <div class="form-group col-md-6 col-sm-12 ">
				    	<!-- 新加字段 -->
				    	<label class="control-label" for="applyDate">申请日期</label>
				        <div>
				        	<input type="text" class="form-control input-sm field-not-editable"  id="applyDate" name="applyDate"  value="${project.applyDate}" />
				        </div>
				    </div>
				    <div class="form-group col-md-12 col-sm-12 ">
				    	<!-- 新加字段 -->
				    	<label class="control-label" for="note">备注</label>
				        <div>
				        	<textarea type="text" class="form-control input-sm field-not-editable"  id="note" name="note"  rows="2" >${project.note}</textarea>
				        </div>
				    </div>
				</form>
	        </div>
	        
		</div>
		</div>
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
					<div id="completionDataAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
         				
					</div>
			    	<div class="clearboth form-box completionDataAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="completionDataAuditForm" action="completionDataAudit/auditSave">
			    			<input type="hidden" id="projId" name = "projId" value = "${projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${da.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${da.daId}"/>
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

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('资料审核 - 工程管理系统');
    
    $("#completionDataAuditForm").toggleEditState();
    $("#completionDataAuditForm").styleFit();
   
    
    $("#completionDataAuditLeftForm").toggleEditState();
    $("#completionDataAuditLeftForm").styleFit();
    
    
    
    
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $.getScript('projectjs/complete/completion-data-audit-page.js').done(function () {
    	auditHistory.init();
	});

    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("completionDataAudit/main");
	});
    
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#completionDataAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#completionDataAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    
    
    var ensureDone=function(){};
    
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.completionDataAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#completionDataAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#completionDataAuditForm").toggleEditState(false); 
    	}
    	console.info("de..."+$("#budgeterId").val());
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