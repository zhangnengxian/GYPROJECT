<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- projectChargeBackReport.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
		<div class="col-sm-112 col-xs-12" >
			<div class="panel panel-inverse" id="content">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
					</div>
					<h4 class="panel-title">现场施工监控</h4>
			    </div>
				<div class="panel-body">
					<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <div style="width:100%;margin:0px;">   
				        <div id="mapContainer" 
				            style="position: absolute;
				                margin:0; 
				                padding:0px;
				                top: 0;
				                bottom: 0;
				                left: 0;
				                right: 0;">
				        </div>
				        <div class="map-flow-panel" style="position: absolute; 
				        top: 100px; 
				        right: 80px; 
				        height: 28px; 
				        width: 140px; 
				        background: rgba(255,255,255,.6); 
				        border:#fff 1px solid; box-shadow: rgba(0,0,0,.4) 0 2px 7px;">
				        <p align="center" style="color:red;" id="loginSum"></p>
				        
				        </div>
				    </div>
			    </div>
			</div>
		</div>
	</div>
</div>
<script include-on-mobile="false" src="https://api.map.baidu.com/getscript?v=2.0&ak=vxX4nmaMSrjYGS8jC2g8j0F9sp32Wyr3&services=&t=20160513110936"></script>
<script>
App.restartGlobalFunction();
App.setPageTitle('报表查询 - 现场施工监控 - 工程管理系统');

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

 /* if(!_isMobile){
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
} */


$.getScript('projectjs/monitor/map-statistics.js').done(function() {
	projectMap.init();
});

</script>
