<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="infodetails">
<input class = "hidden" name="stepId" id="stepId" value="${stepId}"/>
	<%-- <div class="clearboth form-box">
		<input class = "hidden" name="projId" id="projId" value="${projId}"/>
		<form class="form-horizontal" id="fallbackApplyForm" data-parsley-validate="true" action="" method="POST">
		    <div class="form-group col-lg-12 col-md-12 col-sm-6 ">
      			<label class="control-label" for="">审批结果</label>
           		<div>
            	<label>
	            	<input type="radio" name="mrResult" value="${enums.value}"/>${enums.message}
	            </label>
	        	</div>
 			</div>
 				<div class="form-group col-lg-12 col-md-12 col-sm-12">
	     		<label class="control-label" for="">审批意见</label>
	     		<div> 
     				<textarea class="form-control field-editable"  data-parsley-required="true" name="mrAopinion" id="mrAopinion" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
			</div>
		    <div class="form-group col-lg-6 col-md-12 col-sm-6">
	        	<label class="control-label" for="">审批人</label>
		        <div>
		           <input type="hidden"  id="mrCsr"  name="mrCsr" data-parsley-maxlength="100" value="${loginInfo.staffId}">
		           <input type="text" class=" form-control input-sm field-not-editable" id=""  name="" data-parsley-maxlength="100" value="${loginInfo.staffName}">
		        </div>
	    	</div>
		    <div class="form-group col-lg-6 col-md-12 col-sm-6 " >
		        <label class="control-label" for="">审批日期</label>
		        <div>
		           <input type="text" class=" form-control input-sm field-not-editable timestamp all" id="mrTime" value="${auditTime}"  name="mrTime" data-parsley-required="true" data-parsley-maxlength="100" >
		        </div>
		    </div>
		</form>
	</div> --%>
</div>
<div class="p-t-6 p-b-15 p-l-10">
  		<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%">
   		    <thead>
           		<tr>
	               <th>审批日期</th>
	               <th>审批级别</th>
	               <th>审批结果</th>
	               <th>审批意见</th>
	               <th>审批人</th>
         		</tr>
      		</thead>
	</table>
</div>
<script>
    App.restartGlobalFunction();
    //表单样式适应
    $("#fallbackApplyForm").toggleEditState(false).styleFit();
    
    $.getScript('projectjs/project/project-manage-record.js?v=1000').done(function () {
    	projectManageRecord.init();
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->