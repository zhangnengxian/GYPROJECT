<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
		<div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">待签智能表合同工程列表</h4>
                </div>
                <div class="panel-body">
                	<input  type="hidden" id="cuUnitTypeId" name="cuUnitTypeId" value="${cuUnitTypeId}"/>
                	<input  type="hidden" id="deptTypeId" name="deptTypeId" value="${deptTypeId}"/>
                	<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
	            	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="subContractIntelligentTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th></th>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>工程状态</th>
                                <th>推送状态</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- end col-6 -->
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
	                <h4 class="panel-title">操作区</h4>
			    </div>
			    <div class="panel-body" id="">
				    <div class="tab-content">
                        	<div id="sub_contract_Intelligent_panel_box"></div>
				    </div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('智能表合同 - 工程管理系统');
    
    $.getScript('projectjs/subcontract/sub-contract-intelligent.js?v=1001').done(function () {
        subContractIntelligent.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->