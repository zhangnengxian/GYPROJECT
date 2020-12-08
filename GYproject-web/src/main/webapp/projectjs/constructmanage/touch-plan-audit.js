
var searchData = {};
var handleTouchPlanAudit = function() {
	"use strict";
	var projId = getProjectInfo().projId;
	$("#projId").val(projId);
	searchData.projId = projId;
    if ($("#touchPlanAuditTable").length !== 0) {
    	$("#touchPlanAuditTable").on( 'init.dt',function(){
   			//默认选中第一行
    		$(this).bindDTSelected(trSelectedBack,true);
   			//隐藏遮罩
   			$('#touchPlanAuditTable').hideMask();
   			$("#touchPlanAuditTable_filter input").attr("placeholder","工程编号");
   			//搜索监听
   			searchMonitor();
   			//queryCheckRole();
        }).on( 'draw.dt', function () {
        	auditBtnMonitor();
        }).DataTable({
        	language: language_CN,
            lengthMenu: [18],
            dom: 'Bfrtip',
            buttons: [
                { text: '查询', className: 'btn-sm btn-query business-tool-btn searchBtn' }  
            ],
            //启用服务端模式，后台进行分段查询、排序
			serverSide:true,
            ajax: {
                url: 'touchPlanAudit/queryTouchPlan',
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
                {"data":"projNo",responsivePriority:1},
				{"data":"projName",responsivePriority:3},
				{"data":"constructionUnit",responsivePriority:4},
				{"data":"applyTpDate",responsivePriority:5},
				{"data":"tpDate",responsivePriority:6},
				{"data":"mrAuditLevel",responsivePriority:2}
			],
			columnDefs: [{
				"targets": 4,
				"render":function(data,type,row,meta){
					if(data !=="" && data!==null){
						return format(data);
					}else{
						return data;
					}
				}
			},{
				"targets":5,
				 "render": function (data, type, row, meta){
					 
						if(type==="display"){
							var html = getAuditLevelHtml(data,'4','');
							return html;
						}else{
							return data;
						}
				 }
			}],
			
        });
    }
};

var trSelectedBack = function(){
	
}


/**
 * 弹屏监听方法
 */
var searchMonitor = function(){
	$(".searchBtn").off("click").on("click",function(){
		var url = "#touchPlanAudit/searchPopPage?projId="+$("#projId").val();
		var popoptions = {
			title: '查询',
			content: url,
			accept: searchDone
		};
		//加载弹屏
		$("body").cgetPopup(popoptions);
	});
	
	//基础条件查询添加监听
	$('#touchPlanAuditTable_filter input').on('change',function(){
		var projNo = $('#touchPlanAuditTable_filter input').val();
		searchData = {};
		searchData.projId = $('#projId').val();
		searchData.projNo = projNo;
		$('#touchPlanAuditTable').cgetData(true,touchPlanAuditTableCallBack);  //列表重新加载
	});
	//基础条件查询添加回车事件
	$('#touchPlanAuditTable_filter input').on('keyup', function(event) {
	    if (event.keyCode == "13") {
	    	$(this).change();
	    }
	});
}
var touchPlanAuditTableCallBack=function(){
	
}
var selectBack = function(){
	
}

var searchDone = function(){
	
	searchData = $("#searchForm_touchPlantAudit").serializeJson();
	searchData.projId = getProjectInfo().projId;
    $("#touchPlanAuditTable").cgetData(true,selectBack);  //列表重新加载
    
}

/**
 * 加载审核屏
 */
var auditBtnMonitor = function(){
	$(".level").off("click").on("click",function(){
		var isAudit = "0";//未审核过
		if($(this).is(".disabled")) return false;
		if($(this).is(".passed, .refused")){
			isAudit = "1";//已审核过
		}
		var levelDes = $(this).text();
		var auditLevel = "1";
		if(levelDes === "一级"){
			auditLevel = "1";
		}else if(levelDes === "二级"){
			auditLevel = "2";
		}else if(levelDes === "三级"){
			auditLevel = "3";
		}else{
			auditLevel = "4";
		}
		var data = {"isAudit":isAudit,"auditLevel":auditLevel};
		$("#ajax-content").cgetContent("touchPlanAudit/auditPage",data);
	});
}

/**
 * 初始化列表
 */
var touchPlanAudit = function () {
	"use strict";
    return {
        //main function
        init: function () {
        	handleTouchPlanAudit();
        }
    };
}();