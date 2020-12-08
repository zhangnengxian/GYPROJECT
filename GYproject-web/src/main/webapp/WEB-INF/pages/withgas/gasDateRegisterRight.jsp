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
    	<form class="form-horizontal" id="gasDateRegisterForm" action="gasDateRegister/saveGasProject">
			<input type="hidden" id="acceptId" name="acceptId" />
			<input type="hidden" id="acceptType" name="acceptType" />
			<input class="hidden" name="gpId" id="gpId">
    		<input type="hidden" id="projId" name="projId" />
    		<input type="hidden" id="corpId" name="corpId" />
    		<input type="hidden" id="gprojId" name="gprojId" />
			<input type="hidden" id="acceptDate" name="acceptDate" />
			<input type="hidden" id="custName" name="custName" />
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
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="pipeMaterial">管道材质</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="pipeMaterial" name="pipeMaterial"  data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="pipeSize">管径大小</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="pipeSize" name="pipeSize"  data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="pipeRating">压力等级</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="pipeRating" name="pipeRating" data-parsley-maxlength="50" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 col-sm-12 ">
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
		    <div class="form-group col-md-6 col-sm-12">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="gasPoint">带气点数</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-not-editable"  id="gasPoint" name="gasPoint" data-parsley-maxlength="30" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="gasContent">开通内容</label>
				<div>
					<textarea class="form-control input-sm field-not-editable" id="gasContent" name="gasContent" data-parsley-maxlength="200" ></textarea>
				</div>
		    </div>
			<div class="form-group col-md-12 ">
		    	<!-- 新加字段 -->
		        <label class="control-label" for="gasRemark">备注</label>
				<div>
					<textarea class="form-control input-sm field-not-editable" id="gasRemark" name="gasRemark" data-parsley-maxlength="200" ></textarea>
				</div>
		    </div>
		       <!-- 新加字段是否开通,不用写入数据库，不用写入实体类，单纯做判断---结束 -->
		    <div class="form-group col-md-6 col-sm-12   clearboth">
		        <label class="control-label" for="">通气时间</label>
		    	<div>
		    		<input type="text" class="form-control input-sm field-editable datepicker-default" placeholder="请填写通气时间" id="planGasDate" name="planGasDate"  data-parsley-required="true" value=""/>
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
	$(".infodetails").hideMask();
    //参数传false时，表单不可编辑
    $("#gasDateRegisterForm").toggleEditState(false).styleFit();
    $(".editbtn").addClass("hidden");
    trSData.t && trSData.t.cgetDetail('gasDateRegisterForm','','',scaleDetailRefresh(trSData.json));

    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    
    $(".cancelBtn").off().on("click",function(){
    	$(".editbtn").addClass("hidden");
    	$("#gasDateRegisterForm").toggleEditState(false);
    })

    var saveCallBack = function () {
        $(".editbtn").addClass("hidden");
        $("#gasDateRegisterForm").toggleEditState(false);
        $('#gasDateRegisterTable').cgetData(true);
    }
    
    
    $(".saveBtn").off("click").on("click",function(){
    	$('#gasDateRegisterForm').cformSave('gasDateRegisterTable',saveCallBack);
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->