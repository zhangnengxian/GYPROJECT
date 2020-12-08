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
		background-color: #012841 !important;
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
		top:15px;
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
		bottom:10px;
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
		height:70%;
		bottom:10px;
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
</style>

<div id="content" class="content">
	<div class="row">
		<div class="col-lg-5 col-md-5  leftTopBox">
			<div class="col-lg-10 col-md-10  leftTopSmallBox">
				<div class="col-lg-11 col-md-11">
					<p  class="first" style=" color:#fff; margin-top: 5px;margin-left:10px;font-size:25px;">工程项目</p>
					<div class="input-box" ">
				     	<label class="control-label liBefore" for="connectGasDes">上年结转</label>
						<input class="form-control" value="" type="text"  id="lastYearSum" name="lastYearSum">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label" for="connectGasDes">金额</label>
						<input class="input-control" value="" type="text" id="lastYearAmount" name="lastYearAmount">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label liBefore" for="connectGasDes">本年新增</label>
						<input class="form-control" value="" type="text" id="thisYearSum" name="thisYearSum">
				    </div>
		        	<div class="input-box">
				     	<label class="control-label" for="connectGasDes">金额</label>
						<input class="input-control" value="" type="text"  id="thisYearAmount" name="thisYearAmount">
		        	 </div>
		        	 <div class="input-box">
				     	<label class="control-label liBefore" for="connectGasDes">转天然气</label>
						<input class="form-control" value="" type="text" id="alreadyFinishNum" name="alreadyFinishNum" >
				    </div>
		        	<div class="input-box">
				     	<label class="control-label" for="connectGasDes">退单</label>
						<input class="input-control" value="" type="text"  id="terminationNum" name="terminationNum" >
		        	 </div>
				</div>
				<div class="col-lg-11 col-md-11 leftEchartTopBox" id="leftEchartTopBox">
					
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 rightTopBox" id="rightTopBox">
				<span>工程项目统计分析</span>
				<a href="javascript:;" class="btn item-bg-blue" id="projList">列表</a>
		</div>
		<div class="col-lg-7 col-md-7  rightBox">
			<div class="col-lg-12 col-md-12 rightBottomBox" id="rightBottomBox">
			</div>
			<div class="col-lg-12 col-md-12 shadeBox" id="shadeBox">
			</div>
		</div>
		<div class="col-lg-4 col-md-4 homePage" id="homePage">
				<a href="javascript:;" class="btn item-bg-blue" id="homePage">主页</a>
		</div>
		<div class="col-lg-5 col-md-5  leftBottomBox">
			<div class="col-lg-10 col-md-10 leftBottomSmallBox">
				<div class="col-lg-11 col-md-11">
					<p  class="constructionTitle" style=" color:#fff; margin-top: 5px;margin-left:10px;font-size:25px;">工程施工</p>
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
						<td>一处：</td>
						<td></td>
					</tr>
					<tr>
						<td>二处：</td>
						<td></td>
					</tr>
					<tr>
						<td>三处：</td>
						<td></td>
					</tr>
					<tr>
						<td>四处：</td>
						<td></td>
					</tr>
					<tr>
						<td>五处：</td>
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
	$.getScript('projectjs/monitor/lastYear-thisYear-sum.js').done(function () {
		projectStatics.init();
	});
});


$(function () {
	
    $("#rightBottomBox").css({opacity: 0});

    // 百度地图API功能
    var map = new BMap.Map("rightBottomBox");
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
            url:"projectMap/queryProjectSign",
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
    	var myIcon1 =  new BMap.Icon("images/map/blue.png" , new BMap.Size(19,25)) ;
    	var myIcon2 =  new BMap.Icon("images/map/green.png", new BMap.Size(19,25));
    	var myIcon3 =  new BMap.Icon("images/map/yellow.png", new BMap.Size(19,25)) ;
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
    			$("#projsrightBottomBox").html('<p class="m-t-20 m-l-20">该项目缺少坐标数据, 无法查看地图位置</p>');
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
    		map.addOverlay(oval);
    	}
    };
    function getBoundary() {

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
            $("#rightBottomBox").css({opacity: 1});

            renderPoints();

        },2000)

    }

    setTimeout(function () {
        getBoundary();
    }, 500);
});



//跳转到工程项目
$(".leftTopSmallBox").off('click').on('click',function(){
	 //$("body").cgetContent("projTS/projInfo");
	 //window.open("http://localhost/projTS/projInfo");
	var path = '<%=basePath%>';
	window.location.href=path+"projTS/projItemSelf";
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