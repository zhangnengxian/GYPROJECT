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
                    <h4 class="panel-title">选择项目 - 项目列表</h4>
                </div>
                <div class="panel-body">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <table id="projectInspectListTable" class="table table-striped table-bordered nowrap " width="100%">
	            		<thead>
	                		<tr>
	                			<th></th>
	                		    <th>工程编号</th>
	                		    <th>工程名称</th>
	                		    <th>工程地点</th>
				                <th>工程规模</th>
				                <th>工程状态</th>
				                <th>现场代表</th>
				                <th>项目经理</th>
				                <th>施工员</th>
				                <th>安全员</th>
				                <th>材料员</th>
				                <th>资料员</th>
				                <th>班组长</th>
				                <th>焊工</th>
				                <th>质检员</th>
				                <th>总监</th>
				                <th>现场监理</th>
				                <th></th>
				               <!--  <th width="30">选择</th> -->
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
   
    $.getScript('projectjs/project/project-inspect-list.js?v='+Math.random()).done(function () {
        projectInspectList.init();
	});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->