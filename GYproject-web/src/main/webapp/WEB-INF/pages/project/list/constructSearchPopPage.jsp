<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_list" action="/" method="POST">
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
        <div class="form-group col-md-6">
            <label class="control-label" for="projLtypeId">工程类型</label>
            <div >
               <!--  <input type="text" class="form-control input-sm"  name="projLtypeId"/> -->
                <select class="form-control input-sm" id="projLtypeId" name="projLtypeId">
                	<option></option>
                	<c:forEach var="enums" items="${projLtype }">
                		<option value="${enums.projTypeId }">${enums.projConstructDes }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-md-6">
	        <label class="control-label" for="contributionMode">出资方式</label>
	        <div >
	            <select class="form-control input-sm" id="contributionMode" name="contributionMode">
	                <option></option>
	                <c:forEach var="enums" items="${contributionMode}">
	                    <option value="${enums.id }">${enums.contributionDes }</option>
	                </c:forEach>
	            </select>
	        </div>
		</div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">分包单位</label>
            <div>
                <select class="form-control input-sm field-editable" id="cuId"  name="cuId"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${culist}">
						<option value="${enums.unitId}" >${enums.shortName}</option>
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
            <label class="control-label" for="">分包签定日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="subContractDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="subContractDateEnd" >
            </div>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-6">           
            <label class="control-label" for="text2">开工日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="startDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="startDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_list").styleFit();

 	$('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->