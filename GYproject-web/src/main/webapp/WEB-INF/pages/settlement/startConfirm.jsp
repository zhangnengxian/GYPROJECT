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
                    <h4 class="panel-title">待结算初审列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" class="stepIds" value="1902,1904">
	            	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
	            	 <input type="hidden" class="sessionprojNo" name="sessionprojNo" value="${projNo }">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="reportConfirmTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th>工程编号</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>送审金额</th>
                                <th>送审日期</th>
                                <th>审核</th>
                                <th></th>
                               <!--  <th></th> -->
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
    App.setPageTitle('结算初审 - 工程管理系统');
    $.getScript('projectjs/settlement/start-confirm.js?v='+Math.random()).done(function (){
    	
    	reportConfirmAudit.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->