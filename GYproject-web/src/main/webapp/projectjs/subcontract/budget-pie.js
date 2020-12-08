var ddMytable;			//工程列表
var searchData={};		//工程查询条件
var menuId ="110610";
searchData.menuId=menuId;
var budgeterTable;		//预算员列表
var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=8;
}else{
	showLength=10;
}
var budgeterData={};	//预算员查询条件
/**
 * 加载工程列表
 */
var handleBudgetPie = function() {
    searchData.projNo=$("#waitHandleProjNo").val();
    "use strict";
    if ($('#designDispatchTable').length !== 0) {
    	ddMytable= $('#designDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#design_dispatch_panel_box").cgetContent("budgetPie/viewPage");
   			//隐藏遮罩
   			$('#designDispatchTable').hideMask();
   			$("#designDispatchTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
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
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
             ],
             //ajax:'projectjs/design/json/design_dispatch.json',
             serverSide:true,
             ajax:{
            	 url:"budgetPie/queryProject",
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
				{"data":"overdue",className:"none never"},
				{"data":"isSpecialSign",className:"none never",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(cellData=='1'){
							$(td).parent().children().css("color", "rgb(255, 99, 95)");
						}
					}
  				}
		 	 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			 },{
					"targets":3,
					 "orderable":false
				},{
					"targets":4,
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
		var url = "#qualitiesDeclaration/projectSearchPopPage";
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
		searchData.projNo = projNo;
		searchData.menuId=menuId;
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
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	searchData = $("#searchForm_quantity").serializeJson();
	var projNo=$('#designDispatchTable_filter input').val();
	searchData.projNo=projNo;
	searchData.menuId=menuId;
    $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
}
var designDispatchCallBack = function(){
	var len = $('#designDispatchTable').DataTable().ajax.json().data ? $('#designDispatchTable').DataTable().ajax.json().data.length : $('#designDispatchTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#budgetDispatchForm").formReset();
		$("#budgetDispatchForm").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		$("#dataTable").cgetData(false);//列表重新加载
		budgeterData={};	//预算员查询条件
		budgeterData.deptId='-1'
		$("#budgeterTable").cgetData(false);//列表重新加载
	}else{
		$(".dispatchBtn").removeClass("hidden");
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
	t.cgetDetail('budgetDispatchForm','budgetPie/viewDispatchResult','',detailBack);
	//$('#budgeterTable').cgetData(false,budgeterTableCallBack);
}

var detailBack=function(){
	budgeterData={};	//预算员查询条件
	/*if($("#deptDivide").val()==$("#marketingCenter").val()){
		budgeterData.deptDivide =$("#marketingCenter").val();
	}else{*/
	if($("#deptDivide").val()==$("#customerServiceCenter").val()){
		console.info("---1");
		console.info($("#deptDivide").val());
		budgeterData.deptDivide =$("#customerServiceCenter").val();
		console.info(budgeterData);
	}else{
		budgeterData.deptDivide =$("#preSettlementGroup").val();
		console.info("---2");
		console.info($("#deptDivide").val());
		console.info(budgeterData);
	}
	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	
	
	$("#budgetDispatchForm").toggleEditState(true).styleFit();
	$(".editBtn").removeClass("hidden");
	if($.fn.DataTable.isDataTable('#budgeterTable')){
		//初始化过
		$("#budgeterTable").cgetData(false);//列表重新加载
	}else{
		budgetertableinit();
	}	
}

/**
 * 派遣成功后刷新设计员表格
 */
var updateDesignerBack=function(){
	//check if a table node is already a DataTable or not.确保不会重复初始化
	if ( $.fn.DataTable.isDataTable('#budgeterTable')) {
		//列表重新加载
		$('#budgeterTable').cgetData(false,budgeterTableCallBack);
	}else{
		//初始化设计员表格
		budgetertableinit();
	}
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
 * 加载预算员表格
 */
var budgetertableinit= function() {
	"use strict";
	budgeterTable= $('#budgeterTable').on( 'init.dt',function(){
	$('#budgeterTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'budgetPie/queryDesigner',
            type:'post',
            data: function(d){
               	$.each(budgeterData,function(i,k){
               		d[i] = k;
               	});
            },
            dataSrc: 'data'
        },
        //ajax:'projectjs/budget/json/budget_dispatch.json',
        responsive: {
        	details: {
        		renderer: function ( api, rowIdx, columns ){
        			return renderChild(api, rowIdx, columns);
        		}
        	}
        },
        select: true,  //支持多选
        columns: [
            {"data":"budgeterId",className:"none never"},
  			{"data":"budgeter"},
  			{"data":"budgeterTask",className:"text-right"}
  			/*{"data":"TOTALCOST",className:"text-right"}*/
	    ],
	    columnDefs: [
	                 {
				"targets": 0,
			    "visible":false
			}/*{
			 'targets':2,
			 "render": function ( data, type, row, meta ) {
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return data;
					}
				}
		 	}*/]
       
   });  
};

/**
 * 初始化工程列表
 */
var budgetPie = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleBudgetPie();
        	})
        }
    };
}();