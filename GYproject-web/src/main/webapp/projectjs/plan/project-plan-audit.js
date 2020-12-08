
var searchData = {};
var menuId = "110502";
searchData.menuId = menuId;
var handleProjectPlanAudit = function() {
    searchData.projNo=$("#waitHandleProjNo").val();

    "use strict";
    if ($('#planAuditTable').length !== 0) {
    	$('#planAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#planAuditTable').hideMask();
   			$("#planAuditTable_filter input").attr("placeholder","工程编号");
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
                url: 'projectPlanAudit/queryConstructionPlan',
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
				{"data":"cuName",responsivePriority:4},
				{"data":"suName",responsivePriority:5},
				{"data":"cpArriveDate",responsivePriority:6},
				{"data":"cpDocumenter",responsivePriority:7},
				{"data":"workDayDto",responsivePriority:8},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" },
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
			],
			columnDefs: [{
				"targets": 7,
				"render": function ( data, type, row, meta ) {
					
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.cpId,"110502");
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":6,
				 "orderable":false,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
			},
			{
				"targets":5,
				 "visible":false
			},{
				"targets":7,
				 "orderable":false
			},{
				"targets":1,
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
    }
};

/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#projectPlanAudit/planSearchPopPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#planAuditTable_filter input").on("change",function(){
		searchData.menuId = menuId;
		searchData.projNo = $("#planAuditTable_filter input").val();
		//传入false  则不选中行
		$("#planAuditTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#planAuditTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	
	searchData = $("#searchForm_planAudit").serializeJson();
	searchData.projNo = $("#planAuditTable_filter input").val();
	searchData.menuId = menuId;
    $("#planAuditTable").cgetData(false, function(){
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
		var cpId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"cpId":cpId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("projectPlanAudit/auditPage",data);
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
var projectPlanAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleProjectPlanAudit();
        	})
        }
    };
}();