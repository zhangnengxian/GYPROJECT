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
                    <h4 class="panel-title">待踏勘审核列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="projNo" name="projNo" value="${projNo }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="gasAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th width="120">工程编号</th>
                                <th width="120">工程名称</th>
                                <th width="120">工程类型</th>
                                <th width="120">出资方式</th>
                                <th width="80">勘察人</th>
                                <th width="80">勘察日期</th>
                                <th width="80">剩余时限(天)</th>
                                <th width="120">确认操作</th>
                                <th width="120"></th>
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
    App.setPageTitle('踏勘审批 - 工程管理系统');
    
    $.getScript('projectjs/design/connect-gas-audit.js?v='+Math.random()).done(function() {
        connectGasAudit.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->