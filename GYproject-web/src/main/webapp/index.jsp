<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn" manifest="staticFileCache.appcache">
    <head>
        <meta charset="utf-8" />
        <title>工程项目管理系统</title>
        <meta http-equiv="Content-Security-Policy" content="default-src 'self' http://cordova.apache.org data: gap: cdvfile: https://ssl.gstatic.com; child-src 'self' http://cordova.apache.org data: gap: blob: clob: cdvfile: https://ssl.gstatic.com; style-src 'self' 'unsafe-inline'; script-src 'self' *.baidu.com *.bdimg.com https://gss0.bdstatic.com http://api0.map.bdimg.com 'unsafe-eval' 'unsafe-inline' data:; img-src * data: blob: cdvfile: 'unsafe-eval';connect-src * blob:; media-src *">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
		<META HTTP-EQUIV="expires" CONTENT="0">
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <meta content="" name="description" />
        <meta content="ferrinweb @ dfsoft.cc" name="author" />
		<link Rel="shortcut icon" href="./images/common/favicon.ico">
        <!-- ================== BEGIN BASE CSS STYLE ================== -->
        <link href="assets/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
        <link href="assets/css/animate.min.css" rel="stylesheet" />
        <link href="assets/css/style.min.css" rel="stylesheet" />
        <link href="assets/css/style-responsive.min.css" rel="stylesheet" />
        <link href="assets/plugins/ionicons/css/ionicons.min.css" rel="stylesheet" />

        <link href="assets/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
		<link href="assets/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
		<link href="assets/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" rel="stylesheet" />
		<link href="assets/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
		<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
		<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
		<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
		<link href="assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" />
		<link href="assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet">
        <!-- ================== END BASE CSS STYLE ================== -->

        <!-- ================== BEGIN BASE JS ================== -->
        <script src="assets/plugins/pace/pace.min.js"></script>
        <!-- ================== END BASE JS ================== -->
        <!-- ================== 开始 云服务框架更新样式 ================== -->
        <link href="css/ecloud.css" rel="stylesheet" type="text/css"/>
        <!-- ================== 结束 云服务框架更新样式 ================== -->
         <style>
        .red-point{
          position: relative;
        }

        .red-point::before{
          content: " ";
          border: 4px solid red;/*设置红色*/
          border-radius:3px;/*设置圆角*/
          position: absolute;
          z-index: 1000;
          right: 0%;
          margin-right: 4px;
          margin-top: 1px;
        }
        
    </style>
    </head>
   
    <body class="isMobile">
        <!-- begin #page-loader -->
        <div id="page-loader" class="fade in"><span class="spinner"></span></div>
        <!-- end #page-loader -->

        <!-- begin #page-container -->
        <div id="page-container" class="page-container fade page-sidebar-fixed page-header-fixed page-with-top-menu ">
        	<!-- 页首展开按钮，移动设备上页首收起后显示 -->
        	<div class="header-expand-btn"></div>
            <!-- begin #header -->
            <div id="header" class="header navbar navbar-default navbar-fixed-top">
                <!-- begin container-fluid -->
                <div class="container-fluid">
                    <!-- begin mobile sidebar expand / collapse button -->
                    <div class="navbar-header">
                        <a href="index.jsp" class="navbar-brand">
                          <img src="images/common/logo-white.png" /> 
                            <!-- <img src="images/common/cloud3.png" />  -->
                            <!-- <span class="navbar-logo"> </span>-->
                        </a>
                        <button type="button" class="navbar-toggle" data-click="sidebar-toggled">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div id="head-content" class="head-content">
                            <h1 class="enesys-cloud-page-title"></h1>
                        </div>
                    </div>
                    <!-- end mobile sidebar expand / collapse button -->

                    <!-- begin #top-menu -->
                    <div id="top-menu" class="top-menu"></div>
					<input type="hidden" class="loginId"  id="loginId">
                    <!-- begin header navigation right -->
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown navbar-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="assets/img/user-13.jpg" class="hidden" alt="" /> 
                                <span class="hidden-xs profile-name">${sessionScope.session_loginInfo.staffName}</span> <b class="caret"></b>
                            </a>
                            <ul class="admin-menu dropdown-menu animated fadeInRight">
                                <li class="arrow"></li>
                                <li><a href="admin-index.jsp">系统设置</a></li>
                                <li class="divider"></li>
                                <li><a href="projInfoNew.jsp">监控管理</a></li>
                                <li class="divider"></li>  
                                <li><a href="javascript:;" class="updateInfo-pop">更新信息</a></li>
                                <li class="divider"></li>  
                                <li><a href="javascript:;" class="changepassword-pop">修改密码</a></li>
                                <!-- <li class="divider"></li> -->
                                <li><div id="qrcodebox" title="扫一扫直接在手机访问"></div><p class="qrcode-desc">扫一扫用手机访问</p></li>
                                <li class="divider"></li>
                                <li><a id="logout" href="javascript:void(0)">退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- end header navigation right -->
                </div>
                <!-- end container-fluid -->
            </div>
            <!-- end #header -->
            <div class="toggleBtn2"><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></div>
            <!-- begin #sidebar -->
            <div id="sidebar" class="sidebar mobile-s">
                <!-- begin sidebar scrollbar -->
                <div data-scrollbar="true" data-height="100%">
                    <!-- begin sidebar user -->
                    <ul class="nav profilebox">
                        <!-- begin sidebar minify button -->
                        <li class="toggleBtn"><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
                        <!-- end sidebar minify button -->
                        <li class="nav-profile"></li>
                    </ul>
                    <!-- end sidebar user -->
                    <!-- begin sidebar nav -->
                    <ul class="nav" id="sidebar-nav"></ul>
                    <!-- end sidebar nav -->
                </div>
                <!-- end sidebar scrollbar -->
               	<div class="mobile-scroll-btn down"></div>
               	<div class="mobile-scroll-btn up"></div>
            </div>
            <div class="mobile-sidebar-expand-btn"></div>
            <div class="sidebar-bg"></div>
            <!-- end #sidebar -->

            <!-- begin #ajax-content -->
            <div id="ajax-content"></div>
            <!-- end #ajax-content -->

            <!-- begin attach-panel -->
            <div class="attach-panel hidden">
                <a href="javascript:;" data-click="attach-panel-expand" class="attach-collapse-btn"><i class="fa fa-cogs"></i>关联操作</a>
                <div class="attach-panel-content">
                    <div class="attach-btn collection hidden row m-t-10">
                        <a href="javascript:;" class="attach-caiji">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-caiji" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<path class="attach-caiji" d="M297.2,67.2c-195.7,0-354.3,158.6-354.3,354.3c0,195.7,158.6,354.3,354.3,354.3
										c195.7,0,354.3-158.6,354.3-354.3C651.5,225.8,492.9,67.2,297.2,67.2z M458.8,600c0,17.2-13,29.7-30.8,29.7H169.3
										c-17.7,0-33.4-12.5-33.4-29.7V271.2c0-17.2,11.5-29.7,35-29.7H199v-20.3c0-4.2,5.2-7.8,9.4-7.8c4.7,0,9.4,3.6,9.4,7.8v20.3h42.8
										v-20.3c0-4.2,5.2-7.8,9.4-7.8c4.7,0,9.4,3.6,9.4,7.8v20.3h39.1v-20.3c0-4.2,5.2-7.8,9.4-7.8c4.7,0,9.4,3.6,9.4,7.8v20.3h39.1v-20.3
										c0-4.2,5.2-7.8,9.4-7.8c4.7,0,9.4,3.6,9.4,7.8v20.3h33.4c17.7,0,30.8,12.5,30.8,29.7v30.7l-24,23.4v-54.2c0-4.2-2.6-6.3-6.8-6.3
										h-32.9v10.9c0,4.2-5.2,7.8-9.4,7.8c-4.7,0-9.4-3.6-9.4-7.8v-10.9h-39.1v10.9c0,4.2-5.2,7.8-9.4,7.8c-4.7,0-9.4-3.6-9.4-7.8v-10.9
										h-39.1v10.9c0,4.2-5.2,7.8-9.4,7.8c-4.7,0-9.4-3.6-9.4-7.8v-10.9h-42.8v10.9c0,4.2-5.2,7.8-9.4,7.8c-4.7,0-9.4-3.6-9.4-7.8v-10.9
										h-28.7c-5.7,0-11-1.6-10.4,6.3v329.3c0,4.2,4.2,5.7,8.3,5.7l259.3,0c4.2,0,6.8-1.6,6.8-5.7v-90.7l24-23.4V600z M194.8,403.9v-23.3
										h149.7v23.3H194.8z M303,432.4v23.3H194.8v-23.3H303z M194.8,352.1v-23.3h180v23.3H194.8z M533.5,395.1L503,425.6c-2,2-4.6,3-7.3,3
										c-2.8,0-5.4-1.1-7.3-3l-30.5-30.6c-4-4-4-10.6,0-14.7c2-2,4.6-3,7.3-3c2.8,0,5.4,1.1,7.3,3l23.2,23.2l15.9-15.9L464.9,341
										L297.5,505.3v46h46.4l113.5-110.2c1.9-1.9,4.5-3,7.3-3c2.8,0,5.4,1.1,7.3,3c4,4,4,10.6,0,14.7L355.5,569.1c-2,1.9-4.6,3-7.3,3h-61.1
										c-5.7,0-10.4-4.7-10.4-10.4V501c0-2.8,1.1-5.3,3-7.3l177.8-174.7c2-1.9,4.6-3,7.3-3c2.8,0,5.4,1.1,7.3,3l61.3,61.4
										c1.9,1.9,3,4.6,3,7.3C536.5,390.5,535.4,393.1,533.5,395.1z"/>
								</svg>
                        	</i>
                        	<span>采集</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn hidden charge row m-t-10">
                        <a href="javascript:;" class="attach-charge">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-charge" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<path class="attach-charge" d="M297.7,67.3C102,67.3-56.6,225.9-56.6,421.6c0,195.7,158.6,354.3,354.3,354.3c195.6,0,354.3-158.6,354.3-354.3
											C652.1,225.9,493.4,67.3,297.7,67.3L297.7,67.3z M450,396.6v36H330.7v72H450v36H330.7v72h-49.3v-72H162.1v-36h119.4v-72H162.1v-36
											l117,0.4L120.5,252.7h60.7L305,365.1l114.8-112.5h60.7L334.2,397L450,396.6L450,396.6z M469.3,391.6"/>
									</g>
								</svg>
                        	</i>
                        	<span>收费</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn hidden detail row m-t-10">
                        <a href="javascript:;" class="attach-detail">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-detail" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<path class="attach-detail" d="M297.6,67.4C101.9,67.4-56.7,226-56.7,421.7c0,195.7,158.6,354.3,354.3,354.3c195.7,0,354.3-158.6,354.3-354.3
											C651.9,226,493.3,67.4,297.6,67.4L297.6,67.4L297.6,67.4z M293.8,639.7c-14.6,8.2-26.6,12.3-35.9,12.3c-8.1,0-14.6-2.5-19.4-7.5
											c-4.8-5-7.1-11.9-7.1-20.8c0-28.4,17.4-97.9,52.3-208.3c1.6-5.2,2.4-9.9,2.4-14c0-4.5-2.1-6.8-6.3-6.8c-4.7,0-9.9,1.8-15.9,5.5
											c-5.9,3.6-19.1,15.2-39.6,34.8L209,423.2c23.2-25,44.4-42.8,63.6-53.4c19.2-10.6,35.5-15.9,48.9-15.9c7.4,0,13.1,1.6,17.1,4.8
											c3.9,3.2,5.9,7.5,5.9,13c0,6.6-7.5,34.6-22.6,83.9C297.3,536.3,285,585,285,601.5c0,3.2,0.8,5.9,2.4,8c1.6,2.2,3.4,3.2,5.2,3.2
											c7.4,0,26.5-14.1,57.2-42.3l13.6,13C331.7,612.8,308.4,631.5,293.8,639.7L293.8,639.7L293.8,639.7z M375.5,260.4
											c-7.1,7.6-15.1,11.4-23.9,11.4c-7,0-12.8-2.4-17.4-7.2c-4.6-4.8-7-11-7-18.8c0-10.2,3.4-18.9,10.1-26.1
											c6.7-7.2,14.8-10.7,24.1-10.7c7.2,0,13.1,2.3,17.8,7c4.6,4.7,7,10.6,7,17.9C386.2,244,382.6,252.8,375.5,260.4L375.5,260.4
											L375.5,260.4z M375.5,260.4"/>
									</g>
								</svg>
                        	</i>
                        	<span>详情</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn hidden dispatch row m-t-10">
                        <a href="javascript:;" class="attach-dispatch">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-dispatch" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<path class="attach-dispatch" d="M494.5,353.1c-3.9-1.6-7.7-2-11.7-2H295.1h-37.6c2.9-2.7,5-4.5,7-6.3c14.6-12.9,29.2-25.7,43.7-38.8
											c12.3-11,9.2-28-6.3-33.8c-10.5-3.9-20.5-2.7-29.2,5c-25.5,22.5-51.1,45.1-76.7,67.5c-1.7,1.5-2.1,3.1-2.1,5.2
											c0.1,65.9,0.1,131.8,0,197.7c0,2.5,0.9,3.7,3,4.9c9.9,5.8,19.8,11.7,29.7,17.7c2,1.2,4.1,1.7,6.3,1.7c39.1-0.1,78.2-0.1,117.3-0.1
											c2.4-0.1,4.8-0.2,7.1-0.7c8.6-1.8,14.8-6.8,17.9-15.2c2.6-7,0-13-4.5-18.3c-3.5-4-8.1-6.3-13.2-7.6c-5.9-1.5-5.9-1.4-5.9-7.6v-0.4
											c0.2-3.1-1.4-7.2,0.6-9.2c1.9-1.9,5.9-0.4,9.1-0.5c7.2-0.3,14.3,0.7,21.4-0.8c12.7-2.6,25.8-19,12.1-33.7c-2.8-3-6.3-4.6-9.9-6.5
											c-2.9-1.4-7.3,0.6-8.6-2.8c-1-3-0.2-6.5-0.2-9.9c0-0.6,0.1-1.4,0-2c-0.6-3.3,0.9-4,3.9-3.9c6.3,0.4,12.4-0.2,18.1-3.5
											c12.4-7.2,14.6-22.3,4.5-31.8c-2.6-2.5-5.9-4.1-9.1-5.6c-2.9-1.4-7.4,0.2-8.7-3c-1.1-2.9-0.2-6.5-0.2-9.9c0-0.5,0.1-1.1,0-1.6
											c-0.6-3.3,0.8-4.1,4.1-4.1c31.2,0.1,62.6,0.1,93.9,0.1c2.6,0,5.2-0.1,7.9-0.6c9.7-1.8,17.9-10.1,18.8-18.6
											C510.1,365.2,504.3,357,494.5,353.1z"/>
										<path class="attach-dispatch" d="M177.4,357.1c0-2.8-0.6-3.6-3.5-3.5c-8.8,0.1-17.5,0.1-26.3,0c-2.6-0.1-3.2,0.7-3.2,3.2
											c0.1,63.2,0.1,126.4,0,189.7c0,2.5,0.5,3.2,3,3.1c8.6-0.1,17.3-0.2,25.9,0c3.2,0.1,4.1-0.8,4.1-4.1c-0.2-31.4-0.1-62.8-0.1-94.2
											C177.3,419.9,177.3,388.5,177.4,357.1z"/>
										<path class="attach-dispatch" d="M297.8,67.8c-195.7,0-354.3,158.6-354.3,354.3c0,195.7,158.6,354.3,354.3,354.3
											c195.7,0,354.3-158.6,354.3-354.3C652.2,226.4,493.5,67.8,297.8,67.8z M497.2,411.9c-4.6,1.5-9.3,1.9-14.1,1.9h-63.3
											c11.8,22,6.9,39.7-13.6,53.7c10.4,10.6,14.4,23.4,9.2,37.9c-5.1,14.1-16.4,21.5-30.6,25.6c4.1,5.6,6.7,11.7,7.2,18.6
											c1.1,15.8-6.6,32.1-28,37.3c-4.3,1.1-8.5,1.8-13,1.8H228.6c-2.5,0-4.6-0.5-6.8-1.7c-11.6-6.2-23.1-12.3-34.6-18.6
											c-2.5-1.3-5-1.9-7.9-1.9c-16.2,0.1-32.4,0-48.5,0.1c-2.8,0-3.5-0.8-3.5-3.5c0.1-74,0.1-148,0-221.9c0-3.3,1.2-3.8,4.1-3.8
											c15.5,0.1,31,0,46.6,0.1c3.4,0,5.7-1,8.1-3.2c25.2-22.2,50.4-44.5,75.8-66.7c13.9-12.1,29.9-15.4,46.6-8.1
											c29.1,12.6,32,45.4,10.3,63.1c-5.6,4.5-11,9.4-17.2,14.8h115.3c22.7,0,45.3-0.1,68,0.1c17.4,0.2,30.9,8,38.3,23.7
											C532.7,381.5,520.4,404.9,497.2,411.9z"/>
									</g>
								</svg>
                        	</i>
                        	<span>计划</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn hidden customer row m-t-10">
                        <a href="javascript:;" class="attach-customer">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-customer" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<path class="attach-customer" d="M494.5,353.1c-3.9-1.6-7.7-2-11.7-2H295.1h-37.6c2.9-2.7,5-4.5,7-6.3c14.6-12.9,29.2-25.7,43.7-38.8
											c12.3-11,9.2-28-6.3-33.8c-10.5-3.9-20.5-2.7-29.2,5c-25.5,22.5-51.1,45.1-76.7,67.5c-1.7,1.5-2.1,3.1-2.1,5.2
											c0.1,65.9,0.1,131.8,0,197.7c0,2.5,0.9,3.7,3,4.9c9.9,5.8,19.8,11.7,29.7,17.7c2,1.2,4.1,1.7,6.3,1.7c39.1-0.1,78.2-0.1,117.3-0.1
											c2.4-0.1,4.8-0.2,7.1-0.7c8.6-1.8,14.8-6.8,17.9-15.2c2.6-7,0-13-4.5-18.3c-3.5-4-8.1-6.3-13.2-7.6c-5.9-1.5-5.9-1.4-5.9-7.6v-0.4
											c0.2-3.1-1.4-7.2,0.6-9.2c1.9-1.9,5.9-0.4,9.1-0.5c7.2-0.3,14.3,0.7,21.4-0.8c12.7-2.6,25.8-19,12.1-33.7c-2.8-3-6.3-4.6-9.9-6.5
											c-2.9-1.4-7.3,0.6-8.6-2.8c-1-3-0.2-6.5-0.2-9.9c0-0.6,0.1-1.4,0-2c-0.6-3.3,0.9-4,3.9-3.9c6.3,0.4,12.4-0.2,18.1-3.5
											c12.4-7.2,14.6-22.3,4.5-31.8c-2.6-2.5-5.9-4.1-9.1-5.6c-2.9-1.4-7.4,0.2-8.7-3c-1.1-2.9-0.2-6.5-0.2-9.9c0-0.5,0.1-1.1,0-1.6
											c-0.6-3.3,0.8-4.1,4.1-4.1c31.2,0.1,62.6,0.1,93.9,0.1c2.6,0,5.2-0.1,7.9-0.6c9.7-1.8,17.9-10.1,18.8-18.6
											C510.1,365.2,504.3,357,494.5,353.1z"/>
										<path class="attach-customer" d="M177.4,357.1c0-2.8-0.6-3.6-3.5-3.5c-8.8,0.1-17.5,0.1-26.3,0c-2.6-0.1-3.2,0.7-3.2,3.2
											c0.1,63.2,0.1,126.4,0,189.7c0,2.5,0.5,3.2,3,3.1c8.6-0.1,17.3-0.2,25.9,0c3.2,0.1,4.1-0.8,4.1-4.1c-0.2-31.4-0.1-62.8-0.1-94.2
											C177.3,419.9,177.3,388.5,177.4,357.1z"/>
										<path class="attach-customer" d="M297.8,67.8c-195.7,0-354.3,158.6-354.3,354.3c0,195.7,158.6,354.3,354.3,354.3
											c195.7,0,354.3-158.6,354.3-354.3C652.2,226.4,493.5,67.8,297.8,67.8z M497.2,411.9c-4.6,1.5-9.3,1.9-14.1,1.9h-63.3
											c11.8,22,6.9,39.7-13.6,53.7c10.4,10.6,14.4,23.4,9.2,37.9c-5.1,14.1-16.4,21.5-30.6,25.6c4.1,5.6,6.7,11.7,7.2,18.6
											c1.1,15.8-6.6,32.1-28,37.3c-4.3,1.1-8.5,1.8-13,1.8H228.6c-2.5,0-4.6-0.5-6.8-1.7c-11.6-6.2-23.1-12.3-34.6-18.6
											c-2.5-1.3-5-1.9-7.9-1.9c-16.2,0.1-32.4,0-48.5,0.1c-2.8,0-3.5-0.8-3.5-3.5c0.1-74,0.1-148,0-221.9c0-3.3,1.2-3.8,4.1-3.8
											c15.5,0.1,31,0,46.6,0.1c3.4,0,5.7-1,8.1-3.2c25.2-22.2,50.4-44.5,75.8-66.7c13.9-12.1,29.9-15.4,46.6-8.1
											c29.1,12.6,32,45.4,10.3,63.1c-5.6,4.5-11,9.4-17.2,14.8h115.3c22.7,0,45.3-0.1,68,0.1c17.4,0.2,30.9,8,38.3,23.7
											C532.7,381.5,520.4,404.9,497.2,411.9z"/>
									</g>
								</svg>
                        	</i>
                        	<span>客户</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn hidden applydelay row m-t-10">
                        <a href="javascript:;" class="attach-applydelay">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-applydelay" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<path class="attach-applydelay" d="M337.8,8.6C142.1,8.6-16.5,167.2-16.5,362.9c0,195.7,158.6,354.3,354.3,354.3c195.7,0,354.3-158.6,354.3-354.3
											C692.2,167.2,533.5,8.6,337.8,8.6z M520.7,440.1c-10,23.6-24.3,44.8-42.5,63.1c-18.2,18.2-39.4,32.5-63.1,42.5
											c-24.5,10.4-50.5,15.6-77.2,15.6c-26.8,0-52.8-5.2-77.2-15.6c-23.6-10-44.8-24.3-63.1-42.5C179.3,485,165,463.8,155,440.1
											c-10.4-24.5-15.6-50.5-15.6-77.2s5.2-52.8,15.6-77.2c10-23.6,24.3-44.8,42.5-63.1c18.2-18.2,39.4-32.5,63.1-42.5
											c24.5-10.4,50.5-15.6,77.2-15.6c26.8,0,52.8,5.2,77.2,15.6c23.6,10,44.8,24.3,63.1,42.5c18.2,18.2,32.5,39.4,42.5,63.1
											c10.4,24.5,15.6,50.5,15.6,77.2S531,415.6,520.7,440.1z"/>
										<path class="attach-applydelay" d="M337.8,195.1c-44.8,0-86.9,17.4-118.6,49.1c-31.7,31.7-49.1,73.8-49.1,118.6c0,44.8,17.4,86.9,49.1,118.6
											c31.7,31.7,73.8,49.1,118.6,49.1s86.9-17.4,118.6-49.1c31.7-31.7,49.1-73.8,49.1-118.6c0-44.8-17.4-86.9-49.1-118.6
											C424.8,212.6,382.6,195.1,337.8,195.1z M391.4,435.4c-2.9,2.3-6.3,3.5-9.7,3.5c-4.4,0-8.8-1.9-11.9-5.6l-47.4-57.7V243.5
											c0-8.5,6.9-15.3,15.3-15.3s15.3,6.9,15.3,15.3v121.1l40.4,49.2C398.9,420.3,398,430,391.4,435.4z"/>
									</g>
								</svg>
                        	</i>
                        	<span>延期</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn hidden settlementchange row m-t-10">
                        <a href="javascript:;" class="attach-settlementchange">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-settlementchange" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<rect x="351.9" y="135.2" class="attach-settlementchange" width="101.5" height="28.5"/>
										<rect x="351.9" y="204.8" class="attach-settlementchange" width="101.5" height="28.5"/>
										<rect x="351.9" y="272.7" class="attach-settlementchange" width="101.5" height="28.5"/>
										<polygon class="attach-settlementchange" points="141.8,211.3 141.8,230.6 199.7,230.6 199.7,269.2 141.8,269.2 141.8,288.4 199.6,288.4 199.6,327 
											238.2,327 238.2,288.4 296.1,288.4 296.1,269.2 238.2,269.2 238.2,230.6 296.1,230.6 296.1,211.3 243.1,211.5 315.4,134.1 
											276.8,134.1 218.9,192 161.1,134.1 122.5,134.1 201.5,211.5 	"/>
										<path class="attach-settlementchange1" d="M453.4,346.4c-102.4,0-185.5,83-185.5,185.5s83,185.5,185.5,185.5s185.5-83,185.5-185.5
											S555.9,346.4,453.4,346.4z M453.4,690.9c-87.8,0-159-71.2-159-159c0-87.8,71.2-159,159-159c87.8,0,159,71.2,159,159
											C612.4,619.7,541.2,690.9,453.4,690.9z"/>
										<g>
											<path class="attach-settlementchange" d="M243.1,483.9H122.5v28.5h116.2C239.5,502.8,241,493.2,243.1,483.9z"/>
										</g>
										<g>
											<path class="attach-settlementchange" d="M271.5,416h-149v28.5h133.7C260.6,434.6,265.7,425.1,271.5,416z"/>
										</g>
										<g>
											<path class="attach-settlementchange" d="M343.4,346.4H122.5V375h183C317,364.1,329.7,354.5,343.4,346.4z"/>
										</g>
										<g>
											<path class="attach-settlementchange" d="M246.7,593.6H76.2c-7.6,0-13.7-6.1-13.7-13.7V51.6c0-7.6,6.1-13.7,13.7-13.7h421.3c7.6,0,13.7,6.1,13.7,13.7
												v272.5c9.7,2.7,19,6,28.1,9.9V49.8c0-22.7-18.4-41.1-41.1-41.1H76.5c-22.7,0-41.1,18.4-41.1,41.1v532.9
												c0,22.7,18.4,41.1,41.1,41.1h181.7C253.7,614.1,249.8,604,246.7,593.6z"/>
										</g>
										<path class="attach-settlementchange1" d="M515.2,578.3h-95.9l18,30.3c1.8,3-2.5,8.6-9.5,12.6c-7,3.9-14.1,4.7-15.9,1.7c0,0-35.8-54.2-35.8-60
											c0-0.5,0.3-1,0.3-1.5c0-0.4,0.1-0.9,0.3-1.4c1.4-7.1,7.4-12.5,14.9-12.5h3.9c1.2-0.2,2.2-0.1,3.2,0h116.6c8.5,0,15.5,6.9,15.5,15.5
											C530.7,571.4,523.8,578.3,515.2,578.3L515.2,578.3z"/>
										<path class="attach-settlementchange1" d="M530.1,503.9c-1.4,7.1-7.4,12.6-14.9,12.6h-3.9c-1.2,0.2-2.2,0.1-3.2,0H391.6c-8.5,0-15.5-6.9-15.5-15.5
											c0-8.5,6.9-15.5,15.5-15.5h95.9l-18-30.3c-1.8-3,2.5-8.6,9.5-12.6c7-3.9,14.1-4.7,15.9-1.7c0,0,35.8,54.2,35.8,60
											c0,0.5-0.3,1-0.3,1.5C530.4,503,530.3,503.4,530.1,503.9L530.1,503.9z"/>
									</g>
								</svg>
                        	</i>
                        	<span>变更明细</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn grade hidden row m-t-10">
                        <a href="javascript:;" class="attach-grade">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-grade" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
									 viewBox="-205 297 200 200" style="enable-background:new -205 297 200 200;" xml:space="preserve">
								<style type="text/css">
									.st0{fill:#FE5E66;}
									.st1{fill:#FFFFFF;}
								</style>
								<circle class="attach-grade st0" cx="-105" cy="397" r="100"/>
								<path class="st1" d="M-53.5,384.5c-0.5-1.5-1.8-3.6-5.1-4.9c-7.9-3-21.2-7.9-24.4-9c-2.1-2.8-10.7-14.3-15.9-21
									c-1.5-1.9-3.7-3-6.1-3h0c-2.3,0-4.4,1-5.8,2.8c-5.3,6.7-14.2,18.4-16.3,21.2c-3.2,1.2-16.4,5.9-24.3,9c-2.5,1-4.3,2.7-5,5
									c-0.7,2.3-0.3,4.9,1.1,7.1c4.6,7.2,12.5,18.8,14.5,21.7c0.1,3.5,0.3,17.8,0.7,26.3c0.1,3.1,1.1,5.3,2.9,6.6c0.9,0.6,2.2,1.3,4.2,1.3
									c0.9,0,1.9-0.1,3.1-0.5c8.1-2.3,21.6-6.3,24.9-7.4c3.3,1,16.8,5.3,24.9,7.5c1.4,0.4,2.8-0.4,3.2-1.8c0.4-1.4-0.4-2.8-1.8-3.2
									c-9.1-2.6-25.3-7.7-25.5-7.7c-0.5-0.2-1-0.2-1.5,0c-0.2,0.1-16.3,5-25.5,7.6c-1.3,0.4-2.3,0.4-2.8,0c-0.5-0.3-0.8-1.3-0.8-2.6
									c-0.4-9.7-0.7-26.8-0.7-27c0-0.5-0.2-1-0.5-1.5c-0.1-0.1-9.7-14.1-14.9-22.2c-0.6-0.9-0.8-1.9-0.5-2.6c0.3-0.9,1.2-1.4,1.9-1.7
									c9-3.4,24.8-9.1,25-9.2c0.5-0.2,0.9-0.5,1.2-0.9c0.1-0.1,10.7-14.2,16.7-21.7c0.6-0.7,1.3-0.9,1.7-0.9c0.8,0,1.6,0.4,2.1,1
									c5.9,7.6,16.2,21.4,16.3,21.6c0.3,0.4,0.7,0.7,1.2,0.9c0.2,0.1,16.2,5.8,25.1,9.3c0.8,0.3,1.7,0.8,2,1.6c0.2,0.5,0,1.2-0.4,1.9
									C-64.1,396-74,411-74,411c-0.3,0.4-0.4,0.9-0.4,1.4l-0.4,16.7c0,1.5,1.1,2.7,2.5,2.7c1.4,0,2.6-1.1,2.7-2.6l0.4-16
									c1.9-2.9,10.1-15.3,14.8-22.4C-53.2,388.9-52.8,386.5-53.5,384.5L-53.5,384.5L-53.5,384.5z"/>
								</svg>
                        	</i>
                        	<span>评分</span>
                        </a>
                    </div>
                     <div class="divider hidden"></div>
                    <div class="attach-btn hidden auditInformation row m-t-10">
                        <a href="javascript:;" class="attach-auditInformation">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-auditInformation" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 595.3 841.9" style="enable-background:new 0 0 595.3 841.9;" xml:space="preserve">
									<g>
										<path class="attach-auditInformation" d="M433.7,445.5c0.1-20.5-0.4-24.5-5-38c-9.3-27.1-29.3-50.6-54.3-63.7c-17.4-9.1-27.5-12-46.1-13.4
											c-22.4-1.6-39.2,1.7-58.9,11.6c-19.4,9.7-33.4,22-45.4,39.9c-13.4,20-17.8,35.6-17.8,62.6c0,19.4,0.6,23.8,5.2,37
											c9.7,28.3,30.3,51.7,57,64.9c24.7,12.2,49.4,15.5,75.5,10c38.5-8.1,70.1-35.8,84.5-74.4C433,469.9,433.5,466,433.7,445.5z
											 M296.7,375.1c-0.5,3.9-3,6.8-11,12c-10.7,7.1-20.9,18.2-26.1,28.5c-5,9.9-12.4,12.2-19.1,6c-5.2-4.8-5.1-7.2,0.8-18.6
											c8.9-17.6,34.5-39.9,45.9-40C292.6,362.9,297.4,369,296.7,375.1z M409.3,458.7c-6.2,37.1-33.8,66.5-69.6,74.1
											c-16,3.4-20.8,3.3-25.8-0.2c-4.7-3.3-5.5-12-1.4-16.1c1.5-1.5,7.4-3.3,13.3-4c16.4-2,28.9-7.6,40-17.7c12.2-11.2,18.8-24,22-43
											c2.7-15.3,4.4-17.7,12.8-17C409.6,435.7,412,442.4,409.3,458.7z"/>
																			<path class="attach-auditInformation" d="M297,67.8c-195.7,0-354.3,158.6-354.3,354.3c0,195.7,158.6,354.3,354.3,354.3c195.7,0,354.3-158.6,354.3-354.3
											C651.3,226.4,492.7,67.8,297,67.8z M194.3,605.1c-22.7-0.1-39-1-44.4-2.4c-14.9-4-28.1-16.7-33.6-32.2c-2.4-6.8-2.8-27.4-2.8-148.7
											c0-157.4-0.5-150.6,13-166c4-4.5,11.2-10.1,15.9-12.5l8.7-4.2h134.1h134.1l10.9,5.6c8.4,4.3,12.5,8,18.2,16.2l7.3,10.6l0.6,26.3
											c0.7,30.4-0.3,33.9-9.7,33.9c-10.1,0-12-4.5-12-29.5c0-23.4-1.9-28.8-13.4-37.3c-5.2-3.8-6.2-3.8-135-3.8
											c-126.9,0-129.9,0.1-136.2,3.7c-3.8,2.2-8.1,7-10.5,11.9l-4.2,8.1v136.9v136.9l4.1,8.1c7.3,14.3,11,15.5,56.1,16.5
											c42.3,1,42.5,1,42.6,11.4C238.3,604.2,233.9,605.3,194.3,605.1z M475.7,601.4c-5.1,4-5.5,4.1-9.9,2.5c-1.5-0.5-14.8-12.9-29.5-27.5
											L409.6,550l-15.8,9.9c-9.2,5.8-22.6,12.2-32,15.2c-14.9,4.9-18.4,5.3-42.8,5.3c-25.1,0-27.6-0.4-42.7-5.7
											c-22.8-8.1-34.9-15.8-52.7-33.7c-17.6-17.6-27.2-32.7-34.4-54.1c-4.5-13.3-4.9-17.2-4.9-42.2s0.5-28.9,4.9-42.2
											c7.2-21.4,16.8-36.5,34.4-54.2c17.6-17.6,32.6-27.1,54.1-34.3c13.3-4.4,17.2-4.9,42.2-4.9c25,0,28.9,0.5,42.2,4.9
											c21.6,7.2,36.6,16.7,54.1,34.4c26.8,27,38.5,53.1,40,89.5c1.3,32.5-5.4,57.4-22.2,82.6l-9.1,13.6l27.8,28
											c18.1,18.3,27.8,29.3,27.8,31.8C480.6,595.9,478.4,599.3,475.7,601.4z"/>
									</g>
								</svg>
                        	</i>
                        	<span>审核信息</span>
                        </a>
                    </div>
                    
                    <div class="divider hidden"></div>
                    <div class="attach-btn modify hidden row m-t-10">
                        <a href="javascript:;" class="attach-modify">
                        	<i class="fa">
                        		<svg version="1.1" id="attach-modify" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										 viewBox="-446.1 187.3 708.7 708.7" style="enable-background:new -446.1 187.3 708.7 708.7;width:55px;" xml:space="preserve">
									<style type="text/css">
										.stt{fill:#00D1C3;}
									</style>
										
										<path class="stt" d="M-91.8,187.3c-195.7,0-354.3,158.6-354.3,354.3c0,195.7,158.6,354.3,354.3,354.3
											c195.7,0,354.3-158.6,354.3-354.3C262.6,345.9,103.9,187.3-91.8,187.3z M66.4,662.5c0,18.7-15.2,33.9-33.9,33.9h-250.4
											c-18.7,0-33.9-15.2-33.9-33.9V420.8c0-18.7,15.2-33.9,33.9-33.9h160.8c6,0,10.8,4.9,10.8,10.8c0,6-4.8,10.8-10.8,10.8h-159.2
											c-7.7,0-13.9,6.2-13.9,13.9v238.4c0,7.7,6.2,13.9,13.9,13.9H30.9c7.7,0,13.9-6.2,13.9-13.9V514.8c0-6,4.9-10.8,10.8-10.8
											c5.9,0,10.8,4.8,10.8,10.8V662.5z M61,457.2L-95.7,613.9c-0.5,0.5-1.2,0.8-1.8,0.8c-0.1,0-0.1-0.1-0.2-0.1l-58.6,16
											c-0.9,0.2-1.8,0.3-2.6,0.3c-2.7,0-5.2-1-7.1-2.9c-2.5-2.6-3.5-6.3-2.6-9.7l16.1-59c0.1-0.3,0.3-0.6,0.5-0.9
											c0.1-0.2,0.1-0.4,0.2-0.5L4.9,401c4.7-4.6,11-7.2,17.9-7.2c7.8,0,15.7,3.3,21.5,9.1l14.8,14.8c5.8,5.8,9.1,13.6,9.1,21.4
											C68.2,446.2,65.7,452.5,61,457.2z"/>
										<path class="attach-modify" d="M30.1,417.1c-2.8-2.8-7.5-3.1-9.9-0.7L4.9,431.7l25.2,25.6l15.6-15.6c1.1-1.1,1.7-2.6,1.7-4.3
											c0-2-0.9-4.1-2.4-5.6L30.1,417.1z"/>
									</svg>
                        	</i>
                        	<span>更正</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn fallBack hidden row m-t-10">
                        <a href="javascript:;" class="attach-fallBack">
                        	<i class="fa">
                        		<?xml version="1.0" encoding="utf-8"?>
									<!-- Generator: Adobe Illustrator 22.0.0, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
									<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										 viewBox="0 0 708.7 708.7" style="enable-background:new 0 0 708.7 708.7;width:55px;" xml:space="preserve">
									<style type="text/css">
										.stfallBack{fill:#FF5B57;}
									</style>
									<g>
										<path class="stfallBack" d="M340.9,293.9c-15.2-0.3-27.6-13-27.6-28.3v-36.2l-114.3,95.8l114.3,96.2V393c0-12.5,8.1-23.4,20.1-27.1
											c17.3-5.2,34.3-7.9,50.7-7.9c63.1,0,105.9,37.7,132,72.6c-3.1-28.2-12.5-52.3-28-71.9C439.3,297,344.8,294,340.9,293.9z"/>
										<path class="stfallBack" d="M354.3,0.8C158.6,0.8,0,159.4,0,355.1c0,195.7,158.6,354.3,354.3,354.3c195.6,0,354.3-158.6,354.3-354.3
											C708.6,159.4,549.9,0.8,354.3,0.8z M540.9,516.8l0,0.2l-0.1,0.2c-0.4,1-2.6,6-6.9,6c-2.6,0-5-1.9-7-5.7l-0.1-0.2l-0.1-0.2
											c-0.1-0.3-10.5-32.5-33.2-64.3c-30.2-42.2-66.8-63.6-108.7-63.6c-14,0-27.9,2.1-40.5,5.9v49.2l-0.1,0.3c-0.2,0.8-4,19.7-19.4,19.7
											c-5.3,0-11.2-2.4-17.7-7.1l-0.1-0.1L173.6,344.6c-1.9-1.1-12.7-7.6-13.3-18.1c-0.4-7.6,4.6-15.1,14.9-22.2l135.1-113.3l0.2-0.1
											c0.2-0.1,4.8-2.9,10.6-3.7l0-0.2l3.2,0c8.3,0,18.5,4.7,20,27.4l0,0.1v48.3c8.4,0.4,33.1,2.3,62.2,10.6c45.4,12.9,82,35.7,105.9,66
											C547.1,383.3,556.6,442.9,540.9,516.8z"/>
									</g>
								</svg>
                        	</i>
                        	<span>回退</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn rectify hidden row m-t-10">
                        <a href="javascript:;" class="attach-rectify">
                        	<i class="fa">
                        		<?xml version="1.0" encoding="utf-8"?>
									<!-- Generator: Adobe Illustrator 22.0.1, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
									<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										 viewBox="0 0 708.7 708.7" style="enable-background:new 0 0 708.7 708.7;" xml:space="preserve">
									<style type="text/css">
									.st0{fill:#F39800;}
									</style>
									<g>
										<path class="st0" d="M214.4,199.5l-16.2,16.3l16.1,16.1c4.5,4.3,11.7,4.3,16.2,0c4.5-4.5,4.5-11.8,0-16.3L214.4,199.5z"/>
										<path class="st0" d="M515.2,274.2l-3.8,3.8c-10.6,10.6-24.7,16.5-39.7,16.5s-29.1-5.9-39.7-16.5c-21.9-22-21.9-57.5,0-79.5l3.8-3.8
											c-44.6-0.2-81.5,35.8-81.5,80.5c0,6.1,0.7,12.2,2.1,18.2l0.1,0.4c0.1,0.4,0.1,0.8,0.2,1.2l0.1,1.1c0,1.1-0.2,2.2-0.5,3.3l-0.4,1.1
											l-0.5,1c-0.5,1-1.2,1.9-2,2.7l-0.3,0.3L206.5,438.8c-8.3,8.3-13.1,19.9-13.1,32.2s4.8,23.8,13.5,32.5c17.4,17.4,47.6,17.4,65,0v0
											l133.7-146.3l0.3-0.3c0.5-0.6,1.1-1.1,1.8-1.5l0.8-0.5c0.4-0.2,0.7-0.4,1.1-0.6l0.9-0.3c0.7-0.2,1.4-0.4,2.2-0.5
											c1.1-0.1,2.3-0.1,3.4,0.1l0.4,0.1c6.1,1.4,12.2,2.1,18.1,2.1c44.4-0.1,80.4-36.1,80.4-80.6V274.2z M239.4,494
											c-12.7,0-23-10.3-23-23c0-12.7,10.3-23,23-23c12.7,0,23,10.3,23,23C262.4,483.7,252.1,494,239.4,494z"/>
										<path class="st0" d="M386.7,420.9l89.3,89.4c9.1,8.7,23.4,8.7,32.5,0c4.4-4.3,6.7-10.1,6.7-16.3c0-6.1-2.4-12-6.7-16.3l-89.3-89.4
											L386.7,420.9z"/>
										<path class="st0" d="M354.3,1.6C158.6,1.6,0,160.2,0,355.9c0,195.7,158.6,354.3,354.3,354.3c195.7,0,354.3-158.6,354.3-354.3
											C708.6,160.2,550,1.6,354.3,1.6z M524.7,461.5c8.6,8.6,13.5,20.3,13.4,32.5c0,12.3-4.8,23.8-13.5,32.5
											c-8.6,8.7-20.3,13.5-32.5,13.5c-12.3,0-23.8-4.8-32.5-13.5l-92.8-92.9l-78.4,85.8c-13.3,13.4-30.7,20.6-49.1,20.6
											c-18.3,0-35.9-7.2-48.8-20.2c-13-13-20.2-30.4-20.2-48.8c-0.1-18.3,7.2-35.9,20.2-48.8l111.5-102.2l-64.9-65
											c-13.2,6.4-28.9,3.7-39.2-6.7L173.8,224c0,0,0,0,0,0c-4.5-4.5-4.5-11.8,0-16.3l32.5-32.5c0,0,0,0,0,0c4.5-4.5,11.7-4.5,16.2,0
											l24.2,24.2c10.3,10.4,13,26.1,6.7,39.3l65.7,65.8l13.6-12.5c-0.9-5.6-1.4-11.2-1.4-16.6c0-57.1,46.3-103.5,103.4-103.6
											c9.6,0,19.2,1.4,28.4,4c1.9,0.5,3.7,1.6,5.1,3c4.5,4.5,4.4,11.8-0.1,16.2l-19.8,19.9c-12.9,13-12.9,34,0,46.9
											c12.5,12.6,34.3,12.5,46.8,0l19.8-19.9c1.4-1.4,3.1-2.4,5-3c6.1-1.7,12.4,1.9,14.1,8c2.7,9.4,4,18.9,4,28.4
											c0,54.3-42,99.4-96.1,103.3L524.7,461.5z"/>
									</g>
									</svg>
                        	</i>
                        	<span>整改</span>
                        </a>
                    </div>
                    <div class="divider hidden"></div>
                    <div class="attach-btn operateWorkFlow hidden row m-t-10">
                        <a href="javascript:;" class="attach-operateWorkFlow">
                        	<i class="fa">
                        		<?xml version="1.0" id="attach-operateWorkFlow" encoding="utf-8"?>
									<!-- Generator: Adobe Illustrator 22.0.1, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
									<svg version="1.1" id="attach-operateWorkFlow" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										 viewBox="0 0 708.7 708.7" style="enable-background:new 0 0 708.7 708.7;width:55px;" xml:space="preserve">
									<defs><style>.cls-1{fill:#00d1c3;}
									</style></defs><title>lishi</title>
									<g id="_2" data-name="2">
									<path class="cls-1" d="M354.33,0C158.64,0,0,158.64,0,354.33S158.64,708.66,354.33,708.66,708.66,550,708.66,354.33,550,0,354.33,0ZM314,511.68H219.84a27.76,27.76,0,0,1-28-27.53V209.5a27.75,27.75,0,0,1,27.75-27.75H462.29a27.76,27.76,0,0,1,28,27.53V313.41H466.51V205.29H215.63V488.14H314Zm100.8-237.34a13.33,13.33,0,0,1-14.52,12l-.13,0H264.25a13.55,13.55,0,0,1-14.88-12,13.54,13.54,0,0,1,14.88-11.77H400.13A13.31,13.31,0,0,1,414.78,274.34ZM302.88,436.19h-39.3c-8.88,0-16-5.33-16-11.77s7.1-12,16-12h39.3c8.88,0,16,5.33,16,12S311.76,436.19,302.88,436.19Zm14.65-72.6H264.91A13.54,13.54,0,0,1,250,351.82h-.67a13.55,13.55,0,0,1,14.88-12h53.28a12.18,12.18,0,1,1,0,23.76ZM544.66,531.88a11.12,11.12,0,0,1-2.22,2.22,18.08,18.08,0,0,1-8.21,2.44l.44.45A16.41,16.41,0,0,1,524,532.77l-48-47.73a90.14,90.14,0,0,1-118.78-6.89,88.79,88.79,0,0,1-26.65-63.27,90.37,90.37,0,0,1,153.64-63.06,86.8,86.8,0,0,1,26.65,63.06,107.21,107.21,0,0,1-17.1,53.06l48.62,48.4A11.09,11.09,0,0,1,544.66,531.88Z"/>
									<path class="cls-1" d="M420.33,349.38a65,65,0,0,0-46.4,18.87,63.72,63.72,0,0,0-18.87,46,64.59,64.59,0,0,0,18.87,46.18,67.93,67.93,0,0,0,46.4,18.87,64.94,64.94,0,1,0,0-129.88Z"/>
									</g></svg>
                        	</i>
                        	<span>操作记录</span>
                        </a>
                    </div>
                </div>
            </div>
            <!-- end attach-panel -->

            <!-- begin scroll to top btn -->
            <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
            <!-- end scroll to top btn -->
        </div>
        <!-- end page container -->

        <!-- begin #fixed-footbar-content -->
        <div id="fixed-footbar-content"></div>
        <!-- end #fixed-footbar-content -->
        
        <!-- begin #commonModal 模态框 -->
        <div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
            <div class="modal-dialog" role="document" id="modal-content">
                
            </div>
        </div>
        <!-- end #commonModal 模态框 -->

        <!-- ================== BEGIN BASE JS ================== -->
        <script type="text/javascript" src="/ReportServer?op=emb&resource=finereport.js&inter=zh_CN"></script>
        <script include-on-mobile="false" src="https://api.map.baidu.com/api?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
        <script include-on-mobile="false" src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <!--[if lt IE 9]>
        <script include-on-mobile="false" src="assets/crossbrowserjs/html5shiv.js"></script>
        <script include-on-mobile="false" src="assets/crossbrowserjs/respond.min.js"></script>
        <script include-on-mobile="false" src="assets/crossbrowserjs/excanvas.min.js"></script>
        <![endif]-->
        <script include-on-mobile="false" src="assets/plugins/jquery-hashchange/jquery.hashchange.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
        
        <script include-on-mobile="false" src="assets/plugins/DataTables/media/js/jquery.dataTables.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/DataTables/extensions/Buttons/js/buttons.html5.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script include-on-mobile="false" src="assets/plugins/parsley/dist/parsley.js" async></script>
        <script include-on-mobile="false" src="assets/plugins/jSignature/jSignature.min.js" async></script>
        <script include-on-mobile="false" src="assets/plugins/gritter/js/jquery.gritter.js" async></script>
        <script include-on-mobile="false" src="assets/plugins/jquery-qrcode/jquery.qrcode.min.js"></script>
        <script include-on-mobile="false" src="assets/plugins/bootstrap/js/bootstrap-select.min.js"></script>
        <!-- ================== END BASE JS ================== -->
        
        <!-- ================== BEGIN PAGE LEVEL JS ================== -->
        <script include-on-mobile="true" src="assets/js/apps.js"></script>
        <script include-on-mobile="true" src="js/ecloud.js?v=07"></script>
        <!-- ================== END PAGE LEVEL JS ================== -->
        <script>
        $(document).ready(function () {
            App.init();
            
            if(!/MSIE 9.0|MSIE 10.0|rv:11.0/i.test(navigator.userAgent) && !/Trident/i.test(navigator.userAgent) && window.applicationCache && window.applicationCache.status == window.applicationCache.UPDATEREADY){
                window.applicationCache.update();   
            }
            
            $.getScript('js/menu-control.js?v='+Math.random()).done(function () {               
             getMenu.init();
            });
            
          	//退出
    		$('#logout').click(function(){
    			var popoptions = {
    					title: '提示',
    					content: '您确定退出登录吗？',
    					accept: logoutFun
    		    };
    			$("body").cgetPopup(popoptions);
    		});
          	
    		var logoutFun=function(){
    			
    			var device = "";
    			if(_isMobile){
    				device = "1";
    			 }else if(_inApk){
    				 device = "2";
    			 }else{
    				 device = "3";
    			 }
    			 
    			$.ajax({
    		        type: 'POST',
    		        url: 'login/logout',
    		        data:"device="+device,
    		        dataType: 'text',
    		        success: function (data) {
    		        	if(data == '0000'){
    		        		var sysLocation = window.location;
    		        		window.location.href = sysLocation.protocol + "//" + sysLocation.host + "/login.html";
    		        	}else{
    		        		alertError("系统发生异常！");
    		        	}
    		        }
    		    });
    		}
        });

        setTimeout(getMessages, 5000);
        setTimeout(loginCheck, 5000);
        setTimeout(getWorkMessages, 5000);
        console.info('deptIds='+'${sessionScope.session_loginInfo.deptIds}');
        </script>
    </body>
</html>