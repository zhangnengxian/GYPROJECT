<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>

<div class="infodetails">
	<div class="toolBtn f-r m-b-15  editbtn">
		<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 saveBtn" >保存</a>
		<a href="javascript:;" class="btn btn-warn btn-sm m-l-5 cancelBtn">放弃</a>
	</div>
	<div class="clearboth form-box">
		<form class="form-horizontal" id="planAuditForm" action="">
  			<div class="form-group col-lg-12 col-md-12 col-sm-6 ">
      			<label class="control-label" for="">审批结果</label>
	           	<label>
	           		<input type="radio" name="result" value="1" checked/>通过
	           	</label>
	           	<label>
	           		<input type="radio" name="result" value="2" />不通过
	           	</label>
 			</div>
 			<div class="form-group col-lg-12 col-md-12 col-sm-12">
	     		<label class="control-label" for="">审批意见</label>
	     		<div> 
					<textarea class="form-control field-editable" name="" id="" rows="4" cols="" data-parsley-maxlength="200" >同意该施工计划</textarea>
				</div>
			</div>
			<div class="form-group col-lg-6 col-md-12 col-sm-6">
	        	<label class="control-label" for="">审批人</label>
	       		<div>
	           		<input type="text" class=" form-control input-sm field-editable" id=""  name="" data-parsley-maxlength="100" value="王猛">
	        	</div>
	    	</div>
	    	<div class="form-group col-lg-6 col-md-12 col-sm-6 " >
	        	<label class="control-label" for="">审批时间</label>
	        	<div>
	           <input type="text" class=" form-control input-sm field-editable datepicker-default" id=""  name="" data-parsley-maxlength="100" value="2016-5-16">
	        	</div>
	    	</div>
		</form>
	</div>
	<div >
	<h4 class="m-t-15 m-l-7"><strong>审批历史</strong></h4>
  	</div>
  	<table id="auditHistoryTable" class="table table-striped table-bordered nowrap" width="100%"">
		<thead>
          	<tr>
              <th>审批日期</th>
              <th>审批结果</th>
              <th>审批意见</th>
              <th>审批人</th>
         	</tr>
      	</thead>
	</table>	
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    //隐藏loading
    $(".infodetails").hideMask();
    $("#planAuditForm").styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
    //初始化审批历史列表
    planHistoryInit();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->