<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<div class="infodetails">
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="applyDelayDetailForm"  data-parsley-validate="true" action="">
       		<input type="hidden" name="projId" id="projId" value="${applyDelay.projId}"/>
       		
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo" data-parsley-maxlength="100" value="${applyDelay.projNo}"/>
		        </div>
		    </div>
		    <div class="form-group  col-md-6 ">
		        <label class="control-label" for="stepId">工程步骤</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="stepId" name="stepId" data-parsley-maxlength="100" value="踏勘阶段"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName" data-parsley-maxlength="100" value="贵阳XXXX"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" data-parsley-maxlength="100" value="贵阳"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		    	<label class="control-label" for="conNo">延期编号</label>
		        <div>
		        	<input type="text" class="form-control input-sm field-editable"   id="aDId" name="aDId" value="2017000011"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="originalPeriod">原时段</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="originalPeriod" name="originalPeriod" data-parsley-maxlength="100" value="2017-02-01"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="delayPeriod">延期时段</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="delayPeriod" name="delayPeriod" data-parsley-maxlength="100" value="2017-05-06"/>
		        </div>
		    </div>
		     <div class="form-group col-md-12">
		        <label class="control-label" for="delayReason">延期原因</label>
		        <div>
		            <textarea rows="3" cols="" class="form-control input-sm field-not-editable"  id="delayReason" name="delayReason" data-parsley-maxlength="100" value="踏勘人员不够，现场踏勘延期"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="adOperator">操作人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="adOperator" name="adOperator" data-parsley-maxlength="100" value="admin"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="optTime">操作时间</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="optTime" name="optTime" data-parsley-maxlength="100" value="2017-08-09"/>
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
    
    //表单样式适应
    $("#applyDelayDetailForm").styleFit();
    
    //参数传false时，表单不可编辑
    $("#applyDelayDetailForm").toggleEditState(false);
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->