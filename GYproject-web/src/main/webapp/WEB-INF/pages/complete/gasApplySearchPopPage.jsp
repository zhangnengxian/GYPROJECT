<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchGasApplyForm" action="/" method="POST">
	    <div class="form-group col-md-6 col-sm-6">
	    	<label class="control-label" for="">状态</label>
	    	<div>
				<select id="manuIdpop" class="form-control input-sm field-editable " style="width:200px;" name="manuIdpop" >
					<option value=""></option>
					 <c:forEach var="enums" items="${confrimState}">
					 	<option value="${enums.value}" >${enums.message}</option>
		             </c:forEach>
				</select>
			</div>
		</div>
	    <div class="form-group col-md-12 col-sm-6">
            <label class="control-label" for="projName">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-12 col-sm-6">           
            <label class="control-label" for="gasApplyTime">通气申请日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="gasApplyTimeStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="gasApplyTimeEnd" >
            </div>
        </div>
        <div class="form-group col-md-12 col-sm-6">           
            <label class="control-label" for="confirmGas">确认通气日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="confirmGasTimeStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="confirmGasTimeEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchGasApplyForm").styleFit();
    
 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->