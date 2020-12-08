<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row row-v-6">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse no-equal-height">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程项目数量年报</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <div class="echart-box" id="projNumByYear"></div>
                </div>
             </div>
        </div>
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse no-equal-height">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">扣分项目统计</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <div class="echart-box" id="deduction"></div>
                </div>
             </div>
        </div>
    </div>
	<div class="row row-v-6">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse no-equal-height">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">在建工程数量统计</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <div class="echart-box" id="projOnConstruction"></div>
                </div>
             </div>
        </div>
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-inverse no-equal-height">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">图表4</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <div class="echart-box" id="deductionPerMonth"></div>
                </div>
             </div>
        </div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程统计图表 - 工程项目管理系统 ');
    $.getScript('assets/plugins/echarts/build/dist/echarts.js').done(function(){
		$.getScript('projectjs/project/project-scale-statistic.js').done(function () {
			projectScaleStatistic.init();
		});
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->