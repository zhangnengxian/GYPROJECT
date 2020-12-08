<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="infodetails">
    <div class="clearboth form-box">
    	<form class="form-horizontal" id="surveyDetailform" data-parsley-validate="true" action="">
		    <div class="form-group col-md-6">
		        <label class="control-label" for="connectGasNo">接气单编号</label>
		        <div>
		            <input type="text" class="form-control input-sm field-editable"  id="connectGasNo" name="connectGasNo"  value="${surveyInfo.connectGasNo}"/>
		        </div>
		    </div>
		    <div class="form-group col-md-6 clearboth">
		        <label class="control-label" for="surveyer">勘察人</label>
		        <div>
		           <input type="text" class="form-control input-sm field-not-editable" id="surveyer"  name="surveyer"  value="${surveyInfo.surveyer}">
		        </div>
		    </div>
		    <div class="form-group col-md-6">
		        <label class="control-label" for="surveyDate">勘察日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-editable datepicker-default" id="surveyDate"  name="surveyDate"  value="${surveyInfo.surveyDate}" >
		        </div>
		    </div>
		    <div class="form-group col-md-12">
		     	<label class="control-label" for="surveyDes">现场勘察情况</label>
		     	<div> 
		        	<textarea class="form-control field-editable" name="surveyDes" id="surveyDes" rows="4" cols="" >${surveyInfo.surveyDes}</textarea></div>
		    </div>
		    <div class="form-group col-md-12">
		     	<label class="control-label" for="connectGasDes">接气方案描述</label>
		     	<div> 
		        	<textarea class="form-control field-editable" name="connectGasDes" id="connectGasDes" rows="4" cols="" >${surveyInfo.connectGasDes}</textarea>
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
    $("#surveyDetailform").toggleEditState().styleFit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->