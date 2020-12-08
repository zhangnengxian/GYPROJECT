<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse tabs-mixin ">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                	</div>
					<h4 class="panel-title">工程概览</h4>
                </div>
                <div class="panel-body" style="background: #f8f8f8;">
					<div class="row pagination-toolbar">
						<div class="col-sm-12">
					 	 	<form class="form-horizontal pull-left m-t-5">
						 		<div class="form-group m-t-2">
							        <label class="control-label" for="">每页显示：</label>
				        			<div>
										<select name="pageSize" class="page-size-select form-control bg-white">
											<option value="40">40</option>
											<option value="80">80</option>
											<option value="120">120</option>
										</select>
									</div>
								</div>
							</form>
							<ul class="pagination pagination-without-border pull-right m-t-5 m-r-2 m-b-10"></ul>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="project-scan-grid"></div>
						</div>
					</div>
					<div class="row pagination-toolbar">
						<div class="col-sm-12">
					 	 	<form class="form-horizontal pull-left m-t-5">
						 		<div class="form-group m-t-2">
							        <label class="control-label" for="">每页显示：</label>
				        			<div>
										<select name="pageSize" class="page-size-select form-control bg-white">
											<option value="40">40</option>
											<option value="80">80</option>
											<option value="120">120</option>
										</select>
									</div>
								</div>
							</form>
							<ul class="pagination pagination-without-border pull-right m-t-5 m-r-2 m-b-0"></ul>
						</div>
					</div>
					<div class="project-pop-panel hidden">
						<div class="ep-con-box">
							<div class="tool-btns p-b-15" data-pid="">
								<a href="javascript:;" class="btn btn-sm btn-white project-pop-close pull-right"><i class="fa fa-close"></i></a>
								<a href="javascript:;" class="btn btn-sm btn-default">查看项目</a>
								<a href="javascript:;" class="btn btn-sm btn-query">工程施工</a>
								<a href="javascript:;" class="btn btn-sm btn-confirm">工程报验</a>
								<a href="javascript:;" class="btn btn-sm btn-send">统计数据</a>
							</div>
							<div class="project-map">
								<ul class="slide">
									<li class="p-slide" id="projectMap"></li>
									<li class="item-cover p-slide">
										<img class="cover-image" src="images/summary/mlj.jpg">
									</li>
									<li class="item-cover p-slide">
										<img class="cover-image" src="images/summary/tjdfyj.jpg">
									</li>
									<li class="item-cover p-slide">
										<img class="cover-image" src="images/summary/wkjy.jpg">
									</li>
									<li class="p-slide" id="echartBox"></li>
								</ul>
								<ul class="p-slide-control">
									<li class="p-map"><i class="fa ion-map"></i></li>
									<li class="images"><i class="fa ion-images"></i></li>
									<li class="images"><i class="fa ion-images"></i></li>
									<li class="images"><i class="fa ion-images"></i></li>
									<li class="p-echart"><i class="fa ion-stats-bars"></i></li>
								</ul>
							</div>
							<div class="project-detail">
								<h4 class="project-title">米拉吉(克拉玛依路店)</h4>
								<p><span><i class="fa fa-map-marker"></i> 项目位置：</span><span class="project-location">乌鲁木齐沙依巴克区克拉玛依东街北巷176号</span></p>
								<p><span><i class="fa ion-cube"></i> 工程规模：</span><span class="project-scale">锅炉 2吨/3台，餐厅2座</span></p>
								<p><span><i class="fa ion-flag text-danger"></i> 施工承建：</span><span class="project-build">新疆立通通用设备制造有限公司</span></p>
								<p><span><i class="fa ion-clock text-info"></i> 预计工期：</span><span class="project-expected-time">3个月</span></p>
								<p><span><i class="fa fa-cny text-warning"></i> 项目预算：</span><span class="project-budget">36.5万元</span></p>
								<p><span><i class="fa fa-shield text-primary"></i> 监理公司：</span><span class="project-supervise">新疆金石建设项目管理有限公司</span></p>
							</div>
							<div class="project-schedule">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script type="text/javascript" src="https://api.map.baidu.com/getscript?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程概述 - 工程项目管理系统 ');
    
    window.wise=1;
    window.netSpeed=162;
    window.netType=1; 
    window.BMap_loadScriptTime = (new Date).getTime();
    
	if(!_isMobile){
		//请求项目检索工具栏
		loadProjectFilter = function(){
			$.ajax({
				type: 'GET',
	            url: 'projectSummary/filter',
	            dataType: 'html',
	            success: function(data) {
	            	setProjectFilter(data);
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                console.warn('getGridData() -> 项目列表检索工具栏加载失败, 请重试! - 工程项目管理系统');
	                console.warn(jqXHR);
	                console.warn(textStatus);
	                console.warn(errorThrown);
	            }
			});
		}();
	}
    
    //项目列表容器
	var p_grid = $(".project-scan-grid"), 
	pop_panel = $(".project-pop-panel"), 
	pid = '', 
	pJson = {}, 
	cover_img_folder = 'images/summary/',
	project_picture='attachments/',
	//获取项目数据
	getGridData = function(){
		p_grid.closest(".panel-body").loadMask("查询中...");
		var size = $('[name="pageSize"]:eq(0)').val(), post = {};
		post.size = size;
		post.currentPage = "1";
		post.finishedDateStart = new Date().getFullYear() + '-01-01';
		post.menuId = '1001';
		post = JSON.stringify(post);
		$.ajax({
            type: 'POST',
            url: "projectSummary/queryProjectPicture",
            data: post,
            dataType: 'json',
            contentType: "application/json;charset=UTF-8",
            success: function(data) {
            	if(typeof data !== 'object'){
            		console.warn("getGridData() -> 返回数据格式错误，期望json对象");
            		return false;
            	}
            	initPagination(data.recordsTotal, size, 1);
            	drawGrid(fixNull(data.data));
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.warn('getGridData() -> 项目列表加载失败, 请重试! 以下为错误详情：');
                console.warn(jqXHR);
                console.warn(textStatus);
                console.warn(errorThrown);
            }
		});
	}(),
	//列出项目数据
	drawGrid = function(data){
		var item = '';
		p_grid.html("");
		if(!jsonLength(data)){
			p_grid.html('<h4 class="text-center m-t-20 m-t-20">没有记录！</h4>');
			p_grid.hideMask();
			return;
		}
		$.each(data,function(i, n){
			item = '<div title="' + n.projName + '" class="grid-item projDiv' + (n.projStatusId === "1036" ? " complete" : "") + '" ';
			item += 'data-pid="' + n.projId + '"><h4><i class="fa fa-flag-o"></i> ';
			item += n.projName + '</h4><div class="grid-con"><div class="item-cover"><img src="' ;
			if(n.pictureUrl){
				item += project_picture+n.pictureUrl;	
			}else{
				item += cover_img_folder+"zwtp.png";
			}
			
			item += '" title="" alt=""></div><p title="' + n.projAddr + '"><span><i class="fa fa-map-marker"></i> 位置：</span>';
			item += n.projAddr + '</p><p title="' + n.projScaleDes + '"><span><i class="fa ion-cube"></i> 规模：</span>';
			item += n.projScaleDes + '<p></div></div>';
			p_grid.append(item);
		});
		pJson = data.projects;
		//bindClickPop();
		bindClickNewPop();
		bindPopClose();
		p_grid.hideMask();
	},
	bindClickNewPop = function(){
		p_grid.children(".grid-item").on("click",function(){
			pid = $(this).attr("data-pid");
			var url = "#projectView/detailPage?projId="+pid;
			var popoptions = {
				title: '工程信息详述',
				content: url,
				ahide: true,
				ccolor: '#59727D',
				ctext: '确定',
				size:'detail',
				nocache: true,
				icon: 'fa-file-text'
			};
			//加载弹屏
			$("body").cgetPopup(popoptions);
			$(this).addClass("active").siblings().removeClass("active");
		});
		
		$(document).on("click", "#ajax-content, #header, #sidebar", function(e){
			if($(e.target).is(".grid-item") || $(e.target).closest(".grid-item").length) return false;
			$(".grid-item").removeClass("active");
		});
		
	},
	//绑定项目点击事件弹出详情
    bindClickPop = function(){
		p_grid.children(".grid-item").on("click",function(){
			pid = $(this).attr("data-pid");
			//获取当前项目的数据
			var itemJson = {};
			$.each(pJson,function(i,n){
				if(parseInt(n.projId) === parseInt(pid)){
					itemJson = n;
					return false;
				}
			});

			//如果弹窗已打开则仅关闭
			if(pop_panel.is(".open")){
				pop_panel.removeClass("open");
				p_grid.children(".grid-item").removeClass("active");
				return false;
			}
			$(this).addClass("active").siblings().removeClass("active");
			//初始化项目详情弹窗
			popInit(itemJson);
		});
	},
	bindPopClose = function(){
		pop_panel.find(".project-pop-close").on("click", function(){
			pop_panel.removeClass("open");
			p_grid.children(".grid-item").removeClass("active");
		});
	},
	bindPopSlideCtrl = function(){
		pop_panel.find(".p-slide").eq(0).fadeIn();
		pop_panel.find(".p-slide-control li").eq(0).addClass("active").siblings().removeClass("active");
		pop_panel.find(".p-slide-control li").off("mouseenter touchstart").on("mouseenter touchstart", function(){
			if($(this).is(".active")) return false;
			var indx = $(this).index();
			$(this).addClass("active").siblings().removeClass("active");
			pop_panel.find(".p-slide").stop(true, true).fadeOut().eq(indx).fadeIn();
		});
	},
	popInit = function(json){
		pop_panel.removeClass("complete");
		//if(json.projStatusDes === "10" && json.percent === "100") pop_panel.addClass("complete");
		pop_panel.find(".project-map").loadMask("图册初始化...","2");
		
		//赋值data-pid属性
		pop_panel.find(".tool-btns").attr("data-pid",json.projId);
		
		//初始化图册
		var images = json.projectCover;
		pop_panel.find(".slide .item-cover, .p-slide-control .images").remove();
		$.each(images,function(i, n){
			//最多输出四张工程图片
			if(i > 3) return false;
			var item = '<li class="item-cover p-slide"><img class="cover-image" src="' + cover_img_folder;
			item += n.imageName + '" title="' + n.imageTitle + '" alt="' + n.imageTitle + '"';
			item += '></li>';
			pop_panel.find("#echartBox").before(item);
			pop_panel.find(".p-slide-control .p-echart").before('<li class="images"><i class="fa ion-images"></i></li>');
		});
		
		//赋值项目详述信息
		var detail = pop_panel.find(".project-detail");
		detail.find(".project-title").text(json.projName);
		detail.find(".project-location").text(json.projAddr);
		detail.find(".project-scale").text(json.projScaleDes);
		detail.find(".project-build").text(json.projectBuild);
		detail.find(".project-expected-time").text(json.projTimeLimit);
		detail.find(".project-budget").text(json.budgetTotalCost);
		detail.find(".project-supervise").text(json.projectSupervise);
		
		pop_panel.addClass("open");
		
		//渲染进度控件
		renderSchedule(json.projectSchedule,function(){
			//console.info("项目进度渲染完成!");
		});
		
		//渲染统计图表
		renderChart(json.projectChart,function(){
			//console.info("项目数据统计图表渲染完成!");
		});
		
		//渲染工程项目地图
		renderMap(json,function(){
			pop_panel.find(".project-map").hideMask();
			
			//console.info("项目地图位置渲染完成!");
		});
		bindPopSlideCtrl();
	},
	renderSchedule = function(json,callback){
		
		if(json === undefined || json === "" || jsonLength(json) < 1){
			pop_panel.find(".project-schedule").hide();
		}else{

			//进度初始化逻辑
			
			callback && callback();
		}
	},
	renderChart = function(json,callback){
		
		if(json === undefined || json === "" || jsonLength(json) < 1){
			pop_panel.find(".p-echart, #echartBox").hide();
		}else{
			
			//图标初始化逻辑
			
			callback && callback();
		}
	},
	renderMap = function(json,callback){
		
		//地图初始化
		if(json === undefined || json === "" || jsonLength(json) < 1){
			pop_panel.find(".p-echart, #echartBox").hide();
		}else{
			var map = new BMap.Map(pop_panel.find("#projectMap").attr("id"));
			map.centerAndZoom(new BMap.Point(json.projLatitude, json.projLongitude), 16);
			map.addControl(new BMap.MapTypeControl());
			map.setCurrentCity(json.projectCity);
			map.enableScrollWheelZoom(true);
			
			var myIcon = new BMap.Icon("images/summary/bz.png", new BMap.Size(20,27)),
			marker = new BMap.Marker(new BMap.Point(json.projLatitude, json.projLongitude),{icon:myIcon});
			map.addOverlay(marker);
			
			callback && callback();
		}
	},
	//设置项目检索工具栏
	projectLoadTimer,
	setProjectFilter = function(data){
		clearTimeout(projectLoadTimer);
		if($("#sidebar-nav").length > 0){
			if($("#sidebar-nav").nextAll(".project-filter").length < 1) $("#sidebar-nav").after(data);
		}else{
			projectLoadTimer = setTimeout(setProjectFilter,50);
		}
	},
	
	initPagination = function(total, size, p){
		var pagination = $("ul.pagination"),
		pages = Math.ceil(total/size),
		pageSize = $('[name="pageSize"]'),
		p = parseInt(p),
		addPage = function(a){
			pagination.append('<li class="' + (a === p ? "active" : "") + '"><a href="javascript:;">' + a + '</a></li>');
		};
		pagination.html("");
		if(pages < 11){
			for(var i = 1; i < pages + 1; i++){
				addPage(i);
			}
		}else{
			pagination.append('<li class="' + (p < 7 ? "hidden " : "") + 'start"><a href="javascript:;" data-page="1">«</a></li>');
			if(p < 7){
				for(var i = 1; i < 11; i++){
					addPage(i);
				}
			}else if(p > (pages - 6)){
				for(var i = pages - 9; i < pages + 1; i++){
					addPage(i);
				}
			}else{
				for(var i = p - 4; i < p + 5; i++){
					addPage(i);
				}
			}
			pagination.append('<li class="' + (pages - 6 < p ? "hidden " : "") + 'end"><a href="javascript:;" data-page="' + pages + '">»</a></li>');
		}
		
		pagination.find("li").not(".active, .disabled").off("click").on("click",function(){
			$(this).projQuery();
		});
		
		pageSize.val(size);
		
		pageSize.off("change").on("change",function(){
			pageSize.val($(this).val());
			$(".proj-query-btn").projQuery();
		});
		
	};

	/*
	 * 项目查询方法
	 */
	(function(){
		$.fn.projQuery = function(){
			p_grid.closest(".panel-body").loadMask("查询中...");
			var t = this, 
			size = $('[name="pageSize"]:eq(0)').val(), 
			p = t.is(".proj-query-btn") ? "1" : t.is(".pagination li") ? t.is(".pagination li.start, .pagination li.end") ? t.find("a").attr("data-page") : t.find("a").text() : $(".pagination-toolbar:eq(0) .pagination li.active a").text(),
		    param = $("#projectFilterForm").serializeJson();
			if($(".projLtypeId").val() === '-1'){
				param.projLtypeId = '';
			};
			if($(".projStatusId").val() === '-1'){
				param.projStatusId = '';
			}
			param.size = size;
			param.currentPage = p;
			param.menuId = '1001';
			param = JSON.stringify(param);
			$.ajax({
				type: 'POST',
	            url: "projectSummary/queryProjectPicture",
	            data: param,
	            dataType: 'json',
	            contentType: "application/json;charset=UTF-8",
	            success: function(data) {
	            	if(typeof data !== 'object'){
	            		console.warn("projQuery() -> 返回数据格式错误，期望json对象");
	            		return false;
	            	}
	            	initPagination(data.recordsTotal, size, p);
	            	//console.log("分段查询data..."+JSON.stringify(data));
	            	drawGrid(fixNull(data.data));
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                console.warn('projQuery() -> 项目列表查询失败, 请重试! 以下为错误详情：');
	                console.warn(jqXHR);
	                console.warn(textStatus);
	                console.warn(errorThrown);
	            	p_grid.closest(".panel-body").hideMask();
	            }
			});
		};
	})(jQuery);
	
	$(".pagination li").not(".active, .disabled").off("click").on("click",function(){
		$(this).projQuery();
	});
	
</script>
<!-- ================== END PAGE LEVEL JS ================== -->