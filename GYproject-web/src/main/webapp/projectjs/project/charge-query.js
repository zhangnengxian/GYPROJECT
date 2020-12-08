
var arSearchData={},cfSearchData={};
var queryCashFlowTable,queryAccrualsTable;
var menuId = "110902";
arSearchData.menuId= menuId;
cfSearchData.menuId = menuId;
function detailBack(data){
	var json=$.extend({}, data.customer,data.project);
	$("#chargeAcForm").deserialize(json);
	$("#cfDate").val(format($("#cfDate").val(),'all'));
}
//应收记录
var handleArCharge = function() {
	"use strict";
    if ($('#chargeArQueryTable').length !== 0) {
    	queryAccrualsTable= $('#chargeArQueryTable').on( 'init.dt',function(){
    		if($("#chargeArQueryTable_wrapper #typeSelect").length < 1)$("#chargeArQueryTable_wrapper").prepend($("#typeSelect").removeClass("hidden"));
    		//默认选中第一行
    		$(this).bindDTSelected(trArSelectedBack,true);   
    		arSearchInput();
    		$("#charge_query_box").cgetContent("charge/queryViewPage");
			/*$("#chargeArQueryTable_filter input").attr("placeholder","款项类型");*/
			$('#chargeArQueryTable').hideMask();	
			arSearchPop();
   			   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
               { text: '查询', className: 'btn-sm btn-query business-tool-btn arSearchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'charge/queryAccrualsRecordNew',
                type:'post',
                data: function(d){
                   	$.each(arSearchData,function(i,k){
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
            colReorder: true,//列拖动
            select: true,  //支持多选
            columns: [
                      {"data":"arId",className:"none never"},
      	  			  {"data":"arDate",render:changeDate},
      	  			  {"data":"arTypeDes"},
      	  			  {"data":"arCost",className:"text-right"},
      	  			  {"data":"receiveAmount",className:"text-right"},
      	  			  {"data":"arFlagDes"},
      	  			  {"data":"billStateDes"},
      	  			  {"data":"staff.staffName"},
      	  			  {"data":"projId",className:"none never"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				'targets':3,
			    "render": function ( data, type, row, meta ) {
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				},
		 	},{
				'targets':4,
			    "render": function ( data, type, row, meta ) {
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else if(data==null){
						return new Number(0.00);
					}else{
						return data;
					}
				},
		 	},{
				"targets": 8,
			    "visible":false
			},{
				"targets":2,
				 "orderable":false
			},{
				"targets":5,
				 "orderable":false
			},{
				"targets":6,
				 "orderable":false
			},{
				"targets":7,
				 "orderable":false
			}]
        });
        
    }
};
//实收记录
var handleCfCharge = function() {
	"use strict";
    if ($('#chargeCfQueryTable').length !== 0) {
    	queryCashFlowTable= $('#chargeCfQueryTable').on( 'init.dt',function(){
    		if($("#chargeCfQueryTable_wrapper #typeSelect").length < 1)$("#chargeCfQueryTable_wrapper").prepend($("#cfTypeSelect").removeClass("hidden"));
   			//默认选中第一行
    		$(this).bindDTSelected(trCfSelectedBack,true);    		
    		//$("#charge_query_box").cgetContent("charge/queryViewPage");
			/*$("#chargeCfQueryTable_filter input").attr("placeholder","款项类型");*/
    		cfSearchInput();
    		cfSearchPop();
			$('#chargeCfQueryTable').hideMask();	
			
   			   			
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
               { text: '查询', className: 'btn-sm btn-query business-tool-btn cfSearchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'charge/queryCashFlowNew',
                type:'post',
                data: function(d){
                   	$.each(cfSearchData,function(i,k){
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
            colReorder: true,//列拖动
            select: true,  //支持多选
            columns: [
                      {"data":"cfId",className:"none never"},
					  {"data":"cfDate",render:changeDate},
					  {"data":"cfTypeDes"},							
					  {"data":"cfAmount",className:"text-right"},
					  {"data":"cfFlagDes"},
					  {"data":"billStateDes"},
					  {"data":"staff.staffName"},
					  {"data":"department.deptName"},
					  {"data":"projId",className:"none never"}
			],
			columnDefs: [{
				"targets": 0,
			    "visible":false
			},{
				'targets':3,
			    "render": function ( data, type, row, meta ) {
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else if(data==null){
						return new Number(0.00);
					}else{
						return data;
					}
				},
		 	},{
				"targets": 8,
			    "visible":false
			},{
				"targets":2,
				 "orderable":false
			},{
				"targets":4,
				 "orderable":false
			}
			,{
				"targets":5,
				 "orderable":false
			},{
				"targets":6,
				 "orderable":false
			},{
				"targets":7,
				 "orderable":false
			}

]
        });
        
    }
};
/**
 * 弹屏监听方法
 */
var arSearchPop=function(){
	$(".arSearchBtn").off("click").on("click",function(){
		var url = "#charge/querySearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: arSearchDone,
			cancel:clearForm
		});
	});
};
var cfSearchPop=function(){
	$(".cfSearchBtn").off("click").on("click",function(){
		var url = "#charge/querySearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: cfSearchDone,
			cancel:clearForm
		});
	});
};
var arSearchInput=function(){
	$("#typeSelect select:visible").off("change").on("change",function(){
		 arSearchData.arType=$(this).val();arSearchData.menuId= menuId;
		 $("#chargeArQueryTable").cgetData(true,arDataBack);  //列表重新加载
	});
};
var cfSearchInput=function(){
	$("#cfTypeSelect select:visible").off("change").on("change",function(){
		cfSearchData.cfType=$(this).val();cfSearchData.menuId = menuId;
		 $("#chargeCfQueryTable").cgetData(true,cfDataBack);  //列表重新加载
	});
};
	$("#arTab").on("shown.bs.tab",function(){
		$("#chargeAcForm")&&$("#chargeAcForm").formReset();
		if ($.fn.DataTable.isDataTable('#chargeArQueryTable')) {
			$("#chargeArQueryTable").cgetData(true);
		}else{
			handleArCharge();
		}
	});
	$("#cfTab").on("shown.bs.tab",function(){
		$("#chargeAcForm")&&$("#chargeAcForm").formReset();
		if ($.fn.DataTable.isDataTable('#chargeCfQueryTable')) {
			$("#chargeCfQueryTable").cgetData(true);
		}else{
			handleCfCharge();
		}
	});


var clearForm=function(){};
/**
 * 选中时加载详情
 */
var trArSelectedBack = function(v, i, index, t, json){	
	$("#balance").val((new Number(json.arCost)- new Number(json.receiveAmount)).toFixed(2));
	 $(".ar").removeClass("hidden");
	 $(".cf").addClass("hidden");
	 t.cgetDetail('chargeAcForm','charge/queryChargeDetail','8',detailBack);
	 $("#staffName").val(json.staff.staffName);
	 $("#billTypeDes").val(json.billTypeDes);
	console.info(json.billType);
	 if(json.billType!=''&&json.billType==0){
		 $(".Invo").addClass("hidden");
		 $(".receipt").removeClass("hidden");
	 }else{
		 $(".Invo").removeClass("hidden");
		 $(".receipt").addClass("hidden");
	 }
	 if(json.arFlag==-1){
     	$(".fk").addClass("hidden");
     }else{
     	$(".fk").removeClass("hidden");
     }
}
var trCfSelectedBack = function(v, i, index, t, json){	
	 $(".cf").removeClass("hidden");
	 $(".ar").addClass("hidden");
	 $("#billTypeDes").val(json.billTypeDes);
	 t.cgetDetail('chargeAcForm','charge/queryChargeDetail','8',detailBack);
	 $("#staffName").val(json.staff.staffName);
	 
	 if(json.billType!=''&&json.billType==0){
		 $(".Invo").addClass("hidden");
		 $(".receipt").removeClass("hidden");
	 }else{
		 $(".Invo").removeClass("hidden");
		 $(".receipt").addClass("hidden");
	 }
}


/** 查询弹出屏，点击确定后 */
var arSearchDone = function(){	
	arSearchData = $("#searchForm_charge").serializeJson();
	arSearchData.arType=$("#costType").val();arSearchData.menuId= menuId;
    $("#chargeArQueryTable").cgetData(true,arDataBack);  //列表重新加载
    
}
/** 查询弹出屏，点击确定后 */
var cfSearchDone = function(){	
	cfSearchData = $("#searchForm_charge").serializeJson();
	cfSearchData.cfType=$("#cfCostType").val();
	cfSearchData.cfFlag=cfSearchData.arFlag;cfSearchData.menuId = menuId;
    $("#chargeCfQueryTable").cgetData(true,cfDataBack);  //列表重新加载
    
}
var arDataBack=function(){
	var len = $('#chargeArQueryTable').DataTable().ajax.json().data ? $('#chargeArQueryTable').DataTable().ajax.json().data.length : $('#chargeArQueryTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $("#chargeAcForm")[0].reset();
	}
}
var cfDataBack=function(){
	var len = $('#chargeCfQueryTable').DataTable().ajax.json().data ? $('#chargeCfQueryTable').DataTable().ajax.json().data.length : $('#chargeCfQueryTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		 $("#chargeAcForm")[0].reset();
	}
}
//时间格式转换
function changeDate(e){
	return format(e,"all");
}

/**
 * 初始化工程列表
 */
var chargeQuery = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleArCharge();
        }
    };
}();

