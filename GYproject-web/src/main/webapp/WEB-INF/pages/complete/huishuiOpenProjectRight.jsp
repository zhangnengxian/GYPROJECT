<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="toolBtn f-r p-b-10  informbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 hidden informSaveBtn" >确定</a>
	</div>
    <div class="clearboth form-box">
			<input type="hidden" id="deptDes" value="${loginInfo.deptName}"/>
			<input type="hidden" id="prepareDes" value="${loginInfo.staffName}"/>
    	<form class="form-horizontal" id="openingPlanForm" action="openingPlan/saveGasProject">
			<input class="hidden" name="gpId" id="gpId">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="gprojId" name="gprojId" />
			<input type="hidden" id="custName" name="custName" />
			<input type="hidden" id="acceptDate" name="acceptDate" />
			<input type="hidden" id="acceptType" name="acceptType" />
        	<div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="projNo">工程编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"  data-parsley-maxlength="50" value="" />
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group  col-md-12 col-sm-12">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="200" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 col-sm-12">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea  class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes" data-parsley-maxlength="200" ></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="deptName">燃气公司</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="deptName" name="deptName"  data-parsley-maxlength="100" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="projectTypeDes">工程类型</label>
		    	<div>
					<input type="text" class="form-control input-sm field-not-editable hidden"  id="projLtypeId" name="projLtypeId"  value=""/>
					<input type="text" class="form-control input-sm field-not-editable"  id="projectTypeDes" name="projectTypeDes"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="cuName">施工单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="scNo">施工合同号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		    	<label class="control-label" for="gasComLegalRepresent">现场代表</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" value=""/>
		        </div>
		    </div>

		    <%--<div class="form-group col-md-6 col-sm-12">--%>
		    	<%--<!-- 新加字段 -->--%>
		        <%--<label class="control-label" for="opNo">表单号</label>--%>
		    	<%--<div>--%>
		    		<%--<input type="text" class="form-control input-sm field-editable"  id="opNo" name="opNo"   value=""/>--%>
		        <%--</div>--%>
		    <%--</div>--%>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="planGasDate">计划开通时间</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable datepicker-default timestamp "  id="planGasDate" name="planGasDate"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="pipeMaterial">管道材质</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="pipeMaterial" name="pipeMaterial"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="pipeSize">管径大小</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="pipeSize" name="pipeSize"   value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="pipeRating">压力等级</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="pipeRating" name="pipeRating"  value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="isStepDown">是否降压</label>
		    	<div>
		    		<label> 
						<input type="radio" class="field-not-editable" name="isStepDown" value="1"  />是
					</label>
					<label> 
						<input type="radio" class="field-not-editable" name="isStepDown" value="0" checked="checked" />否
					</label>
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="gasContent">开通内容</label>
				<div>
					<textarea class="form-control input-sm field-not-editable" id="gasContent" name="gasContent" data-parsley-maxlength="200" ></textarea>
				</div>
		    </div>
			<div class="form-group col-md-6 isBack ">
				<label class="control-label">是否退回</label>
				<div>
					<label>
						<input type="radio" class="field-editable"  name="isBack"  value="1" /> 是
					</label>
					<label>
						<input type="radio" class="field-editable"  name="isBack"  value="0" checked/> 否
					</label>
				</div>
			</div>
			<div class="form-group col-md-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="gasRemark">备注</label>
				<div>
					<textarea class="form-control input-sm field-editable" id="gasRemark" name="gasRemark" data-parsley-maxlength="200" ></textarea>
				</div>
		    </div>
			<%--<div class="form-group col-md-6 backReason">--%>
				<%--<label class="control-label">退回原因</label>--%>
				<%--<div>--%>
					<%--<select class="form-control input-sm field-editable a2" id="backReason"  name="backReason" data-parsley-required="true" >--%>
						<%--<option></option>--%>
						<%--<c:forEach var="enums" items="${backReason}">--%>
							<%--<option value="${enums.value}">${enums.message}</option>--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
				<%--</div>--%>
			<%--</div>--%>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="preparDeptDes">填报部门</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="preparDeptDes" name="preparDeptDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label" for="preparerDes">填报人</label>
				<div>
					<input type="text" class="form-control input-sm field-not-editable" id="preparerDes" name="preparerDes" value=""/>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-12">
				<!-- 新加字段 -->
				<label class="control-label">填报日期</label>
				<div>
					<input type="text" class="form-control input-sm datepicker-default field-editable"  id="preparDate" name="preparDate"   value=""/>
				</div>
			</div>

		</form>
<!-- 		<div class="clearboth form-box  photoBox"> -->
<!-- 			<div class="form-group width-full attach-images-list" id="projectImagesList"> -->
<!-- 			     <h4><i class="fa fa-file-photo-o"></i> 照片列表<a href="javascript:;" class="btn btn-primary btn-sm uploadBtn pull-right"><i class="fa fa-upload"></i> 上传</a><a href="javascript:;" class="btn btn-primary btn-sm camera-btn pull-right m-r-5"><i class="fa fa-camera"></i> 拍照</a><a href="javascript:;" class="btn btn-primary btn-sm select-images-btn pull-right m-r-5"><i class="fa fa-folder-open"></i> 选择图片</a></h4> -->
<!-- 			     <ul class="row"> -->
<!-- 			     </ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
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
    //参数传false时，表单不可编辑
    $("#openingPlanForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('openingPlanForm','','',scaleDetailRefresh(trSData.json));
//    function scaleDetailRefresh(json){
//        //加载照片列表
//        setTimeout(function(){
//            $("#projectImagesList").getImagesList({
//                "projId": $("#projId").val(),
//                "stepId": getStepId(),
//                "projNo": $("#projNo").val(),
//                "busRecordId": $("#gprojId").val() || '-1'
//            });
//        },300);
//    }
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $(".cancelBtn").off().on("click",function(){
    	$(".editbtn").addClass("hidden");
    	$("#openingPlanForm").toggleEditState(false);
    })

    var SaveCallBack = function () {
        $(".editbtn").addClass("hidden");
        $("#openingPlanForm").toggleEditState(false);
        $('#openingPlanTable').cgetData(true);
    }

    $(".saveBtn").off("click").on("click",function(){
        var data=$("#openingPlanForm").serializeJson();
        if(data.isBack === "1") {
            var popoptions = {
                title: '提示',
                content: '您确定退回吗？',
                accept: function () {
    				$('#openingPlanForm').cformSave('openingPlanTable',SaveCallBack);
                }
            };
            $("body").cgetPopup(popoptions);
        }else{
        	$('#openingPlanForm').cformSave('openingPlanTable',SaveCallBack);
        }
    });


    //移动端点击上传
    $(".uploadBtn").off("click").on("click",function(){
        var gprojId = $("#gprojId").val(),
            projNo = $("#projNo").val(),
            projId = $("#projId").val(),
            stepId=getStepId();
        busRecordId=gprojId;
		if(_inApk && $(".attach-images-list").find(".new-image").length){
				//photoUpload($(".attach-images-list .upload-image-btn"), projId, projNo, getStepId(), opId);
				
			var images = $(".attach-images-list .uploadBtn").parents(".attach-images-list").find(".new-image"),
		    files = [];
		    $.each(images, function (i, el) {
		        files.push($(el).find(".preview-btn").attr("src-orig"));
		    });
		    //navigator.notification.alert(files);
		    if (!busRecordId && $("#opId").length && !$("#opId").val()) {
		        navigator.notification.alert('请先保存该条记录，保存记录后会自动上传图片', null, "提醒", "确定");
		        return false;
		    }
		    if (files.length) {
		    	uploadImages(files, 0, files.length, projId, projNo, stepId, busRecordId);
		    } else {
		        navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		    }
		}else{
			 navigator.notification.alert("没有需要上传的图片!", null, "提醒", "确定");
		};
	})
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->