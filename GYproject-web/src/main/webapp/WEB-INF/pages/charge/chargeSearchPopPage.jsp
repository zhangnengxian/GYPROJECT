<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_accept" action="/" method="POST">
        <%-- <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程大类</label>
            <div>
                <select class="form-control input-sm field-editable" id="projLtypeId"  name="projLtypeId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${projLtype}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	              </select>
            </div>
        </div> --%>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程状态</label>
            <div>
                <select class="form-control input-sm"   name="projStatusId"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${projStatus}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" for="">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">
            <label class="control-label" for="custName">客户名称</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="custName"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6 arFlag">
            <label class="control-label" for="">收付标志</label>
            <div>
                <select class="form-control input-sm"   id="arFlag" name="arFlag"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${arFlagEnum}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6 arStatus">
            <label class="control-label" for="">收付状态</label>
            <div>
                <select class="form-control input-sm" id="arStatus" name="arStatus"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${arPayStatusEnum}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div>
       <%--  <div class="form-group col-lg-6 col-md-12 col-sm-6 arPayStatus">
            <label class="control-label" for="">付款状态</label>
            <div>
                <select class="form-control input-sm"  id="arPayStatus" name="arPayStatus"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${arPayStatusEnum}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div> --%>
        <div class="form-group col-lg-6 col-md-12 col-sm-6 collectionType">
            <label class="control-label" for="">收付类型</label>
            <div>
                <select class="form-control input-sm"   name="collectionType"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${collectionType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="">受理日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="acceptDateEnd" >
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">勘察日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="surveyDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="surveyDateEnd" >
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">设计完成日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="duCompleteDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="duCompleteDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_accept").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
 	
 	$('.arPayStatus').addClass('hidden');
 	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->