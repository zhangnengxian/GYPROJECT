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
	                <h4 class="panel-title">工程计划列表</h4>
				</div>
	            <div class="panel-body">
					<input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
					<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="planEstablishTable" data-attach-table="all" class="table table-bordered nowrap" width="100%">
                        <thead>
                    		<tr>
                             	<th></th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>下达日期</th>
                                <th>工程状态</th>
								<th>是否派工</th>
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
                    <h4 class="panel-title">监理派遣</h4>
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
    App.setPageTitle('监理派遣 - 工程管理系统');
    $('#planAuditForm').styleFit();
    
    $.getScript('projectjs/plan/supervisor-dispatch.js?v='+Math.random()).done(function () {
        projectPlan.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->