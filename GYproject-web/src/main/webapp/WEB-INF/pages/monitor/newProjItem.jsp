<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<link rel="apple-touch-icon" href="apple-touch-icon.png">
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
<link href="/assets/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<!-- Place favicon.ico in the root directory -->
    <style type="text/css">
        body {
            margin: 0;
        }

        .lean-grid {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
        }

        .lean-grid > ul {
            position: relative;
            margin: 0;
            padding: 0;
            list-style: none;
            width: 1061px;
            height: 167%;
            overflow: hidden;
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#03618b+0,14ffff+100 */
            background: #03618b; /* Old browsers */
            background: -moz-linear-gradient(45deg, #03618b 0%, #14ffff 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(45deg, #03618b 0%, #14ffff 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(45deg, #03618b 0%, #14ffff 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#03618b', endColorstr='#14ffff', GradientType=1); /* IE6-9 fallback on horizontal gradient */
            z-index: 2;
            -webkit-transform-origin: 0 0;
            -moz-transform-origin: 0 0;
            -ms-transform-origin: 0 0;
            transform-origin: 0 0;
        }

        .lean-grid > ul > li {
            position: relative;
            float: left;
            width: 250px;
            height: 250px;
            margin: 51.7777px;
            padding: 0;
            overflow: visible;
            -webkit-transform-origin: 50% 50%;
            -moz-transform-origin: 50% 50%;
            -ms-transform-origin: 50% 50%;
            transform-origin: 50% 50%;
            transform: rotate(-45deg);
            -ms-transform: rotate(-45deg); /* IE 9 */
            -moz-transform: rotate(-45deg); /* Firefox */
            -webkit-transform: rotate(-45deg); /* Safari 和 Chrome */
        }

        .lean-grid > ul > li:before {
            content: " ";
            position: absolute;
            top: 40px;
            left: 0px;
            width: 50px;
            height: 180px;
            border-radius: 25px;
            box-shadow: rgb(0, 0, 0) 0 0 50px 18px;
            z-index: 1;

        }

        .lean-grid .grid-bg {
            position: relative;
            width: 100%;
            height: 100%;
            padding: 30px;
            box-sizing: border-box;
            background: #0dffff url("../images/monitor/grid-bg.png") no-repeat left top;
            z-index: 2;
        }

        .lean-grid .grid-content {
            width: 100%;
            height: 100%;
            overflow: hidden;
            /*background: rgba(255,255,255,.4);*/
            transform: rotate3d(1, 1, 0, 40deg);
            -ms-transform: rotate3d(1, 1, 0, 40deg);
            -moz-transform: rotate3d(1, 1, 0, 40deg);
            -webkit-transform: rotate3d(1, 1, 0, 40deg);
        }

        .clear-left {
            clear: left;
        }

        .lean-grid .chart-box {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            box-sizing: border-box;
            padding-left: 1061px;
            background-color: #012841;
            z-index: 1;
        }
        p{
	        font-size: 16px; 
	        line-height: 15px;
	        font-family: "黑体";
        }
        .chart-box{
        	position:relative;
        	color: #f2f2f2;
        }
        .chart-box.toggled{
			padding-left: 0;
			z-index: 3
		}
        .chart-box table.dataTable,
        .chart-box table.dataTable tbody tr,
        .chart-box table.dataTable tbody td{
        	background: transparent !important;
        	color: #b2b2b2;
        	border-color: #999;
        }
        .chart-box table.dataTable tbody tr.selected,
        .chart-box table.dataTable tbody tr.selected td{
        	background:#4c6f86 !important;
        	color: #a9a9a9;
        }
        .echartsBoxTop{
	        position:absolute;
			top:20px;
			left:680px;
			width:640px;
	        height:280px;
        }
        
        .echartsBoxFirst{
        	position:absolute;
			top:40px;
			left:670px;
			width:640px;
	        height:560px;
        }
        .echartsBox{
	        position:absolute;
	        top:320px;
	        left:680px;
	        width:640px;
	        height:300px;
        }
        
        .close-btn{
        	width: 45px;
        	position: absolute;
        	top: 0;
        	bottom: 0;
        	left: 0;
        	display: none;
        	z-index: 4;
    		background: #276d9a;
    		font-size: 24px;
    		text-align: center;
    		line-height: 100%;
        }
        
        .close-btn i{
        	color: #fff;
        	position: absolute;
        	width: 10px;
        	height: 24px;
        	top: 0;
        	bottom: 0;
        	left: 0;
        	right: 0;
        	margin: auto;    
        }
        
        .chart-box.toggled + .close-btn{
        	display: block;
        }
        
    </style>
</head>
<body>
<div class="lean-grid">
    <ul class="grid">
        <li class="grid-item" id="firstLi">
            <div class="grid-bg">
                <div class="grid-content first" >
                	<img src="../images/monitor/list.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">去年工程项目150个</p>
				    <p>收入15200000元</p>
				    <p>今年新增工程项目220个</p>
				    <p>收入22300300元</p>
                </div>
            </div>
        </li>
        <li class="grid-item" id="secondLi">
            <div class="grid-bg">
                <div class="grid-content second">
                	<image src="../images/monitor/statistics.png" style="margin-top: -5px;"/>
                	<p style="margin-top: 10px;">居民户350个</p>
				    <p>工商户550个</p>
				    <!-- <table>
				    	<tr>
				    		<th>居民户</th>
				    		<th>350个</th>
				    	</tr>
				    	<tr>
				    		<th>工商户</th>
				    		<th>550个</th>
				    	</tr>
				    </table> -->
				    
				    
                </div>
            </div>
        </li>
        <li class="grid-item" id="thirdLi">
            <div class="grid-bg" >
                <div class="grid-content third">
                	<image src="../images/monitor/distribute.png" style="margin-top: -10px;width:84.72px;height:74px"/>
                	<p>天山区:50个</p>
				    <p>沙依巴克区：30个</p>
				    <p>新市区：45个</p>
				    <p>水区：30个</p>
				    <p>头屯河区：50个</p>
                </div>
            </div>
        </li>
        <li class="grid-item clear-left" id="fourthLi">
            <div class="grid-bg">
                <div class="grid-content fourth">
                	<image src="../images/monitor/sandGlass.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">立项:150个工程项目</p>
				    <p>在建:200个工程项目</p>
				    <p>结单:180个工程项目</p>
				    <p>退单:10个工程项目</p>
                </div>
            </div>
        </li>
        <li class="grid-item" id="fifthLi">
            <div class="grid-bg" >
                <div class="grid-content fifth">
                	<image src="../images/monitor/contract.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">申请:350个工程项目</p>
				    <p>签约:120个工程项目</p>
                </div>
            </div>
        </li>
        <li class="grid-item clear-left" id="sixthLi">
            <div class="grid-bg">
                <div class="grid-content sixth">
                	<image src="../images/monitor/amount.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">结款:5023020元</p>
				    <p>未结:1205022元</p>
                </div>
            </div>
        </li>
    </ul>
    <div class="chart-box">
    	<div id="echartsBoxFirst" class="echartsBoxFirst">
    		<input type="hidden" value="" id="echartsBoxFirstInput"/>
		</div>
    	<div id="echartsBoxTop" class="echartsBoxTop" >
    		<input type="hidden" value="" id="echartsBoxFirstInput"/>
		</div>
		<div class="echartsBox" id="echartsBox" >
		</div>
    </div>
	<a href="javascript:;" class="close-btn"><i class="fa fa-angle-right"></i></a>
</div>
<!-- begin #commonModal 模态框 -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" id="modal-content">
        
    </div>
</div>
<script src="/assets/plugins/echarts/echarts.min.js"></script>
<script src="/assets/plugins/echarts/shine.js"></script>
<script src="/assets/plugins/echarts/infographic.js"></script>
<script src="/assets/plugins/echarts/macarons.js"></script>
<script src="/assets/plugins/echarts/roma.js"></script>
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
<script src="/js/ecloud.js"></script>
<script>
	/* $(function () {
		var option = {
    			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f'],
    			title: {
    		        text: '结转新增统计',
    		        left: 'center',
    		        top: '10',
    		        textStyle: {
    		            color: '#fff',
    		            fontSize:20,
    		            fontFamily: '黑体'
    		        }
    		    },
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: 'left',
    		        top:'15%',
    		        itemGap:5,
    		        data:['去年结转','今年新增'],
    		        textStyle: {
    		            color: '#fff',
    		            fontSize:12,
    		            fontFamily: '黑体'
    		        }
    		    },
    		    series: [
    		        {
    		            name:'工程信息',
    		            type:'pie',
    		            selectedMode: 'single',
    		            radius: [0, '30%'],

    		            label: {
    		                normal: {
    		                    position: 'inner'
    		                }
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:[
    		                {value:15200000, name:'去年收入', selected:true},
    		                {value:22300300, name:'今年收入'}
    		              
    		            ]
    		        },
    		        {
    		            name:'工程信息',
    		            type:'pie',
    		            radius: ['44%', '59%'],

    		            data:[
    		                {value:150, name:'去年结转'},
    		                {value:220, name:'今年新增'}
    		            ]
    		        }
    		    ]
    		};
    	var areaAmount = echarts.init(document.getElementById('echartsBox'),'shine');
    	areaAmount.setOption(option);
    	
    	$.ajax({
            type: 'POST',
            url: '/projectStatisticalAnalysis/backReasonProjectNum',
            success: function (data) {
            	console.log(data);
            	var result = JSON.parse(data);
            	var seriesData = result.seriesData;
            	var legendData = result.legendData;
            	var option = {
            			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','#4f8699'],
            		    title : {
                		        text: '退单原因统计',
                		        left: 'center',
                		        top: '10',
                		        textStyle: {
                		            color: '#fff',
                		            fontSize:20,
                		            fontFamily: '黑体'
                		        }
            		    },
            		    tooltip : {
            		        trigger: 'item',
            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
            		    },
            		    legend: {
            		        orient : 'vertical',
            		        x : 'left',
            		        top:'15%',
            		        itemGap:5,
            		        data:legendData,
            		        textStyle: {
            		            color: '#fff',
            		            fontSize:12,
            		            fontFamily: '黑体'
            		        }
            		    },
            		    toolbox: {
            		        show : false,
            		        feature : {
            		            mark : {show: true},
            		            dataView : {show: true, readOnly: false},
            		            magicType : {
            		                show: true, 
            		                type: ['pie', 'bar'],
            		                option: {
            		                    funnel: {
            		                        x: '25%',
            		                        width: '50%',
            		                        funnelAlign: 'left',
            		                        max: 1548
            		                    }
            		                }
            		            },
            		            restore : {show: true},
            		            saveAsImage : {show: true}
            		        }
            		    },
            		    calculable : true,
            		    series : [
            		        {
            		            name:'退单统计',
            		            type:'pie',
            		            radius : '55%',
            		            center: ['50%', '60%'],
            		            data:seriesData
            		        }
            		    ]
            		};
               
            	var deductionPerMonth = echarts.init(document.getElementById('echartsBoxTop'),'shine');
            	deductionPerMonth.setOption(option);
            }
        });	
	}); */



    function gridScale() {
        var height = $(window).innerHeight(),
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
        });
    }
    gridScale();

    var timer,
        delay = function () {
            clearTimeout(timer);
            timer = setTimeout(gridScale, 50);
        };

    window.onresize = delay;
    
    
    
    $('.first').off().on('click',function(){
    	
    	var option = {
    			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f'],
    			title: {
    		        text: '结转新增统计',
    		        left: 'center',
    		        top: '10',
    		        textStyle: {
    		            color: '#fff',
    		            fontSize:20,
    		            fontFamily: '黑体'
    		        }
    		    },
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: 'left',
    		        top:'15%',
    		        itemGap:5,
    		        data:['去年结转','今年新增'],
    		        textStyle: {
    		            color: '#fff',
    		            fontSize:12,
    		            fontFamily: '黑体'
    		        }
    		    },
    		    series: [
    		        {
    		            name:'工程信息',
    		            type:'pie',
    		            selectedMode: 'single',
    		            radius: [0, '30%'],

    		            label: {
    		                normal: {
    		                    position: 'inner'
    		                }
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:[
    		                {value:15200000, name:'去年收入', selected:true},
    		                {value:22300300, name:'今年收入'}
    		              
    		            ]
    		        },
    		        {
    		            name:'工程信息',
    		            type:'pie',
    		            radius: ['44%', '59%'],

    		            data:[
    		                {value:150, name:'去年结转'},
    		                {value:220, name:'今年新增'}
    		            ]
    		        }
    		    ]
    		};
    	
    	var areaAmount = echarts.init(document.getElementById('echartsBox'),'shine');
    	areaAmount.setOption(option);
    	deduction();
    	
    })
    
    var deduction=function(){
    $(".echartsBoxTop").removeClass('hidden');
    $(".echartsBox").removeClass('hidden');
    $(".echartsBoxFirst").addClass('hidden');
    $.ajax({
            type: 'POST',
            url: '/projectStatisticalAnalysis/backReasonProjectNum',
            success: function (data) {
            	console.log(data);
            	var result = JSON.parse(data);
            	var seriesData = result.seriesData;
            	var legendData = result.legendData;
            	var option = {
            			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f','#4f8699'],
            		    title : {
                		        text: '退单原因统计',
                		        left: 'center',
                		        top: '10',
                		        textStyle: {
                		            color: '#fff',
                		            fontSize:20,
                		            fontFamily: '黑体'
                		        }
            		    },
            		    tooltip : {
            		        trigger: 'item',
            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
            		    },
            		    legend: {
            		        orient : 'vertical',
            		        x : 'left',
            		        top:'15%',
            		        itemGap:5,
            		        data:legendData,
            		        textStyle: {
            		            color: '#fff',
            		            fontSize:12,
            		            fontFamily: '黑体'
            		        }
            		    },
            		    toolbox: {
            		        show : false,
            		        feature : {
            		            mark : {show: true},
            		            dataView : {show: true, readOnly: false},
            		            magicType : {
            		                show: true, 
            		                type: ['pie', 'bar'],
            		                option: {
            		                    funnel: {
            		                        x: '25%',
            		                        width: '50%',
            		                        funnelAlign: 'left',
            		                        max: 1548
            		                    }
            		                }
            		            },
            		            restore : {show: true},
            		            saveAsImage : {show: true}
            		        }
            		    },
            		    calculable : true,
            		    series : [
            		        {
            		            name:'退单统计',
            		            type:'pie',
            		            radius : '55%',
            		            center: ['50%', '60%'],
            		            data:seriesData
            		        }
            		    ]
            		};
               
            	var deductionPerMonth = echarts.init(document.getElementById('echartsBoxTop'),'shine');
            	deductionPerMonth.setOption(option);
            }
        });
    };
    
    
    
    
    
    $('.second').off().on('click',function(){
    	$(".echartsBoxTop").addClass('hidden');
    	$(".echartsBox").removeClass('hidden');
    	$(".echartsBoxFirst").addClass('hidden');
    	$.ajax({
    	    type: 'POST',
    	    url: '/projectTotalStatistics/projectSankey',
    	    contentType: "application/json;charset=UTF-8",
    	    data: '',
    	    success: function (data) {
    	    	parseReturnSankey(data);
    	    },
    	    error: function (jqXHR, textStatus, errorThrown) {
    	        console.warn("ajax queryScaleDetail...fail");
    	    }
    	});
    })
    function parseReturnSankey(data)
    {
    	var vJsonData = JSON.parse(data);
    	console.info(data);
        option = {
        		//color: ["#c23531","#2f4554","#61a0a8","#d48265","#91c7ae","#749f83","#ca8622","#bda29a","#6e7074","#546570","#c4ccd3"],
    	        tooltip: {
    	            trigger: 'item',
    	            triggerOn: 'mousemove'

    	        },
    	        series: [
    	            {
    	                type: 'sankey',
    	                layout:'none',
    	                data: vJsonData.nodes,
    	                links: vJsonData.links,
    	                itemStyle: {
    	                    normal: {
    	                        borderWidth: 1,
    	                        borderColor: '#347185'
    	                    }
    	                },
    	                lineStyle: {
    	                    normal: {
    	                    	color: 'source',
    	                        curveness: 0.2 
    	                    }
    	                },
    	                label: {
     		                normal: {
     		                    textStyle: {
     		                        color: '#fff'
     		                    }
     		                }
     		            },
    	            }
    	        ]
    	    }
    	
    	var projSankey = echarts.init(document.getElementById('echartsBox'),'shine');
    	
        projSankey.setOption(option);
    }
    $('.third').off().on('click',function(){
    	$(".echartsBoxTop").addClass('hidden');
    	$(".echartsBox").removeClass('hidden');
    	$(".echartsBoxFirst").addClass('hidden');
    	$.ajax({
    	    type: 'POST',
    	    url: '/projectTotalStatistics/projectTypeStatistics',
    	    contentType: "application/json;charset=UTF-8",
    	    data: '',
    	    success: function (data) {
    	    	parseReturnTotal(data);
    	    },
    	    error: function (jqXHR, textStatus, errorThrown) {
    	        console.warn("ajax queryScaleDetail...fail");
    	    }
    	});
    });
    
    
    function parseReturnTotal(data)
    {
    	var vJsonData = JSON.parse(data);
    	
    	option = {
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: 'left',
    		        data:vJsonData.title,
    		        right:'10px',
    		        top:'15%',
    		        itemGap:5,
    		        textStyle: {
    		            color: '#fff',
    		            fontSize:12,
    		            fontFamily: '黑体'
    		        }
    		    },
    		    series: [
    		        {
    		            name:'区域',
    		            type:'pie',
    		            selectedMode: 'single',
    		            radius: [0, '30%'],

    		            label: {
    		                normal: {
    		                    position: 'inner'
    		                }
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:vJsonData.area
    		        },
    		        {
    		            name:'工程规模',
    		            type:'pie',
    		            radius: ['35%', '55%'],
    		            label: {
    		                normal: {
    		                	show: true
    		                }
    		            },
    		            data:vJsonData.scale
    		        }
    		    ]
    		};
    	
    	var projTotal = echarts.init(document.getElementById('echartsBox'),'macarons');
    	projTotal.setOption(option);
    };
    $('.fourth').off().on('click',function(){
    	$(".echartsBoxFirst").removeClass('hidden');
    	$(".echartsBoxTop").addClass('hidden');
    	$(".echartsBox").addClass('hidden');
    	$("#echartsBoxFirst").cgetContent("/projTS/viewStageStatistics");
    })
    $('.fifth').off().on('click',function(){
    	$(".echartsBoxTop").addClass('hidden');
    	$(".echartsBox").removeClass('hidden');
    	$(".echartsBoxFirst").addClass('hidden');
    	        option = {
    	            color: ['#1a71c5', '#00ffb5'],
    	            tooltip: {
    	                trigger: 'axis',
    	                axisPointer: { // 坐标轴指示器，坐标轴触发有效
    	                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    	                }
    	            },
    	            legend: {
    	                data: ['立项', '签约'],
    		            textStyle: {
    			            color: '#fff',
    			            fontSize:18,
    			            fontFamily: '黑体'
    			        },
    	                top: '10'
    	            },
    	            grid: {
    	                left: '3%',
    	                right: '4%',
    	                bottom: '11%',
    	                containLabel: true
    	            },
    	            calculable: true,
    	            xAxis: [{
    	                type: 'category',
    	                data: [{value:'一季度',textStyle: {
    		    		            color: '#fff',
    		    		            fontSize:16
    		    		        }},{value:'二季度',textStyle: {
    		    		            color: '#fff',
    		    		            fontSize:16
    		    		        }},{value:'三季度',textStyle: {
    		    		            color: '#fff',
    		    		            fontSize:16
    		    		        }},{value:'四季度',textStyle: {
    		    		            color: '#fff',
    		    		            fontSize:16
    		    		        }},],
    		    		 axisLine: {
    	                        lineStyle: {
    	                            color: '#fff'
    	                        }
    	                    }
    	            }],
    	            yAxis: [{
    	                type: 'value',
    	                name: '',
    	                nameLocation: 'middle',
    	                nameGap:25,
    	                nameTextStyle: {
    	                   // fontWeight: 'bold',
    	                    fontSize: '24',
    	                    color:'#000',
    	                },
    	                axisLine: {
    	                        lineStyle: {
    	                            color: '#fff'
    	                        }
    	                    }
    	            }],
    	            dataZoom: [{
    	                textStyle: {
    	                    color: '#8392A5'
    	                },
    	                handleSize: '80%',
    	                dataBackground: {
    	                    areaStyle: {
    	                        color: '#00ffb5'
    	                    },
    	                    lineStyle: {
    	                        opacity: 0.8,
    	                        color: '#8392A5'
    	                    }
    	                },
    	                handleStyle: {
    	                    color: '#fff',
    	                    shadowBlur: 3,
    	                    shadowColor: 'rgba(0, 0, 0, 0.6)',
    	                    shadowOffsetX: 2,
    	                    shadowOffsetY: 2
    	                }
    	            }, {
    	                type: 'inside'
    	            }],
    	            series: [{
    	                name: '立项',
    	                type: 'bar',
    	                data: [26, 38, 19, 40],
    	                markPoint: {
    	                    data: [{
    	                        type: 'max',
    	                        name: '最大值'
    	                    }, {
    	                        type: 'min',
    	                        name: '最小值'
    	                    }]
    	                },
    	            }, {
    	                name: '签约',
    	                type: 'bar',
    	                data: [ 54, 47, 18,2],
    	                markPoint: {
    	                    data: [{
    	                        type: 'max',
    	                        name: '最大值'
    	                    }, {
    	                        type: 'min',
    	                        name: '最小值'
    	                    }]
    	                },
    	            }]
    	        }

   		var areaAmount = echarts.init(document.getElementById('echartsBox'),'shine');
       	areaAmount.setOption(option);
    });
    $('.sixth').off().on('click',function(){
    	$(".echartsBoxTop").addClass('hidden');
    	$(".echartsBox").removeClass('hidden');
    	$(".echartsBoxFirst").addClass('hidden');
    	$.ajax({
    	    type: 'POST',
    	    url: '/projectTotalStatistics/projectPaymenetDetail',
    	    contentType: "application/json;charset=UTF-8",
    	    data: '',
    	    success: function (data) {
    	    	parseReturnPayment(data);
    	    },
    	    error: function (jqXHR, textStatus, errorThrown) {
    	        console.warn("ajax queryScaleDetail...fail");
    	    }
    	});	
    })
    function parseReturnPayment(data)
    {
    	var vJsonData = JSON.parse(data);
    	console.info(data);
    	option = {
    			title: {
    	            text: ''
    	            
    	        },
    		    tooltip : {
    		        trigger: 'axis'
    		    },
    		    toolbox: {
    		        show : false,
    		        feature : {
    		            mark : {show: true},
    		            dataView : {show: true, readOnly: false},
    		            magicType: {show: true, type: ['line', 'bar']},
    		            restore : {show: true},
    		            saveAsImage : {show: true}
    		        }
    		    },
    		    calculable : true,
    		    legend: {
    		    	y:'top',
    		        data:['实收金额','实付金额','累计实收金额','累计实付金额'],
    		        textStyle: {
			            color: '#fff',
			            fontSize:12,
			            fontFamily: '黑体'
			        }
    		    },
    		    xAxis : [
    		        {
    		            type : 'category',
    		            data : [{value:'1月',textStyle: {
	    		            color: '#fff',
	    		            fontSize:12
	    		        }},'2月',{value:'3月',textStyle: {
	    		            color: '#fff',
	    		            fontSize:12
	    		        }},'4月',{value:'5月',textStyle: {
	    		            color: '#fff',
	    		            fontSize:12
	    		        }},'6月',{value:'7月',textStyle: {
	    		            color: '#fff',
	    		            fontSize:12
	    		        }},'8月',{value:'9月',textStyle: {
	    		            color: '#fff',
	    		            fontSize:12
	    		        }},'10月',{value:'11月',textStyle: {
	    		            color: '#fff',
	    		            fontSize:12
	    		        }},'12月'],
	    		        axisLine: {
	                        lineStyle: {
	                            color: '#fff'
	                        }
	                    }
    		        }
    		    ],
    		    yAxis : [
    		        {
    		            type : 'value',
    		            name : '收付金额',
    		            axisLabel : {
    		                formatter: '{value}',
	                        textStyle:{
	                        	fontSize:12
	                        }
    		            },
    		            axisLine: {
	                        lineStyle: {
	                            color: '#fff',
	                        }
	                    }
    		        },
    		        {
    		            type : 'value',
    		            name : '累计金额',
    		            axisLabel : {
    		                formatter: '{value}'
    		            },
    		            axisLine: {
	                        lineStyle: {
	                            color: '#fff'
	                        }
	                    }
    		        }
    		    ],
    		    series : [

    		        {
    		            name:'实收金额',
    		            type:'bar',
    		            data:vJsonData.receive
    		        },
    		        {
    		            name:'实付金额',
    		            type:'bar',
    		            data:vJsonData.payement
    		        },
    		        {
    		            name:'累计实收金额',
    		            type:'line',
    		            yAxisIndex: 1,
    		            data:vJsonData.receiveSum,
    		            
    		            lineStyle: {
    		                normal: {
    		                    type: 'solid'
    		                }
    		            }
    		        },
    		        {
    		            name:'累计实付金额',
    		            type:'line',
    		            yAxisIndex: 1,
    		            data:vJsonData.payementSum,
    		            
    		            lineStyle: {
    		                normal: {
    		                    type: 'dashed'
    		                }
    		            }
    		        }
    		    ]
    		};
    	
    	var projPayment = echarts.init(document.getElementById('echartsBox'));
        projPayment.setOption(option);
    }
function drawMap() {


    $("#chart-box").css({opacity: 0});

    // 百度地图API功能
    var map = new BMap.Map("chart-box");
    map.centerAndZoom("乌鲁木齐", 11);
    map.enableScrollWheelZoom();


    var b = new BMap.Bounds(new BMap.Point(87.365177, 43.969272), new BMap.Point(87.834308, 43.581793));
    try {
        BMapLib.AreaRestriction.setBounds(map, b);
    } catch (e) {
        alert(e);
    }

    map.setMapStyle({
        styleJson: [
            {
                "featureType": "land",
                "elementType": "geometry",
                "stylers"    : {
                    "color": "#e7f7fc"
                }
            },
            {
                "featureType": "water",
                "elementType": "all",
                "stylers"    : {
                    "color": "#96b5d6"
                }
            },
            {
                "featureType": "green",
                "elementType": "all",
                "stylers"    : {
                    "color": "#b0d3dd"
                }
            },
            {
                "featureType": "highway",
                "elementType": "geometry.fill",
                "stylers"    : {
                    "color": "#a6cfcf"
                }
            },
            {
                "featureType": "highway",
                "elementType": "geometry.stroke",
                "stylers"    : {
                    "color": "#7dabb3"
                }
            },
            {
                "featureType": "arterial",
                "elementType": "geometry.fill",
                "stylers"    : {
                    "color": "#e7f7fc"
                }
            },
            {
                "featureType": "arterial",
                "elementType": "geometry.stroke",
                "stylers"    : {
                    "color": "#b0d5d4"
                }
            },
            {
                "featureType": "local",
                "elementType": "labels.text.fill",
                "stylers"    : {
                    "color": "#7a959a"
                }
            },
            {
                "featureType": "local",
                "elementType": "labels.text.stroke",
                "stylers"    : {
                    "color": "#d6e4e5"
                }
            },
            {
                "featureType": "arterial",
                "elementType": "labels.text.fill",
                "stylers"    : {
                    "color": "#374a46"
                }
            },
            {
                "featureType": "highway",
                "elementType": "labels.text.fill",
                "stylers"    : {
                    "color": "#374a46"
                }
            },
            {
                "featureType": "highway",
                "elementType": "labels.text.stroke",
                "stylers"    : {
                    "color": "#e9eeed"
                }
            }
        ]
    });

    function renderPoints(){
        //工程位置绘制
        console.info("绘制坐标点");
        
        var post = {};
    	post.finishedDateStart = new Date().getFullYear() + '-01-01';
    	post = JSON.stringify(post);
    	//console.info(post);
    	$.ajax({
            type: 'POST',
            url:"/projectMap/queryProjectSign",
            data: post,
            dataType: 'json',
            contentType: "application/json;charset=UTF-8",
            success: function(data) {
            	handleMap(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	printXHRError("projectQuery", "项目列表加载失败, 请重试", jqXHR, textStatus, errorThrown);
            }
    	});
    }
    function addPosMarker(map, point, text, style, content, dbContent ,projLtype){
    	
    	"use strict";
    	if(!point){
    		console.info("绘制Label标签却没有指定坐标点");
    		return false;
    	}
    	
    	style = style || {};
    	
    	text = text || '记录点';
    	
    	content = content || {};
    	
    	var getWidth = function(text){
            var text = text.replace("（","(").replace("）",")"),
            tarr = text.split("");
            return 14 * (text.indexOf("(") > -1 ? tarr.length - 1 : tarr.length);
        },
        cacuWidth = getWidth(text),
        offsetX = style.offsetX || 0 - cacuWidth / 2 + 3,
    	offsetY = style.offsetY || -24,
    	width = style.width || cacuWidth + 5 + "px",
    	height = style.height || '21px',
    	lineHeight = style.lineHeight || '16px',
    	color = style.color || 'red',
    	fontSize = style.fontSize || '14px',
    	padding = style.padding || '2px',
    	border = style.border || 'red 1px solid';/* ,
    	icon = style.icon || ''; */
        var myIcon;
    	var myIcon1 =  new BMap.Icon("../images/map/blue.png" , new BMap.Size(19,25)) ;
    	var myIcon2 =  new BMap.Icon("../images/map/green.png", new BMap.Size(19,25));
    	var myIcon3 =  new BMap.Icon("../images/map/yellow.png", new BMap.Size(19,25)) ;
    	//console.info('dbContentprojLtype...'+projLtype.projLtypeId);
    	var typeId = projLtype.projLtypeId.split(",");
    	for(var i=0;i<typeId.length;i++){
    		if(typeId[i] == "11"){
    			myIcon=myIcon1;
    			break;
        	}else if(typeId[i] == "12"){
        		myIcon=myIcon2;
    			break;
        	}else if(typeId[i] == "13"){
        		myIcon=myIcon2;
    			break;
        	}
    	}
    	
    	var marker = myIcon ? new BMap.Marker(point, {icon: myIcon}) : new BMap.Marker(point),

    	tipCon = '<hr/>' + (content.text || '暂无介绍'),
    	tipStyle = content.style || {
    		width: 200,
    		height: 120,
    		title: "<b>记录点详情</b>",
    		enableMessage: false
    	},
    	
    	label = new BMap.Label(text,{
    		position : point,
    		offset:new BMap.Size(offsetX, offsetY)
    	});
    	label.setStyle({
    		color : color,
    		fontSize : fontSize,
    		height : height,
    		width : width,
    		lineHeight : lineHeight,
    		padding: padding,
    		border: border
    	});
    	//marker.setLabel(label);
    	
    	label.addEventListener("mouseover", function(e){
    		marker.setTop(true);
    	});
    	label.addEventListener("mouseout", function(e){
    		marker.setTop(false);
    	});
    	label.addEventListener("dblclick", function(e){
    		map.setCenter(point);
    		map.zoomIn();
    	});
    	marker.addEventListener("mouseover", function(e){
    		marker.setTop(true);
    	});
    	marker.addEventListener("mouseout", function(e){
    		marker.setTop(false);
    	});
    	
        //添加鼠标点击监听
    	marker.addEventListener("click",function(e){
    		var p = e.target,
    		infoWindow = new BMap.InfoWindow(tipCon,tipStyle);  // 创建信息窗口对象
    		map.openInfoWindow(infoWindow,point); //开启信息窗口
    	});
    	
    	function openDetailWindow(){
    		var url = "#projectView/detailPage?projId=" + dbContent.pid;
    		//加载弹屏
    		$("body").cgetPopup({
    			title: '工程信息详述',
    			content: url,
    			ahide: true,
    			ccolor: '#59727D',
    			ctext: '确定',
    			size:'detail',
    			nocache: true,
    			icon: 'fa-file-text'
    		});
    	}
    	
    	if(dbContent && jsonLength(dbContent)){
    		if(dbContent.pid){
    			marker.addEventListener("rightclick",function(e){
    				openDetailWindow();
    			});
    			label.addEventListener("click", function(e){
    				openDetailWindow();
    			})
    		}
    	}
    	alert('2');
    	map.addOverlay(marker);
    }
    
    var handleMap = function(data) {
    	"use strict";
    	
       //计算地图中心点、缩放级别
    	var dataLength = data.length;
    	if(!dataLength){
    		map.centerAndZoom(new BMap.Point(103.388611,35.563611), 5);
    		$(".panel-body").hideMask();
    	}else{
    		//drawFitArea(data);

    	    /*/百度地图API功能
    		//创建Map实例
    		//初始化地图,设置中心点坐标和地图级别43.8312840000,87.6235480000
    		map.centerAndZoom(new BMap.Point(centerX, centerY), getZoom(map, maxX, minX, maxY, minY));
    		//添加地图类型控件
    		map.addControl(new BMap.MapTypeControl());
    		map.addControl(new BMap.NavigationControl());
    		map.enableScrollWheelZoom(true);
    		 */
    		for(var i=0; i<dataLength; i++){
    			drawProjMarker(data[i]);
    		}
    		
    		$(".panel-body").hideMask();
    	} 
    	
    	function drawFitArea(data){
            var centerX = 0,
                centerY = 0,
                leftTop,
                rightTop,
                leftBottom,
                rightBottom,
                d0 = data[0],
                maxX = parseFloat(d0.projLongitude),
                maxY = parseFloat(d0.projLatitude),
                minX = parseFloat(d0.projLongitude),
                minY = parseFloat(d0.projLatitude);
            //console.info(d0.signs);
            for(var i = dataLength - 1; i >= 0; i--){
                var d = data[i];
                //console.info(d.projLatitude + " - " + i);
                if(!d.projLongitude) continue;
                maxX = maxX < parseFloat(d.projLongitude) ? parseFloat(d.projLongitude) : maxX;
                maxY = maxY < parseFloat(d.projLatitude) ? parseFloat(d.projLatitude) : maxY;
                minX = minX > parseFloat(d.projLongitude) ? parseFloat(d.projLongitude) : minX;
                minY = minY > parseFloat(d.projLatitude) ? parseFloat(d.projLatitude) : minY;
            }

            centerX = (maxX + minX) / 2;
            centerY = (maxY + minY) / 2;

            leftTop = new BMap.Point(minX, minY);
            rightTop = new BMap.Point(maxX, minY);
            leftBottom = new BMap.Point(minX, maxY);
            rightBottom = new BMap.Point(maxX, maxY);

            var polygon = new BMap.Polygon([leftTop, rightTop, rightBottom, leftBottom], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
            polygon.addEventListener("click", function(){
                map.centerAndZoom(new BMap.Point(centerX, centerY), 12);
            });
            map.addOverlay(polygon);   //增加多边形
        }

    	
    	//绘制项目位置标记和签字标记
    	function drawProjMarker(pinfo){
    		if(!pinfo.projLongitude || !pinfo.projLatitude){
    			$("#projsallmap").html('<p class="m-t-20 m-l-20">该项目缺少坐标数据, 无法查看地图位置</p>');
    			console.warn("项目缺失坐标数据");
    			return false;
    		}
    		var point = new BMap.Point(pinfo.projLongitude, pinfo.projLatitude),
    		oval = new BMap.Circle(point, 100, {fillColor:"red", fillOpacity: 0.1, strokeColor:"red", strokeWeight:1, strokeOpacity:0.2});
    		var text = '<img style="float:right;margin:4px; margin-top: -5px;" src="images/summary/jhjd.jpg" width="139" height="104" /><p>项目名称：';
    		text += pinfo.projName;
    		text += '</p><p>项目地址：';
    		text += pinfo.projAddr;
    		text += '</p><p>项目规模：';
    		text += pinfo.projScaleDes;
    		text += '</p>';
    		
    		addPosMarker(map, point, '项目位置', '', {
    			text: text,
    			style: {
    				width: 500,
    				height: 180,
    				title: "<b>项目简介</b>",
    				enableMessage: false
    			}
    		},{
    			pid: pinfo.projId
    		},{
    			projLtypeId:pinfo.projLtypeId
    		});	
    		alert('3');
    		map.addOverlay(oval);
    	}
    	
    };
    
    

    function getBoundary() {
	alert('2');
        var hideArea = [
//            "乌鲁木齐米东区",
                "五家渠市",
                "乌鲁木齐达坂城",
                "新疆石河子",
                "新疆乌鲁木齐县",
//            "新疆博湖县",
//            "新疆库尔勒",
                "新疆吐鲁番地区",
//            "新疆奇台县",
//            "新疆阜康",
                "新疆哈密地区",
//            "新疆克拉玛依",
//            "新疆奎屯",
//            "新疆新源县",
//            "新疆轮台县",
                "新疆阿勒泰地区",
                "新疆博尔塔拉蒙古自治州",
                "新疆塔城地区",
                "新疆巴音郭楞蒙古自治州",
                "新疆伊犁哈萨克自治州",
                "新疆阿克苏地区",
//            "新疆和田地区",
                "新疆喀什地区",
                "新疆和田地区",
                "昌吉回族自治州",
                "甘肃省",
                "西藏",
                "青海省",
                "内蒙古",
                "乌鲁木齐米东区",
            ],

            drawLine = [
                "乌鲁木齐水磨沟区",
                "乌鲁木齐天山区",
                "乌鲁木齐沙依巴克区",
                "乌鲁木齐头屯河区",
                "乌鲁木齐新市区"
            ],

            points = [
                "87.365177,43.999672",
                "87.75037,43.961893",
                "87.457163,43.722621",
                "87.834308,43.731793"
            ]

        pointArray = [];

        function drawHideArea(area) {

            var bdary = new BMap.Boundary();
            bdary.get(area, function (rs) {
                var count = rs.boundaries.length;
                if (count === 0) {
                    console.warn(area);
                    return;
                }
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {
                        strokeWeight: 1, strokeColor: "#012841", fillOpacity: 1, fillColor: "#012841"
                    });
                    map.addOverlay(ply);
                    pointArray = pointArray.concat(ply.getPath());
                }
            });
            map.setViewport(pointArray);    //调整视野
        }

        function drawLineArea(area) {

            var bdary = new BMap.Boundary();
            bdary.get(area, function (rs) {
                var count = rs.boundaries.length;
                if (count === 0) {
                    console.warn(area);
                    return;
                }
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {
                        strokeWeight: 2, strokeColor: "#ff3300", fillOpacity: 0.0000001
                    });
                    map.addOverlay(ply);
                    pointArray = pointArray.concat(ply.getPath());
                }
            });
            map.setViewport(pointArray);    //调整视野
        }

        for (var i = hideArea.length - 1; i >= 0; i--) {
            drawHideArea(hideArea[i]);
        }

        for (var i = drawLine.length - 1; i >= 0; i--) {
            drawLineArea(drawLine[i]);
        }

        setTimeout(function(){
            $("#chart-box").css({opacity: 1});

            renderPoints();

        },2000)

    }

    setTimeout(function () {
        getBoundary();
    }, 500);
};

(function($){
	$(".grid-item").hover(function(){
		if($(this).is("#firstLi")){
			//alert('1');
			$("#echartsBoxTop").cgetContent("/projTS/thisYearShow");
		}else if($(this).is("#secondLi")){
			//alert('2');
		}else if($(this).is("#thirdLi")){
			//alert('3');
		}else if($(this).is("#fourthLi")){
			//alert('4');
		}else if($(this).is("#fifthLi")){
			//alert('5');
		}else {
			//alert('6');
		}
	}, function(){
		if($(this).is("#firstLi")){
			alert('1');
		}
	});

	$(".grid-item").click(function(){
		
		$(".chart-box").addClass("toggled");
		
		if($(this).is()){
			
		}else if($(this).is()){
			
		}else if($(this).is()){
			
		}else if($(this).is()){
			
		}else if($(this).is()){
			
		}else {
			
		}
	});
})(jQuery);

</script>
</body>
</html>
