
var searchData = {};
var menuId = '110802';
searchData.menuId = menuId;
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var handleReportConfirmAudit = function() {
	"use strict";
	 searchData.confirmState='startConfirm';
	 searchData.projNo = $(".sessionprojNo").val();
    if ($('#reportConfirmTable').length !== 0) {
    	$('#reportConfirmTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#reportConfirmTable').hideMask();
   			$("#reportConfirmTable_filter input").attr("placeholder","工程编号");
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
                url: 'settlementAuditStart/queryAuditStarts',
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
				{"data":"sendDeclarationCost",responsivePriority:5},
				{"data":"ocoDate",responsivePriority:4},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" }
			],
			columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			 },{
				"targets": 5,
				"render": function ( data, type, row, meta ) {
					
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.projId,"110802");
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":6,
				 "visible":false
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
		var url = "#settlementConfirm/settleSearchPopPage?state=start";
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
		searchData.menuId = menuId;
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
	searchData.confirmState='startConfirm';
	searchData.projNo = $("#reportConfirmTable_filter input").val();
	searchData.menuId = menuId;
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
		var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit,"confirmSort":"startConfirm"};
		$("#ajax-content").cgetContent("settlementConfirm/auditPage",data);
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


