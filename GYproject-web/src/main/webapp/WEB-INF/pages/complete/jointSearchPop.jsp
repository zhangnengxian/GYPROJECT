<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchJointPop" action="/" method="POST">
        <div class="form-group col-md-12 col-sm-6">
            <label class="control-label">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>

        <div class="form-group col-md-12 col-sm-6">
            <label class="control-label">工程地点</label>
            <div>
                <input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>

        <div class="form-group col-md-12 col-sm-6">
            <label class="control-label">分包签订日期</label>
            <div class="input-group input-daterange">
                <input type="text" class="form-control datepicker-default input-sm" name="subContractDateStart" >
                <span class="input-group-addon">至</span>
                <input type="text" class="form-control datepicker-default input-sm" name="subContractDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12 col-sm-6">
            <label class="control-label">开工日期</label>
            <div class="input-group input-daterange">
                <input type="text" class="form-control datepicker-default input-sm" name="startDateStart" >
                <span class="input-group-addon">至</span>
                <input type="text" class="form-control datepicker-default input-sm" name="startDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchJointPop").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->