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
        <!-- link href="/assets/css/style.min.css" rel="stylesheet" />-->
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
<div style='text-align:center'>
<div id="safetyQuality" style='width:80%;height:600px;text-align:center'></div>
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

/* var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
        '7a', '8a', '9a','10a','11a',
        '12p', '1p', '2p', '3p', '4p', '5p',
        '6p', '7p', '8p', '9p', '10p', '11p']; */


	var hours = ['1', '2', '3', '4', '5', '6', '7','8', '9', '10','11','12'];
	var days = ['施工一处', '施工二处', '施工三处','施工四处', '施工五处'];

    var data = [[0,0,5],[0,1,1],[0,2,3],[0,3,2],[0,4,17],[0,5,5],[0,6,7],[0,7,16],[0,8,12],[0,9,0],[0,10,14],[0,11,2],
    [1,0,7],[1,1,0],[1,2,9],[1,3,10],[1,4,0],[1,5,11],[1,6,13],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],
    [2,0,1],[2,1,1],[2,2,12],[2,3,15],[2,4,12],[2,5,14],[2,6,18],[2,7,17],[2,8,15],[2,9,12],[2,10,3],[2,11,2],
    [3,0,7],[3,1,3],[3,2,10],[3,3,0],[3,4,17],[3,5,12],[3,6,10],[3,7,9],[3,8,1],[3,9,12],[3,10,5],[3,11,4],
    [4,0,1],[4,1,3],[4,2,0],[4,3,7],[4,4,10],[4,5,1],[4,6,0],[4,7,0],[4,8,10],[4,9,13],[4,10,4],[4,11,4]];
    

     option = {
         tooltip: {
             position: 'top'
         },
         title: [],
         singleAxis: [],
         series: []
     };

	echarts.util.each(days, function (day, idx) {
		option.title.push({
             textBaseline: 'middle',
             top: (idx + 0.5) * 100 / 7 + '%',
             text: day
     	});
        option.singleAxis.push({
            left: 100,
            type: 'category',
            boundaryGap: false,
            data: hours,
            top: (idx * 100 / 7 + 5) + '%',
            height: (100 / 7 - 10) + '%',
            axisLabel: {
                interval: 0
            }
        });
        option.series.push({
            singleAxisIndex: idx,
            coordinateSystem: 'singleAxis',
            type: 'scatter',
            data: [],
            symbolSize: function (dataItem) {
                return dataItem[1] * 3;
            }
        });
	});
		
        
	$.ajax({
	   	type:"post",
	   	url:"/projectTotalStatistics/safetyQualityStatistics",
	   	contentType:"application/json;charset=UTF-8",
	   	data:'',
	   	success:function(data){
	   		parseReturn(data);
	   	},
	   	error: function (jqXHR, textStatus, errorThrown) {
	    	console.warn("查询失败！");
	    }
	});
        
	function parseReturn(data){
		echarts.util.each(data, function (dataItem) {
           option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
		});
	}
      
	var safetyQuality = echarts.init(document.getElementById('safetyQuality'),'infographic');
	safetyQuality.setOption(option);
</script>