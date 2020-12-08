<!-- <%=request.getRequestURI()%> -->
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
		<link href="/assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet">
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
<div style='position:relative;float:left;top:30px'><span style='float:center'><font size='5'>&nbsp;&nbsp;&nbsp;工程量统计分析（区域、阶段）</font></span></div>
<div id="projAnalysis" style='width:1024px;height:500px;text-align:center;border:1;top:80px'></div>
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
        <script src="/assets/plugins/jque	ry-hashchange/jquery.hashchange.min.js"></script>
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
function parseReturn()
{
    var projAnalysis = echarts.init(document.getElementById('projAnalysis'));
    
    $.ajax({
        type: 'POST',
        url: '/projectTotalStatistics/projectStatisticOfAreaAndStatusShowPage',
        success: function (data) {
        	console.info(data);
        	var result = JSON.parse(data);
        	var series = result.series;
        	var legendData = result.legendData;
        	var yAxisData = result.yAxisData;
        	var option = {
            	   tooltip : {
            	        trigger: 'axis',
            	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            	        }
            	    },
            	    legend: {
            	        data: legendData,
            	        top:'top'
            	    },
            	    grid: {
            	    	x: 50,
                        y: 10,
                        y2: 30,
                        x2: 20,
            	        containLabel: true
            	    },
            	    xAxis:  {
            	        type: 'value' 
            	    },
            	    yAxis: {
            	        type: 'category',
            	        data: yAxisData
            	    },
            	    series: series
            };
        	projAnalysis.setOption(option);
        	
        	
        	

        		   /*tooltip : {
        		        trigger: 'axis',
        		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        		        }
        		    },
        		    legend: {
        		        data:legendData
        		    },
        		    grid: {
        		        left: '3%',
        		        right: '4%',
        		        bottom: '3%',
        		        containLabel: true
        		    },
        		    xAxis : [
        		        {
        		            type : 'value'
        		        }
        		    ],
        		    yAxis : [
        		        {
        		            type : 'category',
        		            axisTick : {show: false},
        		            data : yAxisData
        		        }
        		    ],
        		    series : 
        		    	
        		    	[
        		        {
        		            name:'立项',
        		            type:'bar',
        		            stack: '总量',
        		            barCategoryGap:'50%',
        		            barGap:'10%',
        		            label: {
        		                normal: {
        		                    show: false,
        		                    position: 'left'
        		                }
        		            },
        		            data:[5,4,4,0,7]
        		        },
        		        {
        		            name:'设计',
        		            type:'bar',
        		            stack: '总量',
        		            barCategoryGap:'50%',
        		            barGap:'10%',
        		            label: {
        		                normal: {
        		                    show: false,
        		                    position: 'left'
        		                }
        		            },
        		            data:[270,172,231,123,264]
        		        },
        		        {
        		            name:'预算',
        		            type:'bar',
        		            stack: '总量',
        		            barCategoryGap:'50%',
        		            barGap:'10%',
        		            label: {
        		                normal: {
        		                    show: false,
        		                    position: 'left'
        		                }
        		            },
        		            data:[0,2,2,2,2]
        		        }
        		        ,
        		        {
        		            name:'合同',
        		            type:'bar',
        		            stack: '总量',
        		            barCategoryGap:'50%',
        		            barGap:'10%',
        		            label: {
        		                normal: {
        		                    show: false,
        		                    position: 'left'
        		                }
        		            },
        		            data:[4,1,3,0,2]
        		        }
        		        ,
        		        {
        		            name:'计划',
        		            type:'bar',
        		            stack: '总量',
        		            barCategoryGap:'50%',
        		            barGap:'10%',
        		            label: {
        		                normal: {
        		                    show: false,
        		                    position: 'left'
        		                }
        		            },
        		            data:[0,1,2,1,2]
        		        }
        		        ,
        		        {
        		            name:'分包',
        		            type:'bar',
        		            stack: '总量',
        		            barCategoryGap:'50%',
        		            barGap:'10%',
        		            label: {
        		                normal: {
        		                    show: false,
        		                    position: 'left'
        		                }
        		            },
        		            data:[1,1,1,2,2]
        		        }*/
        		    //]
        		//};
    
        	//projAnalysis.setOption(option);
        }
    });
    
        	
}
    
parseReturn();

</script>