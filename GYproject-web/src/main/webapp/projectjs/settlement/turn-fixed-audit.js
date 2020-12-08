
var searchData = {};
var handleTurnFixedAudit = function() {
	"use strict";
    if ($('#turnFixedAuditTable').length !== 0) {
    	$('#turnFixedAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#turnFixedAuditTable').hideMask();
   			$("#turnFixedAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();   			
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
                url: 'turnFixedAudit/queryTurnFixedApply',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                },
                dataSrc: 'data'
            },
           //ajax: 'projectjs/settlement/json/turn_fixed_audit.json',
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
                {"data":"projNo",responsivePriority:2},
                {"data":"projName",responsivePriority:3}, 
                {"data":"projectTypeDes",responsivePriority:4},
                {"data":"contributionModeDes",responsivePriority:5}, 
                {"data":"scNo",responsivePriority:6}, 
                {"data":"tfaDate",responsivePriority:7},
                {"data":"projTotalCost",className:"text-right",responsivePriority:8},
                {"data":"projCost",className:"text-right",responsivePriority:9},
		        {"data":"mrAuditLevel",responsivePriority:1},
                {"data":"overdue",className:"none never" }
			],
			columnDefs: [{
				"targets":0,
				"visible":false
			},{
				"targets": 9,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.projId);						
						return html;
					}else{
						return data;
					}
				}
			},{
				 "targets": 10,
				 "visible":false
			 },{
					"targets":3,
					 "orderable":false
				}
			 ,{
					"targets":4,
					 "orderable":false
				},{
					"targets":9,
					 "orderable":false
				},{
					"targets":2,
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
var trSelectedBack=function(v, i, index, t, json){};
/**
 * 查询监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#turnFixedAudit/turnFixedSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#turnFixedAuditTable_filter input').on('change',function(){
		var projNo = $('#turnFixedAuditTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$("#turnFixedAuditTable").cgetData(false, function(){
   			//跳转到审核页面
			auditBtnMonitor();
		});
	});
	//基础条件查询添加回车事件
	$('#turnFixedAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#searchForm_turnFixed').serializeJson();
	var projNo = $('#turnFixedAuditTable_filter input').val();
	searchData.projNo = projNo;
	//列表重新加载
    $('#turnFixedAuditTable').cgetData(false, function(){
		//跳转到审核页面
    	auditBtnMonitor();
	});
}    
/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	$(".level").off("click").on("click",function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var projId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"projId":projId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("turnFixedAudit/auditPage",data);
	});
};

/**
 * 初始化列表
 */
var turnFixedAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handleTurnFixedAudit();
        	});
        }
    };
}();