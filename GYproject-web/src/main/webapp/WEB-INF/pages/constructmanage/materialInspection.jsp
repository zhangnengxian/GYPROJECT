<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet">
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">材料报验</h4>
                </div>
                <div class="panel-body">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <form action="" id="materialInspectionForm">
				        <table id="materialInspectionTable" class="table table-striped table-bordered nowrap " width="100%">
		            		<thead>
		                		<tr>
					                <th>材料名称</th>
			                		<th width="150">规格型号</th>
					                <th width="60">单位</th>
					                <th width="60">单价</th>
					                <th width="80">设计总量</th>
					                <th width="80">领用总量</th>
					                <th width="80">报验总量</th>
					                <th width="100">本次报验量</th>
		                		</tr>
			               	</thead>
		    	  	  	</table>
	    	  	  	</form>
            	</div>
        	</div>
        </div>
    </div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('材料报验 - 工程施工管理系统 ');
    //是否已选工程项目
    if(!getProjectInfo()){
    	loadProjectList();
    }
    $.getScript('assets/plugins/DataTables/media/js/jquery.dataTables.js').done(function () {
        $.getScript('assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js').done(function () {
            $.getScript('assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.min.js').done(function () {
                $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js').done(function () {
                    $.getScript('assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js').done(function() {
                        $.getScript('assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js').done(function () {
	                        $.getScript('assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js').done(function () {
	                         	$.getScript('assets/plugins/parsley/dist/parsley.js').done(function () {
	                          		$.getScript('projectjs/constructmanage/material-inspection.js').done(function () {
	                          			materialInspection.init();
	                              	});
	                         	});
	                        });
                        });
                    });
                });
            });
        });
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->