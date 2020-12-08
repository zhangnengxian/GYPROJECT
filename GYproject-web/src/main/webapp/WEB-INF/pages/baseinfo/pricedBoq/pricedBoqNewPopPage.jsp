<!-- pricedBoqSearchPopPage.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_pricedBoq" action="/" method="POST">
   		<input type="hidden" id="qsId" name="qsId"/>
      		<div class="form-group col-md-6 ">
	    	<label class="control-label" for="costType">造价类型</label>
	        <div>
	        	<select id="costType" class="form-control input-sm field-editable" name="costType" >
					<option value="-1"><c:out value="${costType}"></c:out></option>
					<c:forEach var="enums" items="${costTypeDes}">
           				<option value="${enums.value}">${enums.message}</option>
       				</c:forEach>
   				</select>
	        	<!-- <input type="text" class="form-control input-sm field-editable"  id="costTypeDes" name="costTypeDes" value=""/> -->
	        </div>
	    </div>
	    <div class="form-group  col-md-12 ">
	        <label class="control-label" for="subitemName">分部分项工程名称</label>
	        <div>
	            <input type="text" class="form-control input-sm field-editable"  id="subitemName" name="subitemName" data-parsley-maxlength="100" value=""/>
	        </div>
	    </div>
	    <div class="form-group col-md-6">
	        <label class="control-label" for="unit">单位</label>
	        <div>
	            <input type="text" class="form-control input-sm field-editable"  id="unit" name="unit" data-parsley-maxlength="100" value=""/>
	        </div>
	    </div>
	    <div class="form-group col-md-6 ">
	        <label class="control-label" for="unitPrice">劳务单价</label>
	        <div>
	            <input type="text" class="form-control input-sm field-editable fixed-number"  id="unitPrice" name="unitPrice" data-parsley-maxlength="100" value=""/>
	        </div>
	    </div>
	    <div class="form-group col-md-12">
	        <label class="control-label" for="subitemContent">工作内容及项目特征</label>
	        <div>
	        	<textarea rows="" cols="" class="form-control input-sm field-editable"  id="subitemContent" name="subitemContent" data-parsley-maxlength="800" value=""></textarea>
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