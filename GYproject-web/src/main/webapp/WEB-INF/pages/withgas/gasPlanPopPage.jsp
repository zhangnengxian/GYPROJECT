<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_gasPlan" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label" for="">计划名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="gpName"/>
            </div>
        </div>
        <%--<div class="form-group col-md-12">           --%>
            <%--<label class="control-label" for="">受理日期</label>--%>
            <%--<div class="input-group input-daterange">--%>
	            <%--<input type="text" class="form-control datepicker-default input-sm" name="acceptDateStart" >--%>
	            <%--<span class="input-group-addon">至</span> --%>
	            <%--<input type="text" class="form-control datepicker-default input-sm" name="acceptDateEnd" >--%>
            <%--</div>--%>
        <%--</div>--%>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_gasPlan").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->