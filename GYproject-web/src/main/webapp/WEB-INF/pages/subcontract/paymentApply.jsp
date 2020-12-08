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
                    <h4 class="panel-title">付款申请工程列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
					<input type="hidden" id="stepId" name="stepId" value="${stepId }">
					<input type="hidden" name="businessOrderId"  id="businessOrderId" class="accBusRecordId" value="-1"/>
                    <table id="projectTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th></th>
                            	<th>工程编号</th>
                                <th>工程名称</th>
                                <th>签订日期</th>
                                <th>合同金额</th>
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
			         <ul class="nav nav-tabs">
			         	<li class="active"><a href="#default-tab-2" id="listTab" data-toggle="tab">列表区</a></li>
		                <li class=""><a href="#default-tab-1" id="detailTab"  data-toggle="tab">操作区</a></li>
	                </ul>
			    </div>
			    <div class="panel-body" id="">
				    <div class="tab-content">
				    	<div class="tab-pane fade in btn-top" id="default-tab-1">
                        	<div id="sub_contract_panel_box"></div>
                        </div>
                        <div class="tab-pane fade in btn-top active" id="default-tab-2">
                        	<input type="hidden" id="projId1" name="projId1"/>
		               		<table id="paymentApplyTable" class="table table-striped table-bordered nowrap" width="100%">
				                <thead>
					                <tr>
					                    <th>Id</th>
					                    <th>申请人</th>
					                    <th>申请金额(元)</th>
					                    <th>申请时间</th>
					                    <th>审核状态</th>
					                    <th>审核状态</th>
					                </tr>
				                </thead>
		           			</table>
                        </div>
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
    App.setPageTitle('付款申请 - 工程管理系统');
    
    $.getScript('projectjs/subcontract/payment-apply.js?v='+Math.random()).done(function () {
    	paymentApplyTable.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->