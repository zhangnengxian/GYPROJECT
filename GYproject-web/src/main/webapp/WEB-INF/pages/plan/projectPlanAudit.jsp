<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待审核计划列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="planAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>施工单位</th>
                                <th>监理公司</th>
                                <th>编制时间</th>
                                <th>编制人</th>
                                <th>剩余时限(天)</th>
                                <th>审核</th>
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
    App.setPageTitle('施工计划审批 - 工程管理系统');
    
    $.getScript('projectjs/plan/project-plan-audit.js?v='+Math.random()).done(function () {
        projectPlanAudit.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->