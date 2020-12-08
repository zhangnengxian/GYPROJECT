<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_gasProject" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label">合同编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="scNo"/>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label">工程地点</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label">计划状态</label>
            <div >
                <select class="form-control" id="gasProjStatus" name="gasProjStatus">
                <option></option>
                <c:forEach var="enums" items="${gasProjectStatusEnum }">
                	<option value="${enums.value} ">${enums.message}</option>
                </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" >填报日期</label>
            <div class="input-group input-daterange">
                <input type="text" class="form-control datepicker-default input-sm" name="preparDateStart" >
                <span class="input-group-addon">至</span>
                <input type="text" class="form-control datepicker-default input-sm" name="preparDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_gasProject").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->