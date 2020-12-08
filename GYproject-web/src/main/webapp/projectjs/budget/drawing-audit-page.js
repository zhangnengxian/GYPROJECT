
var projId=$("#projId").val();
var businessOrderId=$("#businessOrderId").val();
var drawingData={"projId":projId};	 	//图纸列表查询条件
var histSearchData={"projId":projId,"businessOrderId":businessOrderId};	//历史列表查询条件
var budgeterTable;
/**
 * 初始化待签收图纸设计信息列表
 */
var handleDrawingAudit = function() {
	"use strict";
	if ($('#drawingTable').length !== 0) {
		$('#drawingTable').on( 'init.dt',function(){
			$('#drawingTable').hideMask();
			$("#drawingTable_filter input").attr("placeholder","图号");
			//初始化审核列表
			drawingAuditHistoryInit();
			//查询监听
			searchMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
            ],
            //ajax: 'projectjs/budget/json/drawing_audit.json',
            serverSide:true,
            ajax: {
                url: 'designDrawing/queryDrawDirectory',
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
                {"data":"drawDirect"},
	  			{"data":"drawingNo"},
	  			{"data":"mapSheet"},
	  			{"data":"drawQuantity",className:"text-right"},
	  			{"data":"convertIntoNum",className:"text-right"}
			],
			columnDefs: [{
				
			}],
			ordering:false,
        });
    }
};

var searchMonitor= function(){
	//基础条件查询添加监听
	$("#drawingTable_filter input").on("change",function(){
		var drawingNo = $("#drawingTable_filter input").val();
		drawingData.drawingNo = drawingNo;
		$("#drawingTable").cgetData(false);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#drawingTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};



/**
 * 初始化审核列表
 */
var drawingAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleDrawingAudit();
        }
    };
}();

/**
 * 初始化右侧图纸审核列表
 */
var drawingAuditHistoryInit = function() {
	"use strict";
	if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'drawingSignAndAudit/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/budget/json/audit_history.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			},{
				"targets": 3,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "王绍兴";
					}else{
						return data;
					}
				},
			}]
        });
    }
};

/**
 * 加载预算员表格
 */
var budgetertableinit= function() {
	//designerData.deptId = '-1';
	"use strict";
	budgeterTable= $('#budgeterTable').on( 'init.dt',function(){
	$('#budgeterTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        //dom: 'Brt',
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'drawingSignAndAudit/queryBudgeter',
            type:'post',
            data: function(d){
               	$.each(budgeterData,function(i,k){
               		d[i] = k;
               	});
            },
            dataSrc: 'data'
        },
        //ajax:'projectjs/budget/json/budget_dispatch.json',
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,  //支持多选
        columns: [
  			{"data":"BUDGETER"},
  			{"data":"BUDGETERTASK",className:"text-right"}
  			/*{"data":"TOTALCOST",className:"text-right"}*/
	    ],
	    columnDefs: [/*{
			 'targets':2,
			 "render": function ( data, type, row, meta ) {
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
		 	}*/]
       
   });  
};