<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="/assets/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
        <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
        <link href="/assets/css/animate.min.css" rel="stylesheet" />
        <link href="/assets/css/style.min.css" rel="stylesheet" />
        <link href="/assets/css/style-responsive.min.css" rel="stylesheet" />
        <link href="/assets/plugins/ionicons/css/ionicons.min.css" rel="stylesheet" />

        <link href="/assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
		<link href="/assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
		<link href="/assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
		<link href="/assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
		<link href="/assets/plugins/jQuery-Gantt/index_files/css/style.css" rel="stylesheet" />
		<link href="/assets/plugins/jQuery-Gantt/index_files/css/prettify.css" rel="stylesheet" />
        <!-- ================== END BASE CSS STYLE ================== -->
        <!-- ================== BEGIN BASE JS ================== -->
        <script src="/assets/plugins/pace/pace.min.js"></script>
        <!-- ================== END BASE JS ================== -->
        <!-- ================== 开始 云服务框架更新样式 ================== -->
        <link href="/css/ecloud.css" rel="stylesheet" type="text/css"/>
        
<style type="text/css">
	body{
		background-color: #012841 !important;
	}
	.content{
		//border:1px solid red;
		height:100%;
		margin-left:0px;
	}
	.nav.nav-tabs>li>a{
		padding:6px 40px;
	}
	.tab-content{
		/* padding:0px; */
	}
	.projDetailTabs{
		margin:-5px;
	}
	.tab-content{
		background-color: #012841 !important;
		padding:0px;
		margin-bottom:0px;
	}
	.control-label,.form-control{
		color:#fff !important;
	}
	h3{
		color:#fff !important;
	}
	.nav a{
		background-color: #3ba8dc !important;
	}
	.tab-overflow{
		background-color: #012841 !important;
	}
	.projDetailTabs .nav-tabs li.active a{
		background:#4c6f86 !important;
	}
	.nav-tabs>li>a{
		margin-right:5px;
	}
	a svg{
        width: 30px;
    	height: 30px;
    	fill: #fff;
	}
	[readonly], [disabled]{
		border:#093754 1px dotted !important;
	}
	.projDetailTabs .tab-pane h3{
		border-bottom:#165379 1px solid;
	}
	.tab-overflow .nav.nav-tabs{
		background:transparent !important;
	}
	.nav.nav-tabs>li>a{
		color:#fff;
	}
	.backHome{
		padding:5px 8px;
		background-color: #3ba8dc;
		margin-right:62px;
	}
	.item-bg-blue {
         border-radius: 5px;
         background-color: #3ba8dc;
         line-height: 22px !important;
         right: 16px;
         top:13px;
         box-shadow: rgba(0,0,0,.5) 0 2px 8px;
     }
     .btn{
     	padding:5px 8px;
     }
     .rightPanel{
     	background-color:#fff;
     }
     /* .top-tool .btn {
         display: inline-block;
         line-height: 20px;
         height: 33px;
         color: #f2f2f2;
         font-size: 18px;
         text-decoration: none;
         outline: none;
	} */ */
</style>
<div class="panel panel-default panel-with-tabs projDetailTabs" data-sortable-id="ui-unlimited-tabs-2">
    <div class="panel-heading p-0">
        <!-- begin nav-tabs -->
        <div class="tab-overflow overflow-right">
			<!-- <a title="关闭详述" href="javascript:;" class="btn btn-lg pop-close text-white"><i class="fa ion-close-round"></i></a> -->
            <ul class="nav nav-tabs">
            	<!-- <li>
            	<a href="javascript:;">
            		<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
					 viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">
			<path d="M-49.1,402.5h-75.7c-2.8,0-5-2.2-5-5c0-2.8,2.2-5,5-5h75.7c2.8,0,5,2.2,5,5C-44.1,400.3-46.3,402.5-49.1,402.5L-49.1,402.5z
				 M-49.1,362.5h-75.7c-2.8,0-5-2.2-5-5c0-2.8,2.2-5,5-5h75.7c2.8,0,5,2.2,5,5C-44.1,360.3-46.3,362.5-49.1,362.5L-49.1,362.5z
				 M-154,447.5c-5.5,0-10-4.5-10-10c0-5.5,4.5-10,10-10c5.5,0,10,4.5,10,10C-144,443-148.5,447.5-154,447.5L-154,447.5z M-154,407.5
				c-5.5,0-10-4.5-10-10c0-5.5,4.5-10,10-10c5.5,0,10,4.5,10,10C-144,403-148.5,407.5-154,407.5L-154,407.5z M-154,367.5
				c-5.5,0-10-4.5-10-10c0-5.5,4.5-10,10-10c5.5,0,10,4.5,10,10C-144,363-148.5,367.5-154,367.5L-154,367.5z M-124.7,432.5h75.7
				c2.8,0,5,2.2,5,5s-2.2,5-5,5h-75.7c-2.8,0-5-2.2-5-5S-127.5,432.5-124.7,432.5L-124.7,432.5z"/>
			</svg>
            	</a>
            	</li> -->
                <li class="prev-button"><a href="javascript:;" data-click="prev-tab" class="text-inverse"><i class="fa fa-arrow-left"></i></a></li>
                <li id="scaleTab" class="active"><a href="#projectScale"  data-toggle="tab"><i class="fa fa-info-circle"></i> 工程信息</a></li>
				<li id="projectScheduleTab" class=""><a  href="#projectSchedule" data-toggle="tab"><i class="fa fa-calendar"></i> 工程进度</a></li>
				<li id="constructionScheduleTab" class=""><a href="#constructionSchedule"  data-toggle="tab"><i class="fa fa-tasks"></i> 施工进度</a></li>
				<li id="contractTab" class=""><a href="#contract"  data-toggle="tab"><i class="fa fa-file-o"></i> 工程合同</a></li>
				<li id="constructionPlanTab" class=""><a href="#constructionPlan" data-toggle="tab"><i class="fa fa-code-fork"></i> 施工计划</a></li>
				<li id="accessoryListTab" class=""><a href="#accessoryList" data-toggle="tab"><i class="fa fa-file-archive-o"></i> 相关文档</a></li>
                <li class="next-button"><a href="javascript:;" data-click="next-tab" class="text-inverse"><i class="fa fa-arrow-right"></i></a></li>
            </ul>
        </div>
        <div class="pull-right">
        	<a href="javascript:;" class="btn item-bg-blue backHome">返回</a>
        	<a href="javascript:;" class="btn item-bg-blue backList">主页</a>
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
       		<div class="gantt"></div>
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
       	<!-- 附件清单（相关文档） end -->
	</div>
</div>
<script src="/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="/assets/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/plugins/jquery-hashchange/jquery.hashchange.min.js"></script>
<script src="/assets/plugins/slimscroll/jquery.slimscroll.min.js" async></script>
<script src="/assets/plugins/jquery-cookie/jquery.cookie.js"></script>
<script src="/assets/plugins/DataTables/media/js/jquery.dataTables.min.js"></script>
<script src="/assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="/assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
<script src="/assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js"></script>
<script src="/assets/plugins/DataTables/extensions/Buttons/js/buttons.html5.min.js"></script>
<script src="/assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="/assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>
<script src="/assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js"></script>
<script src="/assets/plugins/jQuery-Gantt/index_files/js/jquery.fn.gantt.js"></script>
<script src="/assets/js/apps.js"></script>
<script src="/js/ecloud.js"></script>

<script>
	 $.getScript('/projectjs/monitor/project-detail.js').done(function () {
	    	projectGlobalDetail.init();
	 });
	 $(function(){
		 //工程进度
		 $("#projectScheduleTab").off().on("click",function(){
			 var projId = $("#projId").val();
			 console.info('1..'+projId);
			$("#projectSchedule").cgetContent("/projTS/projectDetailSchedulePage",{"projId":projId});
		 })
		 // 施工进度 
		 $("#constructionScheduleTab").off().on("click",function(){
		 	var projId = $("#projId").val();
		 	$("#constructionSchedule").cgetContent("/projTS/projectDetailconsSchedule",{"projId":projId});
		 });
		 //合同
		  $("#contractTab").off().on("click",function(){
			  $("#contract").cgetContent("/projTS/projectDetailContractPage");
		 });
		 //计划
		  $("#constructionPlanTab").off().on("click",function(){
			  $("#constructionPlan").cgetContent("/projTS/projectDetailPlanPage");
		 });
		 //文档
		  $("#accessoryListTab").off().on("click",function(){
			  var accessorySearchData={};
			  var projId = $("#projId").val();
			  accessorySearchData.projId = projId;
			  if($.fn.DataTable.isDataTable('#accessoryListTable')){
				  //初始化过
			      $("#accessoryListTable").cgetData(false);//列表重新加载
			  }else{
				  accessoryTableInit();
			  }
		 });
		 
		 //回到列表
		 $('.backHome').off().on("click",function(){
			 var path='<%=basePath%>';
			 window.location.href=path+"projTS/viewPorjectList";
			 //$("body").cgetContent("/projTS/viewPorjectList");
		 })
		 //回主程序
		 $('.backList').off().on("click",function(){
			 var path = '<%=basePath%>';
			 //window.location.href="http://localhost/projTS/viewPorjectList";
			 window.location.href=path+"projInfo.jsp"
		 })
	 })
</script>

