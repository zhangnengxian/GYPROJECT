<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_plan" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">业务部门</label>
            <div>
                <select class="form-control input-sm field-editable" name="deptId"  >
                    <option value=""></option>
                    <c:forEach var="enums" items="${department}">
                        <option value="${enums.correlatedInfoId}" >${enums.correlatedInfoDes}</option>
                    </c:forEach>

                </select>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">主合同是否有收款</label>
            <div>
                <select class="form-control input-sm field-editable" name="arStatus"  >
                    <option value=""></option>
                    <option value="2">已有收款</option>
                </select>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">计划开工日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="plannedStartDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="plannedStartDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">计划竣工日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="plannedEndDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="plannedEndDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">签订日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="contractSignDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="contractSignDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_plan").styleFit();
    
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->