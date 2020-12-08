<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->



<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
<table id="materialListTablePop" class="table table-striped table-bordered nowrap " width="100%">
	<thead>
		<tr>
			<th></th>
			<th>设备材料汇总表</th>
          	<th>材料</th>
          	<th>单位</th>
          	<th>数量</th>
		</tr>
	<thead>
</table>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('材料清单 - 工程施工管理系统 ');
    handleMaterialList();
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->