<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
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
                <h4 class="panel-title">安全质量细则</h4>
			</div>
            <div class="panel-body">
               	<div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                     	<table id="safetyPunishTable" class="table table-striped table-bordered nowrap" width="100%">
                          <thead>
                              <tr>
                                  <th>编号</th>
                                  <th>安全质量细则名称</th>
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
                    <h4 class="panel-title">维护区</h4>
              </div>
              <div class="panel-body" id="safetyPunish_establish_panel_box"></div>
          	</div>
		</div>	  
	</div>
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('安全质量细则 - 工程施工管理系统');
    
    $.getScript('assets/plugins/DataTables/media/js/jquery.dataTables.js').done(function () {
        $.getScript('assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js').done(function () {
            $.getScript('assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.js').done(function () {
                $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js').done(function () {
                    $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.flash.js').done(function () {
                        $.getScript('assets/plugins/DataTables/extensions/Buttons/js/buttons.html5.min.js').done(function () {
                            $.getScript('assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js').done(function () {
                                $.getScript('assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js').done(function () {
                                	$.getScript('assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js').done(function () {
                                	    $.getScript('assets/plugins/parsley/dist/parsley.js').done(function () {
                                	    	$.getScript('projectjs/baseinfo/safety-punish.js?v='+Math.random()).done(function () {
                                	    		safetyPunish.init();
                                	    	});
			                            });
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