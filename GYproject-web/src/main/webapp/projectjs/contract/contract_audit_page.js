var searchData = {};
searchData.menuId="110403";

var menuId="110402";//审详细页面与合同签订一个页面，使用合同签订的菜单ID去查询
/**
 * 初始化历史审批列表
 */
var projId = $("#projId").val();
var businessOrderId = $("#businessOrderId").val();
var histSearchData = {"projId":projId,"businessOrderId":businessOrderId};

var handleAuditHistory = function() {
	"use strict";
    if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brt',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'contractAudit/queryManageRecord',
                type:'post',
                data: function(d){
                   	$.each(histSearchData,function(i,k){
                   		d[i] = k;
                   	});
                   	
                },
                dataSrc: 'data'
            },
            //ajax: 'projectjs/design/json/connect_gas_audit_page.json',
            responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            },
            select: true,  //支持多选
            columns: [
				{"data":"mrTime"},
				{"data":"mrResult"},
				{"data":"mrAopinion"},
				{"data":"mrCsr"}
			],
			columnDefs: [{
				"targets": 0,
				"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			},{
				"targets": 1,
				"render": function ( data, type, row, meta ) {
					if(data === "1"){
						return "通过";
					}else if(data === "0"){
						return "不通过";
					}else{
						return "";
					}
				},
			}]
        });
    }
};

/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
}

//加载合同详述页面
var contractDetailPage = function(){
	var conId = $(".conId").val();
	var projId = $('.projId').val();
	var data = {"conId":conId,"menuId":menuId,"projId":projId};
	$("#contractAudit_panelBox").cgetContent("contractAudit/viewPage",data,handleAuditHistory);
}


/**
 * 初始化审批历史列表
 */
var auditHistory = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	contractDetailPage();
        }
    };
}();


