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
                    <h4 class="panel-title">付款审核列表</h4>
                </div>
                <div class="panel-body">
                	
					<input type="hidden" name="businessOrderId"  id="businessOrderId" class="accBusRecordId" value="-1"/>
                    <input type="hidden"   id="busOrderId" value="${businessOrderId}"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="paymentAuditTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th width="120"></th>
                            	<th width="120">请款编号</th>
                            	<th width="120">工程费用类型</th>
                            	<th width="120">其他费用类型</th>
                                <th width="120">申请金额</th>
                                <th width="160">申请人</th>
                                <th width="160">审核人</th>
                                <th width="160">申请日期</th>
                                <th width="60">确认操作</th>
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
    App.setPageTitle('付款审核 - 工程管理系统');
    
    $.getScript('projectjs/subcontract/payment-audit.js?v='+Math.random()).done(function() {
        paymentAudit.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->