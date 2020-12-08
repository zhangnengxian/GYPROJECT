<!-- projectRate.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
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
                    <h4 class="panel-title">费率列表</h4>
                </div>
                <div class="panel-body">
                    <div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="projectRateTable" class="table table-striped table-bordered nowrap">
                      <%--   <thead>
                            <tr>
                            	<th>费率类型</th>
                            	<th>费率值</th>
                            </tr>
                    	</thead>
                        <c:forEach var="projectRate" items="${projectRates}">
                            <tr>
                            	<td>监检费率</td>
                            	<td>${projectRate.inspection}</td>
                            </tr>
                            <tr>
                            	<td>监理费率</td>
                            	<td>${projectRate.supervisor}</td>
                            </tr>
                            <tr>
                            	<td>储备金费率</td>
                            	<td>${projectRate.store}</td>
                            </tr>
                         </c:forEach> --%>
               <thead>
       			<tr>
       			    <th></th>
       				<th>费率类型</th>
        			<th>费率值</th>
        			
       			</tr>
       			</thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- end col-6 -->
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse" >
			    <div class="panel-heading">
			        <div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
			        </div>
			        <h4 class="panel-title">操作区</h4>
			    </div>
			    <div class="panel-body" id="project_rate_panel_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>


<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('费率 - 工程管理系统');
    
    $.getScript('projectjs/baseinfo/project-rate.js').done(function () {
        subContract.init();
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->

</body>
</html>