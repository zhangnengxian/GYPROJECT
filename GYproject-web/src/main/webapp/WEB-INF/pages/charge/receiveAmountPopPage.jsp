<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchFormReceiveAmount" action="/" method="POST">
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
        <%--<input type="text" class="form-control input-sm hidden"  name="arFlag" value="1"/>--%>
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
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="projAddr"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="custName">客户名称</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="custName"/>
            </div>
        </div>
       <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="conNo">合同编号</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="conNo"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="paNo">受理单号</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="paNo"/>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6 arStatus">
            <label class="control-label" for="">收款状态</label>
            <div>
                <select class="form-control input-sm" id="arStatus" name="arStatus"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${arStatusEnum}">
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
            <label class="control-label" for="">收款类型</label>
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
            <div class="form-group col-lg-12 col-md-12 col-sm-6">
                <label class="control-label" for="text2">是否<span style="color: red">标红</span>工程</label>
                <div class="input-group input-daterange">
                    <select class="form-control input-sm"   name="redFlag"  >
                        <option value=""></option>
                        <option value="true">是</option>
                        <option value="false">否</option>
                    </select>
                </div>
            </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchFormReceiveAmount").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
 	
 	$('.arPayStatus').addClass('hidden');
 	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->