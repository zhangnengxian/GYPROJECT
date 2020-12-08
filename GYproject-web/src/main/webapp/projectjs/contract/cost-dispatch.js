//var ddMytable;			//工程列表
var searchData={};		//工程查询条件
 var costerTable;		//设计员列表
 var designerData={};	//设计员查询条件
var accessoryData={};	//资料列表查询条件
 var costerTable;
 var budgeterData={};
 var menuId="110415";
 budgeterData.menuId=menuId;
 searchData.menuId=menuId;


/**
 * 初始化工程列表
 */
var costDispatch = function () {
    return {
        init: function () {
            $.getScript("js/ellipsis.js").done(function(){
                handlebudgetDispatch();
            })
        }
    };
}();




var handlebudgetDispatch = function() {
    searchData.projNo=$("#waitHandleProjNo").val();

    $('#designDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#design_dispatch_panel_box").cgetContent("costDispatchingController/viewPage");
   			//隐藏遮罩
   			$('#designDispatchTable').hideMask();
   			$("#designDispatchTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
   			setTimeout(function(){
   			    $("#designDispatchTable").DataTable().columns.adjust();}, 0);
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
             serverSide:true,
             ajax:{
            	 url:"costDispatchingController/queryProject",
            	 type:'post',
            	 data: function(d){
                	$.each(searchData,function(i,k){
                		d[i] = k;
                	});
                 },
                 dataSrc: 'data'
             },
             select: true,    //支持多选
             columns: [
			    {"data":"projId",visible:false},
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"projStatusDes",orderable:false},
				{"data":"workDayDto"},
				{"data":"overdue",className:"none never",visible:false}
		 	 ],
			 columnDefs: [{
				 "targets":2,
				 //长字符串截取方法
				 render: $.fn.dataTable.render.ellipsis({
					length: 8, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				 })
			 }, {
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
		searchData.projNo = projNo;
		searchData.menuId = menuId;
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
	t.cgetDetail('designDispatchForm','costDispatchingController/viewDispatchResult','',detailBack);
	//$('#costerTable').cgetData(false,costerTableCallBack);
}

var detailBack=function(){
	$("#unitName").val($("#duName").val());
	$("#unitId").val($("#duId").val());
	$("#designDispatchForm").toggleEditState(true).styleFit();
	$(".editBtn").removeClass("hidden");
	$("input[name='depositGet'][value='0']").attr("checked","checked");

	if($.fn.DataTable.isDataTable('#costerTable')){
		//初始化过
		$("#costerTable").cgetData(false);//列表重新加载
	}else{
		costerTableinit();

	}
}

/**
 * 派遣成功后刷新设计员表格
 */
var updateDesignerBack=function(){
	//check if a table node is already a DataTable or not.确保不会重复初始化
	if ( $.fn.DataTable.isDataTable('#costerTable')) {
		designerData.deptId = '-1';
		//列表重新加载
		$('#costerTable').cgetData(false,costerTableCallBack);
	}else{
		//初始化设计员表格
		costerTableinit();
	}
}
var costerTableCallBack = function(){
	var len = $('#costerTable').DataTable().ajax.json().data ? $('#costerTable').DataTable().ajax.json().data.length : $('#costerTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	 }else{
		 $('.editBtn').removeClass("hidden");
	 }
}
/**
 * 加载造价员表格
 */
var costerTableinit= function() {
	if($("#designDispatchTable").DataTable().data().length==0){
		budgeterData.deptId ='-1'; 
	}
	costerTable= $('#costerTable').on( 'init.dt',function(){
	$('#costerTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'costDispatchingController/querySalesma',
            type:'post',
            data: function(d){
               	$.each(budgeterData,function(i,k){
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
            {"data":"costMemberId",className:"none never"},
  			{"data":"costMember"},
  			{"data":"costMemberTask",className:"text-right"}
	    ],
   });  
};


