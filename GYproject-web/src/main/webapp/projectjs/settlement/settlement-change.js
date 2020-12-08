/**查询条件*/
var changeData={},visaData={},materialListTable,state,mcData={};

var handleChangeRecord = function() {
	"use strict";
	changeData={};
	changeData.projId = $('#projId').val();
    if ($('#budgetAdjustTable').length !== 0) {
    	$('#budgetAdjustTable').on( 'init.dt',function(){
    		//隐藏遮罩
   			$('#budgetAdjustTable').hideMask();   
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementChange/queryBudgetChange',
                type:'post',
                data: function(d){
                   	$.each(changeData,function(i,k){
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
      			{"data":"budgetTotalCost","className":"text-right"},
	  			{"data":"civilCost","className":"text-right"}, 
	  			{"data":"yardInstallCost","className":"text-right"},
	  			{"data":"homeInstallCost","className":"text-right"},
	  			{"data":"boilerMeter","className":"text-right"},
	  			{"data":"technology","className":"text-right"},
	  			{"data":"yardEarthwork","className":"text-right"},
	  			{"data":"suCost","className":"text-right"},
	  			{"data":"inspectionCost","className":"text-right"},
	  			{"data":"storeCost","className":"text-right"},
	  			{"data":"otherCost1","className":"text-right"}
	  			
			],
			/*隐藏掉第一列（用户不关心的信息，但是业务上需要id查询右侧的详述内容，故隐藏掉）*/
			columnDefs: [{
		             "targets": 0, 
		             "visible":false
			},{
				"targets":1,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":2,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":3,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":4,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":5,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":6,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":7,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":8,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":9,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":10,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			},{
				"targets":11,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			}]
        });
    }
}

/**
 * 补充协议
 */
var handleSupplementContract = function() {
	"use strict";
	visaData={};
	visaData.projId = $('#projId').val();
    if ($('#suppContTable').length !== 0) {
    	$('#suppContTable').on( 'init.dt',function(){
    		//隐藏遮罩
   			$('#suppContTable').hideMask();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Brtip',
            buttons: [
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'settlementChange/querySupplementContract',
                type:'post',
                data: function(d){
                   	$.each(visaData,function(i,k){
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
          		{"data":"conNo"},
    			{"data":"signDate"},
    			{"data":"adjustment"},
    			{"data":"scAmount","className":"text-right"},
    			{"data":"conAgent"}
			],
			columnDefs: [{
		             "targets": 0, 
		             "visible":false
			},{
				"targets":4,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			}],
			responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            }
        });
    }
}

/**
 * 材料变更
 */
var handleMChangeList = function() {
	"use strict";
	mcData={};
	mcData.projId = $('#projId').val();
	if ($('#materialListTable').length !== 0) {
		if (!$.fn.DataTable.isDataTable('#materialListTable')) {
	        materialListTable= $('#materialListTable').on( 'init.dt',function(){
	    	//隐藏遮罩
	    	$("#materialListTable").hideMask();
	    }).DataTable({
	    	language: language_CN,
	        lengthMenu: [ 18 ],
	        dom: 'Brtip',
	        buttons: [
	        ],
	        //serverSide: true, 
			ajax: {
			    url: 'settlementChange/queryMaterialChange',
			    contenttype:"application/json;charset=utf-8",
			    data:function(d){
			        $.each(mcData,function(i,k){
			            d[i] = k;
			        }); 	
			    },
			    datasrc: ''
			},
	        columns: [
	            {"data":"projId", className:"none never"},
	  			{"data":"materialName"},
	  			{"data":"materialSpec"},
				{"data":"materialUnit"},
				{"data":"adjustQuantity", "className": "text-right"}
			],
			order: [[ 0, "asc" ]],
			columnDefs: [{
				targets: 0,
				"visible":false
			},{
				"targets":4,
				"render":function(data,type,row,meta){
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
			}],
			responsive: {
            	details: {
            		renderer: function ( api, rowIdx, columns ){
            			return renderChild(api, rowIdx, columns);
            		}
            	}
            }
	    }).on("draw.dt",function(){
	    });
	}
	}else{
		materialListTable.ajax.reload();
	}
}
/**
 * js初始加载方法
 */
var changeRecord = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	//初始化变更记录
        	handleChangeRecord();
        }
    };
}();

/**切换到预算调整*/
$("#budgetAdjustTab").on("shown.bs.tab",function(){
  $("#budgetAdjustTable").cgetData(false);
});
/**切换到补充协议*/
$("#suppContTab").on("shown.bs.tab",function(){
	if(!$.fn.DataTable.isDataTable('#suppContTable')){
		handleSupplementContract();
	}else{
		$("#suppContTable").cgetData(false);
	}
});
/**切换到材料变更*/
$("#materialChangeTab").on("shown.bs.tab",function(){
	if(!$.fn.DataTable.isDataTable('#materialListTable')){
		handleMChangeList();
	}else{
		$("#materialListTable").cgetData(false);
	}
});
