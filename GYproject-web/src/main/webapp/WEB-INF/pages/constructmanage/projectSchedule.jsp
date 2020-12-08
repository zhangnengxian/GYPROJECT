<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程进度</h4>
                </div>

                <div class="panel-body">
                	<input type="hidden" id="projStartDate" />
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <form action="" id="projectScheduleForm">
				        <table id="projectScheduleTable" class="table table-bordered nowrap " width="100%">
		            		<thead>
		                		<tr>
		                		    <th>分项工程名称</th>
		                		    <th width="50">单位</th>
					                <th>预计总工程量</th>
					                <th>累计完成工程量</th>
					                <th>完成百分比</th>
					                <th width="80">本次完成量</th>
		                		</tr>
			               	</thead>
		    	  	  	</table>
	    	  	  	</form>

					<%--形象进度记录--%>
					<table id="graphicProgressTable" class="table table-bordered nowrap hidden" width="100%">
						<thead>
							<tr>
								<th>工程名称</th>
								<th>工序名称</th>
								<th>完成进度</th>
								<th>完成时间</th>
							</tr>
						</thead>
					</table>

	    	  	  	<div class="schedule-bar clearboth" style="margin-bottom: -10px;">
                        <div class="progress progress-striped active">
                            <div class="progress-bar totalProgress" style="width: 0%">工程进度 0%</div>
                        </div>
	    	  	  	</div>
					<%--<form action="" id="projectSched">--%>
					<div class="form-group col-sm-6 col-xs-12 hidden">
						<!-- 新加字段 -->
						<label class="control-label" for="nuitProject1">工序名称</label>
						<div>
							<select class="form-control input-sm field-editable" id="nuitProject1"  name="nuitProject"  >
								<option value="1"></option>
								<c:forEach var="enums" items="${progressType}">
									<option value="${enums.gpId}">${enums.gpName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 hidden">
						<!-- 新加字段 -->
						<label class="control-label" for="finishProgress1">完成进度</label>
						<div>
							<input type="text" class="form-control input-sm field-not-editable"  id="finishProgress1" name="finishProgress" value="" />
						</div>
					</div>
					<%--</form>--%>
            	</div>
        	</div>
        </div>
    </div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程进度 - 工程施工管理系统 ');
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
   		$.getScript('projectjs/constructmanage/project-schedule.js').done(function () {
   			projectSchedule.init();
       	});
    }
</script>
<!-- ================== END PAGE LEVEL JS ================== -->