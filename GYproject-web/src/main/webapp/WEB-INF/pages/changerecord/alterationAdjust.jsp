<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" />
<link href="assets/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div id="content" class="content">
	<div class="row">
        <!-- begin col-6 -->
        <div class="col-sm-6 col-xs-12">
            <input type="hidden" id="projId" />
            <input type="hidden" id="cmId" class="addClear accBusRecordId" />
            <!-- 类型:0-变更,1-签证 -->
            <input type="hidden" id="mcType" value="0"/>
            <!-- begin panel -->
           <div class="panel panel-inverse tabs-mixin" id="">
			    <div class="panel-heading">
	               	<div class="panel-heading-btn">
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
		                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
	                </div>
	                <ul class="nav nav-tabs">
		                <li class="active"><a href="#change-tab-1" id="changeRecordTab"  data-toggle="tab">变更记录</a></li>
		                <li class=""><a href="#change-tab-2" id="visaRecordTab" data-toggle="tab">签证记录</a></li>              
	                </ul>
				</div>
			    <div class="panel-body" id="change_panel_box">
			    	<div class="tab-content">
			    		<div class="tab-pane fade in btn-top active" id="change-tab-1">
                        	<div id="changeRecordBox">
	                        	 <table id="changeRecordTable" data-attach-table="all" class="table table-striped table-bordered nowrap collectionTable" width="100%">
	                        		<thead>
			                			<tr>
			                			    <th></th>
			                				<th>变更编号</th>
			                				<th>变更类型</th>
				                			<th>变更日期</th>				                			
				                			<th>工程名称</th>
				                			<th>处理状态</th>
				                			<th>工程ID</th>
				                			<!-- <th>变更原因</th>
				                			<th></th>
				                			<th>操作部门</th> -->
			                			</tr>
		                			</thead>
		                		 </table>
                        	</div>
                        </div>
                        <div class="tab-pane fade in btn-top" id="change-tab-2">  
                        <input type="hidden" id="stepId" name="stepId" value="${stepId }">               	
                       		<div id="visaRecordBox">
                        		<table class="table table-hover table-striped table-bordered nowrap" id="visaRecordTable" width="100%">
		                			<thead>
			                			<tr>
			                			    <th></th>
			                				<th>签证日期</th>
				                			<th>工程编号</th>
				                			<th>工程名称</th>
				                			<th></th>
				                			<th>签证编号</th>
				                			<th>工程ID</th>
			                			</tr>
		                			</thead>
	                		   </table>
                       		</div>                        	
                        </div>                                                                  
			    	</div>
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
		                <li class="active"><a href="#default-tab-1" id="budgetAdjustTab"  data-toggle="tab">预算调整</a></li>
		                <li class=""><a href="#alteration_record" id="alterationInfo" data-toggle="tab">记录详情</a></li>
		                <!-- <li class=""><a href="#default-tab-4" id="quantitiesTab"  data-toggle="tab">工程量</a></li> -->
		                <!-- <li class=""><a href="#default-tab-2" id="suppContTab"   data-toggle="tab">补充协议</a></li>  -->    
		                <li class=""><a href="#default-tab-3" id="materialChangeTab"   data-toggle="tab">材料变更</a></li>           
	                </ul>
				</div>
			    <div class="panel-body" id="change_panel_box">
			    	<input type="hidden" id="cmId1" name="cmId">
			    	<div class="tab-content">
			    		<div class="tab-pane fade in btn-top active" id="default-tab-1">
                        	<div id="budgetAdjustbox"></div>
                        </div>
                        <div class="tab-pane fade in btn-top" id="alteration_record">
                        	<div id="alterationInfobox"></div>
                        </div>
                        <div class="tab-pane fade in btn-top" id="default-tab-2">
                        	<div id="suppContbox">
                        		
                        	</div>
                        </div>
                         <div class="tab-pane fade in btn-top" id="default-tab-4">
                        	<div id="quantitiesbox">
                        	  <input type="hidden" id="costType1" value="${costType}"/>
			                  <input  type="hidden"  id="projId1" />
	                        	<table id="quanlitiesTable" class="table table-striped table-bordered nowrap" width="100%">
				                        <thead>
				                            <tr>
				                                <th>序号</th>
				                                <th>分项名称</th>
				                                <th>金额</th>
				                            </tr>
				                        </thead>
	                    			</table>	
                        	</div>
                        </div>
                        <div class="tab-pane fade in btn-top" id="default-tab-3">
				    
				        <div id="materialChangebox">
				        	<form id="exportExcel" action="changeRecord/exportExcel">
				        	</form>
				          <form action="" id="materialListForm">
					         <table id="materialListTable" class="table table-striped table-bordered nowrap " width="100%">
			            		<thead>
			                		<tr>
				                		<th></th>			                		 
				                		<th>材料名称</th>
						                <th>材料型号</th>
						                <th>单位</th>
						                <th width="120">调整量</th>
						                						            			               					    
			                		</tr>
				               	</thead>
			    	  	  	 </table>
		    	  	  	 </form>
           	       </div>
           	       </div>
                                             
			    	</div>
			    </div>
			</div>
        </div>
    </div>
    
</div>
<%-- <div id="costTypeSelect" class="hidden pull-left">
	<label class="control-label" for="pipeLayiWay">收费类别: </label>
	<select class="form-control input-sm field-editable width-150" name="costType" >
        <c:forEach var="enums" items="${costType}">
            <option value="${enums.value}">${enums.message}</option>
        </c:forEach>
       
    </select>
</div> --%>
<script>
    App.restartGlobalFunction();
    App.setPageTitle('变更签证调整 - 工程管理系统');
    $.getScript('projectjs/changerecord/budget-adjust-total.js?v='+Math.random()).done(function () {
   		getRate();
	    $.getScript('projectjs/changerecord/alteration-adjust.js?v='+Math.random()).done(function () {
	    	changeRecord.init();
	    });
   });

</script>
<!-- ================== END PAGE LEVEL JS ================== -->