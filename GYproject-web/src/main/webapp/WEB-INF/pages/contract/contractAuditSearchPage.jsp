<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_contractAudit" action="/" method="POST">
        <div class="form-group col-md-6">
            <label class="control-label" for="costMember">合同编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="conNo"/>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label class="control-label" for="">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
       <%--  <div class="form-group col-md-6">
            <label class="control-label" for="projLtypeId">资源来源</label>
            <div>
                <select class="form-control input-sm field-editable"   name="fundSource"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${fundSource}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="projLtypeId">付款方式</label>
            <div>
                <select class="form-control input-sm field-editable"  name="payType"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${payType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div> --%>
        <div class="form-group col-md-12">           
            <label class="control-label" for="">签订日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="signDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="signDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_contractAudit").styleFit();
    
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->