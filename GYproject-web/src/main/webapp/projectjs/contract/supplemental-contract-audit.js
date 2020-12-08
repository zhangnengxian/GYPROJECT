
var searchData = {};
var handleContractAudit = function() {
	"use strict";
	searchData.projNo=$(".sessionprojNo").val();
    if ($('#supplementalContractAuditTable').length !== 0) {
    	$('#supplementalContractAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			//隐藏遮罩
   			$('#supplementalContractAuditTable').hideMask();
   			$("#supplementalContractAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
        	console.info(2018);
        	//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).on( 'draw.dt', function () {
        	console.info(2017);
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
                url: 'supplementalContractAudit/querySupplemental',
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
      			{"data":"scNo",responsivePriority:1},
				{"data":"scNo",responsivePriority:1},
				{"data":"projNo",responsivePriority:7},
				{"data":"projName",responsivePriority:3},
				{"data":"projectTypeDes",responsivePriority:4},
				{"data":"contributionModeDes",responsivePriority:5},
				{"data":"signDate",responsivePriority:7},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",responsivePriority:8}
			],
			columnDefs: [{
  				"targets":0,
  				"visible":false
  			},{
				"targets": 7,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.scId,"1104014");
						return html;
					}else{
						return data;
					}
				},
				"orderable":false
			},{
				"targets":8,
				 "visible":false
			},{
				"targets":4,
				 "orderable":false
			},{
				"targets":5,
				 "orderable":false
			},{
				"targets":3,
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
		var url = "#supplementalContractAudit/searchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#supplementalContractAuditTable_filter input").on("change",function(){
		searchData.projNo = $("#supplementalContractAuditTable_filter input").val();
		//传入false  则不选中行
		$("#supplementalContractAuditTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#supplementalContractAuditTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	
	searchData = $("#searchForm_contractAudit").serializeJson();
	searchData.projNo = $("#supplementalContractAuditTable_filter input").val();
    $("#supplementalContractAuditTable").cgetData(false, function(){
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
		var scId = $(this).attr("data-rid");
		var data = {"scId":scId,"currentLevel":currentLevel,"isAudit":isAudit};
		console.log("加载审核屏");
		$("#ajax-content").cgetContent("supplementalContractAudit/auditPage",data);
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
var supplementalContractAudit = function () {
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
//		tableid: 'supplementalContractAuditTable',
//	}
//});