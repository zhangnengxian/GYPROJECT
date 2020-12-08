/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/
var searchData={},
	touchRecordDetail = function(){
	 	projId = getProjectInfo().projId;
	 	data = "id="+projId;
	 	f = $("#touchRecordForm");
		$.ajax({
	         type: 'POST',
	         url: 'touchRecord/touchRecordOrderDetail',
	         data: data,
	         dataType: 'json',
	         success: function (data) {
	             //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
	            var selects = f.find('select[disabled]');
	             selects.removeAttr("disabled");
	             //表单反序列化填充值
	             f.deserialize(data);
	             //有disabled属性的下拉菜单元素对象重新添加禁用属性
	             selects.attr("disabled","disabled");
	             detailCallBack(data);
	             $('#constructionUnit').val(getProjectInfo().cuName);
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             console.warn("cgetDetail() -> 详情查询失败");
	         }
	     });
		 
},

detailCallBack = function(data){
	if(data.croId !== "" && data.croId!== null){
//		$("#conContentTable,#noContentTable,#suContentTable").cgetData(false);
		//将按钮隐藏掉，表单不可改
		$("#touchRecordForm,#conContentTable,#noContentTable,#suContentTable,#conForm,#noForm,#suForm").toggleEditState(false).styleFit();
		$(".saveBtn").addClass("hidden");
		$(".updataBtn").removeClass("hidden");
		$("#projId").val(data.projId);
	}else{
//		$("conContentTable,#noContentTable,#suContentTable").cgetData(false);
		//可维护
		$("#touchRecordForm,conContentTable,#noContentTable,#suContentTable,#conForm,#noForm,#suForm").toggleEditState(true).styleFit();
		$(".saveBtn").removeClass("hidden");
		$(".updataBtn").addClass("hidden");
		
	}
	     var allTime1=format(data.instructionTime,"all");
	     var allTime2=format(data.touchEndDate,"all");
		$("#touchEndDate").val(allTime2);
		$("#instructionTime").val(allTime1);
		
	showReport();
	queryCheckRole();
};
/*tableCallBack = function(){
	$('#conContentForm').toggleEditState(false);
	$('#noContentForm').toggleEditState(false);
	$('#suContentForm').toggleEditState(false);
	showReport();
},
tableCallBack1 = function(){
	$('#conContentForm').toggleEditState(true);
	$('#noContentForm').toggleEditState(true);
	$('#suContentForm').toggleEditState(true);
	showReport();
};*/


var conContentTable,
    noContentTable,
    suContentTable,
    /**
     * 查询条件
     */
   TsearchData = {"projId":getProjectInfo().projId},
   
/**
 * 初始化施工要求内容列表
 */
 conContentTableInit= function() {
		"use strict";
	    if ($('#conContentTable').length !== 0) {
	    	conContentTable= $('#conContentTable').on( 'init.dt',function(){
	    		$('#conContentTable').hideMask();
		        //input 方法
		        inputMonitor();
	        }).DataTable({
	        	language: language_CN,
	            lengthMenu: [18],
	            dom: 'Brt',
	            buttons: [],
	            /*ajax: 'projectjs/accept/json/scale.json',*/
	            //启用服务端模式，后台进行分段查询、排序
	            serverSide:true,
	           
	            ajax: {
	            	url: 'touchRecord/queryConContent',
	            	type:'post',
	            	data: function(d){
	                   	$.each(TsearchData,function(i,k){
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
	            },//自适应
	            columns: [
		  			{"data":"des"},
		  			{"data":"isReady",className:"text-right"},
		  			{"data":"unitType",className:"none never"}
				],
				columnDefs: [{
				    	targets: 1,
				    	/*
						 * render属性
						 * 方法携带四个参数
						 * data: 该单元格的原始数据，也就是默认显示的那些数据
						 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
						 * row: 当前行的所有原始数据
						 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
						 */
				    	render: function ( data, type, row, meta ) {
				    		if(type === "display"){
								if(data === null||data==="1"){
									var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+">" +
											   "<label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
									           "&nbsp;&nbsp;<label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
									return tdcon;
								}
								if(data === "0"){
									var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+">" +
											"<label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
							                "&nbsp;&nbsp;<label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
							        return tdcon;
								}
							}else{
								return data;
							}}
				    },{
				    	targets: 2,
						render: function ( data, type, row, meta ) {
							var crId='';
							if(row.crId!==null && row.crId!==''){
								crId=row.crId;
							}
							if(type === "display"){
								var  tdcon="<input type='hidden' name = '"+ row.connectContentId +"_unitType' id='"+ row.connectContentId +"_unitType' value="+row.unitType+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_crId' id='"+ row.connectContentId +"_crId' value="+crId+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_croId' id='"+ row.connectContentId +"_croId' value="+row.croId+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_welderId1' id='"+ row.connectContentId +"_welderId1' value="+row.welderId1+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_welder1' id='"+ row.connectContentId +"_welder1' value="+row.welder1+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_welder2' id='"+ row.connectContentId +"_welder2' value="+row.welder2+">" +
							    "<input type='hidden' name = '"+ row.connectContentId +"_welder3' id='"+ row.connectContentId +"_welder3' value="+row.welder3+">" +
							    "<input type='hidden' name = '"+ row.connectContentId +"_welderId2' id='"+ row.connectContentId +"_welderId2' value="+row.welderId2+">" +
							    "<input type='hidden' name = '"+ row.connectContentId +"_welderId3' id='"+ row.connectContentId +"_welderId3' value="+row.welderId3+">"
						return tdcon;
							}else{
								return data;
							}}	
				    }
				],
				ordering:false
	       });
	   }
	},
/**
 * 初始化天然气要求内容列表
 */
 noContentTableInit= function() {
		"use strict";
	    if ($('#noContentTable').length !== 0) {
	    	noContentTable= $('#noContentTable').on( 'init.dt',function(){
	    		$('#noContentTable').hideMask();
		        //input 方法
		        inputMonitor();
	        }).DataTable({
	        	language: language_CN,
	            lengthMenu: [18],
	            dom: 'Brt',
	            buttons: [],
	            /*ajax: 'projectjs/accept/json/scale.json',*/
	            //启用服务端模式，后台进行分段查询、排序
	            serverSide:true,
	           
	            ajax: {
	            	url: 'touchRecord/queryNoContent',
	            	type:'post',
	            	data: function(d){
	                   	$.each(TsearchData,function(i,k){
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
	            },//自适应
	            columns: [
		  			{"data":"des"},
		  			{"data":"isReady",className:"text-right"},
		  			{"data":"unitType",className:"none never"}
				],
				columnDefs: [{
				    	targets: 1,
				    	/*
						 * render属性
						 * 方法携带四个参数
						 * data: 该单元格的原始数据，也就是默认显示的那些数据
						 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
						 * row: 当前行的所有原始数据
						 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
						 */
				    	render: function ( data, type, row, meta ) {
				    		if(type === "display"){
								if(data === null||data==="1"){
									var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+"><label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
									        "&nbsp;&nbsp;<label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
									return tdcon;
								}
								if(data === "0"){
									var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+"><label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
							        "&nbsp;&nbsp;<label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
							        return tdcon;
								}
							}else{
								return data;
							}}
				    },{
				    	targets: 2,
						render: function ( data, type, row, meta ) {
							var crId='';
							if(row.crId!==null && row.crId!==''){
								crId=row.crId;
							}
							if(type === "display"){
								var  tdcon="<input type='hidden' name = '"+ row.connectContentId +"_unitType' id='"+ row.connectContentId +"_unitType' value="+row.unitType+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_crId' id='"+ row.connectContentId +"_crId' value="+crId+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_croId' id='"+ row.connectContentId +"_croId' value="+row.croId+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_welderId1' id='"+ row.connectContentId +"_welderId1' value="+row.welderId1+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_welder1' id='"+ row.connectContentId +"_welder1' value="+row.welder1+">" +
								"<input type='hidden' name = '"+ row.connectContentId +"_welder2' id='"+ row.connectContentId +"_welder2' value="+row.welder2+">" +
							    "<input type='hidden' name = '"+ row.connectContentId +"_welder3' id='"+ row.connectContentId +"_welder3' value="+row.welder3+">" +
							    "<input type='hidden' name = '"+ row.connectContentId +"_welderId2' id='"+ row.connectContentId +"_welderId2' value="+row.welderId2+">" +
							    "<input type='hidden' name = '"+ row.connectContentId +"_welderId3' id='"+ row.connectContentId +"_welderId3' value="+row.welderId3+">"
						return tdcon;
							}else{
								return data;
							}}	
				    }
				],
				ordering:false
	       });
	   }
	}, 
/**
 * 初始化监理要求内容列表
 */
 suContentTableInit= function() {
	"use strict";
    if ($('#suContentTable').length !== 0) {
    	suContentTable= $('#suContentTable').on( 'init.dt',function(){
    		$('#suContentTable').hideMask();
	        //input 方法
	        inputMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [],
            /*ajax: 'projectjs/accept/json/scale.json',*/
            //启用服务端模式，后台进行分段查询、排序
            serverSide:true,
           
            ajax: {
            	url: 'touchRecord/querySuContent',
            	type:'post',
            	data: function(d){
                   	$.each(TsearchData,function(i,k){
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
            },//自适应
            columns: [
	  			{"data":"des"},
		  		{"data":"isReady",className:"text-right"},
		  		{"data":"unitType",className:"none never"}
			],
			columnDefs: [{
			    	targets: 1,
			    	/*
					 * render属性
					 * 方法携带四个参数
					 * data: 该单元格的原始数据，也就是默认显示的那些数据
					 * type: 在datatable中数据用途类型，display-用于展示的，filter或search-用于过滤和搜索的，sort-用于排序的
					 * row: 当前行的所有原始数据
					 * meta: meta.row-可以获取该单元所在行的行索引,meta.col-可以获取该单元所在列的列索引
					 */
			    	render: function ( data, type, row, meta ) {
			    		if(type === "display"){
							if(data === null||data==="1"){
								var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+"><label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
								        "&nbsp;&nbsp;<label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
								return tdcon;
							}
							if(data === "0"){
								var  tdcon="<div class='tdInnerInput'><input class='' type='hidden' name = '"+ row.connectContentId +"_connectContentId' id='"+ row.connectContentId +"_connectContentId' value="+row.connectContentId+"><label><input class='field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='1'> 是</label>" +
						        "&nbsp;&nbsp;<label><input class='radio-checked field-editable' name='"+ row.connectContentId +"_isReady' type='radio' value='0'> 否</label></div>";
						        return tdcon;
							}
						}else{
							return data;
						}}
			    },{
			    	targets: 2,
					render: function ( data, type, row, meta ) {
						var crId='';
						if(row.crId!==null && row.crId!==''){
							crId=row.crId;
						}
						if(type === "display"){
							var  tdcon="<input type='hidden' name = '"+ row.connectContentId +"_unitType' id='"+ row.connectContentId +"_unitType' value="+row.unitType+">" +
							"<input type='hidden' name = '"+ row.connectContentId +"_crId' id='"+ row.connectContentId +"_crId' value="+crId+">" +
							"<input type='hidden' name = '"+ row.connectContentId +"_croId' id='"+ row.connectContentId +"_croId' value="+row.croId+">" +
							"<input type='hidden' name = '"+ row.connectContentId +"_welderId1' id='"+ row.connectContentId +"_welderId1' value="+row.welderId1+">" +
							"<input type='hidden' name = '"+ row.connectContentId +"_welder1' id='"+ row.connectContentId +"_welder1' value="+row.welder1+">" +
							"<input type='hidden' name = '"+ row.connectContentId +"_welder2' id='"+ row.connectContentId +"_welder2' value="+row.welder2+">" +
						    "<input type='hidden' name = '"+ row.connectContentId +"_welder3' id='"+ row.connectContentId +"_welder3' value="+row.welder3+">" +
						    "<input type='hidden' name = '"+ row.connectContentId +"_welderId2' id='"+ row.connectContentId +"_welderId2' value="+row.welderId2+">" +
						    "<input type='hidden' name = '"+ row.connectContentId +"_welderId3' id='"+ row.connectContentId +"_welderId3' value="+row.welderId3+">"
					return tdcon;
						}else{
							return data;
						}}	
			    }
			],
			ordering:false
       });
   }
},

//内容table input方法监听
inputMonitor = function(){
	setTimeout(function(){
		$(".radio-checked").attr("checked","checked");
		console.info($(".radio-checked").val());
	},1000);
};

var connectRecord = function () {
	"use strict";
    return {
        init: function () {
        	conContentTableInit();
        	noContentTableInit();
        	suContentTableInit();
        	touchRecordDetail();
        }
    };
}();