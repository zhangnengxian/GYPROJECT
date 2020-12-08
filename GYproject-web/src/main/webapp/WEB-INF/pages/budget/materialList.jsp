<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

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
                    <h4 class="panel-title">材料清单</h4>
                </div>
                <div class="panel-body">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <form action="" id="materialListForm">
				        <table id="materialListTable" class="table table-striped table-bordered nowrap " width="100%">
		            		<thead>
		                		<tr>
			                		<th></th>
		                		    <th>序号</th>
			                		<th>设备材料汇总表</th>
					                <th>材料</th>
					                <th  width="120">单位</th>
					                <th  width="120">数量</th>
					                <th>备注</th>					               					    
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
    App.setPageTitle('材料清单 - 工程施工管理系统 ');
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->