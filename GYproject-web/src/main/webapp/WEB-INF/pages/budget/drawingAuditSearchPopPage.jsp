<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_drawing" action="/" method="POST">
        <%-- <div class="form-group col-md-6">
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
        
        <div class="form-group col-md-6">
            <label class="control-label" for="designer">设计人</label>
            <div >
                <input type="text" class="form-control input-sm"  name="designer"/>
            </div>
        </div>
        <div class="form-group  col-md-12 ">           
            <label class="control-label" for="text2">设计日期</label>
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
    $("#searchForm_drawing").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->