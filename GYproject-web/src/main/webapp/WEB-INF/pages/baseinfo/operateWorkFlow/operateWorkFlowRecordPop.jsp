<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- begin #content -->
<div class="infodetails">
	<input type="hidden" name="projId" id="projId" value=""/>
</div>
<div class="p-t-6 p-b-15 p-l-10">
	<table id="operateWorkFlowRecordTable" class="table table-striped table-bordered nowrap" width="100%">
   		<thead>
     		<tr>
     			<th></th>
           		<th>工程名称</th>
           		<th>步骤</th>
           		<th>审核级别</th>
           		<th>操作人</th>
           		<th>操作时间</th>
            	<th>操作状态</th>
           	</tr>
       	</thead>
	</table>
</div>
<script>
    App.restartGlobalFunction();
    //表单样式适应
    $("#operateWorkFlowRecordForm").toggleEditState(false).styleFit();
  
    var operateWorkFlowRecordTable;
    var handleoperateWorkFlowRecordPop = function() {
    	'use strict';
    	var searchData={};
    	searchData.projId=$("#projId").val();
    	searchData.originalStepId=$("#fallStepId").val();
    	if ($('#operateWorkFlowRecordTable').length !== 0) {
    		operateWorkFlowRecordTable= $('#operateWorkFlowRecordTable').on( 'init.dt',function(){
       			//默认选中第一行
        		//$(this).bindDTSelected(trSelectedBack,true);
            }).DataTable({
            	language: language_CN,
                lengthMenu: [10],
                dom: 'Brtip',
                buttons: [
                     /* { text: '增加', className: 'btn-sm btn-query business-tool-btn  addApplyBtn' },
                     { text: '修改', className: 'btn-sm btn-query business-tool-btn  updateBtn hidden' }, */
                ],
                //启用服务端模式，后台进行分段查询、排序
    			serverSide:true,
                ajax: {
                    url: 'operateWorkFlowRecord/queryOperateRecordList',
                    type:'post',
                    data: function(d){
                       	$.each(searchData,function(i,k){
                       		d[i] = k;
                       	});
                       	
                    },
                    dataSrc: 'data'
                },
                //ajax: 'projectjs/accept/json/attach.json',
                responsive: {
                	details: {
                		renderer: function ( api, rowIdx, columns ){
                			return renderChild(api, rowIdx, columns);
                		}
                	}
                },
                select: true,  //支持多选
                columns: [
                    {"data":"orId",className:"none never"}, 
    	  			{"data":"projName"}, 
    	  			{"data":"stepDes"}, 
    	  			{"data":"grade"}, 
    				{"data":"operater"},
    				{"data":"operateTime"},
    				{"data":"modifyStatusDes"}
    			],
    			columnDefs: [{
    				"targets": 0,
    			    "visible":false
    			},{
    				"targets":1,
    				//长字符串截取方法
    				render: $.fn.dataTable.render.ellipsis({
    					length: 15, 	//截取多少字符（或汉字）
    					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
    				})
    			},{
    				"targets":2,
   				 "orderable":false
   				},{
					"targets":3,
					 "render":function(data,type,row,meta){
						 if(data!=null){
							 return data+"级";
						 }else{
							 return "无";
						 }
					 }
				},{
					"targets":5,
					 "render":function(data,type,row,meta){
						 if(data!=null){
							 return format(data,"all");
						 }else{
							 return data;
						 }
					 }
				},{
					"targets":6,
					 "orderable":false
				}],
    			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
    				if(aData.overdue){
    					$(nRow).addClass("expired-proect");
    				}
    			}
            });
    	}	
    }

</script>
<!-- ================== END PAGE LEVEL JS ================== -->