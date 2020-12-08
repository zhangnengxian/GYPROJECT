<!-- pricedBoqSearchPopPage.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_pricedBoq" action="/" method="POST">
    	<div class="form-group col-md-12">
        	<label class="control-label" for="verId">版本号</label>
        	<div>
				<select id="verId" class="form-control input-sm field-editable width-150" name="veId" >
					<c:forEach var="version" items="${versions}">
						<option value="${version.veId}">${version.veNo}</option>
					</c:forEach>
				</select>
			</div>
		</div>
        <div class="form-group col-md-12">
            <label class="control-label" for="subitemName">分步分项工程名称</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="subitemName" id="subitemName"/>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_pricedBoq").styleFit();
    
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->