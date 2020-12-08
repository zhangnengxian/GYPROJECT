<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <h4 class="panel-title">变更记录列表</h4>
                </div>
                <div class="panel-body">
                	<input type="hidden" id="civilVal" value="${civilVal}">
                    <input  type="hidden" id="publicVal" value="${publicVal}">
                    <div class="mask-html text-center"><div><i class="fa fa-3x fa-spinner"></i><br><p class="text-ellipsis">加载中</p></div></div>
                    <table id="budgetAdjustTable" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                            	<th>工程ID</th>
                                <th>工程编号</th>
                                <th>工程名称</th>
                                <th>变更原因</th>
                                <th>变更日期</th>
                            </tr>
                        </thead>
                    </table>
            	</div>
        	</div>
        </div>
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12" >
            <div class="panel panel-inverse tabs-mixin" id="">
			    <div class="panel-heading">
	               	<div class="panel-heading-btn">
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
	                <ul class="nav nav-tabs">
		                <li class="active"><a href="#default-tab-1" id="budgetSumTab"  data-toggle="tab">预算总表</a></li>
		                <li class=""><a href="#default-tab-2" id="quantitiesTab"   data-toggle="tab">工程量</a></li>

	                </ul>
				</div>
			    <div class="panel-body" id="budget_register_panel_box">
			    	<input type="hidden" id="costType1" value="${costType}"/>
			        <input  type="hidden" id="projId1" />
			    	<div class="tab-content">
			    		<div class="tab-pane fade in btn-top active" id="default-tab-1">
                        	<div id="budget_sum_box"></div>
                        </div>
                        <div class="tab-pane fade in btn-top" id="default-tab-2">
                        	<div id="quantities_box">
                        		<table id="quanlitiesTable" class="table table-striped table-bordered nowrap" width="100%">
			                        <thead>
			                            <tr>
			                                <th>序号</th>
			                                <th>费用名称</th>
			                                <th>取费说明</th>
			                                <th>费率</th>
			                                <th>金额</th>
			                            </tr>
			                        </thead>
                    			</table>
                        	</div>
                        </div>
			    	</div>
			    </div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>
<div id="costTypeSelect" class="hidden pull-left">
	<label class="control-label" for="pipeLayiWay">收费类别: </label>
	<select class="form-control input-sm field-editable width-150 civil" name="costType" >
        <c:forEach var="enums" items="${civilType}">
            <option value="${enums.value}">${enums.message}</option>
        </c:forEach>
       
    </select>
	<select class="form-control input-sm field-editable width-150 public hidden" name="costType" >
        <c:forEach var="enums" items="${publicType}">
            <option value="${enums.value}">${enums.message}</option>
        </c:forEach>
    </select>
</div>

<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('预算调整 - 工程管理系统');

    $.getScript('projectjs/budget/budget-adjust.js').done(function () {
        budgetAdjust.init();
	});

</script>
<!-- ================== END PAGE LEVEL JS ================== -->