<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_preinsection" action="/" method="POST">
        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-6">
            <label class="control-label" for="">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="isPrint">是否打印</label>
            <div>
            	<select class="form-control input-sm " id="isPrint" name="isPrint">
            		<option value=""></option>
            		<c:forEach items="${isPrint}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">预验日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="piDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="piDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_preinsection").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->