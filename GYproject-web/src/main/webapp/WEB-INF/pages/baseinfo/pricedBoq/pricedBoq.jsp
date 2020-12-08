<!-- pricedBoq.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h4 class="panel-title">工程量标准列表</h4>
                    
                </div>
                <div class="panel-body">
                	<div id="costTypeDes" class=" pull-left">
						<select id="costType" class="form-control input-sm field-editable width-150" name="costType" >
							<option value=""></option>
       						<c:forEach var="enums" items="${costTypeDes}">
            					<option value="${enums.value}">${enums.message}</option>
        					</c:forEach>
    					</select>
    				</div>
                    <div class="mask-html text-center"><div><i class="fa fa-4x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div>
                    </div>
                    <table id="pricedBoqTable" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th></th>
                            	<th>造价类型</th>
                                <th>分部分项工程名称</th>
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
			    <div class="panel-body">
				    <div id="priced-boq_panel_box">
				    </div>
			    	<div class = "pricedBoqTable1 hidden" >
			   			<table id="pricedBoqTable1" class="table table-striped table-bordered nowrap" width="100%">
		                      <thead>
		                          <tr>
		                          	<th></th>
		                          	<th>造价类型</th>
		                            <th>分部分项工程名称</th>
	            					<th width="60">单价</th>
	            					<th width="60">单位</th>
		                          </tr>
		                      </thead>
		             	</table>
		         	</div>
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
    App.setPageTitle('工程量标准 - 工程管理系统');
    
    $.getScript('projectjs/baseinfo/priced-boq.js').done(function () {
        subContract.init();
    });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->

</body>
</html>