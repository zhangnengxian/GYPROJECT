<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_annualPlan" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label">计划名称</label>
            <div >
                <input type="text" class="form-control input-sm"  name="planName"/>
            </div>
        </div>
         <div class="form-group col-md-6">
            <label class="control-label">项目类型</label>
            <div>
                <select class="form-control input-sm field-not-editable" id="projectType"  name="projectType" data-parsley-required="true" >
                    <option value=""></option>
                    <c:forEach var="enums" items="${projLtype}">
                        <option value="${enums.correlatedInfoId}">${enums.correlatedInfoDes}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-md-6">
            <label class="control-label">项目地点</label>
            <div>
            	<input type="text" class="form-control input-sm"  name="planAddr"/>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
    $("#searchForm_annualPlan").styleFit();
    $('.datepicker-default').datepicker({
    	todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->