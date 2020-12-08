<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_shutdownAproval" action="/" method="POST">
    <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="control-label" for="sdrNo">停工编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="sdrNo"/>
            </div>
        </div>
        <!--  <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="sdrType">停复工类型</label>
            <div class="input-group input-daterange">
            	<select class="form-control input-sm" name="sdrType">
            		<option value="">--选择--</option>
            		<option value="1">停工</option>
            		<option value="2">复工</option>
            	</select>
            </div>
        </div> -->
         <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" for="sdrProcess">停工工序</label>
            <div >
                <input type="text" class="form-control input-sm"  name="sdrProcess"/>
            </div>
        </div> 
           <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="pushStatus">推送状态</label>
            <div>
                <select class="form-control" name="pushStatus">
                	<option></option>
                	<c:forEach var="enums" items="${pushStatusEnum }">
                		<option value="${enums.value }">${enums.message }</option>
                	</c:forEach>
                </select>
            </div>
        </div> 
        <!-- <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="">复工日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="reworkDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="reworkDateEnd" >
            </div>
        </div> -->
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_shutdownAproval").styleFit();
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
     });
  });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->