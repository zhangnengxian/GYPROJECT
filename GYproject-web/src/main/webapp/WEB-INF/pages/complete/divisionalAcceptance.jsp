<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- divisionalAcceptancce.jsp -->
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
    <div class="row">
   		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">工程列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="haveNotAcceptance" value="${haveNotAcceptance}"/>
                	<input type="hidden" id="alreadyAcceptance" value="${alreadyAcceptance}"/>
                	<input type="hidden" name="deptDivide" id="deptDivide" value="${deptDivide }"/>
					<input type="hidden" name="qualitySafetyGroup" id="qualitySafetyGroup" value="${qualitySafetyGroup }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="divisionalAcceptanceTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                    	<thead>
                            <tr>
                                <th>分部验收申请ID</th>
                            	<th>工程ID</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>工程状态</th>
                                <th>验收状态</th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" id="content">
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			        	<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">验收信息</h4>
			    </div>
			    <div class="panel-body" id="project_accept_panel_box">
			    </div>
			</div>    
        </div>
      <!-- end col-6 -->
    </div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('分部验收 - 工程项目管理系统 ');

    $.getScript('projectjs/complete/divisional-acceptance.js?'+Math.random()).done(function () {
    	divisionalAcceptance.init();
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->