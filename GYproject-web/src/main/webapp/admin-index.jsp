<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>工程系统运行管理</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
		<link Rel="shortcut icon" href="./images/common/favicon.ico">
        <!-- ================== BEGIN BASE CSS STYLE ================== -->
        <!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">-->
        <link href="assets/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
        <link href="assets/css/animate.min.css" rel="stylesheet" />
        <link href="assets/css/style.min.css" rel="stylesheet" />
        <link href="assets/css/style-responsive.min.css" rel="stylesheet" />
        <link href="assets/plugins/ionicons/css/ionicons.min.css" rel="stylesheet" />
        <!--<link href="assets/css/theme/blue.css" rel="stylesheet" id="theme" />-->

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
    </head>
    <body class="adminPanel">
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
                            <!-- <img src="images/common/logo.png" /> -->
                          <img src="images/common/logo-white.png" >
                            <!--<span class="navbar-logo"></span>--></a>
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
<!--                     <div id="top-menu" class="top-menu"></div> -->

                    <!-- begin header navigation right -->
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown navbar-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="assets/img/user-13.jpg" class="hidden" alt="" /> 
                                <span class="hidden-xs">${sessionScope.session_loginInfo.staffName}</span> <b class="caret"></b>
                            </a>
                            <ul class="admin-menu dropdown-menu animated fadeInRight">
                                <li class="arrow"></li>
                                <li><a href="index.jsp">返回前台</a></li>
                                <!-- <li><a href="javascript:;"><span class="badge badge-danger pull-right">2</span> 系统通知</a></li> 
                                <li class="divider"></li>-->
                                <li><div id="qrcodebox" title="扫一扫直接在手机访问"></div><p class="qrcode-desc">扫一扫用手机访问</p></li>
                                <li class="divider"></li>
                                <li><a id="logout" href="javascript:;">退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- end header navigation right -->
                </div>
                <!-- end container-fluid -->
            </div>
            <!-- end #header -->
<!--             <div class="toggleBtn2"><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></div> -->
            <!-- begin #sidebar -->
            <div id="sidebar" class="sidebar">
                <!-- begin sidebar scrollbar -->
                <div data-scrollbar="true" data-height="100%">
                    <!-- begin sidebar user -->
                    <ul class="nav profilebox">
                        <!-- begin sidebar minify button -->
<!--                         <li class="toggleBtn"><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li> -->
                        <!-- end sidebar minify button -->
                        <li class="nav-profile">
                            <div class="image">
                                <a href="javascript:;"><img src="assets/img/user-13.jpg" alt="" /></a>
                            </div>
                            <div class="info">
								${sessionScope.session_loginInfo.staffName}
                                <small>系统管理员</small>
                            </div>
                        </li>
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

            <!-- begin scroll to top btn -->
            <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
            <!-- end scroll to top btn -->
        </div>
        <!-- end page container -->

        <!-- begin #fixed-footbar-content -->
        <div id="fixed-footbar-content"></div>
        <!-- end #fixed-footbar-content -->
        
        <!-- begin #commonModal 模态框 -->
        <div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" id="modal-content">
                
            </div>
        </div>
        <!-- end #commonModal 模态框 -->

        <!-- ================== BEGIN BASE JS ================== -->        
        <script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
        <script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
        <script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <!--[if lt IE 9]>
        <script src="assets/crossbrowserjs/html5shiv.js"></script>
        <script src="assets/crossbrowserjs/respond.min.js"></script>
        <script src="assets/crossbrowserjs/excanvas.min.js"></script>
        <![endif]-->
        <script src="assets/plugins/jquery-hashchange/jquery.hashchange.min.js"></script>
        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
        
        <script src="assets/plugins/DataTables/media/js/jquery.dataTables.min.js"></script>
        <script src="assets/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
        <script src="assets/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
        <script src="assets/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js"></script>
        <script src="assets/plugins/DataTables/extensions/Buttons/js/buttons.html5.min.js"></script>
        <script src="assets/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
        <script src="assets/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>
        <script src="assets/plugins/jquery-deserialize-master/src/jquery.deserialize.min.js"></script>
        <script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script src="assets/plugins/parsley/dist/parsley.js"></script>
        <script src="assets/plugins/jSignature/jSignature.min.js"></script>
        <script src="assets/plugins/gritter/js/jquery.gritter.js"></script>
        <script src="assets/plugins/jquery-qrcode/jquery.qrcode.min.js"></script>
        <script src="https://api.map.baidu.com/getscript?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
        <!-- ================== END BASE JS ================== -->
        
        <!-- ================== BEGIN PAGE LEVEL JS ================== -->
        <script src="assets/js/apps.js"></script>
        <script src="js/ecloud.js?v=1002"></script>
        <!-- ================== END PAGE LEVEL JS ================== -->
        <script>
            $(document).ready(function () {
                App.init();
                $.getScript('js/menu-control2.js?v='+Math.random()).done(function () {
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
        			$.ajax({
        		        type: 'POST',
        		        url: 'login/staffLogout',
        		        dataType: 'text',
        		        success: function (data) {
        		        	if(data == '0000'){
        		        		var sysLocation = window.location;
        		        		window.location.href = sysLocation.protocol + "//" + sysLocation.host + "/admin-login.html";
        		        	}else{
        		        		alertError("系统发生异常！");
        		        	}
        		        }
        		    });
        		}
            });
        </script>
    </body>
</html>