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
                    <h4 class="panel-title">签证记录审核列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="projId" name="projId"/>
                    <input type="hidden" id="businessOrderId"  value="${businessOrderId }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="designAlterationAuditTable" class="table table-hover table-striped table-bordered nowrap" data-attach-table="all" width="100%">
               			<thead>
                			<tr>
                			    <th>签证编号</th>
                				<th>Id</th>
                				<th>工程编号</th>
	                			<th>工程名称</th>
	                			<th>金额(元)</th>
                                <th>签证类型</th>
                                <th>预算日期</th>
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
    App.setPageTitle('签证审核 - 工程管理系统');
    
 /*  //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }else{ */
    	$.getScript('projectjs/constructmanage/engineering-audit.js?v='+Math.random()).done(function () {
    		designAlterationAudit.init();
    	});
    //}
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->