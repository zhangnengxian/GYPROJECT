<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="form-box">
    <form class="form-horizontal" id="auditInfoForm" action="/" method="POST">
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            	<label class="control-label" for="">图纸编号</label>
            <div >
                <input type="text" class="form-control input-sm" id="" name= />
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
           		 <label class="control-label" for="">提出单位</label>
            <div >
                <input type="text" class="form-control input-sm" id="" name="" />
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            	<label class="control-label" for="">工程地点</label>
            <div >
                <input type="text" class="form-control input-sm" id="" name="" />
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
		         <label class="control-label" for="">图纸疑问</label>
		    <div> 
		         <textarea class="form-control field-editable" name="" id="" rows="4" cols="" data-parsley-maxlength="200" ></textarea>
		    </div>
	   </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
		     <label class="control-label" for="">图纸答疑</label>
		     <div> 
		         <textarea class="form-control field-editable" name="" id="" rows="4" cols="" data-parsley-maxlength="200" ></textarea></div>
		    </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $("#auditInfoForm").styleFit();

</script>
<!-- ================== END PAGE LEVEL JS ================== -->