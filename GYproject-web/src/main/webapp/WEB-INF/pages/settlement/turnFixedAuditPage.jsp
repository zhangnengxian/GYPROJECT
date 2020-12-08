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
                <h4 class="panel-title">转固信息</h4>
			</div>
            <div class="panel-body">
	              	<form class="form-horizontal" id="openingPlanForm" action="">
			    		<div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="">工程编号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="projNo" name="projNo" value="${turnFixedApply.projNo}" />
					        </div>
					    </div>
					    <div class="form-group col-md-12 col-sm-12">
					        <label class="control-label" for="">工程名称</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="projName" name="projName" value="${turnFixedApply.projName}"/>
					        </div>
					    </div>
					    <div class="form-group  col-md-12 col-sm-12">
					        <label class="control-label" for="">工程地点</label>
					        <div>
					            <input type="text" class="form-control input-sm field-not-editable" id="projAddr" name="projAddr" value="${turnFixedApply.projAddr}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="">燃气公司</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="corpName" name="corpName" value="${turnFixedApply.corpName}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">工程类型</label>
					    	 <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="projectTypeDes" name="projectTypeDes"  value="${turnFixedApply.projectTypeDes}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="">施工单位</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="cuName" name="cuName" value="${turnFixedApply.cuName}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					    	<label class="control-label" for="">施工合同号</label>
					        <div>
					        	<input type="text" class="form-control input-sm field-not-editable" id="scNo" name="scNo" value="${turnFixedApply.scNo}"/>
					        </div>
					    </div>
					    
					    <div class="form-group col-md-6">
					        <label class="control-label" for="">申请日期</label>
					        <div>
					           <input type="text" class=" form-control input-sm field-not-editable datepicker-default" id="tfaDate" name="tfaDate" value="${turnFixedApply.tfaDate}" >
					        </div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">工程总值</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="projTotalCost" name="projTotalCost" value="${turnFixedApply.projTotalCost}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">工程款</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="projCost" name="projCost" value="${turnFixedApply.projCost}"/>
					        </div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">破路费</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="brokenCost" name="brokenCost" value="${turnFixedApply.brokenCost}"/>
					        </div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">材料费</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="materialCost" name="materialCost" value="${turnFixedApply.materialCost}"/>
					        </div>
					    </div>
					    
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">工程检测费</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="inspectionCost" name="inspectionCost" value="${turnFixedApply.inspectionCost}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">利息</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="interest" name="interest" value="${turnFixedApply.interest}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">建管费</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="constructionCost" name="constructionCost" value="${turnFixedApply.constructionCost}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">其他费用</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable fixed-number text-right" id="otherCost" name="otherCost" value="${turnFixedApply.otherCost}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">管道材质</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable" id="materialQuality" name="materialQuality" value="${turnFixedApply.materialQuality}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">管径大小</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable" id="diameter" name="diameter" value="${turnFixedApply.diameter}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">壁厚</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable" id="thickness" name="thickness" value="${turnFixedApply.thickness}"/>
					        </div>
					    </div>
					    <div class="form-group col-md-6 col-sm-12">
					        <label class="control-label" for="">长度</label>
					    	<div>
					    		<input type="text" class="form-control input-sm field-editable" id="length" name="length" value="${turnFixedApply.length}"/>
					        </div>
					    </div>
						<div class="form-group col-md-12 col-sm-12">
					        <label class="control-label" for="">备注</label>
					    	<div>
					    		<textarea  class="form-control input-sm field-editable" id="remark" name="remark" rows="3">${turnFixedApply.remark}</textarea>
					        </div>
					    </div>
					    <div class="form-group col-md-6 clearboth">
							<label class="control-label signature-tool " for="treasurer">财务部</label>
							<div>
								<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_1"><i class="fa fa-pencil"></i></a>
								<input type="hidden" class="sign-data-input" id="treasurer" name="treasurer" data-parsley-required="true" value="${turnFixedApply.treasurer}"/>
								<input type="hidden" id="treasurert_postType" name="treasurer_postType" value="" >
								<img class="treasurer" alt="" src="" style="height: 30px">
								<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
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
					<div id="turnFixedAuditTopBox">
						<div class="toolBtn f-r m-b-15  editbtn">
    	 				<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
         				<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
					</div>
			    	<div class="clearboth form-box turnFixedAuditFormDiv">
			    		<input type="hidden" id="isAudit" name = "isAudit" value = "${isAudit}"/>
			    		<form class="form-horizontal" id="turnFixedAuditForm" action="turnFixedAudit/auditSave">
			    			<input type="hidden" id="projId" name = "projId" value = "${projId}"/>
			    			<input type="hidden"  name = "projNo" value = "${turnFixedApply.projNo}"/>
                    		<input type="hidden" id="businessOrderId" name = "businessOrderId" value = "${turnFixedApply.tfaId}"/>
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
						    <div class="form-group col-md-6">
								<label class="control-label signature-tool sign-require" for="turnFixedApply" id="">签字</label>
								<div>
									<a href="javascript:;" class="btn btn-sm btn-white" id="signBtn_2"><i class="fa fa-pencil"></i></a>
									<input type="hidden" class="sign-data-input" id="turnFixedApply" name="turnFixedApply${currentLevel }" data-parsley-required="true">
									<input type="hidden" id="turnFixedApply_postType" name="turnFixedApply_postType" value="" >
									<img class="" alt="" src="" style="height: 30px">
									<span class="clear-sign"><i class="fa ion-close-circled"></i></span>
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
    App.setPageTitle('转固审核 - 工程管理系统');
    $("#turnFixedAuditForm").toggleEditState();
    $("#turnFixedAuditForm").styleFit();
    $("#openingPlanForm").toggleEditState();
    $("#openingPlanForm").styleFit();
    //当前日期
    $("#mrTime").change();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
        $("#signBtn_1,#signBtn_2").handleSignature(false);
    });
    $("#turnFixedAuditForm").toggleEditState(true).styleFit();
    $.getScript('projectjs/complete/turn-fixed-apply-audit-page.js').done(function () {
    	auditHistory.init();
	});
    //放弃
    $(".cancelBtn").off("click").on("click",function(){
    	$("#ajax-content").cgetContent("turnFixedAudit/main");
	});
    //保存
    $(".saveBtn").off("click").on("click",function(){
    	var val=$('#turnFixedAuditForm input:radio[name="mrResult"]:checked').val();
    	if(val==null){
    		$("body").cgetPopup({
				title: '提示',
				content: '请选择确认结果！',
				accept: ensureDone
	    	});
    	}else{
    		$("#turnFixedAuditForm").cformSave("auditHistoryTable",auditSaveCallBack,'','','',$(".saveBtn"));
    	}
    });
    var ensureDone=function(){};
    //保存回到函数
    var auditSaveCallBack = function(data){
    	if(data === "true"){
    		$(".saveBtn,.turnFixedAuditFormDiv").addClass("hidden");
    		$(".cancelBtn").text("返回");
    		var val=$('#turnFixedAuditForm input:radio[name="mrResult"]:checked').val();
    		$("#turnFixedAuditForm").toggleEditState(false); 
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