<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	你是是多少的速度
	<div class="map-box">
 		<div id="allmap"></div>
 	</div>
</div>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
<script type="text/javascript" src="https://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script include-on-mobile="false" src="/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script include-on-mobile="true" src="/js/ecloud.js"></script>
<script type="text/javascript">
$(function () {

    $("#allmap").css({opacity: 0});

    // 百度地图API功能
    var map = new BMap.Map("allmap");
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
    		map.addOverlay(oval);

    		/* //绘制签字坐标点
    		var signMarker = pinfo.signs,
    		signMarkerLength = signMarker.length,
    		//定义图标和颜色库，供随机调用
    		icons = [
    		    {"icon":"1.png", "color":"#ed2d2d"},
    		    {"icon":"2.png", "color":"#00d000"},
    		    {"icon":"3.png", "color":"#16bfff"},
    		    {"icon":"4.png", "color":"#ff58ff"},
    		    {"icon":"5.png", "color":"#ccb400"},
    		    {"icon":"6.png", "color":"#ffad00"},
    		    {"icon":"7.png", "color":"#ff52ff"},
    		    {"icon":"8.png", "color":"#d24cff"},
    		    {"icon":"9.png", "color":"#00a4ff"},
    		    {"icon":"10.png", "color":"#0098d3"}
    		];		
    		
    		for(var j=0; j<signMarkerLength; j++){
    			var sd = signMarker[j];
    			if(sd.longitude && sd.latitude){
    				var title = sd.menuDes ? sd.menuDes + '签字地点' : '签字地点',
    				icon = icons[Math.floor(Math.random()*10)],
    				text = '<p><img style="max-width: 100%; height: auto;" src="attachments/sign/';
    				text += sd.imgUrl || 'blank.png';
    				text += '"></p><p>所属项目：<br>';
    				text += pinfo.projName;
    				text += '</p><p>签字时间：<br>';
    				text += format(sd.signTime, 'all');
    				text += '</p>';
    				addPosMarker(map, new BMap.Point(sd.longitude, sd.latitude), title, {
    					icon: icon.icon,
    					color: icon.color,
    					border: icon.color + " 1px solid"
    				},{
    					text: text,
    					style: {
    						width: 220,
    						height: 220,
    						title: sd.menuDes ? sd.menuDes : '签字地点',
    						enableMessage: false
    					}
    				});
    			}else{
    				console.warn("项目个别签字环节缺失坐标数据");
    			}
    		} */
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
            $("#allmap").css({opacity: 1});

            renderPoints();

        },2000)

    }

    setTimeout(function () {
        getBoundary();
    }, 500);
});
</script>
</script>