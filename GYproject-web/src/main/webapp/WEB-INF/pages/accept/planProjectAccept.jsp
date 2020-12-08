<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin ">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                	</div>
                	<ul class="nav nav-tabs ">
						<li class="active"><a href="#projectList" data-toggle="tab"id ="projectTab">工程列表</a></li>
						<li class=""><a href="#planList" data-toggle="tab" id="planTab">计划列表</a></li>
					</ul>
                </div>
                <div class="panel-body " id="box">
                	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                	<div class="tab-content">
	                	<div class="tab-pane fade active in btn-top active" id="projectList" >
	                		<table class="table table-hover table-striped table-bordered nowrap" id="planProjectAcceptTable" width="100%" data-attach-table="all">
	                			<thead>
	                			<tr>
	                				<th>工程ID</th>
	                                <th>工程编号</th>
	                                <th>工程名称</th>
	                                <th>状态</th>
	                                <!-- <th></th> -->
	                			</tr>
	                			</thead>
	                		</table>
	                	</div>
	                	<div class="tab-pane fade in btn-top" id="planList">
						 	<table id="planTable" class="table table-striped table-bordered nowrap" width="100%">
								<thead>
								<tr>
									<th></th>
									<th>序号</th>
									<th>计划名称</th>
									<th>管径</th>
									<th>项目长度(千米)</th>
									<th>完成长度(千米)</th>
									<th>完成进度</th>
									<th>项目类别</th>
									<th>预算价(万元)</th>
									<th>已投资(万元)</th>
									<th>今年计划投资(万元)</th>
									<th>建设比例</th>
									<th>燃气公司</th>
								</tr>
	                           </thead>
		                    </table>
	                   </div>
                	</div>
            	</div>
			</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="">
			    <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                     <h4 class="panel-title">受理区</h4>
            	</div>
            	 <div class="panel-body" id="project_accept_panel_box">
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
    App.setPageTitle('计划立项 - 工程施工管理系统 ');
    $(".editbtn").addClass("hidden");
    
    $.getScript('assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js').done(function() {
    	$('.datepicker-default').datepicker({
            todayHighlight: true
        });
    });
 
	$.getScript('projectjs/accept/plan-project-accept.js?v='+Math.random()).done(function () {
      	planProjectAccept.init();
    });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->