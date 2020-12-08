<!-- accept/projectAccept.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10">
		<a href="javascript:;" class="btn btn-confirm btn-sm saveBtn">保存</a>
<!-- 		<a href="javascript:;" class="btn btn-warn btn-sm giveupBtn">放弃</a> -->
	</div>
	<div class="clearboth form-box m-r-25">
	    <input type="hidden" class="staffName"  value="${loginInfo.staffName}"/>
		<input type="hidden" class="baseTime"  value="${baseTime}"/>
		<input type="hidden" class="staffId" value="${loginInfo.staffId}">
		<form class="form-horizontal" id="completionTransferForm" action="completionTransfer/saveCompletionTransfer">
			<input type="hidden" class="imgUrl"  value="${imgUrl}"/>
			<input type="hidden" name ="projId" id="projId" value=""/>
			<input type="hidden" name ="fdId"  id="fdId"/>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">工程编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projNo" name="projNo" />
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label" for="">工程名称</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projName" name="projName" />
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label" for="">工程地点</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="projAddr" name="projAddr" />
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label" for="">工程规模</label>
				<div>
		        	<textarea class="form-control field-not-editable"  id="projScaleDes" name="projScaleDes" rows="3" data-parsley-maxlength="200"></textarea>
		        </div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">客户联系人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="custContact" name="custContact" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<label class="control-label" for="">联系电话</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" value="" id="custPhone" name="custPhone" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 show">
				<label class="control-label" for="">审核人</label>
				<div>
				    <input type="hidden" id="fdAuditor" name="fdAuditor" >
					<input type="text" class="form-control input-sm field-not-editable" id="staffName1" name="" />
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12 show">
				<label class="control-label" for="">归档时间</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable datepicker-default timestamp all" data-parsley-required="true" id="fdDate" name="fdDate" />
				</div>
			</div>
		</form>
	</div>
</div>
<div class="clearboth p-t-15"></div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('资料流转单 - 工程项目管理系统');
    
  //隐藏loading
    $(".infodetails").hideMask();
    
    //当前日期
    $("#fdDate").change();
  
    //日期
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //详述
    $("#completionTransfeTable").cgetDetail('completionTransferForm','','',tableCallBack);
    //签字
    $.getScript('assets/plugins/jSignature/jSignature.min.js').done(function() {
    	var signatures = $("#signBtn_1, #signBtn_2, #signBtn_3, #signBtn_4, #signBtn_5, #signBtn_6");
    	signatures.handleSignature();        	    	
    });
    
  	//保存
    $(".saveBtn").off("click").on("click",function(){
	    	$("#completionTransferForm").cformSave('completionTransfeTable',saveCallBack);
    });
    
    var saveCallBack = function(data){
    	if(data === "true"){
    		$(".toolBtn").addClass("hidden");
    		$("#completionTransferTable").cgetData(true,tableCallBack);  //列表重新加载
    	}
    }
  //表单样式适应
    $("#completionTransferForm").toggleEditState(true).styleFit();
</script>
<!-- ================== END PAGE LEVEL JS ================== -->