<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html class="no-js" lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
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
		<link href="/assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
		<link href="/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" />
		<link href="/assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet"/>
        <!-- ================== END BASE CSS STYLE ================== -->

        <!-- ================== BEGIN BASE JS ================== -->
        <script src="/assets/plugins/pace/pace.min.js"></script>
        <!-- ================== END BASE JS ================== -->
        <!-- ================== 开始 云服务框架更新样式 ================== -->
        <link href="/css/ecloud.css" rel="stylesheet" type="text/css"/>
        <!-- ================== 结束 云服务框架更新样式 ================== -->
    <style type="text/css">
        body {
            /*  background-color: #012841; */ 
            color: #f2f2f2;
    		background: url('../images/background.png') no-repeat center top #050e42;
    		background-size: cover; 
        }

        .flower-grid {
            position: absolute;
            left: 80px;
            right: 120px;
            top: 120px;
            bottom: 70px;
            box-sizing: border-box;
            border: #306282 1px solid;
           /*  background: #012841; */
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            box-shadow: rgba(0, 0, 0, .35) 0 7px 25px;
        }

        .flower-grid .flower-nav {
            position: absolute;
            width: 225px;
            height: 225px;
            top: -80px;
            right: -80px;
        }

        .flower-grid .top-tool {
            position: absolute;
            top: -50px;
            height: 50px;
            width: 100%;
            line-height: 50px;
            padding-right: 180px;
            box-sizing: border-box;
        }

        .top-tool .btn {
            display: inline-block;
            line-height: 20px;
            height: 33px;
            padding: 1px 15px;
            color: #f2f2f2;
            font-size: 18px;
            text-decoration: none;
            outline: none;
        }
        
        .top-tool .btn svg{
        	width: 30px;
		    height: 30px;
		    fill: #fff;
		}

        .flower-grid .content-box {
            width: 100%;
            height: 100%;
            padding: 30px;
            box-sizing: border-box;
        }

        .flower-nav > div {
            position: absolute;
            width: 110px;
            height: 110px;
            border: #fff 5px solid;
            box-sizing: border-box;
            box-shadow: rgba(0,0,0,.5) 0 3px 18px;
            overflow: hidden;
        }

        .flower-nav > div a {
            display: block;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, .2);
        }

        .flower-nav > div a:hover,
        .flower-nav > div a.active {
            background-color: rgba(255, 255, 255, 0);
        }

        .flower-nav .flower-left-top {
            left: 0;
            top: 0;
            background-color: #f29301;
            border-bottom-left-radius: 30px;
            border-top-right-radius: 30px;
        }

        .flower-nav .flower-left-bottom {
            left: 0;
            bottom: 0;
            background-color: #3ba8dc;
            border-top-left-radius: 30px;
            border-bottom-right-radius: 30px;
        }

        .flower-nav .flower-right-top {
            right: 0;
            top: 0;
            background-color: #169f95;
            border-top-left-radius: 30px;
            border-bottom-right-radius: 30px;
        }

        .flower-nav .flower-right-bottom {
            right: 0;
            bottom: 0;
            background-color: #ea0f6f;
            border-bottom-left-radius: 30px;
            border-top-right-radius: 30px;
        }

        .flower-nav .flower-heart {
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            border: none;
            border-radius: 50%;
            background: url("../images/monitor/heart-bg.png") center 6px no-repeat;
            -webkit-background-size: 104px;
            background-size: 104px;
            box-shadow: none;
        }

        .pull-left {
            float: left;
        }

        .pull-right {
            float: right;
        }

        .p-l-0 {
            padding-left: 0 !important;
        }

        .item-bg-blue {
            border-radius: 5px;
            background-color: #3ba8dc;
            line-height: 22px !important;
            margin-left: 10px;
            box-shadow: rgba(0,0,0,.5) 0 2px 8px;
        }

        .left-top-btn,
        .left-bottom-btn,
        .right-top-btn,
        .right-bottom-btn {
            background-position: center center;
            -webkit-background-size: 100%;
            background-size: 100%;
        }

        .left-top-btn {
            background-image: url("../images/monitor/left-top-btn.png");
        }

        .left-bottom-btn {
            background-image: url("../images/monitor/left-bottom-btn.png");
        }

        .right-top-btn {
            background-image: url("../images/monitor/right-top-btn.png");
        }

        .right-bottom-btn {
            background-image: url("../images/monitor/right-bottom-btn.png");
        }

        .content-box{
        	color: #f2f2f2;
        }
        .content-box table.dataTable,
        .content-box table.dataTable tbody tr,
        .content-box table.dataTable tbody td{
        	background: transparent !important;
        	color: #b2b2b2;
        	border-color: #999;
        }
        .content-box table.dataTable tbody tr.selected,
        .content-box table.dataTable tbody tr.selected td{
        	background:#4c6f86 !important;
        	color: #a9a9a9;
        }
        
        
        .flower-left-bottom{
        	position:absolute;
        	z-index:999;
        }
        .flower-heart{
        	position:absolute;
        	z-index:10000;
        }
        .flower-right-bottom{
        	position:absolute;
        	z-index:10000;
        }
        .chooseRadio{
        	position: absolute;
            top: 11px;
            left: 30px;
            color:#fff;
        }
        /* .chooseRadio >input{
        	/* margin-left:8px;
        }*/
        .chooseRadio >input{
        	margin-right:14px; 
        }
        .chooseRadio >span{
        	margin-right:10px; 
        }
        
        .echartsBox{
        	position: relative;
        }
        .echartSmallLeftBox{
        	position: absolute;
        	left:20px;
        	width:560px;
        	height:400px;
        	/* border:1px solid red; */
        }
		.changeBox{
			width:1100px;
			height:400px;
			/*top:20px;*/
			/*padding: 30px;*/
			position:absolute;
		}
        .echartSmallRightBox{
        	position: absolute;
        	left:560px;
        	width:560px;
        	height:400px;
        	/* border:1px solid green; */
        }
        select.form-control {
        	border:1px solid #325879;
        	color:#fff;
        }
        option {
        	background-color:#012841;
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
        .echartsBoxTopLeft{
        	position: absolute;
        	left:20px;
        	top:20px;
        	width:560px;
        	height:400px;
        	/* border:1px solid green; */
        }
        .echartsBoxTopRight{
        	position: absolute;
        	left:580px;
        	top:20px;
        	width:560px;
        	height:400px;
        	/* border:1px solid red; */
        }
        .clickEffect{
        	background-color:#4c6f86;
        }
        .fourLeafClcik{
        	background-color:rgba(33, 29, 29, 0.2) !important;
        }
        .totalName{
        	position: absolute;
        	top:34px;
        	left:38px;
        	color:#6e6262;
        }
        .mapBox{
        	position: absolute;
        	top:20px;
        	width:1100px;
        	height:420px;
        	/* border:solid 1px red; */
        }
        .pace-progress, .pace:before {
		     top: 0;
	     }
    </style>
</head>
<body>
<div class="flower-grid">
    <div class="top-tool">
        <div class="pull-left">
            <a href="javascript:;" class="btn tool-title p-l-0"><i class="fa fa-folder-o"></i> 工程施工</a>
        </div>
        <div class="pull-right">
        	<!-- 返回首页 -->
        	<a href="javascript:;" class="btn item-bg-blue items-list-view  " id="backHome">
        		<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
					 viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">
				<path d="M-48.1,400.7l-53.8-50.4l-0.2-0.2l-54,50.4c-0.6,0.5-0.9,1.3-0.9,2c0,0.8,0.3,1.5,0.8,2.1c1.1,1.1,3,1.2,4.1,0.1l50.1-46.7
					L-52,405c0.5,0.5,1.3,0.8,2,0.8c0.8,0,1.6-0.3,2.1-0.9C-46.8,403.6-46.9,401.8-48.1,400.7z M-64.7,401.6c-1.6,0-2.9,1.3-2.9,2.9
					v39.7h-20.3v-27.8h-28.5v27.8h-20.3v-39.7c0-1.6-1.3-2.9-2.9-2.9s-2.9,1.3-2.9,2.9v45.5h31.9v-27.8h16.8v27.8h31.9l0-45.5
					C-61.8,403-63.1,401.6-64.7,401.6z M-83,364.9h15.4v13.6c0,1.6,1.3,2.9,2.9,2.9c1.6,0,2.9-1.3,2.9-2.9l0-19.4H-83
					c-1.6,0-2.9,1.3-2.9,2.9C-85.9,363.5-84.6,364.9-83,364.9z"/>
				</svg>
        	</a>
        	<!-- 部门列表 -->
            <a href="javascript:;" class="btn item-bg-blue items-list-view unit clickEffect" id="unitList">
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
            <!-- 部门地图 -->
            <%--<a href="javascript:;" class="btn item-bg-blue items-grid-view unit" id="unitMap">--%>
            <%--<!-- <i class="fa fa-th-large"></i> -->--%>
            	<%--<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"--%>
					 <%--viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">--%>
				<%--<path d="M-104.2,428.1C-104.2,428.1-104.3,428.1-104.2,428.1c-1.1,0-2.1-0.5-2.8-1.3c-1.1-1.2-2.2-2.5-3.4-3.9--%>
					<%--c-10.8-12.3-25.4-28.4-25.4-43.5c0-17.6,14.3-31.8,31.8-31.8s31.8,14.3,31.8,31.8c0,15.4-15.4,31.7-26.4,44.2c-1,1.1-2,2.2-2.9,3.2--%>
					<%--C-102.2,427.6-103.2,428.1-104.2,428.1z M-104,354.8c-13.5,0-24.5,11-24.5,24.5c0,16.7,17.4,31,24.5,39.9--%>
					<%--c7.1-9.3,24.5-23.1,24.5-39.9C-79.5,365.8-90.5,354.8-104,354.8z"/>--%>
				<%--<path d="M-104,391.4c-7.7,0-13.9-6.2-13.9-13.9s6.2-13.9,13.9-13.9c7.7,0,13.9,6.2,13.9,13.9S-96.4,391.4-104,391.4z M-104,370.9--%>
					<%--c-3.6,0-6.6,3-6.6,6.6s3,6.6,6.6,6.6c3.6,0,6.6-2.9,6.6-6.6S-100.4,370.9-104,370.9z"/>--%>
				<%--<path d="M-56.4,447.5h-95.7c-1.3,0-2.4-0.7-3.1-1.7c-0.7-1.1-0.7-2.4-0.2-3.5l14.1-29.2c0.6-1.3,1.9-2.1,3.3-2.1h8.2--%>
					<%--c2,0,3.7,1.6,3.7,3.7c0,2-1.6,3.7-3.7,3.7h-5.9l-10.6,21.9H-62l-9.6-21.9h-7.1c-2,0-3.7-1.6-3.7-3.7c0-2,1.6-3.7,3.7-3.7h9.5--%>
					<%--c1.5,0,2.8,0.9,3.3,2.2l12.8,29.2c0.5,1.1,0.4,2.4-0.3,3.5C-54,446.9-55.2,447.5-56.4,447.5z"/>--%>
				<%--</svg>--%>
            <%--</a>--%>
            <%--<!-- 部门统计 -->--%>
        	<%--<a href="jabascript:;" class="btn item-bg-blue items-grid-view unit" id="unitChart">--%>
        	<%--<!-- <i class="fa fa-th-list"></i> -->--%>
	        	<%--<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"--%>
					 <%--viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">--%>
				<%--<path d="M-152.4,408.4h10v39.1h-10C-152.4,447.5-152.4,408.4-152.4,408.4z"/>--%>
				<%--<path d="M-125.5,389.1h10v58.4h-10V389.1z"/>--%>
				<%--<path d="M-96.2,402.2h10v45.3h-10V402.2z"/>--%>
				<%--<path d="M-67,382.1h10v65.4h-10C-67,447.5-67,382.1-67,382.1z"/>--%>
				<%--<path d="M-83.8,349.5l11,10.6l-19.7,22.2l-21.4-16.9c-1.4-1.1-3.1-1.7-4.9-1.7c-2.1,0-4.1,0.8-5.6,2.3l-28.2,28l0,0--%>
					<%--c-0.9,0.9-1.4,2.1-1.4,3.4c0,2.7,2.2,4.8,4.8,4.8c1.3,0,2.5-0.5,3.4-1.4l0,0l26.7-27.2l21.5,17.6c1.4,1.1,3.1,1.7,4.9,1.7--%>
					<%--c2.2,0,4.2-0.9,5.7-2.5l21.3-23.6l9.6,9.6l0-29L-83.8,349.5z"/>--%>
				<%--</svg>--%>
        	<%--</a>--%>
        	<!-- 承包方列表 -->
        	<a href="javascript:;" class="btn item-bg-blue items-list-view hidden cuName" id="cuNameList">
        	<!-- <i class="fa fa-th-list"></i> -->
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
        	<!-- 承包方统计 -->
        	<%--<a href="jabascript:;" class="btn item-bg-blue items-grid-view hidden cuName" id="cuNameChart">--%>
        		<%--<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"--%>
					 <%--viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">--%>
				<%--<path d="M-152.4,408.4h10v39.1h-10C-152.4,447.5-152.4,408.4-152.4,408.4z"/>--%>
				<%--<path d="M-125.5,389.1h10v58.4h-10V389.1z"/>--%>
				<%--<path d="M-96.2,402.2h10v45.3h-10V402.2z"/>--%>
				<%--<path d="M-67,382.1h10v65.4h-10C-67,447.5-67,382.1-67,382.1z"/>--%>
				<%--<path d="M-83.8,349.5l11,10.6l-19.7,22.2l-21.4-16.9c-1.4-1.1-3.1-1.7-4.9-1.7c-2.1,0-4.1,0.8-5.6,2.3l-28.2,28l0,0--%>
					<%--c-0.9,0.9-1.4,2.1-1.4,3.4c0,2.7,2.2,4.8,4.8,4.8c1.3,0,2.5-0.5,3.4-1.4l0,0l26.7-27.2l21.5,17.6c1.4,1.1,3.1,1.7,4.9,1.7--%>
					<%--c2.2,0,4.2-0.9,5.7-2.5l21.3-23.6l9.6,9.6l0-29L-83.8,349.5z"/>--%>
				<%--</svg>--%>
        	<%--</a>--%>
        	<!-- 施工进度柱状图 -->
        	<a href="javascript:;" class="btn item-bg-blue items-list-view hidden projectSchedule" id="projectSchedule">
        		<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
					 viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">
				<path d="M-152.4,347.4h8v100h-8C-152.4,447.4-152.4,347.4-152.4,347.4z"/>
				<path d="M-132.6,358.2v16.6h75.8v-16.6L-132.6,358.2z M-61.2,363.5v6h-66.9v-6H-61.2z"/>
				<path d="M-132.6,388.9v16.6h62.5v-16.6L-132.6,388.9z M-74.5,394.2v6h-53.7v-6H-74.5z"/>
				<path d="M-132.6,419.6v16.6H-82v-16.6H-132.6z M-86.7,424.9v6h-41.5v-6H-86.7z"/>
				</svg>
        	</a>
        	<!-- 安全质量柱状图 -->
        	<a href="javascript:;" class="btn item-bg-blue items-list-view hidden safeQualitity" id="safeQualitityTopTen">
        		<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
					 viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">
				<path d="M-152.4,347.4h8v100h-8C-152.4,447.4-152.4,347.4-152.4,347.4z"/>
				<path d="M-132.6,358.2v16.6h75.8v-16.6L-132.6,358.2z M-61.2,363.5v6h-66.9v-6H-61.2z"/>
				<path d="M-132.6,388.9v16.6h62.5v-16.6L-132.6,388.9z M-74.5,394.2v6h-53.7v-6H-74.5z"/>
				<path d="M-132.6,419.6v16.6H-82v-16.6H-132.6z M-86.7,424.9v6h-41.5v-6H-86.7z"/>
				</svg>
        	</a>
        	<!-- 安全质量饼图 -->
            <%--<a href="javascript:;" class="btn item-bg-blue items-grid-view hidden safeQualitity" id="safeQualitityUnitLine">--%>
            	<%--<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"--%>
					 <%--viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">--%>
				<%--<g>--%>
					<%--<path d="M-57.9,376c-2.3-5.6-5.6-10.7-9.9-15c-4.2-4.3-9.1-7.7-14.6-10.1c-5.7-2.5-11.7-3.7-17.9-3.7c-1.3,0-2.4,1.1-2.4,2.5v44.6--%>
						<%--c0,1.4,1.1,2.5,2.4,2.5l43.5,0.1c0,0,0,0,0,0c0.6,0,1.3-0.3,1.7-0.7c0.5-0.5,0.7-1.1,0.7-1.8C-54.3,388-55.5,381.8-57.9,376z--%>
						 <%--M-96.7,390.6v-37.2c9.5,0.6,18.3,4.6,25.1,11.6c6.8,6.9,10.7,16,11.3,25.7L-96.7,390.6z"/>--%>
					<%--<path d="M-62.7,404.2c-0.1-1.9-1.6-3.5-3.5-3.5c-1.9,0-3.4,1.5-3.5,3.5h0c-2.1,20.2-18.6,35.9-38.8,35.9c-21.5,0-39-18-39-40.1--%>
						<%--c0-20.9,15.5-38,35.3-39.9c0,0,0.1,0,0.1,0c1.9,0,3.5-1.6,3.5-3.6c0-2-1.5-3.6-3.5-3.6c0,0,0,0-0.1,0v0--%>
						<%--c-23.6,1.9-42.3,22.2-42.3,47c0,26.1,20.5,47.2,45.9,47.2c23.6,0,43.1-18.4,45.6-42C-62.7,404.8-62.7,404.6-62.7,404.2--%>
						<%--C-62.7,404.3-62.7,404.3-62.7,404.2C-62.7,404.2-62.7,404.2-62.7,404.2L-62.7,404.2z"/>--%>
				<%--</g>--%>
				<%--</svg>--%>
            <%--</a>--%>
        	<!-- 安全质量折线图 -->
        	<%--<a href="jabascript:;" class="btn item-bg-blue items-grid-view hidden safeQualitity" id="safeQualitityCuLine">--%>
        		<%--<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"--%>
					 <%--viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">--%>
				<%--<path d="M-58.3,390.7C-58.3,390.7-58.3,390.7-58.3,390.7C-58.3,390.7-58.3,390.7-58.3,390.7c0-0.3,0-0.4,0-0.6--%>
					<%--c-0.3-3.2-3-5.7-6.3-5.7c-1.5,0-2.9,0.5-4,1.4l-12.1-10.7c0.3-0.7,0.4-1.5,0.4-2.3c0-3.5-2.8-6.3-6.3-6.3c-3.5,0-6.3,2.8-6.3,6.3--%>
					<%--c0,1.7,0.7,3.3,1.9,4.4l-7.7,20.9c-0.1,0-0.2,0-0.4,0c-1.1,0-2,0.3-2.9,0.8l-3.5-2.8c0.1-0.5,0.2-1,0.2-1.5c0-3.5-2.8-6.3-6.3-6.3--%>
					<%--s-6.3,2.8-6.3,6.3c0,1.2,0.3,2.2,0.9,3.2l-12.9,13.2c-0.5-0.1-1.1-0.3-1.7-0.3c-3.5,0-6.3,2.8-6.3,6.3c0,3.5,2.8,6.3,6.3,6.3--%>
					<%--c3.5,0,6.2-2.8,6.2-6.3c0-1.1-0.3-2.2-0.8-3.1l12.9-13.3c0.5,0.1,1,0.2,1.6,0.2c1.2,0,2.3-0.4,3.2-0.9l3.3,2.7--%>
					<%--c-0.2,0.6-0.3,1.2-0.3,1.9c0,3.5,2.8,6.3,6.3,6.3c3.5,0,6.3-2.8,6.3-6.3c0-1.6-0.6-3.1-1.6-4.2l7.8-21.1c0,0,0,0,0.1,0--%>
					<%--c0.9,0,1.8-0.2,2.6-0.6l13,11.5c0,0.2,0,0.4,0,0.7c0,3.5,2.8,6.3,6.3,6.3c3.3,0,5.9-2.5,6.3-5.7C-58.3,391.1-58.3,390.9-58.3,390.7--%>
					<%--C-58.3,390.7-58.3,390.7-58.3,390.7z"/>--%>
				<%--<path d="M-152.1,441.9v-90c0-2.8,1.8-5,4-5c2.2,0,4,2.2,4,5v90c0,2.8-1.8,5-4,5C-150.4,446.8-152.1,444.6-152.1,441.9L-152.1,441.9z--%>
					<%--"/>--%>
				<%--<path d="M-147.2,438.8h85.5c2.8,0,5,1.8,5,4s-2.2,4-5,4h-85.5c-2.8,0-5-1.8-5-4S-149.9,438.8-147.2,438.8L-147.2,438.8z"/>--%>
				<%--</svg>--%>
        	<%--</a>--%>
        </div>
    </div>
    <!-- radio -->
    
    <div class="flower-nav">
        <div class="flower-left-top ">
            <a href="javascript:;" class="left-top-btn fourLeaf fourLeafClcik"></a>
        </div>
        <div class="flower-left-bottom">
            <a href="javascript:;" class="left-bottom-btn fourLeaf"></a>
        </div>
        <div class="flower-right-top">
            <a href="javascript:;" class="right-top-btn fourLeaf"></a>
        </div>
        <div class="flower-right-bottom">
            <a href="javascript:;" class="right-bottom-btn fourLeaf"></a>
        </div>
        <div class="flower-heart">
        	<span class="totalName"><b>工程<br>施工</b></span>
            <!-- <i class="fa fa-bar-chart-o">
            </i> -->
        </div>
    </div>
    <%--<div class="echartsBoxTopLeft"  id="echartsBoxTopLeft"></div>--%>
   	<%--<div class="echartsBoxTopRight" id="echartsBoxTopRight"></div>--%>
    <div class="content-box" id="echartsBox">
		<%--<div class="chooseRadio">--%>
			<%--<input type="radio" value="" name="constructionUnit"/><span style="margin-left:4px;">全部</span>--%>
			<%--<input type="radio" value="110201" name="constructionUnit" /><span style="margin-left:4px;">居民户</span>--%>
			<%--<input type="radio" value="110202" name="constructionUnit" /><span style="margin-left:4px;">公建户</span>--%>
			<%--<input type="radio" value="110203" name="constructionUnit" /><span style="margin-left:4px;">改管</span>--%>
			<%--<input type="radio" value="110204" name="constructionUnit" /><span style="margin-left:4px;">干线</span>--%>
			<%--<input type="radio" value="110205" name="constructionUnit" /><span style="margin-left:4px;">智能表</span>--%>
			<%--<input type="radio" value="110" name="constructionUnit" /><span style="margin-left:4px;">场站</span>--%>
		<%--</div>--%>

		<div class="changeBox hidden" id="scheduleBox"></div>                    <%-- //施工进度echarts--%>
		<div class="changeBox hidden" id="safeQualitityBox"></div>               <%--//安全质量echarts--%>
		<div class="echartSmallLeftBox hidden" id="echartSmallLeftBox"></div>    <%--承包方左Echarts--%>
		<div class="echartSmallRightBox hidden" id="echartSmallRightBox"></div>  <%--承包方右Echarts--%>

		<%--<div class="echartSmallLeftBox" id="echartSmallLeftBox"></div>--%>
   		<%--<div class="echartSmallRightBox" id="echartSmallRightBox"></div>--%>
		<div class="clearboth form-box m-t-5" id="listBox">
			<form id="projectListForm">
				<table id="projectConstructListTable" class="table table-striped table-bordered nowrap" width="100%"">
		        	<thead>
			            <tr>
	               		    <th>工程编号</th>
	               		    <th>工程名称</th>
	               		    <th>工程地点</th>
			                <th>工程规模</th>
			                <th>工程状态</th>
			                <th>施工单位</th>
			                <%--<th>分包单位</th>--%>
	               		    <th>设计单位</th>
			                <th>勘察人</th>
			            </tr>
		           </thead>
				</table>
			</form>
		</div>
		<%--<div class="clearboth form-box m-t-5  mapBox" id="mapBox"></div>--%>
    </div>
    <input type="hidden" id="path"/>
</div>
</body>
</html>
<!-- ================= BEGIN BASE JS ================== -->
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
        <script src="/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script src="/assets/plugins/parsley/dist/parsley.js" async></script>
        <script src="/assets/plugins/jSignature/jSignature.min.js" async></script>
        <script src="/assets/plugins/gritter/js/jquery.gritter.js" async></script>
        <script src="/assets/plugins/jquery-qrcode/jquery.qrcode.min.js"></script>
        <script src="/assets/plugins/echarts/echarts.min.js"></script>
        <script src="/assets/plugins/echarts/shine.js"></script>
        <script src="/assets/plugins/echarts/infographic.js"></script>
        <!-- ================== END BASE JS ================== -->
        
        <!-- ================== BEGIN PAGE LEVEL JS ================== -->
        <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
		<script type="text/javascript" src="https://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
        <script src="/assets/js/apps.js"></script>
        <script src="/js/ecloud.js"></script>
        <!-- ================== END PAGE LEVEL JS ================== -->
<script>
	var path = '<%=basePath%>';
	$("#path").val(path);
	
	
	$.getScript('/assets/plugins/echarts/build/dist/echarts.js').done(function(){
		$.getScript('/projectjs/monitor/new-project-construct-list.js').done(function () {
		    projectConstructList.init();
		});
	});


    function gridScale() {
        /*        var height = $(window).innerHeight(),
         ul = $(".lean-grid .grid"),
         uheight = ul.height();
         ul.css({
         "-webkit-transform": "scale(" + height / uheight + ")",
         "-moz-transform"   : "scale(" + height / uheight + ")",
         "-ms-transform"    : "scale(" + height / uheight + ")",
         "-o-transform"     : "scale(" + height / uheight + ")",
         "transform"        : "scale(" + height / uheight + ")"
         });
         $(".chart-box").css({
         "padding-left": height
         });*/


    }
    gridScale();

    var timer,
        delay = function () {
            clearTimeout(timer);
            timer = setTimeout(gridScale, 50);
        };

    window.onresize = delay;  
</script>

