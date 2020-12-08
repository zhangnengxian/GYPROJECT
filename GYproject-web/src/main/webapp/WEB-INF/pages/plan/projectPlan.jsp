<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
	       	<div class="panel panel-inverse tabs-mixin" id="">
				<div class="panel-heading">
	               	<div class="panel-heading-btn">
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
	                <h4 class="panel-title">待编计划列表</h4>
				</div>
	            <div class="panel-body">
					<input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
					<input  type="hidden" id="suUnitTypeId" name="suUnitTypeId" value="${suUnitTypeId}"/>
	            	<input type="hidden" id="notThrough" name="notThrough" value="${notThrough }"/>
	            	<input type="hidden" id="stepId" name="stepId" value="${stepId }">
	            	<input type="hidden" id="curStepId" name="curStepId" value="${curStepId }">	            	
	               	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="planEstablishTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                    		<tr>
                             	<th></th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>合同金额</th>
                                <th>签订日期</th>
                                <th>状态</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                         </thead>
                    </table>
                </div>
            </div>
		</div>
	    <div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
            	<div class="panel-heading">
	                <div class="panel-heading-btn">
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
                    <h4 class="panel-title">计划编制</h4>
              </div>
              <div class="panel-body">
              	<div id="plan_establish_panel_box"></div>
              </div>
          	</div>
		</div>	    	        
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('计划编制 - 工程管理系统');
    $('#planAuditForm').styleFit();
    
    $.getScript('projectjs/plan/project-plan.js?'+Math.random()).done(function () {
        projectPlan.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->