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
                <li id="scaleTab" class="active"><a href="#projectScale" data-toggle="tab"><i class="fa fa-info-circle"></i> 工程信息</a></li>
				<li id="projectScheduleTab" class=""><a href="#projectSchedule" data-toggle="tab"><i class="fa fa-calendar"></i> 工程进度</a></li>
				<li id="constructionScheduleTab" class=""><a href="#constructionSchedule" data-toggle="tab"><i class="fa fa-tasks"></i> 施工进度</a></li>
				<li id="contractTab" class="duDisValide suDisValide"><a href="#contract" data-toggle="tab"><i class="fa fa-file-o"></i> 工程合同</a></li>
				<li id="changeTab" class=""><a href="#change" data-toggle="tab"><i class="fa fa-file-o"></i> 变更签证</a></li>
				<li id="constructionPlanTab" class=""><a href="#constructionPlan" data-toggle="tab"><i class="fa fa-code-fork"></i> 施工计划</a></li>
				<li id="accessoryListTab" class="duDisValide suDisValide cuDisValide"><a href="#accessoryList" data-toggle="tab"><i class="fa fa-file-archive-o"></i> 相关文档</a></li>
				<li id="materialTab" class=""><a href="#materialList" data-toggle="tab"><i class="fa fa-file-archive-o"></i> 材料清单</a></li>
				<li id="serviceTab" class="hidden"><a href="#serviceInfo" data-toggle="tab"><i class="fa fa-file-archive-o"></i> 接口同步</a></li>
                <li class="next-button"><a href="javascript:;" data-click="next-tab" class="text-inverse"><i class="fa fa-arrow-right"></i></a></li>
            </ul>
        </div>
    </div>
    <input type="hidden" id="hiddenConfig" name="hiddenConfig" value="${hiddenConfig}"/>
    <input type="hidden" id="staffRemoveClass" name="staffRemoveClass" value="${staffRemoveClass}"/>
		
    <div class="tab-content">
		<input type="hidden" id="projId" name="projId" value="${projId}"/>
		<input type="hidden" id="customerServiceCenter" name="customerServiceCenter" value="${customerServiceCenter}"/>
		<input type="hidden" id="marketDevelopmentDepartment" name="marketDevelopmentDepartment" value="${marketDevelopmentDepartment}"/>
		<input type="hidden" id="gasCompany" name="gasCompany" value="${gasCompany}"/>
		<input type="hidden" id="constructionUnit" name="constructionUnit" value="${constructionUnit}"/>
		<input type="hidden" id="supervisionUnit" name="supervisionUnit" value="${supervisionUnit}"/>
		<input type="hidden" id="businessPartnersType" name="businessPartnersType" value="${businessPartnersType}"/>
		<input type="hidden" id="loginDeptDivide" name="loginDeptDivide" value="${loginDeptDivide}"/>
		<!-- 工程明细 start -->
       	<div class="tab-pane fade active in" id="projectScale" >
       	</div>
       	<!-- 工程明细 end -->
       	<!-- 工程进度 start -->
       	<div class="tab-pane fade" id="projectSchedule" >
       		<div class="gantt"></div>
       	</div>
       	<!-- 工程进度 end -->
       	<!-- 施工进度 start -->
       	<div class="tab-pane fade" id="constructionSchedule"></div>
       	<!-- 施工进度 end -->
       	<!-- 合同 start -->
       	<div class="tab-pane fade" id="contract"></div>
       	<!-- 合同 end -->
       	<!-- 变更签证 start -->
       	<div class="tab-pane fade" id="change"></div>
       	<!-- 变更签证 end -->
       	<!-- 计划 start -->
       	<div class="tab-pane fade" id="constructionPlan"></div>
       	<!-- 计划 end -->
       	
       	<!-- 附件清单（相关文档） start -->
       	<div class="tab-pane fade" id="accessoryList" >
       		<table id="accessoryListTable" class="table table-hover table-bordered nowrap" width="100%">
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
		      </table>
       	</div>
       	<!-- 材料清单 -->
        	<div class="tab-pane fade" id="materialList" >
       		<table id="materialListTable" class="table table-hover table-bordered nowrap" width="100%">
				<thead>
		           <tr>
		               <th>材料名称</th>
		               <th>规格型号</th>
		               <th>单位</th>
		               <th>设计总量</th>
		               <th>领用总量</th>
		               <th>使用总量</th>
		               <th>是否由物资购买</th>
		           </tr>
		       	</thead>
		      </table>
       	</div>
       	<!-- 接口同步 start -->
       	<div class="tab-pane fade" id="serviceInfo"></div>
       	<!-- 接口同步 end -->
	</div>
</div>

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
	 $.getScript('projectjs/project/project-global-detail.js?'+Math.random()).done(function () {
	    	projectGlobalDetail.init();
	  });
</script>
<!-- ================== END PAGE LEVEL JS ================== -->