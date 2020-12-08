<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="toolBtn f-r p-b-10">
		<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden saveBtn">确认</a>
		<a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden saveBtn2">确认</a>
		<!--  <a href="javascript:;" class="btn  btn-warn btn-sm m-l-5  cancelBtn">放弃</a> -->
	</div>
	<div class="form-box clearboth">
		<!-- 增加表单 -->
		<form class="form-horizontal" id="projectForm"
			data-parsley-validate="true">
			<input type="hidden" id="projId" name="projId"/>
			<input type="hidden" id="status" name="status"/>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">工程编号</label>
				<div>
					<input type="text"
						class="form-control input-sm field-not-editable pro-val"
						id="projNo" name="projNo" value="" />
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-12 ">
				<label class="control-label" for="">工程名称</label>
				<div>
					<input type="text"
						class="form-control input-sm field-not-editable pro-val"
						id="projName" name="projName" value="" />
				</div>
			</div>
			<div class="form-group col-lg-12 col-md-12 col-sm-12 ">
				<label class="control-label" for="">工程地点</label>
				<div>
					<input type="text"
						class="form-control input-sm field-not-editable pro-val"
						id="projAddr" name="projAddr" value="" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="">区域</label>
				<div>
					<input type="text"
						class="form-control input-sm field-not-editable pro-val"
						id="areaDes" name="areaDes" value="" />
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6 ">
				<label class="control-label" for="contractProjType">工程类型</label>
				<div>
					<input type="text"
						class="form-control input-sm field-not-editable pro-val"
						id="contractProjType" name="contractProjType" value="" />
				</div>
			</div>
			<div class="form-group col-md-12 hidden plan_note" >
				<label class="control-label" for="note">备注</label>
				<div>
					<textarea class="form-control" name="note" id="note" rows="2"
						data-parsley-maxlength="200" data-parsley-required="true"></textarea>
				</div>
			</div>
			<div class="form-group col-md-12 hidden procure_feedback">
				<label class="control-label" for="procureFeedback">材料反馈</label>
				<div>
					<textarea class="form-control" name="procureFeedback" id="procureFeedback" rows="5"
						data-parsley-maxlength="2500" data-parsley-required="true"></textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
App.restartGlobalFunction();
$("#projectForm").hideMask();
$("#projectForm").toggleEditState().styleFit();
if(trSData.procurementPlanTable.t){
	$(".editbtn").removeClass("hidden");
	var projId = $("#projId").val();
   trSData.procurementPlanTable.t.cgetDetail('projectForm','procurementPlan/queryProject','6',noteCallBack);
}else{
	$(".editbtn").addClass("hidden");
}

$(".saveBtn").on("click",function(){
	if($("#projectForm").parsley().isValid()){
	var projId = $("#projId").val();
	var note = $("#note").val();
	$.ajax({
		type: 'POST',
		url: 'procurementPlan/saveNote',
        data:{
        	projId : projId,
        	note : note
        },
        success : function(data){
        	var content = "数据保存成功！";
         	if(data === "false"){
         		content = "数据保存失败！";
         	}else{
         		$(".saveBtn").addClass("hidden");
         	}
         	var myoptions = {
                 	title: "提示信息",
                 	content: content,
                 	accept : popClose,
                 	chide: true,
                 	icon: "fa-check-circle",
                 	newpop: 'new'
             }
         	 $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("施工计划备注保存失败！");
        }
	});
  	//如果通过验证, 则移除验证UI
	$("#projectForm").parsley().reset();
}else{
	//如果未通过验证, 则加载验证UI
	$("#projectForm").parsley().validate();
}
});
$(".saveBtn2").on("click",function(){
	if($("#projectForm").parsley().isValid()){
	var projId = $("#projId").val();
	var procureFeedback = $("#procureFeedback").val();
	$.ajax({
		type: 'POST',
		url: 'procurementPlan/saveProcureFeedback',
        data:{
        	projId : projId,
        	procureFeedback : procureFeedback
        },
        success : function(data){
        	var content = "数据保存成功！";
         	if(data === "false"){
         		content = "数据保存失败！";
         	}else{
         		$(".saveBtn2").addClass("hidden");
         	}
         	var myoptions = {
                 	title: "提示信息",
                 	content: content,
                 	accept : popClose,
                 	chide: true,
                 	icon: "fa-check-circle",
                 	newpop: 'new'
             }
         	 $("body").cgetPopup(myoptions);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.warn("材料反馈保存失败！");
        }
	});
	//如果通过验证, 则移除验证UI
	$("#projectForm").parsley().reset();
}else{
	//如果未通过验证, 则加载验证UI
	$("#projectForm").parsley().validate();
}
});
if($("#procureFeedback").val()!==""){
	$(".saveBtn2").addClass("hidden");
}
if($("#note").val()!==""){
	$(".saveBtn").addClass("hidden");
}
</script>
















