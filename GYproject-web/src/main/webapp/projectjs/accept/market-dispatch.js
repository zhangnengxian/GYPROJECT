var ddMytable;         //工程列表
var marketTable;   //市场营销员列表
var accessoryData={};//资料列表查询条件
var searchData={};	 //查询条件
searchData.menuId="110115";
var dataPopTable;
var accessoryTable;
var marketData={};
marketData.menuId = "110115";
/**
 * 加载工程列表
 */
var handleMarketDispatch = function() {
	"use strict";
    if ($('#marketDispatchTable').length !== 0) {
    	ddMytable= $('#marketDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载页面
   			$("#market_dispatch_panel_box").cgetContent("marketDispatch/viewPage");
   			//隐藏遮罩
   			$('#marketDispatchTable').hideMask();
   			$("#marketDispatchTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();

   			setTimeout(function(){
   			    $("#marketDispatchTable").DataTable().columns.adjust();
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
             serverSide:true,
             ajax:{
            	 url:"marketDispatch/queryProject",
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
				{"data":"projStatusDes"}
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
		var url = "#projectAccept/searchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$("#marketDispatchTable_filter input").on("change",function(){
		var projNo = $("#marketDispatchTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
        searchData.menuId ="110115";
		$("#marketDispatchTable").cgetData(true,marketDispatchCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#marketDispatchTable_filter input').on('keyup', function(event) {
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
	var projNo=$('#marketDispatchTable_filter input').val();
	searchData.projNo=projNo;
    $("#marketDispatchTable").cgetData(true,marketDispatchCallBack);  //列表重新加载
}

var marketDispatchCallBack = function(){
	var len = $('#marketDispatchTable').DataTable().ajax.json().data ? $('#marketDispatchTable').DataTable().ajax.json().data.length : $('#marketDispatchTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#marketDispatchForm").formReset();
		$("#marketDispatchForm").toggleEditState(false).styleFit();
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
	t.cgetDetail('marketDispatchForm','marketDispatch/viewDispatchResult','',detailBack);
}

var detailBack=function(){
    marketData.deptId=$("#deptId").val();
	$("#unitName").val($("#duName").val());
	$("#unitId").val($("#duId").val());
	$("#marketDispatchForm").toggleEditState(true).styleFit();
	$(".editBtn").removeClass("hidden");

	if($("#custName").val()==""){
		$(".noUser").addClass("hidden");
	}else{
		$(".noUser").removeClass("hidden");
	}
	
	if($.fn.DataTable.isDataTable('#marketTable')){
		//初始化过
		$("#marketTable").cgetData(false,marketTableCallBack);//列表重新加载
	}else{
        marketTableinit();
	}	
}



/**
 * 派遣成功后刷新设计员表格
 */
var updateMarketBack=function(){
	//check if a table node is already a DataTable or not.确保不会重复初始化
	if ($.fn.DataTable.isDataTable('#marketTable')) {
        marketData.deptId = '-1';
		//列表重新加载
		$('#marketTable').cgetData(false,marketTableCallBack);
	}else{
		//初始化设计员表格
        marketTableinit();
	}
}
var marketTableCallBack = function(){
	console.info("1111");

	var len = $('#marketTable').DataTable().ajax.json().data ? $('#marketTable').DataTable().ajax.json().data.length : $('#marketTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	}else{
		$('.editBtn').removeClass("hidden");
	}
}

/**
 * 派工列表
 */
var marketTableinit= function() {
	"use strict";
    marketData.deptId=$("#deptId").val()==''?'-1':$("#deptId").val();

    marketTable= $('#marketTable').on( 'init.dt',function(){
	$(this).bindDTSelected(trSelectedBack1,true);
	$('#marketTable').hideMask();
        marketTableCallBack();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        //dom: 'Brt',
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'marketDispatch/queryMarket',
            type:'post',
            data: function(d){
               	$.each(marketData,function(i,k){
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
  			{"data":"market",className:"market"},
  			{"data":"marketRegister",className:"text-right"},
	    ],
	    columnDefs: [{
			 "targets": 0,
			 "visible":false
		 }]
   });  
};

var trSelectedBack1=function(v, i, index, t, json){
	$("#marketId").val(json.staffId);
}

/**
 * 初始化工程列表
 */
var marketDispatch = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleMarketDispatch();
        	})
        }
    };
}();





