var ddMytable;         //工程列表
var datacollecttable;//资料收集列表
var designertable;   //设计员列表
var accessoryData={};//资料列表查询条件
var searchData={};	 //查询条件
searchData.menuId="110207";
var dataPopTable;
var accessoryTable;
var designerData={};
designerData.menuId="110207";
/**
 * 加载工程列表
 */
var handleDesignDispatch = function() {
	"use strict";
    if ($('#designDispatchTable').length !== 0) {
    	ddMytable= $('#designDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#design_dispatch_panel_box").cgetContent("designDispatchSecondary/viewPage");
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
            	 url:"designDispatchSecondary/queryProject",
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
		var url = "#designDispatchSecondary/projectSearchPopPage";
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
	searchData = $("#searchForm_designDispatch").serializeJson();
	var projNo=$('#designDispatchTable_filter input').val();
	searchData.projNo=projNo;
    $("#designDispatchTable").cgetData(true,designDispatchCallBack);  //列表重新加载
}

var designDispatchCallBack = function(){
	var len = $('#designDispatchTable').DataTable().ajax.json().data ? $('#designDispatchTable').DataTable().ajax.json().data.length : $('#designDispatchTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#designDispatchForm").formReset();
		$("#designDispatchForm").toggleEditState(false).styleFit();
		$(".editBtn").addClass("hidden");
		$("input[name='depositGet'][value='0']").attr("checked","checked");
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
	t.cgetDetail('designDispatchForm','designDispatchSecondary/viewDispatchResult','',detailBack);
	//$('#designerTable').cgetData(false,designerTableCallBack);
}

var detailBack=function(){
	designerData.deptId=$("#deptId").val();
	$("#unitName").val($("#duName").val());
	$("#unitId").val($("#duId").val());
	$("#designDispatchForm").toggleEditState(true).styleFit();
	$(".editBtn").removeClass("hidden");
	$("input[name='depositGet'][value='0']").attr("checked","checked");
	
	console.info("cust--"+$("#custName").val());
	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	
	
	//accessoryData.projNo=$("#projNo").val();
	if($.fn.DataTable.isDataTable('#designerTable')){
		
		console.info("--1");
		console.info(designerData);
		//初始化过
		$("#designerTable").cgetData(false,designerTableCallBack);//列表重新加载
	}else{
		designertableinit();
	}	
}



/**
 * 派遣成功后刷新设计员表格
 */
var updateDesignerBack=function(){
	//check if a table node is already a DataTable or not.确保不会重复初始化
	if ($.fn.DataTable.isDataTable('#designerTable')) {
		designerData.deptId = '-1';
		//列表重新加载
		$('#designerTable').cgetData(false,designerTableCallBack);
	}else{
		//初始化设计员表格
		designertableinit();
	}
}
var designerTableCallBack = function(){
	console.info("1111");
	
	
	var len = $('#designerTable').DataTable().ajax.json().data ? $('#designerTable').DataTable().ajax.json().data.length : $('#designerTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	}else{
		$('.editBtn').removeClass("hidden");
	}
}

/**
 * 加载设计员表格
 */
var designertableinit= function() {
	"use strict";
	designerData.deptId=$("#deptId").val()==''?'-1':$("#deptId").val();
	console.info(designerData);
	
	designertable= $('#designerTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack1,true);
	$('#designerTable').hideMask();
	designerTableCallBack();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        //dom: 'Brt',
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'designDispatchSecondary/querySurveyer',
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
            {"data":"staffId",className:"text-right"},
  			{"data":"surveyer",className:"surveyer"},
  			{"data":"surveyRegister",className:"text-right"},
	    ],
	    columnDefs: [{
			 "targets": 0,
			 "visible":false
		 }]
   });  
};

var trSelectedBack1=function(v, i, index, t, json){
	$("#surveyerId").val(json.staffId);
}

/**
 * 初始化工程列表
 */
var designDispatch = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleDesignDispatch();
        	})
        }
    };
}();





