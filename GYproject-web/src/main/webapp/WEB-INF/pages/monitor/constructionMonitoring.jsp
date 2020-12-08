<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- constructionMonitoring.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div style="margin: -15px;">
	<div id="allmap" style="width: 960px;height: 450px;margin:0px;padding:0px"></div>
</div>
<!-- end #content -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script include-on-mobile="false" src="https://api.map.baidu.com/getscript?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
<script>
	App.restartGlobalFunction();
	App.setPageTitle('施工运行监控 - 工程统计');

	var map,
	pinfo = {};
	
	function initProjLocation(){
		
		if(trSData.projectGlobalViewListTable){
			pinfo = trSData.projectGlobalViewListTable.json;
			drawMap();
		}else if($('[name="projId"]').val()){
			$.ajax({
	            type: 'POST',
	            url: '/projectView/projectDetail',
	            data: 'projId=' + $("#projId").val(),
	            dataType: 'json',
	            success: function (data) {
	            	//console.info(data);
	            	pinfo = data;
	    			drawMap();
	            }
	        });
		}else{
			console.info("无法获取项目坐标信息");
		}
	}

	function drawMap(){
		if(!pinfo.projLongitude || !pinfo.projLatitude){
			$("#allmap").html('<p class="m-t-20 m-l-20">该项目缺少坐标数据, 无法查看地图位置</p>');
			console.warn("项目缺失坐标数据");
			return false;
		}
		// 百度地图API功能
		map = new BMap.Map("allmap");
		var point = new BMap.Point(pinfo.projLongitude, pinfo.projLatitude),
		oval = new BMap.Circle(point, 100, {fillColor:"red", fillOpacity: 0.1, strokeColor:"red", strokeWeight:1, strokeOpacity:0.2});
		map.centerAndZoom(point, 18);
		map.addControl(new BMap.NavigationControl());
		var text = '<img style="float:right;margin:4px" id="imgDemo" src="images/summary/jhjd.jpg" width="139" height="104" /><p>项目名称：';
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
		});               // 将标注添加到地图中
		//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		
		
		map.addOverlay(oval);
		
		$.ajax({
			type: 'POST',
			url: 'technologyTell/constructionWorkDetail',
			data: "id=" + pinfo.projId,
			dataType: 'json',
			success: function(data){
				//console.info(data);
				if(data.longitude && data.latitude){
					addPosMarker(map, new BMap.Point(data.longitude, data.latitude), '工程交底', {
						icon: '10.png',
						color: "#0098d3",
						border: "#0098d3 1px solid",
						offsetX: -25
					},{
						text: '<p>工程交底</p><p>' + data.cwDate + '</p>'
					});
				}else{
					console.warn("项目缺失坐标数据");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.warn("项目位置工程交底坐标获取失败");
			}
		});
		
		$.ajax({
			type: 'POST',
			url: 'drawingAudit/drawingRecordDetail',
			data: "id=" + pinfo.projId,
			dataType: 'json',
			success: function(data){
				//console.info(data);
				if(data.longitude && data.latitude){
					addPosMarker(map, new BMap.Point(data.longitude, data.latitude), '图纸会审', {
						icon: '5.png',
						color: "#ccb400",
						border: "#ccb400 1px solid",
						offsetX: -25
					},{
						text: '<p>图纸会审</p><p>' + data.drDate + '</p>'
					});
				}else{
					console.warn("项目缺失坐标数据");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.warn("项目位置图纸会审坐标获取失败");
			}
		});
		
		$.ajax({
			type: 'POST',
			url: 'touchPlan/touchPlanDetail',
			data: "id=" + pinfo.projId,
			dataType: 'json',
			success: function(data){
				//console.info(data);
				if(data.longitude && data.latitude){
					addPosMarker(map, new BMap.Point(data.longitude, data.latitude), '联合验收', {
						icon: '2.png',
						color: "#00d000",
						border: "#00d000 1px solid",
						offsetX: -25
					},{
						text: '<p>联合验收</p><p>' + data.tpDate + '</p>'
					});
				}else{
					console.warn("项目缺失坐标数据");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.warn("项目位置碰口方案坐标获取失败");
			}
		});
		
		$.ajax({
			type: 'POST',
			url: 'touchRecord/touchRecordOrderDetail',
			data: "id=" + pinfo.projId,
			dataType: 'json',
			success: function(data){
				//console.info(data);
				if(data.longitude && data.latitude){
					addPosMarker(map, new BMap.Point(data.longitude, data.latitude), '碰口记录', {
						icon: '3.png',
						color: "#16bfff",
						border: "#16bfff 1px solid",
						offsetX: -25
					},{
						text: '<p>碰口记录</p><p>' + data.tpDate + '</p>'
					});
				}else{
					console.warn("项目缺失坐标数据");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.warn("项目位置碰口记录坐标获取失败");
			}
		});
		
	}
</script>
<!-- ================== END PAGE LEVEL JS ================== -->


