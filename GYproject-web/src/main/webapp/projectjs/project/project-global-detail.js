var searchData={};//查询条件
/**
 * 加载工程列表
 */
var handleProjectGlobalDetail = function() {
	"use strict";
	$("#scaleTab").tab('show');
	/*if($("#loginDeptDivide").val()==$("#customerServiceCenter").val()||$("#loginDeptDivide").val()==$("#marketDevelopmentDepartment").val()||$("#loginDeptDivide").val()==$("#gasCompany").val()){
		$("#contractTab,#changeTab,#accessoryListTab").removeClass("hidden");
	}else{
		$("#contractTab,#changeTab,#accessoryListTab").addClass("hidden");
		if($("#businessPartnersType").val()==$("#constructionUnit").val()||$("#businessPartnersType").val()==$("#supervisionUnit").val()){
			$("#changeTab").removeClass("hidden");
		}else{
			$("#changeTab").addClass("hidden");
		}
	}*/
	//隐藏配置属性
	if($("#hiddenConfig").val()!='' && $("#hiddenConfig").val()!=undefined &&  $("#hiddenConfig").val()!=null ){
		$("."+$("#hiddenConfig").val()).addClass("hidden");
	}
	if($("#staffRemoveClass").val()!='' && $("#staffRemoveClass").val()!=undefined &&  $("#staffRemoveClass").val()!=null ){
		$("."+$("#staffRemoveClass").val()).removeClass("hidden");
	}
},

trSelectedBack = function(v, i, index, t, json){
	
},


/**
 * 初始化工程列表
 */
projectGlobalDetail = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleProjectGlobalDetail();
        }
    };
}();

var accessorySearchData = {};
var accessoryTableInit= function() {
	"use strict";
	$('#accessoryListTable').on( 'init.dt',function(){
		$('#accessoryListTable').hideMask();
		//资料查看文件
    /*	$(".Search_Button").off("click").on("click",function(){
    	     var data = {};
    	     data.fpath = $(this).attr("data-row");
    		 $.ajax({
    			url:'accessoryCollect/openFile',
    			type:'post',
    			data:data,
    			success:function(data){
	    		    if(data == "nofile"){
	    		    	$("body").cgetPopup({
		    		    	title: "提示信息",
		    		    	content: "文件不存在! <br>",
		    		    	accept: popClose2,
		    		    	chide: true,
		    		    	icon: "fa-exclamation-circle"
		    		    });  		    	
	    		    }
    		    }
    		});
    	});*/
    }).DataTable({
    	language: language_CN,
        lengthMenu: [18],
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'projectView/queryAccessoryList',
            type:'post',
            data: function(d){
               	$.each(accessorySearchData,function(i,k){
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
        select: true,  //支持多选
        columns: [
            {"data":"stepId"},
  			{"data":"alName"},
  			{"data":"alTypeId"},
  			{"data":"alOperateTime"},
  			{"data":"alOperateCsr"},
  			{"data":"alId"}
  			
		],
		columnDefs: [{
			targets: 5,
			render: function (data, type, row, meta) {
				if(type === "display"){
					var type=row.alTypeId;
					console.info("type--"+type);
					var  tdcon='<a target="_blank" class="Search_Button" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+"&type="+type+'">查看</a>';
					return tdcon;
				}else{
					return data;
				}
			}
		}]
   });
};
/**
 * 材料清单
 */
var materialListTableInit= function() {
	"use strict";
	$('#materialListTable').on( 'init.dt',function(){
		$('#materialListTable').hideMask();
		//资料查看文件
    /*	$(".Search_Button").off("click").on("click",function(){
    	     var data = {};
    	     data.fpath = $(this).attr("data-row");
    		 $.ajax({
    			url:'accessoryCollect/openFile',
    			type:'post',
    			data:data,
    			success:function(data){
	    		    if(data == "nofile"){
	    		    	$("body").cgetPopup({
		    		    	title: "提示信息",
		    		    	content: "文件不存在! <br>",
		    		    	accept: popClose2,
		    		    	chide: true,
		    		    	icon: "fa-exclamation-circle"
		    		    });  		    	
	    		    }
    		    }
    		});
    	});*/
        //导出按钮监听事件
        exportBtnMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [18],
        dom: 'Brtip',
        buttons: [
            { text: '导出', className: 'btn-sm btn-query business-tool-btn exportBtn' },
        ],
        ajax: {
            url: 'projectView/queryMaterialList',
            type:'post',
            data: function(d){
               	$.each(accessorySearchData,function(i,k){
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
        select: true,  //支持多选
        columns: [
         {"data":"materialName"},
	     {"data":"materialSpec"},
	     {"data":"materialUnit"},
	     {"data":"materialNum", "className": "text-right"},
	     {"data":"getAnum", "className": "text-right"},
	     /*{"data":"inspectionAnum", "className": "text-right"},*/
	     {"data":"useAnum", "className": "text-right"},
	     {"data":"flagDes"},
		],
		columnDefs: [//{
			//targets: 5,
			//render: function (data, type, row, meta) {
				//if(type === "display"){
				//	var type=row.alTypeId;
					//console.info("type--"+type);
					//var  tdcon='<a target="_blank" class="Search_Button" data-row="' + data + '" href="/accessoryCollect/openFile?id='+data+"&type="+type+'">查看</a>';
					//return tdcon;
				//}else{
				//	return data;
				//}
			//}
		//}
    ]
   });
};

var drawingData = {};
var drawingTableInit= function() {
	"use strict";
	$('#drawingTable').on( 'init.dt',function(){
		$('#drawingTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [18],
        dom: 'Brtip',
        buttons: [
        ],
        serverSide:true,
        ajax: {
            url: 'projectView/queryDrawDirectory',
            type:'post',
            data: function(d){
               	$.each(drawingData,function(i,k){
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
        select: true,  //支持多选
        columns: [
  			{"data":"drawingNo"},
  			{"data":"drawDirect"},
			{"data":"drawQuantity",className:"text-right"}
	    ],
	    columnDefs: []
   });  
};

/**监听点击 工程明细 标签页*/
$("#scaleTab").on("shown.bs.tab",function(){
	$("#projectScale").cgetContent("projectView/projectDetailBase",'',projectBaseDetailBack);
});
var projectBaseDetailBack = function(){
	/*if(!trSData.projectGlobalViewListTable){*/
		var projId = $("#projId").val();
		var f1 = $("#detail_projectForm");
		var f2 = $("#detail_projectForm1");
		$.ajax({
            type: 'POST',
            url: 'projectView/projectDetail',
            data: 'projId='+projId,
            dataType: 'json',
            success: function (data) {
                //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                var selects1 = f1.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
                selects1.removeAttr("disabled");
                f1.deserialize(fixNull(data));
                f1.initFixedNumber();
                //有disabled属性的下拉菜单元素对象重新添加禁用属性
                selects1.attr("disabled","disabled");
                f1.parents(".panel-inverse").find(".panel-heading").hideloading();
                
               //获取表单中有disabled属性的下拉菜单元素对象, 移除其disabled属性
                var selects2 = f2.find('select[disabled], [type="radio"][disabled], [type="checkbox"][disabled]');
                selects2.removeAttr("disabled");
                f2.deserialize(fixNull(data));
                f2.initFixedNumber();
                //有disabled属性的下拉菜单元素对象重新添加禁用属性
                selects2.attr("disabled","disabled");
                f2.parents(".panel-inverse").find(".panel-heading").hideloading();
                
				if(data.projLongitude && data.projLatitude){
					$(".view-location").removeClass("hidden");
				}
                //终止的工程，显示退单原因
				if(data.backReason!='' && data.backReason!=null && data.backReason!='null'){
					$(".backReason").removeClass("hidden");
				}else{
					$(".backReason").addClass("hidden");
				}
				if(data.backReason=='5'){
					$(".backRemarks").removeClass("hidden");
				}else{
					$(".backRemarks").addClass("hidden");
				}
				if(data.acceptAuditDate!='' && data.acceptAuditDate!=null && data.acceptAuditDate!='null'){
					$(".acceptAuditDate").removeClass("hidden");
				}else{
					$(".acceptAuditDate").addClass("hidden");
				}
                if(data.isBidding!='' && data.isBidding!=null && data.isBidding!='null'){
                    $(".isBidding").removeClass("hidden");
                }else{
                    $(".isBidding").addClass("hidden");
                }
            }
        });
	/*}*//*else{
		var pinfo = trSData.projectGlobalViewListTable;
		pinfo.t.cgetDetail('detail_projectForm','','','');
		pinfo.t.cgetDetail('detail_projectForm1','','',aback);
		
		if(pinfo.json.projLongitude && pinfo.json.projLatitude){
			$(".view-location").removeClass("hidden");
		}
	}*/
		/*if($("#loginDeptDivide").val()==$("#customerServiceCenter").val()||$("#loginDeptDivide").val()==$("#marketDevelopmentDepartment").val()||$("#loginDeptDivide").val()==$("#gasCompany").val()){
			$(".display-authority").removeClass("hidden");
		}else{
			$(".display-authority").addClass("hidden");
		}*/
		$("#detail_projectForm1").styleFit();
}
/**监听点击 附件清单 标签页*/
$("#accessoryListTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	accessorySearchData.projId = projId;
	if($.fn.DataTable.isDataTable('#accessoryListTable')){
		//初始化过
		$("#accessoryListTable").cgetData(false);//列表重新加载
	}else{
		accessoryTableInit();
	}
});

/**
 * 监听点击 材料清单 标签页
 */
$("#materialTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	accessorySearchData.projId = projId;
	if($.fn.DataTable.isDataTable('#materialListTable')){
		//初始化过
		$("#materialListTable").cgetData(false);//列表重新加载
	}else{
		materialListTableInit();
	}
});
/*var aback=function(){
	var pinfo = trSData.projectGlobalViewListTable;
	var data={"projId":pinfo.json.projId};
	$.ajax({
		tyep:'POST',
		url:'/projectAccept/viewProjectApplication',
		data:data,
		success:function(data){
			console.info('data....'+data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
	        console.warn("ajax queryScaleDetail...fail");
	    }
	})
}*/

/**监听点击 勘察信息 标签页*/
$("#surveyInfoTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	$("#surveyInfo").cgetContent("projectView/projectDetailSurvey",{"projId":projId});
});

/**监听点击 勘察信息 标签页*/
$("#designInfoTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	$("#designInfo").cgetContent("projectView/projectDetailDesignInfo",{"projId":projId});
});

/**设计信息---图纸列表*/
var drawingTableInitFunction = function(){
	var projId = $("#projId").val();
	drawingData.projId = projId;
	if($.fn.DataTable.isDataTable('#drawingTable')){
		//初始化过
		$("#drawingTable").cgetData(false);//列表重新加载
	}else{
		drawingTableInit();
	}
};
/**监听点击 预算 标签页*/
$("#budgetTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	$("#budget").cgetContent("projectView/projectDetailBudget",{"projId":projId},costSummaryTableInitFunction);
});

/**监听点击 施工进度 标签页*/
$("#constructionScheduleTab").on("shown.bs.tab",function(){
	//console.log("施工进度。。。。");
	var projId = $("#projId").val();
	$("#constructionSchedule").cgetContent("projectView/projectDetailconsSchedule",{"projId":projId});
});

var costSummaryData = {};
var costSummaryTableInit= function() {
	"use strict";
	$('#costSummaryTable').on( 'init.dt',function(){
		$('#costSummaryTable').hideMask();
		//查询监听
		summarySearchMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query costSummarySearchBtn'}
        ],
        serverSide:true,
        ajax: {
            url: 'projectView/queryCostSummary',
            type:'post',
            data: function(d){
               	$.each(costSummaryData,function(i,k){
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
        select: true,  //支持多选
        columns: [
  			/*{"data":"pcsNum"},*/
  			{"data":"costName"},
  			{"data":"costTypeDes"},
			{"data":"rate",className:"text-right"},
			{"data":"amount",className:"text-right"},
			{"data":"costDes"}
	    ],
	    columnDefs: [{
			"targets": 3,
			"render": function ( data, type, row, meta ) {
				if(data!==null && data!==""){
					return data.toFixed(2);
				}
				return "";
			},
		}]
   });  
};
/**预算---工程费用列表初始化*/
var costSummaryTableInitFunction = function(){
	var projId = $("#projId").val();
	costSummaryData.projId = projId;
	if($.fn.DataTable.isDataTable('#costSummaryTable')){
		//初始化过
		$("#costSummaryTable").cgetData(false);	//列表重新加载
	}else{
		costSummaryTableInit();
	}
};
/**预算---条件查询监听*/
var summarySearchMonitor = function(){
	//查询按钮弹出屏查询
	$(".costSummarySearchBtn").on("click",function(){
		costSummaryData = $("#costSummarySearchForm").serializeJson();
		var projId = $("#projId").val();
		costSummaryData.projId = projId;
		$("#costSummaryTable").cgetData(false);	//列表重新加载
	});
};

/**监听点击 施工合同 标签页*/
$("#contractTab").on("shown.bs.tab",function(){
	$("#contract").cgetContent("projectView/projectDetailContractPage");
});

/**监听点击 变更签证 标签页*/
$("#changeTab").on("shown.bs.tab",function(){
	$("#change").cgetContent("projectView/projectChangePage");
});

/**监听点击 施工计划 标签页*/
$("#constructionPlanTab").on("shown.bs.tab",function(){
	
	$("#constructionPlan").cgetContent("projectView/projectDetailPlanPage",'',constructionPlanCallBack);
});
var constructionPlanCallBack = function(){
	if($("#loginDeptDivide").val()==$("#customerServiceCenter").val()||$("#loginDeptDivide").val()==$("#marketDevelopmentDepartment").val()||$("#loginDeptDivide").val()==$("#gasCompany").val()){
		$(".display-authority").removeClass("hidden");
	}else{
		$(".display-authority").addClass("hidden");
	}
	$("#detail_planEstablishform").styleFit();
}
/**监听点击 分包协议 标签页*/
$("#subcontractTab").on("shown.bs.tab",function(){
	$("#subcontract").cgetContent("projectView/projectDetailSubcontractPage");
});

/**分包协议表格初始化*/
var subcontractData = {};
var subcontractTableInit= function() {
	"use strict";
	$('#subcontractTable').on( 'init.dt',function(){
		
		$('#subcontractTable').hideMask();
		//默认选中第一行
		$(this).bindDTSelected(subcontractTabletrSelectedBack,true);
		//查询监听
		subcontractSearchMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query subcontractSearchBtn'}
        ],
        serverSide:true,
        ajax: {
            url: 'projectView/querySubcontract',
            type:'post',
            data: function(d){
               	$.each(subcontractData,function(i,k){
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
        select: true,  //支持多选
        columns: [
  			{"data":"scNo"},
			{"data":"managementOffice"},
			{"data":"scType"},
			{"data":"scPlannedTotalDays"},
			{"data":"scSignDate"}
	    ],
	    columnDefs: []
   });  
};

var subcontractTabletrSelectedBack = function(v, i, index, t, json){
	trSData.subcontractTable.t.cgetDetail('subContractDetailform','','','');
}

/**分包协议列表*/
var subcontractTableInitFunction = function(){
	var projId = $("#projId").val();
	subcontractData.projId = projId;
	if($.fn.DataTable.isDataTable('#subcontractTable')){
		//初始化过
		$("#subcontractTable").cgetData(false);	//列表重新加载
	}else{
		subcontractTableInit();
	}
};
/**分包协议---条件查询监听*/
var subcontractSearchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".subcontractSearchBtn").on("click",function(){
		subcontractData = $("#subcontractSearchForm").serializeJson();
		var projId = $("#projId").val();
		subcontractData.projId = projId;
		$("#subcontractTable").cgetData(false);	//列表重新加载
	});
}

/**监听点击 分包质量 标签页*/
$("#subQuantitiesTab").on("shown.bs.tab",function(){
	$("#subQuantities").cgetContent("projectView/projectDetailSubQuantitiesPage");
});

/**分包质量表格初始化*/
var subQuantitiesData = {};
var subQuantitiesTableInit= function() {
	"use strict";
	$('#subQuantitiesTable').on( 'init.dt',function(){
		$('#subQuantitiesTable').hideMask();
		//默认选中第一行
		$(this).bindDTSelected(subQuantitiesTabletrSelectedBack,true);
		//查询监听
		subQuantitiesSearchMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query subQuantitiesSearchBtn'}
        ],
        serverSide:true,
        ajax: {
            url: 'projectView/querySubQuantities',
            type:'post',
            data: function(d){
               	$.each(subQuantitiesData,function(i,k){
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
        select: true,  //支持多选
        columns: [
  			{"data":"sqBranchProjName"},
			{"data":"sqUnit"},
			{"data":"sqLabourPrice",className:"text-right"},
			{"data":"sqNum"},
			{"data":"progressAnum"}
	    ],
	    columnDefs: [{
			"targets": 2,
			"render": function ( data, type, row, meta ) {
				if(data!==null && data!==""){
					return data.toFixed(2);
				}
				return "";
			}	    	
	    }]
   });  
};
var subQuantitiesTabletrSelectedBack = function(v, i, index, t, json){
	trSData.subQuantitiesTable.t.cgetDetail('subQuantitiesDetailform','','','');
}
/**分包协议列表*/
var subQuantitiesTableInitFunction = function(){
	var projId = $("#projId").val();
	subQuantitiesData.projId = projId;
	if($.fn.DataTable.isDataTable('#subQuantitiesTable')){
		//初始化过
		$("#subQuantitiesTable").cgetData(false);	//列表重新加载
	}else{
		subQuantitiesTableInit();
	}
};
/**分包协议---条件查询监听*/
var subQuantitiesSearchMonitor = function(){
	
	//查询按钮弹出屏查询
	$(".subQuantitiesSearchBtn").on("click",function(){
		subQuantitiesData = $("#subQuantitiesSearchForm").serializeJson();
		var projId = $("#projId").val();
		subQuantitiesData.projId = projId;
		$("#subQuantitiesTable").cgetData(false);	//列表重新加载
	});
};

/**监听点击 管理记录 标签页*/
$("#manageRecordTab").on("shown.bs.tab",function(){
	$("#manageRecord").cgetContent("projectView/projectDetailManageRecordPage");
});

/**监听点击 工程进度 标签页*/
$("#projectScheduleTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	$("#projectSchedule").cgetContent("projectView/projectDetailSchedulePage",{"projId":projId});
});

/**管理记录表格初始化*/
var manageRecordData = {};
var manageRecordTableInit= function() {
	"use strict";
	$('#manageRecordTable').on( 'init.dt',function(){
		$('#manageRecordTable').hideMask();
		//查询监听
		manageRecordSearchMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query manageRecordSearchBtn'}
        ],
        serverSide:true,
        ajax: {
            url: 'projectView/queryManageRecord',
            type:'post',
            data: function(d){
               	$.each(manageRecordData,function(i,k){
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
        select: true,  //支持多选
        columns: [
  			{"data":"stepDes"},
			{"data":"docTypeDes"},
			{"data":"mrTime"},
			{"data":"mrAuditLevel"},
			{"data":"mrResultDes"}
	    ],
	    columnDefs: [{
			"targets": 2,
			"render": function ( data, type, row, meta ) {
				if(type === "display" && data!==null && data!==""){
					return format(data,'all');
				}else{
					return data;
				}
			},
		}]
   });  
};
/**管理记录列表*/
var manageRecordTableInitFunction = function(){
	var projId = $("#projId").val();
	manageRecordData.projId = projId;
	if($.fn.DataTable.isDataTable('#manageRecordTable')){
		//初始化过
		$("#manageRecordTable").cgetData(false);	//列表重新加载
	}else{
		manageRecordTableInit();
	}
};
/**管理记录---条件查询监听*/
var manageRecordSearchMonitor = function(){
	//查询按钮弹出屏查询
	$(".manageRecordSearchBtn").on("click",function(){
		manageRecordData = $("#manageRecordSearchForm").serializeJson();
		var projId = $("#projId").val();
		manageRecordData.projId = projId;
		$("#manageRecordTable").cgetData(false);	//列表重新加载
	});
};

/**监听点击 操作记录 标签页*/
$("#operateRecordTab").on("shown.bs.tab",function(){
	$("#operateRecord").cgetContent("projectView/projectDetailoperateRecordPage");
});

/**操作记录表格初始化*/
var operateRecordData = {};
var operateRecordTableInit= function() {
	"use strict";
	$('#operateRecordTable').on( 'init.dt',function(){
		$('#operateRecordTable').hideMask();
		//查询监听
		operateRecordSearchMonitor();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
            { text: '查询', className: 'btn-sm btn-query operateRecordSearchBtn'}
        ],
        serverSide:true,
        ajax: {
            url: 'projectView/queryOperateRecord',
            type:'post',
            data: function(d){
               	$.each(operateRecordData,function(i,k){
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
        select: true,  //支持多选
        columns: [
  			{"data":"STEPNAME"},
			{"data":"OPERATETIME"},
			{"data":"STAFFNAME"},
			{"data":"DEPTNAME"}
	    ],
	    columnDefs: [{
			"targets": 1,
			"render": function ( data, type, row, meta ) {
				if(type === "display" && data!==null && data!==""){
					//console.log("日期转换:"+format(data,'all'));
					return format(data,'all');
				}else{
					return data;
				}
			},
		}]
   });  
};
/**操作记录列表*/
var operateRecordTableInitFunction = function(){
	var projId = $("#projId").val();
	operateRecordData.projId = projId;
	if($.fn.DataTable.isDataTable('#operateRecordTable')){
		//初始化过
		$("#operateRecordTable").cgetData(false);	//列表重新加载
	}else{
		operateRecordTableInit();
	}
};
/**操作记录---条件查询监听*/
var operateRecordSearchMonitor = function(){
	//查询按钮弹出屏查询
	$(".operateRecordSearchBtn").on("click",function(){
		operateRecordData = $("#operateRecordSearchForm").serializeJson();
		var projId = $("#projId").val();
		operateRecordData.projId = projId;
		$("#operateRecordTable").cgetData(false);	//列表重新加载
	});
};

//导出按钮监听事件
var exportBtnMonitor = function(){
    $(".exportBtn").off("click").on("click",function(){
        var projId = accessorySearchData.projId;
        window.location ="projectView/exportExcel?projId="+projId;
    })
};

/**监听点击 接口同步 标签页*/
/*
$("#serviceTab").on("shown.bs.tab",function(){
	var projId = $("#projId").val();
	serviceSearchData.projId = projId;
	if($.fn.DataTable.isDataTable('#serviceTable')){
		//初始化过
		$("#serviceTable").cgetData(false);//列表重新加载
	}else{
		serviceTableInit();
	}
	
});*/

/**接口标签页*/
$("#serviceTab").on("shown.bs.tab",function(){
	$("#serviceInfo").cgetContent("projectView/serviceListPop");
});