<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content" class="content">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="panel panel-inverse tabs-mixin">
				<div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <ul class="nav nav-tabs">
				       	<li class="active"><a href="#default-tab-1-1" id="workNoticeTab" data-toggle="tab">工作通知</a></li>
				     	<li class=""><a href="#default-tab-3-3" id="signNoticeTab"   data-toggle="tab">签字通知</a></li>
				     	<li class=""><a href="#default-tab-5-5" id="conTaskNoticeTab" data-toggle="tab">施工任务通知</a></li>
				   	</ul>
                </div>
                <div class=" panel-body ">
				<div class="tab-content">
			       	<div class="tab-pane fade active in btn-top" id="default-tab-1-1" >
			       		<div class="clearboth form-box m-t-5">
							<form id="scaleTableForm">
								<input type="text" id="searchId" placeholder="工程编号搜索" onkeydown="projNoSearch(this.value,event)" style="width: 100%;border:1px solid #def1d1;padding-left: 20px">
								<!--keydown的时候，如果只有一个输入框，页面就会自动刷新，所以添加一个隐藏的输入框就可以了   -->
								<input type="hidden" onkeydown="" style="width: 100%;border:1px solid #def1d1;padding-left: 20px">
								<table id="workNoticeTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
					            	<thead>
					                    <tr>
					                    	<th>ID</th>
					                    	<th>工程编号</th>
					                    	<th>工程名称</th>
					                        <th>操作步骤</th>
					                        <th>审核级别</th>
					                        <th>待办人</th>
					                        <th>链接</th>
					                        <th></th>
					                    </tr>
					                </thead>
					            </table>
							</form>
						</div>
					</div>
					<div class="tab-pane fade in btn-top" id="default-tab-3-3">
						<div class="clearboth form-box m-t-5">
							<form id="signNoticeTableForm">
								<table id="signNoticeTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
					            	<thead>
					                    <tr>
					                    	<th>ID</th>
					                    	<th>工程编号</th>
					                        <th>工程名称</th>
					                        <th>待签字岗位</th>
					                        <th>单据类型</th>
					                        <th>链接</th>
					                        <th></th>
					                    </tr>
					                </thead>
					            </table>
							</form>
						</div>
					</div>
					<div class="tab-pane fade in btn-top" id="default-tab-5-5">
						<div class="clearboth form-box m-t-5">
							<form id="disNoticeTableForm">
								<table id="disNoticeTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
					            	<thead>
					                    <tr>
					                    	<th>ID</th>
					                    	<th>工程编号</th>
					                        <th>工程名称</th>
					                        <th>工程状态</th>
					                    </tr>
					                </thead>
					            </table>
							</form>
						</div>
					</div>
			   	</div>
			   	</div>
			</div>
		</div>
	</div>
</div>
<div class="clearboth p-t-15">
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	$.getScript('projectjs/project/task-notice.js?v='+Math.random()).done(function () {
        taskNotice.init();
	});
   	
	if(_inApk){
   		$("#workNoticeTab").text("工作");
 		$("#auditNoticeTab").text("审核");
 		$("#signNoticeTab").text("签字");
 		$("#dispatchNoticeTab").text("派遣");
 		$("#conTaskNoticeTab").text("施工任务");
 		$("#chargeNoticeTab").text("收费");
   	 }else{
   		$("#workNoticeTab").text("工作通知");
 		$("#auditNoticeTab").text("审核通知");
 		$("#signNoticeTab").text("签字通知");
 		$("#dispatchNoticeTab").text("施工/监理派遣通知");
 		$("#conTaskNoticeTab").text("施工任务通知");
 		$("#chargeNoticeTab").text("收费通知");
   	 }
	
	
	
</script>

<!-- ================== END PAGE LEVEL JS ================== -->