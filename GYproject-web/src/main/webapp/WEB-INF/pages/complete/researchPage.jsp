<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchAcceptanceApplyPrintPop" action="/" method="POST">
        <div class="form-group col-md-6">
            <label class="control-label" for="projName">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="projAddr">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="isPrint">是否打印</label>
            <div>
            	<select class="form-control input-sm " id="isPrint" name="isPrint">
            		<option value="">全部</option>
            		<c:forEach items="${isPrint}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="conNo">合同编号</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="conNo"/>
            </div>
        </div>
        <div class="form-group col-md-12 col-sm-6">           
            <label class="control-label" for="">申请日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="applyDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="applyDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchAcceptanceApplyPrintPop").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->