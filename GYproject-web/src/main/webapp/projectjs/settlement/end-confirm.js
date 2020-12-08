var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=12;
}else{
	showLength=15;
}
var searchData = {},reportConfirmTable;
var menuId ="110807";
searchData.menuId=menuId;
var handleReportConfirmAudit = function() {
	"use strict";
	searchData.confirmState="endConfirm";
    searchData.projNo=$("#projNo").val();
    if ($('#reportConfirmTable').length !== 0) {
    	reportConfirmTable=$('#reportConfirmTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#reportConfirmTable').hideMask();
   			$("#reportConfirmTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			setTimeout(function(){
   			    $("#reportConfirmTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
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
                url: 'settlementConfirm/querySettlementEnd',
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
				{"data":"projNo",responsivePriority:1},
				{"data":"projName",responsivePriority:3},
				{"data":"sendDeclarationCost",responsivePriority:4,className:"text-right"},
				{"data":"ocoDate",responsivePriority:5},
				{"data":"firstAuditer",responsivePriority:5},
				{"data":"firstAuditDate",responsivePriority:5},
				{"data":"endDeclarationCost",responsivePriority:8,className:"text-right"},
				//{"data":"endDeclaraDate",responsivePriority:7},
				{"data":"settlementer"},
				{"data":"workDayDto"},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" }
			],
			columnDefs: [{
				'targets':0,
				 'visible':false
			},{
				'targets':3,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			}/*,{
				'targets':4,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			}*/,{
				'targets':7,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			},{
				"targets":9,
				 "render":function(data,type,row,meta){
					 if(data!=null){
						 return data.haveDays;
					 }else{
						 return 0;
					 }
				 }
			},{
				"targets": 10,
				"render": function ( data, type, row, meta ) {
					
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.projId,"110807");
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":10,
				 "orderable":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: true	//false从后部开始截取，及从后部开始计数，并裁掉超出的内容,true则相反
				})
			}
			/*{
				"targets":6,
                "render": function ( data, type, row, meta ) {					
					if(type==="display"){
						if(data){
						var html = '<image height="25px" src='+data+'>';
						return html;
						}else{
							return data;
						}
					}else{
						return data;
					}
				}
			},{
				"targets":8,
				 "visible":false
			}*/],
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
		var url = "#settlementConfirm/settleSearchPopPage?state=end";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#reportConfirmTable_filter input").on("change",function(){
		searchData.projNo = $("#reportConfirmTable_filter input").val();
		searchData.menuId=menuId;
		//传入false  则不选中行
		$("#reportConfirmTable").cgetData(false, function(){
	   		//跳转到审核页面
			auditBtnMonitor();
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#reportConfirmTable_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	
	searchData = $("#searchForm_reportAudit").serializeJson();
	searchData.confirmState='endConfirm';
	searchData.projNo = $("#reportConfirmTable_filter input").val();
	searchData.menuId=menuId;
    $("#reportConfirmTable").cgetData(false, function(){
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
		var projId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit,"confirmSort":"endConfirm"};
		$("#ajax-content").cgetContent("settlementConfirm/auditPage",data);
	});
};

/** 
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	$("#projId").val(json.projId);
}

/**
 * 初始化列表
 */
var reportConfirmAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleReportConfirmAudit();
        	})
        }
    };
}();
//$('.attach-panel').initAttachOper({
//	settlementchange: {
//	    tableid : 'reportConfirmTable'
//	}
//});