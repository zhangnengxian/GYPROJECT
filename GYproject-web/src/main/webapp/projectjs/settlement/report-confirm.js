
var searchData = {};
var handleReportConfirmAudit = function() {
    searchData.projNo=$("#waitHandleProjNo").val();

    "use strict";
	searchData.confirmState="reportConfirm";
    if ($('#reportConfirmTable').length !== 0) {
    	$('#reportConfirmTable').on( 'init.dt',function(){
   			//默认选中第一行
    		//$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#reportConfirmTable').hideMask();
   			$("#reportConfirmTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
        }).on( 'draw.dt', function () {
        	auditBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementConfirm/querySettlement',
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
            select: true,  //支持多选
            columns: [
				{"data":"projNo",responsivePriority:1},
				{"data":"projName",responsivePriority:3},
				{"data":"quantitiesTotal",responsivePriority:5},
				{"data":"sendDeclarationCost",responsivePriority:4},
				{"data":"ocoDate",responsivePriority:7},
				{"data":"subBudgeter",responsivePriority:6},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" }
			],
			columnDefs: [{
				"targets": 6,
				"render": function ( data, type, row, meta ) {
					
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.projId);
						return html;
					}else{
						return data;
					}
				},
			},
			{
				"targets":5,
                "render": function ( data, type, row, meta ) {					
					if(type==="display"){
						var html = '<image height="25px" src='+data+'>';
						return html;
					}else{
						return data;
					}
				}
			}
			,{
				"targets":7,
				 "visible":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
		/*	fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}*/
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#settlementConfirm/settleSearchPopPage?state=report";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#reportConfirmTable_filter input").on("change",function(){
		searchData.projNo = $("#reportConfirmTable_filter input").val();
		//传入false  则不选中行
		$("#reportConfirmTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#reportConfirmTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	
	searchData = $("#searchForm_reportAudit").serializeJson();
	searchData.confirmState="reportConfirm";
	searchData.projNo = $("#reportConfirmTable_filter input").val();
    $("#reportConfirmTable").cgetData(false, function(){
   		//跳转到审核页面
    	auditBtnMonitor();
	});  //列表重新加载
    
}

/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	$(".level").off("click").on("click",function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var projId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit,"confirmSort":"reportConfirm"};
		$("#ajax-content").cgetContent("settlementConfirm/auditPage",data);
	});
};

/** 
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
}

/**
 * 初始化列表
 */
var reportConfirmAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleReportConfirmAudit();
        }
    };
}();
