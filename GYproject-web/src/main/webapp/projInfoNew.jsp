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
		<link href="/assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
		<link href="/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" />
		<link href="/assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet"/>
		<link href="/css/ecloud.css" rel="stylesheet" type="text/css"/>
        <!-- ================== END BASE CSS STYLE ================== -->

       
<style type="text/css">
	body{
		/* background-color: #012841 !important; */
		color: #f2f2f2;
	    background: url('images/background.png') no-repeat center top #050e42;
	    background-size: cover;
	}
	.content{
		/* border:1px solid #fff; */
		height:100%;
		margin-left:0px;
	}
	.leftTopBox{
		/* border:1px solid red; */
		height:50%;
	}
	.rightBox{
		position: relative;
		/* border:1px solid green; */
		height:100%;
	}
	.row{
		position:relative;
	}
	.leftBottomBox{
		position:absolute;
		float:left;
		/* border:1px solid blue; */
		height:50%;
		top:50%;
	}
	.leftTopSmallBox{
		position:relative;
		border:1px solid #fff;
		height:92%;
		top:30px;
		left:60px;
		border: rgba(255,255,255,.6) 1px solid;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
       	box-shadow: 2px 4px 6px #000 ;
	}
	.leftEchartTopBox{
		position:absolute;
		/* border:1px solid #ff1100; */
		height:45%;
		bottom:0px;
	}
	
	.leftBottomSmallBox{
		position:relative;
		border:1px solid #fff;
		height:92%;
		top:15px;
		left:60px;
		border: rgba(255,255,255,.6) 1px solid;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
       	box-shadow: 2px 4px 6px #000 ;
	}
	
	.leftEchartBottomBox{
	    position:absolute;
		/* border:1px solid #ff1100; */
		height:60%;
		bottom:0px;
		right:10px;
	}
	td{
		font-size:18px;
		color:#fff;
		font-family:"黑体";
		padding:5 2 5 2;
	}
	.input-box{
		float: left;
		width: 50%;
		height: 30px;
		overflow: hidden;
		box-sizing: border-box;
		color: #fff;
	}
	.input-box label{
		font-size: 18px; 
		float:left;
		margin-right: 6px;
		color: #fff;
		line-height:30px;
		max-width:120%;
	}
	.input-box .form-control{
		font-size: 18px; 
		float: left;
		width: calc(100% - 6em);
		border: none;
		background: transparent;
		outline: none !important;
		color: #fff;
		line-height: 30px;
		text-align:right;
		font-family: "黑体"; 
	}
	.input-box .input-control{
		font-size: 18px; 
		float: left;
		width: calc(100% - 6em);
		border: none;
		background: transparent;
		outline: none !important;
		color: #fff;
		line-height: 30px;
		text-align:right;
		font-family: "黑体"; 
	}
	
	
	.liBefore:before{
		content: "■";
		-webkit-transform: scale(.4);
		-moz-transform: scale(.4);
		-ms-transform: scale(.4);
		transform: scale(.4);
		padding-right: 10px;
		float: left;
		line-height: 26px;
	}
	.unitTable{
		margin-left:12px;
	}
	.constructionTitle{
		margin-left:2px !important;
	}
	.rightTopBox{
		position:absolute;
		height:10%;
		/* background-color:red; */
		right:0px;
		top:10px;
		/* border:1px solid #b2b2b2; */
		z-index:101;
	}
	
	.homePage{
		position:absolute;
		height:10%;
		right:0px;
		bottom:30px;
		z-index:103;
	}
	.shadeBox{
		position:absolute;
		height:8%;
		right:0px;
		bottom:0px;
		background-color:#012841;
		z-index:102;
	}
	
	.rightBottomBox{
		/* margin-to:50px; */
		position:absolute;
		/* top:20px; */
		height:100%;
		/* z-index:10000; */
		left: 0;
        top: 0;
        bottom: -50px;
        right: 50px;
		/* border:1px solid red;  */
		z-index:100;
	}
	
	
	.btn {
         display: inline-block;
         line-height: 20px;
         height: 33px;
         padding: 4px 13px;
         color: #f2f2f2;
         font-size: 18px;
         text-decoration: none;
         outline: none;
     }
     .btn:hover{
     	color:#fff;
    	background:#4c6f86 !important;
    	border-color:#4c6f86 !important;
    }
     .item-bg-blue {
         border-radius: 5px;
         background-color: #3ba8dc;
         line-height: 22px !important;
         margin-left: 10px;
         margin-top:15px;
         float:right;
         box-shadow: rgba(0,0,0,.5) 0 2px 8px;
     }
     .rightTopBox span{
	    position:absolute; 
	     font-size:25px;
	     right:90px;
	     top:12px;
	     color:#fff;
     } 
     
     .pace-progress, .pace:before {
	     top: 0;
     }
</style>

<div id="content" class="content">
	<div class="row">
		<div class="col-lg-5 col-md-5 leftTopBox">
			<div class="col-lg-10 col-md-10 leftTopSmallBox">
				<div class="col-lg-11 col-md-11">
					<p  class="first" style=" color:#fff; margin-top: 40px;margin-left:10px;font-size:25px;">工程项目</p>
					<div class="input-box">
				     	<label class="control-label liBefore" for="lastYearSum">上年结转</label>
						<input class="form-control" value="" type="text"  id="lastYearSum" name="lastYearSum">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label" for="lastYearAmount">金额</label>
						<input class="input-control" value="" type="text" id="lastYearAmount" name="lastYearAmount">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label liBefore" for="thisYearSum">本年新增</label>
						<input class="form-control" value="" type="text" id="thisYearSum" name="thisYearSum">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label" for="thisYearAmount">金额</label>
						<input class="input-control" value="" type="text"  id="thisYearAmount" name="thisYearAmount">
					</div>
					<%--<div class="input-box">--%>
				     	<%--<label class="control-label liBefore" for="alreadyFinishNum">转天然气</label>--%>
						<%--<input class="form-control" value="" type="text" id="alreadyFinishNum" name="alreadyFinishNum" >--%>
				    <%--</div>--%>
		        	<%--<div class="input-box">--%>
				     	<%--<label class="control-label" for="terminationNum">退单</label>--%>
						<%--<input class="input-control" value="" type="text"  id="terminationNum" name="terminationNum" >--%>
					<%--</div>--%>
				</div>
				<div class="col-lg-11 col-md-11 leftEchartTopBox" id="leftEchartTopBox"></div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 rightTopBox" id="rightTopBox">
				<span>工程项目统计分析</span>
				<!-- <a href="javascript:;" class="btn item-bg-blue" id="projList">列表</a> -->
				<a href="javascript:;" class="btn item-bg-blue" id="homePage">主页</a>
		</div>
		<div class="col-lg-7 col-md-7  rightBox">
			<div class="col-lg-12 col-md-12 rightBottomBox" id="rightBottomBoxBg"></div>
			<div class="col-lg-12 col-md-12 rightBottomBox" id="rightBottomBox"></div>
			<!-- <div class="col-lg-12 col-md-12 shadeBox" id="shadeBox">
			</div> -->
		</div>
		<div class="col-lg-4 col-md-4 homePage" id="homePage">
			<!-- 	<a href="javascript:;" class="btn item-bg-blue" id="homePage">主页</a> -->
		</div>
		<div class="col-lg-5 col-md-5  leftBottomBox">
			<div class="col-lg-10 col-md-10 leftBottomSmallBox">
				<div class="col-lg-11 col-md-11">
					<p  class="constructionTitle" style=" color:#fff; margin-top: 40px;margin-left:10px;font-size:25px;">工程施工</p>
					<div class="input-box">
				     	<label class="control-label " for="conProjectNum">在建工程</label>
						<input class="form-control" value="" type="text" id="conProjectNum" name="conProjectNum">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label" for="conProjectAmount">金额</label>
						<input class="input-control" value="" type="text" id="conProjectAmount" name="conProjectAmount">
		        	 </div>
				</div>
				<table class="unitTable" id="unitTable">
					<tr>
						<td>民用户：</td>
						<td></td>
					</tr>
					<tr>
						<td>公建户：</td>
						<td></td>
					</tr>
					<tr>
						<td>改管：</td>
						<td></td>
					</tr>
					<tr>
						<td>干线：</td>
						<td></td>
					</tr>
					<tr class="hidden">
						<td>智能表：</td>
						<td></td>
					</tr>
					<tr>
						<td>场站：</td>
						<td></td>
					</tr>
				</table>
				<div class="col-lg-8 col-md-8 leftEchartBottomBox" id="leftEchartBottomBox">
					
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ================== BEGIN BASE JS ================== -->
<script src="/assets/plugins/pace/pace.min.js"></script>
<!-- ================== END BASE JS ================== -->
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
<script>

$.getScript('assets/plugins/echarts/build/dist/echarts.js').done(function(){
	$.getScript('projectjs/monitor/lastYear-thisYear-sum.js?v=1000').done(function () {
		projectStatics.init();
	});
});

renderPoints();

function renderPoints(){
	var post = {};
	post.finishedDateStart = new Date().getFullYear() + '-01-01';
	post = JSON.stringify(post);
	$.ajax({
	    type: 'POST',
	    url:"projectMap/echartsMapData",
	    data: post,
	    dataType: 'json',
	    contentType: "application/json;charset=UTF-8",
	    success: function(text) {
	    	var dd=text.data;
	    	var geoMap=text.geoCoordMap;
	    	renderMapDataChart(dd,geoMap);
	    },
	    error: function(jqXHR, textStatus, errorThrown) {
			printXHRError("projectQuery", "项目列表加载失败, 请重试", jqXHR, textStatus, errorThrown);
	    }
	});
}

let renderMapDataChart = function (dd,geoMap) {
	
	$.get('assets/plugins/echarts/guiyang.json', function (json) {
        echarts.registerMap('wlmqs', json);
        renderBg('rightBottomBoxBg',dd);
        renderData('rightBottomBox',dd,geoMap); 
    });
	
	 let renderBg = function (id,dd) {
         let dataMapBg = echarts.init(document.getElementById(id)),
         optionBg = {
             title    : null,
             tooltip  : null,
             legend   : null,
             dataRange: {
                 min       : 0,
                 max       : 1000,
                 color     : ['rgba(31,27,62,.6)', 'rgba(29,147,185,.6)'],
                 text      : ['高', '低'],           // 文本，默认为数值文本
                 calculable: true,
                 show      : false
             },
             series   : [
                 {
                     type            : 'map',
                     mapType         : 'guiyang', //
                     selectedMode    : 'single',
                     itemStyle       : {
                         normal  : {label: {show: false}},
                         emphasis: {label: {show: false}},
                         show    : false
                     },
                     showLegendSymbol: false,
                     data            : [
                         {name:"天山区", value: 300},
                         {name:"沙依巴克区", value: 650},
                         {name:"新市区", value: 80},
                         {name:"水磨沟区", value: 520},
                         {name:"头屯河区", value: 960}
                                        ]
                 }
             ]
         }

         dataMapBg.setOption(optionBg);
     },

    renderData = function (id,data,geoCoordMap) {
		let dataMap = echarts.init(document.getElementById(id)),
        convertData = function (data) {
			let res = [];
            for (let i = 0; i < data.length; i++) {
                let geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name : data[i].name,
                        value: geoCoord.concat(data[i].value)
                    });
                }
            }
            return res;
		},

        option = {
            title  : {
                text     : '',
                left     : 'center',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function(params){
                	return params.name+'<br />'+'金额：'+params.value[2];
                }
            },
            legend : {
                show     : false,
                orient   : 'vertical',
                y        : 'bottom',
                x        : 'right',
                data     : ['pm2.5'],
                textStyle: {
                    color: '#fff'
                }
            },
            geo    : {
                map      : 'wlmqs',
                label    : {
                    emphasis: {
                        show: false
                    }
                },
                roam     : true,
                itemStyle: {
                    normal  : {
                        areaColor  : 'transparent',
                        borderColor: '#e2e2e2'
                    },
                    emphasis: {
                        areaColor: 'transparent'
                    }
                }
            },
            series : [
                {
                    name            : '',
                    type            : 'effectScatter',
                    coordinateSystem: 'geo',
                    data            : convertData(data),
//                    symbolSize      : function (val) {
//                        return val[2] / 1000000;
//                    },
                    label           : {
                        normal  : {
                            formatter: '{b}',
                            position : 'right',
                            show     : false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle       : {
                        normal: {
                            color: '#ddb926'
                        }
                    }
                }
            ]
        };

    dataMap.setOption(option);
	}
};

//跳转到工程项目
$(".leftTopSmallBox").off('click').on('click',function(){
	 //$("body").cgetContent("projTS/projInfo");
	 //window.open("http://localhost/projTS/projInfo");
	var path = '<%=basePath%>';
	window.location.href=path+"projTS/newSelf";
});

//跳转到工程施工
$(".leftBottomSmallBox").off('click').on('click',function(){
	//$("body").cgetContent("projTS/projConstructionInfo");
	//window.open("http://localhost/projTS/projConstructionInfo");
	var path = '<%=basePath%>';
	window.location.href=path+"projTS/projConstructionInfo";
});

//跳转到列表屏
$('#projList').off('click').on('click',function(){
	 //window.location.href="http://localhost/projTS/viewPorjectList";
	 //$("body").cgetContent("projTS/viewPorjectList");
	 var path = '<%=basePath%>';
	 console.info('path...'+path);
	 window.location.href=path+"projTS/viewPorjectList";
})

//跳转到首页
$('#homePage').off('click').on('click',function(){
	 var path = '<%=basePath%>';
	 //window.location.href="http://localhost/projTS/viewPorjectList";
	 window.location.href=path+"index.jsp"
;})  



</script>