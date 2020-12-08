
var searchData = {};
var budgeterData={};
var handlegasAudit = function() {

	"use strict";
	searchData.projNo=$("#projNo").val();
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
        	setTimeout(function(){
   			    $("#gasAuditTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
   			
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
                url: 'gasAudit/queryGasProject',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});

                },
                dataSrc: 'data'
            },
            // ajax: 'projectjs/complete/json/gas_audit.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
                {"data":"gprojId"},
                {"data":"projNo",responsivePriority:2},
                {"data":"projName",responsivePriority:4},
                {"data":"projAddr",responsivePriority:5},
                {"data":"projScaleDes",responsivePriority:6},
                {"data":"acceptDate",responsivePriority:7},
                {"data":"acceptType",responsivePriority:8},
		        {"data":"mrAuditLevel",responsivePriority:1},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
			],
			columnDefs: [{
				"targets":0,
				"visible":false
			},{
				"targets":7,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.gprojId,"110717");
						return html;
					}else{
						return data;
					}
				}
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":3,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":4,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 15, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
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
var nollBack = function(){}

var trSelectedBack=function(v, i, index, t, json){
    t.cgetDetail('openingPlanForm','','',nollBack);
};
/**
 * 查询监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#openingPlan/searchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#gasAuditTable_filter input').on('change',function(){
		var projNo = $('#gasAuditTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$("#gasAuditTable").cgetData(false, function(){
   			//跳转到审核页面
			auditBtnMonitor();
		});
	});
	//基础条件查询添加回车事件
	$('#gasAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#searchForm_gasProject').serializeJson();
	var projNo = $('#gasAuditTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#gasAuditTable').cgetData(false, function(){
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
		var gprojId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"gprojId":gprojId,"currentLevel":currentLevel,"isAudit":isAudit,"menuId":"110717"};
		$("#ajax-content").cgetContent("gasAudit/auditPage",data);
	});
};

/**
 * 初始化列表
 */
var gasAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handlegasAudit();
        	});
        }
    };
}();