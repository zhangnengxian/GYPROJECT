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
                    <h4 class="panel-title">待结算初审工程列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
	            	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">	            	
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="settlementAuditStartTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th></th> 
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>状态</th>
                                <th>剩余时限(天)</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="auditStartContent" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <ul class="nav nav-tabs">
		                <li class="active"><a href="#default-tab-1" id="settlementInfo"  data-toggle="tab">初审信息</a></li>
		                <li class=""><a href="#default-tab-2" id="qualitiesTab"   data-toggle="tab">工程量</a></li>        
	                </ul>
			    </div>
<!-- 			    <div class="panel-body" id="settlement_auditStart_panel_box"></div> -->

				<div class="panel-body" >
<!-- 					<input type="hidden" id="projId1" name="projId"/> -->
					<div class="tab-content">
						<div class="tab-pane fade active in btn-top" id="default-tab-1">
                        	<div id="settlement_auditStart_panel_box">
                        	</div>
                        </div>
                        
                        <div class="tab-pane fade in btn-top" id="default-tab-2">
                        	<div id="qualities_list_box">
                        		<form id="qualitiesForm" data-parsley-validate="true">
                        			<input type="hidden" class="form-control field-editable" id="init_quantitiesTotal" name="quantitiesTotal" value=""/>
									<table id="qualitiesTable" class="table table-hover table-bordered nowrap" width="100%">
								    	<thead>
									        <tr>
									            <th>id</th>
									            <th>id</th>
									            <th>分部分项工程名称</th>
									            <th width="60">单位</th>
									            <th width="60">单价</th>
									            <th width="60">数量</th>
									            <th width="60">金额</th>
									        </tr>
								      </thead>
								      <tfoot>
								      		<tr>
								      		    <td></td>
								      		    <td></td>
								      			<td>合计</td>
								      			<td></td>
								      			<td></td>
								      			<td></td>
								      			<td class="total-amount"></td>
								      		</tr>
								      </tfoot>
									</table>
								</form>
                        	</div>
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
    App.setPageTitle('结算初审 - 工程管理系统');

    $.getScript('projectjs/settlement/settlement-audit-start.js?v=1081').done(function () {
    	$.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
    		sttlementAuditStart.init();
        });
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->