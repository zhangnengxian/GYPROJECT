<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_maerialPlan" action="/" method="POST">
         <!-- <div class="form-group col-md-6">
            <label class="control-label" for="createStaffName">创建人名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="createStaffName"/>
            </div>
        </div> -->
        <div class="form-group col-md-6">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="area">大区</label>
            <div>
                <select class="form-control input-sm field-editable"   name="area"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${area}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	             </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="builder">甲方代表</label>
            <div >
                <input type="text" class="form-control input-sm"  name="builder"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="constructionPm">项目经理</label>
            <div >
                <input type="text" class="form-control input-sm"  name="constructionPm"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="constructionUnit">施工管理处</label>
            <div>
                <select class="form-control input-sm field-editable"   name="constructionUnit"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${manageOfficeList}">
						<option value="${enums.id}" >${enums.name}</option>
	                </c:forEach>
	             </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="constructionUnit">分包单位</label>
            <div>
                <select class="form-control input-sm field-editable"   name="cuName"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${culist}">
						<option value="${enums.unitId}" >${enums.shortName}</option>
	                </c:forEach>
	             </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="isFeedBack">是否反馈</label>
            <div>
                <select class="form-control input-sm field-editable"   name="isFeedBack"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${isFeedBack}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	             </select>
            </div>
        </div>
         <div class="form-group col-md-12">           
            <label class="control-label" for="applicationDate">申请日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="applicationDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="applicationDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="createTime">计划领用日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="planReceiveDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="planReceiveDateEnd" >
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_maerialPlan").styleFit();
    
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== --></html>