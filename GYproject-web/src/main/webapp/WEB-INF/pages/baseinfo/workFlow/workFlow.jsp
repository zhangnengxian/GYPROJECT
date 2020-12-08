<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- grooveRecord.jsp -->
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
                    <h4 class="panel-title">流程列表</h4>
                </div>
               	<div class="panel-body" id="">
             		<div class="toolBtn f-r p-b-10" >
	                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole addWfBtn">增加</a>
	                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole saveWfBtn">保存</a>
	                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole deleteWfBtn">删除</a>
				    </div>
				     <div class="clearboth form-box">
				     		<div class="form-group col-sm-6 col-xs-12 hidden">
					            <label class="control-label" for="">所有类型</label>
					            <div>
						    		<select class="form-control input-sm field-editable" id="allType"  name="allType" >
						                <c:forEach var="enums" items="${allType}">
											   	<option value="${enums.correlateInfoId}" data-scaleType="${enums.scaleType}">${enums.correlateInfoDes}</option>
						                </c:forEach>
						             </select>
						        </div>
					        </div>
              				<form class="form-horizontal" id="workFlowForm" data-parsley-validate="true" action="">
	                       	<input type="hidden" id="wfId" name="wfId">
	                       	
	                       	<div class="form-group col-sm-6 col-xs-12">
					            <label class="control-label" for="">流程类型</label>
					            <div>
						    		<select class="form-control input-sm field-editable" id="typeId"  name="typeId" >
						 		      	<option value="-1">--请选择--</option>
						                <c:forEach var="enums" items="${typeId}">
											   	<option value="${enums.value}">${enums.message}</option>
						                </c:forEach>
						             </select>
						        </div>
					        </div>
					        <div class="form-group col-sm-6 col-xs-12 hidden assistTypeId">
					            <label class="control-label" for="">辅助流程类型</label>
					            <div>
						    		<select class="form-control input-sm field-editable" id="assistTypeId"  name="assistTypeId" >
						 		      	<option value="-1">--请选择--</option>
						                <c:forEach var="enums" items="${assistTypeId}">
											   	<option value="${enums.value}">${enums.message}</option>
						                </c:forEach>
						             </select>
						        </div>
					        </div>
	                       	
	                       	
	                       	<div class="form-group col-sm-6 col-xs-12">
					            <label class="control-label" for="">分公司</label>
					            <div>
						    		<select class="form-control input-sm field-editable" id="corpId"  name="corpId" >
						 		      	<option value="-1">--请选择--</option>
						                <c:forEach var="enums" items="${corp}">
											   	<option value="${enums.deptId}">${enums.deptName}</option>
						                </c:forEach>
						             </select>
						        </div>
					        </div>
	                       	<div class="form-group col-sm-6 col-xs-12">
					            <label class="control-label" for="">工程类型</label>
					             <div>
						    		<select class="form-control input-sm field-editable" id="projType"  name="projType" >
						 		      	<option value="-1">--请选择--</option>
						 		      	<c:forEach var="enums" items="${projType}">
											   	<option value="${enums.projTypeId}">${enums.projConstructDes}</option>
						                </c:forEach>
						             </select>
						        </div>
					        </div>
					        <div class="form-group col-sm-6 col-xs-12">
					            <label class="control-label" for="">出资类型</label>
					            <div>
						            <select class="form-control input-sm field-editable" id="contributionCode"  name="contributionCode" >
						 		      	<option value="-1">--请选择--</option>
						             </select>
					             </div>
					        </div>
	                        <table data-attach-table="all"  id="workFlowTable" class="table table-hover table-striped table-bordered nowrap" width="100%">
	                			<thead>
		                			<tr>
		                				<th>流程Id</th>
		                				<th>工程类型编码</th>
		                				<th>分公司Id</th>
		                				<th>出资类型Id</th>
			                			<th>公司名称</th>
			                			<th>工程类型</th>
			                			<th>出资类型</th>
			                			<th>流程类型</th>
			                			<th>流程名称</th>
		                			</tr>
	                			</thead>
                			</table>
	                    </form>
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
			        <h4 class="panel-title">流程步骤</h4>
			    </div>
			    <div class="panel-body" id="">
                	<div class="toolBtn f-r p-b-10" >
	                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole addWfrBtn">增加</a>
	                	<a href="javascript:;" class="btn btn-confirm btn-sm m-l-5 checkRole saveWfrbtn">保存</a>
	                    <a href="javascript:;" class="btn btn-warn btn-sm m-l-5 checkRole deleteWfrBtn">删除</a>
					</div>
				    <div class="clearboth form-box">
                   		<form class="form-horizontal" id="workFlowRecordForm" data-parsley-validate="true" action="">
	                       	<div class="form-group col-sm-6 col-xs-12">
					            <label class="control-label" for="">步骤名称</label>
					            <div>
						            <select class="form-control input-sm field-editable" id="actionCode"  name="actionCode" >
						 		      	<option value="-1">--请选择--</option>
						                <c:forEach var="enums" items="${allType}">
											   	<option value="${enums.correlateInfoId}" data-scaleType="${enums.scaleType}">${enums.correlateInfoDes}</option>
						                </c:forEach>
						             </select>
					             </div>
					        </div>
	                       	<table id="workFlowRecordTable" class="table table-striped table-bordered nowrap" width="100%">
	                            <thead>
	                                <tr>
	                                	<th>步骤编码</th>
	                                    <th>步骤序号</th>
	                                    <th>步骤名称</th>
	                                </tr>
	                            </thead>
	                       	</table>
                       	</form>
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
    App.setPageTitle('流程设置 - 工程项目管理系统 ');
    //表单样式
    $("#workFlowForm").toggleEditState(true).styleFit();
    $("#workFlowRecordForm").toggleEditState(true).styleFit();
	
    $.getScript('projectjs/baseinfo/work-flow.js?v='+Math.random()).done(function () {
    	workFlow.init();
	});
    $("#projType").change(function(){
    	$("#contributionCode").empty();
    	var selectEl = $("#projType"),
        index = selectEl[0].selectedIndex,
        selectOption = selectEl[0].options[index];
    	var data = $(selectOption).attr("value");
    	$.ajax({
    		type: 'post',
    		url: 'workFlow/querySubcompany?id='+data,
    		dataType: 'json',
    		success: function(data){
    	        $("#contributionCode").append('<option value="-1">--请选择--</option>');
    			$.each(data,function(n, v){
    	            $("#contributionCode").append('<option value='+v.correlatedInfoId+'>' + v.correlatedInfoDes + '</option>');
    	        });
    		},
    		error: function(data){
    			console.warn("出资类型选框查询失败");
    		}
    	});
    });
    
    $("#typeId").change(function(){
    	if($(this).val()=="1"){
    		//主流程 
    		$(".assistTypeId").addClass("hidden");
    		
    		var selectOprions = $("#actionCode option");
       	 	$.each(selectOprions,function(i,e){
       	 		//查询主流程的枚举 默认0为主流程
	       		if($(this).attr("data-scaleType")=="0"){
	       			//remove
	       			$(this).removeClass("hidden");
	       		}else{
	       			$(this).addClass("hidden");
	       		}
       		})
    	}else{
    		$(".assistTypeId").removeClass("hidden");
    	}
    })
	
    //辅助流程
    $("#assistTypeId").change(function(){
    	var scale=$(this).val();
    	
    	var selectOprions = $("#actionCode option");
    	 $.each(selectOprions,function(i,e){
    		if($(this).attr("data-scaleType")==scale){
    			//remove
    			$(this).removeClass("hidden");
    		}else{
    			$(this).addClass("hidden");
    		}
    		
    	})
    	
    	
    })
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->
