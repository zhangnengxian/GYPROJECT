var projNoLength;
if(_inApk){
	projNoLength=12;
}else{
	projNoLength=15;
}
var projId;
var searchData = {};
searchData.menuId = "110409";
var menuId="110409";
var handleContractAudit = function() {
	"use strict";
	searchData.projNo=$(".sessionprojNo").val();
    if ($('#contractAuditTable').length !== 0) {
    	$('#contractAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			//隐藏遮罩
   			$('#contractAuditTable').hideMask();
   			$("#contractAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   		
   			searchMonitor();
   			//跳转链接
    		if (crossPageBus) {
    			getSidebarMenu(11, true);
    			checkSidebarMenu(crossPageBus.hash)
    				//跳转后销毁对象
    			crossPageBus = null
    		}
   			setTimeout(function(){
   			    $("#applyCompleteDrawingTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
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
                url: 'contractModifyAudit/queryContract',
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
                {"data":"projId",visible:false},
				{"data":"conNo"},
				{"data":"projNo"},
				{"data":"projName"},
				{"data":"projectTypeDes"},
				{"data":"contributionModeDes"},
				{"data":"contractAmount","className":"text-right"},
				{"data":"signDate"},
				{"data":"mrAuditLevel"},
				{"data":"overdue",visible:false}
			],
			columnDefs: [{
				"targets":6,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets": 8,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.conId,menuId);
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":4,
				 "orderable":false
			},{
				"targets":5,
				 "orderable":false
			},{
				"targets":8,
				 "orderable":false
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//如果是false从后部开始截取，及从后部开始计数，并裁掉超出的内容。如果是true从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: projNoLength, 	//截取多少字符（或汉字）
					end: true	//如果是false从后部开始截取，及从后部开始计数，并裁掉超出的内容。如果是true从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
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
		var url = "#contractAudit/searchPopPage";
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
	searchData = $("#searchForm_contractAudit").serializeJson();
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
	$(document).off("click", ".level").on("click", ".level", function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var currentLevel = $(this).attr("data-level");
		var conId = $(this).attr("data-rid");
		var data = {"conId":conId,"currentLevel":currentLevel,"isAudit":isAudit,'projId':projId};
		$("#ajax-content").cgetContent("contractModifyAudit/auditPage",data);
	});
	
};



/** 
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
    projId=json.projId;
}

/**
 * 初始化列表
 */
var contractAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleContractAudit();
        	})
        }
    };
}();

//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid: 'contractAuditTable',
//	}
//});