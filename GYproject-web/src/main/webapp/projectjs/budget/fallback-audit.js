
var searchData = {};
var budgeterData={};
var menuId="110310";
var handlefallBackAudit = function() {
	"use strict";
    if ($('#fallBackAuditTable').length !== 0) {
    	$('#fallBackAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#fallBackAuditTable').hideMask();
   			$("#fallBackAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();   			
        }).on( 'draw.dt', function () {
        	auditBtnMonitor();

		//跳转链接
		if (crossPageBus) {
			getSidebarMenu(11, true);
			checkSidebarMenu(crossPageBus.hash)
			//跳转后销毁对象
			crossPageBus = null
		}
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
                url: 'fallBackAudit/queryProject',
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
                {"data":"projScaleDes",responsivePriority:7},
                {"data":"projectType",responsivePriority:9},
                {"data":"constructionMode",responsivePriority:10},
                {"data":"projStatusDes",responsivePriority:8},
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
				"targets": 7,
				"render": function ( data, type, row, meta ) {
					console.info(data);
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.faId,menuId);
						return html;
					}else{
						return data;
					}
				}
			},{
				"targets":5,
				 "orderable":false
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
		var url = "#fallBackAudit/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#fallBackAuditTable_filter input').on('change',function(){
		var projNo = $('#fallBackAuditTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$("#fallBackAuditTable").cgetData(false, function(){
   			//跳转到审核页面
			auditBtnMonitor();
		});
	});
	//基础条件查询添加回车事件
	$('#fallBackAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#fallBackPopForm').serializeJson();
	var projNo = $('#fallBackAuditTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#fallBackAuditTable').cgetData(false, function(){
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
		var faId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"faId":faId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("fallBackAudit/auditPage",data);
	});
};

/**
 * 初始化列表
 */
var fallBackAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handlefallBackAudit();
        	});
        }
    };
}();