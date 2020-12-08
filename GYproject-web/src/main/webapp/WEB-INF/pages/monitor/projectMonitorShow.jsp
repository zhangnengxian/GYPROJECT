<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
        <meta charset="utf-8" />
        <title>工程项目管理系统</title>
        <meta http-equiv="Content-Security-Policy" content="default-src 'self' http://cordova.apache.org data: gap: cdvfile: https://ssl.gstatic.com; child-src 'self' http://cordova.apache.org data: gap: blob: clob: cdvfile: https://ssl.gstatic.com; style-src 'self' 'unsafe-inline'; script-src 'self' *.baidu.com *.bdimg.com 'unsafe-eval' 'unsafe-inline' data:; img-src * data: blob:;connect-src * blob:; media-src *">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <meta content="" name="description" />
        <meta content="ferrinweb @ dfsoft.cc" name="author" />
		<link Rel="shortcut icon" href="./images/common/favicon.ico">
        <!-- ================== BEGIN BASE CSS STYLE ================== -->
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
    </head>
<body>
<div id='monitorShow' style='text-align:center;width:1024px;height:100%'>

</div>
</body>
</html>
 <!-- ================== BEGIN BASE JS ================== -->
        <script src="/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
        <script src="/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
        <script src="/assets/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <!--[if lt IE 9]>
        <script src="assets/crossbrowserjs/html5shiv.js"></script>
        <script src="assets/crossbrowserjs/respond.min.js"></script>
        <script src="assets/crossbrowserjs/excanvas.min.js"></script>
        <![endif]-->
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
        <script src="/assets/js/apps.js"></script>
        <script src="/js/ecloud.js"></script>
        <!-- ================== END PAGE LEVEL JS ================== -->
<script type='text/javascript'>
var timeTicket;
clearInterval(timeTicket);
timeTicket = setInterval(function (){
	$.ajax({
	    type: 'POST',
	    url: '/projectTotalStatistics/projectMonitorShow',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	getPage(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
},2000);

	function getPage(data)
	{
		console.info(data);
		if(data != '' && data != '0')
		{
			$("#monitorShow").cgetContent(data);
		}
		
	}
</script>