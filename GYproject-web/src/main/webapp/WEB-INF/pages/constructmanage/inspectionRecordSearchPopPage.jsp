<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_inspection" action="/" method="POST">
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="">检查日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="checkDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="checkDateEnd" >
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">扣分范围</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control  input-sm" name="totalStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control  input-sm" name="totalEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_inspection").styleFit();
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
     });
  });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->