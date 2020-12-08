var searchData = {};
var menuId="110209";
searchData.menuId = menuId;
var handleapplyDelayAudit = function() {
	"use strict";
    if ($('#applyDelayAuditTable').length !== 0) {
    	$('#applyDelayAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#applyDelayAuditTable').hideMask();
   			$("#applyDelayAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();   			
        	auditBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'applyDelayAudit/queryApplyDelayAudit',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
			//ajax:'projectjs/design/json/apply-delay-audit.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
	              {"data":"projId"},     
	              {"data":"projNo",responsivePriority:1}, 
	              {"data":"projName",responsivePriority:3},
	              {"data":"projectTypeDes",responsivePriority:8},
	              {"data":"contributionModeDes",responsivePriority:9},
	              {"data":"stepIdDes",responsivePriority:4},
	              {"data":"originalPeriod",responsivePriority:6},
	              {"data":"delayPeriod",responsivePriority:5},
	              {"data":"delayReasonDes",responsivePriority:7},
		          {"data":"mrAuditLevel",responsivePriority:2},
		          {"data":"overdue", className:"none never"}
			],
			columnDefs: [{
				"targets":0,
				"visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				 "orderable":false
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
				"targets":9,
				 "orderable":false
			},{
				"targets":6,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets": 9,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.adId,menuId);
						return html;
					}else{
						return data;
					}
				}
			}],
			fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				if(aData.overdue){
					$(nRow).addClass("expired-proect");
				}
			}
        });
;
    }
};
var trSelectedBack=function(){};
/**
 * 查询监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#applyDelayAudit/delaySearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#applyDelayAuditTable_filter input').on('change',function(){
		var projNo = $('#applyDelayAuditTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
        searchData.menuId = menuId;
		$("#applyDelayAuditTable").cgetData(true, function(){
   			//跳转到审核页面
			auditBtnMonitor();
		});
	});
	//基础条件查询添加回车事件
	$('#applyDelayAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#searchForm_delay').serializeJson();
	var projNo = $('#applyDelayAuditTable_filter input').val();
	searchData.projNo = projNo;
    searchData.menuId = menuId;
	//列表重新加载
    $('#applyDelayAuditTable').cgetData(true, function(){
		//跳转到审核页面
    	auditBtnMonitor();
	});
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
		var adId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"daId":adId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("applyDelayAudit/delayCheckPage",data);
	});
};

/**
 * 初始化列表
 */
var applyDelayAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleapplyDelayAudit();
        	});
        }
    };
}();