<!-- accessoryItem.jsp -->
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
                <h4 class="panel-title">工程规模</h4>
			</div>
			
            <div class="panel-body">
               	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <div class="tab-content">
                   	<div class="tab-pane fade active in btn-top" id="default-tab-1">
                       	<table id="projectScaleTable" class="table table-striped table-bordered nowrap" width="100%">
                            <thead>
                                <tr>
                                	<th></th>
                                    <th>规模名称</th>
                                    <th>工程类型</th>
                                </tr>
                            </thead>
                       	</table>
                   	</div>
                   	</div>
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
                    <h4 class="panel-title">维护区</h4>
              </div>
              
              <div class="panel-body" id="ps_panel_box">
             
              </div>
          	</div>
		</div>	    	        
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程规模 - 工程管理系统');
    
    $.getScript('projectjs/projectscale/project-scale.js').done(function () {
    	projectScale.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->