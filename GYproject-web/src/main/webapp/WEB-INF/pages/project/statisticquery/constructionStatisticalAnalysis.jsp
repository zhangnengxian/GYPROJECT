<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <h4 class="panel-title">各管理处施工进度统计表</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <div class="echart-box" id="projNoStates"></div>
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
                    <h4 class="panel-title">工程项目安全质量检查项目排名TOP10</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
			        <div class="echart-box" id="deductionPerMonth"></div>
                </div>
             </div>
        </div>
    </div>
	<div class="row row-v-6">
        <div class="col-xs-12">
            <div class="panel panel-inverse no-equal-height">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">近三年施工部安全质量检查对比</h4>
                </div>
                <div class="panel-body echart-panel">
			        <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
   					<div class="form-box">
		   				<form action="" class="" id="">
		   					<div class="form-group col-md-2 col-sm-2">
						    	<div>
						    		
						    		<select class="form-control input-sm" id="param"  name="param"  >
						    			<c:forEach var="dep" items="${department }">
						 		      	<option value="${dep.deptName }">${dep.deptName }</option>
						                </c:forEach>
						             </select>
						        </div>
						    </div>
					 	</form>
					</div>
			        <div class="echart-box" id="deduction"></div>
                </div>
             </div>
        </div>
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('工程统计分析 - 工程项目管理系统 ');  
   	$.getScript('assets/plugins/echarts/build/dist/echarts.js').done(function(){
		$.getScript('projectjs/project/construction-statistical-analysis.js').done(function () {
			constructionStatisticalAnalysis.init();
		});
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->