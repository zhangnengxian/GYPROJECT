<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="infodetails">
	<div class="form-box clearboth">
		<!-- 增加表单 -->
		<form class="form-horizontal" id="projectForm" data-parsley-validate="true">
			<input type="hidden" id="projId" name = "projId" />
			<div class="form-group col-md-6 ">
		        <label class="control-label" for="projNo">工程编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projNo" name="projNo"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="projAddr">工程地点</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projAddr" name="projAddr" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projName">工程名称</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="projName" name="projName"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="projScaleDes">工程规模</label>
		        <div>
		            <textarea rows="4" class="form-control input-sm field-not-editable"  id="projScaleDes" name="projScaleDes"></textarea>
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="duName">申报单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custName" name="custName" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custContact">联系人</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custContact" name="custContact" data-parsley-maxlength="20" value=""/>
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="custPhone">联系电话</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="custPhone" name="custPhone" data-parsley-maxlength="12" value=""/>
		       
		        </div>
		    </div>
		    <div class="form-group col-md-12 ">
		        <label class="control-label" for="">分包单位</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuName" name="cuName"/>
		        </div>
		    </div>
		    
		    <div class="form-group col-md-6 ">
		        <label class="control-label" for="">项目经理</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="cuPm" name="cuPm"/>
		        </div>
		    </div>
		    
		     <div class="form-group col-md-6 ">
		        <label class="control-label" for="">甲方代表</label>
		        <div>
		            <input type="text" class="form-control input-sm field-not-editable"  id="builder" name="builder"/>
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
	
trSData.t && trSData.t.cgetDetail('projectForm','materialPlanning/viewProject','',noteCallBack);

</script>

