
var searchData = {};
var menuId="110618";
var handleConnectGasAudit = function() {
	"use strict";
	searchData.projNo=$(".sessionprojNo").val();
    if ($('#gasAuditTable').length !== 0) {
    	$('#gasAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#gasAuditTable').hideMask();
   			$("#gasAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
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
                url: 'subContractAudit/querySubContractAuditList',
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
				{"data":"projName",responsivePriority:7},
				{"data":"projScaleDes",responsivePriority:8},
				{"data":"projAddr",responsivePriority:5},
				{"data":"scScope",responsivePriority:6},
				{"data":"scAmount",responsivePriority:9},
                {"data":"operateDate",responsivePriority:10},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"scId",className:"none never" }
			],
			columnDefs: [{
				"targets": 8,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){				
						var html = getAuditLevelHtml(data,row.level,row.scId,menuId);
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":8,
				 "orderable":false
			},{
				 "targets": 9,
				 "visible":false
			 },
			 {
					"targets":1,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
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
		var url = "#subContractAudit/subContractAuditSearchPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#gasAuditTable_filter input").on("change",function(){
		searchData.projNo = $("#gasAuditTable_filter input").val();
		//传入false  则不选中行
		$("#gasAuditTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		});  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#gasAuditTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	searchData = $("#searchForm_survey").serializeJson();
	searchData.projNo = $("#gasAuditTable_filter input").val();
    $("#gasAuditTable").cgetData(false, function(){
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
		var scId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"scId":scId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("subContractAudit/auditPage",data);
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
var subConnectAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleConnectGasAudit();
        	})
        }
    };
}();
