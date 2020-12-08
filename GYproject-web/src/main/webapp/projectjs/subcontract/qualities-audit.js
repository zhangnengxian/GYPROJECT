var showLength; //显示长度
//判断是否是手机端，如果是手机端显示8位，不是则显示10位
if(_inApk){
	showLength=12;
}else{
	showLength=15;
}
var searchData = {};
var menuId ="110602";
searchData.menuId = menuId;
var qualitiesAuditTable;
var handleDrawingAudit = function() {
	"use strict";
	searchData.projNo=$(".sessionprojNo").val();
    if ($('#qualitiesAuditTable').length !== 0) {
    	qualitiesAuditTable =$('#qualitiesAuditTable').on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,false);
   			//隐藏遮罩
   			$('#qualitiesAuditTable').hideMask();
   			$("#qualitiesAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			setTimeout(function(){
   			    $("#qualitiesAuditTable").DataTable().columns.adjust();
   			}, 0);
   			if (crossPageBus) {

   				getSidebarMenu(11, true);
   				checkSidebarMenu(crossPageBus.hash)
   				
					//跳转后销毁对象
   				crossPageBus = null
   			
			}
        }).on( 'draw.dt', function () {
        	auditBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            bStateSave:true,
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:false,
            ajax: {
                url: 'qualitiesJudgement/queryProject',
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
            select: true,  //支持多选
            columns: [
                {"data":"projId"},
                {"data":"projNo",responsivePriority:1},
                {"data":"projName",responsivePriority:3},
                {"data":"corpName",responsivePriority:5},
                {"data":"cuName",responsivePriority:6},
                {"data":"budgeterAudit",responsivePriority:4},
                {"data":"projAddr",responsivePriority:7},
                {"data":"firstSubmitAmount",className:"text-right",responsivePriority:6},
                {"data":"submitAmount",className:"text-right",responsivePriority:6},
                {"data":"projStatusDes",responsivePriority:7},
		        {"data":"workDayDto"},
		        {"data":"mrAuditLevel",responsivePriority:2},
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
				"targets":0,
				"visible":false
			},{
				"targets":2,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: 10, 	//截取多少字符（或汉字）
					end: false	//从后部开始截取，及从后部开始计数，并裁掉超出的内容
				})
			},
			{
				"targets":1,
				//长字符串截取方法
				render: $.fn.dataTable.render.ellipsis({
					length: showLength, 	//截取多少字符（或汉字）
					end: true	//false从后部开始截取，及从后部开始计数，并裁掉超出的内容,true从前部开始截取，及从前部开始计数，并裁掉超出的内容
				})
			},{
				'targets':7,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){							
							return parseFloat(data).toFixed(2);
							
						}else{
							return data;
						}
				 },
			},{
				'targets':8,
				 "render": function ( data, type, row, meta ) {
						if(data!=="" && data!==null){							
							return parseFloat(data).toFixed(2);
							
						}else{
							return data;
						}
				 },
			},
			{
				"targets": 11,
				"render": function ( data, type, row, meta ) {
					if(type==="display"){
						var html = getAuditLevelHtml(data,row.level,row.projId,"110602");
						return html;
					}else{
						return data;
					}
				}
			},{
				"targets":9,
				 "orderable":false
			},{
				"targets":11,
				 "orderable":false
			},{
				"targets":10,
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
var trSelectedBack = function(v, i, index, t, json){
	$('#projId').val(json.projId);

}
/**
 * 查询监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = '#qualitiesJudgement/projectSearchPopPage';
		//加载弹屏
		$("body").cgetPopup({
			title: '查询',
			content: url,
			accept: searchDone
		});
	});
	//基础条件查询添加监听
	$("#qualitiesAuditTable_filter input").on("change",function(){
		var projNo = $("#qualitiesAuditTable_filter input").val();
		searchData = {};
		searchData.projNo = projNo;
		searchData.menuId = menuId;
		$("#qualitiesAuditTable").cgetData(false,function(){
   			//跳转到审核页面
			auditBtnMonitor();
		}); //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#qualitiesAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
};

var searchDone = function(){
	searchData = $('#searchForm_sub').serializeJson();
	console.log(searchData);
	var projNo = $('#qualitiesAuditTable_filter input').val();
	searchData.projNo = projNo;
	searchData.menuId = menuId;
	//列表重新加载
    $('#qualitiesAuditTable').cgetData(false,function(){
		//跳转到审核页面
    	auditBtnMonitor();
	});
}

/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	$(document).off("click", ".level").on("click", ".level", function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var projId = $(this).attr("data-rid");
		var level = $(this).attr("data-level");
		var data = {"projId":projId,"currentLevel":level,"isAudit":isAudit};
		$("#ajax-content").cgetContent("qualitiesJudgement/auditPage",data);
	});
};


/**
 * 初始化列表
 */
var qualitiesAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	$.getScript("js/ellipsis.js").done(function(){
        		handleDrawingAudit();
        	});
        }
    };
}();