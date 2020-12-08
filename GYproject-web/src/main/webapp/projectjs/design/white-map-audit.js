
var searchData = {};
var menuId = "110216";
searchData.menuId = menuId;
var budgeterData={};
var handlewhiteMapAudit = function() {
	"use strict";
    if ($('#whiteMapAuditTable').length !== 0) {
    	$('#whiteMapAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#whiteMapAuditTable').hideMask();
   			$("#whiteMapAuditTable_filter input").attr("placeholder","工程编号");
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
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'whiteMapAudit/queryProject',
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
                {"data":"projId"},     
                {"data":"projNo",responsivePriority:1}, 
                {"data":"projName",responsivePriority:3},
                {"data":"projAddr",responsivePriority:4},
                {"data":"projScaleDes",responsivePriority:7},
                {"data":"projectTypeDes",responsivePriority:9},
                {"data":"contributionModeDes",responsivePriority:10},
                {"data":"designer",responsivePriority:5},
                {"data":"projStatusDes",responsivePriority:8},
                {"data":"workDayDto",responsivePriority:11},
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
				"targets":4,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets": 10,
				"render": function ( data, type, row, meta ) {
					console.info(data);
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.projId,"110216");						
						return html;
					}else{
						return data;
					}
				}
			},{
				"targets":5,
				 "orderable":false
			},{
				"targets":6,
				 "orderable":false
			},{
				"targets":8,
				 "orderable":false
			},{
				"targets":10,
				 "orderable":false
			},{
				"targets":9,
				"orderable":false,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
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
		var url = "#whiteMapAudit/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#whiteMapAuditTable_filter input').on('change',function(){
		var projNo = $('#whiteMapAuditTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		searchData.menuId = menuId;
		$("#whiteMapAuditTable").cgetData(false, function(){
   			//跳转到审核页面
			auditBtnMonitor();
		});
	});
	//基础条件查询添加回车事件
	$('#whiteMapAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#whiteMapAudit_searchForm').serializeJson();
	var projNo = $('#whiteMapAuditTable_filter input').val();
	searchData.projNo = projNo;
	searchData.menuId = menuId;
	//列表重新加载
    $('#whiteMapAuditTable').cgetData(false, function(){
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
		var projId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("whiteMapAudit/auditPage",data);
	});
};

/**
 * 初始化列表
 */
var whiteMapAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handlewhiteMapAudit();
        	});
        }
    };
}();