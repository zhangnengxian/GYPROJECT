<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="infodetails">
    <div class="toolBtn f-r p-b-10  editbtn">
    	 <a href="javascript:;" class="btn btn-confirm  btn-sm m-l-5 saveBtn" >保存</a>
         <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
    <div class="clearboth form-box">
			<input type="hidden" id="deptDes" value="${loginInfo.deptName}"/>
			<input type="hidden" id="prepareDes" value="${loginInfo.staffName}"/>
    	<form class="form-horizontal" id="openPlanForm" action="openPlan/saveGasProject">
			<input type="hidden" id="acceptId" name="acceptId" />
			<input type="hidden" id="acceptType" name="acceptType" />
			<input class="hidden" name="gpId" id="gpId">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="corpId" name="corpId" />
    		<input type="hidden" id="gprojId" name="gprojId" />
			<input type="hidden" id="acceptDate" name="acceptDate" />
			<input type="hidden" id="isHaveCM" name="isHaveCM" />
			<input type="hidden" id="gasProjStatus" name="gasProjStatus">
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
		 <!--    <div class="form-group col-md-6 col-sm-12">
		    	新加字段
		    	<label class="control-label" for="cuName">施工单位</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"  value=""/>
		        </div>
		    </div> -->
		     <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="custName">用户名称</label>
		    	<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName"  value=""/>
		        </div>
		    </div>
		  
		      <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="custAddr">用户地址</label>
		    	<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custAddr" name="custAddr"  value=""/>
		        </div>
		    </div>
		      <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="custContcat">联系人</label>
		    	<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custContcat" name="custContcat"  value=""/>
		        </div>
		    </div> 
		     <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="custPhone">联系电话</label>
		    	<div>
					<input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone"  value=""/>
		        </div>
		    </div> 
		     <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="scNo">合同编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="scNo" name="scNo"  value=""/>
		        </div>
		    </div>
		     <!--   <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="contractAmount">签约金额</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="contractAmount" name="contractAmount"  value=""/>
		        </div>
		    </div> -->
		      <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="installationNumber">安装户数</label>  
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="installationNumber" name="installationNumber"  value=""/>
		        </div>
		    </div>
		   <!--    <div class="form-group col-md-6 col-sm-12">
		    	<label class="control-label" for="settlementAmount">已结算金额</label>  
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="settlementAmount" name="settlementAmount"  value=""/>
		        </div>
		    </div> -->
		  
		 <!--    <div class="form-group col-md-6 col-sm-12">
		    	新加字段
		    	<label class="control-label" for="gasComLegalRepresent">现场代表</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-not-editable"  id="gasComLegalRepresent" name="gasComLegalRepresent" value=""/>
		        </div>
		    </div> -->
		    <%--<div class="form-group col-md-6 col-sm-12">--%>
		    	<%--<!-- 新加字段 -->--%>
		        <%--<label class="control-label" for="preparDeptDes">填报部门</label>--%>
		    	<%--<div>--%>
		    		<%--<input type="text" class="form-control input-sm field-not-editable" id="preparDeptDes" name="preparDeptDes" value=""/>--%>
		        <%--</div>--%>
		    <%--</div>--%>
		    <%--<div class="form-group col-md-6 col-sm-12">--%>
		    	<%--<!-- 新加字段 -->--%>
		        <%--<label class="control-label" for="preparerDes">填报人</label>--%>
		    	<%--<div>--%>
		    		<%--<input type="text" class="form-control input-sm field-not-editable" id="preparerDes" name="preparerDes" value=""/>--%>
		        <%--</div>--%>
		    <%--</div>--%>
		    <%--<div class="form-group col-md-6 col-sm-12">--%>
		    	<%--<!-- 新加字段 -->--%>
		        <%--<label class="control-label" for="preparDate">填报日期</label>--%>
		    	<%--<div>--%>
		    		<%--<input type="text" class="form-control input-sm datepicker-default field-editable"  id="preparDate" name="preparDate"   value=""/>--%>
		        <%--</div>--%>
		    <%--</div>--%>
		    <%--<div class="form-group col-md-6 col-sm-12">--%>
		    	<%--<!-- 新加字段 -->--%>
		        <%--<label class="control-label" for="opNo">表单号</label>--%>
		    	<%--<div>--%>
		    		<%--<input type="text" class="form-control input-sm field-editable"  id="opNo" name="opNo"   value=""/>--%>
		        <%--</div>--%>
		    <%--</div>--%>
	<!-- 	  <div class="form-group col-md-6 col-sm-12">
		        <label class="control-label" for="planGasDate">计划开通时间</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable datepicker-default"  id="planGasDate" name="planGasDate"   value=""/>
		        </div>
		    </div>  -->
		  <!--   <div class="form-group col-md-6 col-sm-12">
		    	新加字段
		        <label class="control-label" for="pipeMaterial">管道材质</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable"  id="pipeMaterial" name="pipeMaterial"  data-parsley-maxlength="50" value=""/>
		        </div>
		    </div> -->
		  <!--   <div class="form-group col-md-6 col-sm-12 isHdden">
		    	新加字段
		        <label class="control-label" for="pipeSize">管径大小</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable"  id="pipeSize" name="pipeSize"  data-parsley-maxlength="50" value=""/>
		        </div>
		    </div> -->
		 <!--    <div class="form-group col-md-6 col-sm-12 isHdden">
		    	新加字段
		        <label class="control-label" for="pipeRating">压力等级</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable"  id="pipeRating" name="pipeRating" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div> -->
	<!-- 	    <div class="form-group col-md-6 col-sm-12 isHdden">
		    	新加字段
		        <label class="control-label" for="isStepDown">是否降压</label>
		    	<div>
		    		<label> 
						<input type="radio" class="field-editable" name="isStepDown" value="1"  />是
					</label>
					<label> 
						<input type="radio" class="field-editable" name="isStepDown" value="0" checked="checked" />否
					</label>
		        </div>
		    </div> -->
		      <!--  <div class="form-group col-md-6 col-sm-12 ">	
		    	新加字段是否开通,不用写入数据库，不用写入实体类，单纯做判断---开始
		        <label class="control-label" for="WhetherOpen">是否开通</label>
		    	<div>
		    		<label> 
						<input type="radio" class="field-editable" name="whetherOpen" value="1" checked="checked" />是
					</label>
					<label> 
						<input type="radio" class="field-editable" name="whetherOpen" value="0"  />否
					</label>
		        </div>
		    </div> -->
		   <!--  <div class="form-group col-md-6 col-sm-12">
		    	新加字段
		        <label class="control-label" for="gasPoint">带气点数</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable"  id="gasPoint" name="gasPoint" data-parsley-maxlength="30" value=""/>
		        </div>
		    </div> -->
		    <!-- 新加字段是否开通,不用写入数据库，不用写入实体类，单纯做判断---结束 -->
		  <!--   <div class="form-group col-md-6 col-sm-12 hidden deliveryTime clearboth">
		        <label class="control-label" for="deliveryTime">交付时间</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable datepicker-default" placeholder="请填写交付时间" id="deliveryTime" name="deliveryTime"  data-parsley-required="true" value=""/>
		        </div>
		    </div> -->
		  <!--    <div class="form-group col-md-12 hidden reason">
		    	新加字段
		        <label class="control-label" for="reason" >原因</label>
				<div>
					<textarea class="form-control input-sm field-editable" data-parsley-required="true" placeholder="请填写原因" id="reason" name="reason" data-parsley-maxlength="200" ></textarea>
				</div>
		    </div> -->
		 <!--    <div class="form-group col-md-12 isHdden">
		    	新加字段
		        <label class="control-label" for="gasContent">开通内容</label>
				<div>
					<textarea class="form-control input-sm field-editable" id="gasContent" name="gasContent" data-parsley-maxlength="200" ></textarea>
				</div>
		    </div> -->
			<div class="form-group col-md-6 ">
		        <label class="control-label" for="gasRemark">表型</label><!-- 此处用备注表示表的种类 -->
				<div>
					<input type="text" class="form-control input-sm field-editable" id="gasRemark" name="gasRemark" data-parsley-maxlength="200" />
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
    $("#openPlanForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('openPlanForm','','',scaleDetailRefresh(trSData.json));
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
    	$("#openPlanForm").toggleEditState(false);
    })

    var saveCallBack = function () {
        $(".editbtn").addClass("hidden");
        $("#openPlanForm").toggleEditState(false);
        $('#openPlanTable').cgetData(true);
    }
    $(".saveBtn").off("click").on("click",function(){
    	if($("#isHaveCM").val()>0){
	        	$("body").cgetPopup({
            	title: "提示信息",
            	content: "还有没签订补充协议的用户变更，不能进行通气计划!",
            	accept: popClose,
            	chide: true,
            	icon: "fa-warning",
            	newpop: 'new'
            });
        	return false;
	        }
    	$('#openPlanForm').cformSave('openingPlanTable',saveCallBack);
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
	//开通计划选择方式
    $("input[name='whetherOpen']").on("change",function() { 
    if (this.value == '1') {
    	   $(".deliveryTime,.reason").addClass("hidden");
    	   $(".isHdden").removeClass("hidden");
    	   $("#gasProjStatus").val("");
    }
    else if (this.value == '0') {
        $(".deliveryTime,.reason").removeClass("hidden");
        $(".isHdden").addClass("hidden");
    	$("#gasProjStatus").val('-1');
    }
  
});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->