
var projId=$("#projId").val();
var drawingData={"projId":projId};	 	//图纸列表查询条件
var histSearchData={"projId":projId};	//历史列表查询条件
var budgeterTable;
/**
 * 初始化待签收图纸设计信息列表
 */
var handlewhiteMapAudit = function() {
	"use strict";
	if ($('#drawingTable').length !== 0) {
		$('#drawingTable').on( 'init.dt',function(){
			$('#drawingTable').hideMask();
			$("#drawingTable_filter input").attr("placeholder","名称");
			//初始化审核列表
			whiteMapAuditHistoryInit();
			//查询监听
			searchMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
            ],
            ajax: 'projectjs/budget/json/budget_audit.json',
            /*serverSide:true,
            ajax: {
                url: 'designDrawing/queryDrawDirectory',
                type:'post',
                data: function(d){
                   	$.each(drawingData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },*/
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"no"},
	  			{"data":"name"},
	  			{"data":"cost",className:"text-right"}
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
 * 预算详述
 */
var handleBudgetDetial = function(){
	var url = 'budgetResultRegister/queryPro';
	console.info($('#projId1').val());
	var projId =$('#projId1').val();
	var data = "id="+projId;
	var f = $("#budgetSumForm");
	 $.ajax({
         type: 'POST',
         url: url,
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
           //  $("#cuName").val(getProjectInfo().cuName);
         },
         error: function (jqXHR, textStatus, errorThrown) {
             console.warn("cgetDetail() -> 详情查询失败");
         }
     });
}
var detailCallBack =function(data){
	 $("#stepId").val(getStepId());
	 $("#alPath").val($("#projNo").val()+"/预算");
	 $(".searchButton").attr("href","/accessoryCollect/openCoFile?busRecordId="+$("#budgetId").val());
     $(".searchButton").removeClass("hidden");
     $(".Search_Button").addClass("hidden");
	if(data.drawName){
		$(".hasVal").removeClass("hidden");
		$(".noVal").addClass("hidden");
		$(".noVal+#filePreviews tr").remove();
	}else{
		$(".hasVal").addClass("hidden");
		$(".noVal").removeClass("hidden");
	}
	//加载预算审批历史
	whiteMapAuditHistoryInit(data);
}

/**
 * 初始化审核列表
 */
var whiteMapAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleBudgetDetial();
        }
    };
}();


/**
 * 初始化右侧审核列表
 */
var whiteMapAuditHistoryInit = function(data) {
	"use strict";
	$('#businessOrderId').val(data.budgetId);
	$('#projId1').val(data.projId);
	$('#projNo1').val(data.projNo);
	histSearchData.businessOrderId=data.budgetId;
	histSearchData.projId=data.projId;
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
                url: 'whiteMapAudit/queryManageRecord',
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