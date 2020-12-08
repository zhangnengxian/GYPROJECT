<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		 <!-- <div class="panel">
		 	<div id="allmap" style="width: 100%;height: 100%;margin:0px;padding:0px"></div> 
		 </div> -->
        <div class="col-md-12 col-sm-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程列表</h4>
                </div>
                <div class="panel-body">
                <input type="hidden" class="corpId" value="${corpId }"/>
                <input type="hidden" class="groupCompany" value="${groupCompany }"/>
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <table id="projectGlobalViewListTable" data-attach-table="all" class="table table-striped table-bordered nowrap " width="100%">
	            		<thead>
	                		<tr>
	                			<th></th>
	                		    <th>工程编号</th>
	                		    <th>工程名称</th>
	                		    <th>工程地点</th>
				                <th>工程规模</th>
				                <th>工程状态</th>
				                 <th>待办人</th>
				                <th>工程类型</th>
				                <th>出资方式</th>
				                <th>业务部门</th>
				               
				                <th>客户名称</th>
				                <th>客户联系人</th>
				                <th>电话</th>
				                <th>受理日期</th>
				                <th>勘察人</th>
				                <th>勘察日期</th>
	                		    <th>设计单位</th>
				                <th>设计人</th>
				                <th>设计完成日期</th>
				                <!-- <th>预算总造价</th> -->
				                <th>预算时间</th>
				                <th>预算员</th>
				              <!--   <th>确定造价</th> -->
				                <th>造价员</th>
				                <!-- <th>合同金额</th> -->
				                <th>合同签订时间</th>
				                <th>验收日期</th>
				                <th>验收结果</th>
				                <th>验收负责人</th>
				                <th>结算日期</th>
				                <th>结算人</th>
	                		</tr>
		               	</thead>
	    	  	  	</table>
                </div>
             </div>
        </div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程一览 - 工程项目管理系统 ');
   
    $.getScript('projectjs/project/project-global-view.js?v='+Math.random()).done(function () {
        projectGlobalView.init();
	});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->