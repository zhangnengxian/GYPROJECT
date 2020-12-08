<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<div class="clearboth form-box" style="margin-bottom:-36px">
	<form class="form-horizontal" id="projectForm" action="/" method="POST">
		<div class="form-group col-lg-5 col-md-5 col-sm-5">
            <label class="control-label" for="projNo">工程编号</label>
            <div >
                <input type="text" class="form-control input-sm"  name="projNo"/>
            </div>
        </div>
        <div class="form-group col-lg-5 col-lg-5 col-md-5 col-sm-5">
			<label class="control-label" for="projName">工程名称</label>
		    <div >
                <input type="text" class="form-control input-sm"  name="projName"/>
            </div>
		</div>
        <div class="form-group  col-md-5 col-sm-5">
			<label class="control-label" for="projLtypeId">工程类型</label>
		    <div >
                <select class="form-control input-sm" id="projLtypeId" name="projLtypeId">
                	<option></option>
                	<c:forEach var="enums" items="${projLtype }">
                	<option value="${enums.projTypeId }">${enums.projTypeDes }</option>
                	</c:forEach>
                </select>
            </div>
		</div>
	</form>
</div>
<div>
    <div class="p-t-6">
        <div class="mask-html text-center">
            <div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
        </div>
        <form id="exportExcelProjInfo" name = "exportExcelProjInfo" action="" method = "post"></form>
        <table id="costApplyTablePop" class="table table-striped table-bordered nowrap " width="100%">
            <thead>
                <tr>
                	<th></th>
                    <th width="50px">工程编号</th>
                    <th width="50px">工程名称</th>
                    <th width="50px">工程规模</th>
                    <th width="50px">工程地点</th>
                    <th width="50px">施工结算审定价</th>
                    <th width="50px">质保金</th>
                    <th width="50px"></th>
                </tr>
            <thead>
        </table>
    </div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程列表 - 工程施工管理系统 ');
    $("#projectForm").styleFit();
    $.getScript('projectjs/subcontract/pay-project-pop.js?'+Math.random()).done(function () {
    	payProject.init();
    });
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->