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
            <label class="control-label">工程类型</label>
            <div>
                <select class="form-control input-sm"  name="projectType" id = "projectType">
                <option value = "">==请选择==</option>
                <c:forEach items="${projectType}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach> 
                
                </select>
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