<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_draw" action="/" method="POST">
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="custName">客户名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="custName"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="projAddr">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="projScaleDes">工程规模</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projScaleDes"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="surveyer">勘察人</label>
            <div >
                <input type="text" class="form-control input-sm"  name="surveyer"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="">受理日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateEnd" >
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">勘察日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="surveyDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="surveyDateEnd" >
            </div>
        </div>
       
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_draw").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->