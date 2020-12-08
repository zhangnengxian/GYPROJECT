<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_maerialPlan" action="/" method="POST">
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">工程名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label" for="">分包单位</label>
            <div>
                <select class="form-control input-sm field-editable" id="cuName"  name="cuName"  >
	 		      	<option value=""></option>
	                <c:forEach var="enums" items="${culist}">
						<option value="${enums.unitId}" >${enums.shortName}</option>
	                </c:forEach>
				</select>
            </div>
        </div>
        <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">反馈人</label>
            <div >
                <input type="text" class="form-control input-sm"  name="feedBacker"/>
            </div>
        </div>
         <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">项目经理</label>
            <div >
                <input type="text" class="form-control input-sm"  name="constructionPm"/>
            </div>
        </div>
         <div class="form-group col-lg-6 col-md-12 col-sm-6">
            <label class="control-label" for="">甲方代表</label>
            <div >
                <input type="text" class="form-control input-sm"  name="builder"/>
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
            <label class="control-label" for="applicationDate">反馈日期</label>
            <div class="input-group input-daterange">
	            <input type="text" class="form-control datepicker-default input-sm" name="applicationDateStart" >
	            <span class="input-group-addon">至</span> 
	            <input type="text" class="form-control datepicker-default input-sm" name="applicationDateEnd" >
            </div>
        </div>
        <div class="form-group col-md-12">           
            <label class="control-label" for="createTime">反馈领用日期</label>
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