<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="clearboth form-box">
    <form class="form-horizontal" id="searchForm_fallBack" action="/" method="POST">
        <div class="form-group col-md-12">
            <label class="control-label" for="corpId">分公司</label>
            <div>
                <select class="form-control input-sm " id="corpId" name="corpId">
                    <option value=""></option>
                    <c:forEach items="${corp}" var="enums">
                        <option value="${enums.deptId}">${enums.deptName}</option>
                    </c:forEach>
                </select>
            </div>

        </div>
        <div class="form-group col-sm-6 col-xs-12">
            <label class="control-label" for="projectType">工程类型</label>
            <div>
                <select class="form-control input-sm field-editable" id="projectType"  name="projectType" >
                    <option value=""></option>
                    <c:forEach var="enums" items="${projType}">
                        <option value="${enums.projTypeId}">${enums.projConstructDes}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-sm-6 col-xs-12">
            <label class="control-label" for="contributionMode">出资方式</label>
            <div>
                <select class="form-control input-sm field-editable" id="contributionMode"  name="contributionMode" >
                    <option value=""></option>
                    <c:forEach var="enums" items="${contributionMode}">
                        <option value="${enums.id}">${enums.contributionDes}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    $("#searchForm_fallBack").styleFit();
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->