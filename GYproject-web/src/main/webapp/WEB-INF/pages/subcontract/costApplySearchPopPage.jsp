<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_paymentAudit" action="/" method="POST">
        <div class="form-group col-lg-12 col-md-6 col-sm-6">
            <label class="control-label" for="">请款单位</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="applyDeptName"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-6 col-sm-6">
            <label class="control-label" for="">审核状态</label>
            <div>
            	<select class="form-control input-sm" name="auditState">
            		<option></option>
            		<c:forEach var="enums" items="${auditResultEnum }">
            			<option value="${enums.value }">${enums.message }</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">请款日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="applyStartDate" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default  input-sm" name="applyEndDate" >
            </div>
        </div> 
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_paymentAudit").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->