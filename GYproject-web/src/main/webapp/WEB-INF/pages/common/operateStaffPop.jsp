<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="infodetails">
    <div class="clearboth form-box">
    	<!--  人员信息 -->
    	<input type="hidden" name="projId" />
    	<div class="clearboth form-box" style="margin-bottom:4px;margin-right:280px">
		<%-- <form class="form-horizontal" id="staffForm" action="/" method="POST">
			<input type="hidden"   name="post" value="${post}"/>
			<input type="hidden"   name="unitType" value="${unitType}"/>
			<input type="hidden"   name="deptId" value="${deptId}"/>
			<input type="hidden"   name="corpId" value="${corpId}"/>
	        <div class="form-group col-lg-5 col-md-5 col-sm-5">
				<label class="control-label" for="staffName"></label>
			    <div >
	                <input type="text" class="form-control input-sm"  name="staffName" placeholder="姓名"/>
	            </div>
			</div>
		</form> --%>
		</div>
    	<div class="form-group col-md-6 ">
    		<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="staffTable" >
       			<thead>
	       			<tr>
	       			    <th></th>
            			<th>编号</th>
           				<th>姓名</th>
            			<th>职务</th>
	       			</tr>
       			</thead>
       		</table>
    	</div>
    	<!-- 人员信息 end -->
        <!-- 签证记录 start-->
        <div class="form-group col-md-6 ">
	        <!-- <h3>操作人员信息</h3> -->
        	<table class="table table-hover table-striped table-bordered nowrap" width="100%" id="chooseStaffTable" >
       			<thead>
	       			<tr>
            			<th>编号</th>
           				<th>姓名</th>
            			<th>职务</th>
	       			</tr>
       			</thead>
       		</table>
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
   
    //初始化人员信息
    var accrualsData = {};
    var chargedatainit= function() {
    	"use strict";
        if ($('#staffTable').length !== 0) {
        	var projId = $("#projId").val();
        	if(projId === ""){
        		projId="-1";
        	}
        	accrualsData.projId = projId;
        	$('#staffTable').on( 'init.dt',function(){
        		$("#staffTable_filter input").attr("placeholder","姓名");
       			//默认选中第一行
      			$(this).bindDTSelected(trAccrualsBack,false);
        		$('#staffTable').hideMask();
        		visadatainit();
        		addStaffMonitor();
            }).DataTable({
            	language: {
                    url: 'js/dt-chinese.json'
                },
               lengthMenu: [10],
               dom: 'Bfrtip',
               buttons: [
					{ text: '增加', className: 'btn-sm btn-query addStaffBtn' }
               ],
              //启用服务端模式，后台进行分段查询、排序
    		   serverSide:true,
    		   select: true,  //支持多选
    		   ordering:false,
    	       ajax: {
    	    	     url: 'popup/queryStaff',
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
					{"data":"staffId",className:"none never"}, //隐藏
	  				{"data":"staffNo"}, 
					{"data":"staffName"},
					{"data":"postName",className:"text-right"}
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
    var trAccrualsBack = function(){
    	
    }
    
    var searchData = {};
    var visadatainit= function() {
    	"use strict";
        if ($('#chooseStaffTable').length !== 0) {
        	var projId = $("#projId").val();
        	if(projId === ""){
        		projId="-1";
        	}
        	searchData.owfId = trSData.operateWorkFlowTable.json.owfId;
        	$('#chooseStaffTable').on( 'init.dt',function(){
       		//默认选中第一行
      		$(this).bindDTSelected(visaBack,false);
        	$('#chooseStaffTable').hideMask();
        	deleteMonitor();
            }).DataTable({
            	language: {
                    url: 'js/dt-chinese.json'
                },
               lengthMenu: [5],
               dom: 'Brt',
               buttons: [
					{ text: '删除', className: 'btn-sm btn-warn deleteBtn' }
               ],
              //启用服务端模式，后台进行分段查询、排序
    		   serverSide:true,
    		   select: true,  //支持多选
    		   ordering:false,
    	       ajax: {
    	    	     url: 'operateWorkFlow/queryOpStaff',
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
	  				{"data":"staffNo"}, 
					{"data":"staffName"},
					{"data":"postName",className:"text-right"}
    			],
    			columnDefs: []
           });
       }
    };
    
    
    //增加监听
    var addStaffMonitor=function(){
    	$(".addStaffBtn").off("click").on("click",function(){
    		
    	})
    }
    
    var deleteMonitor=function(){
    	$(".deleteBtn").off("click").on("click",function(){
    		alert(2);
    	})
    }
    
    var visaBack=function(){
    	
    }
    
</script>
<!-- ================== END PAGE LEVEL JS ================== -->