<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
                    <h4 class="panel-title">待联合验收工程列表</h4>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="waitHandleProjNo" name="projNo"  value="${projNo }"/>
                    <input type="hidden" name="loginPost" id="loginPost" value="${loginPost }"/>
                    <input type="hidden" name="corpId" id="corpId" value="${corpId }"/>
                    <input type="hidden" name="inPost" id="inPost" value="${inPost }"/>
                	<input type="hidden" name="deptDivide" id="deptDivide" value="${deptDivide }"/>
					<input type="hidden" name="qualitySafetyGroup" id="qualitySafetyGroup" value="${qualitySafetyGroup }"/>
					<input type="hidden" name="curStepId" id="curStepId" value="${curStepId }"/>
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="jointAcceptanceTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>验收状态</th>
                                <th>状态</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="">		    
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('联合验收 - 工程管理系统');

    $.getScript('projectjs/complete/joint-acceptance.js?v='+Math.random()).done(function () {
        jointAcceptance.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->