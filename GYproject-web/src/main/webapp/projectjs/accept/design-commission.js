
var designCommissionMytable;     //工程列表
var scaleTable;  //规模列表
var searchData={};//查询条件
var detailSearchData = {};//查询条件
//初始化待委托列表
var handleDesignCommission = function() {
	"use strict";
    if ($('#designCommissionTable').length !== 0) {
    	designCommissionMytable= $('#designCommissionTable').on( 'init.dt',function(){
    		//加载页面
    		$("#design_commission_panel_box").cgetContent("designCommission/viewPage");
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#designCommissionTable').hideMask();
   			$("#designCommissionTable_filter input").attr("placeholder","工程编号");
   			//绑定单击事件 弹出搜索框
   			searchPop();
   			//委托监听
   			commMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' },
                { text: '委托', className: 'btn-sm btn-confirm  business-tool-btn commBtn' }
            ],
			
            //启用服务端模式，后台进行分段查询、排序
			 serverSide:true,
             ajax: {
               url: 'designCommission/queryProject',
               type:'post',
               data: function(d){
                  	$.each(searchData,function(i,k){
                  		d[i] = k;
                  	});
                  	
               },
               dataSrc: 'data'
            },
            //ajax: "projectjs/contract/json/construct_contract.json",
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
	  			{"data":"projNo"}, 
				{"data":"projName"},
				{"data":"surveyDate"},
				{"data":"projStatusDes"},
				{"data":"overdue",className:"none never" }
			],
			columnDefs: [{
				"targets": 0,
				"visible":false
			},{
				"targets": 5,
				"visible":false
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
 * 选中时加载详情
 */
var trSelectedBack = function(v, i, index, t, json){
	 $("#designCommForm").removeClass("hidden");
	//$("#custDetailform").toggleEditState(false);
	//custManagementRight.jsp 维护按钮
	$(".editbtn").addClass("hidden");
	//参数含义： 表单id、url、id为选中行的第几列的值，若传空串，则默认为第一列、详述显示未完成后的回调函数
	trSData.t&&trSData.t.cgetDetail('designCommissionform','','',scaleDetailRefresh);
}

var scaleDetailRefresh = function(){
	var ltypeId = $(".projLtypeId").val();
	if(!ltypeId){
		detailSearchData.projId = "-1";
		$('#scaleCommTable').DataTable().ajax.reload();
		console.info("12121212");
		return;
	}
	ltypeId = ltypeId.split(",");
	$("input[name='projLtype']").attr("checked",false);
	for(var i=0;i<ltypeId.length;i++){
		$("input[name='projLtype'][value="+ltypeId[i]+"]").attr("checked","checked");
	}
	var len = $('#designCommissionTable').DataTable().ajax.json().data ? $('#designCommissionTable').DataTable().ajax.json().data.length : $('#designCommissionTable').DataTable().ajax.json().length;
	if(len>0){
		$("#designCommForm").removeClass("hidden");
		console.info($("#projId").val());
		detailSearchData.projId = $("#projId").val();
		if($.fn.DataTable.isDataTable('#scaleCommTable')){
			//初始化过
			$("#scaleCommTable").cgetData(false,scaleTableCallBack);//列表重新加载
		}else{
			sacletableinit();
		}
	}else{
		//$("#designCommForm").addClass("hidden");
		detailSearchData.projId = "-1";
		$('#scaleCommTable').DataTable().ajax.reload();
	}
}

var scaleTableCallBack = function(){
	 $('#designCommForm').toggleEditState(false);
}

/**
 * 弹屏监听方法
 */
var searchPop=function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#designCommission/projectSearchPopPage";
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone,
			cancel:clearForm
		});
	});
	
	//基础条件查询添加监听
	$('#designCommissionTable_filter input').on('change',function(){
		var projNo = $('#designCommissionTable_filter input').val();
		searchData = {};
		searchData.projNo = projNo;
		$('#designCommissionTable').cgetData(true,designTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#designCommissionTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == '13') {
	    	$(this).change();
	    }
	});			
};
/**
 * 查询弹出屏，点击确定后
 */
var searchDone = function(){
	//查询条件
	searchData = $("#searchForm_designCommission").serializeJson();
	var projNo = $("#designCommissionTable_filter input").val();
	searchData.projNo=projNo;
	//列表重新加载
    $("#designCommissionTable").cgetData(true,designTableCallBack);  
    //清空表单
    //$("#searchForm_designCommission input").val("");
    //清空下拉框
    //$("#projLtypeId  option[value=''] ").attr("selected",true);
}

var designTableCallBack = function(){
	var len = $('#designCommissionTable').DataTable().ajax.json().data ? $('#designCommissionTable').DataTable().ajax.json().data.length : $('#designCommissionTable').DataTable().ajax.json().length;
	if(len<1){
		//清空右侧记录
		$("#designCommissionform,#designCommForm")[0].reset();
		$('.editbtn').addClass('hidden');
		$('#designCommissionform').toggleEditState(false);
	}
	scaleDetailRefresh();
}

var clearForm=function(){};
//委托监听方法
var commMonitor = function() {
	$(".commBtn").on("click", function() {
		var len = $('#designCommissionTable').DataTable().ajax.json().data ? $('#designCommissionTable').DataTable().ajax.json().data.length : $('#designCommissionTable').DataTable().ajax.json().length;
		if (len!== 0) {
			// 切换可编辑状态
			$("#designCommissionform,#designCommForm").toggleEditState(true);
			$('#ocoNo').val($('#projNo').val());
			// 维护按钮显示出来
			$(".editbtn").removeClass("hidden");
		} else {
			alertInfo("委托任务数据为空！");
			// 切换不可编辑状态
			$("#designCommissionform,#designCommForm").toggleEditState(false);
			$(".toolBtn").addClass("hidden");
		}
	});
};


/**
 * 初始化规模列表
 */
var sacletableinit= function() {
	"use strict";
    if ($('#scaleCommTable').length !== 0) {
    	scaleTable= $('#scaleCommTable').on( 'init.dt',function(){
    		$('#scaleCommTable').hideMask();
    		$('#designCommForm input.field-editable').attr("readonly","readonly");
    		$('#designCommForm [type="checkbox"].field-editable').attr("disabled","disabled");
        }).DataTable({
           language: language_CN,
           lengthMenu: [18],
           dom: 'Brt',
           buttons: [
           ],
           //ajax: 'projectjs/accept/json/scale.json',
           //启用服务端模式，后台进行分段查询、排序
		   serverSide:true,
           ajax: {
               url: 'designCommission/queryScaleDetail',
               type:'post',
               data: function(d){
                  	$.each(detailSearchData,function(i,k){
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
           columns: [
	  			{"data":"projStypeDes"},
	  			{"data":"tonnage"},
	  			{"data":"searNum"},
	  			{"data":"num"},
	  			{"data":"houseNum"},
	  			{"data":"gasConsumption"},
//	  			{"data":"scaleId",className:"text-right"}
			],
			columnDefs: [
			            ],
			ordering:false
       });
   }
};



/**
 * 初始化工程列表
 */
var designCommission = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleDesignCommission();
        }
    };
}();