<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_accessory" action="/" method="POST">
        <div class="form-group col-md-6">
            <label class="control-label" for="isPrint">分公司</label>
            <div>
            	<select class="form-control input-sm " id="corpId" name="corpId">
            		<option value=""></option>
            		<c:forEach items="${corp}" var="enums">
            			<option value="${enums.deptId}">${enums.deptName}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-6 col-sm-6">
            <label class="control-label" for="">资料名称</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="accessoryName"/>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_accessory").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->