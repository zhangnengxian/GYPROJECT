var ddMytable;         //工程列表
var datacollecttable;//资料收集列表
var auditerTable;   //预结算员列表
var accessoryData={};//资料列表查询条件
var searchData={};	 //查询条件
var dataPopTable;
var accessoryTable;
var designerData={};
/**
 * 加载工程列表
 */
var handleCostApplyDispatch = function() {
	"use strict";
    if ($('#costApplyDispatchTable').length !== 0) {
    	ddMytable= $('#costApplyDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$("#done_dispatch_panel_box").cgetContent("costApplyDispatch/viewPage");
   			//隐藏遮罩
   			$('#costApplyDispatchTable').hideMask();
   			$("#costApplyDispatchTable_filter input").attr("placeholder","请款编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   		//跳转链接
   			if (crossPageBus) {
   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   					//跳转后销毁对象
   				crossPageBus = null
   			}
        }).DataTable({
        	 language: language_CN,
             lengthMenu: [18],
             dom: 'Bfrtip',
             buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
             ],
             //ajax:'projectjs/design/json/design_dispatch.json',
             serverSide:true,
             ajax:{
            	 url:"costApplyDispatch/queryPaymentApply",
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
             select: true,    //支持多选
             columns: [
				{"data":"paId",className:"none never"},
				{"data":"applyNo"}, 
				{"data":"applyer"},
				{"data":"applyDate"},
				{"data":"auditStateDes"},
				{"data":"isDispatchDes"}
		 	 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
			 },{
					"targets":4,
					 "orderable":false
				},{
					"targets":5,
					 "orderable":false
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
var searchMonitor= function(){
	$(".searchBtn").on("click",function(){
		var url = "#costApplyDispatch/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$("#costApplyDispatchTable_filter input").on("change",function(){
		var applyNo= $("#costApplyDispatchTable_filter input").val();
		searchData = {};
		searchData.applyNo = applyNo;
		$("#costApplyDispatchTable").cgetData(true,doneDispatchCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#costApplyDispatchTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};

/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	searchData = $("#searchForm_paymentAudit").serializeJson();
	var applyNo=$('#costApplyDispatchTable_filter input').val();
	searchData.applyNo=applyNo;
    $("#costApplyDispatchTable").cgetData(true,doneDispatchCallBack);  //列表重新加载
}

var doneDispatchCallBack = function(){
	var len = $('#costApplyDispatchTable').DataTable().ajax.json().data ? $('#costApplyDispatchTable').DataTable().ajax.json().data.length : $('#costApplyDispatchTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#costApplyAuditDispatchFrom").formReset();
		$("#costApplyAuditDispatchFrom").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		accessoryData.projNo="####";
		$("#costApplyDispatchTable").cgetData(false);//列表重新加载
	}
	 
}

/**
 * 查询弹出屏，点击取消后
 */
var clearForm=function(){
	//清空表单
    $("#searchForm_doneDispatch input").val("");
    //清空下拉框
    $("#projLtypeId  option[value=''] ").attr("selected",true);
}


/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	console.info("111--");
	t.cgetDetail('costApplyAuditDispatchFrom','costApplyDispatch/viewPaymentApply','',detailBack);
}

var detailBack=function(data){
	console.info("data--");
	console.info(data);
	$("#devide").val(data.devide);
	console.info("data.devide--"+data.devide);
	designerData={};	//预算员查询条件
	//客服中心
	if(data.devide==$("#customerServiceCenter").val()){
		
		designerData.deptDivide =$("#customerServiceCenter").val();
		console.info("customerServiceCenter--"+$("#customerServiceCenter").val());
	}else{
		console.info("preSettlementGroup--"+$("#preSettlementGroup").val());
		//预结算组
		designerData.deptDivide =$("#preSettlementGroup").val();
	}
	console.info("designerData--");
	console.info(designerData);
	
	$('#auditerTable').cgetData(false,auditerTableCallBack);
	$("#costApplyAuditDispatchFrom").toggleEditState(false).styleFit();
	$(".editBtn").removeClass("hidden");
}




/**
 * 派遣成功后刷新预结算员表格
 */
var updateDesignerBack=function(){
	if ( $.fn.DataTable.isDataTable('#auditerTable')) {
		designerData.deptId = '-1';
		//列表重新加载
		$('#auditerTable').cgetData(true,auditerTableCallBack);
	}else{
		//初始化预结算员表格
		auditerTableinit();
	}
}
var auditerTableCallBack = function(){
	var len = $('#auditerTable').DataTable().ajax.json().data ? $('#auditerTable').DataTable().ajax.json().data.length : $('#auditerTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	 }else{
		 $('.editBtn').removeClass("hidden");
	 }
}

/**
 * 加载预结算员表格
 */
var auditerTableinit= function() {
	//designerData.deptId = '-1';
	"use strict";
	auditerTable= $('#auditerTable').on( 'init.dt',function(){
	$('#auditerTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'costApplyDispatch/queryAuditer',
            type:'post',
            data: function(d){
               	$.each(designerData,function(i,k){
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
            {"data":"surveyerId",className:"none never"},
  			{"data":"surveyer",className:"surveyer"},
  			{"data":"surveyRegister",className:"text-right"}
	    ],
	    columnDefs: [{
	        "targets":0,
		   // "visible":false
		}]
       // ordering:false
   });  
};



/**
 * 初始化工程列表
 */
var costApplyDispatch = function () {
	"use strict";
    return {
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){       
        		handleCostApplyDispatch();
        	});
        }
    };
}();





