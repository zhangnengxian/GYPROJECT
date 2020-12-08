
/**
 * 初始化历史审批列表
 */
var projId = $("#projId").val();
var businessOrderId = $("#businessOrderId").val();
var histSearchData = {"projId":projId,"businessOrderId":businessOrderId};
var subSearchData={},settState,qualitiesTable;
var handleGasAuditHistory = function(t){
	"use strict";
	histSearchData.settlementState=t;
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
                url: 'settlementConfirm/queryManageRecord',
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




/**
 * 初始化审批历史列表
 */
var gasAuditHistory = function () {
	"use strict";
    return {
        //main function
        init: function (t) {
        	settState=t;
        	handleGasAuditHistory(t);
        }
    };
}();

$("#qualitiesTab").on("shown.bs.tab",function(){
	qualitiesInit();	  
});
var qualitiesInit = function() {
	"use strict";
	subSearchData.projId=projId;
	subSearchData.settlementState=settState;
	subSearchData.sdId = $("#sdId").val();
    if ($('#qualitiesTable').length !== 0) {
    	if(!$.fn.DataTable.isDataTable('#qualitiesTable')){
    	    qualitiesTable= $('#qualitiesTable').on( 'init.dt',function(){
	    	    $(".total-amount").text($('#quantitiesTotal').val());
	        }).DataTable({
	        	language: language_CN,
//              lengthMenu: [18],
	            dom: 'Brtip',
	            buttons: [
	          
	            ],
	            ajax: {
	                url: 'settlementAuditStart/queryQuantityStandard',
	                type: 'post',
	                data: function(d){
	                   	$.each(subSearchData,function(i,k){
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
	                {"data":"id", className:"none never"},
	                {"data":"sqStandardId", className:"none never"},
		  			{"data":"sqBranchProjName", responsivePriority: 1}, 
					{"data":"sqUnit", className:"text-center", responsivePriority: 5},
					{"data":"sqLabourPrice", className:"text-right", responsivePriority: 4},
					{"data":"declaraAmount", className:"text-right", responsivePriority: 3},
					{"data":"sqAmount", className:"text-right amount", responsivePriority: 2}
				],
				columnDefs: [{
					"targets":0,
					"visible":false
				},{
					"targets":1,
					"visible":false
				},{
	  				 "targets":4,
					 "render": function ( data, type, row, meta ) {
						 if(type === "display"){
							if(data!=="" && data!==null){
								var pdata = parseFloat(data).toFixed(2);
							}else{
								var pdata = data;
							}
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqLabourPrice" class="form-control disabled input-sm text-right" data-parsley-type="number" readonly value="' + pdata + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
				},{
					"targets": 5,
					"render": function ( data, type, row, meta ) {
						if(type === "display"){
							data = data || 0;
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_declaraAmount" class="form-control disabled input-sm text-right" data-parsley-type="number" value="' + data + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
			    },{
					"targets": 6,
					"render": function ( data, type, row, meta ) {
						if(type === "display"){
							data = data || 0;
							var tdcon='<div class="tdInnerInput"><input name="' + row.sqStandardId + '_sqAmount" class="form-control disabled input-sm text-right" data-parsley-type="number" readonly value="' + data + '"></div>';
							return tdcon;
						}else{
							return data;
						}
					}
				}],
				ordering:false
	        });
	    }
    }
};
