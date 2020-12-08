
var searchData = {};
var menuId = "110403";
var conProjId="";
searchData.menuId = menuId;
var handleContractAudit = function() {
    searchData.projNo=$("#waitHandleProjNo").val();
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
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'contractAudit/queryContract',
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
                {"data":"projId",className:"none never"},
				{"data":"conNo",responsivePriority:1},
				{"data":"projNo",responsivePriority:7},
				{"data":"projName",responsivePriority:3},
				{"data":"projectTypeDes",responsivePriority:4},
				{"data":"contributionModeDes",responsivePriority:5},
				{"data":"contractAmount","className":"text-right",responsivePriority:6},
				{"data":"signDate",responsivePriority:7},
				{"data":"workDayDto",responsivePriority:8},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" },
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							//$(td).parent().children().css("background", "rgb(255, 99, 95)");
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
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
				"targets":8,
				 "orderable":false,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
			},{
				"targets": 9,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.conId,"110403");
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":10,
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

		searchData.menuId = menuId;
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
	searchData.menuId = menuId;
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
		console.info("21----");
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var currentLevel = $(this).attr("data-level");
		var conId = $(this).attr("data-rid");
		var data = {"conId":conId,"currentLevel":currentLevel,"isAudit":isAudit,"menuId":menuId,"projId":conProjId};
		$("#ajax-content").cgetContent("contractAudit/auditPage",data);
	});
	
};



/** 
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	conProjId = json.projId;
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