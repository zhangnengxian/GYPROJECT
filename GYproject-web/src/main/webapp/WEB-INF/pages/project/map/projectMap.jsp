<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程分布</h4>
                </div>
                <div class="panel-body">
                	<div class="map-box">
 		               	<div id="projsallmap" style="width: 100%;height: 100%;margin:0px;padding:0px"></div>
 		            </div>
                </div>
             </div>
        </div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<!-- <script include-on-mobile="false" src="http://api.map.baidu.com/api?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script> -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程分布 - 工程项目管理系统 ');
    
    window.wise=1;
    window.netSpeed=162;
    window.netType=1; 
    window.BMap_loadScriptTime = (new Date).getTime();
    
    var projectLoadTimer,
    setProjectFilter = function(data){
		clearTimeout(projectLoadTimer);
		if($("#sidebar-nav").length > 0){
			if($("#sidebar-nav").nextAll(".project-filter").length < 1) $("#sidebar-nav").after(data);
		}else{
			projectLoadTimer = setTimeout(setProjectFilter,50);
		}
	};
    
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
   	$.getScript('projectjs/project/project-map.js?v='+Math.random()).done(function () {
   		projectMap.init();
   	});
    /*
	 * 项目查询方法
	 */
	(function(){
		$.fn.projQuery = function(){
        	$(".panel-body").loadMask("查询中...");
			var t = this, 
			param = $("#projectFilterForm").serializeJson();
			if($(".projLtypeId").val() === '-1'){
				param.projLtypeId = '';
			};
			if($(".projStatusId").val() === '-1'){
				param.projStatusId = '';
			}
			param = JSON.stringify(param);
			//console.info(param);
			$.ajax({
				type: 'POST',
	            url: "projectMap/queryProjectSign",
	            data: param,
	            dataType: 'json',
	            contentType: "application/json;charset=UTF-8",
	            success: function(data) {
	            	handleMap(data);
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	            	printXHRError("projQuery", "项目列表加载失败, 请重试", jqXHR, textStatus, errorThrown);
	            }
			});
		};
	})(jQuery);
</script>
<!-- ================== END PAGE LEVEL JS ================== -->