<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
        <div class="col-md-12 col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">控制面板</h4>
                </div>
                <div class="panel-body">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                </div>
             </div>
        </div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('控制面板 - 工程管理系统 ');
   
    $.getScript('assets/plugins/DataTables/media/js/jquery.dataTables.js').done(function () {
        $.getScript('assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js').done(function () {
            $.getScript('assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.js').done(function () {
                $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js').done(function () {
                    $.getScript('assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js').done(function () {
                        $.getScript('assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js').done(function () {
                        	$.getScript('assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js').done(function () {
                        	    $.getScript('projectjs/system/dashboard.js').done(function () {
                        	    	dashboard.init();
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