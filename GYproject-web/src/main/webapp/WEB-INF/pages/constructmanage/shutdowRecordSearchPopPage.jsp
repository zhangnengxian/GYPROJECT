<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_shutdownRecord" action="/" method="POST">
     <!-- <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" for="">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div> -->
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="control-label" for="sdrNo">停工编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="sdrNo"/>
            </div>
        </div>
         <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="sdrType">停复工类型</label>
            <div class="input-group input-daterange">
            	<select class="form-control input-sm" name="sdrType">
            		<option value="">--选择--</option>
            		<option value="1">停工</option>
            		<option value="2">复工</option>
            	</select>
            </div>
        </div>
         <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="control-label" for="sdrProcess">停工工序</label>
            <div >
                <input type="text" class="form-control input-sm"  name="sdrProcess"/>
            </div>
        </div> 
        <!-- <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">停工日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="shutdownDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="shutdownDateEnd" >
            </div>
        </div> -->
       
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_shutdownRecord").styleFit();
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
     });
  });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->