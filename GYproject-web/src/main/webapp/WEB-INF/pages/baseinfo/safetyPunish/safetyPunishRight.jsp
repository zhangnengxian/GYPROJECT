<!-- subContractLiuboRight.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="infodetails">
	<div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelAllBtn" >放弃</a>
	</div>
    <div class="toolBtn f-r p-b-15  editbtn">
    	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="safetyPunishform"	data-parsley-validate="true" action="">
			<div class="form-group col-md-6">
				<label class="control-label" for="corp">分公司</label>
				<div>
					<select id="corpId" name="corpId" class="form-control input-sm field-editable">
						<option value="" ></option>
						<c:forEach var="enums" items="${corp}">
							<option value="${enums.deptId}" >${enums.deptName}</option>
						</c:forEach>
					</select>
			   </div>
		 	</div> 
			<div class="form-group col-md-6">
				<label class="control-label" for="">细则大类编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable"	id="spId" name="id" value="" />
				</div>
			</div>
			<div class="form-group  col-md-6">
				<label class="control-label" for="">细则类型</label>
				<div>
					<select class="form-control input-sm field-editable" id="type" name="type">
						<option value="1">安全检查</option>
						<option value="0">质量检查</option>
					</select>
				</div>
			</div>
			<div class="form-group  col-md-6">
				<label class="control-label" for="">细则大类名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"	id="des" name="des" data-parsley-required="true" value="" />
				</div>
			</div>
		</form>
	</div>
	
	<div class="toolBtn f-r p-b-15  editbtn cancelDetermine">
     	<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelData" >放弃</a>
	</div>
	
	<div class="toolBtn f-r p-b-15  editbtn updateDetermine">
     	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 enBtn" >确定</a>
	</div>
	<div class="toolBtn f-r p-b-15  editbtn addDetermine">
     	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 addData" >确定</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="safetyPunishMinform"	data-parsley-validate="true" action="">
			<div class="form-group col-md-6">
				<label class="control-label" for="">细则小类编号</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="ids" name="id" value="" />
					<input type="hidden" id="tempId" name="tempId" value="0"/>
				</div>
			</div>
			<input type="hidden" id="typeChild" name="type">
			<div class="form-group  col-md-6">
				<label class="control-label" for="">细则小类名称</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"	id="des" name="des" data-parsley-required="true" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="deduction">分值</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"	id="deduction" name="deduction" data-parsley-type="number" data-parsley-required="true" value="" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="remark">备注</label>
				<div>
					<input type="text" class="form-control input-sm field-editable"	id="remark" name="remark" value="" />
				</div>
			</div>
		</form>
	</div>
</div>
<div class="clearboth form-box m-t-5">
	<form id="safetyPunishMinforms">
		<table id="safetyPunishMinTable" class="table table-striped table-bordered nowrap" width="100%"">
			<thead>
				<tr>
					<th>细则小类编号</th>
					<th>安全小类细则名称</th>
					<th>分值</th>
					<th>备注</th>
				</tr>
			</thead>
		</table>
	</form>
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
	$('#safetyPunishform,#safetyPunishMinform').toggleEditState(true).styleFit();
	//查右侧工程详述
	trSData.t && trSData.t.cgetDetail('safetyPunishform', 'safetyPunish/viewSafetyPunish', '',queryBack);
	//右屏小类table初始化
	SafetyPunishMin();
	$('.datepicker-default').datepicker({
		todayHighlight : true
	});

	/**点击右侧维护区 保存 按钮时*/
	$('.saveBtn').on('click', function() {
		if ($('#safetyPunishform').parsley().isValid()) {
			//$("#surveyDetailform").cformSave('surveyResultTable',saveSurveyBack,true);
			var data = $('#safetyPunishform').serializeJson();
			/* var tableData = $("#safetyPunishMinforms").serializeJson();
			console.info(tableData); */
			
			var dataObj={};
			var json=$("#safetyPunishMinTable").DataTable().rows().data();
	    	dataObj.list = [];
			$.each(json, function(k,v){
				dataObj.list.push(v);
			})
			var tableData=JSON.stringify(dataObj.list);
			    dataObj.list = [];//清空了；
			   
			$.ajax({
				type : 'POST',
				url : 'safetyPunish/saveSafetyPunish',
				contentType : "application/json;charset=UTF-8",
				data : JSON.stringify(data),
				success : function(data) {
					var content = "数据保存成功！";
					if (data === "false") {
						content = "数据保存失败！";
					} else if (data === "exist") {
						content = "编号已存在，请修改！";
					} else if (data === "true") {
						$("#safetyPunishform input").val('');
					}
					/* var myoptions = {
						title : "提示信息",
						content : content,
						accept : popClose,
						chide : true,
						icon : "fa-check-circle"
					}
					$("body").cgetPopup(myoptions); */
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.warn("保存失败！");
				}
			});
			 console.info(tableData);
			$.ajax({
				type : 'POST',
				url : "safetyPunish/saveAllSafetyPunish",
				contentType : "application/json;charset=UTF-8",
				data : tableData,
				success : function(data) {
					var content = "数据保存成功！";
					if (data === "false") {
						content = "数据保存失败！";
					} else if (data === "exist") {
						content = "编号已存在，请修改！";
					} else if (data === "true") {
						$("#safetyPunishform input").val('');
					}
					var myoptions = {
						title : "提示信息",
						content : content,
						accept : popClose,
						chide : true,
						icon : "fa-check-circle"
					}
					$("body").cgetPopup(myoptions);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.warn("保存失败！");
				}
			}); 
			$("#safetyPunishMinTable").cgetData(); //列表重新加载
			$("#safetyPunishTable").cgetData(); //列表重新加载
			//如果通过验证, 则移除验证UI
			$("#safetyPunishform").parsley().reset();
		} else {
			//如果未通过验证, 则加载验证UI
			$("#safetyPunishform").parsley().validate();
		}

	});

//整体放弃按钮
$('.cancelAllBtn').on('click', function() {
	//移除验证UI
	$("#safetyPunishMinform").parsley().reset();
	// 表单不可编辑
	$("#safetyPunishform").toggleEditState(false);
	$("#safetyPunishMinform").toggleEditState(false);
/*	$(".updateDetermine").addClass("hidden");
	$(".addDetermine").addClass("hidden");
	$(".cancelDetermine").addClass("hidden");
 	$(".cancelAllBtn").addClass("hidden");
	$(".saveBtn").addClass("hidden"); */
	$(".editbtn").addClass("hidden");
	$(".addBtns").addClass("hidden");
	$(".updateBtns").addClass("hidden");
	$(".delBtns").addClass("hidden");
	selectTr["safetyPunishMinTable"] = 0;
	$("#safetyPunishMinform").formReset();//重置表单
	$("#safetyPunishMinTable").cgetData();//列表重新加载
	$("#safetyPunishTable").cgetData();//列表重新加载
});

//小类放弃按钮
$('.cancelData').on('click', function() {
	$(".updateDetermine").addClass("hidden");
	$(".addDetermine").addClass("hidden");
	$(".cancelDetermine").addClass("hidden");
	// 表单不可编辑
	$("#safetyPunishMinform").toggleEditState(false);
	//移除验证UI
	$("#safetyPunishMinform").parsley().reset();
	/* // 默认选中第一行
	$("#safetyPunishMinTable").bindDTSelected(trSelectedBacks, true); */
});
	
//小类修改确定按钮
$('.enBtn').on('click', function() {
	//$("#safetyPunishMinform").cformSave('',,true);
	if($('#safetyPunishMinform').parsley().isValid()){
		delBtn();
		var data = [],
		    json = $("#safetyPunishMinform").serializeJson(); 
		data.push(json);
		console.info(data);
		safetyPunishMinTable.rows.add(data).draw();
		$(".updateDetermine").addClass("hidden");
		$(".cancelData").click();
		//如果通过验证, 则移除验证UI
		$("#safetyPunishMinform").parsley().reset();
	}else{
		//如果未通过验证, 则加载验证UI
		$("#safetyPunishMinform").parsley().validate();
	}
	
});

var delBtn = function(){
	var row=$("#safetyPunishMinTable").DataTable().row( '.selected' ).remove().draw();	
}

//小类增加确定按钮
$('.addData').on('click', function() {
	//$("#safetyPunishMinform").cformSave('',,true);
	if($('#safetyPunishMinform').parsley().isValid()){
		var data = [],
	    json = $("#safetyPunishMinform").serializeJson(); 
		data.push(json);
		console.info(data);
		safetyPunishMinTable.rows.add(data).draw();
		$(".addDetermine").addClass("hidden");
		var tempId = $("#ids").val();
		$("#tempId").val(tempId);
		$(".cancelData").click();
		//如果通过验证, 则移除验证UI
		$("#safetyPunishMinform").parsley().reset();
	}else{
		//如果未通过验证, 则加载验证UI
		$("#safetyPunishMinform").parsley().validate();
	}
});

//小类删除
/* $(".delBtns").on("click",function(){
	var row=$("#safetyPunishMinTable").DataTable().row( '.selected' ).remove().draw();	
	var arr=[];
	var json=$("#safetyPunishMinTable").DataTable().row().data();
	for(var i=0;i<json.length;i++){
		arr.push(json[i].id);
	}
}); */

$("#type").on("change",function(){
	$("#typeChild").val($(this).val());
})
</script>