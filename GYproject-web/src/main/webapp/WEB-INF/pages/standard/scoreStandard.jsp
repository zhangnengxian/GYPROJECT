<!-- scoreStandard.jsp -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->

<div id="content" class="content">
	<div class="row">
		<!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
       	<div class="panel panel-inverse tabs-mixin" id="">
			<div class="panel-heading">
               	<div class="panel-heading-btn">
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                </div>
                <h4 class="panel-title">标准列表</h4>
               
			</div>
            <div class="panel-body">
            	 <div id="caiIdDes" class=" pull-left">
            	 <input type="hidden" id="ssId" name="ssId"/>
                	<select id="deptName" class="form-control input-sm field-editable width-150" name="deptName" >
						<option value=""></option>
       					<c:forEach var="enums" items="${department}">
            				<option value="${enums.deptId}">${enums.deptName}</option>
        				</c:forEach>
    				</select>
    			</div>
               	<div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <div class="tab-content">
                   	<div class="tab-pane fade active in btn-top" id="default-tab-1">
                       	<table id="scoreStandardTable" class="table table-striped table-bordered nowrap" width="100%">
                            <thead>
                                <tr>
                                	<th></th>
                                    <th>部门</th>
                                    <th>评分项</th>
                                    <th>分值</th>
                                </tr>
                            </thead>
                       	</table>
                   	</div>
                   	</div>
               </div>
		</div>
	</div>
	    <div class="col-sm-6 col-xs-12">
			<div class="panel panel-inverse" id="">
            	<div class="panel-heading">
	                <div class="panel-heading-btn">
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
	                    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
                    <h4 class="panel-title">维护区</h4>
              </div>
              <div class="panel-body" id="accessory_item_panel_box"></div>
          	</div>
		</div>	    	        
	</div>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('资料标准 - 工程管理系统');
    $('#planAuditForm').styleFit();
    
    $.getScript('projectjs/standard/score-standard.js').done(function () {
        scoreStandard.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->