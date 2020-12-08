
var projId = $("#projId").val();
var stepId = $("#stepId").val();
var histSearchData = {"projId":projId,"stepId":stepId};
console.info("查询历史---");
console.info(histSearchData);
var handleAuditHistory = function() {
	"use strict";
    if ($('#auditHistoryTable').length !== 0) {
    	$('#auditHistoryTable').on( 'init.dt',function(){
   			//隐藏遮罩
   			$('#auditHistoryTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'projectView/queryManageRecordList',
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
				{"data":"mrAuditLevel"},
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
					if(type === "display"){
						data=data+"级";
						return data;
					}else{
						return data;
					}
				},
			},{
				"targets": 2,
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


/**
 * 初始化工程列表
 */
projectManageRecord = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){   
        		handleAuditHistory();
        	})
        }
    };
}();



/******
 * @Description 审核记录表初始化
 * @author zhangnx
 * @param tableId 表ID
 * @param searchData 查询条件
 * @type {{initTable: ManageRecord.initTable}}
 */
var ManageRecord={
	initTable:function (tableId,searchData) {
		if(!$.fn.DataTable.isDataTable('#'+tableId)){
			$('#'+tableId).on( 'init.dt',function(){
			}).DataTable({
				language: language_CN,
				lengthMenu: [10],
				dom: 'Brtip',
				buttons: [],
				serverSide:true,//启用服务端模式，后台进行分段查询、排序
				ajax: {
					url: 'queryManageRecord/queryManageRecord',
					type:'post',
					data: function(d){$.each(searchData,function(i,k){d[i] = k;});},
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
					{"data":"stepDes"},
					{"data":"mrAuditLevel"},
					{"data":"mrResult"},
					{"data":"mrAopinion"},
					{"data":"mrCsr"},
					{"data":"mrTime"},

				],
				columnDefs: [{
					"targets": 2,
					"render": function ( data, type, row, meta ) {
						if(data === "1"){
							return "通过";
						}else if(data === "0"){
							return "不通过";
						}else{
							return "";
						}
					},
				},{
				"targets": 5,
					"render": function ( data, type, row, meta ) {
					if(type === "display"){
						return format(data,'all');
					}else{
						return data;
					}
				},
			}]
			});
		}else{
			$('#'+tableId).cgetData(true);
		}
	}
};


