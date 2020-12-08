
var searchData = {};
var handleContractAudit = function() {
	"use strict";
    if ($('#contractAuditTable').length !== 0) {
    	$('#contractAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			//隐藏遮罩
   			$('#contractAuditTable').hideMask();
   			$("#contractAuditTable_filter input").attr("placeholder","工程编号");
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
                url: 'subContract/querySubAudit',
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
				{"data":"scNo",responsivePriority:3},
				{"data":"projNo",responsivePriority:4},
				{"data":"projName",responsivePriority:1},
				{"data":"scAmount","className":"text-right",responsivePriority:5},
				{"data":"scSignDate",responsivePriority:7},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" },
				{"data":"projId",className:"none never" }
			],
			columnDefs: [{
				"targets":3,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets": 5,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.scId);
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":6,
				 "visible":false
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#subContract/subAuditSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#contractAuditTable_filter input").on("change",function(){
		searchData.projNo = $("#contractAuditTable_filter input").val();
		//传入false  则不选中行
		$("#contractAuditTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#contractAuditTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	
	searchData = $("#searchForm_subContractAudit").serializeJson();
	searchData.projNo = $("#contractAuditTable_filter input").val();
    $("#contractAuditTable").cgetData(false, function(){
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
		var currentLevel = $(this).attr("data-level");
		var scId = $(this).attr("data-rid");
		var data = {"scId":scId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("subContract/auditPage",data);
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
var contractAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleContractAudit();
        }
    };
}();

//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid: 'contractAuditTable',
//	}
//});