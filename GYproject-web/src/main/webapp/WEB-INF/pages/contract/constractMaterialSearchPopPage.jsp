<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_contract" action="/" method="POST">
        <%-- <div class="form-group col-md-6">
            <label class="control-label" for="projLtypeId">工程大类</label>
            <div>
                <select class="form-control input-sm field-editable" id="projLtypeId"  name="projLtypeId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${projLtype}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div> --%>
        
       
        <div class="form-group col-md-12">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
        	<label class="control-label" for="conNo">合同编号</label>
        	<div>
        		<input type="text" class="form-control input-sm" name="conNo"/>
        	</div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label" for="materialIsRegister">是否登记</label>
            <div>
            	<select class="form-control input-sm " id="materialIsRegister" name="materialIsRegister">
            		<option value=""></option>
            		<c:forEach items="${materialIsRegister}" var="enums">
            			<option value="${enums.value}">${enums.message}</option>
            		</c:forEach>
            	</select>
            </div>
        </div>
        <!--  <div class="form-group col-md-6">
            <label class="control-label" for="budgeter">预算员</label>
            <div >
                <input type="text" class="form-control input-sm"  name="budgeter"/>
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">受理日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">预算日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="budgetDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="budgetDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">造价确认日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="affirmCostDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="affirmCostDateEnd" >
            </div>
        </div> -->
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_contract").styleFit();
    
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->