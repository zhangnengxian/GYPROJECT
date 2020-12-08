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
                    <h4 class="panel-title">碰口方案审核列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="projId" name="projId"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="touchPlanAuditTable" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>分包单位</th>
                                <th>申请碰口日期</th>
                                <th>确定碰口日期</th>
                                <th>审核</th>
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
    App.setPageTitle('碰口方案审批 - 工程管理系统');
    
  //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{
    	$.getScript('projectjs/constructmanage/touch-plan-audit.js').done(function () {
            touchPlanAudit.init();
    	});
    }
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->