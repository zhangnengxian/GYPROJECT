/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/


var handleMap = function(data) {
	"use strict";
	
	//计算地图中心点、缩放级别
	var dataLength = data.length,
	
	map = new BMap.Map("mapContainer");
	$("#loginSum").text("当前登录人数:"+dataLength);
	if(!dataLength){
		map.centerAndZoom(new BMap.Point(103.388611,35.563611), 5);
		$(".panel-body").hideMask();
	}else{
		var centerX = 0,
		centerY = 0,
		d0 = data[0],
		maxX = parseFloat(d0.XAXIS),
		maxY = parseFloat(d0.YAXIS),
		minX = parseFloat(d0.XAXIS),
		minY = parseFloat(d0.YAXIS);
		console.info('d0.xaxis'+d0.XAXIS);
		
		console.info('d0.yaxis'+d0.YAXIS);
		//console.info(d0.signs);
		for(var i=1; i<dataLength; i++){
			var d = data[i];
			//console.info(d.projLatitude + " - " + i);
			if(!d.projLongitude) continue;
			maxX = maxX < parseFloat(d.XAXIS) ? parseFloat(d.XAXIS) : maxX;
			maxY = maxY < parseFloat(d.YAXIS) ? parseFloat(d.YAXIS) : maxY;
			minX = minX > parseFloat(d.XAXIS) ? parseFloat(d.XAXIS) : minX;
			minY = minY > parseFloat(d.YAXIS) ? parseFloat(d.YAXIS) : minY;
		}
		//console.info(maxX + " / " + maxY + " / " + minX + " / " + minY);
		centerX = (maxX + minX) / 2;
		centerY = (maxY + minY) / 2;
		//console.info(centerX + " / " + centerY);
		
	    //百度地图API功能
		//创建Map实例
		//初始化地图,设置中心点坐标和地图级别43.8312840000,87.6235480000
		map.centerAndZoom(new BMap.Point(centerX, centerY), getZoom(map, maxX, minX, maxY, minY));
		//添加地图类型控件
		map.addControl(new BMap.MapTypeControl());
		map.addControl(new BMap.NavigationControl());
		map.enableScrollWheelZoom(true);
		
		for(var i=0; i<dataLength; i++){
			drawProjMarker(data[i]);
		}
		
		$(".panel-body").hideMask();
	}
	
	//绘制项目位置标记和签字标记
	function drawProjMarker(pinfo){
		console.log('pinfo.XAXIS..'+pinfo.XAXIS);
		console.log('pinfo..yAXIS.'+pinfo.YAXIS);
		
		if(!pinfo.XAXIS || !pinfo.YAXIS){
			$("#projsallmap").html('<p class="m-t-20 m-l-20">该项目缺少坐标数据, 无法查看地图位置</p>');
			console.warn("项目缺失坐标数据");
			return false;
		}
		var point = new BMap.Point(pinfo.XAXIS, pinfo.YAXIS);
		//oval = new BMap.Circle(point, 100, {fillColor:"", fillOpacity: 0.1, strokeColor:"red", strokeWeight:1, strokeOpacity:0.2});
		var text =  '<img class="pull-left img-circle" alt="Cinque Terre" width="100" height="100" style="margin-right:10px" src=';
		text += 'attachments/photo/'+pinfo.PHOTOURL;
		text += '></img><p style="padding-left:120px;line-height:15px" >登陆人:';
		text += pinfo.STAFFNAME;
		text += '</p><p style="padding-left:120px;line-height:15px">职务:';
		text += pinfo.POST;
		text += '</p><p style="padding-left:120px;line-height:15px">部门:';
		text += pinfo.DEPTNAME;
		text += '</p><p style="padding-left:120px;line-height:15px">登录时间:';
		text += format(pinfo.OPERATETIME, 'MDHms');
		text += '</p>';
		
		addPosMarker(map, point, '登录位置', '', {
			text: text,
			style: {
				width: 300,
				height: 160,
				title: "<b>登录详情</b>",
				enableMessage: false
			}
		},{
			pid: pinfo.OPERATELOGID
		});		
		//map.addOverlay(oval);

		/*//绘制签字坐标点
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
		}*/
	}
	
};

var projectQuery = function(){
	var post = {};
	post.finishedDateStart = new Date().getFullYear() + '-01-01';
	post = JSON.stringify(post);
	//console.info(post);
	$.ajax({
        type: 'POST',
        url:"mapStatistics/queryMapStatistics",
        data: post,
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        success: function(data) {
        	console.info('data1....'+data);
        	/*var json=JSON.parse(data);
        	console.info('json1....'+json);*/
        	handleMap(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	printXHRError("projectQuery", "项目列表加载失败, 请重试", jqXHR, textStatus, errorThrown);
        }
	});
}

var projectMap = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$(".panel-body").loadMask("查询中...");
        	setTimeout(projectQuery, 1000);
        }
    };
}();



