var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}  
var searchData = {};


/**
 * 初始化列表
 */
var raiseMoneyAudit = function () {
    return {
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handleConnectGasAudit();
            })
        }
    };
}();


var handleConnectGasAudit = function() {
	"use strict";
    if ($('#raiseMoneyAuditTab').length !== 0) {
    	$('#raiseMoneyAuditTab').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#raiseMoneyAuditTab').hideMask();
   			$("#raiseMoneyAuditTab_filter input").attr("placeholder","工程编号");
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
            lengthMenu: [18],//分页条数
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'rasieMoneyAuditController/getAuditProject',
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
				{"data":"projNo",responsivePriority:3},
				{"data":"projName",responsivePriority:4},
				{"data":"projAddr",responsivePriority:7},
				{"data":"custName",responsivePriority:8},
				{"data":"corpName",responsivePriority:9},
				{"data":"projectTypeDes",responsivePriority:5},
				{"data":"contributionModeDes",responsivePriority:6},
				{"data":"mrAuditLevel",responsivePriority:2},
				{"data":"overdue",className:"none never" }
			],
			columnDefs: [{
				"targets": 7,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){				
						var html = getAuditLevelHtml(data,row.level,row.projId,"110213");
						return html;
					}else{
						return data;
					}
				},
			},{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
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
		var url = "#acceptAudit/surveySearchPage";
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	$("#raiseMoneyAuditTab_filter input").on("change",function(){
		searchData.projNo = $("#raiseMoneyAuditTab_filter input").val();
        $("#raiseMoneyAuditTab").cgetData(true);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#raiseMoneyAuditTab_filter input').on('keyup', function(event) {
		 if (event.keyCode == "13") {
			$(this).change();
		 }
    });
};

var searchDone = function(){
	searchData = $("#searchForm_survey").serializeJson();
	searchData.projNo = $("#raiseMoneyAuditTab_filter input").val();
    $("#raiseMoneyAuditTab").cgetData(true);  //列表重新加载
};

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
		$("#ajax-content").cgetContent("rasieMoneyAuditController/auditPage",data);
	});
	
};


/** 
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
}


