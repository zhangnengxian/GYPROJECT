<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_correlation" action="/" method="POST">
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
        <div class="form-group col-sm-6 col-xs-12">
	            <label class="control-label" for="">关联类型</label>
	             <div>
		    		<select class="form-control input-sm field-editable" id="corType"  name="corType" >
		 		      	<option value=""></option>
		 		      	<c:forEach var="enums" items="${correlationType}">
							   	<option value="${enums.value}">${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
	        </div>
	        <div class="form-group col-sm-6 col-xs-12">
	            <label class="control-label" for="">立项方式类型</label>
	             <div>
		    		<select class="form-control input-sm field-editable" id="acceptType"  name="acceptType" >
		 		      	<option value=""></option>
		 		      	<c:forEach var="enums" items="${acceptType}">
							   	<option value="${enums.value}">${enums.message}</option>
		                </c:forEach>
		             </select>
		        </div>
	        </div>
	        <div class="form-group col-sm-6 col-xs-12">
	            <label class="control-label" for="">规模类型</label>
	             <div>
		    		<select class="form-control input-sm field-editable" id="scaleType"  name="scaleType" >
		 		      	<option value=""></option>
		 		      	<c:forEach var="enums" items="${scaleType}">
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
    $("#searchForm_correlation").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->