<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <div class="col-md-5 col-sm-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">部门结构</h4>
                </div>
                <div class="panel-body">
                	<div style="padding-bottom: 10px; float:right;">
               			<input type="button" id="add" value="增加" onclick="addDept();" class="btn btn-sm btn-query"/>
                        <input type="button" id="edit" value="修改" onclick="editDept();" class="btn btn-sm btn-query"/>
                        <input type="button" id="del" value="删除" onclick="delDept();" class="btn btn-sm btn-warn"/>
                    </div>
                    <div id="deptTree"></div>
            	</div>
        	</div>
        </div>
        <div class="col-md-7 col-sm-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			         <h4 class="panel-title">业务操作</h4>
			    </div>
			    <div class="panel-body" id="dept_manage_panel_box" data-c="#dept/viewPage"></div>
			</div>
        </div>
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('部门管理 - 工程系统运行管理');
    
    $.getScript('assets/plugins/jstree/dist/jstree.min.js').done(function() {
	    $.getScript('projectjs/dept/dept-manage.js?v='+Math.random()).done(function () {
	        deptManage.init();
	    });
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->