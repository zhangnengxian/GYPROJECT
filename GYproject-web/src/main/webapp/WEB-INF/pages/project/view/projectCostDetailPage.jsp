<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div class="panel panel-default panel-with-tabs projDetailTabs" data-sortable-id="ui-unlimited-tabs-2">
    <div class="panel-heading p-0">
        <!-- begin nav-tabs -->
        <div class="tab-overflow overflow-right">
			<a title="关闭详述" href="javascript:;" class="btn btn-lg pop-close text-white"><i class="fa ion-close-round"></i></a>
            <ul class="nav nav-tabs">
                <li class="prev-button"><a href="javascript:;" data-click="prev-tab" class="text-inverse"><i class="fa fa-arrow-left"></i></a></li>
                <li id="scaleTab" class="active"><a href="#projectScale" data-toggle="tab"><i class="fa fa-info-circle"></i> 工程概算</a></li>
				<li id="projectScheduleTab" class=""><a href="#projectSchedule" data-toggle="tab"><i class="fa fa-calendar"></i> 工程预算</a></li>
				<li id="constructionScheduleTab" class=""><a href="#constructionSchedule" data-toggle="tab"><i class="fa fa-tasks"></i> 合同价</a></li>
				<li id="contractTab" class=""><a href="#contract" data-toggle="tab"><i class="fa fa-file-o"></i> 变更签证</a></li>
				<li id="constructionPlanTab" class=""><a href="#constructionPlan" data-toggle="tab"><i class="fa fa-code-fork"></i> 工程结算</a></li>
				<li id="accessoryListTab" class=""><a href="#accessoryList" data-toggle="tab"><i class="fa fa-file-archive-o"></i> 工程决算</a></li>
                <li class="next-button"><a href="javascript:;" data-click="next-tab" class="text-inverse"><i class="fa fa-arrow-right"></i></a></li>
            </ul>
        </div>
    </div>
    <div class="tab-content">
		<input type="hidden" id="projId" name="projId" value="${projId}"/>
		<!-- 工程明细 start -->
       	<div class="tab-pane fade active in" id="projectScale" >
       	</div>
       	<!-- 工程明细 end -->
       	<!-- 工程进度 start -->
       	<div class="tab-pane fade" id="projectSchedule" >
       		<table id="accessoryListTable" class="table table-hover table-bordered nowrap" width="100%">
				<thead>
		           <tr>
		               <th>序号</th>
		               <th>费用名称</th>
		               <th>取费说明</th>
		               <th>费率</th>
		               <th>金额</th>
		           </tr>
		       	</thead>
		      </table>
       	</div>
       	<!-- 工程进度 end -->
       	<!-- 施工进度 start -->
       	<div class="tab-pane fade" id="constructionSchedule"></div>
       	<!-- 施工进度 end -->
       	<!-- 合同 start -->
       	<div class="tab-pane fade" id="contract"></div>
       	<!-- 合同 end -->
       	<!-- 计划 start -->
       	<div class="tab-pane fade" id="constructionPlan"></div>
       	<!-- 计划 end -->
       	
       	<!-- 附件清单（相关文档） start -->
       	<div class="tab-pane fade" id="accessoryList" >
       		<!-- <table id="accessoryListTable" class="table table-hover table-bordered nowrap" width="100%">
				<thead>
		           <tr>
		               <th>工程阶段</th>
		               <th>资料名称</th>
		               <th>文档类型</th>
		               <th>签收日期</th>
		               <th>签收人</th>
		               <th>链接</th>
		           </tr>
		       	</thead>
		      </table> -->
       	</div>
       	<!-- 附件清单（相关文档） end -->
	</div>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	 $.getScript('projectjs/project/project-cost-detail.js').done(function () {
	    	projectGlobalDetail.init();
	  });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->