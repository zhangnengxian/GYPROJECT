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
            <div class="panel panel-inverse tabs-mixin">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <ul class="nav nav-tabs">
		                <li class="active"><a href="#default-tab-1" id="arTab"  data-toggle="tab">应收应付</a></li>
		                <li class=""><a href="#default-tab-2" id="cfTab"   data-toggle="tab">实收实付</a></li>
	                </ul>
                </div>
                <div class="panel-body">
                   <div class="tab-content">
                   <div class="tab-pane fade in btn-top active" id="default-tab-1">
                    <table id="chargeArQueryTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                               <th></th>
                               <th>操作时间</th>
        			           <th>款项类型</th>
        			           <th>金额</th>
        			           <th>实收金额</th>
        			           <th>收付标志</th>
        			           <th>票据状态</th>       			           
        			           <th>流水操作人</th>
        			           <th></th>
                            </tr>
                        </thead>
                    </table>
                    </div>
                    <div class="tab-pane fade in btn-top" id="default-tab-2">
                    <table id="chargeCfQueryTable" data-attach-table="all" class="table table-striped table-bordered nowrap" width="100%">
                        <thead>
                            <tr>
                               <th></th>
       			  	           <th>操作时间</th>
        			           <th>款项类型</th>
        			           <th>金额</th>
        			           <th>收付标志</th>
        			           <th>票据状态</th>
        			           <th>收付操作人</th>
        			           <th>收款部门</th>
        			           <th></th>
                            </tr>
                        </thead>
                    </table>
                    </div>
                    </div>
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
			         <h4 class="panel-title">详情区</h4>
			    </div>
			    <div class="panel-body" id="charge_query_box"></div>
			</div>
        </div>
        <!-- end col-6 -->
    </div>
</div>

  <div id="typeSelect" class="hidden pull-left">
            <label class="control-label" for="pipeLayiWay">款项类型</label>
         
                <select class="form-control input-sm width-150" id ="costType" name="costType"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${collectionType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            
   </div> 
   <div id="cfTypeSelect" class="hidden pull-left">
            <label class="control-label" for="pipeLayiWay">款项类型</label>
         
                <select class="form-control input-sm width-150" id ="cfCostType" name="cfCostType"  >
	 		        <option value=""></option>
	                <c:forEach var="enums" items="${collectionType}">
						<option value="${enums.value}" >${enums.message}</option>
	                </c:forEach>
	            </select>
            
   </div> 
 
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    App.setPageTitle('收付查询 - 工程项目管理系统 ');
	$.getScript('projectjs/project/charge-query.js?v='+Math.random()).done(function() {
		chargeQuery.init();
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->