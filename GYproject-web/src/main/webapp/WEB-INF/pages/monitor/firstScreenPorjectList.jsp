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
<link href="/assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->

<link href="/css/ecloud.css" rel="stylesheet" type="text/css"/>

<!-- ================== BEGIN BASE JS ================== -->
<script src="/assets/plugins/pace/pace.min.js"></script>
        <!-- ================== END BASE JS ================== -->
<style type="text/css">
	body{
		background-color: #012841 !important;
	}
	.content{
		/* border:1px solid red; */
		height:100%;
		margin-left:0px;
	}
	.pagination>li>a{
        background-color:#fff !important;
	}
	.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span,
	.pagination>.active>span:focus,
	.pagination>.active>span:hover{
		background:#4c6f86 !important;
	}
	.dataTables_wrapper table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before, .dataTables_wrapper table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before{
    	background:#fff !important;
	}
	table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before, table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before{
      	color:#012841 !important;
	}
	.content{
      	position:relative;
      	color: #f2f2f2;
	}
	.content table.dataTable,
	.content table.dataTable tbody tr,
	.content table.dataTable tbody td{
		background: transparent !important;
		color: #b2b2b2;
      	border-color: #999;
	}
	.content table.dataTable tbody tr.selected,
	.content table.dataTable tbody tr.selected td{
		background:#4c6f86 !important;
      	color: #a9a9a9;
	}
	.btn{
		display: inline-block;
        line-height: 20px;
        height: 33px;
        padding: 1px 15px;
        color: #f2f2f2;
        font-size: 18px;
        text-decoration: none;
        outline: none;
	}
    .item-bg-blue {
		position:absolute;
        border-radius: 5px;
        background-color: #3ba8dc;
        line-height: 22px !important;
        bottom:10px;
        box-shadow: rgba(0,0,0,.5) 0 2px 8px;
	}
	.projTitle{
    	margin-left: 630px;
        margin-top:  40px;
      	color:#fff;
      	font-size:20px;
    }
    .btn svg{
        width: 30px;
    	height: 30px;
    	fill: #fff;
	}
    .backBtn{
		height:10px;
    }
    
    .btn.btn-query{
   		/* border-radius:5px; */
    	background:#3ba8dc !important;
    	width:60px;
    	height:33px;
    	margin-right:5px;
    	font-weight:300;
    	border-color:#3ba8dc !important;
    	box-shadow:rgba(0,0,0,.5) 0 2px 8px;
    }
    .btn.btn-query:hover{
    	background:#4c6f86 !important;
    	border-color:#4c6f86 !important;
    }
    .btn{
    	line-height:25px;
    }
    
    /* .modal-body{
    	background:#012841   !important;
    } */
    /* .control-label,.form-control{
		color:#fff !important;
	} */
	/* .modal-footer,.modal-header{
		background:#012841   !important;
	}
	.modal-title,ion-information-circled{
		color:#fff !important;
	} */
	/* .form-control{
		border:#093754 1px dotted !important;
	} */
	/* select.form-control{
	background:#012841;
	} */
	.table thead{
		border-color:#3ba8dc !important;
	}
</style>

<div id="content" class="content">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<!-- <div class="backBtn">
			<a href="javascript:;" class="btn item-bg-blue" id="firstScreenBack">
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
			</div> -->
			<div>
			<input type="hidden" id="path">
			<table id="projectGlobalViewListTable"  class="table table-striped table-bordered nowrap " width="100%">
           		<thead>
               		<tr>
               			<th></th>
               		    <th>工程编号</th>
               		    <th>工程名称</th>
               		    <th>工程地点</th>
		                <th>工程规模</th>
		                <th>工程状态</th>
		                <th>区域</th>
		                <th>客户名称</th>
		                <th>客户联系人</th>
		                <th>电话</th>
		                <th>受理日期</th>
		                <th>勘察人</th>
		                <th>勘察日期</th>
               		    <th>设计单位</th>
		                <th>设计人</th>
		                <th>设计完成日期</th>
		                <th>预算总造价</th>
		                <th>预算时间</th>
		                <th>预算员</th>
		                <th>确定造价</th>
		                <th>造价员</th>
		                <th>合同金额</th>
		                <th>签订时间</th>
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

<!-- begin #commonModal 模态框 -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" id="modal-content">
        
    </div>
</div>
<!-- end #commonModal 模态框 -->
<!-- ================== 开始 云服务框架更新样式 ================== -->
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
<script src="/assets/plugins/echarts/echarts.min.js"></script>
<script src="/assets/plugins/echarts/shine.js"></script>
<script src="/assets/plugins/echarts/infographic.js"></script>
<script src="/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<!-- ================== END BASE JS ================== -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
<script type="text/javascript" src="https://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script src="/assets/js/apps.js"></script>
<script src="/js/ecloud.js"></script>
<script>
var path='<%=basePath%>';
$("#path").val(path);
$.getScript('/projectjs/monitor/first-screen-project.js').done(function () {
    projectGlobalView.init();
});
</script>