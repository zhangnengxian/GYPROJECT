var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=7;
}else{
	showLength=15;
}
var searchData = {};
var budgeterData={};
var menuId="110611";
searchData.menuId= menuId;
var handlePaymentAudit = function() {
	searchData.paId=$("#busOrderId").val();

	"use strict";
    if ($('#paymentAuditTable').length !== 0) {
    	$('#paymentAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#paymentAuditTable').hideMask();
   			$("#paymentAuditTable_filter input").attr("placeholder","请款编号");
   			//搜索监听
   			searchMonitor();  
   			setTimeout(function(){
   			    $("#paymentAuditTable").DataTable().columns.adjust();
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
            bStateSave:true,
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'paymentAudit/queryPaymentAudit',
                type:'post',
                data: function(d){
                   	$.each(searchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/complete/json/divisional_acceptance_audit.json',
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
                {"data":"applyNo",responsivePriority:2}, 
                {"data":"applyReasonDes",responsivePriority:3},
                {"data":"feeTypeDes",responsivePriority:3},
                {"data":"applyAmount",responsivePriority:4},
                {"data":"applyer",responsivePriority:5},
                {"data":"paAuditer",responsivePriority:6},
                {"data":"applyDate",responsivePriority:7},
		        {"data":"mrAuditLevel",responsivePriority:1}
			],
			columnDefs: [{
				"targets":0,
				"visible":false
			},{
				 'targets':4,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){
							return parseFloat(data).toFixed(2);
						}else{
							return data;
						}
					},
			 	},{
				"targets": 8,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.paId,'110611');						
						return html;
					}else{
						return data;
					}
				}
			},{
				"targets":2,
				 "orderable":false
			},{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: true	//false从后部开始截取，及从后部开始计数，并裁掉超出的内容,true则相反
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
var trSelectedBack=function(v, i, index, t, json){
	$("#businessOrderId").val(json.paId);
	console.info("---");
	console.info(json);
};
/**
 * 查询监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#paymentAudit/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$('#paymentAuditTable_filter input').on('change',function(){
		var applyNo = $('#paymentAuditTable_filter input').val();
		searchData = {};
		searchData.applyNo = applyNo;
		searchData.menuId= menuId;
		$("#paymentAuditTable").cgetData(false, function(){
   			//跳转到审核页面
			auditBtnMonitor();
		});
	});
	//基础条件查询添加回车事件
	$('#paymentAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#searchForm_paymentAudit').serializeJson();
	var applyNo = $('#paymentAuditTable_filter input').val();
	searchData.applyNo = applyNo;
	searchData.menuId= menuId;
	//列表重新加载
    $('#paymentAuditTable').cgetData(false, function(){
		//跳转到审核页面
    	auditBtnMonitor();
	});
}    
/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	$(document).off("click", ".level").on("click", ".level", function(){
		//var paId=trSData.paymentAuditTable.json.paId;
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var paId = $(this).attr("data-rid");
		var currentLevel = $(this).attr("data-level");
		var data = {"paId":paId,"currentLevel":currentLevel,"isAudit":isAudit};
		$("#ajax-content").cgetContent("paymentAudit/auditPage",data);
	});
};

/**
 * 初始化列表
 */
var paymentAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){  
        		handlePaymentAudit();
        	});
        }
    };
}();