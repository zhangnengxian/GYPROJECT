<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div id="content" class="content">
	<div class="row">
		<div class="col-md-6 col-xs-12 " >
			<div id="" style='width:100%;height:400px;text-align:center;margin-top: 180px'>
			
			<h4 ><b>工程项目总体信息</b></h4>
			<table id="scaleAmountTable" class="table table-hover table-bordered" style="margin:auto;" >
				<tr>
					<th class="text-left" width="100"></th>
					<th class="text-right" width="180">上年结转数量</th>
					<th class="text-right" width="180">上年结转金额</th>
		            <th class="text-right" width="180">本年签约数量</th>
		            <th class="text-right" width="180">本年签约金额</th>
				</tr>
				<c:forEach var="dto" items="${amountList}">
					<tr>
		            	<td class="text-left">${dto.type}</td>
		                <td class="text-right ">${dto.lastYearNum}</td>
		                <td class="text-right">${dto.lastYearMoney}</td>
		                <td class="text-right">${dto.thisYearNum}</td>
		                <td class="text-right">${dto.thisYearMoney}</td>
					</tr>	             	
				</c:forEach>
				<tr>
	      			<td class="text-left">合计</td>
	      			<td class="text-right"></td>
	      			<td class="text-right"></td>
	      			<td class="text-right"></td>
	      			<td class="text-right"></td>
	      		</tr>
			</table>
			</div>
		</div>	
		<div class="col-md-6 col-xs-12">
			<div id="scaleAmount" style='width:100%;height:600px;text-align:center'>
			
			</div>
		</div>				
	</div>
</div> 

<!-- <div style='text-align:center'>
<div id="scaleAmount" style='width:80%;height:600px;text-align:center'></div>  -->

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

/* $.getScript('projectjs/monitor/scale-amount.js').done(function () {
    scaleAmount.init();
}); */


/* $.ajax({
    type: 'POST',
    url: '/projectTotalStatistics/scaleAmountStatistics',
    contentType: "application/json;charset=UTF-8",
    data: '',
    success: function (data) {
    	console.log("data..."+data);
    	var json=JSON.parse(data);
    	 console.info('1111111111111.'+$("#scaleAmountTable tr:eq(1)").children().eq(2).html());
    },
    error: function (jqXHR, textStatus, errorThrown) {
        console.warn("ajax queryScaleDetail...fail");
    }
});  */
var lastYearNum = 0.00;
//5行2列的值求和
$("#scaleAmountTable tr td:nth-child(2)").each(function () {
	lastYearNum += new Number($(this).text());
});
$("#scaleAmountTable tr:eq(4)").children().eq(1).html(lastYearNum)

var lastYearSum = 0.00;
$("#scaleAmountTable tr td:nth-child(3)").each(function () {
	lastYearSum += new Number($(this).text());
});
$("#scaleAmountTable tr:eq(4)").children().eq(2).html(lastYearSum)

var thisYearSum = 0.00;
$("#scaleAmountTable tr td:nth-child(4)").each(function () {
	thisYearSum += new Number($(this).text());
});
$("#scaleAmountTable tr:eq(4)").children().eq(3).html(thisYearSum)

var thisYearNum = 0.00;
$("#scaleAmountTable tr td:nth-child(5)").each(function () {
	thisYearNum += new Number($(this).text());
});
$("#scaleAmountTable tr:eq(4)").children().eq(4).html(thisYearNum)





//alert(sum);
 var civilAmount1= new Number($("#scaleAmountTable tr:eq(1)").children().eq(2).html());  
 var civilAmount2= new Number($("#scaleAmountTable tr:eq(1)").children().eq(4).html());  
 var civilAmount=civilAmount1+civilAmount2;
 
 var publicAmount1= new Number($("#scaleAmountTable tr:eq(2)").children().eq(2).html());  
 var publicAmount2= new Number($("#scaleAmountTable tr:eq(2)").children().eq(4).html());  
 var publicAmount=publicAmount1+publicAmount2;
 
 var motorAmount1= new Number($("#scaleAmountTable tr:eq(3)").children().eq(2).html());  
 var motorAmount2= new Number($("#scaleAmountTable tr:eq(3)").children().eq(4).html());  
 var motorAmount=motorAmount1+motorAmount2;
 
 
/*  console.info("civilAmount1.."+civilAmount1);
 console.info("civilAmount2.."+civilAmount2);
 console.info("civilAmount.."+civilAmount); */
 
var data = [{
    value: civilAmount,
    name: '民用'
}, {
    value: publicAmount,
    name: '公用'
}, {
    value: motorAmount,
    name: '车用'
}];
option = {
    backgroundColor: '#fff',
    title: {
        text: '工程项目总体信息',
        subtext: '2015年-2016年',
        x: 'center',
        y: 'center',
        textStyle: {
            fontWeight: 'normal',
            fontSize: 16
        }
    },
    tooltip: {
        show: true,
        trigger: 'item',
        formatter: "{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'horizontal',
        bottom: '0%',
        data: ['民用', '公用', '车用']
    },
    //半径
    series: [{
        type: 'pie',
        selectedMode: 'single',
        radius: ['25%', '58%'],
        color: ['#FF999A', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67'],

        label: {
            normal: {
                position: 'inner',
                formatter: '{d}%',

                textStyle: {
                    color: '#fff',
                    fontWeight: 'bold',
                    fontSize: 14
                }
            }
        },
        labelLine: {
            normal: {
                show: false
            }
        },
        data: data
    }, {
        type: 'pie',
        radius: ['58%', '83%'],
        itemStyle: {
            normal: {
                color: '#F2F2F2'
            },
            emphasis: {
                color: '#ADADAD'
            }
        },
        label: {
            normal: {
                position: 'inner',
                formatter: '{c}',
                textStyle: {
                    color: '#777777',
                    fontWeight: 'bold',
                    fontSize: 14
                }
            }
        },
        data: data
    }]
};
var scaleAmount = echarts.init(document.getElementById('scaleAmount'),'infographic');

scaleAmount.setOption(option);

</script>