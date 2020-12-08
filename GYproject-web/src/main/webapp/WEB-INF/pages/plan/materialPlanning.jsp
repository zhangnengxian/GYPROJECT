<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
        <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程列表</h4>
                </div>
                <div class="panel-body">
                    <table id="materialPlanningTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
	                            <tr>
	                            	<th>工程ID</th>
	                                <th>工程编号</th>
	                                <th>工程名称</th>
	                                <th>状态</th>
	                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <ul class="nav nav-tabs">
			        	<li class="active"><a href="#default-tab-1" id="procurPlanIdTab" data-toggle="tab">材料明细</a></li>
			        	<li class=""><a href="#default-tab-2" id="projectTeb" data-toggle="tab">工程信息</a></li>
			        </ul>			         
			    </div>
				<div class="panel-body" id="budget_register_panel_box">
					<input type="hidden" id="procurPlanId" name="procurPlanId"/>
					<input type="hidden" id="alreadyExport" value="${alreadyExport}"/>
					<input type="hidden" id="haveNotExport" value="${haveNotExport}"/>
               		<div class="tab-content">
               		<div class="tab-pane fade in btn-top active" id="default-tab-1">
               		<div id="quantities_box">
               		<table id="materialDetailTable" class="table table-striped table-bordered nowrap" width="100%">
		                <thead>
			                <tr>
				                <th></th>
		                		<th></th>
	                		    <th width="100">材料名称</th>
		                		<th width="70">规格型号</th>
				                <th width="60">单位</th>
				                <th width="60">单价</th>
				                <th width="70">设计总量</th>
				                <th width="70">领用总量</th>
				                <th width="70">欠量</th>
			                </tr>
		                </thead>
           			</table>
           			<div class="hidden">
	           			<form id="procurementPlanForm" method ="Post" action="materialPlanning/exportExcel">
	           				<input type="hidden" id="projId1" name="projId"/>
	           			</form>
           			</div>
           			</div>
           			</div>
           			<div class="tab-pane fade in btn-top" id="default-tab-2">
           				<div id="budget_sum_box"></div>
           			</div>
           			</div>
				</div>
			</div>
        </div>
    </div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	App.restartGlobalFunction();
	App.setPageTitle('材料明细 - 工程管理系统');
	
	$.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.flash.js').done(function () {
		$.getScript('projectjs/plan/material-planning.js').done(function () {
			materialPlanning.init();
		});
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->