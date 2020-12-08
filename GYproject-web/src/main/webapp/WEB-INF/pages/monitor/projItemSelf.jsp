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
		
		.lean-grid.fullScreen > ul {
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
            z-index: 1;
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
			z-index: 3;
		}
		.fullScreenEchartBox.toggled{
			padding-left: 0;
			z-index: 4;
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
			left:660px;
			width:580px;
	        height:280px;
        }
        
        .echartsBoxFirst{
        	position:absolute;
			top:40px;
			left:660px;
			width:580px;
	        height:560px;
        }
        .backHomeBtn{
       		float:right;
       		right:20px;
       		width:80px;
	        height:60px;
        	/* border:1px solid red; */
        	
        }
        .backHomeBtn .btn {
            display: inline-block;
            line-height: 20px;
            height: 33px;
            padding: 1px 12px;
            color: #f2f2f2;
            font-size: 18px;
            text-decoration: none;
            outline: none;
        }
        .backHomeBtn a{
        	position:absolute;
        	top:20px;
        }
        .btn svg{
        	width: 30px;
		    height: 30px;
		    fill: #fff;
		}
        /* 按钮 */
        .item-bg-blue {
            border-radius: 5px;
            background-color: #3ba8dc;
            line-height: 22px !important;
            margin-left: 10px;
            box-shadow: rgba(0,0,0,.5) 0 2px 8px;
        }
        
        .echartsBox{
	        position:absolute;
	        top:320px;
	        left:660px;
	        width:580px;
	        height:300px;
	       /*  border:1px solid red; */
        }
       
        .fullScreenEchartBox{
        	position:absolute;
        	top:30px;
        	bottom:0;
        	left:80px;
        	right:10px;
        	width:90%;
        	height:90%;
        	/* border:1px solid red; */
        }
        .fullScreenLeftBox{
        	position:absolute;
        	width:50%;
        	height:100%;
        	/* border:1px solid #fff; */
        }
        .fullScreenRightBox{
        	float:right;
        	width:50%;
        	height:100%;
        /* 	border:1px solid #fff; */
        }
        .close-btn{
        	width: 45px;
        	position: absolute;
        	top: 0;
        	bottom: 0;
        	left: 0;
        	display: none;
        	z-index: 4;
    		background: #0ec9d7;
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
        
        /* .chart-box.toggled + .close-btn{
        	display: block;
        } */
        .fullScreenEchartBox.toggled + .close-btn{
        	display: block;
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
		.fullScreenEchartBox  table.dataTable,
		.fullScreenEchartBox  table.dataTable tbody tr,
		.fullScreenEchartBox  table.dataTable tbody td{
			background: transparent !important;
			color: #b2b2b2;
	      	border-color: #999;
		}
		.fullScreenEchartBox  table.dataTable tbody tr.selected,
		.fullScreenEchartBox  table.dataTable tbody tr.selected td{
			background:#4c6f86 !important;
	      	color: #a9a9a9;
		}
		.table>thead>tr>th{
			color:#fff !important;
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
                	<p style="margin-top: 10px;">去年工程项目<span class="lastYearNum">0</span>个</p>
				    <p>收入<span class="lastYearMoney">0.00</span>元</p>
				    <p>今年新增工程项目<span class="thisYearNum">0</span>个</p>
				    <p>收入<span class="thisYearMoney">0.00</span>元</p>
                </div>
            </div>
        </li>
        <li class="grid-item" id="secondLi">
            <div class="grid-bg">
                <div class="grid-content second">
                	<image src="../images/monitor/statistics.png" style="margin-top: -5px;"/>
                	<p style="margin-top: 10px;">居民户<span class="civilianNum">0</span>个</p>
				    <p>工商户<span class="publicNum">0</span>个</p>
				    <p>车用户<span class="vehicleUsed">0</span>个</p>
                </div>
            </div>
        </li>
        <li class="grid-item" id="thirdLi">
            <div class="grid-bg" >
                <div class="grid-content third">
                	<image src="../images/monitor/distribute.png" style="margin-top: -10px;width:84.72px;height:74px"/>
                	<p>天山区:<span class="area0">0</span>个</p>
				    <p>沙依巴克区：<span class="area1">0</span>个</p>
				    <p>新市区：<span class="area2">0</span>个</p>
				    <p>水区：<span class="area3">0</span>个</p>
				    <p>头屯河区：<span class="area4">0</span>个</p>
                </div>
            </div>
        </li>
        <li class="grid-item clear-left" id="fourthLi">
            <div class="grid-bg">
                <div class="grid-content fourth">
                	<image src="../images/monitor/sandGlass.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">立项:<span class="acceptNum">0</span>个工程项目</p>
				    <p>在建:<span class="constructingNum">0</span>个工程项目</p>
				    <p>结单:<span class="trunGasNum">0</span>个工程项目</p>
				    <p>退单:<span class="chargebackNum">0</span>个工程项目</p>
                </div>
            </div>
        </li>
        <li class="grid-item" id="fifthLi">
            <div class="grid-bg" >
                <div class="grid-content fifth">
                	<image src="../images/monitor/contract.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">申请:<span class="acceptTotalNum">0</span>个工程项目</p>
				    <p>签约:<span class="signTotalNum">0</span>个工程项目</p>
                </div>
            </div>
        </li>
        <li class="grid-item clear-left" id="sixthLi">
            <div class="grid-bg">
                <div class="grid-content sixth">
                	<image src="../images/monitor/amount.png" style="margin-top: -15px;"/>
                	<p style="margin-top: 10px;">结款:<span class="alreadyChargeMoney">0.00</span>元</p>
				    <p>未结:<span class="haveNotCharge">0.00</span>元</p>
                </div>
            </div>
        </li>
    </ul>
    <div class="chart-box">
    	<div id="backHomeBtn" class="backHomeBtn">
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
		</div>
    	<div id="echartsBoxFirst" class="echartsBoxFirst">
    		<input type="hidden" value="" id="echartsBoxFirstInput"/>
		</div>
    	<div id="echartsBoxTop" class="echartsBoxTop" >
    		<input type="hidden" value="" id="echartsBoxFirstInput"/>
		</div>
		<div class="echartsBox" id="echartsBox" >
		</div>
    </div>
    <div class="fullScreenEchartBox" id="fullScreenEchartBox">
    	<div class="fullScreenLeftBox" id="fullScreenLeftBox">
    	</div>
    	<div class="fullScreenRightBox" id="fullScreenRightBox">
    	</div>
    </div>
	<a href="javascript:;" class="close-btn"><i class="fa fa-angle-right"></i></a>
	<input type="hidden" id="path">
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

    $.getScript('/assets/plugins/echarts/build/dist/echarts.js').done(function(){
		$.getScript('/projectjs/monitor/project-info-item.js').done(function () {
			projectInfoItem.init();
		});
	});  
	

	$("#backHome").off().on('click',function(){
		var path = '<%=basePath%>';
		 window.location.href=path+"projInfo.jsp"
	})
	
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

(function($){
	$(".grid-item").hover(function(){
		$("#echartsBoxTop").removeClass('hidden');
		if($(this).is("#firstLi")){
			//alert('1');
			$("#echartsBoxTop").cgetContent("/projTS/thisYearShow");
		}else if($(this).is("#secondLi")){
			//alert('2');
			$("#echartsBoxTop").cgetContent("/projTS/projTypeShow");
		}else if($(this).is("#thirdLi")){
			//alert('3');
			$("#echartsBoxTop").cgetContent("/projTS/projAreaShow");
		}else if($(this).is("#fourthLi")){
			//alert('4');
			$("#echartsBoxTop").cgetContent("/projTS/projStageShow");
		}else if($(this).is("#fifthLi")){
			//alert('5');
			$("#echartsBoxTop").cgetContent("/projTS/projApplySignShow");
		}else {
			//alert('6');
			$("#echartsBoxTop").cgetContent("/projTS/projAmountShow");
		}
	}, function(){
		$("#echartsBoxTop").addClass('hidden');
	});

	$(".grid-item").click(function(){
		
		$(".fullScreenEchartBox").addClass("toggled");
		$('.lean-grid').addClass('fullScreen');
		if($(this).is("#firstLi")){
			//第一个grid
			thisYearShow();
		}else if($(this).is($("#secondLi"))){
			//第二个grid
			projTypeShow();
		}else if($(this).is($("#thirdLi"))){
			//第三个grid
			projAreaShow();
		}else if($(this).is($("#fourthLi"))){
			//第四个grid
			$("#fullScreenEchartBox").cgetContent("/projTS/viewStageStatisticsFullScreen");
		}else if($(this).is($("#fifthLi"))){
			//第五个grid
			projApplySignShow();
		}else if($(this).is($("#sixthLi"))){
			//第六个grid
			projAmountShow();
		}
	});
	$('.close-btn').off().on('click',function(){
		$(".fullScreenEchartBox").removeClass("toggled");
		$(".fullScreenEchartBox").empty();
		$('.lean-grid').removeClass('fullScreen');
		//window.history.back();
		var path = '<%=basePath%>';
		window.location.href=path+"projTS/projItemSelf";
		
	});
})(jQuery);



//结转新增
var thisYearShow=function(){
	
	$.ajax({
	    type: 'POST',
	    url: '/projTS/queryLastYearAndThisYearNum',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	console.info(data);
	    	var data=JSON.parse(data);
	    	var thisYearNum=data[0].thisYearNum;
	    	var thisYearMoney=data[0].thisYearMoney;
	    	var lastYearNum=data[0].lastYearNum;
	    	var lastYearMoney=data[0].lastYearMoney;
	    	thisYearShowFullScreen(lastYearNum,thisYearNum,lastYearMoney,thisYearMoney);	    	
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
}


var thisYearShowFullScreen=function(lastYearNum,thisYearNum,lastYearMoney,thisYearMoney){

	var option = {
			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f'],
			title: {
		        text: '工程项目数量金额统计',
		        left: 'center',
		        top: '94%',
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
		        data:['去年项目','今年新增'],
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
		                {value:lastYearMoney, name:'去年收入'},
		                {value:thisYearMoney, name:'今年收入', selected:true}
		              
		            ]
		        },
		        {
		            name:'工程信息',
		            type:'pie',
		            radius: ['44%', '59%'],

		            data:[
		                {value:lastYearNum, name:'去年项目'},
		                {value:thisYearNum, name:'今年新增'}
		            ]
		        }
		    ]
		};
	
	var areaAmount = echarts.init(document.getElementById('fullScreenLeftBox'),'shine');
	areaAmount.setOption(option);
	deduction();
}






var deduction=function(){
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
                		        text: '工程项目退单原因统计',
                		        left: 'center',
                		        top: '94%',
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
            		           /*  center: ['50%', '60%'], */
            		            data:seriesData
            		        }
            		    ]
            		};
               
            	var deductionPerMonth = echarts.init(document.getElementById('fullScreenRightBox'),'shine');
            	deductionPerMonth.setOption(option);
            }
        });
    };
    
    
//桑基图
var projTypeShow=function(){
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
}
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
	                    	color: 'target',
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
	
	var projSankey = echarts.init(document.getElementById('fullScreenEchartBox'),'shine');
    projSankey.setOption(option);
}
//区域统计
var projAreaShow=function(){
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
}
function parseReturnTotal(data)
{
	var vJsonData = JSON.parse(data);
	
	option = {
			color : ['#ff9824','#0dbacd','#ffb980','#1dcc9f'],
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
		        itemGap:15,
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
	
	var projTotal = echarts.init(document.getElementById('fullScreenEchartBox'),'macarons');
	projTotal.setOption(option);
};

//立项签约
var projApplySignShow=function(){
	
	$.ajax({
	    type: 'POST',
	    url: '/projectTotalStatistics/projectOfAcceptAndSign',
	    contentType: "application/json;charset=UTF-8",
	    data: '',
	    success: function (data) {
	    	projApplySignCallback(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	});
	
}

function projApplySignCallback(data)
{
	var dataObj = JSON.parse(data);
	console.info(dataObj);
	console.info(dataObj.sign);
	option = {
		color : ['#0dbacd','#1dcc9f'],
	    title: {
	        text: '近一年工程项目立项及签约数量统计',
	        left: 'center',
	        top:'94%',
	        textStyle: {
	            color: '#fff',
	            fontSize:18,
	            fontFamily: '黑体'
	        }
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['立项','签约'],
	        textStyle: {
	            color: '#fff',
	            fontSize:18,
	            fontFamily: '黑体'
	        },
	        top: '10'
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : [{value:'1月',textStyle: {
	    		    		            color: '#fff',
	    		    		            fontSize:16
	    		    	}},'2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
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
	            axisLine: {
		    	    lineStyle: {
		    	    color: '#fff'
	    	    	}
	    	    }
	        }
	    ],
	    series : [
	        {
	            name:'立项',
	            type:'bar',
	            data:dataObj.accept,
	           /* markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },*/
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'签约',
	            type:'bar',
	            data:dataObj.sign,
	            /*markPoint : {
	                data : [
	                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183},
	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
	                ]
	            },*/
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};
	var projTotal = echarts.init(document.getElementById('fullScreenEchartBox'),'macarons');
	projTotal.setOption(option);
};
//实收实付
var projAmountShow=function(){
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
}

function parseReturnPayment(data)
{
	var vJsonData = JSON.parse(data);
	console.info(data);
	option = {
			color : ['#0dbacd','#1dcc9f'],
			title: {
		        text: '近一年工程项目实收实付统计',
		        left: 'center',
		        top:'94%',
		        textStyle: {
		            color: '#fff',
		            fontSize:18,
		            fontFamily: '黑体'
		        }
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
	
	var projPayment = echarts.init(document.getElementById('fullScreenEchartBox'));
    projPayment.setOption(option);
}
</script>
</body>
</html>
