<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->



<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
<table id="materialListTablePop" class="table table-striped table-bordered nowrap " width="100%">
	<thead>
		<tr>
			<th></th>
			<th>材料名称</th>
          	<th>规格型号</th>
            <th>单位</th>
            <th>设计总量</th>
            <th>领用总量</th>
            <th>报验总量</th>
            <th>使用总量</th>
            <th>计划领用总量</th>
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