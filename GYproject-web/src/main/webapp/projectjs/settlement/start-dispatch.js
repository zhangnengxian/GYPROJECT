var ddMytable;         //工程列表
var datacollecttable;//资料收集列表
var designertable;   //设计员列表
var accessoryData={};//资料列表查询条件
var searchData={};	 //查询条件
var dataPopTable;
var accessoryTable;
var designerData={};
/**
 * 加载工程列表
 */
var handleDesignDispatch = function() {
	"use strict";
    if ($('#startDispatchTable').length !== 0) {
    	ddMytable= $('#startDispatchTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//加载右侧页面
   			$("#start_dispatch_panel_box").cgetContent("auditStartDispatch/viewPage");
   			
   			//隐藏遮罩
   			$('#startDispatchTable').hideMask();
   			$("#startDispatchTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchMonitor();
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
            	 url:"auditStartDispatch/queryProject",
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
				{"data":"overdue",className:"none never"}
		 	 ],
			 columnDefs: [{
				 "targets": 0,
				 "visible":false
			 },{
					"targets":2,
					//长字符串截取方法
					render: $.fn.dataTable.render.ellipsis({
						length: 10, 	//截取多少字符（或汉字）
						end: true	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
					})
				}
			 ],
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
		var url = "#auditStartDispatch/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$("#startDispatchTable_filter input").on("change",function(){
		var projNo = $("#startDispatchTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		$("#startDispatchTable").cgetData(true,startDispatchCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#startDispatchTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};

/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	searchData = $("#searchForm_startDispatch").serializeJson();
	var projNo=$('#startDispatchTable_filter input').val();
	searchData.projNo=projNo;
    $("#startDispatchTable").cgetData(true,startDispatchCallBack);  //列表重新加载
}

var startDispatchCallBack = function(){
	var len = $('#startDispatchTable').DataTable().ajax.json().data ? $('#startDispatchTable').DataTable().ajax.json().data.length : $('#startDispatchTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#startDispatchForm").formReset();
		$("#startDispatchForm").toggleEditState(false).styleFit();
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
    $("#searchForm_startDispatch input").val("");
    //清空下拉框
    $("#projLtypeId  option[value=''] ").attr("selected",true);
}


/**
 * 选中行时，查询详述
 */
var trSelectedBack = function(v, i, index, t, json){
	//t.cgetDetail('startDispatchForm','startDispatch/viewDispatchResult','',detailBack); 
	t.cgetDetail('startDispatchForm','auditStartDispatch/viewDispatchResult','',detailBack);
	console.info("```111222222``");
	$('#designerTable').cgetData(false,designerTableCallBack);
	console.info("`````````");
}

var detailBack=function(){
	designerData.deptId=$("#duId").val();
	designerData.designStationId=$("#designStationId").val();
	$("#unitName").val($("#duName").val());
	$("#unitId").val($("#duId").val());
	$("#startDispatchForm").toggleEditState(true).styleFit();
	$(".editBtn").removeClass("hidden");
	$("input[name='depositGet'][value='0']").attr("checked","checked");
	/*accessoryData.projNo=$("#projNo").val();
	if($.fn.DataTable.isDataTable('#dataTable')){
		//初始化过
		$("#dataTable").cgetData(false);//列表重新加载
	}else{
		datatableinit();
	}	*/
}



/**
 * 加载资料收集列表
 */
/*var datatableinit= function() {
	"use strict";
	//accessoryData.projNo=$("#startDispatchTable tr td:eq(0)").text();
	datacollecttable= $('#dataTable').on( 'init.dt',function(){
	$('#dataTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [18],
        dom: 'Brt',
        buttons: [
        ],
        ajax: {
            url: 'accessoryCollect/queryAccessoryList',
            type:'post',
            data: function(d){
               	$.each(accessoryData,function(i,k){
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
  			{"data":"caiId",className:"none never"},
  			{"data":"alName"},
  			{"data":"alOperateTime"},
  			{"data":"alOperateCsr"}
  			
		],
		columnDefs: [{
		}]
   });
};*/

/**
 * 派遣成功后刷新设计员表格
 */
var updateDesignerBack=function(){
	//check if a table node is already a DataTable or not.确保不会重复初始化
	if ( $.fn.DataTable.isDataTable('#designerTable')) {
		designerData.deptId = '-1';
		//列表重新加载
		$('#designerTable').cgetData(false,designerTableCallBack);
	}else{
		//初始化设计员表格
		designertableinit();
	}
}
var designerTableCallBack = function(){
	var len = $('#designerTable').DataTable().ajax.json().data ? $('#designerTable').DataTable().ajax.json().data.length : $('#designerTable').DataTable().ajax.json().length;
	if(len<1){
		 //清空右侧记录
		$('.editBtn').addClass("hidden");
	 }else{
		 $('.editBtn').removeClass("hidden");
	 }
}

/**
 * 加载结算员表格
 */
var designertableinit= function() {
	//designerData.deptId = '-1';
	"use strict";
	designertable= $('#designerTable').on( 'init.dt',function(){
	$('#designerTable').hideMask();
    }).DataTable({
    	language: language_CN,
        lengthMenu: [10],
        //dom: 'Brt',
        dom: 'Brtip',
        buttons: [
        ],
        ajax: {
            url: 'auditStartDispatch/queryBudgetMember',
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
  			{"data":"surveyer",className:"surveyer"},
  			{"data":"surveyRegister",className:"text-right"},
//			{"data":"DESIGNDRAWING",className:"text-right"},
//			{"data":"DESIGNDRAWING",className:"text-right"}
  			{"data":"contractAmount",className:"text-right"}
	    ],
	    columnDefs: [{
			 'targets':2,
			 "render": function ( data, type, row, meta ) {
					if(data!=="" && data!==null){
						return parseFloat(data).toFixed(2);
					}else{
						return 0;
					}
				},
		 	}/*{
	        "targets":3,
	        "render":function ( data, type, row, meta ){
		    	var html="<input type='radio' name='singer'>";
		    	if(type=="display"){
		    		return html;
		    	}else{
		    		return data;
		    	}
		    }
		}*/]
       // ordering:false
   });  
};



/**
 * 初始化工程列表
 */
var startDispatch = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){    
        		handleDesignDispatch();
        	});
        }
    };
}();
//$(".attach-panel").initAttachOper({
//	collection: {
//		tableid:'startDispatchTable'
//	}
//});




