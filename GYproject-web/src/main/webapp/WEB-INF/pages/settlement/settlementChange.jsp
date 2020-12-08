<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="panel-body p-l-0 p-r-0 p-t-0">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#default-tab-1" id="budgetAdjustTab"  data-toggle="tab">预算调整</a></li>
		<li class=""><a href="#default-tab-2" id="suppContTab"   data-toggle="tab">补充协议</a></li>     
		<li class=""><a href="#default-tab-3" id="materialChangeTab"   data-toggle="tab">材料变更</a></li>           
	</ul>
	<input id='projId' type='hidden' name='projId'>
   	<div class="tab-content">
   		<div class="tab-pane fade in btn-top active" id="default-tab-1">
           	<div id="budgetAdjustbox">                   
				<table id="budgetAdjustTable" class="table table-striped table-bordered nowrap " width="100%">
			 		<thead>
			     		<tr>
			     			<th>工程ID</th>
				      		<th>总造价</th>			                		 
				      		<th>民用土建</th>
				      		<th>民用庭院</th>
				      		<th>民用户内</th>
				      		<th>仪表</th>
				      		<th>工艺</th>
				      		<th>土建</th>
				      		<th>监理费</th>
					        <th>监检费</th>
					        <th>储备金</th>
					        <th>其他金额</th>
			     		</tr>
			     	</thead>
				</table>
			</div>
		</div>
        <div class="tab-pane fade in btn-top" id="default-tab-2">
        	<div id="suppContbox">
				<table id="suppContTable" class="table table-striped table-bordered nowrap " width="100%">
			 		<thead>
			     		<tr>
				      		<th>工程ID</th>			                		 
				      		<th>合同编号</th>
					        <th>签订日期</th>
					        <th>调整事项</th>
					        <th>协议价款</th>
					        <th>经办人</th>
			     		</tr>
			     	</thead>
				 </table>
			</div>
		</div>
        <div class="tab-pane fade in btn-top" id="default-tab-3">
	        <div id="materialChangebox">
				<table id="materialListTable" class="table table-striped table-bordered nowrap " width="100%">
			 		<thead>
			     		<tr>
				      		<th>工程Id</th>			                		 
				      		<th>材料名称</th>
					        <th>材料型号</th>
					        <th>单位</th>
					        <th width="120">调整量</th>
			     		</tr>
			     	</thead>
				 </table>
			</div>
		</div>
	</div>
</div>
<script>
    App.restartGlobalFunction();
    App.setPageTitle('变更记录 - 工程管理系统');
    
    $.getScript('projectjs/settlement/settlement-change.js').done(function () {
    	changeRecord.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->