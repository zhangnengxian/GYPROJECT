<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_charge" action="/" method="POST">
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projNo"/>
            </div>
        </div>
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
       
        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
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
        <div class="form-group col-lg-6 col-md-12 col-sm-6 ">
            <label class="control-label" for="">票据类型</label>
            <div>
                <select class="form-control input-sm" id="billType" name="billType"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${billType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">票据状态</label>
            <div>
                <select class="form-control input-sm" id="billState" name="billState"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${billState}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            </div>
        </div>
       <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">金额范围</label>
            <div>
                <select class="form-control input-sm" id="billState" name="amountScale"  >
	 		        <option value=""></option>
	                <option value="1">2万以下</option>
					<option value="2">2万至5万</option>
					<option value="3">5万至20万</option>
					<option value="4">20万至50万</option>
					<option value="5">50万至200万</option>
					<option value="6">200万以上</option>
	            </select>
            </div>
        </div>
       <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">超时天数</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control  input-sm" name="overDaysStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control  input-sm" name="overDaysEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_charge").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
 	

 	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->