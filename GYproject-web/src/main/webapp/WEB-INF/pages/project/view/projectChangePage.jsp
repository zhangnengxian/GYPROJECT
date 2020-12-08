<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
    <div class="clearboth form-box">
    	<!--  变更记录 -->
    	<input type="hidden" name="projId" />
    	<div class="form-group col-md-6 ">
    		<h3>设计变更</h3>
    		<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="changeTable" >
       			<thead>
	       			<tr>
	       			    <th></th>
              			<th>变更编号</th>
              			<th>变更类型</th>
               			<th>日期</th>
               			<th>工程名称</th>
               			<th>状态</th>
	       			</tr>
       			</thead>
       		</table>
			<hr style="height: 1px;border:none;border-bottom:1px dotted rgb(56,102,172);"/>
			<div class="panel panel-inverse" >
				<div class="panel-body" id="change_panel_box"></div>
			</div>
    	</div>
    	<!-- 变更记录 end -->
        <!-- 签证记录 start-->
        <div class="form-group col-md-6 ">
			<h3>签证记录</h3>
        	<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="visaTable" >
       			<thead>
	       			<tr>
	       			    <th>Id</th>
              			<th>签证编号</th>
               			<th>工程名称</th>
               			<th>签证日期</th>
               			<th>金额</th>
               			<th>状态</th>
	       			</tr>
       			</thead>
       		</table>
			<hr style="height: 1px;border:none;border-bottom:1px dotted rgb(56,102,172);"/>
			<div class="panel panel-inverse">
				<div class="panel-body" id="visa_panel_box"></div>
			</div>
        </div>
        <!-- 签证记录 end->
    </div>
</div>
<div class="clearboth p-t-15">
   
</div>
<!-- end #content -->

<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script>
    App.restartGlobalFunction();
    
    //隐藏loading
    $(".infodetails").hideMask();
    
    //表单样式适应
    $("#detail_contractform,#subContractDetailform").toggleEditState().styleFit();
    
    $('.datepicker-default').datepicker({
        todayHighlight: true
    });
   
  //初始化变更记录
    var accrualsData = {};
    var chargedatainit= function() {
    	"use strict";
        if ($('#changeTable').length !== 0) {
        	var projId = $("#projId").val();
        	if(projId === ""){
        		projId="-1";
        	}
        	accrualsData.projId = projId;
        	$('#changeTable').on( 'init.dt',function(){
       		//默认选中第一行
      		$(this).bindDTSelected(trAccrualsBack,false);
        	$('#changeTable').hideMask();
        	visadatainit();
            }).DataTable({
            	language: {
                    url: 'js/dt-chinese.json'
                },
               lengthMenu: [5],
               dom: 'rtip',
               buttons: [
               ],
              //启用服务端模式，后台进行分段查询、排序
    		   serverSide:true,
    		   select: true,  //支持多选
    		   ordering:false,
    	       ajax: {
    	    	     url: 'designAlteration/queryDesignAlteration',
    	             type:'post',
    	             data: function(d){
    	                   $.each(accrualsData,function(i,k){
    	                   		d[i] = k;
    	                   	});
    	                   	
    	                },
    	                dataSrc: 'data'
    	            },        
               responsive: {
               	   details: {
            		   renderer: function ( api, rowIdx, columns ){
            			   return renderChild(api, rowIdx, columns);
            		   }
            	   }
               },
               columns: [
					{"data":"cmId",className:"none never"},
					{"data":"cmNo", responsivePriority: 1},
					{"data":"changeTypeDes", responsivePriority: 2},
					{"data":"cmDate", responsivePriority: 3}, 
					{"data":"projName", responsivePriority: 4},
					{"data":"designChangeTypeDes", responsivePriority: 5}
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			}]
           });
       }
    };
    chargedatainit();
    //时间格式转换
    function changeDate(e){
    	return format(e,"all");
    }
    var trAccrualsBack = function(v, i, index, t, json){
        $("#changeForm").toggleEditState(false);//切换表单可编辑
        //加载右侧页面
        $('#change_panel_box').cgetContent('projectView/viewChangeDetailPage',{},function () {
            t.cgetDetail('changeForm','projectView/findChangeDetail','',detailCallback);
        });
    }
    
    var searchData = {};
    var visadatainit= function() {
    	"use strict";
        if ($('#visaTable').length !== 0) {
        	var projId = $("#projId").val();
        	if(projId === ""){
        		projId="-1";
        	}
        	searchData.projId = projId;
        	$('#visaTable').on( 'init.dt',function(){
       		//默认选中第一行
      		$(this).bindDTSelected(visaBack,false);
        	$('#visaTable').hideMask();
            }).DataTable({
            	language: {
                    url: 'js/dt-chinese.json'
                },
               lengthMenu: [5],
               dom: 'rtip',
               buttons: [
               ],
              //启用服务端模式，后台进行分段查询、排序
    		   serverSide:true,
    		   select: true,  //支持多选
    		   ordering:false,
    	       ajax: {
    	    	   	 url: 'engineering/queryEngineeringVisa',
    	             type:'post',
    	             data: function(d){
    	                   $.each(searchData,function(i,k){
    	                   		d[i] = k;
    	                   	});
    	                   	
    	                },
    	                dataSrc: 'data'
    	            },        
               responsive: {
               	   details: {
            		   renderer: function ( api, rowIdx, columns ){
            			   return renderChild(api, rowIdx, columns);
            		   }
            	   }
               },
               columns: [
					{"data":"evId",className:"none never"},
        			{"data":"evNo", responsivePriority: 1},
  	  				{"data":"projName", responsivePriority: 2},
  	  			    {"data":"visaDate", responsivePriority: 2},
  	  				{"data":"quantitiesTotal", responsivePriority: 3},
  	  			    {"data":"auditStateDes", responsivePriority: 4}
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				},{
					"targets": 3,
					"render":function(data,type,row,meta){
						if(data !=="" && data!==null){
							return format(data);
						}else{
							return data;
						}
					}
				},{
				 'targets':4,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	}]
           });
       }
    };
    

    var visaBack=function(v, i, index, t, json){
        $("#visaForm").toggleEditState(false);//切换表单可编辑
        //加载右侧页面
        $('#visa_panel_box').cgetContent('projectView/viewVisaDetailPage',{},function () {
            t.cgetDetail('visaForm','projectView/findVisaDetail','',detailCallback);
        });
    	
    }
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->