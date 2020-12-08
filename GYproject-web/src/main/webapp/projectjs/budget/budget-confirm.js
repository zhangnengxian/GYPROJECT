var ddMytable;			//工程列表
var searchData={};		//工程查询条件
var budgeterTable;		//设计员列表
var designerData={};	//设计员查询条件
var accessoryData={};	//资料列表查询条件
var budgeterTable;
var budgeterData={};
var menuId='110307';
budgeterData.menuId=menuId;
searchData.menuId=menuId;
/**
 * 加载工程列表
 */
var handlebudgetConfirm = function() {
	"use strict";
    if ($('#designDispatchTable').length !== 0) {
    	ddMytable= $('#designDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#design_dispatch_panel_box").cgetContent("budgetConfirm/viewPage");
   			//隐藏遮罩
   			$('#designDispatchTable').hideMask();
   			$("#designDispatchTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			confirmMonitor();
   			setTimeout(function(){
   			    $("#designDispatchTable").DataTable().columns.adjust();
   			    //$("#workOrderTable").DataTable().responsive.recalc();
   			}, 0);
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
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },  
                { text: '确认', className: 'btn-sm btn-query business-tool-btn confirmBtn' }  
             ],
             //ajax:'projectjs/design/json/design_dispatch.json',
             serverSide:true,
             ajax:{
            	 url:"budgetConfirm/queryProject",
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
			    {"data":"projId",className:"none never"}, 
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes"},
				{"data":"workDayDto"},
				{"data":"overdue",className:"none never"}
		 	 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			 },{
					"targets":3,
					 "orderable":false
				},{
					"targets":4,
					"orderable":false,
					 "render":function(data,type,row,meta){
						 if(data!=null){
							 return data.haveDays;
						 }else{
							 return 0;
						 }
					 }
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
		var url = "#budgetDispatch/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$("#designDispatchTable_filter input").on("change",function(){
		var projNo = $("#designDispatchTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;searchData.menuId=menuId;
		$("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#designDispatchTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};
/**
 * 弹屏监听方法
 */
var confirmMonitor= function(){
	$(".confirmBtn").on("click",function(){
		$(".editBtn").removeClass("hidden");
		$("#designDispatchForm").toggleEditState(true);
	});
};

/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	searchData = $("#costSummarySearchForm").serializeJson();
	var projNo=$('#designDispatchTable_filter input').val();
	searchData.projNo=projNo;
	searchData.menuId=menuId;
    $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
}

var designDispatchCallBack = function(){
	var len = $('#designDispatchTable').DataTable().ajax.json().data ? $('#designDispatchTable').DataTable().ajax.json().data.length : $('#designDispatchTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#designDispatchForm").formReset();
		$("#designDispatchForm").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		accessoryData.projNo="####";
		$("#dataTable").cgetData(false);//列表重新加载
	}
	 
}

/**
 * 查询弹出屏，点击取消后
 */
var clearForm=function(){
	//清空表单
    $("#searchForm_designDispatch input").val("");
    //清空下拉框
    $("#projLtypeId  option[value=''] ").attr("selected",true);
}


/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	t.cgetDetail('designDispatchForm','budgetConfirm/viewProject','',detailBack);
	//$('#budgeterTable').cgetData(false,budgeterTableCallBack);
}

var detailBack=function(){
	
	/*if($("#deptDivide").val()==$("#marketingCenter").val()||$("#deptDivide").val()==$("#customerServiceCenter").val()){
		budgeterData.deptId=$("#deptId").val();
	}else{
		budgeterData.deptId='';
	}*/
	budgeterData={};	//预算员查询条件
	if($("#deptDivide").val()==$("#marketingCenter").val()){
		budgeterData.deptDivide =$("#marketingCenter").val();
	}else if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		budgeterData.deptDivide =$("#customerServiceCenter").val();
	}else{	
		budgeterData.deptDivide =$("#preSettlementGroup").val();
	}
	console.info($("#deptDivide").val());
	console.info($("#marketingCenter").val());
	budgeterData.menuId=menuId;
	console.info(budgeterData);
	$("#designDispatchForm").toggleEditState(false).styleFit();
	$(".editBtn").addClass("hidden");	
}

var budgeterTableCallBack = function(){
	var len = $('#budgeterTable').DataTable().ajax.json().data ? $('#budgeterTable').DataTable().ajax.json().data.length : $('#budgeterTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	 }else{
		 $('.editBtn').removeClass("hidden");
	 }
}

/**
 * 初始化工程列表
 */
var budgetConfirm = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handlebudgetConfirm();
        	})
        }
    };
}();
//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'designDispatchTable'
//	}
//});
