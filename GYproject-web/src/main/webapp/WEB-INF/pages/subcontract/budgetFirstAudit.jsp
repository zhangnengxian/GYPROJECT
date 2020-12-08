<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待预算审核工程列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="projId" name="projId"/>
                	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
                	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
					<input type="hidden" class="stepIds" value="160201,1603">
					<input type="hidden" class="sessionprojNo" name="sessionprojNo" value="${projNo }">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="qualitiesAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th width="120"></th>
                                <th width="120">工程编号</th>
                                <th width="160">工程名称</th>
                                <th width="160">燃气公司</th>
                                <th width="160">施工单位</th>
                                <th width="160">工程地点</th>
                                <th width="80">预算金额(元)</th>
                                <th width="80">工程状态</th>
                                <th width="80">剩余时限(天)</th>
                                <th width="60">审批操作</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('预算初审 - 工程管理系统');
    
    $.getScript('projectjs/subcontract/budget_first-audit.js?v='+Math.random()).done(function() {
        qualitiesAudit.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->